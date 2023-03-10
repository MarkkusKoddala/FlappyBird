package FlappyBird;

import javax.swing.*;
import java.awt.*;

public class FlappyBird extends JFrame {
    Paneel paneel;
    public FlappyBird() {
        paneel = new Paneel();
        add(paneel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlappyBird();

    }
}
