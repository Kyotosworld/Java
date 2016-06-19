import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Math;

public class Panneau extends JPanel {

    // Composants
    Boule b;
    int i, j;


    /** constructeur Panneau
     *  initialise les divers composants du jeu
     * @return instance de Panneau
     */
    public Panneau(Boule b) {
        this.b = b;
        this.i = 0;
        this.j = 0;
    }

    public void paintComponent(Graphics g) {
        Color[] couleurs = {Color.RED, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA,
                            Color.ORANGE, Color.PINK, Color.GREEN, Color.WHITE, Color.BLACK, Color.GRAY};
        int rand;

        // Redessine les zones qui n'ont pas changé
        g.setColor(Color.BLACK);
        g.fillRect(0,0, this.getWidth(), this.getHeight());

        //rand = (int)(Math.random()*couleurs.length);
        //g.setColor(couleurs[rand]);
        //g.fillRect(i, j, 49, 49);

        // dessine les composants
        // boule
        g.setColor(this.b.c);
        g.fillOval(this.b.x, this.b.y, this.b.r, this.b.r);
    }

}
