public class TestJeuDeDames {

	public static void main(String[] args) {
		System.out.println("Le joueur 1 avec les pions noirs (ici des +) commence. Tapez un chiffre et c'est parti.");
		System.out.println();
		
		Plateau1 jeu = new Plateau1("On joue !");		
		System.out.println("C'est fini !!");		
	}

}


Les méthodes dans la classe Plateau1 :

- initialisationPlateau : classe qui initialise l'échiquier avec les pions noirs et blancs dans leurs positions initiales.

- affichageEchiquier : affiche l'échiquier avec les pions

- saisiCoordonneesX et saisiCoordonneesX : Permet de rentrer la valeur de la coordonnée du pion (x : la ligne, y : la colonne)

- selectionnerPion : vérifie si on a sélectionné un pion

- selectionnerDame : vérifie si on a sélectionné une dame

- deplacementPossiblePion : verifie si on peut déplacer le pion

- deplacementPossibleDame : vérifie si on peut déplacer la dame

- pionPasseDame : change le pion en dame si le pion arrive sur la première ligne de l'échiquier du "camp" adverse

- deplacer : deplace le pion ou dame dans sa nouvelle case

- gagner : vérifie si un des deux joueur a gagné 


Le constructeur de la classe Plateau1 :

- Plateau1 : permet d'itérer le jeu grâce aux méthodes précèdentes