public class Square {
    private int index;
    public String playerHere = "| ";
    private boolean isSafe;
    int pieceNumber = 0;
    public Square(int index){
        this.index = index;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public String getPlayerHere() {
        return playerHere;
    }
    public void setPlayerHere(String playerHere) {
        this.playerHere = playerHere;
    }
    public boolean isSafe() {
        return isSafe;
    }
    public void clearOne(){
        this.pieceNumber--;
        if(this.pieceNumber == 0){
            this.playerHere = "| ";
        }
    }
    public void  addOne(String who){
        this.pieceNumber++;
        this.playerHere = who;
    }
    public Square Copy(){
        Square copy=new Square(this.index);
        copy.playerHere = this.playerHere;
//        copy.special = this.special;
        copy.isSafe = this.isSafe;
        copy.pieceNumber = this.pieceNumber;
        return copy;
    }
    @Override
    public String toString() {
        return playerHere + index;
    }
}
