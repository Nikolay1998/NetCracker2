import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DwellingTest {
//    private Dwelling building;
//
//    @Before
//    public void setUp() throws Exception {
//        building = new Dwelling(4, new int[] {3, 1, 4, 2});
//    }

    @Test
    public void testGetAndSetFlat() {
        Dwelling building = new Dwelling(4, new int[]{3, 1, 4, 2});
        assertNotNull(building);

        Flat[] expectedFlats = new Flat[10];
        for (int i = 0; i < 10; i++) {
            expectedFlats[i] = new Flat(i*10, i);
            building.setFlat(i, expectedFlats[i]);
        }

        for (int i = 0; i < 10; i++) {
            Flat actualFlat = building.getFlat(i);
            System.out.println(actualFlat);
            assertEquals("Квартиры отличаются", expectedFlats[i], actualFlat);
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void failIncorrectInitialization() throws Exception {
        new Dwelling(4, new int[]{3, 1, 4});
    }
}