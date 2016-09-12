import java.util.Scanner ;
public class RPG { //main
	public static void main (String [] args) {
		double wow = Math.random()*10;
		keyListener kListener = new keyListener();		
		Plateau monde = new Plateau (17); //Génération d'un plateau de 17 cases sur 17
		Thread song = SoundManager.music(monde.randomSong());
		monde.debutJeu(17);
		perso [] t = new perso [2];
		t = monde.selection(monde);
		perso J1test = t[0];
		perso J2test = t[1];
		int [] t2 = monde.trie(J1test.getvitesse(),J2test.getvitesse(),J1test.classe,J2test.classe);	
		perso J1 = new perso(t2[0]);
		perso J2 = new perso (t2[1]);
		song.start();
		Frame frame = new Frame (monde,J1,J2,1,1) ;
		frame.addKeyListener(kListener);
		while (J1.estvivant() && J2.estvivant()){ //Condition de maintien du jeu
			monde.jouer (monde , J1 , J2 , frame , kListener ,1); 
			if (J2.estvivant()){ 
				monde.jouer (monde , J2 , J1 , frame , kListener ,2);  		
			}	
		}
		monde.endGame=true;//Fin du jeu
		frame.repaint();
		song.stop();
		if (wow < 5) { //Petit sélectionneur de chanson
			SoundManager.play("sfx/victory.wav");
		} else {
			SoundManager.play("sfx/victory2.wav");
		}
	}
}
