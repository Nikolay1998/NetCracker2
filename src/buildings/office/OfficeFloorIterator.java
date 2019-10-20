package buildings.office;

import buildings.Space;

import java.util.Iterator;

public class OfficeFloorIterator implements Iterator<Space> {

    private OfficeFloor floor;
    OneLinkNode head;
    OneLinkNode currentNode;

    public OfficeFloorIterator(OfficeFloor floor, OneLinkNode head) {
        this.floor = floor;
        this.head = head;
        currentNode = this.head;
    }

    @Override
    public boolean hasNext() {
        if (currentNode.getNext() != head) return true;
        return false;
    }

    @Override
    public Space next() {
        Space result = currentNode.getSpace();
        currentNode = currentNode.getNext();
        return result;
    }
}
