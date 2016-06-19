import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Image;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class Fenetre extends JFrame {

    // Paramètres de la fenêtre
    public static final int LARGEUR = 1280;
    public static final int HAUTEUR = 720;

    // ContentPane de la fenetre
    private Panneau p;
    private int fps;


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
        this.setSize(Fenetre.LARGEUR, Fenetre.HAUTEUR);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // création et initialisation des composants
        fps = 0;

        p = new Panneau(fps);
        this.setContentPane(p);
        this.setVisible(true);
        this.mainLoop();
    }

    public void mainLoop() {
        Image offscreen = createImage(Fenetre.LARGEUR, Fenetre.HAUTEUR);

        int frames = 0;
        long time0 = System.currentTimeMillis();
        long time;

    	while(true) {
            time = System.currentTimeMillis();
            frames++;
            if(time - time0 >= 1000) {
                this.fps = frames;
                p.fps = frames;
                time0 = time;
                frames = 0;
            } else {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException err) {
                    System.exit(1);
                }
            }

            this.p.paintComponent(offscreen.getGraphics());
            this.p.getGraphics().drawImage(offscreen, 0, 0, this);
        }    
    }
}
