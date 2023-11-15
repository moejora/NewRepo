import java.util.Random;
import java.util.Scanner;

public class BattlingRoom extends Rooms {

    private int battleCount = 0;

    public BattlingRoom(String roomType, Rooms nextRoom) {
        super(roomType, nextRoom);
    }

    @Override
    public void enterRoom(Hero hero) {
        battleCount++;
        System.out.println("Welcome to a battle!");

        if (battleCount % 5 == 0) {
            Boss boss = createBoss();
            fight(hero, boss);
        } else {
            Monster monster = createMonster();
            fight(hero, monster);
        }
    }

    private Monster createMonster() {
        Random ran = new Random();
        int monsterHealth = ran.nextInt(50) + 1;
        int monsterAttack = ran.nextInt(10) + 1;
        int monsterSpeed = ran.nextInt(3) + 1;
        int monsterLevel = ran.nextInt(5) + 1;

        System.out.println("Monster Health: " + monsterHealth);
        System.out.println("Monster Attack: " + monsterAttack);
        System.out.println("Monster Speed: " + monsterSpeed);
        System.out.println("Monster Level: " + monsterLevel);

        Monster monster = new Monster(monsterHealth, monsterAttack, 5, monsterSpeed, 0, monsterLevel, 0);

        return monster;
    }

    private Boss createBoss() {
        int monsterHealth = 100;
        int monsterAttack = 10;
        int monsterSpeed = 4;

        return new Boss(monsterHealth, monsterAttack, 5, monsterSpeed, 0, 5, 0);
    }

    public void fight(Hero hero, Monster monster) {
        System.out.println("Lets Dual Monster!");

        System.out.println("Welcome to a battle to the death!");

        while (hero.getLife() > 0 && monster.getLife() > 0) {

            System.out.println(hero.strike(monster));
            System.out.println("Monster's life: " + monster.getLife());

            if (monster.getLife() <= 0) {
                System.out.println("You have defeated the monster!");
                hero.gainXP(1);
                System.out.println("Heres your Xp:" + hero.getXp());
                sendToNextRoom(hero);
                break;
            }

            // Monster attacks hero
            System.out.println(monster.slice(hero));
            System.out.println("Hero's life: " + hero.getLife());

            if (hero.getLife() <= 0) {
                System.out.println("Game Over");
                System.exit(0);
            }
        }
        sendToNextRoom(hero);
    }

    public void fight(Hero hero, Boss boss) {
        while (hero.getLife() > 0 && boss.getLife() > 0) {
            System.out.println(hero.strike(boss));
            System.out.println("Hero's life: " + hero.getLife());
            System.out.println(boss.slice(hero));
            System.out.println("The Boss has " + boss.getLife() + " life left");

            if (boss.getLife() <= 0) {
                System.out.println("You have defeated the boss!");
                hero.incrementBattleCount();
                hero.gainXP(10);
                sendToNextRoom(hero);
                return;
            }

            if (hero.getLife() <= 0) {
                System.out.println("Game Over");
                System.exit(0);
            }
        }
    }

    public void sendToNextRoom(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your path: 'L' to move left, 'R' to move right, 'Q' to quit");
        String userInput = scanner.nextLine().trim();

        RoomLinkedList roomList = new RoomLinkedList(0); 

               if ("L".equalsIgnoreCase(userInput) || "R".equalsIgnoreCase(userInput)) {
                    roomList.moveForward(hero);
                } else if ("M".equalsIgnoreCase(userInput)) {
                   // roomList.displayMap(hero);
                } else if ("Q".equalsIgnoreCase(userInput)) {
                    System.out.println("Quitting the game.");
                      System.exit(0);
                } else {
                    System.out.println("Invalid input. Please choose 'L', 'R', 'M', or 'Q'.");
                }
            }
}
