import java.util.ArrayList;
import java.util.Objects;

public class State {
    ArrayList<Player> Players;
    int value;
    public String[] board;
    ArrayList<Square> squares;


    public State() {
        board = new String[57];
        for (int I = 0; I < 57; I++) {
            board[I] = "_|";
        }
        squares = new ArrayList<>();
        for (int i = 0; i < 57; i++) {
            this.squares.add(new Square(i));
        }
        this.Players = new ArrayList<>();
        this.Players.add(new Player("@"));
        this.Players.add(new Player("#"));
    }

    public void refreshBoard() {
        for (int i = 0; i < board.length; i++) {
            String ll = "";
            if (!Players.get(0).indexes[i].playerHere.equals("| ")) {
                ll += Players.get(0).indexes[i].playerHere;
            }
            if (!Players.get(1).indexes[i].playerHere.equals("| ")) {
                ll += Players.get(1).indexes[i].playerHere;
            }
            if (!ll.isEmpty()) {
                board[i] = ll + "_|";
            } else {
                board[i] = "_|";
            }
        }
    }

    public void draw() {
        for (String cell : board) {
            System.out.print(cell);
        }
        System.out.println();
    }

    public boolean Final() {
        return Players.get(0).checkFinal() || Players.get(1).checkFinal();
    }

    public State Copy() {
        System.out.println("Copying state...");
        State copy = new State();

        for (int i = 0; i < 57; i++) {
            copy.squares.add(this.squares.get(i).Copy());
        }

        copy.Players = new ArrayList<>();
        copy.setValue(this.value);

        for (Player player : this.Players) {
            Player newPlayer = player.Copy();
            if (newPlayer.pieces == null) {
                newPlayer.pieces = new ArrayList<>();
            }
            copy.Players.add(newPlayer);
        }

        System.out.println("State copied successfully.");
        return copy;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Players.get(0).equals(state.Players.get(0)) && Players.get(1).equals(state.Players.get(1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares, Players);
    }
}
