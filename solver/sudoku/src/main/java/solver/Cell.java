package solver;
import java.util.ArrayList;

public class Cell {

    private int value, row, col;
    private ArrayList<Integer> possible;

    public Cell(int row, int col, int value) {
        this.value = value;
        this.col = col;
        this.row = row;
        this.possible = new ArrayList<>(9);
    }

    public Cell(int row, int col, int value, ArrayList<Integer> possible) {
        this(row, col, value);
        this.possible = possible;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.col;
    }

    public void setColumn(int col) {
        this.col = col;
    }

    public ArrayList<Integer> getPossible() {
        return this.possible;
    }

    public void setPossible(ArrayList<Integer> possible) {
        this.possible.addAll(possible);
    }

    public void addPossibility(int possibility) {
        this.possible.add(possibility);
    }

    public void removePossibility(int possibility) {
        this.possible.remove(Integer.valueOf(possibility));
    }

    public void clearPossible() {
        this.possible = new ArrayList<>(9);
    }

}
