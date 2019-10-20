package buildings.dwelling;

import buildings.Floor;

import java.util.Iterator;

public class DwellingIterator implements Iterator<Floor> {
    Dwelling dwelling;
    Floor[] floors;
    int index;

    public DwellingIterator(Dwelling dwelling, Floor[] floors) {
        this.dwelling = dwelling;
        this.floors = floors;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index < floors.length) {
            return true;
        }
        return false;
    }

    @Override
    public Floor next() {
        return floors[index++];
    }
}
