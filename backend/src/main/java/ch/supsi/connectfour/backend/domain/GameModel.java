package ch.supsi.connectfour.backend.domain;

import java.io.*;
import java.util.*;

public class GameModel implements GameModelInterface {

    private static GameModel myself;

    private final int numPlayers = 2;

    private final ArrayList<PlayerModel> players;
    private int turn;
    private final TableModel table;

    private static String path;
    private boolean alreadySaved = false;

    public GameModel() {
        players = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {
            players.add(new PlayerModel(i+1));
        }

        table = new TableModel();
        turn = new Random().nextInt(1,2);

        myself = this;
    }

    public static GameModel getInstance() {
        if (myself == null) {
            myself = new GameModel();
        }

        return myself;
    }

    @Override
    public boolean move(int colonna) {
        if (table.availableColumn(colonna)) {
            table.insertCoin(table.freeRow(colonna), colonna, checkTurn().getSymbol());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean tie(){

        for (int i = 0; i < getLarghezza(); i++) {
            if(table.availableColumn(i)){
                return false;
            }
        }

        return true;

    }

    @Override
    public boolean youWon() {
        String[][] matrix = table.getTable();
        PlayerModel actualPlayer = checkTurn();
        String actualSymbol = actualPlayer.getSymbol();
        int righe = matrix.length;
        int colonne = matrix[0].length;

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false; // Matrice vuota, nessuna combinazione vincente possibile
        }

        // Controllo sulle righe
        for (int riga = 0; riga < righe; riga++) {
            for (int colonna = 0; colonna <= colonne - 4; colonna++) {
                if (matrix[riga][colonna] != null &&
                        matrix[riga][colonna + 1] != null &&
                        matrix[riga][colonna + 2] != null &&
                        matrix[riga][colonna + 3] != null &&
                        matrix[riga][colonna].equals(actualSymbol) &&
                        matrix[riga][colonna + 1].equals(actualSymbol) &&
                        matrix[riga][colonna + 2].equals(actualSymbol) &&
                        matrix[riga][colonna + 3].equals(actualSymbol)) {

                    actualPlayer.setVictory(true);
                    return true;
                }
            }
        }

        // Controllo sulle colonne
        for (int colonna = 0; colonna < colonne; colonna++) {
            for (int riga = 0; riga <= righe - 4; riga++) {
                if (matrix[riga][colonna] != null &&
                        matrix[riga + 1][colonna] != null &&
                        matrix[riga + 2][colonna] != null &&
                        matrix[riga + 3][colonna] != null &&
                        matrix[riga][colonna].equals(actualSymbol) &&
                        matrix[riga + 1][colonna].equals(actualSymbol) &&
                        matrix[riga + 2][colonna].equals(actualSymbol) &&
                        matrix[riga + 3][colonna].equals(actualSymbol)) {

                    actualPlayer.setVictory(true);
                    return true;
                }
            }
        }

        // Controllo sulle linee oblique ascendenti \
        for (int riga = 3; riga < righe; riga++) {
            for (int colonna = 0; colonna <= colonne - 4; colonna++) {
                if (matrix[riga][colonna] != null &&
                        matrix[riga - 1][colonna + 1] != null &&
                        matrix[riga - 2][colonna + 2] != null &&
                        matrix[riga - 3][colonna + 3] != null &&
                        matrix[riga][colonna].equals(actualSymbol) &&
                        matrix[riga - 1][colonna + 1].equals(actualSymbol) &&
                        matrix[riga - 2][colonna + 2].equals(actualSymbol) &&
                        matrix[riga - 3][colonna + 3].equals(actualSymbol)) {

                    actualPlayer.setVictory(true);
                    return true;
                }
            }
        }

        // Controllo sulle linee oblique discendenti /
        for (int riga = 0; riga <= righe - 4; riga++) {
            for (int colonna = 0; colonna <= colonne - 4; colonna++) {
                if (matrix[riga][colonna] != null &&
                        matrix[riga + 1][colonna + 1] != null &&
                        matrix[riga + 2][colonna + 2] != null &&
                        matrix[riga + 3][colonna + 3] != null &&
                        matrix[riga][colonna].equals(actualSymbol) &&
                        matrix[riga + 1][colonna + 1].equals(actualSymbol) &&
                        matrix[riga + 2][colonna + 2].equals(actualSymbol) &&
                        matrix[riga + 3][colonna + 3].equals(actualSymbol)) {

                    actualPlayer.setVictory(true);
                    return true;
                }
            }
        }

        return false; // Nessuna combinazione vincente trovata
    }

    @Override
    public void changeTurn(){
        //fai mossa
        if(turn == 1)
            turn+=1;
        else
            turn-=1;
    }

    @Override
    public PlayerModel checkTurn(){
        return players.get(turn-1);
    }

    @Override
    public PlayerModel getPlayer1() {
        return players.get(0);
    }

    @Override
    public PlayerModel getPlayer2() {
        return players.get(1);
    }

    @Override
    public ArrayList<PlayerModel> getPlayers() {
        return players;
    }

    @Override
    public TableModel getTable() {
        return table;
    }

    @Override
    public int getTurn() {
        return turn;
    }

    @Override
    public int getAltezza(){
        return table.getAltezza();
    }

    @Override
    public int getLarghezza(){
        return table.getLarghezza();
    }

    @Override
    public boolean partitaTerminata(){
        for (PlayerModel player : players) {
            if (player.isVictory()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Map<String, Integer> getPlayerIdBySymbol() {
        Map<String, Integer> symbolToIdMap = new HashMap<>();
        for (PlayerModel player : players) {
            symbolToIdMap.put(player.getSymbol(), player.getId());
        }
        return symbolToIdMap;
    }

    @Override
    public String[][] getMatrixFromTableModel(){
        return table.getTable();
    }

    public void setMatrix(String[][] matrix){
        table.setMatrix(matrix);
    }

    @Override
    public void setTurn(int turn){
        this.turn = turn;
    }

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        GameModel.path = path;
    }

    public boolean isAlreadySaved() {
        return alreadySaved;
    }

    public void setAlreadySaved(boolean alreadySaved) {
        this.alreadySaved = alreadySaved;
    }
}
