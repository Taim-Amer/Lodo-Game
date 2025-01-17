import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Player {
    public Square[] indexes;
    public String who;

    //@ me
    // # computer
    public ArrayList<Piece> pieces;
    public ArrayList<Piece> isWin;
    public int piecesOut;

    public Player(String who) {
        this.who = who;
        pieces = new ArrayList<>();
        for (int II = 0; II < 4; II++) {
            pieces.add(new Piece());
        }
        isWin = new ArrayList<Piece>();
        this.piecesOut = 4;
        indexes = new Square[57];
        for (int I = 0; I < 57; I++) {
            indexes[I] = new Square(I);
//            if (I < 76 && I > 7) indexes[I].special = true;
        }

    }
    public ArrayList<Piece> getPieces() {
        return pieces;
    }
    public boolean checkFinal() {
        return this.isWin.size() == 4;
    }

    public Player Copy() {
        Player player = new Player(this.who);
        for (int ii = 0; ii < 56; ii++) {
            player.indexes[ii] = this.indexes[ii].Copy();
        }
        player.isWin = new ArrayList<>(this.isWin);
        player.who = this.who;
        player.piecesOut = this.piecesOut;
        for (int i = 0; i < this.pieces.size(); i++) {
            player.pieces.add(this.pieces.get(i).Copy());
        }
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        if (!Objects.equals(this.who, player.who))
            return false;
        for (int i = 0; i < this.indexes.length; i++) {
            if (!this.indexes[i].equals(player.indexes[i])) {
                return false;
            }
        }
        for (int i = 0; i < this.pieces.size(); i++) {
            if (!this.pieces.get(i).equals(player.pieces.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(who, piecesOut, pieces, isWin);
        result = 31 * result + Arrays.hashCode(indexes);
        return result;
    }
}
