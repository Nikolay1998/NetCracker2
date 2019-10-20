package buildings.office;

import buildings.Building;
import buildings.dwelling.Dwelling;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class OfficeBuildingIteratorTest {

    @Test
    public void test() {
        Building building = new OfficeBuilding(5, new int[]{4, 3, 5, 4, 3});
        Iterator iterator = building.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(building.getFloor(i++), iterator.next());
        }
        assertEquals(building.getFloorCount(), i);
    }

}