package buildings;

import java.io.Serializable;

public class OneLinkNode implements Serializable {
    private Space space;
    private OneLinkNode next;

    public OneLinkNode(Space space, OneLinkNode next) {
        this.space = space;
        this.next = next;
    }

    public OneLinkNode(Space space) {
        this.space = space;
    }

    public Space getSpace() {
        return space;
    }

    public OneLinkNode getNext() {
        return next;
    }

    public void setNext(OneLinkNode next) {
        this.next = next;
    }

    public void setSpace(Space space) {
        this.space = space;
    }
}
