package buildings;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Building building = new Dwelling(4, new int[] {3,5,6,3});
        building.addSpace(3, new Office());
        try {
            boolean bol = true;
            FileOutputStream fout = new FileOutputStream("build.bin");
            Buildings.outputBuilding(building, fout);
            Building newBuilding = Buildings.inputBuilding(new FileInputStream("build.bin"));
            Buildings.writeBuilding(newBuilding, new FileWriter("build.txt"));
            Reader reader = new FileReader("build.txt");
            Building lastBuilding = Buildings.readBuilding(reader);
            Buildings.writeBuilding(lastBuilding, new FileWriter("build.txt"));
        }catch (FileNotFoundException e){
            System.out.println("No such files");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
