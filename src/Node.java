public class Node {
    private Office office;
    private Node next;

    public Node(Office office, Node next) {
        this.office = office;
        this.next = next;
    }

    public Node(Office office) {
        this.office = office;
    }

    public Office getOffice() {
        return office;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
