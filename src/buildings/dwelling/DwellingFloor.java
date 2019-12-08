package buildings.dwelling;

import buildings.Floor;
import buildings.Space;
import buildings.exceptions.SpaceIndexOutOfBoundsException;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DwellingFloor implements Floor {
    private Space[] spaces;

    public DwellingFloor(int flatCount) {
        spaces = new Space[flatCount];
        for (int i = 0; i < flatCount; i++) {
            spaces[i] = new Flat();
        }
    }

    public DwellingFloor(Space... spaces) {
        this.spaces = spaces;
    }

    public int getSpaceCount() {
        return spaces.length;
    }

    public double getArea() {
        double result = 0;
        for (int i = 0; i < spaces.length; i++) {
            result += spaces[i].getArea();
        }
        return result;
    }

    public int getRoomCount() {
        int result = 0;
        for (int i = 0; i < spaces.length; i++) {
            result += spaces[i].getRoomCount();
        }
        return result;
    }

    public Space[] getSpaces() {
        return spaces;
    }

    public Space getSpace(int num) {
        if (num >= getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return spaces[num];
    }

    public void setSpace(int num, Space newSpace) {
        if (num >= getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        spaces[num] = newSpace;
    }

    public void addSpace(int num, Space newSpace) {
        if (num > getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }

        Space[] newSpaces = new Space[spaces.length + 1];
        int i = 0;
        while (i < num) {
            newSpaces[i] = spaces[i];
            i++;
        }
        newSpaces[i] = newSpace;
        i++;
        while (i < newSpaces.length) {
            newSpaces[i] = spaces[i - 1];
            i++;
        }
        spaces = newSpaces;
    }

    public void deleteSpace(int num) {
        if (num >= getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }

        Space[] newSpaces = new Space[spaces.length - 1];
        int i = 0;
        while (i < num) {
            newSpaces[i] = spaces[i];
            i++;
        }
        i++;
        while (i < newSpaces.length) {
            newSpaces[i] = spaces[i - 1];
            i++;
        }
        spaces = newSpaces;
    }

    public Space getBestSpace() {
        if (spaces.length > 0) {
            double bestArea = 0;
            int bestSpaceNum = 0;
            for (int i = 0; i < spaces.length; i++) {
                if (spaces[i].getArea() > bestArea) {
                    bestArea = spaces[i].getArea();
                    bestSpaceNum = i;
                }
            }
            return spaces[bestSpaceNum];
        } else return null;
    }

    @Override
    public boolean equals(Floor floor) {
        if (!DwellingFloor.class.isInstance(floor)) {
            return false;
        }
        if (floor.getSpaceCount() != this.getSpaceCount()) {
            return false;
        }
        for (int i = 0; i < this.getSpaceCount(); i++) {
            if (!this.getSpace(i).equals(floor.getSpace(i))) return false;
        }
        return true;
    }


    @Override
    public Iterator iterator() {
        return new DwellingFloorIterator(this, this.spaces);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("DwellingFloor(" +
                getSpaceCount() + ", ");
        DwellingFloorIterator iterator = new DwellingFloorIterator(this, getSpaces());
        while (iterator.hasNext()) {
            string.append(iterator.next().toString());
            string.append(", ");
        }
        string.delete(string.length() - 2, string.length());
        string.append(")");
        return string.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        DwellingFloor res = null;
        res = (DwellingFloor) super.clone();
        Space[] cloneSpaces = new Space[getSpaceCount()];
        DwellingFloorIterator iterator = new DwellingFloorIterator(this, spaces);
        int i = 0;
        while (iterator.hasNext()) {
            cloneSpaces[i++] = (Space) iterator.next().clone();
        }
        res.spaces = cloneSpaces;
        return res;
    }

    public int hashCode() {

        int hash = getSpaceCount();
        Iterator iterator = iterator();
        while (iterator.hasNext()) {
            hash ^= iterator.next().hashCode();
        }
        return hash;

    }
}
