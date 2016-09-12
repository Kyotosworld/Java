/**
* Se charge d'interagir avec l'utilisateur et d'effectuer le deroulement du programme : Instanciations, affichages, etc.
*/
import java.util.Scanner;

public class Main {
	/**
	* Nombre de lignes a afficher
	*/	
	private static int nb = 10; // superieur a 5 pour entrer dans a boucle voir plus loin
	// Creation de la fenetre d'affichage des courbes (Visualisation visu creee en meme temps)
	private static Fenetre f = new Fenetre();
	/**
	* Coordonnee en x du point de depart
	*/
	private static double x0;
	/**
	* Coordonnee en y du point de depart
	*/
	private static double y0;

	public static void main(String[] args) {
		// Dialogue avec l'utilisateur
			Scanner sc = new Scanner(System.in); // Scanner pour les double
			Scanner sc2 = new Scanner(System.in); // Scanner pour les int
			System.out.println("Entrez les deux valeurs des coordonnees du point de depart :");
			System.out.println("x0=");
			x0 = sc.nextDouble();
			System.out.println("y0=");
			y0 = sc.nextDouble();
			while (nb>5) {
				System.out.println("Combien de courbes voulez vous visualiser ? (MAX=5)");
				nb = sc2.nextInt();
			}
			System.out.println("Afficher : numerique (0), analytique(1), les deux (2)");
			int choix = sc2.nextInt();
			
			
		// FIN
		// Ajout a l'affichage des choix
			switch(choix) {
				case 0:
					numerique();
					break;
				case 1:
					analytique();
					break;
				default:
					numerique();
					analytique();
					break;
			}
		// FIN
		// Affichage
			f.visu.repaint();
			// Ouvre la fenetre
	        f.setVisible(true);
        // FIN
	        System.out.println("Cliquez sur le bouton pour calculer la circulation !");
	}

	/**
	* Ajoute a la visualisation les courbes analytiques
	*/
	private static void analytique() {
		for (int i=0; i<nb; i++) {
			// Initialisation de la ligne de champ
			LigneChamp lc = new LigneChamp((double)(i+1)*2/10,i);
			lc.init_A(y0,f.visu.yMax,0.1);
			// Ajout de la ligne de champ a l'affichage
			f.visu.addLine(lc);
		}
	}
	/**
	* Ajoute a la visualisation les courbes numeriques
	*/
	private static void numerique() {
		for (int i=0; i<nb; i++) {
			// Initialisation de la ligne de champ
			LigneChamp lc = new LigneChamp((double)i*2/10,i);
			lc.init_N(x0+i, y0);
			// Ajout de la ligne de champ a l'affichage
			f.visu.addLine(lc);
		}
	}

}
