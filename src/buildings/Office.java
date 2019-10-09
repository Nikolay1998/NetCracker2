package buildings;

public class Office implements Space{
    private double area;
    private int roomCount;

    public static final double DEF_AREA = 250.0;
    public static final int DEF_ROOM_COUNT = 1;

    public Office() {
        this.area = DEF_ROOM_COUNT;
        this.roomCount = DEF_ROOM_COUNT;
    }

    public Office(double area) {

        if(area <= 0){
            throw new InvalidSpaceAreaException();
        }

        this.area = area;
        this.roomCount = DEF_ROOM_COUNT;

    }

    public Office(double area, int roomCount) {

        if(area <= 0){
            throw new InvalidSpaceAreaException();
        }

        if(roomCount <= 0) {
            throw new InvalidRoomsCountException();
        }

        this.area = area;
        this.roomCount = roomCount;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {

        if(area <= 0){
            throw new InvalidSpaceAreaException();
        }

        this.area = area;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        if(roomCount <= 0) {
            throw new InvalidRoomsCountException();
        }

        this.roomCount = roomCount;
    }

    @Override
    public String toString() {
        return "Office{" +
                "area=" + area +
                ", roomCount=" + roomCount +
                '}';
    }
}
