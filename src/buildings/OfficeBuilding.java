package buildings;

public class OfficeBuilding {
    NodeFloor head;

    private NodeFloor getNode(int num) {
        NodeFloor currentNode = head;
        for (int i = 0; i < num; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    private void addNode(int num) {
        NodeFloor currentNode = head;
        for (int i = 0; i < num - 1; i++) {
            currentNode = currentNode.getNext();
        }
        NodeFloor nextNode = currentNode.getNext();
        NodeFloor newNode = new NodeFloor(new OfficeFloor(0), nextNode, currentNode); // 0?
        currentNode.setNext(newNode);
        nextNode.setPrev(newNode);
    }

    private void deleteNode(int num){
        NodeFloor currentNode = head;
        for (int i = 0; i < num - 1; i++) {
            currentNode = currentNode.getNext();
        }
        NodeFloor next = currentNode.getNext().getNext();
        currentNode.setNext(next);
        next.setPrev(currentNode);
    }

    public OfficeBuilding(int floorCount, int[] officeCounts){
        head = new NodeFloor(new OfficeFloor(officeCounts[0]));
        for(int i = 1; i < floorCount; i++){
            NodeFloor prevNode = getNode(i-1);
            NodeFloor newNode = new NodeFloor(new OfficeFloor(officeCounts[i]), prevNode);
            prevNode.setNext(newNode);
        }
        NodeFloor lastNode = getNode(floorCount - 1);
        lastNode.setNext(head);
        head.setPrev(lastNode);
    }

    public OfficeBuilding(OfficeFloor[] floorArray) {
        head = new NodeFloor(floorArray[0]);
        for (int i = 1; i < floorArray.length; i++) {
            NodeFloor prevNode = getNode(i-1);
            NodeFloor newNode = new NodeFloor(floorArray[i], prevNode);
            prevNode.setNext(newNode);
        }
        NodeFloor lastNode = getNode(floorArray.length - 1);
        lastNode.setNext(head);
        head.setPrev(lastNode);
    }

    public int getFloorCount(){
        NodeFloor currentNode = head.getNext();
        int counter = 1;
        while (currentNode != head) {
            currentNode = currentNode.getNext();
            counter++;
        }
        return counter;
    }

    public int getOfficeCount(){
        int counter = head.getFloor().getSpaceCount();
        NodeFloor currentNode = head.getNext();
        while (currentNode != head){
            counter += currentNode.getFloor().getSpaceCount();
            currentNode = currentNode.getNext();
        }
        return counter;
    }

    public double getArea(){
        double area = head.getFloor().getArea();
        NodeFloor currentNode = head.getNext();
        while (currentNode != head){
            area += currentNode.getFloor().getArea();
            currentNode = currentNode.getNext();
        }
        return area;
    }

    public int getRoomCount(){
        int counter = head.getFloor().getRoomCount();
        NodeFloor currentNode = head.getNext();
        while (currentNode != head){
            counter += currentNode.getFloor().getRoomCount();
            currentNode = currentNode.getNext();
        }
        return counter;
    }

    public OfficeFloor[] getFloors(){
        OfficeFloor[] floors = new OfficeFloor[getFloorCount()];
        for(int i = 0; i < getFloorCount(); i++){
            floors[i] = getFloor(i);
        }
        return floors;
    }

    public OfficeFloor getFloor(int num){
        return getNode(num).getFloor();
    }

    public void setFloor(int num, OfficeFloor floor){
        getNode(num).setFloor(floor);
    }

    public Office getOffice(int num){
        int i = 0;
        while (num >= getFloor(i).getSpaceCount()) {
            num -= getFloor(i++).getSpaceCount();
        }
        return getFloor(i).getOffice(num);
    }

    public void setOffice(int num, Office newOffice) {
        int i = 0;
        while (num >= getFloor(i).getSpaceCount()) {
            num -= getFloor(i++).getSpaceCount();
        }
        getFloor(i).setOffice(num, newOffice);
    }

    public void addOffice(int num, Office newOffice) {
        int i = 0;
        while (num >= getFloor(i).getSpaceCount()) {
            num -= getFloor(i++).getSpaceCount();
        }
        getFloor(i).addOffice(num, newOffice);
    }

    public void deleteOffice(int num) {
        int i = 0;
        while (num >= getFloor(i).getSpaceCount()) {
            num -= getFloor(i++).getSpaceCount();
        }
        getFloor(i).deleteOffice(num);
    }

    public Office getBestSpace() {
        double bestArea = 0;
        int floorWithBestFlatNum = 0;
        for (int i = 0; i < getFloorCount(); i++) {
            if (getFloor(i).getSpaceCount() > 0) {
                if (getFloor(i).getBestSpace().getArea() > bestArea) {
                    bestArea = getFloor(i).getBestSpace().getArea();
                    floorWithBestFlatNum = i;
                }
            }
        }
        return getFloor(floorWithBestFlatNum).getBestSpace();
    }

    public Office[] getSortedByAreaOffices(){
        long start = System.currentTimeMillis();
        Office[] result;
        if (getFloorCount() > 1) {
            result = merge(sort(getFloor(0).getOffices()), sort(getFloor(1).getOffices()));
            for (int i = 2; i < getFloorCount(); i++) {
                result = merge(result, sort(getFloor(i).getOffices()));
            }
        } else {
            result = sort(getFloor(0).getOffices());
        }
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("Merge sort done in " + timeConsumedMillis + " ms.");
        return result;
    }

    private Office[] sort(Office[] offices) {
        if (offices.length > 2) {
            Office[] leftPart = new Office[offices.length / 2];
            for (int i = 0; i < leftPart.length; i++) {
                leftPart[i] = offices[i];
            }
            Office[] rightPart = new Office[offices.length - leftPart.length];
            for (int i = leftPart.length; i < offices.length; i++) {
                rightPart[i - leftPart.length] = offices[i];
            }
            return merge(sort(leftPart), sort(rightPart));
        } else if (offices.length == 2) {
            if (offices[0].getArea() < offices[1].getArea()) {
                Office t = offices[0];
                offices[0] = offices[1];
                offices[1] = t;
            }
        }
        return offices;
    }

    private Office[] merge(Office[] leftPart, Office[] rightPart) {
        Office[] result = new Office[leftPart.length + rightPart.length];
        int leftPartCount = 0;
        int rightPartCount = 0;
        int i = 0;
        while (leftPartCount < leftPart.length && rightPartCount < rightPart.length) {
            if (leftPart[leftPartCount].getArea() > rightPart[rightPartCount].getArea()) {
                result[i] = leftPart[leftPartCount];
                leftPartCount++;
            } else {
                result[i] = rightPart[rightPartCount];
                rightPartCount++;
            }
            i++;
        }
        if (leftPartCount == leftPart.length) {
            for (; i < result.length; i++) {
                result[i] = rightPart[rightPartCount];
                rightPartCount++;
            }
        }
        if (rightPartCount == rightPart.length) {
            for (; i < result.length; i++) {
                result[i] = leftPart[leftPartCount];
                leftPartCount++;
            }
        }
        return result;
    }

}
