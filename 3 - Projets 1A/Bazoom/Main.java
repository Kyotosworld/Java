// Numero 1 :
//On importe la classe JFrame utile pour la creation de fenetre.
import javax.swing.JFrame;

/*   Dans cette classe on cree la fenetre en ajoutant la scene ( voir plus loin ) le keyListener ( permettant de lire instantanement la touche
     appuyee) ainsi que la trame ( permettant d'actualiser ).
*/

public class Main {

	static Scene scene = new Scene(); // On construit une scene.
	static Clavier clavier = new Clavier(); // On construit un clavier keyListener.
	static int i;  // variable utile a l'animation ( voir plus loin ).
	static int n;  // variable comptant les actualisations realise par la methode Trame ( ci dessous ).
	public static void main(String[] args) {
		
		JFrame Fenetre = new JFrame(); // Creation d'un objet JFrame pour la fenetre.
		Fenetre.setTitle("Bazoom"); // Titre de la fenetre.
		Fenetre.setSize(1366, 768);		// Taille de la fenetre.
		Fenetre.setLocationRelativeTo(null); // Mettre la fenetre au milieur de l'ecran.
		Fenetre.setResizable(false); // Ne pas pouvoir changer la taille de la fenetre.
		Fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Quitter la fenetre en appuyant sur la croix.
		Fenetre.addKeyListener(clavier); // On ajoute a notre fenetre le clavier keylistener.
		
		Fenetre.add(scene);		// On ajoute la scene.
		
		Fenetre.setVisible(true); // Affiche la scene
		
		Trame(); //On lance la boucle d'animation.
		
		
	}
	
	public static void Trame(){
		while(true){
			
			
			
			try {Thread.sleep(12);} // Temps d'acutalisation de 12ms.
			catch (InterruptedException e) {e.printStackTrace();}
			
		i++; // Ajoute 1 a la variable i a chaque actualisation.
		n++; // De meme.
		Scene.c.tick(); // Applique la methode tick prevener du controller c cree dans la scene ( voir apres ).
		scene.repaint(); // Permet la reactualisation.
		}
		
	}

}
