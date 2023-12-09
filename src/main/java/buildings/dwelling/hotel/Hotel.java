package buildings.dwelling.hotel;

import buildings.Building;
import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.DwellingIterator;

import java.util.Iterator;

public class Hotel extends Dwelling {

    public Hotel(int floorCount, int[] flatCount) {
        if (floorCount != flatCount.length) {
            throw new IllegalArgumentException();
        }
        floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            floors[i] = new HotelFloor(flatCount[i]);
        }
    }

    public Hotel(Floor... floors) {
        super(floors);
    }

    public Rating getRating() {
        Rating bestRating = Rating.ONE_STAR;
        for (int i = 0; i < getFloorCount(); i++) {
            if (HotelFloor.class.isInstance(getFloor(i))) {
                HotelFloor currentFloor = (HotelFloor) getFloor(i);
                if (currentFloor.getRating().ordinal() > bestRating.ordinal()) {
                    bestRating = currentFloor.getRating();
                }
            }
        }
        return bestRating;
    }

    public Space getBestSpace() {
        double bestSpaceCoeff = 0;
        int floorWithBestSpaceNum = 0;
        for (int i = 0; i < getFloorCount(); i++) {
            if (HotelFloor.class.isInstance(getFloor(i))) {
                HotelFloor currentFloor = (HotelFloor) getFloor(i);
                if (currentFloor.getSpaces().length > 0) {
                    if (currentFloor.getBestSpace().getArea() * currentFloor.getRating().getKoeff() > bestSpaceCoeff) {
                        bestSpaceCoeff = currentFloor.getBestSpace().getArea() * currentFloor.getRating().getKoeff();
                        floorWithBestSpaceNum = i;
                    }
                }
            }
        }
        return getFloor(floorWithBestSpaceNum).getBestSpace();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Hotel(" + getRating() + ", " +
                getFloorCount() + ", ");
        DwellingIterator iterator = new DwellingIterator(this, getFloors());
        while (iterator.hasNext()){
            string.append(iterator.next().toString());
            string.append(", ");
        }
        string.delete(string.length() - 2, string.length());
        string.append(") ");
        return string.toString();
    }


    @Override
    public boolean equals(Building building) {
        if (!Hotel.class.isInstance(building)) {
            return false;
        }
        if (building.getFloorCount() != this.getFloorCount()) {
            return false;
        }
        for (int i = 0; i < this.getFloorCount(); i++) {
            if (!this.getFloor(i).equals(building.getFloor(i))) return false;
        }
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        HotelFloor res = (HotelFloor) super.clone();
        return res;
    }

    public int hashCode() {

        int hash = getFloorCount();
        Iterator iterator = iterator();
        while (iterator.hasNext()) {
            HotelFloor curFloor = (HotelFloor) iterator.next();
            hash ^= curFloor.hashCode();
        }
        return hash;

    }
}
