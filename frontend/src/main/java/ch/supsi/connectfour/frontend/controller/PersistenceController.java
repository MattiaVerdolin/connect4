package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.domain.*;
import ch.supsi.connectfour.frontend.view.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class PersistenceController {
    private static PersistenceController myself;

    private GameModelInterface gameModel = GameModel.getInstance();

    private InfoBarViewInterface infoBarView;
    private BoardViewInterface boardView;

    public static PersistenceController getInstance() {
        if (myself == null) {
            myself = new PersistenceController();
        }

        return myself;
    }

    public void open(Stage stage) {
        Properties properties = loadGame(stage);

        try {
            if (properties != null) {
                String tableString = properties.getProperty("Table").replaceAll("^\\[|\\]$", "");
                int currentPlayer = Integer.parseInt(properties.getProperty("CurrentPlayer"));

                String[] tableArray = tableString.trim().split(", ");

                ArrayList<Integer> arrayOfTable = new ArrayList<>();
                for (String s : tableArray) {
                    arrayOfTable.add(Integer.parseInt(s));
                }

                loadFromArray(arrayOfTable);
                gameModel.setTurn(currentPlayer);
                infoBarView.displayTurnText(gameModel.checkTurn());

                gameModel.getTable().print();
                boardView.updateBoard(gameModel.getTable()); // Aggiorna la vista
            }

        }catch (Exception ignored){}
    }

    //metodo che serve per convertire la matrice di gioco in un arraylist che verrà poi salvato nel file di salvataggio
    private ArrayList<Integer> arrayForSaving() {
        ArrayList<Integer> playerIds = new ArrayList<>();
        String[][] tableMatrix = gameModel.getMatrixFromTableModel();

        gameModel.getTable().print();

        Map<String, Integer> symbolToIdMap = gameModel.getPlayerIdBySymbol();

        for (int row = 0; row < tableMatrix.length; row++) {
            for (int col = 0; col < tableMatrix[row].length; col++) {
                String symbol = tableMatrix[row][col];
                if (symbol == null) {
                    playerIds.add(0);
                } else if (symbolToIdMap.containsKey(symbol)) {
                    playerIds.add(symbolToIdMap.get(symbol));
                }
            }
        }

        return playerIds;
    }

    //converte l'array salvato nella matrice corrispondente
    private void loadFromArray(ArrayList<Integer> array) {
        int rows = gameModel.getAltezza();
        int cols = gameModel.getLarghezza();

        String[][] tableMatrix = new String[rows][cols];

        Map<String, Integer> symbolToIdMap = gameModel.getPlayerIdBySymbol();
        Map<Integer, String> idToSymbolMap = new HashMap<>();

        // Creare una mappa inversa da id a simbolo
        for (Map.Entry<String, Integer> entry : symbolToIdMap.entrySet()) {
            idToSymbolMap.put(entry.getValue(), entry.getKey());
        }

        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int value = array.get(index++);
                if (value == 0) {
                    tableMatrix[row][col] = null; // Cella vuota
                } else {
                    tableMatrix[row][col] = idToSymbolMap.get(value); // Simbolo del giocatore
                }
            }
        }

        gameModel.getTable().setMatrix(tableMatrix); // Aggiorna la matrice del TableModel
    }

    public void saveGameAs(Stage stage) {

        gameModel = GameModel.getInstance();

        ArrayList<Integer> arrayOfTable = arrayForSaving();
        int currentPlayer = gameModel.getTurn();
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Save Game");

        // Impostare il filtro per l'estensione .connect4
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Connect Four Files (*.connect4)", "*.connect4");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrare la finestra di dialogo di salvataggio e ottenere il file selezionato
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {

            // Crea un oggetto Properties
            Properties properties = new Properties();

            // Converti l'array in una stringa
            String arrayAsString = Arrays.toString(arrayOfTable.toArray());

            // Aggiungi la stringa dell'array come valore
            properties.setProperty("CurrentPlayer", Integer.toString(currentPlayer));
            properties.setProperty("Table", arrayAsString);

            // Salva le proprietà nel file selezionato
            try (FileOutputStream output = new FileOutputStream(file)) {
                properties.store(output, "Array saved as a string");
            } catch (IOException e) {
               infoBarView.saveError();
            } finally {
                gameModel.setAlreadySaved(true); //la partita è stata salvata
                gameModel.setPath(file.getPath()); //salvo la path di salvataggio
            }
        } else {
            infoBarView.saveCancelled();
        }
    }

    public void save() {

        gameModel = GameModel.getInstance();

        ArrayList<Integer> arrayOfTable = arrayForSaving();
        int currentPlayer = gameModel.getTurn();

        if (gameModel.isAlreadySaved()) {

            // Crea un oggetto Properties
            Properties properties = new Properties();

            // Converti l'array in una stringa
            String arrayAsString = Arrays.toString(arrayOfTable.toArray());

            // Aggiungi la stringa dell'array come valore
            properties.setProperty("CurrentPlayer", Integer.toString(currentPlayer));
            properties.setProperty("Table", arrayAsString);

            // Salva le proprietà nel file selezionato
            try (FileOutputStream output = new FileOutputStream(gameModel.getPath())) {
                properties.store(output, "Array saved as a string");
            } catch (IOException e) {
                infoBarView.saveError();
            }
        } else {
            infoBarView.saveFirst();
        }
    }

    private Properties loadGame(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Game");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Connect Four Files (*.connect4)", "*.connect4");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(stage);

        Properties properties = new Properties();

        if (file != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                properties.load(bufferedReader);

            } catch (IOException e) {
                infoBarView.loadError();
            }
        } else {
            infoBarView.loadCancelled();
        }

        return properties;
    }

    public void setInfoBarView(InfoBarView infoBarView) {
        this.infoBarView = infoBarView;
    }

    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
        //aggiorno l'interfaccia di gioco alla creazione (risultato atteso: griglia vuota)
        this.boardView.updateBoard(gameModel.getTable());
    }
}
