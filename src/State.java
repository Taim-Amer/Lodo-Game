import java.util.ArrayList;

public class State {
    // 1 14 27 40 is the safe area
    int value;
    ArrayList<Player> players;
    public String[] board;
    ArrayList<Square> squares;

    public State(){
        board = new String[57];

    }
}
