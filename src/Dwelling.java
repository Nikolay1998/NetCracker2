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
            result += floors[i].getFlatCount();
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
        int i=0;
        while(num >= floors[i].getFlatCount())
        {
            num-=floors[i++].getFlatCount();
        }
        return floors[i].getFlat(num);
    }

    public void setFlat(int num, Flat newFlat) {
        int i=0;
        while(num >= floors[i].getFlatCount())
        {
            num-=floors[i++].getFlatCount();
        }
        floors[i].setFlat(num, newFlat);
    }
}
