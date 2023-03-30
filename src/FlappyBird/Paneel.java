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

    //public int ülemiseAlumiseVahe = (int) Math.round(Math.random()*40+110); hiljem teen sellega kõik torude vertikaalsed vahed suvaliseks)

    public double skoor = 0;

    //public String skoorStringina = Integer.toString(Skoor); //garbage code vist, delete later

    public static int takistuseKiirus = 5; //Math.toIntExact(Math.round(Math.random()*10+5)); hiljem implementida midagi sellist, aga seniks on see staatiline muutuja

    private ArrayList<Takistus> takistusteList;

    enum MänguOlek {
        PEAMENÜÜS,
        MÄNG_KÄIB,
        MÄNG_LÄBI
    }

    public static MänguOlek mänguOlek;


    public Paneel(){
        mängijaRuut= new Mängija(200, 300);
        takistusteList = new ArrayList<Takistus>();
        setPreferredSize(new Dimension(Aken.LAIUS, Aken.KÕRGUS));
        setBackground(new Color(31, 158, 155));
        setLayout(null);
        setFocusable(true); //võtab sisendit vastu
        addKeyListener(this);
        mänguOlek = MänguOlek.PEAMENÜÜS;
        timer = new Timer(20, this);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(38, 129, 120));
        g.fillRect(0, 0, Aken.LAIUS, Aken.KÕRGUS);

        g.setColor(new Color(100, 153, 0));
        g.fillRect(0, Aken.mängualaAluminePiire, Aken.LAIUS, 20);

        g.setColor(new Color(192, 106, 4));
        g.fillRect(0, Aken.KÕRGUS - 80, Aken.LAIUS, 80);

        if (mänguOlek == MänguOlek.MÄNG_KÄIB || mänguOlek == MänguOlek.MÄNG_LÄBI) { //kui mäng käib
            mängijaRuut.joonista(g);
            for (Takistus takistus : takistusteList) {
                takistus.joonistaObjektid(g);
            }
        }
        if (mänguOlek == MänguOlek.MÄNG_KÄIB) // ainult kui mäng käib
        {Font font = new Font("Arial", Font.BOLD, 45);
            g.setFont(font);
            g.setColor(Color.ORANGE);
            if ( skoor > 0)
            g.drawString(String.valueOf(Math.round(skoor)), 300, 200);}


        if (mänguOlek == MänguOlek.MÄNG_LÄBI) { //kui mäng on läbi saanud
            Font font = new Font("Arial", Font.BOLD, 45);
            g.setFont(font);
            g.setColor(Color.ORANGE);
            g.drawString("Mäng läbi", 212, 200);
            g.setColor(Color.ORANGE);
            g.drawString("punktid: " + String.valueOf(Math.round(skoor)), 210, 300);
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
            if (mänguOlek == MänguOlek.MÄNG_KÄIB){
            mängijaRuut.tühikuVajutus();}
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

        for (Takistus takistus: takistusteList){  // collision control
            if (mängijaRuut.getX() + Mängija.LAIUS >= takistus.getX() &&
                takistus.getX()+Takistus.LAIUS >= mängijaRuut.getX() &&
                mängijaRuut.getY() <= takistus.getY() + takistus.kõrgus &&
                mängijaRuut.getY() + Mängija.KÕRGUS >= takistus.getY() ||
                mängijaRuut.getY() + Mängija.KÕRGUS >= 500 ||
                mängijaRuut.getY() <=0){

                lõpetaMäng();
            }
            if (mängijaRuut.getX() == takistus.getX() + Takistus.LAIUS) {
                skoor += 0.5; // lisab 0.5p, sest lind läheb kahest takistusest mööda
            }
        }
        for (Takistus takistus: takistusteList){
            takistus.takistusLiigub();
        }
    }

    public void kontrolliJagenereeriTakistused(){

        while (takistusteArv < 10){
            int kõrgus = (int) (Math.random() * 300) + Math.toIntExact(Math.round(Math.random() * 50));
            int xAsukoht = 700 ;
            //int torudeVahedeRandomizer = (int) Math.round(Math.random()*500); hiljem implementeerin
            //int ajutineXAsukoht = (int) Math.round(Math.random()*500); hiljem implementeerin
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
