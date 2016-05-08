import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Bouton extends JButton implements MouseListener{
    
    private String name;
    private Color bg;
    private final Color bgDefault = Color.LIGHT_GRAY;

    public Bouton(String str) {
        super(str);
        this.name = str;
        this.bg = bgDefault;

        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        g.setColor(this.bg);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(Color.BLACK);
        g.drawString(this.name,
                     this.getWidth() / 2 - (this.getWidth() /2 /4),
                     (this.getHeight()/2) + 5);
    }

    public void mouseClicked(MouseEvent event) {}

    public void mouseEntered(MouseEvent event) {
        this.bg = Color.RED;
    }

    public void mouseExited(MouseEvent event) {
        this.bg = bgDefault;
    }

    public void mousePressed(MouseEvent event) {
        this.bg = Color.YELLOW;
    }

    public void mouseReleased(MouseEvent event) {
        this.bg = Color.DARK_GRAY;
    }
}
