package ch.supsi.connectfour.backend.domain;

public class TableModel {
    private String[][] matrix;

    private final int altezza = 6;
    private final int larghezza = 7;
    public TableModel() {
        matrix = new String[altezza][larghezza];
    }

    public String[][] getTable() {
        return matrix;
    }

    public void insertCoin(int riga, int colonna, String symbol) {
        matrix[riga][colonna] = symbol;
        print();
    }


    public boolean availableColumn(int colonna) {
        //se la colonna è piena ritorno senza fare nulla
        if(matrix[0][colonna] != null) {
            return false;
        }else{
            return true;
        }
    }

    public int freeRow(int colonna) {
        //scorro la colonna a partire dall'alto fino a trovare l'ultimo buco libero
        for (int i = matrix.length - 1; i >= 0; i--) {
            // Se trovo una cella vuota, restituisco la posizione Y
            if (matrix[i][colonna] == null) {
                return i;
            }
        }

        return - 1;
    }

    public void print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == null) {
                    System.out.print(" - ");
                }else{
                    System.out.print(" "+matrix[i][j] + " ");
                }
            }
            // Vai a capo dopo ogni riga
            System.out.println();
        }
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    public int getAltezza() {
        return altezza;
    }

    public int getLarghezza() {
        return larghezza;
    }
}
