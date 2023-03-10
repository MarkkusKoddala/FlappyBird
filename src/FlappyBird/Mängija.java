package FlappyBird;

import java.awt.*;

public class Mängija {

    public int x, y;
    private final int suurus;
    private final Color värv;

    public Mängija(int x, int y) {
        this.x = x;
        this.y = y;
        this.suurus = 20;
        this.värv = new Color(206, 11, 11);
    }

    public void joonista(Graphics g) {
        g.setColor(värv);
        g.drawOval(x, y, 20, 20);
        g.fillOval(x, y, 20, 20);
    }


    public void tühikuVajutus(){
        y -= 50;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void liigub(){
        y+=3;
    }

}
