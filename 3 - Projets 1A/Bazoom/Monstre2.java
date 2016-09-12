import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/*
    Ici on cree un nouveau monstre, d'image differente et de tire mutli-directionnel.
    Il apparait au niveau 10.
*/
public class Monstre2 {

	private int x,y,v;
	static int largeur = 90;
	static int hauteur = 70;
	
	private String orientation="face";
	private String t="bas";
	
	
	public Monstre2(int x,int y){
		this.x=x;
		this.y=y;
		
	}
	
	public  void draw(Graphics g){
		g.drawImage(new ImageIcon(""+this.orientation+"k.png").getImage(),x,y,null);
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
	
	public void deplacement(){
		int r = (int)(Math.random()*4);
		if( r==0){
			x+=20;
			
		}
		if( r==1){
			x-=20;
			
		}
		if( r==2){
			y+=20;
			
			this.orientation="face";
		
		}
		if( r==3){
			y-=20;
			t="haut";
			this.orientation="dos";
		}
		
	}
	
	public void tire(){
		
		
			Scene.c.ajouterFlamme2(new Flamme2(x-30,y,"haut"));
		
			Scene.c.ajouterFlamme2(new Flamme2(x-25,y-65,"bas"));
		
			Scene.c.ajouterFlamme2(new Flamme2(x-50,y-40,"droite"));
		
			Scene.c.ajouterFlamme2(new Flamme2(x,y-35,"gauche"));
		
		
	}
	
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


