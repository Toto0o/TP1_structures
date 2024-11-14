package solver;
import java.util.ArrayList;
import solver.interfaces.*;


public class GameTree extends AbstractTree<IntegerBoard> {

    private Node root;

    /* ------ Inner Node class ------ */
    private class Node implements Position<IntegerBoard> {

        private IntegerBoard element;
        private Node parent;
        private ArrayList<Node> children;
 
        public Node( IntegerBoard element, Node parent ) {
            this.element = element;
            this.parent = parent;
            this.children = new ArrayList<>(9);
        }

        @Override
        public IntegerBoard getElement() {
            return this.element;
        }

        public void setChildren( Node child ) {
            this.children.add(child);
        }

        public Iterable<Position<IntegerBoard>> getChildren() {
            return new ArrayList<>(children);
        }

        public Node getParent() {
            return this.parent;
        }

        public int numChildren() {
            return children.size();
        }
    }
    /* ------ End inner Node class ------- */

    // Constructor
    public GameTree( IntegerBoard root ) {
        this.root = new Node(root, null);
    }

    public Position<IntegerBoard> root() {
        return this.root;
    }

    public Position<IntegerBoard> parent( Position<IntegerBoard> p) {
        Node node = (Node) p;
        return node.getParent();
    }

    public Iterable<Position<IntegerBoard>> children(Position<IntegerBoard> p) {
        Node node = (Node) p;
        return node.getChildren();
    }

    public int numChildren(Position<IntegerBoard> p) {
        Node node = (Node) p;
        return node.numChildren();
    }

    public int size() {
        int size = 0;
        for (Position<IntegerBoard> positions : this.positions()) { positions.getElement().display(); size++; }
        return size;
    }

    public Position<IntegerBoard> setChildren(Position<IntegerBoard> parent, IntegerBoard children) {
        Node node = (Node) parent;
        Node child = new Node(children, node);
        node.setChildren(child);
        return child;
    }

}
