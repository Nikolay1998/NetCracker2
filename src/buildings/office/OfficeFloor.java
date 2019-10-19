package buildings.office;

import buildings.Floor;
import buildings.Space;
import buildings.exceptions.SpaceIndexOutOfBoundsException;

public class OfficeFloor implements Floor {
    OneLinkNode head;

    private OneLinkNode getNode(int num) {
        OneLinkNode currentNode = head;
        for (int i = 0; i < num; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    private void addNode(int num, Space space) {
        OneLinkNode currentNode = head;
        if (num == 0) {
            OneLinkNode prevHead = head;
            OneLinkNode newHead = new OneLinkNode(space, prevHead);
            while (currentNode.getNext() != head) currentNode = currentNode.getNext();
            currentNode.setNext(newHead);
            head = newHead;
        } else {
            for (int i = 0; i < num - 1; i++) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(new OneLinkNode(space, currentNode.getNext()));
        }
    }

    private void deleteNode(int num) {
        OneLinkNode currentNode = head;
        if (num == 0) {
            OneLinkNode newHead = head.getNext();
            while (currentNode.getNext() != head) currentNode = currentNode.getNext();
            currentNode.setNext(newHead);
            head = newHead;
        } else {
            for (int i = 0; i < num - 1; i++) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(currentNode.getNext().getNext());
        }
    }

    public OfficeFloor(Space[] spaceArray) {
        head = new OneLinkNode(spaceArray[0]);
        for (int i = 1; i < spaceArray.length; i++) {
            getNode(i - 1).setNext(new OneLinkNode(spaceArray[i]));
        }
        getNode(spaceArray.length - 1).setNext(head);
    }

    public OfficeFloor(int size) {
        head = new OneLinkNode(new Office());
        for (int i = 1; i < size; i++) {
            getNode(i - 1).setNext(new OneLinkNode(new Office()));
        }
        getNode(size - 1).setNext(head);
    }

    public int getSpaceCount() {
        OneLinkNode currentNode = head.getNext();
        int counter = 1;
        while (currentNode != head) {
            currentNode = currentNode.getNext();
            counter++;
        }
        return counter;
    }

    public double getArea() {
        OneLinkNode currentNode = head.getNext();
        double area = head.getSpace().getArea();
        while (currentNode != head) {
            area += currentNode.getSpace().getArea();
            currentNode = currentNode.getNext();
        }
        return area;
    }

    public int getRoomCount() {
        OneLinkNode currentNode = head.getNext();
        int roomCount = head.getSpace().getRoomCount();
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

        if(num >= getSpaceCount()){
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
        if(!OfficeFloor.class.isInstance(floor)){
            return false;
        }
        if(floor.getSpaceCount() != this.getSpaceCount()) {
            return false;
        }
        for(int i = 0; i < this.getSpaceCount(); i++){
            if(!this.getSpace(i).equals(floor.getSpace(i))) return false;
        }
        return true;
    }
}
