public class CoffeeMachineInterface {
    enum Coffee {
        ESPRESSO, LATTE, CAPPUCCINO
    }

    static final int ESPRESSO_WATER_ML_PER_CUP = 250;
    static final int ESPRESSO_MILK_ML_PER_CUP = 0;
    static final int ESPRESSO_BEANS_G_PER_CUP = 16;
    static final int ESPRESSO_PRICE = 40;
    static final int LATTE_WATER_ML_PER_CUP = 350;
    static final int LATTE_MILK_ML_PER_CUP = 75;
    static final int LATTE_BEANS_G_PER_CUP = 20;
    static final int LATTE_PRICE = 45;
    static final int CAPPUCCINO_WATER_ML_PER_CUP = 200;
    static final int CAPPUCCINO_MILK_ML_PER_CUP = 100;
    static final int CAPPUCCINO_BEANS_G_PER_CUP = 12;
    static final int CAPPUCCINO_PRICE = 50;
    int water;
    int milk;
    int beans;
    int cups;
    int cash;
    int price;
    int moneyInserted;
    int changeMoney;

    CoffeeMachineInterface(int water, int milk, int beans, int cups, int cash) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.cash = cash;
    }

    CoffeeMachineInterface() {

    }

    boolean canMakeCoffee(int waterNeeded, int milkNeeded, int beansNeeded) {
        return water >= waterNeeded &&
                milk >= milkNeeded &&
                beans >= beansNeeded &&
                cups >= 1;
    }

    public boolean canMakeEsspresso(){
        return canMakeCoffee(ESPRESSO_WATER_ML_PER_CUP,
        ESPRESSO_MILK_ML_PER_CUP,
        ESPRESSO_BEANS_G_PER_CUP);
    }

    public boolean canMakeLatte(){
        return canMakeCoffee(LATTE_WATER_ML_PER_CUP,
        LATTE_MILK_ML_PER_CUP,
        LATTE_BEANS_G_PER_CUP);
    }

    public boolean canMakeCappucino(){
        return canMakeCoffee(CAPPUCCINO_WATER_ML_PER_CUP,
        CAPPUCCINO_MILK_ML_PER_CUP,
        CAPPUCCINO_BEANS_G_PER_CUP);
    }

    public void makeCoffee(int waterNeeded, int milkNeeded, int beansNeeded) {
        if (canMakeCoffee(waterNeeded, milkNeeded, beansNeeded)) {
            this.water -= waterNeeded;
            this.milk -= milkNeeded;
            this.beans -= beansNeeded;
            this.cups --;
        }
    }

    public void makeEspresso() {
        makeCoffee(ESPRESSO_WATER_ML_PER_CUP,
                ESPRESSO_MILK_ML_PER_CUP,
                ESPRESSO_BEANS_G_PER_CUP
                );
    }

    public void makeLatte() {
        makeCoffee(LATTE_WATER_ML_PER_CUP,
                LATTE_MILK_ML_PER_CUP,
                LATTE_BEANS_G_PER_CUP
                );
    }

    public void makeCappuccino() {
        makeCoffee(CAPPUCCINO_WATER_ML_PER_CUP,
                CAPPUCCINO_MILK_ML_PER_CUP,
                CAPPUCCINO_BEANS_G_PER_CUP
                );
    }

    public void doTakeMoney() {
        this.cash = 0;
    }

}
