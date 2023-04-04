import java.util.Scanner;

public class CoffeeMachineText extends CoffeeMachineInterface {
    static Scanner scanner = new Scanner(System.in);

    CoffeeMachineText(int water, int milk, int beans, int cups, int cash) {
        super(water, milk, beans, cups, cash);
    }

    // printstate
    public void printState() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water\n", water);
        System.out.printf("%d ml of milk\n", milk);
        System.out.printf("%d g of coffee beans%n", beans);
        System.out.printf("%d disposable cups%n", cups);
        System.out.printf("$%d of money%n", cash);
    }

    // dobuycoff
    public void doBuyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String option = scanner.next();
        if (option.equals("back")) {
            // go back to main
        } else {
            int coffeeType = Integer.parseInt(option);
            Coffee coffee = Coffee.values()[coffeeType - 1];

            switch (coffee) {
                case ESPRESSO -> makeEspresso();
                case LATTE -> makeLatte();
                case CAPPUCCINO -> makeCappuccino();
                default -> {
                }
                // do nothing
            }
        }
    }

    // doFilling
    public void doFilling() {
        System.out.println("Write how many ml of water you want to add:");
        int addedWater = scanner.nextInt();
        water += addedWater;

        System.out.println("Write how many ml of milk you want to add:");
        int addedMilk = scanner.nextInt();
        milk += addedMilk;

        System.out.println("Write how many grams of coffee beans you want to add:");
        int addedBeans = scanner.nextInt();
        beans += addedBeans;

        System.out.println("Write how many disposable cups you want to add:");
        int addedCups = scanner.nextInt();
        cups += addedCups;
    }

    public void doTakeMoney() {
        System.out.println("I gave you $" + cash);
        cash = 0;
    }

    public boolean execute(String action) {
        boolean done = false;
        switch (action) {
            case "buy" -> doBuyCoffee();
            case "fill" -> doFilling();
            case "take" -> doTakeMoney();
            case "remaining" -> printState();
            case "exit" -> {
                done = true;
            }
            default -> {
            }
            // do nothing
        }
        return done;
    }
}
