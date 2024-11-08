
import Interfaces.*;
import java.util.Iterator;
import java.util.List;

/**
 * add description
 *
 * @author Antoine Tessier
 * @version 1.0
 * @since 1.0 (1 Novembre 2024)
 */
public class SudokuSolver implements GameSolver {

    // attributes
    IntegerBoard board;
    IntegerBoard solution;
    GameTree tree;

    // constructor
    public SudokuSolver(Integer[][] board) {
        this.board = new IntegerBoard(board);
        this.solution = new IntegerBoard(board);
        this.tree = new GameTree(this.board);
    }

    // mandatory GameSolver interface methods
    @Override
    public boolean solve() {
        this.setUp();
        if (this.solveBoard( this.board, this.tree.root() )) {
            this.printSolution();
            return true;
        } else {
            System.out.println("There is no solution to this board;");
            this.board.display();
            return false;
        }
    }

    @Override
    public void printSolution() {
        this.solution.display();
    }

    // validate an insertion in the board
    public boolean isValidPlacement(int row, int col, Integer value) {
        for (Cell verifyRow : this.board.getRow(row)) {
            if (verifyRow.getValue() == value) {
                return false;
            }
        }

        for (Cell verifyColumn : this.board.getColumn(col)) {
            if (verifyColumn.getValue() == value) {
                return false;
            }
        }

        for (Cell verifyBlock : this.board.getBlock(row, col)) {
            if (verifyBlock.getValue() == value) {
                return false;
            }
        }
        return true;
    }

    // actual solver
    private boolean solveBoard( IntegerBoard board, Position<IntegerBoard> parent ) {
        if (isCleared(board)) {
            return true;
        }
        IntegerBoard newBoard = board;
        Iterator<Cell> iterator = newBoard.iterator();
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            int row= cell.getRow();
            int col = cell.getColumn();
            if (cell.getPossible().size() == 1) {
                cell.setFixed(true);
                int value = cell.getPossible().get(0);
                cell.setValue(value);
            }

        }
        this.solution = this.board;
        return false;
    }

    private boolean isCleared(IntegerBoard board) {
        Iterator<Cell> iterator = board.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().isFixed())
            return false;
        }
        return true;
    }
    

    private Position<IntegerBoard> getPosition() {
        List<Position<IntegerBoard>> positions = (List<Position<IntegerBoard>>) this.tree.positions();
        return positions.get(positions.size() - 1);
    }

    private void setUp() {
        // Add root position to cell already filled out (from the input);
        // Set possible values to each unfilled cell;
        Iterator<Cell> iterator = this.board.iterator();
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            if (cell.getValue() != 0) {
                cell.setPosition(this.tree.root());
            } else {
                for (int i = 1; i < 10; i++) {
                    if (isValidPlacement(cell.getRow(), cell.getColumn(), i)) {
                        cell.addPossibility(i);
                    }
                }
                if (cell.getPossible().size() == 1) {
                    System.out.println("Size = 1 on setup");
                    cell.setFixed(true);
                    int value = cell.getPossible().get(0);
                    System.out.println(value);
                    cell.setValue(value);
                    cell.clearPossible();
                }
            }
        }
    }
}
