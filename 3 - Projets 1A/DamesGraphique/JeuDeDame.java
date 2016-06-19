import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JeuDeDame {
	
	public static void main (String args[]) throws Exception {
        int jeu = 0;
		Tableau tableau = new Tableau();
		KeyListener keyListener = new KeyListener() {
			public void keyPressed(KeyEvent keyEvent) {tableau.deplaceCurseur(keyEvent);}
			public void keyReleased(KeyEvent keyEvent) {}
			public void keyTyped(KeyEvent keyEvent) {}
		};
		Affichage affichage = new Affichage();
		affichage.afficherTableau(tableau);
		affichage.setFocusable(true);
		affichage.frame.addKeyListener(keyListener);
		affichage.afficherTableau(tableau);
		System.out.println(tableau.curseur.x + "  " + tableau.curseur.y);
		while (!tableau.finDuJeu) {
			affichage.afficherTableau(tableau);
			if (!tableau.finDePartie()) {
				jeu++;
				if (jeu > 50) {												// Prise en compte de la pause de l'affichage pour initialisé affichage.frameEnd
					affichage.frameEnd.addKeyListener(keyListener);			// Et permettre de stopper le jeu en appuyant sur echap
				}
			}
		} 
		System.exit(0);
	}
}

