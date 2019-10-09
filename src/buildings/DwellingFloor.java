package buildings;

public class DwellingFloor implements Floor{
    private Space[] spaces;

    public DwellingFloor(int flatCount) {
        spaces = new Flat[flatCount];
        for (int i = 0; i < flatCount; i++) {
            spaces[i] = new Flat();
        }
    }

    public DwellingFloor(Flat[] spaces) {
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

    public Space getFlat(int num) {
        return spaces[num];
    }

    public void setFlat(int num, Flat newFlat) {
        spaces[num] = newFlat;
    }

    public void addFlat(int num, Space newFlat) {
        Space[] newFlats = new Space[spaces.length + 1];
        int i = 0;
        while (i < num) {
            newFlats[i] = spaces[i];
            i++;
        }
        newFlats[i] = newFlat;
        i++;
        while (i < newFlats.length) {
            newFlats[i] = spaces[i - 1];
            i++;
        }
        spaces = newFlats;
    }

    public void deleteFlat(int num) {
        Space[] newFlats = new Space[spaces.length - 1];
        int i = 0;
        while (i < num) {
            newFlats[i] = spaces[i];
            i++;
        }
        i++;
        while (i < newFlats.length) {
            newFlats[i] = spaces[i - 1];
            i++;
        }
        spaces = newFlats;
    }

    public Space getBestSpace() {
        if(spaces.length>0) {
            double bestArea = 0;
            int bestFlatNum = 0;
            for (int i = 0; i < spaces.length; i++) {
                if (spaces[i].getArea() > bestArea) {
                    bestArea = spaces[i].getArea();
                    bestFlatNum = i;
                }
            }
            return spaces[bestFlatNum];
        }
        else return null;
    }
}
