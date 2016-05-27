import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    
    protected int x, y;
    protected int vX, vY;
    protected ID id;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    // Getters
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getVX() {
        return this.vX;
    }
    public int getVY() {
        return this.vY;
    }
    public ID getId() {
        return this.id;
    }

    // Setters
    public void setX(int val) {
        this.x = val;
    }
    public void setY(int val) {
        this.y = val;
    }
    public void setVX(int val) {
        this.vX = val;
    }
    public void setVY(int val) {
        this.vY = val;
    }
    public void setId(ID val) {
        this.id = val;
    }
}