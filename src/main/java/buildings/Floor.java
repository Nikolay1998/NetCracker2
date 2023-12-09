package buildings;

import java.io.Serializable;
import java.util.Iterator;

public interface Floor extends Serializable, Iterable<Space>, Cloneable {
    int getSpaceCount();
    double getArea();
    int getRoomCount();
    Space[] getSpaces();
    Space getSpace(int num);
    void setSpace(int num, Space space);
    void addSpace(int num, Space space);
    void deleteSpace(int num);
    Space getBestSpace();
    boolean equals(Floor floor);
    public Object clone() throws CloneNotSupportedException;
}
