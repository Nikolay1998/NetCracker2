package buildings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OfficeBuildingTest {
    OfficeBuilding building;
    int size;

    @Before
    public void setUp(){
        size = 10;
        int[] officeCounts = new int[size];
        for(int i = 0; i < size; i++){
            officeCounts[i] = i+1;
        }
        building = new OfficeBuilding(size, officeCounts);
    }

    @Test
    public void getFloorCountTest(){
        assertEquals(size, building.getFloorCount());
    }

    @Test
    public void getOfficeCountTest(){
        int expectedSize = 0;
        for(int i = 0; i < size; i++){
            expectedSize += i + 1;
        }
        assertEquals(expectedSize, building.getOfficeCount());
    }
}
