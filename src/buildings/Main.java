package buildings;

public class Main {
    public static void main(String[] args) {
        Dwelling build = new Dwelling(9, new int[]{3, 1, 3, 4, 3, 2, 4, 2, 4});
        DwellingFloor floor = build.getFloor(3);
        //System.out.println(floor.getFlatCount());
        System.out.println(build.getFlat(14).getArea());
    }
}
