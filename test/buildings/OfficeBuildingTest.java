package buildings;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class OfficeBuildingTest {
    OfficeBuilding building;
    int size;

    @Before
    public void setUp(){
        size = 4;
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

    @Test
    public void getSortedByAreaOfficesTest(){

        Office[] originalOffices = new Office[building.getOfficeCount()];
        Office[] expectedOffices = new Office[building.getOfficeCount()];
        for(int i = 0; i < building.getOfficeCount(); i++){
            originalOffices[i] = new Office((building.getOfficeCount() - i - 1) * 10);
            expectedOffices[i] = originalOffices[i];
        }

        Collections.shuffle(Arrays.asList(originalOffices));
        for(int i = 0; i < building.getOfficeCount(); i++){
            building.setOffice(i, originalOffices[i]);
        }

        Office[] sortedByAreaOffices = building.getSortedByAreaOffices();

        for (int i = 0; i < building.getOfficeCount(); i++) {
            assertEquals("on " + i + " iteration.", expectedOffices[i], sortedByAreaOffices[i]);
        }


    }
}
