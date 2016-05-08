import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Image;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Fenetre extends JFrame implements ActionListener, KeyListener {

    // Paramètres de la fenêtre
    public static final int LARGEUR = 1200;
    public static final int HAUTEUR = 700;
    public static final int DIFF = 37;

    // ContentPane de la fenetre
    private Boule boule;
    private Panneau panneau;
    private Bouton bouton;
    private int boutonCompteur;
    private JLabel label;
    private JTextField field;
    private JPanel botContainer;
    private JPanel container;


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
        this.boule = new Boule(400, 200, 40);
        this.panneau = new Panneau(this.boule);

        // mise sur ecoute du bouton par la fenetre
        this.bouton = new Bouton("Click me !");
        this.bouton.addActionListener(this);
        this.boutonCompteur = 0;

        // mise sur ecoute du champ de texte par la fenetre
        this.field = new JTextField();
        this.field.addKeyListener(this);

        // mise en forme du texte
        this.label = new JLabel("Le JLabel.");
        this.label.setFont(new Font("Tahoma", Font.ITALIC, 15));
        this.label.setForeground(Color.BLUE);
        this.label.setHorizontalAlignment(JLabel.CENTER);

        this.botContainer = new JPanel();
        this.botContainer.setLayout(new BorderLayout());
        this.botContainer.add(field, BorderLayout.EAST);
        this.botContainer.add(bouton, BorderLayout.WEST);

        this.container = new JPanel();
        this.container.setLayout(new BorderLayout());
        this.container.add(botContainer, BorderLayout.SOUTH);
        this.container.add(label, BorderLayout.NORTH);
        this.container.add(panneau, BorderLayout.CENTER);

        this.setContentPane(this.container);
        this.setVisible(true);
        this.mainLoop();
    }

    public void mainLoop() {
        //Image offscreen = createImage(Fenetre.LARGEUR, Fenetre.HAUTEUR);
        Image offscreen = createImage(panneau.getWidth(), panneau.getHeight());

    	while(true) {

            boule.update();
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

    public void actionPerformed(ActionEvent event) {
        boutonCompteur++;
        //this.label.setForeground(Color.BLUE);
        this.label.setText("You clicked "+boutonCompteur+" times.");
    }

    public void keyPressed(KeyEvent event) {}
    public void keyReleased(KeyEvent event) {}

    public void keyTyped(KeyEvent event) {
        this.label.setForeground(Color.RED);
        this.label.setText(KeyEvent.getKeyText(event.getKeyCode()) +" : "+ event.getKeyChar());
    }
}
