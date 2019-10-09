package buildings;

public class TwoLinkNode {
    private Floor floor;
    private TwoLinkNode next;
    private TwoLinkNode prev;

    public TwoLinkNode(Floor floor, TwoLinkNode next, TwoLinkNode prev) {
        this.floor = floor;
        this.next = next;
        this.prev = prev;
    }

    public TwoLinkNode(Floor floor, TwoLinkNode prev) {
        this.floor = floor;
        this.prev = prev;
    }

    public TwoLinkNode(Floor floor) {
        this.floor = floor;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public TwoLinkNode getNext() {
        return next;
    }

    public void setNext(TwoLinkNode next) {
        this.next = next;
    }

    public TwoLinkNode getPrev() {
        return prev;
    }

    public void setPrev(TwoLinkNode prev) {
        this.prev = prev;
    }
}
