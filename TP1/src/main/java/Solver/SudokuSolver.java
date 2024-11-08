package Solver;
import java.util.Iterator;

import Solver.Interfaces.GameSolver;
import Solver.Interfaces.Position;
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
        if (this.solveBoard(this.tree.root())) {
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
    public boolean isValidPlacement(int row, int col, Integer value, IntegerBoard vboard) {
        for (Cell verifyRow : vboard.getRow(row)) {
            if (verifyRow.getValue() == value) {
                return false;
            }
        }

        for (Cell verifyColumn : vboard.getColumn(col)) {
            if (verifyColumn.getValue() == value) {
                return false;
            }
        }

        for (Cell verifyBlock : vboard.getBlock(row, col)) {
            if (verifyBlock.getValue() == value) {
                return false;
            }
        }
        return true;
    }

    // actual solver
    private boolean solveBoard(Position<IntegerBoard> node) {
        IntegerBoard currentBoard = node.getElement();
        if (!isCleared(currentBoard)) {
            Iterator<Cell> iterator = currentBoard.iterator();
        
            while (iterator.hasNext()) {
                Cell cell = iterator.next();
                if (cell.getValue() == 0) {
                    for (Integer possibility : cell.getPossible()) {
                        int row = cell.getRow();
                        int col = cell.getColumn();
                        if (this.isValidPlacement(row, col, possibility, currentBoard)) {
                            cell.setValue(possibility);
                            Position<IntegerBoard> newBoard = 
                                this.tree.setChildren(node, currentBoard);
                            currentBoard.display();
                            System.out.println("\n");
                            if (!solveBoard(newBoard)) {
                                cell.setValue(0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
        
            
    }

    private boolean isCleared(IntegerBoard cboard) {
        Iterator<Cell> iterator = cboard.iterator();
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            int row = cell.getRow();
            int col = cell.getColumn();
            if (!isValidPlacement(row, col, cell.getValue(), cboard))
            return false;
        }
        return true;
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
                    if (isValidPlacement(cell.getRow(), cell.getColumn(), i, this.board)) {
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

    public IntegerBoard getBoard() {
        return this.board;
    }
}
