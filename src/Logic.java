import java.util.ArrayList;
import java.util.Scanner;

public class Logic {

    public static void win(Piece piece, Player player) {
        piece.setWin(true);
        piece.setOnBoard(false);
        piece.square = new Square(57);
        player.pieces.remove(piece);
        player.isWin.add(piece);
        System.out.println("Piece reached home for player " + player.who);
    }

    public static void move(int moves, Piece piece, State state, Player player) {
        if (!check(piece, moves, state, player)) return;

        int oldIndex = piece.square.getIndex();
        int targetIndex = oldIndex + moves;

        if (targetIndex >= 57) {
            System.out.println("Move out of bounds.");
            return;
        }

        if (targetIndex == 56) {
            win(piece, player);
        } else if (!player.indexes[targetIndex].special && !player.indexes[targetIndex].isSafe()) {
            kill(targetIndex, state.Players.get(1 - state.Players.indexOf(player)));
        }

        player.indexes[oldIndex].clearOne();
        player.indexes[targetIndex].addOne(player.who);
        piece.setIndex(targetIndex);

        state.refreshBoard();
        System.out.println("Piece moved to index " + targetIndex);
    }

    public static void addPiece(Player player) {
        for (Piece piece : player.pieces) {
            if (!piece.isOnBoard() && !piece.isWin()) {
                piece.square = new Square(0);
                piece.setOnBoard(true);
                player.piecesOut--;
                player.indexes[0].addOne(player.who);
                System.out.println("New piece added to the board for player " + player.who);
                return;
            }
        }
        System.out.println("No pieces available to add for player " + player.who);
    }

    public static void kill(int index, Player opponent) {
        for (Piece piece : opponent.pieces) {
            if (piece.isOnBoard() && piece.square.getIndex() == index) {
                piece.setOnBoard(false);
                opponent.piecesOut++;
                opponent.indexes[index].clearOne();
                piece.square = new Square(0);
                System.out.println("Opponent's piece sent back to start.");
            }
        }
    }

    public static boolean check(Piece piece, int moves, State state, Player player) {
        if (!piece.isOnBoard()) return false;

        int targetIndex = piece.square.getIndex() + moves;

        if (targetIndex >= 57) return false;

        if (player.indexes[targetIndex].special || player.indexes[targetIndex].isSafe()) return true;

        Player opponent = state.Players.get(1 - state.Players.indexOf(player));
        return !opponent.indexes[targetIndex].getPlayerHere().equals(opponent.who);
    }

    public static ArrayList<State> nextStates(String who, State state, Roll roll) {
        ArrayList<State> states = new ArrayList<>();
        Player currentPlayer = who.equals("@") ? state.Players.get(0) : state.Players.get(1);

        for (Piece piece : currentPlayer.getPieces()) {
            if (piece.isOnBoard() && check(piece, roll.getSteps(), state, currentPlayer)) {
                State newState = Move(currentPlayer, state, roll.getSteps(), piece);
                states.add(newState);
            }
        }

        if (roll.isAgain() && currentPlayer.piecesOut > 0) {
            State newState = state.Copy();
            addPiece(newState.Players.get(state.Players.indexOf(currentPlayer)));
            states.add(newState);
        }

        return states;
    }

    public static State Move(Player player, State state, int moves, Piece piece) {
        State newState = state.Copy();
        move(moves, piece, newState, player);
        return newState;
    }

    public static void playWithComputer(State state) {
        Scanner scanner = new Scanner(System.in);
        String who = "@";

        while (!state.Final()) {
            state.draw();
            Player currentPlayer = who.equals("@") ? state.Players.get(0) : state.Players.get(1);
            Roll roll = new Roll();

            System.out.println("Current Player: " + who);
            System.out.println("Dice Roll: " + roll.getSteps());

            if (roll.getSteps() == 6 && currentPlayer.piecesOut > 0) {
                addPiece(currentPlayer);
                state.refreshBoard();
                continue;
            }

            System.out.println("Select a piece to move (index): ");
            for (Piece piece : currentPlayer.getPieces()) {
                if (piece.isOnBoard()) {
                    System.out.println("Piece at index: " + piece.square.getIndex());
                }
            }

            int index = scanner.nextInt();
            Piece pieceToMove = null;
            for (Piece piece : currentPlayer.getPieces()) {
                if (piece.isOnBoard() && piece.square.getIndex() == index) {
                    pieceToMove = piece;
                    break;
                }
            }

            if (pieceToMove != null) {
                move(roll.getSteps(), pieceToMove, state, currentPlayer);
            } else {
                System.out.println("Invalid piece selection.");
                continue;
            }

            if (!roll.isAgain()) {
                who = who.equals("@") ? "#" : "@";
            }
        }

        System.out.println("Game Over! Winner: " + (state.Players.get(0).checkFinal() ? "Player @" : "Player #"));
    }
}
