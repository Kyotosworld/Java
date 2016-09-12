/**
* La classe objet Plateau contient toutes les méthodes relatives à la gestion et à la lecture du plateau de jeu par le reste des classes.
*
*/

public class Plateau {
	
	/** objet de type int faisant appel au numéro de plateau que l'on souhaite utiliser
	 */
	 
	private int numerodeplateau;
	
	/** un tableau 2D, appelé par le numéro de tableau. 
	 */
	 
	private int [][] table;
	

    /** @param a un objet de type entier, faisant appel au terrain de jeu numéro "a" et à ses caractéristiques.
     */
     
	public Plateau (int a) {
		numerodeplateau=a;  //0 pour case normale, 1 pour mur, 2 trou 
		table = Codex.plateaux[a]; // 
	}
	
   
    /**
     * La méthode estSurPiege de type boolean permet de savoir si le personnage est sur un piège.
     * @param p, est un objet de type Personnage, le personnage que l'on analyse pour savoir s'il est sur un piège.
     * @return objet de type boolean signifiant la position du personnage sur un piège (vrai) ou non (faux).
     */  
    
	public boolean estSurPiege (Personnage p) {
		boolean b= false;
		if (table [p.getposX()][p.getposY()]==3){
			b=true;
			}else{
			b=false;
			}
		return b;		
    }

    /**
     * La méthode estSurPiege de type boolean permet de savoir si le personnage est sur un piège.
     * @param p, est un objet de type Personnage, le personnage que l'on analyse pour savoir s'il est sur un piège.
     * @return objet de type boolean signifiant la position du personnage sur un trou (vrai) ou non (faux).
     */  
    
	public boolean estSurTrou (Personnage p) {
		boolean b= false;
		if (table [p.getposX()][p.getposY()]==2){
			b=true;
			}else{
			b=false;
			}
		return b;		
    }
    
     /** 
      * Récupère le tableau d'entiers correspondant au plateau sur lequel évoluent les personnages de la partie en cours.
      * @return le tableau d'entiers caractérisant tout le plateau.
      */
    
    public int[][] getTable(){
        return table;
    }
    
     /** 
      * La méthode recherchePosIni renvoie un tableau d'entiers 1D qui se rapporte au coordonnées d'un personnage au début de la partie.
      * @param numJoueur, un objet de type int, renvoyant au numéro du joueur dont il faut rechercher la position de départ.
      * @return les coodonnées du joueur.
      */
      
     public int[] recherchePosIni(int numJoueur){
         int[][] coordIni= new int[2][2];
         int c=0;
         int k=0;
         for (int i=0 ; i<table.length ; i++){
             for (int j=0 ; j<table[0].length ; j++){
                if (numJoueur==0 && table[i][j]==-1){
                    coordIni[c][0]=i;
                    coordIni[c][1]=j;
                    c++;
                }else if (numJoueur==1 && table[i][j]==-2){
                    coordIni[c][0]=i;
                    coordIni[c][1]=j;
                    c++;
                }
             }
         }
         double d=Math.random()*2;
         if(d>1){
             k=1;
         }
         return coordIni[k];
     }
}