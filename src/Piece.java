import java.util.Objects;

public class Piece {
    private String who;
    private boolean onBoard;
    private boolean isWin;
    public Square square;
    public Piece(){
        this.onBoard = false;
        this.isWin = false;
        this.square = null;
    }
    public boolean isOnBoard() {
        return onBoard;
    }
    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }
    public boolean isWin() {
        return isWin;
    }
    public void setWin(boolean win) {
        isWin = win;
    }
    public void setIndex(int target) {
        this.square.setIndex(target);
    }

    public Piece Copy(){
        Piece piece = new Piece();
        if (this.onBoard && !this.isWin)
            piece.square = this.square.Copy();
        piece.isWin = this.isWin;
        piece.onBoard  = this.onBoard ;
        piece.who = this.who;
        return piece;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        if (this.square == null || piece.square == null) return false;
        return onBoard == piece.onBoard && isWin == piece.isWin && Objects.equals(who, piece.who) && Objects.equals(square, piece.square);
    }
    @Override
    public String toString() {
        return this.square.getIndex() +
                "- \n";
    }
    @Override
    public int hashCode() {
        return Objects.hash(who, onBoard, isWin, square);
    }

}
