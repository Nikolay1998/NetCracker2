package buildings.dwelling;

import buildings.exceptions.InvalidRoomsCountException;
import buildings.exceptions.InvalidSpaceAreaException;
import buildings.Space;

public class Flat implements Space {
    private double area;
    private int roomCount;
    public static final double DEF_AREA = 50.0;
    public static final int DEF_ROOM_COUNT = 2;

    public Flat(){
        area = DEF_AREA;
        roomCount = DEF_ROOM_COUNT;
    }

    public Flat(double area){
        if(area <= 0){
            throw new InvalidSpaceAreaException();
        }
        this.area = area;
        roomCount = DEF_ROOM_COUNT;
    }

    public Flat(double area, int roomCount){
        if(area <= 0){
            throw new InvalidSpaceAreaException();
        }

        if(roomCount <= 0) {
            throw new InvalidRoomsCountException();
        }

        this.area = area;
        this.roomCount = roomCount;
    }

    public int getRoomCount(){
        return roomCount;
    }

    public double getArea(){
        return area;
    }

    public void setRoomCount(int roomCount) {
        if(roomCount <= 0) {
            throw new InvalidRoomsCountException();
        }

        this.roomCount = roomCount;
    }

    public void setArea(double area) {

        if(area <= 0){
            throw new InvalidSpaceAreaException();
        }

        this.area = area;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "area=" + area +
                ", roomCount=" + roomCount +
                '}';
    }

    @Override
    public boolean equals(Space space) {
        if(!Flat.class.isInstance(space)){
            return false;
        }
        if(space.getArea() != this.getArea()){
            return false;
        }
        if(space.getRoomCount() != this.getRoomCount()){
            return false;
        }
        return true;
    }
}
