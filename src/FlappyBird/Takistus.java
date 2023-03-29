package FlappyBird;

import java.awt.*;

public class Takistus {
    public int x;
    private int y;
    public int kõrgus;
    public static final int LAIUS = 50;

    public int getY() {
        return y;
    }

    public Takistus(int x, int y, int kõrgus){
        this.x = x;
        this. y = y;
        this.kõrgus = kõrgus;
    }

    public void joonistaObjektid (Graphics g){
        g.setColor(Color.green);
        g.drawRect(x, y, LAIUS, kõrgus);
        g.fillRect(x, y, LAIUS, kõrgus);
    }

    public void takistusLiigub(){
        x -= Paneel.takistuseKiirus;
    }
    public int getX() {
        return x;
    }

}
