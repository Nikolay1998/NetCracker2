package buildings;

import java.io.*;

public class Buildings {
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
            if (isDwellingFloor) currentFloor = new DwellingFloor(spaceCount);
            else currentFloor = new OfficeFloor(spaceCount);
            for (int j = 0; j < spaceCount; j++) {
                Space space;
                boolean isFlat = din.readBoolean();
                if (isFlat) space = new Flat();
                else space = new Office();
                space.setRoomCount(din.readInt());
                space.setArea(din.readDouble());
                currentFloor.setSpace(j, space);
            }
            floors[i] = currentFloor;
        }
        if (isDwelling) {
            building = new Dwelling(floors);
        } else {
            building = new OfficeBuilding(floors);
        }
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

    public static Building readBuilding(Reader in) throws IOException{
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
            if (Boolean.valueOf(isDwellingFloor)) currentFloor = new DwellingFloor(spaceCount);
            else currentFloor = new OfficeFloor(spaceCount);
            for (int j = 0; j < spaceCount; j++) {
                Space space;
                streamTokenizer.nextToken();
                String isFlat = streamTokenizer.sval;
                if (Boolean.valueOf(isFlat)) space = new Flat();
                else space = new Office();
                streamTokenizer.nextToken();
                space.setRoomCount((int) streamTokenizer.nval);
                streamTokenizer.nextToken();
                space.setArea(streamTokenizer.nval);
                currentFloor.setSpace(j, space);
            }
            floors[i] = currentFloor;

        }
        if (Boolean.valueOf(isDwelling)) {
            building = new Dwelling(floors);
        } else {
            building = new OfficeBuilding(floors);
        }
        return building;
    }
}

