
/**
 * Cell is an object for each Sudoku cell;
 *  it permits a single value or a list of possible values
 *
 * @author Antoine Tessier
 * @since 1.0
 * @version 1.0 (29 octobre)
 *
 */
import Interfaces.*;
import java.util.ArrayList;

public class Cell {

    private int value, row, col;
    private ArrayList<Integer> possible;
    private Position position;
    private boolean fixed;

    public Cell(int col, int row, int value) {
        this.value = value;
        this.col = col;
        this.row = row;
        this.possible = new ArrayList();
        this.position = null;
        this.fixed = !(value == 0);
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
        this.possible = new ArrayList();
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isFixed() {
        return this.fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }



}
