import java.util.ArrayList;

public class State {
    int value;
    ArrayList<Player> players;
    public String[] board;
    ArrayList<Square> squares;

    public State(){
        board = new String[57];

    }
}
