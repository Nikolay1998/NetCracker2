package buildings;

import buildings.Dwelling.Dwelling;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Building building = new Dwelling(3, new int[] {1, 4, 1});
        try {
            Buildings.outputBuilding(building, System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
