package FlappyBird;

import javax.swing.*;
import java.awt.*;

public class FlappyBird extends JFrame {
    public static FlappyBird flappyBird;
    private Paneel aken;

    public final int LAIUS = 600, KÕRGUS = 600;

    public FlappyBird() {

        aken = new Paneel();
        add(aken);
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
