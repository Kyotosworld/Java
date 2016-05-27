 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
 
public class Jeu extends JPanel {
 
    private int x,  y;
    private int vx,  vy;
    private Timer timer;
    ActionListener moveBall;
 
    public Jeu(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
 
        moveBall = new ActionListener() {
 
            /**
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int margin = 2;
                // Effacement de l'ancienne position de la boule.
                repaint(Jeu.this.x - margin, Jeu.this.y - margin, 40 + 2 * margin, 40 + 2 * margin);
                calculerPosition();
                // Affichage du nouvel emplacement de la boule.
                repaint(Jeu.this.x - margin, Jeu.this.y - margin, 40 + 2 * margin, 40 + 2 * margin);
            }
        };
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Shape clip = g.getClip();
        if (clip.intersects(x, y, 40, 40)) {
            Graphics2D g2d = (Graphics2D) g.create();
            //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            try {
                g2d.fillOval(x, y, 40, 40);
            } finally {
                g2d.dispose();
            }
        }
    }
 
    public void init() {
        this.setBackground(Color.BLACK);
        this.setForeground(Color.BLUE);
        timer = new Timer(40, moveBall);
        timer.start();
    }
 
    private void calculerPosition() {
        if (x < 0 || x >= 800 - 10) {
            vx = -vx;
        }
 
        if (y < 0 || y >= 600 - 10) {
            vy = -vy;
        }
 
        x += vx;
        y += vy;
    }
}
