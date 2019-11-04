package buildings.office;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.DwellingFloorIterator;
import buildings.exceptions.SpaceIndexOutOfBoundsException;

import java.util.Iterator;

public class OfficeFloor implements Floor {
    OneLinkNode head;

    private OneLinkNode getNode(int num) {
        OneLinkNode currentNode = head.getNext();
        for (int i = 0; i < num; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    private void addNode(int num, Space space) {
        OneLinkNode currentNode = head;
        for (int i = 0; i < num; i++) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(new OneLinkNode(space, currentNode.getNext()));
    }

    private void deleteNode(int num) {
        OneLinkNode currentNode = head;
        for (int i = 0; i < num; i++) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(currentNode.getNext().getNext());

    }

    public OfficeFloor(Space[] spaceArray) {
        head = new OneLinkNode();
        OneLinkNode currentNode = head;
        for (int i = 0; i < spaceArray.length; i++) {
            currentNode.setNext(new OneLinkNode(spaceArray[i]));
            currentNode = currentNode.getNext();
        }
        getNode(spaceArray.length - 1).setNext(head);
    }

    public OfficeFloor(int size) {
        if (size < 1) throw new IllegalArgumentException();
        head = new OneLinkNode();
        OneLinkNode cuNode = head;
        for (int i = 0; i < size; i++) {
            cuNode.setNext(new OneLinkNode(new Office()));
            cuNode = cuNode.getNext();
        }
        getNode(size - 1).setNext(head);
    }

    public int getSpaceCount() {
        OneLinkNode currentNode = head.getNext();
        int counter = 0;
        while (currentNode != head) {
            currentNode = currentNode.getNext();
            counter++;
        }
        return counter;
    }

    public double getArea() {
        OneLinkNode currentNode = head.getNext();
        double area = 0;
        while (currentNode != head) {
            area += currentNode.getSpace().getArea();
            currentNode = currentNode.getNext();
        }
        return area;
    }

    public int getRoomCount() {
        OneLinkNode currentNode = head.getNext();
        int roomCount = 0;
        while (currentNode != head) {
            roomCount += currentNode.getSpace().getRoomCount();
            currentNode = currentNode.getNext();
        }
        return roomCount;
    }

    public Space[] getSpaces() {
        Space[] spaces = new Space[getSpaceCount()];
        for (int i = 0; i < getSpaceCount(); i++) {
            spaces[i] = getSpace(i);
        }
        return spaces;
    }

    public Space getSpace(int num) {

        if (num >= getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }

        return getNode(num).getSpace();
    }

    public void setSpace(int num, Space space) {

        if (num >= getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }

        getNode(num).setSpace(space);
    }

    public void addSpace(int num, Space newSpace) {

        if (num > getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }

        addNode(num, newSpace);
    }

    public void deleteSpace(int num) {

        if (num >= getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }

        deleteNode(num);
    }

    public Space getBestSpace() {
        if (getSpaceCount() > 0) {
            double bestArea = 0;
            int bestSpaceNum = 0;
            for (int i = 0; i < getSpaceCount(); i++) {
                if (getSpace(i).getArea() > bestArea) {
                    bestArea = getSpace(i).getArea();
                    bestSpaceNum = i;
                }
            }
            return getSpace(bestSpaceNum);
        } else return null;
    }

    @Override
    public boolean equals(Floor floor) {
        if (!OfficeFloor.class.isInstance(floor)) {
            return false;
        }
        if (floor.getSpaceCount() != this.getSpaceCount()) {
            return false;
        }
        for (int i = 0; i < this.getSpaceCount(); i++) {
            if (!this.getSpace(i).equals(floor.getSpace(i))) return false;
        }
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        OfficeFloor res = null;
        res = (OfficeFloor) super.clone();
        res.head = new OneLinkNode();
        OfficeFloorIterator iterator = new OfficeFloorIterator(this, head);
        int i = 0;
        while(iterator.hasNext()){
            res.addSpace(i++, (Space) iterator.next().clone());
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("OfficeFloor(" +
                getSpaceCount() + ", ");
        OfficeFloorIterator iterator = new OfficeFloorIterator(this, head);
        while (iterator.hasNext()) {
            stringBuffer.append(iterator.next().toString());
            stringBuffer.append(", ");
        }
        stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public int hashCode(){

        int hash = getSpaceCount();
        Iterator iterator = iterator();
        while(iterator.hasNext()){
            hash^=iterator.next().hashCode();
        }
        return hash;
    }

    @Override
    public Iterator iterator() {
        return new OfficeFloorIterator(this, this.head);
    }
}
