package buildings;

import java.io.Serializable;

public interface Space extends Serializable, Cloneable {
    int getRoomCount();
    void setRoomCount(int roomCount);
    double getArea();
    void setArea(double area);
    boolean equals(Space space);
    public Object clone() throws CloneNotSupportedException;
}
