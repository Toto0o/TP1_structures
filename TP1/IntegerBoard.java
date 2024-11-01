import Interfaces.GameBoard;
import Interfaces.Position;

/**
 * add description
 *
 * @author      Antoine Tessier  
 * @version     1.0
 * @since       1.0 (26 October 2024)
 */


public class IntegerBoard<T extends Cell>  implements GameBoard<T>{
    

    private AbstractTree<T> tree;

    public IntegerBoard (AbstractTree<T> tree ) { this.tree = tree; }

    @Override
    public T getCell(int x, int y) throws IndexOutOfBoundsException {
        try {
            
        }
        catch (IndexOutOfBoundsException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    void setCell(int x, int y, T value) throws IndexOutOfBoundsException {
        try {
            for ()
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @Override
    int getHeight() {

    }

    @Override
    int getWidth() {

    }

    @Override
    void display() {
        
    }

}
