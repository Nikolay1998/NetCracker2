package buildings;

import buildings.dwelling.DwellingFloor;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
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
        /*
        Floor[] floors = new OfficeFloor[4];
        for(int i = 0; i < 4; i++){
            floors[i] = new OfficeFloor(3);
        }
        building = new OfficeBuilding(floors);

         */
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
        assertEquals(expectedSize, building.getSpaceCount());
    }

    @Test
    public void getSortedByAreaOfficesTest(){

        Office[] originalOffices = new Office[building.getSpaceCount()];
        Office[] expectedOffices = new Office[building.getSpaceCount()];
        for(int i = 0; i < building.getSpaceCount(); i++){
            originalOffices[i] = new Office((building.getSpaceCount() - i - 1) * 10 + 1);
            expectedOffices[i] = originalOffices[i];
        }

        Collections.shuffle(Arrays.asList(originalOffices));
        for(int i = 0; i < building.getSpaceCount(); i++){
            building.setSpace(i, originalOffices[i]);
        }

        Space[] sortedByAreaOffices = building.getSortedByAreaSpaces();

        for (int i = 0; i < building.getSpaceCount(); i++) {
            assertEquals("on " + i + " iteration.", expectedOffices[i], sortedByAreaOffices[i]);
        }


    }

    @Test
    public void cloneTest(){
        OfficeBuilding newOffice = (OfficeBuilding) building.clone();
        building.toString();
        System.out.println(newOffice.toString() + "\n" + building.toString());
        assertTrue(building.equals(newOffice));

    }
}
