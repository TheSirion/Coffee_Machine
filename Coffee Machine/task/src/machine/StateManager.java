package machine;

import static machine.CoffeeMachine.*;

class StateManager {
    /**
     * This is a very bad idea. This isn't a good use of static methods, as it has what is known
     * as "side effects". Static methods should be "pure" - meaning they always produce the exact
     * same result given the same input.
     *
     * This is just using a static method in place of proper modeling or feature classes because it's
     * convenient.
     */
    static void manageInput(String input) {
        switch (input) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                showResources();
                break;
            case "exit":
                exit();
                break;
            default:
                System.out.println("Invalid input: " + input);
        }
    }
}
