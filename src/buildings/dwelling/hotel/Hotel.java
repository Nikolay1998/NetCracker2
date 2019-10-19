package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;

public class Hotel extends Dwelling {

    public Hotel(int floorCount, int[] flatCount) {
        super(floorCount, flatCount);
    }

    public Hotel(Floor[] floors) {
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
}
