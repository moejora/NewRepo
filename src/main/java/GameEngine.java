import java.util.Random;
import java.util.Scanner;

public class GameEngine {

    public void startGame() {
        int numRooms = 50; // Change this to the desired number of rooms
        RoomLinkedList roomList = new RoomLinkedList(numRooms);
        roomList.randomizeRooms();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to your demise!");
        System.out.println("Type 'y' to continue or 'n' to back away");

        String userInput = scanner.nextLine().trim();

        if (userInput.equalsIgnoreCase("y")) {
            Hero hero = new Hero(100, 10, 5, 20, 30, 1, 0);

            boolean continueGame = true;
            while (continueGame) {
                System.out.println("Choose your path: 'L' to move left, 'R' to move right, 'Q' to quit");
                userInput = scanner.nextLine().trim();

                if ("L".equalsIgnoreCase(userInput)) {
                    roomList.moveForward(hero);
                } else if ("R".equalsIgnoreCase(userInput)) {
                    roomList.moveForward(hero);
                } else if ("Q".equalsIgnoreCase(userInput)) {
                    System.out.println("Quitting the game.");
                    continueGame = false;
                } else {
                    System.out.println("Invalid input. Please choose 'L', 'R', or 'Q'.");
                }
            }
        } else {
            System.out.println("Goodbye! You have exited the game.");
        }
    }

    public static void main(String[] args) {
        GameEngine game = new GameEngine();
        game.startGame();
    }
}