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
            expectedFlats[i] = new Flat(i * 10, i);
            building.setFlat(i, expectedFlats[i]);
        }

        for (int i = 0; i < 12; i++) {
            Flat actualFlat = building.getFlat(i);
            assertEquals("Квартиры отличаются", expectedFlats[i], actualFlat);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void failIncorrectInitialization() throws Exception {
        new Dwelling(4, new int[]{3, 1, 4});
    }

   /* @Test
    public void testGetAndSetFlat() {


    }

    */

    @Test
    public void testAddAndDeleteFlat() {
        for (int i = 0; i < TEST_SIZE + 1; i++) {
            Flat addingFlat = new Flat(i * 20, i * 2);
            building.addFlat(i, addingFlat);
            //System.out.println(addingFlat);
            assertEquals("Квартира добавленна некоркетно", addingFlat, building.getFlat(i));
            building.deleteFlat(i);
        }
    }

    @Test
    public void testGetBestSpace() {
        Flat expectedBestSpace = building.getFlat(TEST_SIZE - 1);
        assertEquals("Поиск наибольшей по площади квартиры работает некоректно", expectedBestSpace, building.getBestSpace());
    }

    @Test
    public void testSortFlatsbyArea() {
        Flat[] originalFlats = new Flat[TEST_SIZE];
        Flat[] expectedFlats = new Flat[TEST_SIZE];
        for (int i = 0; i < TEST_SIZE; i++) {
            originalFlats[i] = new Flat((TEST_SIZE - i - 1) * 10);
            expectedFlats[i] = originalFlats[i];
        }

        Collections.shuffle(Arrays.asList(originalFlats));

        for (int i = 0; i < TEST_SIZE; i++) {
            building.setFlat(i, originalFlats[i]);
        }

        Flat[] sortedFlats = building.getSortedByAreaFlats();
        for (int i = 0; i < TEST_SIZE; i++) {
            System.out.println(sortedFlats[i].toString());
            assertEquals(expectedFlats[i], sortedFlats[i]);
        }

    }
/*
    @Test
    public void testBubbleSort(){
        building.bubbleSort();
    }

 */
}