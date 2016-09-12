//Numero 5 :
// On importe ce qu'on a besoin.
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Monstre {
    
    //Comme pour le personnage. Ici le private est très important car sinon les monstres auront les memes coordonnes, orientations.
    
	private int x,y;
	static int largeur = 100;
	static int hauteur = 70;
	
	private String orientation="face";// Orienation pour l'image affichee.
	private String t="bas";// Orientation pour le tire.
	
	
	public Monstre(int x,int y){
		this.x=x;
		this.y=y;
		
	}
	//On dessine en fonction de son orientation.
	public  void draw(Graphics g){
		g.drawImage(new ImageIcon(""+this.orientation+".png").getImage(),x,y,null);
	}
    //Getter et Setter.
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
    
    // Methode permettant le deplacement aleatoire grace au Math.random.
	public void deplacement(){
		int r = (int)(Math.random()*4);
		if( r==0){
			x+=20;
			t="droite";
		}
		if( r==1){
			x-=20;
			t="gauche";
		}
		if( r==2){
			y+=20;
			t="bas";
			this.orientation="face";
		
		}
		if( r==3){
			y-=20;
			t="haut";
			this.orientation="dos";
		}
		
	}
	//Methode permettant le tire du monstre en fonction de son orientation.
	public void tire(){
		
		if( t=="haut")
			Scene.c.ajouterFlamme2(new Flamme2(x-30,y,t));
		else if(  t=="bas")
			Scene.c.ajouterFlamme2(new Flamme2(x-25,y-65,t));
		else if(  t=="droite")
			Scene.c.ajouterFlamme2(new Flamme2(x-50,y-40,t));
		else
			Scene.c.ajouterFlamme2(new Flamme2(x,y-35,t));
		
		
	}
	// Pareil que pour le personnage.
	public  void mur(){
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
