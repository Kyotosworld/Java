import java.awt.*;
import javax.swing.*;

public class Fenetre extends JFrame {

    private Panneau p = new Panneau();

    public static void main(String[] args) {
        Fenetre f = new Fenetre();
    }

    public Fenetre() {
        this.setTitle("untitled.fenetre.Fenetre");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(p);
        this.setVisible(true);
        System.out.println(p.getWidth()+" "+p.getHeight());
        loop();
    }

    public void loop() {
        boolean b = true;           // b rentre dans la premiere condition
        
        Graphics g = p.getGraphics();
        Image offscreen = p.createImage(getWidth(), getHeight());
        Graphics buffer = offscreen.getGraphics();
 
        while(true) {
            System.out.println("("+p.getX()+", "+p.getWidth()+")");
            if ((p.getX() == (int)(p.getWidth()*9/10) && p.getY() == (int)(p.getHeight()*9/10)) ||
                (p.getX() == 0 && p.getY() == 0))
                b = !b;
            if (!b) {
                p.setX(p.getX()+1);
                p.setY(p.getY()+1);
            } else {
                p.setX(p.getX()-1);
                p.setY(p.getY()-1);
            }
            //p.repaint();
            p.paintComponent(buffer);
            try {
                Thread.sleep(25);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            g.drawImage(offscreen, 0, 0, this);
        }
    }
}
