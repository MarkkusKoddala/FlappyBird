package FlappyBird;

import java.awt.*;

public class Takistus {
    private int x;
    private int y;
    private int kõrgus;
    public final int LAIUS = 50;

    public Takistus(int x, int y, int kõrgus){
        this.x = x;
        this. y = y;
        this.kõrgus = kõrgus;
    }


    public int getY() {
        return y;
    }

    public int getKõrgus() {
        return kõrgus;
    }

    public int getLAIUS() {
        return LAIUS;
    }

    public void joonistaObjektid (Graphics g){
        g.setColor(Color.green);
        g.drawRect(x, y, LAIUS, kõrgus);
        g.fillRect(x, y, LAIUS, kõrgus);
    }

    public void takistusLiigub(){
        x -= 3;
    }
    public void lisaTakistus(){

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

}
