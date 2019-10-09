package buildings;

public class Main {
    public static void main(String[] args) {
        Building building = new Dwelling(4, new int[] {3,5,6,3});
        building.addSpace(3, new Office());
    }
}
