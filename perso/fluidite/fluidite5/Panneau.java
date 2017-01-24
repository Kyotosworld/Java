//package untitled.resolution;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Panel;

class Panneau extends JPanel {

    Boule b;
    long fps;

    public Panneau(Boule b) {
        this.b = b;
        this.fps = 0;
    }

    public void paintComponent(Graphics g) {
    	// Reset
    	g.setColor(Color.white);
    	g.fillRect(0,0, this.getWidth(), this.getHeight());

        // Boule
        g.setColor(b.getColor());
        g.drawOval(b.getX(), b.getY(), b.getR(), b.getR());

        // FPS
        g.setColor(Color.black);
        g.drawString(String.valueOf(fps), 4, 14);
    }

}
