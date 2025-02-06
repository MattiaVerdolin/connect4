package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.domain.PlayerModel;
import ch.supsi.connectfour.backend.domain.TableModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.*;


public class BoardView implements BoardViewInterface {

    private final Map<String, Color> signColor = new HashMap<>();
    @FXML
    private GridPane board;

    // Metodo per aggiornare il testo della cella
    private void updateCell(int x,int y, String symbol, Color color) {
        for (Node node : board.getChildren()) {
            if(GridPane.getRowIndex(node) == x && GridPane.getColumnIndex(node) == y){
                for (Node mnode : ((AnchorPane) node).getChildren()) {
                    Label label = (Label) mnode;

                    Text text = new Text(symbol);
                    text.setFill(color);

                    label.setText(""); //elimino quello che c'è scritto
                    label.setGraphic(text);

                    break;
                }
            }
        }
    }

    @Override
    public void updateBoard(TableModel table){
        String[][] matrix = table.getTable();
        for(int riga = 0; riga < matrix.length; riga++){
            for(int colonna = 0; colonna < matrix[riga].length; colonna++){
                if(matrix[riga][colonna] != null){

                    updateCell(riga,colonna,matrix[riga][colonna], signColor.get(matrix[riga][colonna]));
                }else{

                    updateCell(riga,colonna,"-", Color.BLACK);
                }
            }
        }
    }

    @Override
    public void addSignColor(ArrayList<PlayerModel> players){
        Color color;

        for (int i = 0; i < players.size(); i++) {
            switch(players.get(i).getColor().toUpperCase()) {
                case "ROSSO":
                case "RED":
                    color = Color.RED;
                    break;
                case "GIALLO":
                case "YELLOW":
                    color = Color.ORANGE;
                    break;
                default:
                    color = Color.BLACK;
                    break;
            }

            signColor.put(players.get(i).getSymbol(), color);
        }

    }

    @Override
    public void clearMap(){
        signColor.clear();
    }
}