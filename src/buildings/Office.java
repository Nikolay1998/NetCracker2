package buildings;

public class Office {
    private double area;
    private int roomCount;

    public static final double DEF_AREA = 250.0;
    public static final int DEF_ROOM_COUNT = 1;

    public Office() {
        this.area = DEF_ROOM_COUNT;
        this.roomCount = DEF_ROOM_COUNT;
    }

    public Office(double area) {
        this.area = area;
        this.roomCount = DEF_ROOM_COUNT;

    }

    public Office(double area, int roomCount) {
        this.area = area;
        this.roomCount = roomCount;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }
}
