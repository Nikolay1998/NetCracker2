package buildings;

public class NodeFloor {
    private Floor floor;
    private NodeFloor next;
    private NodeFloor prev;

    public NodeFloor(Floor floor, NodeFloor next, NodeFloor prev) {
        this.floor = floor;
        this.next = next;
        this.prev = prev;
    }

    public NodeFloor(Floor floor, NodeFloor prev) {
        this.floor = floor;
        this.prev = prev;
    }

    public NodeFloor(Floor floor) {
        this.floor = floor;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public NodeFloor getNext() {
        return next;
    }

    public void setNext(NodeFloor next) {
        this.next = next;
    }

    public NodeFloor getPrev() {
        return prev;
    }

    public void setPrev(NodeFloor prev) {
        this.prev = prev;
    }
}
