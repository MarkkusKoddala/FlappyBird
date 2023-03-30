package FlappyBird;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Mängija {

    public int x, y;
    private final int suurus;
    private final Color värv;

    public static final int LAIUS = 20;

    public static final int KÕRGUS = 20;

    public Mängija(int x, int y) {
        this.x = x;
        this.y = y;
        this.suurus = 20;
        this.värv = new Color(133, 226, 200);
    }

    public void joonista(Graphics g) {
        g.setColor(värv);
        g.drawRect(x, y, LAIUS, KÕRGUS);
        g.fillRect(x, y, LAIUS, KÕRGUS);
    }


    public void tühikuVajutus(){
        kiirus = -7;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double kiirus = 2;

    public void liigub(){
        if (y <= 500) {
            y += kiirus;
            kiirus += 1/2.0;
        }
        if (y>Aken.mängualaAluminePiire - Mängija.KÕRGUS) {
            y=Aken.mängualaAluminePiire - Mängija.KÕRGUS;
        }
        if (Paneel.mänguOlek == Paneel.MänguOlek.MÄNG_LÄBI){
            x -= Paneel.takistuseKiirus;
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
