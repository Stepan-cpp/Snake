package Snake;

import javax.swing.*;

public class draw{

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960,540);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.add(new Display(frame));
        frame.setVisible(true);
    }

}
