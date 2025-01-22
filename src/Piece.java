import java.util.Objects;

public class Piece {
    private String who;
    private boolean onBoard;
    private boolean isWin;
    public Square square;

    public boolean isOnBoard() {
        return onBoard;
    }
    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }
    public Piece(){
        this.onBoard = false;
        this.isWin = false;
        this.square = null;
    }
    public boolean isWin() {
        return isWin;
    }
    public void setWin(boolean win) {
        isWin = win;
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
    public int hashCode() {
        return Objects.hash(who, onBoard, isWin, square);
    }

    public void setIndex(int target) {
        this.square.setIndex(target);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "onBoard=" + (isOnBoard() ? "true" : "false") +
                ", win=" + (isWin() ? "true" : "false") +
                ", square=" + (square != null ? square.getIndex() : "null") +
                '}';
    }

}
