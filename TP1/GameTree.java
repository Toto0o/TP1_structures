import Interfaces.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameTree extends AbstractTree<IntegerBoard> {

    private PositionNode root;

    /*------ Inner PositionNode class  */
    protected static class PositionNode implements Position<IntegerBoard> {
        private IntegerBoard element;
        private PositionNode parent;
        private final ArrayList<Position<IntegerBoard>> children;

        public PositionNode(IntegerBoard element, PositionNode parent) {
            this.element = element;
            this.parent = parent;
            this.children = new ArrayList(9);
        }

        @Override
        public IntegerBoard getElement() {
            return this.element;
        }

        public PositionNode getParent() {
            return this.parent;
        }

        public Iterable<Position<IntegerBoard>> getChildren() {
            return this.children;
        }

        public int getChildrenSize() {
            return this.children.size();
        }

        public void setElement(IntegerBoard element) {
            this.element = element;
        }

        public void setParent(PositionNode parent) {
            this.parent = parent;
        }

        public void addChild(PositionNode child) {
            this.children.add(child);
        }
    }

    /*-----End Inner PositionNode class*/

    public GameTree( IntegerBoard root ) {
        this.root = new PositionNode(root, null);
    }

    @Override
    public Position<IntegerBoard> root() {
        return this.root;
    }

    @Override
    public Position<IntegerBoard> parent(Position<IntegerBoard> p) throws IllegalArgumentException {
        // Verify validity of Position
        if (p == null || !(p instanceof PositionNode)) {
            throw new IllegalArgumentException("Not a valid position");
        }

        // Cast position p to PositionNode to use the methods of PostionNode
        PositionNode node = (PositionNode) p;
        return node.getParent();
    }

    @Override
    public Iterable<Position<IntegerBoard>> children(Position<IntegerBoard> p) throws IllegalArgumentException {
        // Verify validity of Position
        if (p == null || !(p instanceof PositionNode)) {
            throw new IllegalArgumentException("Not a valid position");
        }

        // Cast position p to PositionNode node to use the methods of PostionNode
        PositionNode node = (PositionNode) p;
        return node.getChildren();
    }

    @Override
    public int numChildren(Position<IntegerBoard> p) throws IllegalArgumentException {
        PositionNode node = (PositionNode) p;
        return node.getChildrenSize();
    }

    @Override
    public int size() {
        int size = 0;
        Iterator<IntegerBoard> iterator = iterator();
        while (iterator.hasNext()) {
            size ++;
            iterator.next();
        }
        return size;
    }

    public Position<IntegerBoard> setChildren(Position<IntegerBoard> parent, IntegerBoard board) {
        PositionNode node = (PositionNode) parent;
        PositionNode child = new PositionNode(board, node);
        node.addChild(child);
        return child;
    }

}
