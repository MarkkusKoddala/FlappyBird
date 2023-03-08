package FlappyBird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//https://www.youtube.com/watch?v=a3Hzs2XAJBg
public class Paneel extends JPanel implements KeyListener  {
    public final int LAIUS = 600, KÕRGUS = 600;
    private Mängija mängijaRuut;

    public Paneel(){
        mängijaRuut = new Mängija(LAIUS/2, KÕRGUS/2, 20, Color.green);
        setPreferredSize(new Dimension(LAIUS, KÕRGUS));
        setBackground(Color.blue);
        setLayout(null);
        setFocusable(true); //võtab sisendit vastu
        //addKeyListener(this);

    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        mängijaRuut.joonista(g);
    }

    public void keyPressed (KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            mängijaRuut.tühikuVajutus();
            repaint();
        }

    }

    public void keyReleased (KeyEvent e){ //ei lähe vaja, aga kuna liideses on see olemas, siis...

    }

    public void keyTyped (KeyEvent e){

    }


}
