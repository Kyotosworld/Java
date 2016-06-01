import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

/* Cette mÃ©thode contient le main dans lequel on appelle toute les fonctions dÃ©finis dans les class
 * Le KeyListener se trouve Ã©galement dans cette class
 * */

public class Test {
	
	public static void main (String args[]) {
		final int LARGEUR_MONDE = 140; //largeur du monde (en case) 
		final int HAUTEUR_MONDE = 80; //hauteur du monde (en case) NB PAIR OBLIGATOIR !!!!!
		final int RESOLUTION = 7;
		final int DELAI_GAIN_ENERGIE = 40;
		final int J1_HAUT = KeyEvent.VK_Z;
		final int J1_BAS = KeyEvent.VK_S;
		final int J1_TIR = KeyEvent.VK_SPACE;
		final int J2_HAUT = KeyEvent.VK_UP;
		final int J2_BAS = KeyEvent.VK_DOWN;
		final int J2_TIR = KeyEvent.VK_NUMPAD0;
        final int FERMER_FENETRE = KeyEvent.VK_ESCAPE;
		
		boolean jeuOuSimulation; //true si jeu, false si simulation
		
		
		Plateau monde = new Plateau(LARGEUR_MONDE,HAUTEUR_MONDE,(HAUTEUR_MONDE)/2,HAUTEUR_MONDE/2);
        Affichage.setResolution(RESOLUTION);
        
    	
    	String[] choix = {"Jouer", "Simulation de jeu"};   
		JOptionPane jop1;      
		jop1 = new JOptionPane();
		ImageIcon img = new ImageIcon("regle.jpg");
		int reponse = jop1.showOptionDialog(null, null, "ALIEN_SHOT", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, img, choix,choix[1]);    
		      
         if(reponse == 0){ //si on est en mode jeu 
			 
			 Affichage.sleepTime = 1;
			 Joueur.UNITE_DEPLACEMENT = 3; //On modifie le longeur du dÃ©placement Ã©lÃ©mentaire qui est diffÃ©rent en simulation
			 jeuOuSimulation = true;
			 
		 }else if(reponse == 1){ //si on est en mode simulation
			 
			 Affichage.sleepTime = 10; //on diminue la vitesse de rafraichissement
			 Joueur.UNITE_DEPLACEMENT = 1;
			 jeuOuSimulation = false;
			 
		 }else{//Si on ne clique pas sur jouer ou simulation, on ne fait rien, on ferme la fenetre
			 
			 jeuOuSimulation = true;
			 Affichage.fermerFenetre();
			 
		 }
		
		monde.genereMonde();
		Affichage.afficherMonde(monde);
    	Affichage.frame.setIconImage(new ImageIcon("icone.gif" ).getImage()); //permet de modifier l'icone du jeu
    	Affichage.frame.setFocusable(true);
    	
		KeyListener keyListener = new KeyListener(){
		
			
			public void keyTyped( KeyEvent e){
				
				
				
			}
			
			public void keyPressed(KeyEvent e){
			
			if(jeuOuSimulation == true){//On ne peut appuyer sur les touches que si on est en mode jeu 
					
				switch(e.getKeyCode()){
				
				case J1_HAUT : 
					
					monde.j1.deplace(monde,true);
					break;
				case J1_BAS : 
					
					monde.j1.deplace(monde,false);
					break;
				case J1_TIR : 
					
					monde.j1.tir(monde);
					break;
					
				case J2_HAUT : 
					
					monde.j2.deplace(monde,true);
					break;
				case J2_BAS : 
					
					monde.j2.deplace(monde,false);
					break;
				case J2_TIR : 
					
					monde.j2.tir(monde);
					break;	
				case FERMER_FENETRE : //Si on appui sur echap, on quitte le jeu
                
                    Affichage.fermerFenetre();
				
				
				}
			}	
				
			}
			
			public void keyReleased(KeyEvent e){
				
				
				
			}
			
			
		};
		
		
		
		Affichage.frame.addKeyListener(keyListener);
		
		int k = 0;
		int i = 0;
		
		
		

        while(monde.j1.vie > 0 && monde.j2.vie >0){
        	
            Affichage.frame.requestFocus(); //Permet de lire les touches du clavier meme si on change de fenetre ou qu'on dÃ©place la fenetre
            
            if(jeuOuSimulation == false){ //si on est en mode simulation on execute les mÃ©thodes de simulation
				
				monde.j1.simDeplace(monde);
				monde.j2.simDeplace(monde);
				
				monde.j1.simTir(monde);
				monde.j2.simTir(monde);
			}
            
            
        	monde.j1.deplacerTir(monde);
        	monde.j2.deplacerTir(monde);
        	
        	monde.j1.recevoirDegat(monde);
        	monde.j2.recevoirDegat(monde);
        	
        	monde.genereMonde();
        	Affichage.afficherMonde(monde);
        	
        	i++;
        	if(i-k > DELAI_GAIN_ENERGIE){ //Permet de rÃ©gler la vitesse de rechargement d'Ã©nerie Ã  chaque itÃ©ration
        		monde.j1.gainEnergie(2);
        		monde.j2.gainEnergie(2);
        		k = i;
        	}
        	
        	

        }
        
        Affichage.fermerFenetre();
        Affichage.victoire(monde);
        
        
        
        
	}
}
