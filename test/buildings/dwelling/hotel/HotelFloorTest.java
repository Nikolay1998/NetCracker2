package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.dwelling.Flat;
import org.junit.Test;

import static org.junit.Assert.*;

public class HotelFloorTest {

    @Test
    public void HotelFloorTest(){
        HotelFloor hotelFloor = new HotelFloor(10);
        Flat bigFlat = new Flat(150,3);
        hotelFloor.addSpace(4,bigFlat);
        HotelFloor coolHotelFloor = new HotelFloor(10);
        coolHotelFloor.setRating(Rating.FIVE_STAR);
        Flat notBigFlat = new Flat(100, 2);
        coolHotelFloor.addSpace(2,notBigFlat);
        Hotel hotel = new Hotel(new Floor[] {hotelFloor, coolHotelFloor});
        System.out.println(hotel.getBestSpace().getArea());
    }

}