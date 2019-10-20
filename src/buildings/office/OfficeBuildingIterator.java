package buildings.office;

import java.util.Iterator;

public class OfficeBuildingIterator implements Iterator {
    private OfficeBuilding building;
    private TwoLinkNode head;
    private TwoLinkNode currentNode;

    public OfficeBuildingIterator(OfficeBuilding building, TwoLinkNode head) {
        this.building = building;
        this.head = head;
        currentNode = this.head;
    }

    @Override
    public boolean hasNext() {
        if (currentNode.getNext() != head) return true;
        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}
