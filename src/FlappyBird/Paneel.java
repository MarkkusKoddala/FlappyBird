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
        mängijaRuut= new Mängija(400, 400);
        setPreferredSize(new Dimension(LAIUS, KÕRGUS));
        setBackground(new Color(31, 158, 208));
        setLayout(null);
        setFocusable(true); //võtab sisendit vastu
        addKeyListener(this);
        mängKäib = false;
        timer = new Timer(20, this);
    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);

        g.setColor(new Color(38, 129, 216));
        g.fillRect(0,0,LAIUS,KÕRGUS);

        g.setColor(new Color(10, 189, 15));
        g.fillRect(0, KÕRGUS-100, LAIUS, 20);

        g.setColor(new Color(192, 106, 4));
        g.fillRect(0, KÕRGUS-80, LAIUS, 80 );

        if (!(mängKäib)){
            Font font = new Font("Arial", Font.BOLD,  25);
            g.setFont(font);
            g.drawString("Vajuta ENTER, et alustada mängu!", 120, 300);

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
