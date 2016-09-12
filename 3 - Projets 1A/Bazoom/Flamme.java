//Numero 7 :
//On importe ce dont on a besoin :
import java.awt.Graphics;
import java.awt.Image;


import javax.swing.ImageIcon;
// Flamme lance par le Personnage.
// Comme le personnage et les monstres a peu pres.
public class Flamme {
	
	private int x,y;
	int largeur =40;
	int hauteur = 40;
	ImageIcon ico;
	Image img ;
	String Orientation;
	
	
	public Flamme(int x1, int y1, String o){
		Orientation = o;
		if(o == "haut"){
			x=x1+55; 
			y=y1-40;
		}
		if(o == "bas"){
			x=x1+55; 
			y=y1+135;
		
		}
		if(o == "droite"){
			x=x1+115; 
			y=y1+55;
		}
		if(o == "gauche"){
			x=x1-10; 
			y=y1+55;
		}
		
		
		ico = new ImageIcon("flamme"+o+".png");
		img = ico.getImage();  
		
	}
	
	
	public void tick(){
		if(Orientation == "haut")
			y-=10;
		if(Orientation == "bas")
			y+=10;
		if(Orientation == "gauche")
			x-=10;
		if(Orientation == "droite")
			x+=10;
		
		
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


	// Les deux collisions possibles de la flamme.
	public boolean collision(Monstre m){
		if(y<m.getY()+m.hauteur && y+hauteur>m.getY() && x+largeur< m.getX()+m.largeur && x>m.getX()){
			
			return true;
		}else{
			return false;
		}
	}
	
	
	/*public boolean collision2(){
		if(y<Scene.p1.y+Scene.p1.epaisseur && y+hauteur>Scene.p1.y && x+largeur< Scene.p1.x+Scene.p1.epaisseur && x>Scene.p1.x){
			return true;
		}else{
			return false;
		}
	}*/
	
	public boolean collisionM(Monstre2 m){
		if(y<m.getY()+m.hauteur && y+hauteur>m.getY() && x+largeur< m.getX()+m.largeur && x>m.getX()){
			
			return true;
		}else{
			return false;
		}
	}
	
	public void draw(Graphics g){
		g.drawImage(img,x ,y , null);
	}
    
    
    
    
}
