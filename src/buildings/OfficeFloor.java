package buildings;

public class OfficeFloor implements Floor{
    Node head;

    private Node getNode(int num) {
        Node currentNode = head;
        for (int i = 0; i < num; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    private void addNode(int num, Space space) {
        Node currentNode = head;
        if(num == 0){
            Node prevHead = head;
            Node newHead = new Node(space, prevHead);
            while (currentNode.getNext() != head) currentNode = currentNode.getNext();
            currentNode.setNext(newHead);
            head = newHead;
        }
        else {
            for (int i = 0; i < num - 1; i++) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(new Node(space, currentNode.getNext()));
        }
    }

    private void deleteNode(int num) {
        Node currentNode = head;
        if(num == 0){
            Node newHead = head.getNext();
            while (currentNode.getNext() != head) currentNode = currentNode.getNext();
            currentNode.setNext(newHead);
            head = newHead;
        }
        else {
            for (int i = 0; i < num - 1; i++) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(currentNode.getNext().getNext());
        }
    }

    public OfficeFloor(Space[] spaceArray) {
        head = new Node(spaceArray[0]);
        for (int i = 1; i < spaceArray.length; i++) {
            getNode(i - 1).setNext(new Node(spaceArray[i]));
        }
        getNode(spaceArray.length - 1).setNext(head);
    }

    public OfficeFloor(int size) {
        head = new Node(new Office());
        for (int i = 1; i < size; i++) {
            getNode(i - 1).setNext(new Node(new Office()));
        }
        getNode(size - 1).setNext(head);
    }

    public int getSpaceCount() {
        Node currentNode = head.getNext();
        int counter = 1;
        while (currentNode != head) {
            currentNode = currentNode.getNext();
            counter++;
        }
        return counter;
    }

    public double getArea() {
        Node currentNode = head.getNext();
        double area = head.getSpace().getArea();
        while (currentNode != head) {
            area += currentNode.getSpace().getArea();
            currentNode = currentNode.getNext();
        }
        return area;
    }

    public int getRoomCount() {
        Node currentNode = head.getNext();
        int roomCount = head.getSpace().getRoomCount();
        while (currentNode != head) {
            roomCount += currentNode.getSpace().getRoomCount();
            currentNode = currentNode.getNext();
        }
        return roomCount;
    }

    public Space[] getSpaces(){
        Space[] spaces = new Space[getSpaceCount()];
        for(int i = 0; i < getSpaceCount(); i++){
            spaces[i] = getSpace(i);
        }
        return spaces;
    }

    public Space getSpace(int num){
        return getNode(num).getSpace();
    }

    public void setSpace(int num, Space space){
        getNode(num).setSpace(space);
    }

    public void addSpace(int num, Space newSpace){
        addNode(num, newSpace);
    }

    public void deleteSpace(int num){
        deleteNode(num);
    }

    public Space getBestSpace() {
        if(getSpaceCount()>0) {
            double bestArea = 0;
            int bestSpaceNum = 0;
            for (int i = 0; i < getSpaceCount(); i++) {
                if (getSpace(i).getArea() > bestArea) {
                    bestArea = getSpace(i).getArea();
                    bestSpaceNum = i;
                }
            }
            return getSpace(bestSpaceNum);
        }
        else return null;
    }
}
