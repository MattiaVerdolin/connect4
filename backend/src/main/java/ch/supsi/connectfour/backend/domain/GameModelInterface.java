package ch.supsi.connectfour.backend.domain;
import java.util.ArrayList;
import java.util.Map;

public interface GameModelInterface {
    boolean move(int colonna);
    boolean tie();
    boolean youWon();
    void changeTurn();
    PlayerModel checkTurn();
    PlayerModel getPlayer1();
    PlayerModel getPlayer2();
    TableModel getTable();
    boolean partitaTerminata();
    int getTurn();
    ArrayList<PlayerModel> getPlayers();
    Map<String, Integer> getPlayerIdBySymbol();
    String[][] getMatrixFromTableModel();
    void setTurn(int turn);
    String getPath();
    void setPath(String path);
    boolean isAlreadySaved();
    void setAlreadySaved(boolean alreadySaved);

    int getAltezza();

    int getLarghezza();
}
