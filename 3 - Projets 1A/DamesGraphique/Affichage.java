import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.Panel;
import java.awt.Font;
import java.awt.*;

public class Affichage extends Panel {					// Nous nous sommes tres largement inspiré de l'affichage fourni dans le TP Fourmi, et ce type de code n'etant pas au programme,
														// Nous n'avons pas jugé important de commenter cette classe, qui n'est pas totalement issue de notre travail
    public static final int RESOLUTION = 95;			// (Il se résume en une succession d'affichages de differentes formes geometriques et de textes)
    public static final int SIZE = 100;
	public static JFrame frame = null;
	public static JFrame frameEnd = null;
	public static Affichage world = null;
    public static int sleepTime = 20;
    public static BufferedImage image = null;
	public static boolean init = false;
	public static boolean initEnd = false;
    public BufferedImage worldImage;
    
    public Affichage () {}
    
    public static void drawCell(Graphics g, Tableau tableau, int x, int y) {
		if (tableau.monde[x][y] == 1) {
			//g.setColor(new Color(159,16,1));
			//g.setColor(new Color(139,108,66));
			g.setColor(new Color(30,30,30));
			g.fillOval(RESOLUTION * x + 6, RESOLUTION * y + 6, RESOLUTION - 11, RESOLUTION - 11); 
		} else if (tableau.monde[x][y] == 2) {
			g.setColor(new Color(220,220,220));
			g.fillOval(RESOLUTION * x + 6, RESOLUTION * y + 6, RESOLUTION - 11, RESOLUTION - 11);
		} else if (tableau.monde[x][y] == 3) {
			g.setColor(java.awt.Color.GRAY);
			g.fillOval(RESOLUTION * x + 2, RESOLUTION * y + 2, RESOLUTION - 2, RESOLUTION - 2);
			g.setColor(java.awt.Color.LIGHT_GRAY);
			g.fillOval(RESOLUTION * x + 6, RESOLUTION * y + 6, RESOLUTION - 10, RESOLUTION - 10);
		} else if (tableau.monde[x][y] == 4) {
			g.setColor(java.awt.Color.GRAY);
			g.fillOval(RESOLUTION * x + 2, RESOLUTION * y + 2, RESOLUTION - 2, RESOLUTION - 2);
			g.setColor(java.awt.Color.BLACK);
			g.fillOval(RESOLUTION * x + 6, RESOLUTION * y + 6, RESOLUTION - 10, RESOLUTION - 10);
		}
    }
    
    public static void afficherTableau (Tableau tableau) {
		int xSize = tableau.monde.length;
		int ySize = tableau.monde[0].length;
		if (!init) {
			frame = new JFrame ("Jeu de dame");
			frame.setSize((int)(12.35 * RESOLUTION), (int)(10.65 * RESOLUTION));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			image = new BufferedImage(RESOLUTION * (xSize + 2), RESOLUTION * ySize + 2, BufferedImage.TYPE_INT_RGB);
			world = new Affichage(image);
			frame.add(world);
			frame.setVisible(true);
			init = true;
		}	
		if (tableau.finDePartie()) {
			Graphics g = image.getGraphics();
			g.setColor(java.awt.Color.WHITE);
			g.fillRect(0, 0, RESOLUTION * xSize + 2, RESOLUTION * ySize + 2);
			/* on dessine les cases */
			for (int i = 0; i < 10; i++) {
				for (int k = i%2; k < 10; k += 2) {
					//g.setColor(new Color(240,195,0));
					g.setColor(new Color(230,230,230));
					g.fillRect(RESOLUTION * k + 1, RESOLUTION * i + 1, RESOLUTION, RESOLUTION);
				}
				for (int k = 1 - i%2; k < 10; k+= 2) {
					//g.setColor(new Color(167,103,10));
					g.setColor(new Color(100,100,100));
					g.fillRect(RESOLUTION * k + 1, RESOLUTION * i + 1, RESOLUTION, RESOLUTION);
				}
			}
			/* on dessine la bordure */
			g.setColor(java.awt.Color.GRAY);
			g.drawRect(0, 0, RESOLUTION * xSize + 1, RESOLUTION * ySize + 1);
			/* on dessine le curseur*/
			if (tableau.curseur.select) {
				g.setColor(new Color(60,200,60,140));
				g.fillRect(1 + RESOLUTION * tableau.curseur.x, 1 + RESOLUTION * tableau.curseur.y, RESOLUTION, RESOLUTION);
			} else if (!tableau.curseur.select) {
				g.setColor(new Color(60,200,60,100));
				g.fillRect(1 + RESOLUTION * tableau.curseur.x, 1 + RESOLUTION * tableau.curseur.y, RESOLUTION, RESOLUTION);
				g.drawRect(2 + RESOLUTION * tableau.curseur.x, 2 + RESOLUTION * tableau.curseur.y, RESOLUTION - 2, RESOLUTION - 2);
			}
			/* et on dessine les pions */
			for (int i = 0; i < ySize; i++) {
				for (int j = 0; j < xSize; j++) {
					drawCell(g, tableau, i, j);
				}
			}
			/* on dessine sur le cote les pions morts */
			int[] pionsMort = tableau.trouvePionMort();
			
			/* si un pion est selectionne, on dessine les cases representant ses actions possibles */
			if (tableau.curseur.select) {
				if (tableau.curseur.pionSelect != -1) {
					for (int i = 0; i < tableau.pions[tableau.curseur.pionSelect].deplacementPossible.length; i++) {
						g.setColor(new Color(255,0,0,80));
						g.fillRect(1 + RESOLUTION * tableau.pions[tableau.curseur.pionSelect].deplacementPossible[i][0], 1 + RESOLUTION * tableau.pions[tableau.curseur.pionSelect].deplacementPossible[i][1], RESOLUTION, RESOLUTION);
						g.setColor(java.awt.Color.RED);
					}
				}	
			}
			
			/* on affiche le panneau des scores à droite du plateau */
			g.setColor(new Color(50,50,50));
			g.fillRect(10 * RESOLUTION + 1, 1, 2 * RESOLUTION, 10 * RESOLUTION);
			g.setColor(new Color(230,230,230));
			g.fillRect((int)(10.1 * RESOLUTION + 1), (int)(0.1 * RESOLUTION), (int)(1.8 * RESOLUTION), (int)(9.8 * RESOLUTION));
			
			/* on affiche le nombre de tours joués */
			g.setFont(new Font("default",Font.BOLD,26));
			g.setColor(java.awt.Color.BLACK);
			g.drawString("Tour : ", (int)(10.4 * RESOLUTION), RESOLUTION);
			g.drawString(Integer.toString(tableau.tourDeJeu), (int)(11.4 * RESOLUTION), RESOLUTION);
			g.setFont(new Font("default",Font.BOLD,22));
			if (tableau.tourDeJeu%2 == 1) {
				g.drawString("Joueur blanc",(int)(10.3 * RESOLUTION), 2 * RESOLUTION);
			} else {
				g.drawString("Joueur noir", (int)(10.3 * RESOLUTION), 2 * RESOLUTION);
			}
			
			/* on affiche le nombre de pion restant */
			int[] tabPionsMort = tableau.trouvePionMort();
			g.setFont(new Font("default",Font.BOLD,22));
			g.drawString("Pions blancs", (int)(10.3 * RESOLUTION), 3 * RESOLUTION);
			g.drawString("restants : ", (int)(10.3 * RESOLUTION), (int)(3.3 * RESOLUTION));
			g.drawString(Integer.toString(20 - tabPionsMort[0]), (int)(11.4 * RESOLUTION), (int)(3.3 * RESOLUTION));
			g.drawString("Pions noirs", (int)(10.3 * RESOLUTION), 4 * RESOLUTION);
			g.drawString("restants : ", (int)(10.3 * RESOLUTION), (int)(4.3 * RESOLUTION));
			g.drawString(Integer.toString(20 - tabPionsMort[1]), (int)(11.4 * RESOLUTION), (int)(4.3 * RESOLUTION));
			
			
		} else if (!tableau.finDePartie()) {
			String couleurGagnant = "0";
			int i = 0;
			while (couleurGagnant.equals("0")) {
				if (tableau.pions[i].viv) {
					if (tableau.pions[i].couleur == 1) {
						couleurGagnant = new String ("blanc");
					} else {
						couleurGagnant = new String ("noir");
					}
				}
				i++;
			}
			if (!initEnd) {
				frameEnd = new JFrame ("Jeu de dame");
				frameEnd.setSize(800, 400);
				frameEnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				image = new BufferedImage(800, 400, BufferedImage.TYPE_INT_RGB);
				world = new Affichage(image);
				frameEnd.add(world);
				frameEnd.setVisible(true);
				frame.setVisible(false);
				initEnd = true;
			}	
			couleurGagnant = "Joueur " + couleurGagnant + " gagne !";
			Graphics g = image.getGraphics();
			g.setColor(java.awt.Color.WHITE);
			g.fillRect(1,1,800,400);
			g.setColor(java.awt.Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 38));
			g.drawString(couleurGagnant, 200, 170);
			g.setFont(new Font("default",Font.ITALIC, 20));
			g.drawString("Appuyez sur Echap", 280, 230);
		}
		/* lorsque l'image est calculée, on rafraichit l'affichage */
		world.paint(world.getGraphics());

		/* on limite artificiellement les FPS */
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}
	}
	
	public Affichage(BufferedImage image) {
        this.worldImage = image;
    }
	
	public void paint(Graphics g) {
        //super.paint();
        g.drawImage(worldImage, 0, 0, this);
    }
    
    	
}

