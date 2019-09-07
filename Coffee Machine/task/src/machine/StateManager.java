package machine;

import static machine.CoffeeMachine.*;

class StateManager {
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
