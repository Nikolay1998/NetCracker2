package buildings;

public interface Floor {
    int getSpaceCount();
    double getArea();
    int getRoomCount();
    Space[] getSpaces();
    Space getSpace(int num);
    void setSpace(int num, Space space);
    void addSpace(int num, Space space);
    void deleteSpace(int num);
    Space getBestSpace();
}
