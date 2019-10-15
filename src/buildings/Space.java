package buildings;

import java.io.Serializable;

public interface Space extends Serializable {
    int getRoomCount();
    void setRoomCount(int roomCount);
    double getArea();
    void setArea(double area);
    boolean equals(Space space);
}
