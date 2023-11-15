
import java.util.Random;

public class RoomLinkedList {

    Rooms head;
    Rooms tail;
    int numRooms;

    private Rooms[][] map;

    public void genRooms() {
        map = new Rooms[3][50];
        Rooms currentRoom = head;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length && currentRoom != null; j++) {
                map[i][j] = currentRoom;
                currentRoom = currentRoom.nextRoom;
            }
        }
    }
    
        public void removeRoom(Rooms roomToRemove) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == roomToRemove) {
                    map[i][j] = null;
                    return;
                }
            }
        }
    }


public void displayMap(Hero hero) {
        if (map == null) {
            System.out.println("Map not generated.");
            return;
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == null) {
                    System.out.print("[]");
                } else {
                    String roomType = getRoomType(map[i][j]);
                    if (map[i][j] == hero.getCurrentRoom()) {
                        System.out.print("-[HERO]-");
     //                   removeRoom(map[i][j]);
                    } else {
                        System.out.print(" " + roomType + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    private String getRoomType(Rooms room) {
        if (room instanceof RoomTraps) {
            return "T";
        } else if (room instanceof BattlingRoom) {
            return "B";
        } else if (room instanceof Items) {
            return "I";
        } else {
            return "E"; 
        }
    }

    public RoomLinkedList(int numRooms) {
        this.head = null;
        this.tail = null;
        this.numRooms = numRooms;
        this.map = new Rooms[5][numRooms / 10];
        genRooms();
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
}