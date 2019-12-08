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
import java.util.Formatter;
import java.util.Scanner;

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

    public static Building inputBuilding(InputStream in) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        DataInputStream din = new DataInputStream(in);
        Building building;
        int floorCount = din.readInt();
        Floor[] floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            Floor currentFloor;
            int spaceCount = din.readInt();
            currentFloor = createFloor(spaceCount, DwellingFloor.class);
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
        Formatter formatter = new Formatter(out);
        formatter.format("%d ", building.getFloorCount());
        //out.write(String.valueOf(building.getFloorCount()) + ' ');
        for (int i = 0; i < building.getFloorCount(); i++) {
            formatter.format("%d ", building.getFloor(i).getSpaceCount());
            //out.write(String.valueOf(building.getFloor(i).getSpaceCount()) + ' ');
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                formatter.format("%f %d ", building.getFloor(i).getSpace(j).getArea(), building.getFloor(i).getSpace(j).getRoomCount());
                //out.write(String.valueOf(building.getFloor(i).getSpace(j).getArea()) + ' ');
                //out.write(String.valueOf(building.getFloor(i).getSpace(j).getRoomCount()) + ' ');
            }
        }
        out.flush();
        out.close();
    }

    public static Building readBuilding(Reader in) throws IOException {
        Scanner scanner = new Scanner(in);
        int floorCount = scanner.nextInt();
        Building building;
        Floor[] floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            Floor currentFloor;
            int spaceCount = scanner.nextInt();
            currentFloor = createFloor(spaceCount);
            for (int j = 0; j < spaceCount; j++) {
                Space space;
                double area = scanner.nextDouble();
                int roomCount = scanner.nextInt();
                space = newSpace(area, roomCount);
                currentFloor.setSpace(j, space);
            }
            floors[i] = currentFloor;
        }

        building = createBuilding(floors);
        return building;
    }

    private static <T> Space newSpace(double area, int roomCount) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //return buildingFactory.createSpace(roomCount, area);
        T obj = new Space();
        return   .class.getConstructor(Double.TYPE, Integer.TYPE).newInstance(area, roomCount);
    }

    private static Space newSpace(double area, Class spaceClass) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //return buildingFactory.createSpace(area);
        return (Space) spaceClass.getConstructor(Double.TYPE).newInstance(area);
    }

    private static Floor createFloor(int spacesCount, Class floorClass) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //return buildingFactory.createFloor(spacesCount);
        return (Floor) floorClass.getConstructor(Integer.TYPE).newInstance(spacesCount);
    }

    private static Floor createFloor(Class floorClass, Space... spaces) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Floor) floorClass.getConstructor().newInstance(spaces);
        //return buildingFactory.createFloor(spaces);
    }

    private static Building createBuilding(int floorsCount, int[] spacesCounts, Class buildingClass) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Building) buildingClass.getConstructor().newInstance(floorsCount, spacesCounts);
        //return buildingFactory.createBuilding(floorsCount, spacesCounts);
    }

    private static Building createBuilding(Class buildingClass, Floor... floors) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Building) buildingClass.getConstructor().newInstance(floors);
        //return buildingFactory.createBuilding(floors);
    }


}

