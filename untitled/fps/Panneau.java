import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Panneau extends JPanel {

    // Composants
    public int fps;

    /** constructeur Panneau
     *  initialise les divers composants du jeu
     * @return instance de Panneau
     */
    public Panneau(int fps) {
        this.fps = fps;
    }

    public void paintComponent(Graphics g) {
    	// Redessine les zones qui n'ont pas changé
        g.setColor(Color.BLACK);
        g.fillRect(0,0, Fenetre.LARGEUR, Fenetre.HAUTEUR);
        g.setColor(Color.RED);
        //g.fillRect(0,0, this.getWidth(), this.getHeight());

        // dessine les composants
        g.setColor(Color.YELLOW);
        g.drawString(String.valueOf(fps), 4, 14);
    }

}
