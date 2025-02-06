package ch.supsi.connectfour.backend.domain;

public class PlayerModel {
    private String name;
    private String color;
    private String symbol;
    private boolean victory;
    private final int id;

    public PlayerModel(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public void setPreferences(String name, String symbol, String color) {
        this.name = name;
        this.symbol = symbol;
        this.color = color;
    }

    public int getId() {
        return id;
    }
}
