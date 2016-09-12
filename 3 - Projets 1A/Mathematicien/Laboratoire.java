import java.util.Scanner;
import java.lang.Math;

/**
* Objet Laboratoire
* @author Marie Neyret
*/
public class Laboratoire {
	
	/*Déclaration des variables globales*/
	/**
	* Somme à disposition du laboratoire pour sa gestion
	*/
	private double budget;
	/**
	* Tableau de mathématiciens (Objet "Mathematicien"), employés du labo
	*/
	private Mathematicien[] tab; // Tableau simple qui contiendra des valeurs de type Mathematicien
	
	/**
	* Constructeur du laboratoire selon un budget de départ et un nombre initial d'employés
	* @param budget Le budget initial
	* @param n Le nombre d'employés mathématiciens initial
	* @author Marie Neyret
	*/
	public Laboratoire (double budget, int n){
		this.budget = budget;
		tab =new Mathematicien [n];
		for (int i=0; i < n; i++){
			tab[i]= new Mathematicien();
		}	
	}
	
	/*Méthodes*/
	/**
	* @author Marie Neyret
	*/
	public String toString (){
		String str = ("Le Laboratoire a un bugdet de " +(int)budget+ " euros et voila la liste des mathematiciens qui y travaillent :\n");
		for (int i =0; i< tab.length; i ++) {
			str+= " - "+i+" : "+tab[i].toString()+"\n";
		}
		return str;
	}

	/**
	* Ajoute un objet "Mathematicien" à tab de l'objet en cours (sur lequel la méthode est appelée)
	* L'âge est défini aléatoirement entre 25 et 38 ans. Le level et le salaire dépendent de l'âge.
	* @author Marie Neyret
	*/
	public void embauche () {
		int n = tab.length;
		n=n+1; 
		Mathematicien [] newtab= new Mathematicien [n]; /* on crée un tableau en rajoutant une case*/
		for (int i=0; i<n-1; i++) { /* on remplit le tableau avec les anciens mathematiciens*/ 
			newtab[i]=tab[i];
		}
		newtab[n-1] = new Mathematicien( ( 25 + (int)(Math.random() * 13) ) ); // MIN = 25, MAX = 25+13 = 38
		tab = new Mathematicien[n];
		tab = newtab;
	}

	/**
	* Supprime l'objet "Mathematicien" à l'emplacement n de tab.
	* @author Marie Neyret
	*/
	public void licencier (int n){
		this.budget-=1000;
		int newlength = tab.length-1;
		Mathematicien[] tab2 = new Mathematicien [newlength];
		int j = 0; // Compteur secondaire (Pour corriger le saut à la suppression)
		for (int i=0; i<tab.length; i++) {
			if (i!=n) {
				tab2[j]=tab[j];
				j++;
			}
		}
		tab = new Mathematicien[newlength];
		tab = tab2;
	}
	/**
	* Surcharge de la méthode licencier (int n)
	* Si aucun paramètre n'est passé, on demande à l'utilisateur d'interagir.
	* @author Marie Neyret
	*/
	public void licencier () {
		System.out.println("Lequel licencier ? (numero)");
		Scanner sc = new Scanner ( System.in ) ;
		int n = sc.nextInt();
		licencier(n);
	}

	/**
	* Méthode pour passer une année : Augmente de 1 l'age et le level de tous les mathematiciens du labo.
	* Réorganise tab selon le salaire des objets "Mathematicien".
	* @author Valentin Viennot
	*/
	public void annee() {
		for (int i = 0; i<tab.length; i++) {
			this.tab[i].vieillir();
			this.tab[i].augmenterLevel();
		}
		this.triBulles();
	}

	/**
	* Augmente le budget du labo selon un savant calcul de subventions.
	* @author Valentin Viennot
	*/
	public void subventions() {
		this.budget+=1500;
		for (int i = 0; i<tab.length; i++) {
			this.budget+=tab[i].getLevel()*200;
		}
	}


	/**
	* Vérifie l'âge des mathématiciens, ceux de plus de 40 ans quittent le labo.
	* @author Valentin Viennot
	*/
	public void retraites() {
		for (int i = 0; i<tab.length; i++) {
			if (tab[i].getAge()>40) {
				this.licencier(i);
				System.out.println("\n ATTENTION : Un mathematicien a pris sa retraite !");
			}
		}
	}

	/**
	* Soustrait le salaire de chaque mathématicien au budget du labo.
	* @author Valentin Viennot
	*/
	public void payer() {
		for (int i = 0; i<tab.length; i++) {
			this.budget-=tab[i].getSalaire();
		}
	}

	/**
	* Interagir avec l'utilisateur pour choisir un objet "Mathematicien" de tab et l'entrainer.
	* @author Valentin Viennot
	*/
	public void forme() {
		Scanner sc = new Scanner(System.in);
		this.budget-=1000;
		System.out.println("Quel mathematicien voulez-vous former ? (numero)");
		tab[sc.nextInt()].entrainer();
	}

	/**
	@author Valentin Viennot
	*/
	public void triBulles() {
        /**
         * Tri de tab par ordre croissant (méthode : Tri à bulles)
         * Après exécution les mathématiciens sont rangés du mieux au moins bien payé dans tab
         * Rappel : Tab est du type Mathematicien[]
         */
         boolean utile=true; // Est-il utile de trier le tableau
         while(utile) { // Tant que c'est utile de le faire !
             utile=false; // Supposons comme déjà trié...
             int min=0; // Réduit la plage de traitement du tri (voir plus loin)
             for (int i = min; i<(tab.length-1) ; i++) { // NB : On va jusqu'à length -1 parce qu'on fait ensuite appel à la position i+1 du tableau
                 if (tab[i].getSalaire()<tab[i+1].getSalaire()) { // Si le salaire à gauche est plus petit que celui de droite...
                     echangePositions(tab,i,(i+1)); // ... on inverse la position !
                     utile=true; // Ce passage a été utile (si aucun passage n'a été utile, on aura utile=false [voir plus haut])
                 }
             }
             min++; // La premiere case contient deja le maximum
        }
    }

    public void echangePositions(Mathematicien[] tab, int i1, int i2) {
        /**
         * Dans le tableau tab, échange les valeurs aux positions i1 et i2.
         */ 
        Mathematicien temp=tab[i1];
        tab[i1]=tab[i2];
        tab[i2]=temp;
    }

	// ACCESSEURS

	public double getBudget() {
		return this.budget;
	}
	
}