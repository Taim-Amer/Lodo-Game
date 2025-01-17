import java.util.ArrayList;
import java.util.Objects;

public class State {
    // 1 14 27 40 is the safe area
    int value;
    ArrayList<Player> players;
    public String[] board;
    ArrayList<Square> squares;
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public State(){
        board = new String[57];
        for (int i = 0; i <= 57; i++){
            board[i] = "_|";
        }
        squares = new ArrayList<>();
        for(int i = 0 ; i <= 83 ; i++){
            this.squares.add(new Square(i));
        }
        this.players=new ArrayList<>();
        this.players.add(new Player("@"));
        this.players.add(new Player("#"));
    }
    public void refreshBoard(){
        for(int i = 0 ; i <= 83 ; i++ ){
            String ll="";
            if(!this.players.get(0).indexes[i].playerHere.equals("| ")) ll+=this.players.get(0).indexes[i].playerHere;
            if(!this.players.get(1).indexes[i].playerHere.equals("| ")) ll+=this.players.get(1).indexes[i].playerHere;
            if(!ll.equals("")){
                this.board[i]=ll+"_|";
            }
        }
    }
    public void draw(){

        for(String item : this.board){
            System.out.printf(item);
        }
        System.out.println();
    }
    public boolean Final(){
        return players.get(0).checkFinal() || players.get(1).checkFinal();
    }
    public State Copy(){
        State copy=new State();
        for(int i = 0 ; i < 83 ; i++ ){
            this.squares.add(this.squares.get(i).Copy());
        }
        copy.players=new ArrayList<>();
        copy.setValue(this.value);
        copy.players.add(this.players.get(0).Copy());
        copy.players.add(this.players.get(1).Copy());
        return copy;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return players.get(0).equals(state.players.get(0)) && players.get(1).equals(state.players.get(1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares, players);
    }

}
