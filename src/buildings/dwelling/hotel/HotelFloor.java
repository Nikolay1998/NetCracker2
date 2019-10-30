package buildings.dwelling.hotel;

import buildings.Space;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.DwellingFloorIterator;

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
                + getSpaceCount() + ", ");
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
        Space[] spaces = new Space[getRoomCount()];
        DwellingFloorIterator iterator = new DwellingFloorIterator(this, spaces);
        int i = 0;
        while (iterator.hasNext()) {
            spaces[i++] = (Space) iterator.next().clone();
        }
        HotelFloor result = new HotelFloor(spaces);
        result.setRating(rating);
        return result;
    }


}
