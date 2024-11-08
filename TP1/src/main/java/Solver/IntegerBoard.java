package Solver;
import java.util.ArrayList;
import java.util.Iterator;

import Solver.Interfaces.GameBoard;

/**
 * add description
 *
 * @author Antoine Tessier
 * @version 1.0
 * @since 1.0 (26 October 2024)
 */
public class IntegerBoard implements GameBoard<Cell> {

    private final Cell[][] gameBoard;
    private final Integer[][] inputBoard;

    public IntegerBoard(Integer[][] inputBoard) {
        this.inputBoard = inputBoard;
        this.gameBoard = new Cell[9][9];
        makeBoard();
    }

    public final void makeBoard() throws IndexOutOfBoundsException {
        try {
            int row = 0;
            for (Integer[] rows : inputBoard) {
                int col = 0;
                for (int value : rows) {
                    Cell cell = new Cell(row, col, value);
                    this.setCell(row, col++, cell);
                    /* System.out.println(Integer.toString(cell.getRow()) + " " + cell.getColumn()); */
                }
                row++;
            }

        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Board size is to big");
        }
    }

    @Override
    public Cell getCell(int row, int col) throws IndexOutOfBoundsException {
        return (this.gameBoard[row][col]);
    }

    @Override
    public void setCell(int row, int col, Cell value) throws IndexOutOfBoundsException {
        this.gameBoard[row][col] = value;
    }

    public Cell[] getRow(int row) {
        return this.gameBoard[row];
    }

    public Cell[] getColumn(int column) {
        Cell[] rcolumn = new Cell[9];
        int i = 0;
        for (Cell[] rows : this.gameBoard) {
            rcolumn[i++] = rows[column];
        }
        return rcolumn;
    }

    public Cell[] getBlock(int blockRow, int blockColumn) {
        Cell[] block = new Cell[9];
        int n = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                block[n++] = this.gameBoard[(blockRow / 3) * 3 + i][(blockColumn / 3) * 3 + j];
            }
        }
        return block;
    }

    @Override
    public int getHeight() {
        return (this.gameBoard.length);
    }

    @Override
    public int getWidth() {
        return this.gameBoard[0].length;
    }

    @Override
    public void display() {
        Iterator<Cell> iterator = this.iterator();
        ArrayList<Integer> col = new ArrayList<>(9);
        int index = 0;
        while (iterator.hasNext()) {
            while (index < 9) {
                col.add(iterator.next().getValue());
                index++;
            }
            System.out.println(col);
            index = 0;
            col = new ArrayList<>(9);
        }

    }   


    /* Inner CellIterator class */

    private class CellIterator implements Iterator<Cell> {

        private int row = 0, col = 0;

        @Override
        public boolean hasNext() {
            return this.row < 9 && col < 9;
        }

        @Override
        public Cell next() {
            if (!this.hasNext()) { return null; }
            Cell cell = gameBoard[row][col++];
            if (col >= 9) { col = 0; row++; }
            return cell;
        }

    }

    public Iterator<Cell> iterator() { return new CellIterator(); }

}
