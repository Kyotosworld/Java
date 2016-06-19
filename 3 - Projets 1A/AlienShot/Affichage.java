import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.Panel;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Affichage extends Panel {

    public static JFrame frame = null;
    private static BufferedImage image = null;
    private static Affichage world = null;
    private static boolean init = false;
    private static int resolution;
   // private static int largeurFenetre;
  //  private static int hauteurFenetre;
  //MEDIFIE §§§§
    public static int sleepTime = 1;
    
    private BufferedImage worldImage;
    
    private Affichage() {}
    
    public Affichage(BufferedImage image){
        this.worldImage = image;
    }
    
   
    
    public static void drawCell(Graphics g, Plateau simulation, int x, int y){
        
       switch(simulation.plateau[x][y]){
        case 0:       
        	//g.setColor(new Color(0,0,0,0));
			g.setColor(Color.BLACK);
        	break;
        case 1:
            g.setColor(Color.WHITE);
            break;
        case 2:
        	g.setColor(Color.WHITE);
            break;
        case 11:
            g.setColor(Color.red);
            break;
        case 22:
            g.setColor(Color.yellow);
            break;
        default:
        	g.setColor(Color.black);
          	break;
        }
        
        g.fillRect(resolution*x,resolution*y,resolution,resolution);
    }
    
    /*
     * Méthode pour afficher la barre de vie des joueurs
     * Sous la forme d'une barre  dans un rectangle noir
     * La couleur change en fonction de la vie du joueur
     */
    public static void afficherVie(Graphics g, Plateau simulation){
    	
    	//On dessine un rectangle blanc en dessous de l'aire de jeu
    	g.setColor(Color.WHITE);
    	g.fillRect(0, resolution*simulation.y+1, resolution*simulation.x+1, 100);
    	
    	//On dessine la vie du joueur 1
    	g.setColor(Color.black);
    	g.drawRect(resolution*simulation.x/2 - 300, resolution*simulation.y + 25, 200, 20); //Le contour du rectangle
    	
    	if(simulation.j1.vie > 50){
    		g.setColor(Color.GREEN);
    	}else if(simulation.j1.vie <= 50){
    		if(simulation.j1.vie > 20){
        		g.setColor(Color.ORANGE);
    		}else{
    			g.setColor(Color.red);
    		}
    	}
    	
    	g.fillRect(resolution*simulation.x/2 - 299, resolution*simulation.y +26, simulation.j1.vie*2-1, 19); //On rempli la barre de vie en fonction de la vie du j1
    	
    	//On dessine la vie du joueur 2
    	g.setColor(Color.BLACK);
    	g.drawRect(simulation.x*resolution/2 + 100, resolution*simulation.y +25, 200, 20); //Le contour du rectangle
    	
    	
    	if(simulation.j2.vie > 50){
    		g.setColor(Color.GREEN);
    	}else if(simulation.j2.vie <= 50){
    		if(simulation.j2.vie > 20){
        		g.setColor(Color.ORANGE);
    		}else{
    			g.setColor(Color.red);
    		}
    	}
    	g.fillRect(simulation.x*resolution/2 + 101, resolution*simulation.y +26, simulation.j2.vie*2-1, 19);
    }
    
    
    /*
     * Un méthode similaire à la méthode afficherVie mais pour afficher l'énergie
     * Sous la forme d'une barre jaune
     */
    public static void afficherEnergie(Graphics g, Plateau simulation){
    	//On dessine les contours
    	g.setColor(Color.BLACK);
    	
    	g.drawRect(simulation.x*resolution/2 - 300, resolution*simulation.y + 60, 200, 20);
    	g.drawRect(resolution*simulation.x/2 + 100, resolution*simulation.y + 60, 200, 20);
    	
    	//On remplit les barres d'énergie en jaune
    	g.setColor(Color.YELLOW);
    	
    	g.fillRect(simulation.x*resolution/2 - 299, resolution*simulation.y + 61, simulation.j1.energie*4 - 1, 19);//celle du j1
    	g.fillRect(simulation.x*resolution/2 + 101, resolution*simulation.y + 61, simulation.j2.energie*4 - 1, 19); //celle du j2
    }
    
    
    
    /*
     * Un méthode pour afficher tous les éléments de texte qui doivent apparaitre à l'écran
     */
    public static void afficherTexte(Graphics g, Plateau simulation){
    	//On crée une nouvelle police d'écriture qui nous servira pour écrire tous les textes
    	Font police = new Font("Courier", Font.BOLD, 15);
    	
    	//On écrit les textes en noir et on charge la nouvelle police d'écriture
    	g.setColor(Color.BLACK);
    	g.setFont(police);
    	
    	//Affichage du mot "vie" au milieu de l'écran entre les 2 barres de vie
    	g.drawString("Vie", simulation.x*resolution/2-15, simulation.y*resolution + 40);
    	
    	//Affichage du mot "Energie" juste en dessous du mot "Vie", entre les 2 barres d'énergie
    	g.drawString("Energie", simulation.x*resolution/2 - 30, simulation.y*resolution + 75);
    	
    	//Affiche du mot "Joueur 1" au dessus de la barre de vie du j1 (celle de gauche)
    	g.drawString("Joueur 1", simulation.x*resolution/2 - 235, simulation.y*resolution + 17);
    	
    	//Afichage du mot "Joueur 2" au dessus de la barre de vie du j2 (celle de droite)
    	g.drawString("Joueur 2", simulation.x*resolution/2 + 165, simulation.y*resolution + 17);
    }
    
    
    /*
     * Méthode pour afficher le monde
     * Ouvre une fenetre si elle n'est pas déjà ouverte
     * Appelle drawCell pour colorier les cases
     * Appelle afficherVie pour dessiner la vie des joueurs
     * Appelle afficherEnergie pour afficher l'énergie des joueurs
     * Oblige un temps d'attente à la fin (réglable grace à la variable sleepTime)
     */
    public static void afficherMonde(Plateau simulation){
        
    	if(!init){
            frame = new JFrame("ALIEN SHOT");
            frame.setSize(resolution*(simulation.x+5),resolution*simulation.y + 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
                        
            image = new BufferedImage(resolution*simulation.x,resolution*simulation.y+100, BufferedImage.TYPE_INT_RGB);
            world = new Affichage(image);
            frame.add(world);
            frame.setVisible(true);
            init = true;
        }
        
        Graphics g = image.getGraphics();
        
        //On affiche le PlateauBis de jeu en coloriant toute les cases du tableau de notre objet simulation grace à la méthode drawCell
        
        
        for(int i=0;i<simulation.x; i++){
        	for(int j=0; j<simulation.y;j++){
        		drawCell(g, simulation, i, j);
        	}
        }
        
        //g.setColor(Color.RED);
        //g.drawRect(0, 0, resolution*simulation.x, resolution*simulation.y);
        
        //On affiche la vie des 2 joueurs
        afficherVie(g, simulation);
        
        //On affiche l'énergie des deux joueurs
        afficherEnergie(g, simulation);
        
        //On affiche tous les textes
        afficherTexte(g, simulation);
        
        world.paint(world.getGraphics());
        
        try{
        	Thread.sleep(sleepTime);
        }catch (InterruptedException e) {}
        
        
    }
    
    public static void fermerFenetre(){
    	frame.setVisible(false);
    }
    
    public static void victoire(Plateau simulation){
    	frame = new JFrame("ALIEN SHOT");
    	frame.setSize(500, 300);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        image = new BufferedImage(resolution*simulation.x,resolution*simulation.y+100, BufferedImage.TYPE_INT_RGB);
        world = new Affichage(image);
        frame.add(world);
        frame.setVisible(true);
        
        Graphics g = image.getGraphics();
        
        g.setColor(Color.BLACK);
        g.fillRect(0,0,500, 300);
        
        Font police = new Font("Courier", Font.BOLD, 35);
    	g.setColor(Color.WHITE);
    	g.setFont(police);
    	
    	if(simulation.j1.vie <= 0){
    		g.drawString("JOUEUR 2 A GAGNE !", 50, 100);
    	}else if (simulation.j2.vie <= 0){
    		g.drawString("JOUEUR 1 A GAGNE !", 50, 100);
    	}else{
    		g.drawString("ERREUR !", 50, 100);
    		System.out.println("Vie j1 = " + simulation.j1.vie);
    		System.out.println("Vie j2 = " + simulation.j2.vie);
    	}
    	
    	world.paint(world.getGraphics());

    }
    public void paint(Graphics g) {
        //super.paint();
        g.drawImage(worldImage, 0, 0, this);
    }
    
    public static void setResolution(int resolution){
    	Affichage.resolution = resolution;
    }
 
    
}

