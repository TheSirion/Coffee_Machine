package machine;

import java.util.Scanner;

/**
 * while this is a single file application for the most part, this is a fairly bad naming convention.
 * Though it is clear and concise, that is not necessarily good practice as you expand outwards. A better design would
 * be to create a "machines" module and a "coffee" class, or a "coffee" module with a "machine" class.
 *
 * Here it is not a big issue, but practicing naming strategies helps teach organization that serves you better down the road.
 *
 * There is no rule that classes cannot be multiple words - and sometimes they have to be, but anytime I make a class
 * that is more than one word, I consider it a code smell that I need to stop and consider whether that's really the best
 * way to structure it or not.
 */
public class CoffeeMachine {
    /**
     * this is poor variable naming. It's pedantic for this situation, but again, perfect practice
     * makes perfect. A better name would be `scanner` or even sticking to a minimalist convention
     * with your private instance fields using underscores.
     *
     * @suggestion
     * private static Scanner _scanner = new Scanner(System.in);
     */
    private static Scanner s = new Scanner(System.in);

    /**
     * these are fine like this, but making so many static variables is indicative of
     * no structure. It's a code smell for "couldn't bother to make the models". Again, because
     * of the narrow scope of this exact project it is technically fine - but it wouldn't be a good
     * code review without pointing out areas for improvement.
     *
     * @suggestion
     * Create a `Coffee.Machine.Materials.Available` class with the properties
     * - Water
     * - Milk
     * - Beans
     * - Cups
     *
     * They would ideally be as protected as possible without being private. You always want to try and
     * keep things as far away from public scope as you can - and keep them narrow to the module they are
     * relavent in.
     */
    private static int waterAvailable = 400;
    private static int milkAvailable = 540;
    private static int beansAvailable = 120;
    private static int cupsAvailable = 9;

    /**
     * again, this looks like its tracking information about the output or input, but even in a program
     * this trivial we're getting to a point where the intent is no longer clear without further digging.
     * For proper program structure, model your data.
     *
     * @suggestion
     * define clearly what "money" means in this context; Looking ahead in the code, it appears to
     * be the result of input (purchases), so it should be modeled like that.
     *
     * `Coffee.Machine.Income` class with a property
     * - Total
     *
     * You could further abstract this out to another model, `Coffee.Machine.Transaction` with properties such
     * as ...
     * - Cost
     * - History <int, Date>
     *
     * In this situation, `Cost` indicates the price per unit, and `History` would be a Dictionary of
     * the past costs as they have changed over time.
     */
    private static int money = 550;

    /**
     * Once again, putting logic related to the program lifecycle in static variables will lead to
     * big problems further down the line in real applications. This should be part of the inner class that
     * is not static.
     *
     * @suggestion
     * Don't use static variables unless you **have** to.
     */
    private static boolean workLoop = true;

    public static void main(String[] args) {
        /**
         * This is just for keeping the program running, so it's fine. It's a little obtuse though.
         */
        while (workLoop) {
            /**
             * you call `System.out.println` over two dozen times in this file. This is an
             * indicator that it may be wise to create a simple internal method to make output
             * faster and centralized.
             */
            System.out.println("Write action (buy, fill, take, remaining, exit): ");

            /**
             * Because of the poor naming convention for the scanner, this code is completely
             * unintelliglbe without reading the entire file.
             */
            String option = s.next();

            /**
             * Now we're bringing in another class; Which is also using static fields. This is
             * another code smell and indicates the need to better abstract and model your data.
             *
             * @suggestion
             * Rule #1 of all programming is to Understand Your Data
             * I would suggest more work with pedantic models just for practice.
             */
            StateManager.manageInput(option);
        }
    }

    /**
     * The number of times we're calling `System.out.println` is getting ridiculous now. A helper
     * method to keep the calls centralized and apply any standard formatting you want would make this
     * all more legible and intelligent. It would also provide a convenient place for debugging breakpoints.
     */
    static void showResources() {
        /**
         * Learn Java Template Strings
         */
        System.out.println("The coffee machine has: ");
        /**
         * Learn Java Template Strings
         */
        System.out.println(waterAvailable + " of water");
        /**
         * Learn Java Template Strings
         */
        System.out.println(milkAvailable + " of milk");
        /**
         * Learn Java Template Strings
         */
        System.out.println(beansAvailable + " of coffee beans");
        /**
         * Learn Java Template Strings
         */
        System.out.println(cupsAvailable + " of disposable cups");
        /**
         * Seriously template strings are like getting buttsex with Elizabeth Hurley. Learn them.
         */
        System.out.println(money + " of money");
    }

    static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        /**
         * Again the unclear variable rears its head. Another point of possible error because of confusion if
         * this were to grow to any significant complexity.
         */
        String order = s.next();

        /**
         * What's the purpose of this switch? It's just to mete the input and provide output
         * like a state machine. This is technically an appropriate use of a switch, but the way
         * it's being employed here would quickly become out of control - especially using strings as the
         * inputs.
         *
         * @suggestion
         * The way you are outputting data should be examined and encapsulated. You would be wise to
         * create models to contain output messages, and finally this is a place where static fields would be appropriate. You
         * would want to store things like common messages that are technically resources in static instance fields in some
         * kind of class or model that is barebones and has no functionality other than to hold those values.
         *
         * This would make it easy to run them against validation and formatting; (Back to the template strings!)
         */
        switch (order) {
            /**
             * Strings are bad. Always. Encapsulate your input into its own method that performs error checking
             * on the strings. You can crash this whole thing with a keystroke here.
             */
            case "1":
                if (waterAvailable >= 250 && beansAvailable >= 16 && cupsAvailable > 1) {
                    waterAvailable -= 250;
                    beansAvailable -= 16;
                    money += 4;
                    cupsAvailable -= 1;
                }
                System.out.println("I have enough resources, making you a coffee!");
                break;
            /**
             * Strings have not become a better option since line 167.
             */
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
            /**
             * Starting to see a pattern here?
             */
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
            /**
             * No user will ever type this correctly. Assume your users are made of cheese and milkshakes, and appropriately
             * design all of your inputs to accomodate that level of intellect minus 300.
             */
            case "back":
                break;
            default:
                /**
                 * This isn't a helpful error message to anyone.
                 */
                System.out.println("Unknown request");
                break;
        }

        /**
         * You just made a huge switch, so it seems even more awkward to have
         * a series of state machines with 'if' statements. This is another code
         * smell about how the logic could be better encapsulated.
         */
        if (waterAvailable <= 0) {
            waterAvailable = 0;
            System.out.println("Sorry, not enough water");
        }

        /**
         * This is a fall-through conditional and will produce unintended results.
         * @suggestion
         * Use 'else' where appropriate.
         */
        if (milkAvailable <= 0) {
            System.out.println("Sorry, not enough milk");
            milkAvailable = 0;
        }

        /**
         * Now your machine is just being a jerk. If they go through all this and don't
         * get coffee, you've got a big problem on your hands. The computer will explode.
         * @suggestion
         * Don't buy your coffee machines from the company that makes McDonald's Ice Cream Machines.
         */
        if (beansAvailable <= 0) {
            System.out.println("Sorry, not enough coffee beans");
            beansAvailable = 0;
        }
    }

    /**
     * So much `System.out.print` and `println` ...
     */
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

        /**
         * It is unclear why you would cycle back to this after using the `fill()` method.
         */
        showResources();
    }

    /**
     * More improper use of static methods and variables that could be wrapped properly
     * in models and internalized more safely. If this were dealing with real money, the
     * security of the application would score a 0.
     */
    static void take() {
        System.out.println("I gave you " + money);
        money = 0;

        showResources();
    }

    /**
     * All these static flow control methods indicate that you need something else to
     * manage the actual process of program entry/exit.
     */
    static void exit() {
        workLoop = false;
    }
}

