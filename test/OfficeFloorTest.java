import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OfficeFloorTest {
    OfficeFloor floor;
    int size;

    @Before
    public void setUp() {
        size = 20;
        Office[] offices = new Office[size];
        for (int i = 0; i < size; i++) {
            offices[i] = new Office(10*i, i);
        }
        floor = new OfficeFloor(offices);
    }

    @Test
    public void getFloorCountTest() {
        assertEquals(size, floor.getOfficesCount());
    }

    @Test
    public void getAreaTest(){
        double area = 0;
        for(int i = 0; i < size; i++) {
            area += i*10;
        }
        assertEquals((int)area, (int)floor.getArea());
    }
}
