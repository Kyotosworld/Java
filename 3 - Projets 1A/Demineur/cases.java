/**
 * cette classe définit un nouveau type d'objet:
 * qui regroupe les caractéristiques de chaque case du demineur
 */
public class cases {
//on déclare les attributs de la case en les mettant en private
	private boolean bombe;
	private boolean drapeau; //déclare si oui ou non le joueur a déposé un drapeau dans la case
	private int nbvoisines;
	private boolean revele; //déclare si oui ou non la case est révélée
	private int x;
	private int y;
	
	//initialisation des atributs
	public cases () {
		this.x=0;
		this.y=0;
		this.nbvoisines=0;
		this.bombe=false;
		this.drapeau=false;
		this.revele=false;
		}
	
	//methode pour accéder à l'attribut bombe
	public boolean getBombe () {
		return this.bombe;
	}
	
	//methode pour accéder à l'attribut drapeau
	public boolean getdrapeau () {
		return this.drapeau;
	}
	
	//methode pour accéder aux nombres de voisins
	public int getnbvoisines () {
		return this.nbvoisines;
	}
	
	//methode pour accéder à l'attribut revele
	public boolean getrevele () {
		return this.revele;
	}
	
	//methodes pour accéder aux coordonées
	public int getx () {
		return this.x;
	}
	public int gety () {
		return this.y;
	}
	
	//methode pour modifier l'attribut bombe
	public void setBombe (boolean a) {
		this.bombe=a;
	}
	
	//methode pour modifier l'attribut drapeau
	public void setdrapeau (boolean a) {
		this.drapeau=a;
	}
	
	//methode pour modifier l'attribut revele
	public void setRevele (boolean a) {
		this.revele=a;
	}
	
	//methode pour modifier les coordonnées
	public void setX (int a) {
		this.x=a;
	}
	public void setY (int a) {
		this.y=a;
	}
	
	//methode pour modifier le nombre de voisins
	public void setNbVoisines (int a) {
		this.nbvoisines=a;
	}
}

