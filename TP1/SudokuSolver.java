import Interfaces.*;

/**
 * add description
 * 
 * @author      Antoine Tessier  
 * @version     1.0
 * @since       1.0 (1 Novembre 2024)
 */

public class SudokuSolver implements GameSolver {

    // attributes
    IntegerBoard board;
    IntegerBoard solution;
    Tree<IntegerBoard> tree;

    // constructor
    public SudokuSolver( Integer[][] board ) {
        this.board = new IntegerBoard(board);
        this.solution = new IntegerBoard(board);
    }

    // mandatory GameSolver interface methods
    public boolean solve(){ 
        if (this.solveBoard()) {
            this.printSolution();
            return this.solveBoard();
        }
        else {
            System.out.println("There is no solution to this board;");
            return false;
        } 
    }
    public void printSolution(){
        this.solution.display();
    }

    // validate an insertion in the board
    public boolean isValidPlacement( int row, int col, Integer value ){
        for (Cell[] verifyRow : this.board.getRow(row)) {

        }
    }

    // actual solver
    private boolean solveBoard(){}
}
