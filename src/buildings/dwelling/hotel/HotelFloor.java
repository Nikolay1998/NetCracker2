package buildings.dwelling.hotel;

import buildings.Space;
import buildings.dwelling.DwellingFloor;

public class HotelFloor extends DwellingFloor {

    public static final Rating DEF_RATING = Rating.ONE_STAR;

    private Rating rating;


    public HotelFloor(int flatCount) {
        super(flatCount);
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
}
