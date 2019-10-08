package buildings;

public class NodeFloor {
    private OfficeFloor floor;
    private NodeFloor next;
    private NodeFloor prev;

    public NodeFloor(OfficeFloor floor, NodeFloor next, NodeFloor prev) {
        this.floor = floor;
        this.next = next;
        this.prev = prev;
    }

    public NodeFloor(OfficeFloor floor, NodeFloor prev) {
        this.floor = floor;
        this.prev = prev;
    }

    public NodeFloor(OfficeFloor floor) {
        this.floor = floor;
    }

    public OfficeFloor getFloor() {
        return floor;
    }

    public void setFloor(OfficeFloor floor) {
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
