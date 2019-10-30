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

    public DwellingFloor(Space[] spaces) {
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
        if(num >= getSpaceCount()){
            throw new SpaceIndexOutOfBoundsException();
        }
        return spaces[num];
    }

    public void setSpace(int num, Space newSpace) {
        if(num >= getSpaceCount()){
            throw new SpaceIndexOutOfBoundsException();
        }
        spaces[num] = newSpace;
    }

    public void addSpace(int num, Space newSpace) {
        if(num > getSpaceCount()){
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
        if(num >= getSpaceCount()){
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
        if(spaces.length>0) {
            double bestArea = 0;
            int bestSpaceNum = 0;
            for (int i = 0; i < spaces.length; i++) {
                if (spaces[i].getArea() > bestArea) {
                    bestArea = spaces[i].getArea();
                    bestSpaceNum = i;
                }
            }
            return spaces[bestSpaceNum];
        }
        else return null;
    }

    @Override
    public boolean equals(Floor floor) {
        if(!DwellingFloor.class.isInstance(floor)){
            return false;
        }
        if(floor.getSpaceCount() != this.getSpaceCount()) {
            return false;
        }
        for(int i = 0; i < this.getSpaceCount(); i++){
            if(!this.getSpace(i).equals(floor.getSpace(i))) return false;
        }
        return true;
    }


    @Override
    public Iterator iterator() {
        return new DwellingFloorIterator(this, this.spaces);
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DwellingFloor(" +
                getSpaceCount() + ", ");
        DwellingFloorIterator iterator = new DwellingFloorIterator(this, getSpaces());
        while (iterator.hasNext()){
            stringBuffer.append(iterator.next().toString());
            stringBuffer.append(", ");
        }
        stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public Object clone() {
        Space[] cloneSpaces = new Space[getSpaceCount()];
        DwellingFloorIterator iterator = new DwellingFloorIterator(this, spaces);
        int i = 0;
        while (iterator.hasNext()) {
            cloneSpaces[i++] = (Space) iterator.next().clone();
        }
        DwellingFloor result = new DwellingFloor(cloneSpaces);
        return result;
    }

    public int hashCode(){

        int hash = getSpaceCount();
        Iterator iterator = iterator();
        while(iterator.hasNext()){
            hash^=iterator.next().hashCode();
        }
        return hash;

    }
}
