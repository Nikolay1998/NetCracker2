package buildings;

public class Node {
    private Space space;
    private Node next;

    public Node(Space space, Node next) {
        this.space = space;
        this.next = next;
    }

    public Node(Space space) {
        this.space = space;
    }

    public Space getSpace() {
        return space;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setSpace(Space space) {
        this.space = space;
    }
}
