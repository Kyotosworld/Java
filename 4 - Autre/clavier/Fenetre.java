import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Image;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Fenetre extends JFrame {

    // Paramètres de la fenêtre
    public static final int LARGEUR = 1200;
    public static final int HAUTEUR = 700;
    public static final int DIFF = 37;

    // ContentPane de la fenetre
    private Panneau panneau;
    private Boule boule;
    private EcouteClavier clavier;

    public class EcouteClavier implements KeyListener {
    
        public void keyPressed(KeyEvent event) {
            deplacer(event.getKeyCode());
        }
        public void keyReleased(KeyEvent event) {}
        public void keyTyped(KeyEvent event) {}
    }

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

        // ecoute du clavier
        clavier = new EcouteClavier();
        this.addKeyListener(clavier);
        this.setFocusable(true);

        // création et initialisation des composants
        boule = new Boule(400, 200, 40);

        panneau = new Panneau(boule);
        this.setContentPane(panneau);
        this.setVisible(true);
        this.mainLoop();
    }

    public void deplacer(int num) {
        switch (num) {
            case 37:
                boule.x--;
                break;
            case 38:
                boule.y--;
                break;
            case 39:
                boule.x++;
                break;
            case 40:
                boule.y++;
                break;
        }
    }

    public void mainLoop() {
        Image offscreen = createImage(Fenetre.LARGEUR, Fenetre.HAUTEUR);

    	while(true) {

            //boule.update();
            panneau.i+=50;
            if (panneau.i>=1200) {
            	panneau.i=0;
            	panneau.j+=50;
            }

            panneau.paintComponent(offscreen.getGraphics());
            panneau.getGraphics().drawImage(offscreen, 0, 0, this);

            try {Thread.sleep(15);}
            catch (InterruptedException err) {System.exit(1);}
        } 

    }

    public Boule getBoule() {
        return this.boule;
    }
}
