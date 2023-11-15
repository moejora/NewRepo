import java.util.Random;

public class RoomLinkedList {

    Rooms head;
    Rooms tail;
    int numRooms;

    private Rooms[][] map;

    public RoomLinkedList(int numRooms) {
        this.head = null;
        this.tail = null;
        this.numRooms = numRooms;
        this.map = new Rooms[5][numRooms / 10];
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

    
    public void moveFoward(Hero hero) {
        if (head != null) {
            head = head.nextRoom;
            if (head != null) {
                head.enterRoom(hero);
            }
        }
    }

    public void dispayRooms(Hero hero) {
        showRoom(hero, false);
    }

    void moveForward(Hero hero) {
        if (head != null) {
            head = head.nextRoom;
            if (head != null) {
                head.enterRoom(hero);
            }
        }
    }
    public Rooms getCurrentRoom(){
    return head;
    }
    
public Rooms[][] getMap() {
        return map;
    }

   public int getCurrentRoomRow() {
    if (head != null) {
        int rowIndex = 0;
        Rooms currentRoom = head;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == currentRoom) {
                    return rowIndex;
                }
            }
            rowIndex++;
        }
    }

    return 0;
}

public int getCurrentRoomColumn() {
    if (head != null) {
        Rooms currentRoom = head;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == currentRoom) {
                    return j;
                }
            }
        }
    }

    return 0;
}
public void genRooms() {
    map = new Rooms[5][numRooms / 10];
    Rooms currentRoom = head;

    for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length && currentRoom != null; j++) {
            map[i][j] = currentRoom;
            currentRoom = currentRoom.nextRoom;
        }
    }
}
}