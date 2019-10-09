package buildings;

public class Dwelling {
    private DwellingFloor[] floors;

    public Dwelling(int floorCount, int[] flatCount) {
        if (floorCount != flatCount.length) {
            throw new IllegalArgumentException();
        }
        floors = new DwellingFloor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            floors[i] = new DwellingFloor(flatCount[i]);
        }
    }

    public Dwelling(DwellingFloor[] floors) {
        this.floors = floors;
    }

    public DwellingFloor[] getFloors() {
        return floors;
    }

    public int getFloorCount() {
        return floors.length;
    }

    public int getFlatCount() {
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

    public double getRoomCount() {
        int result = 0;
        for (int i = 0; i < floors.length; i++) {
            result += floors[i].getRoomCount();
        }
        return result;
    }

    public DwellingFloor getFloor(int num) {
        return floors[num];
    }

    public void setFloor(int num, DwellingFloor floor) {
        floors[num] = floor;
    }

    public Flat getFlat(int num) {
        int i = 0;
        while (num >= floors[i].getSpaceCount()) {
            num -= floors[i++].getSpaceCount();
        }
        return floors[i].getSpace(num);
    }

    public void setFlat(int num, Flat newFlat) {
        int i = 0;
        while (num >= floors[i].getSpaceCount()) {
            num -= floors[i++].getSpaceCount();
        }
        floors[i].setSpace(num, newFlat);
    }

    public void addFlat(int num, Flat flat) {
        int i = 0;
        while (num > floors[i].getSpaceCount()) {
            num -= floors[i++].getSpaceCount();
        }
        floors[i].addSpace(num, flat);
    }

    public void deleteFlat(int num) {
        int i = 0;
        while (num > floors[i].getSpaceCount()) {
            num -= floors[i++].getSpaceCount();
        }
        floors[i].deleteSpace(num);
    }

    public Flat getBestSpace() {
        double bestArea = 0;
        int floorWithBestFlatNum = 0;
        for (int i = 0; i < floors.length; i++) {
            if (floors[i].getSpaces().length > 0) {
                if (floors[i].getBestSpace().getArea() > bestArea) {
                    bestArea = floors[i].getBestSpace().getArea();
                    floorWithBestFlatNum = i;
                }
            }
        }
        return floors[floorWithBestFlatNum].getBestSpace();
    }

    public Flat[] getSortedByAreaFlats() {
        long start = System.currentTimeMillis();
        Flat[] result;
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


    private Flat[] sort(Flat[] flats) {
        if (flats.length > 2) {
            Flat[] leftPart = new Flat[flats.length / 2];
            for (int i = 0; i < leftPart.length; i++) {
                leftPart[i] = flats[i];
            }
            Flat[] rightPart = new Flat[flats.length - leftPart.length];
            for (int i = leftPart.length; i < flats.length; i++) {
                rightPart[i - leftPart.length] = flats[i];
            }
            return merge(sort(leftPart), sort(rightPart));
        } else if (flats.length == 2) {
            if (flats[0].getArea() < flats[1].getArea()) {
                Flat t = flats[0];
                flats[0] = flats[1];
                flats[1] = t;
            }
        }
        return flats;
    }

    private Flat[] merge(Flat[] leftPart, Flat[] rightPart) {
        Flat[] result = new Flat[leftPart.length + rightPart.length];
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
