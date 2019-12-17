package buildings.dwelling;

import buildings.Floor;
import buildings.Space;
import buildings.exceptions.SpaceIndexOutOfBoundsException;

import java.util.*;
import java.util.function.Consumer;

public class DwellingFloor implements Floor {
    //private Space[] spaces;
    private List<Space> spaces;

    public DwellingFloor(int flatCount) {
        spaces = new LinkedList<>();
        //spaces = new Space[flatCount];
        for (int i = 0; i < flatCount; i++) {
            //spaces[i] = new Flat();
            spaces.add(new Flat());
        }
    }

    public DwellingFloor(Space... spaces) {
        this.spaces.addAll(Arrays.asList(spaces));
    }

    public int getSpaceCount() {
        return spaces.size();
    }

    public double getArea() {
        double result = 0;
        for (Space space : spaces) {
            result += space.getArea();
        }
        return result;
    }

    public int getRoomCount() {
        int result = 0;
        for (int i = 0; i < spaces.size(); i++) {
            result += spaces.get(i).getRoomCount();
        }
        return result;
    }

    public Space[] getSpaces() {
        return (Space[]) spaces.toArray();
    }

    public Space getSpace(int num) {
        if (num >= getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return spaces.get(num);
    }

    public void setSpace(int num, Space newSpace) {
        if (num >= getSpaceCount()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        spaces.set(num, newSpace);
    }

    public void addSpace(int num, Space newSpace) {
        spaces.add(num, newSpace);
        /*
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

         */
    }

    public void deleteSpace(int num) {
        spaces.remove(num);
        /*
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

         */
    }

    public Space getBestSpace() {
        if (spaces.size() > 0) {
            double bestArea = 0;
            int bestSpaceNum = 0;
            int i = 0;
            for (Space space : spaces) {
                if (space.getArea() > bestArea) {
                    bestArea = space.getArea();
                    bestSpaceNum = i;
                }
                i++;
            }
            return spaces.get(bestSpaceNum);
        } else return null;
    }

    @Override
    public boolean equals(Floor floor) {
        floor.getSpaces();
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
        return new DwellingFloorIterator(this, (Space[]) this.spaces.toArray());
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
        DwellingFloorIterator iterator = new DwellingFloorIterator(this, (Space[]) spaces.toArray());
        int i = 0;
        while (iterator.hasNext()) {
            cloneSpaces[i++] = (Space) iterator.next().clone();
        }
        res.spaces = Arrays.asList(cloneSpaces);
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
