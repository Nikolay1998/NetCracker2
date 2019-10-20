package buildings.office;

import buildings.Floor;
import buildings.dwelling.DwellingFloor;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class OfficeFloorIteratorTest {

    @Test
    public void test(){
        Floor floor = new OfficeFloor(15);
        Iterator iterator = floor.iterator();
        int i = 0;
        assertEquals(floor.getSpace(i++), iterator.next());
        while(iterator.hasNext()){
            assertEquals(floor.getSpace(i++), iterator.next());
        }
        assertEquals(floor.getSpaceCount(), i);
    }

}