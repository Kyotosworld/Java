public class Plateau{

//ATTRIBUTS
    public int taille ;
    public int [] [] monde ;
    public Bateau b ;
    
//CONSTRUCTEURS
	public Plateau() {}
    
    public Plateau(int n){  
		taille=n;
        
        monde = new int[n][n];
        
        for(int i=0 ; i<n ; i++){
            for(int j=0; j<n ; j++){
                monde[i][j]=1;  // au départ, toutes les cases sont de la mer
            }
        }
    }
  
// METHODES
    
    public Bateau getbateau () { // récupère la bateau
        return b;
    }
    
    public int [][] getmonde() { // récupère la plateau
        return monde;
    }
    
    public int  gettaille () { // récupère la taille du plateau
        return taille;
    }
    
    public void modifcompo (int x, int y,int a) { // le plateau prend la valeur a aux coordonées (x,y)
		monde [x][y]=a;
	}
	public int getval (int x,int y) {  // la méthode renvoie la valeur de la case de coordonées (x,y)
		int val=monde[x][y];
		return val;
	}
}
