package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private static Scanner s = new Scanner(System.in);
    private static int waterAvailable = 400;
    private static int milkAvailable = 540;
    private static int beansAvailable = 120;
    private static int cupsAvailable = 9;
    private static int money = 550;
    private static boolean workLoop = true;

    public static void main(String[] args) {

        while (workLoop) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String option = s.next();

            StateManager.manageInput(option);
        }
    }

    static void showResources() {
        System.out.println("The coffee machine has: ");
        System.out.println(waterAvailable + " of water");
        System.out.println(milkAvailable + " of milk");
        System.out.println(beansAvailable + " of coffee beans");
        System.out.println(cupsAvailable + " of disposable cups");
        System.out.println(money + " of money");
    }

    static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");

        String order = s.next();

        switch (order) {
            case "1":
                if (waterAvailable >= 250 && beansAvailable >= 16 && cupsAvailable > 1) {
                    waterAvailable -= 250;
                    beansAvailable -= 16;
                    money += 4;
                    cupsAvailable -= 1;
                }
                System.out.println("I have enough resources, making you a coffee!");
                break;
            case "2":
                if (waterAvailable >= 350 && milkAvailable >= 75 && beansAvailable >= 20 && cupsAvailable >= 1) {
                    waterAvailable -= 350;
                    milkAvailable -= 75;
                    beansAvailable -= 20;
                    money += 7;
                    cupsAvailable -= 1;
                }
                System.out.println("I have enough resources, making you a coffee!");
                break;
            case "3":
                if (waterAvailable >= 200 && milkAvailable >= 100 && beansAvailable >= 12 && cupsAvailable >= 1) {
                    waterAvailable -= 200;
                    milkAvailable -= 100;
                    beansAvailable -= 12;
                    money += 6;
                    cupsAvailable -= 1;
                }
                System.out.println("I have enough resources, making you a coffee!");
                break;
            case "back":
                break;
            default:
                System.out.println("Unknown request");
                break;
        }

        if (waterAvailable <= 0) {
            waterAvailable = 0;
            System.out.println("Sorry, not enough water");
        }

        if (milkAvailable <= 0) {
            System.out.println("Sorry, not enough milk");
            milkAvailable = 0;
        }

        if (beansAvailable <= 0) {
            System.out.println("Sorry, not enough coffee beans");
            beansAvailable = 0;
        }
    }

    static void fill() {
        System.out.print("Write how many ml of water do you want to add: ");
        int water = s.nextInt();
        waterAvailable += water;
        System.out.print("Write how many ml of milk do you want to add: ");
        int milk = s.nextInt();
        milkAvailable += milk;
        System.out.print("Write how many grams of coffee beans do you want to add: ");
        int beans = s.nextInt();
        beansAvailable += beans;
        System.out.print("Write how many disposable cups of coffee do you want to add: ");
        int cups = s.nextInt();
        cupsAvailable += cups;

        showResources();
    }

    static void take() {
        System.out.println("I gave you " + money);
        money = 0;

        showResources();
    }

    static void exit() {
        workLoop = false;
    }
}

