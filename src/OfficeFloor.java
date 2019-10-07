public class OfficeFloor {
    Node[] oficceList;
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
        for (int i = 0; i < num - 1; i++) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(new Node(office, currentNode.getNext()));
    }

    private void deleteNode(int num) {
        Node currentNode = head;
        for (int i = 0; i < num - 1; i++) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(currentNode.getNext().getNext());
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

    public int getOfficesCount() {
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

    public double getRoomCount() {
        Node currentNode = head.getNext();
        double roomCount = head.getOffice().getRoomCount();
        while (currentNode != head) {
            roomCount += currentNode.getOffice().getRoomCount();
            currentNode = currentNode.getNext();
        }
        return roomCount;
    }

    public Office[] getOffices(){
        Office[] offices = new Office[getOfficesCount()];
        for(int i = 0; i < getOfficesCount(); i++){
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

    public void addOfice(int num){
        addNode(num, new Office());
    }

    public void deleteOffice(int num){
        deleteNode(num);
    }

    public Office getBestSpace() {
        if(getOfficesCount()>0) {
            double bestArea = 0;
            int bestOfficeNum = 0;
            for (int i = 0; i < getOfficesCount(); i++) {
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
