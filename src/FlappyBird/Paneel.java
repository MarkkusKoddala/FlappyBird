package FlappyBird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

//https://www.youtube.com/watch?v=a3Hzs2XAJBg
public class Paneel extends JPanel implements KeyListener, ActionListener {
    public final int LAIUS = 600, KÕRGUS = 600, TAKISTUSTEVAHE = 100;
    private int takistusteArv;
    private Mängija mängijaRuut;
    private Timer timer;
    private boolean mängKäib;
    private ArrayList<Takistus> takistusteList;


    public Paneel(){
        mängijaRuut= new Mängija(400, 400);
        takistusteList = new ArrayList<Takistus>();
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
            for (Takistus takistus : takistusteList) {
                takistus.joonistaObjektid(g);
            }
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
        kontrolliJagenereeriTakistused();

        if (!(takistusteList.get(0).getX() + 50 > 0)){
            takistusteList.remove(0);
            takistusteList.remove(0);
            takistusteArv -= 2;
        }

        for (Takistus takistus: takistusteList){
            takistus.takistusLiigub();
        }
    }

    public void kontrolliJagenereeriTakistused(){
        while (takistusteArv < 6){
            if (takistusteList.size() == 0) {
                int kõrgus = (int) (Math.random() * 100) +200;
                takistusteList.add(new Takistus(150, 0, kõrgus));
                takistusteList.add(new Takistus(150, kõrgus+150, 600-kõrgus-150 ));
            } else {
                int kõrgus = (int) (Math.random() * 300) +200;
                takistusteList.add(new Takistus(takistusteList.get(takistusteList.size()-2).getX() + LAIUS + 100, 0, kõrgus));
                takistusteList.add(new Takistus(takistusteList.get(takistusteList.size()-2).getX() + LAIUS + 100, kõrgus+150, 600-kõrgus-150));
            }
            takistusteArv += 2;
        }
    }

    public void kontrolliPuudet (){

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
