package solver;
import java.util.ArrayList;
import solver.interfaces.*;

public class SudokuSolver implements GameSolver {

    // attributes
    IntegerBoard board;
    IntegerBoard solution;
    GameTree tree;

    // constructor
    public SudokuSolver( IntegerBoard board ) {
        this.board = board;
        this.solution = board;
        
    }

    // mandatory GameSolver interface methods
    @Override
    public boolean solve() {
        this.setUp(this.board);
        this.tree = new GameTree(this.board);
        if (solveBoard()) {
            System.out.println("A solution was found!");
            return true;
        }
        else {
            System.out.println("No solution was found");
            return false;
        }
    }

    @Override
    public void printSolution() {
        this.solution.display();
    }

    // validate an insertion in the board
    public boolean isValidPlacement( int row, int col, int value, IntegerBoard board ) {
        // Assuming value != 0

        // Check if value is in row
        for (Cell check : board.getRow(row)) {
            if (check.getValue() == value && check.getRow() != row) return false;
        }

        // Check if value is in column
        for (Cell check : board.getColumn(col)) {
            if (check.getValue() == value && check.getColumn() != col) return false;
        }

        // Check if value is in block
        for (Cell check : board.getBlock(row, col)) {
            if (check.getValue() == value && check.getRow() != row && check.getColumn() != col) return false;
        }

        return true;
    }

    // actual solver
    private boolean solveBoard() {
        makeTree(this.tree.root());
        for (Position<IntegerBoard> p : this.tree.breadthfirst()) {
            if (this.tree.isExternal(p)) {
                p.getElement().display();
                if (isCleared(p.getElement())) {
                    this.solution = p.getElement();    
                    return true;
                }
            }
        } 
        return false;
    }

    private void makeTree(Position<IntegerBoard> node) {
        
        if (node.getElement().getEmptyCells() == 0 ) return;

        IntegerBoard currentBoard = node.getElement();

        Cell nextCell = nextEmptyCell(node.getElement());

        if (nextCell != null) {

            for (int possibility : nextCell.getPossible()) {
            
                if (isValidPlacement(nextCell.getRow(), nextCell.getColumn(), possibility, currentBoard)) {
    
                    IntegerBoard copy = copy(currentBoard);
    
                    copy.getCell(nextCell.getRow(), nextCell.getColumn()).setValue(possibility);
    
                    copy.setEmptyCells(copy.getEmptyCells() - 1);
    
                    Position<IntegerBoard> nextNode = this.tree.setChildren(node, copy);
    
                    makeTree(nextNode);
                }
            }
        }

    }

    private Cell nextEmptyCell(IntegerBoard eboard) {
        for (Cell cell : eboard) {
            if (cell.getValue() == 0) return cell;
        }
        return null;
    }


    public boolean isCleared(IntegerBoard fboard) {
        for (Cell cell : fboard) {
            int row = cell.getRow();
            int col = cell.getColumn();
            if (!isValidPlacement(row, col, cell.getValue(), fboard)) {
                return false;
            }
        }
        return true;
    }

    private IntegerBoard copy( IntegerBoard cboard) {
        return new IntegerBoard(cboard.getRaw());
    }

    private void setUp( IntegerBoard board ) {
        for (Cell cell : board) {
            if (cell.getValue() == 0) {
                ArrayList<Integer> possible = new ArrayList<>(9);
                for (int i = 1; i < 10; i++) {
                    if (isValidPlacement(cell.getRow(), cell.getColumn(), i, board)) {
                        possible.add(i);
                    }
                }
                

                if (possible.size() == 1) {
                    cell.setValue(possible.get(0));
                    board.setEmptyCells(board.getEmptyCells() - 1);
                }
                else {
                    cell.setPossible(possible);
                }
            }
        }
    }

}
