package buildings;

public class OfficeBuilding implements Building{
    TwoLinkNode head;

    private TwoLinkNode getNode(int num) {
        TwoLinkNode currentNode = head;
        for (int i = 0; i < num; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    private void addNode(int num, Floor floor) {
        TwoLinkNode currentNode = head;
        for (int i = 0; i < num - 1; i++) {
            currentNode = currentNode.getNext();
        }
        TwoLinkNode nextNode = currentNode.getNext();
        TwoLinkNode newNode = new TwoLinkNode(floor, nextNode, currentNode); // 0?
        currentNode.setNext(newNode);
        nextNode.setPrev(newNode);
    }

    private void deleteNode(int num){
        TwoLinkNode currentNode = head;
        for (int i = 0; i < num - 1; i++) {
            currentNode = currentNode.getNext();
        }
        TwoLinkNode next = currentNode.getNext().getNext();
        currentNode.setNext(next);
        next.setPrev(currentNode);
    }

    public OfficeBuilding(int floorCount, int[] officeCounts){
        head = new TwoLinkNode(new OfficeFloor(officeCounts[0]));
        for(int i = 1; i < floorCount; i++){
            TwoLinkNode prevNode = getNode(i-1);
            TwoLinkNode newNode = new TwoLinkNode(new OfficeFloor(officeCounts[i]), prevNode);
            prevNode.setNext(newNode);
        }
        TwoLinkNode lastNode = getNode(floorCount - 1);
        lastNode.setNext(head);
        head.setPrev(lastNode);
    }

    public OfficeBuilding(Floor[] floorArray) {
        head = new TwoLinkNode(floorArray[0]);
        for (int i = 1; i < floorArray.length; i++) {
            TwoLinkNode prevNode = getNode(i-1);
            TwoLinkNode newNode = new TwoLinkNode(floorArray[i], prevNode);
            prevNode.setNext(newNode);
        }
        TwoLinkNode lastNode = getNode(floorArray.length - 1);
        lastNode.setNext(head);
        head.setPrev(lastNode);
    }

    public int getFloorCount(){
        TwoLinkNode currentNode = head.getNext();
        int counter = 1;
        while (currentNode != head) {
            currentNode = currentNode.getNext();
            counter++;
        }
        return counter;
    }

    public int getSpaceCount(){
        int counter = head.getFloor().getSpaceCount();
        TwoLinkNode currentNode = head.getNext();
        while (currentNode != head){
            counter += currentNode.getFloor().getSpaceCount();
            currentNode = currentNode.getNext();
        }
        return counter;
    }

    public double getArea(){
        double area = head.getFloor().getArea();
        TwoLinkNode currentNode = head.getNext();
        while (currentNode != head){
            area += currentNode.getFloor().getArea();
            currentNode = currentNode.getNext();
        }
        return area;
    }

    public int getRoomCount(){
        int counter = head.getFloor().getRoomCount();
        TwoLinkNode currentNode = head.getNext();
        while (currentNode != head){
            counter += currentNode.getFloor().getRoomCount();
            currentNode = currentNode.getNext();
        }
        return counter;
    }

    public Floor[] getFloors(){
        Floor[] floors = new Floor[getFloorCount()];
        for(int i = 0; i < getFloorCount(); i++){
            floors[i] = getFloor(i);
        }
        return floors;
    }

    public Floor getFloor(int num){

        if(num >= getFloorCount()){
            throw new FloorIndexOutOfBoundException();
        }

        return getNode(num).getFloor();
    }

    public void setFloor(int num, Floor floor){

        if(num >= getFloorCount()){
            throw new FloorIndexOutOfBoundException();
        }

        getNode(num).setFloor(floor);
    }

    public Space getSpace(int num){

        if(num >= getSpaceCount()){
            throw new SpaceIndexOutOfBoundsException();
        }

        int i = 0;
        while (num >= getFloor(i).getSpaceCount()) {
            num -= getFloor(i++).getSpaceCount();
        }
        return getFloor(i).getSpace(num);
    }

    public void setSpace(int num, Space newSpace) {

        if(num >= getSpaceCount()){
            throw new SpaceIndexOutOfBoundsException();
        }

        int i = 0;
        while (num >= getFloor(i).getSpaceCount()) {
            num -= getFloor(i++).getSpaceCount();
        }
        getFloor(i).setSpace(num, newSpace);
    }

    public void addSpace(int num, Space newSpace) {

        if(num > getSpaceCount()){
            throw new SpaceIndexOutOfBoundsException();
        }

        int i = 0;
        while (num >= getFloor(i).getSpaceCount()) {
            num -= getFloor(i++).getSpaceCount();
        }
        getFloor(i).addSpace(num, newSpace);
    }

    public void deleteSpace(int num) {

        if(num >= getSpaceCount()){
            throw new SpaceIndexOutOfBoundsException();
        }

        int i = 0;
        while (num >= getFloor(i).getSpaceCount()) {
            num -= getFloor(i++).getSpaceCount();
        }
        getFloor(i).deleteSpace(num);
    }

    public Space getBestSpace() {
        double bestArea = 0;
        int floorWithBestSpaceNum = 0;
        for (int i = 0; i < getFloorCount(); i++) {
            if (getFloor(i).getSpaceCount() > 0) {
                if (getFloor(i).getBestSpace().getArea() > bestArea) {
                    bestArea = getFloor(i).getBestSpace().getArea();
                    floorWithBestSpaceNum = i;
                }
            }
        }
        return getFloor(floorWithBestSpaceNum).getBestSpace();
    }

    public Space[] getSortedByAreaSpaces(){
        long start = System.currentTimeMillis();
        Space[] result;
        if (getFloorCount() > 1) {
            result = merge(sort(getFloor(0).getSpaces()), sort(getFloor(1).getSpaces()));
            for (int i = 2; i < getFloorCount(); i++) {
                result = merge(result, sort(getFloor(i).getSpaces()));
            }
        } else {
            result = sort(getFloor(0).getSpaces());
        }
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("Merge sort done in " + timeConsumedMillis + " ms.");
        return result;
    }

    private Space[] sort(Space[] spaces) {
        if (spaces.length > 2) {
            Space[] leftPart = new Space[spaces.length / 2];
            for (int i = 0; i < leftPart.length; i++) {
                leftPart[i] = spaces[i];
            }
            Space[] rightPart = new Space[spaces.length - leftPart.length];
            for (int i = leftPart.length; i < spaces.length; i++) {
                rightPart[i - leftPart.length] = spaces[i];
            }
            return merge(sort(leftPart), sort(rightPart));
        } else if (spaces.length == 2) {
            if (spaces[0].getArea() < spaces[1].getArea()) {
                Space t = spaces[0];
                spaces[0] = spaces[1];
                spaces[1] = t;
            }
        }
        return spaces;
    }

    private Space[] merge(Space[] leftPart, Space[] rightPart) {
        Space[] result = new Space[leftPart.length + rightPart.length];
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
