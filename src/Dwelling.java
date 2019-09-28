public class Dwelling {
    private DwellingFloor[] floors;

    public Dwelling(int floorCount, int[] flatCount){
        floors = new DwellingFloor[floorCount];
        for(int i = 0; i < floorCount; i++){
            floors[i] = new DwellingFloor(flatCount[i]);
        }
    }

    public Dwelling(DwellingFloor[] floors){
        this.floors = floors;
    }

    public DwellingFloor[] getFloors() {
        return floors;
    }

    public int getFloorCount(){
        return floors.length;
    }

    public int getFlatCount(){
        int result = 0;
        for(int i = 0; i < floors.length; i++){
            result += floors[i].getFlatCount();
        }
        return result;
    }

    public double getArea(){
        int result = 0;
        for(int i = 0; i < floors.length; i++){
            result += floors[i].getAmountArea();
        }
        return result;
    }

    public double getRoomCount(){
        int result = 0;
        for(int i = 0; i < floors.length; i++){
            result += floors[i].getAmountRoomCount();
        }
        return result;
    }

    public DwellingFloor getFloor(int num){
        return floors[num];
    }

    public void setFloor(int num, DwellingFloor floor){
        floors[num] = floor;
    }

    public Flat getFlat(int num){
        int flatCount = 0;
        int floorCount = 0;
        while(flatCount < num){
            flatCount += floors[floorCount].getFlatCount();
            floorCount++;
        }
        return floors[floorCount].getFlat(flatCount - num);
    }
}
