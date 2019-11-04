package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.DwellingFloorIterator;

import java.util.Iterator;

public class HotelFloor extends DwellingFloor {

    public static final Rating DEF_RATING = Rating.ONE_STAR;

    private Rating rating;


    public HotelFloor(int spaceCount) {
        super(spaceCount);
        rating = DEF_RATING;
    }

    public HotelFloor(Space[] spaces) {
        super(spaces);
        rating = DEF_RATING;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("HotelFloor(" +
                getRating() + ", " +
                +getSpaceCount() + ", ");
        DwellingFloorIterator iterator = new DwellingFloorIterator(this, getSpaces());
        while (iterator.hasNext()) {
            stringBuffer.append(iterator.next().toString());
            stringBuffer.append(", ");
        }
        stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public boolean equals(Floor floor) {
        if (!HotelFloor.class.isInstance(floor)) {
            return false;
        }
        HotelFloor eqFloor = (HotelFloor) floor;
        if (eqFloor.getSpaceCount() != this.getSpaceCount()) {
            return false;
        }
        if (eqFloor.getRating() != this.getRating()) {
            return false;
        }
        for (int i = 0; i < this.getSpaceCount(); i++) {
            if (!this.getSpace(i).equals(floor.getSpace(i))) return false;
        }
        return true;
    }

    public Object clone() throws CloneNotSupportedException {
        HotelFloor res = null;
        res = (HotelFloor) super.clone();
        res.rating = rating;
        return res;
    }

    public int hashCode() {

        int hash = getSpaceCount() ^ rating.ordinal();
        Iterator iterator = iterator();
        while (iterator.hasNext()) {
            //HotelFloor curFloor = (HotelFloor) iterator.next();
            hash ^= (iterator.next().hashCode());
        }
        return hash;

    }
}
