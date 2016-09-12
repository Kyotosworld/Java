/**
* La classe objet Personnage contient toute les informations à propos d'un personnage et toutes les actions directements liées à un personnage (utilisation d'une compétence, déplacement)
*/
public class Personnage{
	/**
     * pv, objet de type int, qui renvoie le nombre de point de vie rapporté au type de personnage
	 */
	public int pv;
	
	/** 
     * un objet de type int, qui renvoie le nombre d'action deplacement que le personnage peut effectuer
	 */
     
	public int pm;
    
	/**
	 * un objet de type int, renvoyant au nombre d'action de sorts (chaque sort demande un certain nombre de points d'action) d'un personnage.
	 */
     
	public int pa;
    
	/**
	 * un tableau a une dimension de dimension 5, qui sera ensuite l'"emplacement" des sorts d'un personnage.
	 */
     
    public int[] competences=new int[5];
    
    /**
     * un objet de type int, renvoyant à la caractéristique "force" du personnage.
     */
     
	public int force;
    
	/**
	* un objet de type int, renvoyant à la caractéristique "intelligence d'un personnage".
	*/
	
	public int intelligence;
	
	/** 
	 * un objet de type int, renvoyant au type de personnage utilisé
	 */
	 
	public int type;
	 
    private int posX;
    
    private int posY;
    
    /**
     * un objet de type int, renvoyant au numéro d'un des deux joueurs.
     */
     
    public int numJoueur;

     /**
      * La méthode recherchePosPiege est une méthode renvoyant un tableau d'entiers 1D qui se rapporte aux coordonnées d'un piège le plus proche d'une case sur le plateau de jeu.
      * @param p, un objet de type int[][], qui se rapporte au plateau sur lequel les deux joueurs évoluent.
      * @param cible, un objet de type Personnage, qui se rapporte aux coordonnées du personnages.
      * @return les coordonnées d'un piège (abscisses, ordonnées).
      */ 
                    
    private int[] recherchePosPiege(int[][] p, Personnage cible){
         int[] r= new int[2];
         double d;
         double a=100;
         for (int i=0 ; i<p.length ; i++){
             for (int j=0 ; j<p[0].length ; j++){
                 if (p[i][j]==3){
                     d=Math.sqrt(Math.pow(i-cible.getposX(),2)+Math.pow(j-cible.getposY(),2));
                     if(d<a){
                         a=d;
                         r[0]=i;
                         r[1]=j;
                     }
                 }
             }
         }
         return r;
     }
    
    /**
     * Il s'agit ici du constructeur.
     * @param typeNum, objet de type int qui renverra le type de personnage utilisé par un joueur.
     * @param numJoueur, objet de type int, le numéro du joueur qui joue (1 ou 2).  
     * @param p, un objet de type plateau, renvoyant au plateau sur lequel les deux joueurs évoluent.
     */
    
	public Personnage(int typeNum, Plateau p, int numJoueur){
            this.numJoueur=numJoueur;
			type=typeNum;
			pv=Codex.types[typeNum][0];
			pm=Codex.types[typeNum][1];
            pa=Codex.types[typeNum][2];
            competences[1]=0;
            competences[2]=Codex.types[typeNum][3];
            competences[3]=Codex.types[typeNum][4];
            competences[4]=Codex.types[typeNum][5];
            int [] pos =p.recherchePosIni(numJoueur);
            posX=pos[0];
            posY=pos[1];
            force=Codex.types[typeNum][6];
            intelligence=Codex.types[typeNum][7];
	}
	

                   
    /** 
     * La méthode getposX permet de renvoyer la position en X du personnage sur le plateau.
     * @return posX, la position du personnage selon X.
     */        
     
    public int getposX(){
        return posX;
    }
    
    /** 
     * La méthode getposY permet de renvoyer la position en Y du personnage sur le plateau.
     * @return posY, la position du personnage selon Y.
     */  
     
    public int getposY(){
        return posY;
    }
    
    /**
     * La méthode setposX permet de mettre à jour la position du personnage en X.
     * @param x, un objet de type entier permettant d'affecter la nouvelle position en X du personnage.
     */
   
    public void setposX(int x){
        posX=x;
    }
     /**
     * La méthode setposY permet de mettre à jour la position du personnage en Y.
     * @param y, un objet de type entier permettant d'affecter la nouvelle position en Y du personnage.
     */
    public void setposY(int y){
        posY=y;
    }
    
    /** 
     * La méthode ajoutePa de type void permet d'ajouter des points d'action à un personnage sans dépasser son maximum.
     * @param a, un objet de type int qui permet d'ajouter un nombre "a" de PA au personnage.
     */
    public void ajoutePA(int a){
        this.pa+=a;
        if(this.pa>Codex.types[type][2])
            this.pa=Codex.types[type][2];
    }
     
    /** La méthode reinitialisePm de type void permet de reinitialiser à chaque tour de jeu les points de mouvement du personnage
     */  
    public void reinitialisePM(){
        this.pm=Codex.types[type][1];
    }
    
    /**
     * La méthode ajoutePV de type void permet de rajouter des points de vie à un personnage.
     * @param a un objet de type int qui renvoie au nombre de point de vie que le personnage va gagner.
     */
     
    public void ajoutePV(int a){
        this.pv+=a;
    }                   
    
    /**
     * La méthode utiliserCompetence est une méthode de type int, qui permet d'appliquer les effets des sorts lancés par les personnages.
     * @param sort, un objet de type int, qui renvoie au sort lancé par le personnage.
     * @param cible, un objet de type Personnage qui renvoie au personnage subissant un sort.
     * @param p, un objet de type int[][], renvoyant au plateau sur lequel les joueurs evoluent.
     * @param pos, est un tableau d'entier 1D, ayant les coordonnées nécessaires si le sort en requiert (ex : téléportation).
     * @return un entier qui correspond au message de succès si la compétence a bien été utilisée, ou d'erreur si les conditions (PA, PM, portée) ne sont pas toutes respectées.
     */
     
     
	public int utiliserCompetence(int sort, Personnage cible, int[][] p, int [] pos){
		int[] coordCible={cible.getposX(),cible.getposY()};
		int[] coordLanceur={this.getposX(),this.getposY()};
		int re=0;
        if(Portee.distanceDeuxPoints(p,coordLanceur,coordCible,false)<=Codex.sorts[sort][8] && pa>=Codex.sorts[sort][2]) {
            if((int)(this.pa+Codex.sorts[sort][5])>=0 && (int)(this.pm+Codex.sorts[sort][6])>=0){
                cible.pv+=((double)Codex.sorts[sort][0]*(double)(1+((double)force/100)));
                System.out.println("tot"+((double)Codex.sorts[sort][0]*(double)(1+((double)force/100))));
                System.out.println("coef"+(double)(1+((double)force/100)));
                cible.pv+=((double)Codex.sorts[sort][1]*(double)(1+((double)intelligence/100)));
                cible.pa+=Codex.sorts[sort][2];
                cible.additionnerPM(Codex.sorts[sort][3]);
                this.pv+=Codex.sorts[sort][4];

				this.pa+=Codex.sorts[sort][5];
                System.out.println((int)this.pa);
                this.pm+=Codex.sorts[sort][6];
			
                switch(Codex.sorts[sort][7])
                {
                    case 1 :
                        if(pos[0]>=0 && pos[1]>=0 && pos[0]<p.length && pos[1]<p[0].length){
                            if(p[pos[0]][pos[1]]!=1) {
                                this.posX=pos[0];
                                this.posY=pos[1];
                            } else {
                                return 9;
                            }
                        }else{
                            return 8;
                        }
                            //teleportation: modifie posX et posY (tableau d arguments)
                        break;
                    case 2 :
                       //attirance: attire la cible vers le lanceur, utilise les calculs de portée (l'attire d'au maximum 5 cases en fonction de la carte pour éviter les murs)
                            //initialisation d'une grande distance aléatoire
                            int distanceNouvellePositionJoueur=100;
                            int[][] porteeAttirance=Portee.genererPortee(p,coordCible,false);
                            int[] nouvellePos=new int[2];
                            int puissanceAttirance=7;
                            //liste les cases de porteeAttirance() qui sont à moins de puissanceAttirance cases de la cible et sauvegarde les coordonnées de la plus proche du lanceur
                            for(int i=0;i<porteeAttirance.length;i++)
                                for(int j=0;j<porteeAttirance[0].length;j++)
                                    if(porteeAttirance[i][j]<=puissanceAttirance) {
                                        int[] cTemp={i,j};
                                        if(Portee.distanceDeuxPoints(p,coordLanceur,cTemp,false)<distanceNouvellePositionJoueur && p[i][j]!=1){
                                            distanceNouvellePositionJoueur=Portee.distanceDeuxPoints(p,coordLanceur,cTemp,false);
                                            nouvellePos[0]=i;
                                            nouvellePos[1]=j;
                                        }
                                    }
                            System.out.println(distanceNouvellePositionJoueur);
                            cible.setposX(nouvellePos[0]);
                            cible.setposY(nouvellePos[1]);
                        break;
                    case 4 :
                       //sort peur: calculer distance piege le plus proche et mettre l'adversaire dedans// 
                       int [] t = recherchePosPiege(p, cible);
                       cible.setposX(t[0]);
                       cible.setposY(t[1]);
                        break;
                }
            }else if((int)(this.pa+Codex.sorts[sort][5])<0){
                return 3;
            }else if((int)(this.pm+Codex.sorts[sort][6])<0){
                return 4;
            }
			return 0;
		}else{
			System.out.println("Hors de portée !");
			return 2;
		}
	}
	
	/**
	 * La méthode deplacer de type int permet de savoir si l'on peut déplacer le personnage jusqu'à une certaine case du tableau.
	 * @param coordonnees, est un tableau d'entiers 1D, renvoyant au coordonnées finales du joueur (abscisses/ordonnées).
	 * @param p, est un tableau d'entiers 2D, se référant au plateau sur lequel les joueurs évoluent.
	 * @return un entier renvoyant sur la capacité ou non du personnage à atteindre la case demandée par le joueur (numéro de message de succès ou d'erreur).
	 */
	 
	public int deplacer(int[] coordonnees,int[][]p) {
		int[] coordLanceur={this.getposX(),this.getposY()};
        if(coordonnees[0]>=0 && coordonnees[1]>=0 && coordonnees[0]<p.length && coordonnees[1]<p[0].length){
            if(Portee.distanceDeuxPoints(p,coordonnees,coordLanceur,true)<=pm) {
                pm-=Portee.distanceDeuxPoints(p,coordonnees,coordLanceur,true);
                this.setposX(coordonnees[0]);
                this.setposY(coordonnees[1]);
                return 1;
            }else{
                return 4;
            }
        }else{
            return 8;
        }
	}
	
	/**
	 * La méthode additionnerPm de type void, permet d'additionner des points de mouvements à ceux d'un personnage sans qu'ils ne deviennent négatifs.
	 * @param pm, le nombre de points de mouvement que l'on souhaite additionner à ceux du personnage.
	 */
	 
	 
	public void additionnerPM(int pm) {
		this.pm+=pm;
		
		if(this.pm<0)
			this.pm=0;
	}
    
}
	
	
	
