package Snake;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import II.NN;
import II.QLearn;

public class Display extends JPanel implements ActionListener {

    public static final int BS = 60;

    Snake snake;

    Timer timer = new Timer(10,this);

    NN ii = new NN();
    QLearn l = new QLearn(ii);

    public static JFrame frame;

    public Display(JFrame frame){
        this.frame = frame;
        timer.start();
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                snake.move(e);
            }
        });
        snake = new Snake();
    }

    public void paint(Graphics g){
        g.clearRect(0,0,frame.getWidth(),frame.getHeight());
        String s = snake.getBlocks().getLast().getX() + " " + snake.getBlocks().getLast().getY() + "    " + snake.getFx() + " " + snake.getFy();
        g.drawString(s, 10, 10);
        for(Block e : snake.getBlocks()){
            int x = e.getX(), y = e.getY();
            if(e.isHead()){
                g.setColor(new Color(120,120,120));

            }
            else {
                g.setColor(new Color(150,150,150));
            }
            g.fillRoundRect(x*BS,y*BS,BS,BS,BS/4,BS/4);
            g.setColor(new Color(0,0,0));
            g.drawRoundRect(x*BS,y*BS,BS,BS,BS/4,BS/4);
        }
        g.drawRoundRect(snake.getFx() * BS, snake.getFy() * BS, BS, BS, BS/4, BS/4);
        g.setColor(new Color(200,140,0));
        g.fillRoundRect(snake.getFx() * BS, snake.getFy() * BS, BS, BS, BS/4, BS/4);
    }

    public void actionPerformed(ActionEvent e) {
        ii();
        snake.step();
        repaint();
    }

    private void ii(){
        int hx = snake.getBlocks().getLast().getX(), hy = snake.getBlocks().getLast().getY();
        int[][] out = new int[16][9];
        for(Block b : snake.getBlocks()){
            try{
                out[hx-b.getX()+8][hy-b.getY()+4] = 1;
            }
            catch (ArrayIndexOutOfBoundsException e){ }
        }
        try{
            out[hx - snake.getFx() + 8][hy - snake.getFy() + 4] = -1;
        }
        catch (ArrayIndexOutOfBoundsException e){ }
        ii.setInput(out);
        l.learn(snake.size(), len(snake.getLastBlock().getX() - snake.getFx(), snake.getLastBlock().getY() - snake.getFy()));
        snake.setDir(ii.getOut());
    }

    private int len(int x, int y){
        return (int) Math.sqrt(x*x + y*y);
    }
}
