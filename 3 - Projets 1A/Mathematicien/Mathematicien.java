import java.util.Scanner;
import java.lang.Math;

/**
* Objet Mathematicien
* @author Valentin Viennot
*/
public class Mathematicien {
	// variables globales
	/**
	* Niveau de compétence > 0 - Ne peut qu'augmenter
	*/
	private int level;
	private int age;
	private double salaire;

	// constructeurs
	/**
	* Constructeur par défaut : Level 0, Age 30
	*/
	public Mathematicien() {
		this(0,30,0); // Appelle le constructeur surchargé
		this.setSalaire(); // Défini le salaire automatiquement
	}
	/**
	* Constructeur prenant uniquement l'âge en paramètre (level selon l'âge)
	*/
	public Mathematicien(int age) {
		this( ((int)(age/10)) ,age,0);
		this.setSalaire();
	}
	/**
	* Constructeur surchargé complet
	*/
	public Mathematicien(int level, int age, double salaire) {
		this.level = level;
		this.age = age;
		this.salaire = salaire;
	}

	// Méthodes

	public void vieillir() {
		this.age++;
	}

	public String toString() {
		return this.age+" ans, paye "+this.salaire+" euros (Level de "+this.level+")";
	}

	/**
	* Mini jeu
	* Pose des additions aléatoires à l'utilisateur...
	* En fonction de son nombre de bonnes réponses , le level du Mathematicien est augmenté
	*/
	public void entrainer() {
		Scanner sc = new Scanner(System.in); // Entrée clavier
		System.out.println("\n*** Bienvenue à l'entrainement ***");
		int score = 0, counter = 0; // Score à zéro et compteur à zéro
		boolean estgagnant = true; // On fera plusieurs questions
		while (estgagnant&&counter<5) { // 5 questions
			int a = (int) (Math.random()*100); // Nombre a aléatoire entier entre 0 et 100
			int b = (int) (Math.random()*100); // Nombre b aléatoire entier entre 0 et 100
			int res = a+b; // résultat de l'addition de a avec b
			System.out.println("Combien font "+a+" + "+b+" ?");
			int res_utilisateur = sc.nextInt(); // On demande à l'utilisateur d'entrer son résultat
			if (res_utilisateur == res) // Si l'utilisateur a juste
				score++; // On augmente son score de 1
			else // Sinon, il a perdu
				estgagnant=false;
			counter++; // On augmente le nombre de questions
		}
		this.level+=score;
		this.setSalaire();
		System.out.println("Vous avez augmente le level de "+score);
		System.out.println("*** Fin de l'entrainement ***\n");
	}

	// GETTERS

	public int getAge() {
		return this.age;
	}

	public int getLevel() {
		return this.level;
	}

	public double getSalaire() {
		return this.salaire;
	}

	// SETTERS

	/**
	* Redéfini le salaire du mathématicien selon son niveau actuel et son âge
	*/
	public void setSalaire() {
		this.salaire = age*50+level*200;
	}

	public void augmenterLevel() {
		this.level++;
		this.setSalaire();
	}
}