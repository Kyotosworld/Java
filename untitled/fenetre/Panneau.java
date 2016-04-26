package untitled.fenetre;
import java.awt.*;
import javax.swing.*;

public class Panneau extends JPanel {

    private int x = 0;
    private int y = 0;

    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.red);
        g.fillOval(x, y, this.getWidth()/10, this.getHeight()/10);
    }

    public int getX() {
        return this.x;
    }
    public void setX(int val) {
        this.x = val;
    }
    public int getY() {
        return this.y;
    }
    public void setY(int val) {
        this.y = val;
    }

}
