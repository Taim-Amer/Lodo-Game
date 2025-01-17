import java.util.Objects;
import java.util.Random;

public class Dice {
    int steps;
    boolean again;

    public Dice(int steps  , boolean again){
        this.steps = steps;
        this.again = again;
    }
    public Dice (){
        Dice roll = rollDice();
        this.steps = roll.steps;
        this.again = roll.again;
    }
    public boolean isAgain() {
        return again;
    }
    public int getSteps() {
        return steps;
    }
    public Dice rollDice() {
        Dice roll = null;
        Random random = new Random();
        int chance = random.nextInt(6) + 1;
        roll = switch (chance) {
            case 1 -> Item.ONE;
            case 2 -> Item.TOW;
            case 3 -> Item.THREE;
            case 4 -> Item.FOUR;
            case 5 -> Item.FIVE;
            case 6 -> Item.SIX;
            default -> roll;
        };
        return roll;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dice roll = (Dice) obj;
        return steps == roll.steps && again == roll.again;
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps, again);
    }
}
