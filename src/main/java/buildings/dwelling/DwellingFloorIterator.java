package buildings.dwelling;

import buildings.Space;

import java.util.Iterator;

public class DwellingFloorIterator implements Iterator<Space> {
    private DwellingFloor floor;
    private Space[] spaces;
    private int index;

    public DwellingFloorIterator(DwellingFloor floor, Space[] spaces) {
        this.floor = floor;
        this.spaces = spaces;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if(index < spaces.length){
            return true;
        }
        return false;
    }

    @Override
    public Space next() {
        return spaces[index++];
    }
}
