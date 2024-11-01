import Interfaces.GameBoard;

/**
 * add description
 *
 * @author      Antoine Tessier  
 * @version     1.0
 * @since       1.0 (26 October 2024)
 */


public class IntegerBoard  implements GameBoard<Cell>{

    private Cell[][] gameBoard;
    private Integer[][] inputBoard;

    public IntegerBoard( Integer[][] inputBoard ) {
        this.inputBoard = inputBoard;
        this.gameBoard = new Cell[9][9];
        makeBoard();
    }

    public void makeBoard() throws IndexOutOfBoundsException {
        try {
            int i = 0;
            for (Integer[] rows : inputBoard) {
                int j = 0;
                for (int cell : rows) {
                    this.gameBoard[i][j++] = new Cell( cell );
                }
                i++;
            }
        }
        catch (IndexOutOfBoundsException e) { 
            throw new IndexOutOfBoundsException("Board size is to big");
        }
        
    }

    @Override
    public Cell getCell(int x, int y) throws IndexOutOfBoundsException {
        return (this.gameBoard[y][x]);
    }

    @Override
    public void setCell(int x, int y, Cell value) throws IndexOutOfBoundsException {
        this.gameBoard[y][x] = value;
    }

    public Cell[] getRow( int row ) { return this.gameBoard[row]; }
    
    public Cell[] getColumn( int column ) {
        Cell[] rcolumn = new Cell[9];
        int i = 0;
        for (Cell[] rows : this.gameBoard) {
            rcolumn[i++] = rows[column];
        }
        return rcolumn;
    }

    public Cell[] getBlock( int blockRow, int blockColumn ) {
        Cell[] block = new Cell[9];
        int n = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                block[n++] = this.gameBoard[blockRow + i][blockColumn + j]; 
            }
        }
        return block;
    }

    @Override
    public int getHeight() { return (this.gameBoard.length); }

    @Override
    public int getWidth() { return this.gameBoard[0].length; }

    @Override
    public void display() { System.out.println(this.gameBoard); }

}
