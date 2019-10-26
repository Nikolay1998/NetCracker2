package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.hotel.Hotel;
import buildings.office.OfficeBuilding;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Building building = new Hotel(3, new int[] {1, 4, 1});
        System.out.println(building.toString());
    }
}
