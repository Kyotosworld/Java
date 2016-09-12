import java.awt.Graphics;
import java.awt.Image;


import javax.swing.ImageIcon;
//Flamme des ennemis.
//Comme la premire flamme, image change et collision avec personnage uniquement.
public class Flamme2 {
	
	private int x,y;
	int largeur =40;
	int hauteur = 40;
	ImageIcon ico;
	Image img ;
	String Orientation;
	
	
	public Flamme2(int x1, int y1, String o){
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
		
		
		ico = new ImageIcon("flamme2"+o+".png");
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


	
	
	
	
	public boolean collision2(){
		if(y<Scene.p1.y+Scene.p1.epaisseur && y+hauteur>Scene.p1.y && x+largeur< Scene.p1.x+Scene.p1.epaisseur && x>Scene.p1.x){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	public void draw(Graphics g){
		g.drawImage(img,x ,y , null);
	}
}
