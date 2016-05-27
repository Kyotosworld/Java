import java.awt.Graphics;
import java.awt.Color;

public class PacMan extends GameObject {

    public PacMan(int x, int y, ID id) {
        super(x,y,id);
        vX = 0;
        vY = 0;
    }

    public void tick() {
        x += vX;
        y += vY;
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 20, 20);
    }
}