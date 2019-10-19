package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.Flat;
import buildings.exceptions.InvalidSpaceAreaException;
import buildings.exceptions.SpaceIndexOutOfBoundsException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class DwellingTest {
    private Dwelling building;
    private final int TEST_SIZE = 10048;
    private final int[] FLOORS_ARRAY = {1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000 ,1048};

    @Before
    public void setUp() throws Exception {
        building = new Dwelling(FLOORS_ARRAY.length, FLOORS_ARRAY);
        assertNotNull(building);
        Flat[] expectedFlats = new Flat[TEST_SIZE];
        for (int i = 0; i < TEST_SIZE; i++) {
            expectedFlats[i] = new Flat(i * 10 + 1, i+1);
            building.setSpace(i, expectedFlats[i]);
        }

        for (int i = 0; i < 12; i++) {
            Space actualSpace = building.getSpace(i);
            assertEquals("Помещения отличаются", expectedFlats[i], actualSpace);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void failIncorrectInitialization() throws Exception {
        new Dwelling(4, new int[]{3, 1, 4});
    }


    @Test
    public void testAddAndDeleteFlat() {
        for (int i = 0; i < TEST_SIZE + 1; i++) {
            Flat addingFlat = new Flat(i * 20 + 1, i * 2 + 1);
            building.addSpace(i, addingFlat);
            //System.out.println(addingFlat);
            assertEquals("Квартира добавленна некоркетно", addingFlat, building.getSpace(i));
            building.deleteSpace(i);
        }
    }

    @Test(expected = SpaceIndexOutOfBoundsException.class)
    public void uncorrectSetSpaceTest(){
        building.setSpace(TEST_SIZE, new Flat());
    }

    @Test(expected = InvalidSpaceAreaException.class)
    public void uncorrectSetNewSpaceTest(){
        building.setSpace(TEST_SIZE, new Flat(0));
    }

    @Test
    public void testGetBestSpace() {
        Space expectedBestSpace = building.getSpace(TEST_SIZE - 1);
        assertEquals("Поиск наибольшего по площади помещения работает некоректно", expectedBestSpace, building.getBestSpace());
    }

    @Test
    public void testSortSpacesbyArea() {
        Flat[] originalFlats = new Flat[TEST_SIZE];
        Flat[] expectedFlats = new Flat[TEST_SIZE];
        for (int i = 0; i < TEST_SIZE; i++) {
            originalFlats[i] = new Flat((TEST_SIZE - i - 1) * 10 + 1);
            expectedFlats[i] = originalFlats[i];
        }

        Collections.shuffle(Arrays.asList(originalFlats));

        for (int i = 0; i < TEST_SIZE; i++) {
            building.setSpace(i, originalFlats[i]);
        }

        Space[] sortedSpaces = building.getSortedByAreaSpaces();
        for (int i = 0; i < TEST_SIZE; i++) {
            System.out.println(sortedSpaces[i].toString());
            assertEquals(expectedFlats[i], sortedSpaces[i]);
        }

    }
}