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

public class Buildings {

    private static BuildingFactory buildingFactory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory factory) {
        buildingFactory = factory;
    }

    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream dout = new DataOutputStream(out);
        dout.writeBoolean(Dwelling.class.isInstance(building));
        dout.writeInt(building.getFloorCount());
        for (int i = 0; i < building.getFloorCount(); i++) {
            dout.writeBoolean(DwellingFloor.class.isInstance(building.getFloor(i)));
            dout.writeInt(building.getFloor(i).getSpaceCount());
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                dout.writeBoolean(Flat.class.isInstance(building.getFloor(i).getSpace(j)));
                dout.writeInt(building.getFloor(i).getSpace(j).getRoomCount());
                dout.writeDouble(building.getFloor(i).getSpace(j).getArea());
            }
        }
        dout.close();
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream din = new DataInputStream(in);

        boolean isDwelling = din.readBoolean();
        Building building;

        int floorCount = din.readInt();
        Floor[] floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            Floor currentFloor;
            boolean isDwellingFloor = din.readBoolean();
            int spaceCount = din.readInt();
            if (isDwellingFloor) {
                buildingFactory = new DwellingFactory();
                //currentFloor = new DwellingFloor(spaceCount);
            } else {
                buildingFactory = new OfficeFactory();
                //currentFloor = new OfficeFloor(spaceCount);
            }
            currentFloor = buildingFactory.createFloor(spaceCount);
            for (int j = 0; j < spaceCount; j++) {
                Space space;
                boolean isFlat = din.readBoolean();
                if (isFlat) {
                    buildingFactory = new DwellingFactory();
                    //space = new Flat();
                } else {
                    buildingFactory = new OfficeFactory();
                    //space = new Office();
                }
                space = buildingFactory.createSpace(din.readInt(), din.readDouble());
                //space.setRoomCount(din.readInt());
                //space.setArea(din.readDouble());
                currentFloor.setSpace(j, space);
            }
            floors[i] = currentFloor;
        }
        if (isDwelling) {
            buildingFactory = new DwellingFactory();
            //building = new Dwelling(floors);
        } else {
            buildingFactory = new OfficeFactory();
            //building = new OfficeBuilding(floors);
        }
        building = buildingFactory.createBuilding(floors);
        din.close();
        return building;
    }

    public static void writeBuilding(Building building, Writer out) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(out);
        out.write(Dwelling.class.isInstance(building) + " " + building.getFloorCount() + ' ');
        for (int i = 0; i < building.getFloorCount(); i++) {
            out.write(DwellingFloor.class.isInstance(building.getFloor(i)) + " ");
            out.write(String.valueOf(building.getFloor(i).getSpaceCount()) + ' ');
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                out.write(Flat.class.isInstance(building.getFloor(i).getSpace(j)) + " ");
                out.write(String.valueOf(building.getFloor(i).getSpace(j).getRoomCount()) + ' ');
                out.write(String.valueOf(building.getFloor(i).getSpace(j).getArea()) + ' ');
            }
        }
        out.flush();
        out.close();
    }

    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);
        streamTokenizer.nextToken();
        String isDwelling = streamTokenizer.sval;
        streamTokenizer.nextToken();
        int floorCount = (int) streamTokenizer.nval;
        Building building;
        Floor[] floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            Floor currentFloor;
            streamTokenizer.nextToken();
            String isDwellingFloor = streamTokenizer.sval;
            streamTokenizer.nextToken();
            int spaceCount = (int) streamTokenizer.nval;
            if (Boolean.valueOf(isDwellingFloor)) {
                buildingFactory = new DwellingFactory();
                //currentFloor = new DwellingFloor(spaceCount);
            } else {
                buildingFactory = new OfficeFactory();
                //currentFloor = new OfficeFloor(spaceCount);
            }
            currentFloor = buildingFactory.createFloor(spaceCount);
            for (int j = 0; j < spaceCount; j++) {
                Space space;
                streamTokenizer.nextToken();
                String isFlat = streamTokenizer.sval;
                if (Boolean.valueOf(isFlat)) {
                    //space = new Flat();
                    buildingFactory = new DwellingFactory();
                } else {
                    buildingFactory = new OfficeFactory();
                }
                streamTokenizer.nextToken();
                //space.setRoomCount((int) streamTokenizer.nval);
                int roomCount = (int) streamTokenizer.nval;
                streamTokenizer.nextToken();
                double area = streamTokenizer.nval;
                space = buildingFactory.createSpace(roomCount, area);
                currentFloor.setSpace(j, space);
            }
            floors[i] = currentFloor;
        }
        if (Boolean.valueOf(isDwelling)) {
            buildingFactory = new DwellingFactory();
            //building = new Dwelling(floors);
        } else {
            buildingFactory = new OfficeFactory();
            //building = new OfficeBuilding(floors);
        }
        building = buildingFactory.createBuilding(floors);
        return building;
    }

    private static Space newSpace(double area, int roomCount) {
        return buildingFactory.createSpace(roomCount, area);
    }

    private static Space newSpace(double area) {
        return buildingFactory.createSpace(area);
    }

    private static Floor createFloor(int spacesCount) {
        return buildingFactory.createFloor(spacesCount);
    }

    private static Floor createFloor(Space[] spaces) {
        return buildingFactory.createFloor(spaces);
    }

    private static Building createBuilding(int floorsCount, int[] spacesCounts) {
        return buildingFactory.createBuilding(floorsCount, spacesCounts);
    }

    private static Building createBuilding(Floor[] floors) {
        return buildingFactory.createBuilding(floors);
    }
}

