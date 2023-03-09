package FlappyBird;

import java.awt.*;

public class Mängija {

    public int x, y;
    private int suurus;
    private Color värv;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Mängija(int x, int y, int suurus, Color värv) {
        this.x = x;
        this.y = y;
        this.suurus = suurus;
        this.värv = värv;
    }

    public void joonista(Graphics g){
        g.setColor(värv);
        g.fillRect(x, y, suurus, suurus);
    }

    public void tühikuVajutus(){
        y += 50;
    }

    public void liigub(){
        y-=3;
    }

}
