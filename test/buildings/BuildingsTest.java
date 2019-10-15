package buildings;

import buildings.Dwelling.Dwelling;
import buildings.Dwelling.DwellingFloor;
import buildings.Dwelling.Flat;
import buildings.Office.Office;
import buildings.Office.OfficeBuilding;
import buildings.Office.OfficeFloor;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class BuildingsTest {

    @Test
    public void inputOutputTest() {
        for(int m = 0; m < 20; m++) {
            final int FLOOR_SIZE = 14;
            Floor[] floors = new Floor[FLOOR_SIZE];
            for (int i = 0; i < FLOOR_SIZE; i++) {
                Space[] spaces = new Space[(int) Math.random() * 10 + 1];
                for (int j = 0; j < spaces.length; j++) {
                    if (Math.random() < 0.5) spaces[j] = new Flat(i * 10 + j + 1, i + j + 1);
                    else spaces[j] = new Office(i * 10 + j + 1, i + j + 1);
                }

                if (Math.random() < 0.5) floors[i] = new OfficeFloor(spaces);

                else floors[i] = new DwellingFloor(spaces);
            }
            Building building;
            if (Math.random() < 0.5) building = new OfficeBuilding(floors);
            else building = new Dwelling(floors);


            try (OutputStream out = new FileOutputStream("build.bin");
                 InputStream in = new FileInputStream("build.bin")
            ) {
                Buildings.outputBuilding(building, out);
                Building readBuilding = Buildings.inputBuilding(in);

                assertTrue(readBuilding.equals(building));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void readWriteTest() {
        for(int m = 0; m < 20; m++) {
            final int FLOOR_SIZE = 14;
            Floor[] floors = new Floor[FLOOR_SIZE];
            for (int i = 0; i < FLOOR_SIZE; i++) {
                Space[] spaces = new Space[(int) Math.random() * 10 + 1];
                for (int j = 0; j < spaces.length; j++) {
                    if (Math.random() < 0.5) spaces[j] = new Flat(i * 10 + j + 1, i + j + 1);
                    else spaces[j] = new Office(i * 10 + j + 1, i + j + 1);
                }

                if (Math.random() < 0.5) floors[i] = new OfficeFloor(spaces);

                else floors[i] = new DwellingFloor(spaces);
            }
            Building building;
            if (Math.random() < 0.5) building = new OfficeBuilding(floors);
            else building = new Dwelling(floors);


            try (Writer out = new FileWriter("build.txt");
                 Reader in = new FileReader("build.txt")
            ) {
                Buildings.writeBuilding(building, out);
                Building readBuilding = Buildings.readBuilding(in);
                assertTrue(readBuilding.equals(building));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
