package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;


public class SerializeTest {

    @Test
    public void serializeDeserializeTest(){
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


        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("serializedBuilding.ser"));
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("serializedBuilding.ser")); ){
            output.writeObject(building);
            Building readBuilding = (Building) input.readObject();
            assertTrue(readBuilding.equals(building));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
