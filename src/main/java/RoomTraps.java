
import java.util.Random;
import java.util.Scanner;

public class RoomTraps extends Rooms {
    
private Hero hero;

    public RoomTraps(String roomType, Rooms nextRoom) {
        super(roomType, nextRoom);
    }
    
    public RoomTraps() {
        super("Traps", null);
    }

    @Override
    public void enterRoom(Hero hero) {
        System.out.println("Haha You fell for the trap!");
        roomTrap(hero);
    }

    public void roomTrap(Hero hero) {
        Random n = new Random();
        int num = n.nextInt(2);

        if (num == 1) {
            System.out.println("Ouch! you stepped barbed wire!");
            hero.speedBoost(-3);
        } else {
            System.out.println("Whooosh!");
            System.out.println("You took a heavy blow from a rush of water!");
            hero.gainHealth(-5);
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

        if ("L".equalsIgnoreCase(userInput)) {
            roomList.moveForward(hero);
        } else if ("R".equalsIgnoreCase(userInput)) {
            
        } else if ("Q".equalsIgnoreCase(userInput)) {
            System.out.println("Quitting the game.");
        } else {
            System.out.println("Invalid input. Please choose 'L', 'R', or 'Q'.");
        }
    }
}
