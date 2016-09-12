import java.util.Scanner;
/**
* 	"Jeu" de gestion
*	Vous avez un laboratoire de Mathématiciens à gérer. Chaque mathématicien a un age, un level (sa compétence) et un salaire (lié à l'âge et au level).
*	Vous commencez avec un budget de 100 000 € pour votre laboratoire.
*	Attention, à chaque tour (une année) :
*	- Vous payez les mathématiciens de votre laboratoire (fonction de leurs salaires)
*	- Vous devez effectuer une action après avoir consulté l'état de votre laboratoire (*)
*	- Le laboratoire reçoit les subventions de l'Etat (1500€ + total_des_levels * 200€)
*   - Les mathématiciens de plus de 40ans quitteront votre laboratoire (retraite) avec 1000€ d'indemnités
*	- Une année passe (Les mathématiciens vieillissent d'un an et augmentent leur level de 1 grâce à la recherche !)
*
*	(*) Actions que vous pourrez effectuer :
*	- Payer une formation à un mathématicien au choix (coût de 1000€)
*	- Engager un nouveau mathématicien (Age entre 25 et 38 ans au hasard et level selon son age (3 ou 4))
*	- Licencier un mathématicien au choix (Indemnites de 1000€ a verser !)
*
*	@author Marie Neyret et Valentin Viennot
*/
public class Main {
	/**
*	Méthode principale : Programme en lui même
	*/
	public static void main(String[] args) {
		System.out.println("--- BIENVENUE DANS LE JEU ---");
		Scanner sc = new Scanner(System.in);
		Laboratoire lab = new Laboratoire(100000.0,5);
		int tour = 0;
		int tours = 15;
		while (tour<tours) {
			if (lab.getBudget()<0) {
				System.out.println("\n --- BUDGET INFERIEUR A ZERO : PERDU ---");
				tour=tours;
			}
			else {
				System.out.println("\n\n --- TOUR "+(tour+1)+" ---");
				lab.retraites(); // Les mathématiciens trop vieux partent à la retraite
				lab.payer(); // Les mathématiciens recoivent leur salaire
				System.out.println("\nDescription de votre laboratoire : ");
				System.out.println(lab.toString()); // Décris le labo
				/*
				- Le joueur peut payer une formation à un Mathématicien (entre dans une série de calculs) et peut être augmenter le niveau du Mathématicien
				- OU engager un nouveau mathématicien
				- OU licensier un mathématicien existant
				*/
				System.out.println("\nQue voulez-vous faire a ce tour ? ( "+(tours-tour-1)+" tour(s) restant(s) )");
				System.out.println("1 - Payer une formation a un mathematicien (prix : 1000 euros)");
				System.out.println("2 - Engager un nouveau mathematicien (25<age<38 aleatoire & level selon age)");
				System.out.println("3 - Licencier un mathematicien existant (indemnites a verser de 1000 euros)");
				System.out.println("0 - Quitter");
				int choix = sc.nextInt();
				switch (choix) {
					case 0:
						tour = tours;
						break;
					case 1:
						lab.forme();
						break;
					case 2:
						lab.embauche();
						break;
					case 3:
						lab.licencier();
						break;
				}
				lab.subventions(); // Le budget du labo est augmenté par des subventions en fonction du level des Mathématiciens présents et de leur nombre
				lab.annee(); // Une année passe
				tour++;
			}
		}
	}
}