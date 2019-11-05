package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class BuildingsTest {

    Building building;

    Building officeBuilding;


    @Before
    public void setUp() {
        final int FLOOR_SIZE = 14;
        Floor[] floors = new Floor[FLOOR_SIZE];
        for (int i = 0; i < FLOOR_SIZE; i++) {
            Space[] spaces = new Space[12];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j] = new Flat(i * 10 + j + 1, i + j + 1);
            }
            floors[i] = new DwellingFloor(spaces);
        }
        building = new Dwelling(floors);

        Floor[] officeFloors = new Floor[FLOOR_SIZE];
        for (int i = 0; i < FLOOR_SIZE; i++) {
            Space[] spaces = new Space[12];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j] = new Office(i * 10 + j + 1, i + j + 1);
            }
            officeFloors[i] = new OfficeFloor(spaces);
        }
        officeBuilding = new OfficeBuilding(officeFloors);
    }

    @Test
    public void inputOutputTest() {

        try (OutputStream out = new FileOutputStream("build.bin");
             InputStream in = new FileInputStream("build.bin")
        ) {
            Buildings.outputBuilding(building, out);
            Building readBuilding = Buildings.inputBuilding(in, Dwelling.class, DwellingFloor.class, Flat.class);
            System.out.println(building.toString());
            System.out.println(readBuilding.toString());
            assertTrue(readBuilding.equals(building));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (OutputStream out = new FileOutputStream("build.bin");
             InputStream in = new FileInputStream("build.bin")
        ) {
            Buildings.outputBuilding(officeBuilding, out);
            Building readBuilding = Buildings.inputBuilding(in, OfficeBuilding.class, OfficeFloor.class, Office.class);
            System.out.println(officeBuilding.toString());
            System.out.println(readBuilding.toString());
            assertTrue(readBuilding.equals(officeBuilding));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void readWriteTest() {
        try (Writer out = new FileWriter("build.txt");
             Reader in = new FileReader("build.txt")
        ) {
            Buildings.writeBuilding(building, out);
            Building readBuilding = Buildings.readBuilding(in, Dwelling.class, DwellingFloor.class, Flat.class);
            assertTrue(readBuilding.equals(building));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (OutputStream out = new FileOutputStream("build.bin");
             InputStream in = new FileInputStream("build.bin")
        ) {
            Buildings.outputBuilding(officeBuilding, out);
            Building readBuilding = Buildings.inputBuilding(in, OfficeBuilding.class, OfficeFloor.class, Office.class);
            System.out.println(officeBuilding.toString());
            System.out.println(readBuilding.toString());
            assertTrue(readBuilding.equals(officeBuilding));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
