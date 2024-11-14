package solver;

import java.util.Arrays;
import java.util.Iterator;
import solver.interfaces.GameBoard;

public class IntegerBoard implements GameBoard<Cell>, Iterable<Cell> {

    private Cell[][] board;

    private int emptyCells;

    public IntegerBoard(Integer[][] input) {
        this.board = new Cell[9][9];
        this.emptyCells = 0;
        makeBoard(input);
    }

    public IntegerBoard(Cell[][] input) {
        this.board = new Cell[9][9];
        for (Cell[] row : input) {
            for (Cell cell : row) {
                this.board[cell.getRow()][cell.getColumn()] = 
                    new Cell(cell.getRow(), cell.getColumn(), cell.getValue(), cell.getPossible());
            }
        }
    }

    private void makeBoard(Integer[][] input) {
        int row = 0;
        while (row < 9) {
            int col = 0;
            while (col < 9) {
                int value = input[row][col];
                if (value == 0) {
                    this.emptyCells++;
                }
                this.board[row][col] = new Cell(row, col, value);
                col++;
            }
            row++;
        }
    }

    private class CellIterator implements Iterator<Cell> {

        private int row, col = 0;

        @Override
        public boolean hasNext() {
            return this.row < 9 && this.col < 9;
        }

        @Override
        public Cell next() {
            if (!hasNext()) {
                return null;
            }
            Cell cell = getCell(row, col);
            col++;
            if (col == 9) {
                col = 0;
                row++;
            }
            return cell;
        }

    }

    @Override
    public Iterator<Cell> iterator() {
        return new CellIterator();
    }

    @Override
    public Cell getCell(int row, int col) {
        return this.board[row][col];
    }

    @Override
    public void setCell(int row, int col, Cell cell) {
        this.board[row][col] = cell;
    }

    @Override
    public void display() {
        Integer[] row = new Integer[9];
        int col = 0;
        for (Cell cell : this) {
            row[col++] = cell.getValue();
            if (col == 9) {
                System.out.println(Arrays.toString(row));
                col = 0;
            }
        }
        System.out.println("\n");
    }

    @Override
    public int getHeight() {
        int height = 0;
        for (Cell[] cell : this.board) {
            height++;
        }
        return height;
    }

    @Override
    public int getWidth() {
        int width = 0;
        for (Cell cell : this.board[0]) {
            width++;
        }
        return width;
    }

    public Cell[] getRow(int row) {
        return this.board[row];
    }

    public Cell[] getColumn(int col) {
        Cell[] column = new Cell[9];
        int p = 0;
        for (Cell[] row : this.board) {
            column[p++] = row[col];
        }
        return column;
    }

    public Cell[] getBlock(int row, int col) {
        Cell[] block = new Cell[9];
        int p = 0;

        row = 3 * (row / 3);
        col = 3 * (col / 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                block[p++] = getCell(row + i, col + j);
            }
        }
        return block;
    }

    public int getEmptyCells() {
        return this.emptyCells;
    }

    public void setEmptyCells(int emptyCells) {
        this.emptyCells = emptyCells;
    }

    public Cell[][] getRaw() {
        return this.board;
    }

}
