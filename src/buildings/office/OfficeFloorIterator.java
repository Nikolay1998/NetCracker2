package buildings.office;

import buildings.Space;

import java.util.Iterator;

public class OfficeFloorIterator implements Iterator<Space> {

    private OfficeFloor floor;
    OfficeFloor.OneLinkNode head;
    OfficeFloor.OneLinkNode currentNode;

    public OfficeFloorIterator(OfficeFloor floor, OfficeFloor.OneLinkNode head) {
        this.floor = floor;
        this.head = head;
        currentNode = this.head.getNext();
    }

    @Override
    public boolean hasNext() {
        if (currentNode != head) return true;
        return false;
    }

    @Override
    public Space next() {
        Space result = currentNode.getSpace();
        currentNode = currentNode.getNext();
        return result;
    }
}
