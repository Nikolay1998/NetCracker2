package buildings.dwelling;

import buildings.Floor;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class DwellingFloorIteratorTest {

    @Test
    public void test(){
        Floor floor = new DwellingFloor(15);
        Iterator iterator = floor.iterator();
        int i = 0;
        while(iterator.hasNext()){
            assertEquals(floor.getSpace(i++), iterator.next());
            //System.out.println(iterator.next());
        }
        assertEquals(floor.getSpaceCount(), i);
    }

}