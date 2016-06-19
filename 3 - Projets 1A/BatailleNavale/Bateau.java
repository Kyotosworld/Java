
public class Bateau{

//ATTRIBUTS

    public int taille; //taille comprise entre 2 et 5 cases
    public String direction; //vertical ou horizontal
    public int x;
    public int y;
    
//CONSTRUCTEURS
    public Bateau(int t, String d){
        taille=t;
        direction=d;
        System.out.println("erreur");
    }
    
    public Bateau (int t, String d, int a, int b) { //2ème constructeur demandant à l'utilisateur les coordonnées du bateau
		taille = t;
		direction= d;	// v ou h			
		x=a;
		y=b;
	}
	
	
//METHODES
    
    public void updatex (int a) {
		x=a;	
	}
    
    public void updatey (int a) {
		y=a;
	}
	
	public int gettaille () {
		return taille;
	}
	
	public String getdirection() {
		return direction;
	}
}
