package TP11;

import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.Panel;

/**
 * Gestionnaire d'affichage pour le jeu de la vie.
 * 
 * @author Brice Chardin <brice.chardin@insa-lyon.fr>
 */
public class AffichageFourmi extends Panel {
    private static final int MAX_RESOLUTION = 10;
    private static final int MAX_SIZE = 700;
    private static JFrame frame = null;
    private static AffichageFourmi world = null;
    private static BufferedImage image = null;
    private static boolean init = false;
    private static int resolution = 0;
    private static int sleepTime = 500;
    private BufferedImage worldImage;
    //private static long time; // pour le calcul des FPS

    private AffichageFourmi() {}

    /**
     * Affiche le contenu d'une cellule.
     * @param g l'abstraction Graphics du composant à dessiner.
     * @param monde le monde auquel appartient la cellule.
     * @param x l'abscisse de la cellule.
     * @param y l'ordonnée de la cellule.
     */
    private static void drawCell(Graphics g, Plateau simulation, int x, int y) {
        if (simulation.monde[x][y]) {
            g.fillRect(resolution * y + 1, resolution * x + 1, resolution, resolution); 
        }
    }

    /**
     * Affiche un monde. Une fois l'affichage fait, cette méthode force 
     * une attente, permettant d'avoir le temps de voir les choses.
     * La durée d'attente est configurable via setDelay.
     * @param monde le monde à afficher
     */
    public static void afficherMonde(Plateau simulation) {
        int xSize = simulation.taille;
        int ySize = simulation.taille;
        if (!init) {
            if (resolution <= 0) {
                /* on adapte la résolution à la hauteur du monde */
                resolution = MAX_SIZE / simulation.taille;
                if (resolution <= 0) {
                    resolution = 1;
                } else if (resolution > MAX_RESOLUTION) {
                    resolution = MAX_RESOLUTION;
                }
            }
            frame = new JFrame("Fourmi de Langton ");
            frame.setSize(resolution * xSize + 2, resolution * ySize + 2);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            /*frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        System.exit(0);
                    }
                }
            );*/
            image = new BufferedImage(resolution * xSize + 2, resolution * ySize + 2, BufferedImage.TYPE_INT_RGB);
            world = new AffichageFourmi(image);
            frame.add(world);
            frame.pack();
            frame.setVisible(true);
            //time = System.nanoTime();
            init = true;
        }

        Graphics g = image.getGraphics();
        /* on efface l'image */
        //g.clearRect(0, 0, resolution * xSize + 2, resolution * ySize + 2);
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, resolution * xSize + 2, resolution * ySize + 2);
        /* on dessine la bordure */
        g.setColor(java.awt.Color.GRAY);
        g.drawRect(0, 0, resolution * xSize + 1, resolution * ySize + 1);
        /* et on dessine les cellules vivantes */
        g.setColor(java.awt.Color.BLACK);
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                drawCell(g, simulation, i, j);
            }
        }
        /* et on dessine la fourmi */
        g.setColor(java.awt.Color.RED);
        g.fillRect(resolution * simulation.f.y + 1, resolution * simulation.f.x + 1, resolution, resolution);
        
        /* affichage des FPS */
        /*
        g.setColor(java.awt.Color.RED);
        long newTime = System.nanoTime();
        g.drawString(Long.toString(1000000000/(newTime - time)), 4, 14);
        time = newTime;
        */

        /* lorsque l'image est calculée, on rafraichit l'affichage */
        world.paint(world.getGraphics());

        /* on limite artificiellement les FPS */
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {}
    }

    /**
     * Modifie le temps d'attente entre deux affichages successifs.
     * @param delay le temps d'attente en millisecondes.
     */
    public static void setDelay(int delay) {
        AffichageFourmi.sleepTime = delay;
    }

    /**
     * Modifie la résolution de l'affichage.
     * @param resolution la taille des cellules en pixels.
     */
    public static void setResolution(int resolution) {
        AffichageFourmi.resolution = resolution;
    }

    public AffichageFourmi(BufferedImage image) {
        this.worldImage = image;
    }

    public void paint(Graphics g) {
        //super.paint();
        g.drawImage(worldImage, 0, 0, this);
    }
 
    public Dimension getPreferredSize() {
        return new Dimension(worldImage.getWidth(), worldImage.getHeight());
    }
}       

