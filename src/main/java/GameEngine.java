
import java.util.Scanner;

public class GameEngine {

    public void startGame() {
        int numRooms = 50;
        RoomLinkedList roomList = new RoomLinkedList(numRooms);
        roomList.randomizeRooms();
        roomList.genRooms();
        Map gameMap = new Map(5, 10);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to your demise!");
        System.out.println("Type 'y' to continue or 'n' to back away");

        String userInput = scanner.nextLine().trim();

  if (userInput.equalsIgnoreCase("y")) {
            Hero hero = new Hero(100, 10, 5, 20, 30, 1, 0);

            boolean continueGame = true;
            while (continueGame) {
                Rooms currentRoom = roomList.getCurrentRoom();
                gameMap.updateMap(roomList.getMap(), currentRoom);

                gameMap.displayMap();

                //roomList.displayMap(hero);
                System.out.println("Choose your path: 'L' to move left, 'R' to move right, 'Q' to quit");
                userInput = scanner.nextLine().trim();
                if ("L".equalsIgnoreCase(userInput) || "R".equalsIgnoreCase(userInput)) {
                    roomList.moveForward(hero);
                } else if ("M".equalsIgnoreCase(userInput)) {
                    //   roomList.displayMap(hero);
                } else if ("Q".equalsIgnoreCase(userInput)) {
                    System.out.println("Quitting the game.");
                    continueGame = false;
                } else {
                    System.out.println("Invalid input. Please choose 'L', 'R', 'M', or 'Q'.");
                }
            }
        } else {
            System.out.println("Goodbye! You have exited the game.");
        }
    }
}
