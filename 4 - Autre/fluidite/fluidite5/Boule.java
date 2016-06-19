//package untitled.resolution;

import java.awt.Color;

public class Boule {

    private int x, y, r;
    private Color c;

    public Boule(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.c = Color.blue;
    }
    public Boule(int r) {
        this(0,0,r);
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getR() {
        return this.r;
    }
    public Color getColor() {
        return this.c;
    }

    public void setX(int val) {
        this.x = val;
    }
    public void setY(int val) {
        this.y = val;
    }
    public void setR(int val) {
        this.r = val;
    }
    public void setColor(Color c) {
        this.c = c;
    }
}
