
import java.util.Random;

public class RoomLinkedList {

    Rooms head;
    Rooms tail;
    int numRooms;

    public RoomLinkedList(int numRooms) {
        this.head = null;
        this.tail = null;
        this.numRooms = numRooms;
    }

    public void addRooms(int roomType) {
        Rooms newRoom;

        if (roomType == 1) {
            newRoom = new RoomTraps();
        } else if (roomType == 2) {
            newRoom = new BattlingRoom("Fighting", null);
        } else {
            newRoom = new Items();
        }

        if (head == null) {
            head = newRoom;
            tail = newRoom;
            newRoom.nextRoom = head;

        } else {
            tail.nextRoom = newRoom;
            tail = newRoom;
            tail.nextRoom = head;  
        }
    }

    public void randomizeRooms() {
        Random ran = new Random();
        for (int i = 0; i < numRooms; i++) {
            int roomType;
            while ((roomType = ran.nextInt(3) + 1) == 3) {
            }
            addRooms(roomType);
        }
    }
    
    public void showRoom(Hero hero, boolean moveFoward) {
        Rooms currentRoom = head;

        while (currentRoom != null) {
            if (currentRoom instanceof Items) {
                ((Items) currentRoom).enterRoom(hero);
            } else if (currentRoom instanceof RoomTraps) {
                ((RoomTraps) currentRoom).enterRoom(hero);
            } else if (currentRoom instanceof BattlingRoom) {
                ((BattlingRoom) currentRoom).enterRoom(hero);
            }
            if (moveFoward) {
                currentRoom = currentRoom.nextRoom;
            } else {
                break;

            }
        }
    }
    
    public void moveFoward(Hero hero){
    if(head != null){
    head = head.nextRoom;
    if(head != null){
    head.enterRoom(hero);
    }
    }
    }

    public void dispayRooms(Hero hero) {
        showRoom(hero, false);
    }

    void moveForward(Hero hero) {
     if(head != null){
    head = head.nextRoom;
    if(head != null){
    head.enterRoom(hero);
    }
    }
    }
}