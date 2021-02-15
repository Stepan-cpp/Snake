package Snake;

public class Block{
    private boolean isHead;

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean b){
        isHead = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x, y;

    Block(boolean isHead, int x, int y){
        this.isHead = isHead;
        this.x = x;
        this.y = y;
    }


}