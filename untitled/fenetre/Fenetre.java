package untitled.fenetre;
import java.awt.*;
import javax.swing.*;

public class Fenetre extends JFrame {

    private Panneau p = new Panneau();

    public static void main(String[] args) {
        Fenetre f = new Fenetre();
    }

    public Fenetre() {
        this.setTitle("untitled.fenetre.Fenetre");
        this.setSize(500, 537);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(p);
        this.setVisible(true);
        loop();
    }

    public void loop() {
        boolean b = true;           // b rentre dans la premiere condition
        //for(int i=-50; i<p.getWidth(); i++) {
        while(true) {
            if ((p.getX()+50 == p.getWidth() && p.getY()+50 == p.getHeight()) ||
                (p.getX() == 0 && p.getY() == 0))
                b = !b;
            if (!b) {
                p.setX(p.getX()+1);
                p.setY(p.getY()+1);
            } else {
                p.setX(p.getX()-1);
                p.setY(p.getY()-1);
            }
            p.repaint();
            try {
                Thread.sleep(5);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
