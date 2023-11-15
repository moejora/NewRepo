
import java.util.Random;
import java.util.Scanner;

public class Items extends Rooms {

    public Items(String roomType, Rooms nextRoom) {
        super(roomType, nextRoom);
    }

    public Items() {
        super("Items", null);
    }

        @Override
    public void enterRoom(Hero hero) {
        System.out.println("welcome to the items room");
        getItem(hero);
    }

    public void getItem(Hero hero) {
        Random n = new Random();
        int num = n.nextInt(3);
        if (num == 1) {
            System.out.println("You just discovered Bandages!");
            hero.gainHealth(10);
            System.out.println("You wrap yourself up and gain 10 health");
        } else if (num == 2) {
            System.out.println("Look over there a Needle!");
            System.out.println("You pluck yourself trying to retrieve it and gain an adrenaline rush");
            hero.speedBoost(5);
        } else {
            System.out.println("You found a sharpening stone to polish up your sword");
            hero.attackBoost(10);
        }
        displayStats(hero);
        sendToNextRoom(hero);
    }

    public void displayStats(Hero hero) {
        System.out.println("Your current stats:");
        System.out.println("Health: " + hero.getLife());
        System.out.println("Attack: " + hero.getAttack());
        System.out.println("Speed: " + hero.getSpeed());
    }

    public void askToMoveOn(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to move on? (y/n)");
        String choice = scanner.next().toLowerCase();

        if (choice.equals("n")) {
            System.out.println("You decide to stay and collect more items.");
            enterRoom(hero); 
        } else {
            System.out.println("You decide to move on to the next room.");
        }
        sendToNextRoom(hero);
    }

    public void sendToNextRoom(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Choose your path: 'L' to move left, 'R' to move right, 'Q' to quit");
        String userInput = scanner.nextLine().trim();

        RoomLinkedList roomList = new RoomLinkedList(0);

               if ("L".equalsIgnoreCase(userInput) || "R".equalsIgnoreCase(userInput)) {
                    roomList.moveForward(hero);
                } else if ("M".equalsIgnoreCase(userInput)) {
                    //roomList.displayMap(hero);
                } else if ("Q".equalsIgnoreCase(userInput)) {
                    System.out.println("Quitting the game.");
                      System.exit(0);
                } else {
                    System.out.println("Invalid input. Please choose 'L', 'R', 'M', or 'Q'.");
                }
            }
}
