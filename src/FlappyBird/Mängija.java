package FlappyBird;

import java.awt.*;

public class Mängija {

    private int x, y;
    private int suurus;
    private Color värv;

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
        x += 10;
    }
}
