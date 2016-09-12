//Numero 3 :
// On importe les classes utiles au Keylistener
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener {
	static int p=0; // Variable qui gère le lancement du jeu. On a gerer le fait que l'on puisse appuyer sur p qu'une fois.
	
	public void keyPressed(KeyEvent e) {
		
        /* Si le joueur appuye sur la touche z,q,s,d  le personnage se deplace dans la direction indiquee et son orientation change. 
           Ce qui influera sur la direction de son projectile.
        */
        if (e.getKeyChar() == 'z'){
			Scene.p1.Orientation="haut";
			Scene.p1.y-=49;
			
			
		}else if (e.getKeyChar() == 's'){
			Scene.p1.Orientation="bas";
			Scene.p1.y+=49;
			
			
		}else if (e.getKeyChar() == 'q'){
			Scene.p1.Orientation="gauche";
			Scene.p1.x-=49;
			
		}else if (e.getKeyChar() == 'd'){
			Scene.p1.Orientation="droite";
			Scene.p1.x+=49;
			
		}else if (e.getKeyChar() == ' '){
			Scene.c.ajouterFlamme(new Flamme(Scene.p1.x,Scene.p1.y,Scene.p1.Orientation));
			
						
		// Petite bonus pour se rajouter des vies.	
		}else if (e.getKeyChar() == 'k'){
			Scene.p1.vie++;
			
						
			
		}else if (e.getKeyChar() == 'p'){
			if(p==0){
			Scene.c.ajouterMonstre(new Monstre((int) (Math.random()*1100),(int) (Math.random()*500)));
			p++;
			}
		}
	}

	// Pareil mais quand on reste appuye sur la touche.
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'z'){
			Scene.p1.Orientation="haut";
			Scene.p1.y-=10;
			
			
		}else if (e.getKeyChar() == 's'){
			Scene.p1.Orientation="bas";
			Scene.p1.y+=10;
			
			
		}else if (e.getKeyChar() == 'q'){
			Scene.p1.Orientation="gauche";
			Scene.p1.x-=10;
			
		}else if (e.getKeyChar() == 'd'){
			Scene.p1.Orientation="droite";
			Scene.p1.x+=10;
			
		}
		
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}

}
