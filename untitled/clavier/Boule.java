import java.lang.Math;
import java.awt.Color;

public class Boule {

    public int x, y, r;
    public Color c = Color.RED;
    private final int centerX, centerY;
    private boolean bX, bY;

    public Boule(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;

        this.centerX = (int)(Fenetre.LARGEUR/2);
        this.centerY = (int)(Fenetre.HAUTEUR/2);
        this.bX = (this.x == 0)? false: true;
        this.bY = (this.y == 0)? false: true;
    }
    public Boule(int r) {
        this(0,0,r);
    }

    public void update() {
        int deltaX = Math.abs(x+(int)(r/2)-centerX);
        int deltaY = Math.abs(y+(int)(r/2)-centerY);
        int diff = (int)Math.sqrt((centerX-x)*(centerX-x) + (centerY-y)*(centerY-y));

        //System.out.println(deltaX +" "+ deltaY +" "+ diff);
        //System.out.println(x +" "+ y +" "+ r);

        if(x <= 0 || x+r >= Fenetre.LARGEUR)
            bX = !bX;
        if(y <= 0 || y+r >= Fenetre.HAUTEUR)
            bY = !bY;


//        x += (int)(deltaX/20);
//        y += (int)(deltaY/20);
/*
        if(bX) x += (int)(deltaY/10);
        else   x -= (int)(deltaY/10);
        if(bY) y += (int)(deltaX/10);
        else   y -= (int)(deltaX/10);

/*
        if (bX) x += (int)y/100;
        else    x -= (int)y/100;
        if (bY) y += (int)x/100;
        else    y -= (int)x/100;
/*/
/**/
        if(bX) x++;
        else   x--;
        if(bY) y++;
        else   y--;
/**/
    }
}
