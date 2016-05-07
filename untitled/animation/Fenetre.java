import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Image;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class Fenetre extends JFrame {

    // Paramètres de la fenêtre
    public static final int LARGEUR = 1200;
    public static final int HAUTEUR = 700;
    public static final int DIFF = 37;

    // ContentPane de la fenetre
    private Panneau p;
    private Boule b;

    /** main
     *  Crée une nouvelle fenêtre
     * @param args le tableau d'arguments passés depuis la console
     */
    public static void main(String[] args) {
        Fenetre f = new Fenetre();
    }

    /** constructeur Fenetre
     *  Initialise une nouvelle instance de Fenetre(), crée les composants nécessaires et lance la mainloop
     *
     *  Initialise divers paramètres (titre, taille, position, etc..)
     *  Crée tous les composants nécessaires au jeu (une boule, une case, etc..)
     *  Crée un Panneau qui sera le contentPane et qui aura comme attributs les divers composants du jeu
     *  Rend visible la fenetre
     *  Lance la mainloop
     * @return instance de la classe Fenetre
     */
    public Fenetre() {
        this.setTitle("untitled");
        this.setSize(Fenetre.LARGEUR, Fenetre.HAUTEUR+Fenetre.DIFF);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // création et initialisation des composants
        this.b = new Boule(400, 200, 40);

        p = new Panneau(b);
        this.setContentPane(p);
        this.setVisible(true);
        this.mainLoop();
    }

    public void mainLoop() {
        Image offscreen = createImage(Fenetre.LARGEUR, Fenetre.HAUTEUR);

    	while(true) {

            b.update();
            p.i+=50;
            if (p.i>=1200) {
            	p.i=0;
            	p.j+=50;
            }

            this.p.paintComponent(offscreen.getGraphics());
            this.p.getGraphics().drawImage(offscreen, 0, 0, this);

            try {Thread.sleep(15);}
            catch (InterruptedException err) {System.exit(1);}
        } 

    }
}
