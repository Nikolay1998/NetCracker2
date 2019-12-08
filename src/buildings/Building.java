package buildings;

import java.io.Serializable;

public interface Building extends Serializable, Iterable<Floor>, Cloneable {
    int getFloorCount();
    int getSpaceCount();
    double getArea();
    int getRoomCount();
    Floor[] getFloors();
    Floor getFloor(int num);
    void setFloor(int num, Floor floor);
    Space getSpace(int num);
    void setSpace(int num, Space space);
    void addSpace(int num, Space space);
    void deleteSpace(int num);
    Space getBestSpace();
    Space[] getSortedByAreaSpaces();
    boolean equals(Building building);
    public Object clone() throws CloneNotSupportedException;
    String toView();

}
