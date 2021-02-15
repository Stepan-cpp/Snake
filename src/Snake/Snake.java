package Snake;

import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.Random;

public class Snake {

    private ArrayDeque<Block> blocks = new ArrayDeque<>();
    private int dir, fx, fy;

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getFx() {
        return fx;
    }

    public int getFy() {
        return fy;
    }

    public Snake(){
        dir = 0;
        blocks.addLast(new Block(true,3,1));
        blocks.addFirst(new Block(false, 2, 1));
        blocks.addFirst(new Block(false, 1, 1));

        Random r = new Random();
        fx = Math.abs(r.nextInt()) % (Display.frame.getWidth() / Display.BS);
        fy = Math.abs(r.nextInt()) % (Display.frame.getHeight() / Display.BS);
    }

    public ArrayDeque<Block> getBlocks(){
        return blocks;
    }

    public int size(){
        return blocks.size();
    }

    public void die(){
        blocks.clear();
    }

    public void move(KeyEvent event){
        switch (event.getKeyCode()){
            case KeyEvent.VK_W:
                dir = 3;
                break;
            case KeyEvent.VK_D:
                dir = 0;
                break;
            case KeyEvent.VK_S:
                dir = 1;
                break;
            case KeyEvent.VK_A:
                dir = 2;
                break;
        }

    }

    public Block getLastBlock(){
        return blocks.getLast();
    }

    public void step(){
        int x = blocks.getLast().getX(), y = blocks.getLast().getY();

        blocks.removeFirst();
        blocks.getLast().setHead(false);

        switch (dir){
            case 0:
                blocks.addLast(new Block(true, x+1, y));
                break;
            case 1:
                blocks.addLast(new Block(true, x, y+1));
                break;
            case 2:
                blocks.addLast(new Block(true, x-1, y));
                break;
            case 3:
                blocks.addLast(new Block(true, x, y-1));
                break;
        }

        isCollided();
        isEated();
    }

    private void isCollided(){
        ArrayDeque<Block> bl = blocks.clone();
        int x = blocks.getLast().getX(), y = blocks.getLast().getY();
        bl.removeLast();
        while (bl.size() > 0){
            if(bl.getLast().getX() == x && bl.getLast().getY() == y){
                die();
                return;
            }
            bl.removeLast();
        }
    }

    private void isEated(){
        if(blocks.getLast().getX() == fx && blocks.getLast().getY() == fy){
            blocks.getLast().setHead(false);

            int x = blocks.getLast().getX(), y = blocks.getLast().getY();

            switch (dir){
                case 0:
                    blocks.addLast(new Block(true, x+1, y));
                    break;
                case 1:
                    blocks.addLast(new Block(true, x, y+1));
                    break;
                case 2:
                    blocks.addLast(new Block(true, x-1, y));
                    break;
                case 3:
                    blocks.addLast(new Block(true, x, y-1));
                    break;
            }

            Random r = new Random();
            fx = Math.abs(r.nextInt()) % (Display.frame.getWidth() / Display.BS);
            fy = Math.abs(r.nextInt()) % (Display.frame.getHeight() / Display.BS);

        }
    }
}
