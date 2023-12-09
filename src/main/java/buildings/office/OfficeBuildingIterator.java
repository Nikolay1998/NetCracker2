package buildings.office;

import buildings.Floor;

import java.util.Iterator;

public class OfficeBuildingIterator implements Iterator <Floor> {
    private OfficeBuilding building;
    private OfficeBuilding.TwoLinkNode head;
    private OfficeBuilding.TwoLinkNode currentNode;

    public OfficeBuildingIterator(OfficeBuilding building, OfficeBuilding.TwoLinkNode head) {
        this.building = building;
        this.head = head;
        currentNode = this.head.getNext();
    }

    @Override
    public boolean hasNext() {
        if (currentNode != head) return true;
        return false;
    }

    @Override
    public Floor next() {
        Floor result = currentNode.getFloor();
        currentNode = currentNode.getNext();
        return result;
    }
}
