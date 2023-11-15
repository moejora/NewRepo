public class Map {
    private String[][] displayMap;
    private Rooms[][] roomsMap;
    private int heroRow;
    private int heroColumn;

    public Map(int numRows, int numCols) {
        displayMap = new String[numRows][numCols];
        roomsMap = new Rooms[numRows][numCols];
        heroRow = -1; // Set to an invalid value initially
        heroColumn = -1;
    }

public void updateMap(Rooms[][] rooms, Rooms currentRoom) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] != null) {
                    if (rooms[i][j] == currentRoom) {
                        displayMap[i][j] = "-[HERO]-";
                    } else {
                        displayMap[i][j] = getRoomType(rooms[i][j]);
                    }
                }
            }
        }
    }

    public void displayMap() {
        // Display the map
        for (String[] row : displayMap) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private String getRoomType(Rooms room) {
        // Implement your logic to determine the room type as needed
        // For now, let's just return "E" for all types
        return "E";
    }
}
