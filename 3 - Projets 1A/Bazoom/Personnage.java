//Numero 4 :
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Personnage {
	static int x,y; // abscisse, ordonne.
	static int epaisseur = 140; 
	static int m =0; //Variable utilise pour la mort du personnage. On aurait pu mettre un boolean.
	static int vie =1;
	static String Orientation="bas"; // Initialisation de l'orientation vers le bas.
	
	
	
	public Personnage(int Startx,int Starty){
		x = Startx;
		y = Starty;
	}
	//Methode qui dessine le personnage en fonction de son orientation et grace a la variable i on cree une petite animation.
	public static void Draw(Graphics g){
		if(Main.i>50&& Main.i<101){
			g.drawImage(new ImageIcon(""+Orientation+".png").getImage(), x, y, null);
			if(Main.i==100)
			Main.i=0;
		}else{
			g.drawImage(new ImageIcon(""+Orientation+"2"+".png").getImage(), x, y, null);
		}
			
	}
	
    
    // Gere la collision avec la limite de la carte.
	public static void mur(){
		if(x >1175)
			x = 1175;
		if(x<40)
			x = 40;
		if(y >565)
			y =565;
		if(y<30)
			y = 30;
		
	}
	
	
	
	

}
