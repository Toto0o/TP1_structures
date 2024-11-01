/**
 * Cell is an object for each Sudoku cell;
 *  it permits a single value or a list of possible values
 * 
 * @author Antoine Tessier
 * @since 1.0
 * @version 1.0 (29 octobre)
 *  
 */

 import java.util.ArrayList;


public class Cell {

    private int row, col, value;
    private ArrayList<Integer> possible;
    private boolean locked;

    public Cell( int row, int col, boolean locked) {
        this.row = row; /* Row */
        this.col = col; /* Column */
        this.locked = locked; /* If number value is certain, locked is true; else cell is a list of possible values and locked is false */
        this.possible = new ArrayList<>();
    }

    public int getRow() { return this.row; }

    public int getColumn() { return this.col; }

    public int getValue() { return this.value; }

    public ArrayList<Integer> getPossible() { return this.possible; }

    public boolean isLocked() { return this.locked; }

    public void setRow( int row) { this.row = row; }

    public void setCol( int col ) { this.col = col; }

    public void setValue( int value ) { this.value = value; }

    public void setLocked( boolean locked ) { this.locked = locked; }

    public void setPossible ( int[] possible ) { for (int i : possible) { this.possible.add(i); } }

    public void addPossibility( int possibility ) { this.possible.add(possibility); }



}
