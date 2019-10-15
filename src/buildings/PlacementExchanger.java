package buildings;

public class PlacementExchanger {
    public static boolean isValidSpaceReplace(Space first, Space second){
        if(first.getArea() == second.getArea() && first.getRoomCount() == second.getRoomCount()){
            return true;
        }
        return false;
    }

    public static boolean isValidFloorReplace(Floor first, Floor second) {
        if(first.getArea() == second.getArea() && first.getRoomCount() == second.getRoomCount()){
            return true;
        }
        return false;
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException{
        if(index1 >= floor1.getSpaceCount() || index2 >= floor2.getSpaceCount()){
            throw new IllegalArgumentException();
        }
        if(!isValidSpaceReplace(floor1.getSpace(index1), floor2.getSpace(index2))){
            throw new InexchangeableSpacesException();
        }
        Space t = floor1.getSpace(index1);
        floor1.setSpace(index1, floor2.getSpace(index2));
        floor2.setSpace(index2, t);
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException {
        if(index1 >= building1.getFloorCount() || index2 >= building2.getSpaceCount()){

        }
        if(!isValidFloorReplace(building1.getFloor(index1), building2.getFloor(index2))){
            throw new InexchangeableFloorsException();
        }
        Floor t = building1.getFloor(index1);
        building1.setFloor(index1, building2.getFloor(index2));
        building2.setFloor(index2, t);
    }

}
