import java.util.List;

public class ExpectiMinMax {

    private static int evaluateState(State state) {
        int val = 0;

        val += evaluatePlayerPieces(state.Players.get(1).getPieces(), true);
        val += evaluatePlayerPieces(state.Players.get(0).getPieces(), false);

        state.setValue(val);
        return val;
    }

    private static int evaluatePlayerPieces(List<Piece> pieces, boolean max) {
        int val = 0;

        for (Piece item : pieces) {
            if (item == null) {
                System.out.println("Found null piece, skipping evaluation.");
                continue;
            }

            System.out.println("Evaluating piece: " + item);

            if (item.isOnBoard() && item.square.isSafe()) {
                val += 2;
            }

            if (!item.isOnBoard()) {
                val += 5;
            }

            if (max && item.isWin()) {
                val += 10;
            }
        }

        System.out.println("Total value for " + (max ? "Max" : "Min") + " player: " + val);
        return val;
    }

    private static void printDepthValue(int depth, int value, String type) {
        System.out.println("Depth: " + depth + ", " + type + " Value: " + value);
    }

    public static State expectiminimax(State state, int depth, boolean isMaxPlayer, Roll roll) {
        if (depth == 0 || state.Final()) {
            printDepthValue(depth, evaluateState(state), "Leaf");
            return state;
        }

        if (isMaxPlayer) {
            return maxPlayerMove(state, depth, roll);
        } else {
            return minPlayerMove(state, depth, roll);
        }
    }

    private static State maxPlayerMove(State state, int depth, Roll roll) {
        State bestState = state.Copy();
        int bestValue = Integer.MIN_VALUE;

        List<State> children = Logic.nextStates("#", state, roll);
        if (children == null || children.isEmpty()) {
            System.out.println("No children states for Max player at depth " + depth);
            printDepthValue(depth, evaluateState(state), "Max");
            return state;
        }

        for (State child : children) {
            if (child == null) continue;
            State evaluatedChild = expectiminimax(child, depth - 1, false, roll);
            int value = evaluateState(evaluatedChild);

            if (value > bestValue) {
                bestValue = value;
                bestState = evaluatedChild;
            }
        }

        state.setValue(bestValue);
        printDepthValue(depth, bestValue, "Max");
        return bestState;
    }

    private static State minPlayerMove(State state, int depth, Roll roll) {
        int totalValue = 0;

        List<State> children = Logic.nextStates("@", state, roll);
        if (children == null || children.isEmpty()) {
            System.out.println("No children states for Min player at depth " + depth);
            printDepthValue(depth, evaluateState(state), "Min");
            return state;
        }

        for (State child : children) {
            if (child == null) continue;
            State evaluatedChild = expectiminimax(child, depth - 1, true, roll);
            int value = evaluatedChild.getValue();
            totalValue += value;
        }

        int averageValue = totalValue / children.size();
        state.setValue(averageValue);
        printDepthValue(depth, averageValue, "Min");
        return state;
    }
}
