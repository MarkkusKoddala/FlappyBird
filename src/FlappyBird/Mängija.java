package FlappyBird;

import java.awt.*;
import java.sql.Time;

public class Mängija {

    public final int RUUDUA = 20;

    public int x, y;
    public double yKiirus;
    private final Color värv;

    public Mängija(int y) {
        this.x = 250;
        this.y = y;
        this.yKiirus = 4;
        this.värv = new Color(206, 11, 11);
    }

    public void joonista(Graphics g) {
        g.setColor(värv);
        g.fillRect(x, y, 20, 20);
    }

    public int getRUUDUA() {
        return RUUDUA;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public void setyKiirus(int kiirus){
        this.yKiirus=kiirus;
    }

    public void liigub(){
        if (yKiirus < 4){
            yKiirus += 0.2;
            y+=yKiirus;}
        else y+=4;
    }
}
