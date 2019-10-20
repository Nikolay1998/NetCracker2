package buildings.dwelling;

import buildings.Building;
import buildings.Floor;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class DwellingIteratorTest {

    @Test
    public void test() {
        Building building = new Dwelling(5, new int[]{4, 3, 5, 4, 3});
        Iterator iterator = building.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(building.getFloor(i++), iterator.next());
        }
        assertEquals(building.getFloorCount(), i);
    }

}