import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Panneau extends JPanel {

    // Composants

    /** constructeur Panneau
     *  initialise les divers composants du jeu
     * @return instance de Panneau
     */
    public Panneau() {
    }

    public void paintComponent(Graphics g) {
    	// Redessine les zones qui n'ont pas changé
        g.setColor(Color.BLACK);
        g.fillRect(0,0, this.getWidth(), this.getHeight());

        // dessine les composants
    }

}
