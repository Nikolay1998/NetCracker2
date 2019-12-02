package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFactory;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFactory;
import buildings.office.OfficeFloor;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Buildings {

    private static BuildingFactory buildingFactory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory factory) {
        buildingFactory = factory;
    }

    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream dout = new DataOutputStream(out);
        dout.writeInt(building.getFloorCount());
        for (int i = 0; i < building.getFloorCount(); i++) {
            dout.writeInt(building.getFloor(i).getSpaceCount());
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                dout.writeDouble(building.getFloor(i).getSpace(j).getArea());
                dout.writeInt(building.getFloor(i).getSpace(j).getRoomCount());
            }
        }
        dout.close();
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream din = new DataInputStream(in);
        Building building;
        int floorCount = din.readInt();
        Floor[] floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            Floor currentFloor;
            int spaceCount = din.readInt();
            currentFloor = createFloor(spaceCount);
            for (int j = 0; j < spaceCount; j++) {
                Space space;
                space = newSpace(din.readDouble(), din.readInt());
                currentFloor.setSpace(j, space);
            }
            floors[i] = currentFloor;
        }
        building = createBuilding(floors);
        din.close();
        return building;
    }

    public static void writeBuilding(Building building, Writer out) throws IOException {
        out.write(String.valueOf(building.getFloorCount()) + ' ');
        for (int i = 0; i < building.getFloorCount(); i++) {
            out.write(String.valueOf(building.getFloor(i).getSpaceCount()) + ' ');
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                out.write(String.valueOf(building.getFloor(i).getSpace(j).getArea()) + ' ');
                out.write(String.valueOf(building.getFloor(i).getSpace(j).getRoomCount()) + ' ');
            }
        }
        out.flush();
        out.close();
    }

    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);
        streamTokenizer.nextToken();
        int floorCount = (int) streamTokenizer.nval;
        Building building;
        Floor[] floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            Floor currentFloor;
            streamTokenizer.nextToken();
            int spaceCount = (int) streamTokenizer.nval;
            currentFloor = createFloor(spaceCount);
            for (int j = 0; j < spaceCount; j++) {
                Space space;
                streamTokenizer.nextToken();
                double area = streamTokenizer.nval;
                streamTokenizer.nextToken();
                int roomCount = (int) streamTokenizer.nval;
                space = newSpace(area, roomCount);
                currentFloor.setSpace(j, space);
            }
            floors[i] = currentFloor;
        }
        building = createBuilding(floors);
        return building;
    }

    private static Space newSpace(double area, int roomCount) throws IllegalArgumentException {
        return buildingFactory.createSpace(roomCount, area);
        //return (Space) spaceClass.getConstructor(Double.TYPE, Integer.TYPE).newInstance(area, roomCount);
    }

    private static Space newSpace(double area) throws IllegalArgumentException {
        return buildingFactory.createSpace(area);
    }

    private static Floor createFloor(int spacesCount) throws IllegalArgumentException {
        return buildingFactory.createFloor(spacesCount);
    }

    private static Floor createFloor(Space[] spaces) throws IllegalArgumentException {
        return buildingFactory.createFloor(spaces);
    }

    private static Building createBuilding(int floorsCount, int[] spacesCounts) throws IllegalArgumentException {
        return buildingFactory.createBuilding(floorsCount, spacesCounts);
    }

    private static Building createBuilding(Floor[] floors) throws IllegalArgumentException {
        return buildingFactory.createBuilding(floors);
    }
}

