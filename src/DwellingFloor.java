public class DwellingFloor {
    private Flat[] flats;

    public DwellingFloor(int flatCount) {
        flats = new Flat[flatCount];
        for (int i = 0; i < flatCount; i++) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Flat[] flats) {
        this.flats = flats;
    }

    public int getFlatCount() {
        return flats.length;
    }

    public double getArea() {
        double result = 0;
        for (int i = 0; i < flats.length; i++) {
            result += flats[i].getArea();
        }
        return result;
    }

    public int getRoomCount() {
        int result = 0;
        for (int i = 0; i < flats.length; i++) {
            result += flats[i].getRoomCount();
        }
        return result;
    }

    public Flat[] getFlats() {
        return flats;
    }

    public Flat getFlat(int num) {
        return flats[num];
    }

    public void setFlat(int num, Flat newFlat) {
        flats[num] = newFlat;
    }

    public void addFlat(int num, Flat newFlat) {
        Flat[] newFlats = new Flat[flats.length + 1];
        int i = 0;
        while (i < num) {
            newFlats[i] = flats[i];
            i++;
        }
        newFlats[i] = newFlat;
        i++;
        while (i < newFlats.length) {
            newFlats[i] = flats[i - 1];
            i++;
        }
        flats = newFlats;
    }

    public void deleteFlat(int num) {
        Flat[] newFlats = new Flat[flats.length - 1];
        int i = 0;
        while (i < num) {
            newFlats[i] = flats[i];
            i++;
        }
        i++;
        while (i < newFlats.length) {
            newFlats[i] = flats[i - 1];
            i++;
        }
        flats = newFlats;
    }

    public Flat getBestSpace() {
        double bestArea = 0;
        int bestFlatNum = 0;
        for (int i = 0; i < flats.length; i++) {
            if (flats[i].getArea() > bestArea) {
                bestArea = flats[i].getArea();
                bestFlatNum = i;
            }
        }
        return flats[bestFlatNum];
    }
}
