package FlappyBird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//https://www.youtube.com/watch?v=a3Hzs2XAJBg
public class Paneel extends JPanel implements KeyListener, ActionListener {
    private int takistusteArv;
    private Mängija mängijaRuut;
    private Timer timer;

    public static int takistuseKiirus = 5;
    private ArrayList<Takistus> takistusteList;

    enum MänguOlek {
        PEAMENÜÜS,
        MÄNG_KÄIB,
        MÄNG_LÄBI
    }

    public static MänguOlek mänguOlek;


    public Paneel(){
        mängijaRuut= new Mängija(400, 400);
        takistusteList = new ArrayList<Takistus>();
        setPreferredSize(new Dimension(Aken.LAIUS, Aken.KÕRGUS));
        setBackground(new Color(31, 158, 208));
        setLayout(null);
        setFocusable(true); //võtab sisendit vastu
        addKeyListener(this);
        mänguOlek = MänguOlek.PEAMENÜÜS;
        timer = new Timer(20, this);
    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);

        g.setColor(new Color(38, 129, 216));
        g.fillRect(0,0,Aken.LAIUS,Aken.KÕRGUS);

        g.setColor(new Color(10, 189, 15));
        g.fillRect(0, Aken.mängualaAluminePiire, Aken.LAIUS, 20);

        g.setColor(new Color(192, 106, 4));
        g.fillRect(0, Aken.KÕRGUS-80, Aken.LAIUS, 80 );

        if (mänguOlek == MänguOlek.MÄNG_KÄIB || mänguOlek == MänguOlek.MÄNG_LÄBI) { //kui mäng käib
            mängijaRuut.joonista(g);
            for (Takistus takistus : takistusteList) {
                takistus.joonistaObjektid(g);
            }

        }

        if (mänguOlek == MänguOlek.MÄNG_LÄBI) { //kui mäng on läbi saanud
            Font font = new Font("Arial", Font.BOLD, 45);
            g.setFont(font);
            g.setColor(Color.red);
            g.drawString("Mäng läbi", 205, 200);
        } else if (mänguOlek == MänguOlek.PEAMENÜÜS) { //kui mäng pole veel alanud

            Font font = new Font("Arial", Font.BOLD,  25);
            g.setFont(font);
            g.drawString("Vajuta ENTER, et alustada mängu!", 120, 300);
        }
    }

    public void keyPressed (KeyEvent e) {
        if (mänguOlek != MänguOlek.MÄNG_KÄIB && e.getKeyCode() == KeyEvent.VK_ENTER){
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
            if (mängijaRuut.getX() + Mängija.LAIUS >= takistus.getX() &&
                takistus.getX()+Takistus.LAIUS >= mängijaRuut.getX() &&
                mängijaRuut.getY() <= takistus.getY() + takistus.kõrgus &&
                mängijaRuut.getY() + Mängija.KÕRGUS >= takistus.getY() ||
                mängijaRuut.getY() + Mängija.KÕRGUS >= 500 ||
                mängijaRuut.getY() <=0){

                lõpetaMäng();
            }
        }
        for (Takistus takistus: takistusteList){
            takistus.takistusLiigub();
        }
    }

    public void kontrolliJagenereeriTakistused(){

        double suvalineOoteaeg = Math.random() * 3;
        Double newDouble = Double.valueOf(suvalineOoteaeg);
        long suvalineOoteaegLongina = newDouble.longValue();

        while (takistusteArv < 6){
            int kõrgus = (int) (Math.random() * 100) +200;
            int xAsukoht = 700;

            if (takistusteList.size() != 0) {
                xAsukoht = takistusteList.get(takistusteList.size()-2).getX() + Aken.LAIUS + 100;
            }

            takistusteList.add(new Takistus(xAsukoht, 0, kõrgus));
            takistusteList.add(new Takistus(xAsukoht, kõrgus+150, Aken.KÕRGUS-kõrgus-150));

            takistusteArv += 2;
        }
    }
    public void lõpetaMäng(){
        mänguOlek = MänguOlek.MÄNG_LÄBI;
    }
    public void alustaMängu(){
        mänguOlek = MänguOlek.MÄNG_KÄIB;
        timer.start();
    }

    public void keyReleased (KeyEvent e){ //ei lähe vaja, aga kuna liideses on see olemas, siis...

    }

    public void keyTyped (KeyEvent e){

    }


}
