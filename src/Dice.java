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
        Dice roll = new
        Random random = new Random();
        steps = random.nextInt(6) + 1;
        return
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
