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

    private void addNode(int num, Office office) {
        Node currentNode = head;
        if(num == 0){
            Node prevHead = head;
            Node newHead = new Node(new Office(), prevHead);
            while (currentNode.getNext() != head) currentNode = currentNode.getNext();
            currentNode.setNext(newHead);
            head = newHead;
        }
        else {
            for (int i = 0; i < num - 1; i++) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(new Node(office, currentNode.getNext()));
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

    public OfficeFloor(Office[] oficceArray) {
        head = new Node(oficceArray[0]);
        for (int i = 1; i < oficceArray.length; i++) {
            getNode(i - 1).setNext(new Node(oficceArray[i]));
        }
        getNode(oficceArray.length - 1).setNext(head);
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
        double area = head.getOffice().getArea();
        while (currentNode != head) {
            area += currentNode.getOffice().getArea();
            currentNode = currentNode.getNext();
        }
        return area;
    }

    public int getRoomCount() {
        Node currentNode = head.getNext();
        int roomCount = head.getOffice().getRoomCount();
        while (currentNode != head) {
            roomCount += currentNode.getOffice().getRoomCount();
            currentNode = currentNode.getNext();
        }
        return roomCount;
    }

    public Office[] getOffices(){
        Office[] offices = new Office[getSpaceCount()];
        for(int i = 0; i < getSpaceCount(); i++){
            offices[i] = getOffice(i);
        }
        return offices;
    }

    public Office getOffice(int num){
        return getNode(num).getOffice();
    }

    public void setOffice(int num, Office office){
        getNode(num).setOffice(office);
    }

    public void addOffice(int num, Office newOffice){
        addNode(num, newOffice);
    }

    public void deleteOffice(int num){
        deleteNode(num);
    }

    public Office getBestSpace() {
        if(getSpaceCount()>0) {
            double bestArea = 0;
            int bestOfficeNum = 0;
            for (int i = 0; i < getSpaceCount(); i++) {
                if (getOffice(i).getArea() > bestArea) {
                    bestArea = getOffice(i).getArea();
                    bestOfficeNum = i;
                }
            }
            return getOffice(bestOfficeNum);
        }
        else return null;
    }
}
