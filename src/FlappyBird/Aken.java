package FlappyBird;

import javax.swing.*;

public class Aken {

    public final int LAIUS = 600, KÕRGUS = 600;
    public Aken(){
        JFrame aken = new JFrame();
        aken.setSize(LAIUS, KÕRGUS);
        aken.setVisible(true);
        aken.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aken.setContentPane(new Paneel());
    }
}
