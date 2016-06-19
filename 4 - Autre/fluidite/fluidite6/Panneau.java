//package untitled.resolution;

//import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Panel;

class Panneau extends Panel {

    Boule b;

    public Panneau(Boule b) {
        this.b = b;
    }

    public void paint(Graphics g3d) {
        Graphics2D g = (Graphics2D)g3d;

    	// Reset
    	g.setColor(Color.white);
    	g.fillRect(0,0, this.getWidth(), this.getHeight());

        // Boule
        g.setColor(b.getColor());
        g.drawOval(b.getX(), b.getY(), b.getR(), b.getR());
    }

}
