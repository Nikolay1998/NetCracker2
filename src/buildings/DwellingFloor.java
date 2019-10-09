package buildings;

public class DwellingFloor implements Floor{
    private Space[] spaces;

    public DwellingFloor(int flatCount) {
        spaces = new Flat[flatCount];
        for (int i = 0; i < flatCount; i++) {
            spaces[i] = new Flat();
        }
    }

    public DwellingFloor(Space[] spaces) {
        this.spaces = spaces;
    }

    public int getSpaceCount() {
        return spaces.length;
    }

    public double getArea() {
        double result = 0;
        for (int i = 0; i < spaces.length; i++) {
            result += spaces[i].getArea();
        }
        return result;
    }

    public int getRoomCount() {
        int result = 0;
        for (int i = 0; i < spaces.length; i++) {
            result += spaces[i].getRoomCount();
        }
        return result;
    }

    public Space[] getSpaces() {
        return spaces;
    }

    public Space getSpace(int num) {
        return spaces[num];
    }

    public void setSpace(int num, Space newSpace) {
        spaces[num] = newSpace;
    }

    public void addSpace(int num, Space newSpace) {
        Space[] newSpaces = new Space[spaces.length + 1];
        int i = 0;
        while (i < num) {
            newSpaces[i] = spaces[i];
            i++;
        }
        newSpaces[i] = newSpace;
        i++;
        while (i < newSpaces.length) {
            newSpaces[i] = spaces[i - 1];
            i++;
        }
        spaces = newSpaces;
    }

    public void deleteSpace(int num) {
        Space[] newSpaces = new Space[spaces.length - 1];
        int i = 0;
        while (i < num) {
            newSpaces[i] = spaces[i];
            i++;
        }
        i++;
        while (i < newSpaces.length) {
            newSpaces[i] = spaces[i - 1];
            i++;
        }
        spaces = newSpaces;
    }

    public Space getBestSpace() {
        if(spaces.length>0) {
            double bestArea = 0;
            int bestSpaceNum = 0;
            for (int i = 0; i < spaces.length; i++) {
                if (spaces[i].getArea() > bestArea) {
                    bestArea = spaces[i].getArea();
                    bestSpaceNum = i;
                }
            }
            return spaces[bestSpaceNum];
        }
        else return null;
    }
}
