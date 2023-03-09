package FlappyBird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//https://www.youtube.com/watch?v=a3Hzs2XAJBg
public class Paneel extends JPanel implements KeyListener, ActionListener {
    public final int LAIUS = 600, KÕRGUS = 600;
    private Mängija mängijaRuut;
    private Timer timer;
    private boolean mängKäib;

    public Paneel(){
        mängijaRuut= new Mängija(400, 400, 10, Color.green);
        setPreferredSize(new Dimension(LAIUS, KÕRGUS));
        setBackground(Color.blue);
        setLayout(null);
        setFocusable(true); //võtab sisendit vastu
        addKeyListener(this);
        mängKäib = false;
        timer = new Timer(20, this);
    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        if (!mängKäib){
            g.drawString("Vajuta ENTER, et alustada mängu!", 300, 300);

        } else {
            mängijaRuut.joonista(g);
        }
    }
    public void keyPressed (KeyEvent e) {
        if (!mängKäib && e.getKeyCode() == KeyEvent.VK_ENTER){
            alustaMängu();

    }else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            mängijaRuut.tühikuVajutus();
        }
    }

    public void actionPerformed (ActionEvent a){
        mänguMootor();
        repaint();
    }

    public void mänguMootor(){
        mängijaRuut.liigub();
    }

    public void alustaMängu(){
        mängKäib = true;
        timer.start();
    }

    public void keyReleased (KeyEvent e){ //ei lähe vaja, aga kuna liideses on see olemas, siis...

    }

    public void keyTyped (KeyEvent e){

    }

}
