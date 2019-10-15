package buildings;

import buildings.Office.Office;
import buildings.Office.OfficeFloor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OfficeFloorTest {
    OfficeFloor floor;
    int size;

    @Before
    public void setUp() {
        size = 100;
        Office[] offices = new Office[size];
        for (int i = 0; i < size; i++) {
            offices[i] = new Office(10 * i + 1, i + 1);
        }
        floor = new OfficeFloor(offices);
    }

    @Test
    public void getSpaceCountTest() {
        assertEquals(size, floor.getSpaceCount());
    }

    @Test
    public void getAreaTest() {
        double area = 0;
        for (int i = 0; i < size; i++) {
            area += i * 10 + 1;
        }
        assertEquals((int) area, (int) floor.getArea());
    }

    @Test
    public void getRoomCountTest() {
        int roomCount = 0;
        for (int i = 0; i < size; i++) {
            roomCount += i + 1;
        }
        assertEquals(roomCount, floor.getRoomCount());
    }

    @Test
    public void getBestSpaceTest() {
        Office bigOffice = new Office(size*10+1, 10);
        floor.setSpace(2, bigOffice);
        assertEquals(bigOffice, floor.getBestSpace());
    }

    @Test
    public void setAndGetOfficeTest() {
        Office[] offices = new Office[size];
        for (int i = 0; i < size; i++) {
            offices[i] = new Office(i * 20 + 1, i + 1);
            floor.setSpace(i, offices[i]);
        }
        for (int i = 0; i < size; i++) {
            assertEquals(offices[i], floor.getSpace(i));
        }
    }


    @Test
    public void addAndDeleteOfficeTest() {
        for (int i = 0; i < size; i++) {
            floor.addSpace(i, new Office());
            assertEquals("on " + i + " iteration", 1, floor.getSpace(i).getRoomCount());
            floor.deleteSpace(i);
        }
        Office lastOffice = new Office(130, 1);
        floor.addSpace(size, lastOffice);
        assertEquals(lastOffice, floor.getSpace(size));
        assertEquals(size + 1, floor.getSpaceCount());
    }


}
