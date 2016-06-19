public class Plateau {
	public int x;//largeur du tableau plateau
	public int y;//hauteur du tableau plateau
	
	public int [][]plateau;
	
	public Joueur j1;	
	public Joueur j2;

	
	
	/**Constructeur
	 *@param x = largeur du tableau (nb de colonnes)
	 *@param y = hauteur du tableau (nb de lignes)
	 *@param posj1 = position du j1 (verticales)
	 *@param posj2 = position du j2 (verticales)
	 */
	public Plateau (int x, int y, int posj1, int posj2){ 
		//On affecte la taille du tableau
		this.x = x;
		this.y = y;
		
		plateau = new int[x][y]; // on crée le plateau de taile (x,y)
		initMonde();
		
		//création des 2 joueurs avec leur position respective
		j1 = new Joueur(posj1, 1);
		j2 = new Joueur(posj2, 2);
	}
	
	
	//initialisation du plateau (toute les cases à 0)
	public void initMonde(){
		int i = 0, j = 0;
		
		//On initialise tout le plateau à 0 (qui correspond à une case vide)
		for(i=0; i<this.x; i++){
			for(j=0; j<this.y; j++){
				plateau[i][j] = 0;
			}
		}
	}
	
	 //On gégnere le monde grace a toutes les positions
	public void genereMonde(){
		int i = 0,j = 0;
		
		//on remet d'abord tout à 0
		for(i=0; i<this.x; i++){
			for(j=0; j<this.y; j++){
				plateau[i][j] = 0;
			}
		}
		
		/*On place le vaisseau du joueur 1
		 * abscisse = 0
		 * ordonnée = j1.position
		 * La position est repérée par la case centrale du vaiseau (a gauche)
		 * hauteur : 5/3/1 (selon les colonnes) (voir cahier des charges)
		 */
		for(i=0; i<4;i++){//on parcourt les 4 premières colonnes)
			for(j = j1.position - 3 + i; j<= j1.position + 3 - i; j++){
				
                if((j < plateau[0].length - j1.UNITE_DEPLACEMENT)&&(j > j1.UNITE_DEPLACEMENT)){
                
                plateau[i][j] = 1;
                
                }
			}
		} 
		
		/*On place le vaisseau du joueur 2
		 * abscisse : x
		 * ordonnée : j2.position
		 * c'est le symetrique du vaiseau du joueur 1 par rapport au centre de de l'écran
		 */
		for(i=this.x-1; i>this.x-5; i--){ //on parcourt selon les 4 dernières colonnes
			for(j=j2.position - 4 - i+this.x; j<= j2.position +4 + i - this.x; j++){
                
                if((j < plateau[0].length - j2.UNITE_DEPLACEMENT)&&(j > j2.UNITE_DEPLACEMENT)){
                
                plateau[i][j] = 2;
                
                }
			}
		}
		
				/*On place les missiles du j1
		 */
		for(i=1; i<this.x; i++){//on parcourt le plateau selon les colonnes
			for(j= 0; j<j1.positionMissilesX.length;j++){//On parcourt les positions en x des missiles
				if(i==j1.positionMissilesX[j]){//Si il y a un missile sur x=i
					plateau[i][j1.positionMissilesY[j]] = 11; 
				}
			}
			
		}
		
		/*On place les missiles du j2
		 * pareil que pour j1 sauf qu'on met 22
		 */
		for(i=0; i<this.x; i++){
			for(j=0; j<j2.positionMissilesX.length;j++){
				if(i==j2.positionMissilesX[j]){
					plateau[i][j2.positionMissilesY[j]] = 22;
				}
			}
		}
			
		for(int a = 0 ; a < this.x ; a++){
            
            plateau[a][0] = 0;
            
        }
	}
	


}

