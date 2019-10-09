package buildings;

public class Flat implements Space{
    private double area;
    private int roomCount;
    public static final double DEF_AREA = 50.0;
    public static final int DEF_ROOM_COUNT = 2;

    public Flat(){
        area = DEF_AREA;
        roomCount = DEF_ROOM_COUNT;
    }

    public Flat(double area){
        this.area = area;
        roomCount = DEF_ROOM_COUNT;
    }

    public Flat(double area, int roomCount){
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
        this.roomCount = roomCount;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "area=" + area +
                ", roomCount=" + roomCount +
                '}';
    }
}
