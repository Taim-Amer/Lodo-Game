import java.util.Objects;
import java.util.Random;

public class Roll {
    private int steps;
    private boolean again;

    public Roll(int steps, boolean again) {
        this.steps = steps;
        this.again = again;
    }

    public Roll() {
        Roll roll = rollDice();
        this.steps = roll.steps;
        this.again = roll.again;
    }

    // Getters
    public boolean isAgain() {
        return again;
    }

    public int getSteps() {
        return steps;
    }

    public Roll rollDice() {
        Random random = new Random();
        int result = random.nextInt(6) + 1;
        return new Roll(result, result == 6);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Roll roll = (Roll) obj;
        return steps == roll.steps && again == roll.again;
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps, again);
    }
}
