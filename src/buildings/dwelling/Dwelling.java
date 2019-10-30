package buildings.dwelling;

import buildings.*;
import buildings.exceptions.FloorIndexOutOfBoundException;
import buildings.exceptions.SpaceIndexOutOfBoundsException;

import java.util.Iterator;

public class Dwelling implements Building {
    protected Floor[] floors;

    public Dwelling() {
    }

    public Dwelling(int floorCount, int[] flatCount) {
        if (floorCount != flatCount.length) {
            throw new IllegalArgumentException();
        }
        floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            floors[i] = new DwellingFloor(flatCount[i]);
        }
    }

    public Dwelling(Floor[] floors) {
        this.floors = floors;
    }

    public Floor[] getFloors() {
        return floors;
    }

    public int getFloorCount() {
        return floors.length;
    }

    public int getSpaceCount() {
        int result = 0;
        for (int i = 0; i < floors.length; i++) {
            result += floors[i].getSpaceCount();
        }
        return result;
    }

    public double getArea() {
        int result = 0;
        for (int i = 0; i < floors.length; i++) {
            result += floors[i].getArea();
        }
        return result;
    }

    public int getRoomCount() {
        int result = 0;
        for (int i = 0; i < floors.length; i++) {
            result += floors[i].getRoomCount();
        }
        return result;
    }

    public Floor getFloor(int num) {

        if (num >= getFloorCount()) {
            throw new FloorIndexOutOfBoundException();
        }


        return floors[num];
    }

    public void setFloor(int num, Floor floor) {

        if (num >= getFloorCount()) {
            throw new FloorIndexOutOfBoundException();
        }

        floors[num] = floor;
    }

    public Space getSpace(int num) {
        if (num >= getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int i = 0;
        while (num >= floors[i].getSpaceCount()) {
            num -= floors[i++].getSpaceCount();
        }
        return floors[i].getSpace(num);
    }

    public void setSpace(int num, Space newSpace) {

        if (num >= getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }

        int i = 0;
        while (num >= floors[i].getSpaceCount()) {
            num -= floors[i++].getSpaceCount();
        }
        floors[i].setSpace(num, newSpace);
    }

    public void addSpace(int num, Space space) {

        if (num > getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }

        int i = 0;
        while (num > floors[i].getSpaceCount()) {
            num -= floors[i++].getSpaceCount();
        }
        floors[i].addSpace(num, space);
    }

    public void deleteSpace(int num) {

        if (num >= getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }

        int i = 0;
        while (num > floors[i].getSpaceCount()) {
            num -= floors[i++].getSpaceCount();
        }
        floors[i].deleteSpace(num);
    }

    public Space getBestSpace() {
        double bestArea = 0;
        int floorWithBestSpaceNum = 0;
        for (int i = 0; i < floors.length; i++) {
            if (floors[i].getSpaces().length > 0) {
                if (floors[i].getBestSpace().getArea() > bestArea) {
                    bestArea = floors[i].getBestSpace().getArea();
                    floorWithBestSpaceNum = i;
                }
            }
        }
        return floors[floorWithBestSpaceNum].getBestSpace();
    }

    public Space[] getSortedByAreaSpaces() {
        long start = System.currentTimeMillis();
        Space[] result;
        if (floors.length > 1) {
            result = merge(sort(floors[0].getSpaces()), sort(floors[1].getSpaces()));
            for (int i = 2; i < floors.length; i++) {
                result = merge(result, sort(floors[i].getSpaces()));
            }
        } else {
            result = sort(floors[0].getSpaces());
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

    @Override
    public boolean equals(Building building) {
        if (!Dwelling.class.isInstance(building)) {
            return false;
        }
        if (building.getFloorCount() != this.getFloorCount()) {
            return false;
        }
        for (int i = 0; i < this.getFloorCount(); i++) {
            if (!this.getFloor(i).equals(building.getFloor(i))) return false;
        }
        return true;
    }

    @Override
    public Object clone() {
        DwellingIterator iterator = new DwellingIterator(this, floors);
        Floor[] cloneFloors = new Floor[getFloorCount()];
        int i = 0;
        while (iterator.hasNext()) {
            cloneFloors[i++] = (Floor) iterator.next().clone();
        }
        Dwelling result = new Dwelling(cloneFloors);
        return result;
    }

    @Override
    public Iterator iterator() {
        return new DwellingIterator(this, this.floors);
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Dwelling(" +
                getFloorCount() + ", ");
        DwellingIterator iterator = new DwellingIterator(this, floors);
        while (iterator.hasNext()) {
            stringBuffer.append(iterator.next().toString());
            stringBuffer.append(", ");
        }
        stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        stringBuffer.append(") ");
        return stringBuffer.toString();
    }

    public int hashCode(){

        int hash = getFloorCount();
        Iterator iterator = iterator();
        while(iterator.hasNext()){
            hash^=iterator.next().hashCode();
        }
        return hash;

    }
}
