/**
* La classe statique portée contient tous les calculs et les méthodes nécessaires au l'appréciation des portées pendant la partie.
*/
public class Portee{
    
    private static final int ID_MUR=1;

    //méthode réccurcive : le principe de cette méthode s'appuie sur le fait qu'elle s'appelle elle-même 4 fois, une fois pour chaque case adjacente.
    //Utile et nécessaire pour mesurer des distances de chemin qui ne peuvent se faire uniquement par déplacement sur cases adjacentes.
    //Copyright: Aina Rasoldier
    private static void itererPorteeCase(int[][] plateau,int[][] tableauPortees,int[] caseDepart, int[] c0,boolean contourneMurs,int[] coordonneesAdversaire){
        int[] c1=new int[2];
        
        c1[0]=c0[0]-1;
        c1[1]=c0[1];
        if(c1[0]>=0){
            if((tableauPortees[c1[0]][c1[1]]>tableauPortees[c0[0]][c0[1]]+1||tableauPortees[c1[0]][c1[1]]==0)&& !comparerTableaux(c1,caseDepart)){
                if(!(contourneMurs&&((plateau[c1[0]][c1[1]]==ID_MUR) || comparerTableaux(c1,coordonneesAdversaire)))){
                    tableauPortees[c1[0]][c1[1]]=tableauPortees[c0[0]][c0[1]]+1;
                    itererPorteeCase(plateau,tableauPortees,caseDepart,c1,contourneMurs,coordonneesAdversaire);
                }
            }
        }
        
        c1[0]=c0[0]+1;
        c1[1]=c0[1];
        if(c1[0]<tableauPortees.length){
            if((tableauPortees[c1[0]][c1[1]]>tableauPortees[c0[0]][c0[1]]+1||tableauPortees[c1[0]][c1[1]]==0)&& !comparerTableaux(c1,caseDepart)){
                if(!(contourneMurs&&((plateau[c1[0]][c1[1]]==ID_MUR) || comparerTableaux(c1,coordonneesAdversaire)))){
                    tableauPortees[c1[0]][c1[1]]=tableauPortees[c0[0]][c0[1]]+1;
                    itererPorteeCase(plateau,tableauPortees,caseDepart,c1,contourneMurs,coordonneesAdversaire);
                }
            }
        }
        
        c1[0]=c0[0];
        c1[1]=c0[1]-1;
        if(c1[1]>=0){
            if((tableauPortees[c1[0]][c1[1]]>tableauPortees[c0[0]][c0[1]]+1||tableauPortees[c1[0]][c1[1]]==0)&& !comparerTableaux(c1,caseDepart)){
                if(!(contourneMurs&&((plateau[c1[0]][c1[1]]==ID_MUR) || comparerTableaux(c1,coordonneesAdversaire)))){
                    tableauPortees[c1[0]][c1[1]]=tableauPortees[c0[0]][c0[1]]+1;
                    itererPorteeCase(plateau,tableauPortees,caseDepart,c1,contourneMurs,coordonneesAdversaire);
                }
            }
        }
        
        c1[0]=c0[0];
        c1[1]=c0[1]+1;
        if(c1[1]<tableauPortees[c0[0]].length){
            if((tableauPortees[c1[0]][c1[1]]>tableauPortees[c0[0]][c0[1]]+1||tableauPortees[c1[0]][c1[1]]==0)&& !comparerTableaux(c1,caseDepart)){
                if(!(contourneMurs&&((plateau[c1[0]][c1[1]]==ID_MUR) || comparerTableaux(c1,coordonneesAdversaire)))){
                    tableauPortees[c1[0]][c1[1]]=tableauPortees[c0[0]][c0[1]]+1;
                    itererPorteeCase(plateau,tableauPortees,caseDepart,c1,contourneMurs,coordonneesAdversaire);
                }
            }
        }
    }
    
    /** 
    * La méthode genererPortee renvoie un tableau d'entiers permettant de savoir combien de points de déplacement le personnage doit utiliser pour se déplacer d'une case à une autre. Cette méthode est aussi utilisée pour la portée des sorts.
    * @param plateauOriginal est un tableau d'entier 2D, qui se rappporte au plateau choisi par les joueurs auquel on applique la méthode.
    * @param c0, est un tableau d'entier 1D est la case où le personnage se situe initialement (cordonnées: abscisees/ordonnées).
    * @param contourneMurs est un objet de type boolean, permet de savoir si on prend en compte le contournement des murs lors des déplacements.
    * @param coordonneesAdversaire est un tableau d'entiers 1D qui renvoie les coordonnées de l'adversaire.
    * @return tableauPortee, est un tableau d'entiers 2D avec le nombre de points de déplacement nécessaire au personnage pour aller à chaque case du tableau.
    */ 
    public static int[][] genererPortee(int[][] plateauOriginal,int[] c0,boolean contourneMurs,int[] coordonneesAdversaire){
        int[][] tableauPortees= new int[plateauOriginal.length][plateauOriginal[0].length];
        int[][] plateau=recopierTableau(plateauOriginal);
        plateau[coordonneesAdversaire[0]][coordonneesAdversaire[1]]=ID_MUR;
        
        if(c0.length==2){
            itererPorteeCase(plateau,tableauPortees,c0,c0,contourneMurs,coordonneesAdversaire);
        }else{
            System.out.println("Erreur : c0.length = "+c0.length);
        }
        
        
        return tableauPortees;
    }
    
    /** 
    * La méthode genererPortee est une méthode renvoyant un tableau d'entiers (en prenant en compte un contournement de l'adversaire) Il s'agit d'une surcharge de la méthode précédente.
    * @param plateauOriginal, est un plateau d'entiers 2D  renvoyant au plateau de jeu.
    * @param c0, est un tableau d'entiers 1D renvoyant à la case où le personnage se situe initialement (cordonnées abscisees/ordonnées).
    * @param contourneMurs est un objet de type de type boolean, permet de savoir si on prend en compte le contournement des personnages/murs lors de leurs déplacement.
    * @return tableauPortee, un tableau d'entiers 2D avec le nombre de points de déplacement necessaires au personnage pour aller à chaque case du tableau.
    */

    public static int[][] genererPortee(int[][] plateauOriginal,int[] c0,boolean contourneMurs){
        int[][] tableauPortees= new int[plateauOriginal.length][plateauOriginal[0].length];
        int[][] plateau=recopierTableau(plateauOriginal);
        int[] coordonneesAdversaire={-1,-1};
        
        if(c0.length==2){
            itererPorteeCase(plateau,tableauPortees,c0,c0,contourneMurs,coordonneesAdversaire);
            
        }else{
            System.out.println("Erreur : c0.length = "+c0.length);
        }
        
        
        return tableauPortees;
    }
    
    /**
    * La méthode de type void afficherTableau est la méthode d'affichage du tableau.
    * @param t est le tableau d'entier 2D à afficher.
    */
    public static void afficherTableau(int[][] t){
        for(int i=0;i<t.length;i++){
            for(int j=0;j<t[i].length;j++)
                System.out.print("|"+t[i][j]+"\t");
            System.out.println("|");
        }
    }
    
    /**
     * La méthode RecopierTableau est une méthode qui renvoie un tableau d"entiers, elle a pour but de copier un tableau donné en paramètres.
     * @param t, est un tableau d'entiers 2D , renvoyant au tableau à recopier.
     * @return t0, un tableau d'entiers 2D, qui est le tableau d'entiers recopié.
     */
     
    public static int[][] recopierTableau(int [][] t){
        int[][] t0=new int[t.length][t[0].length];
        for(int i=0;i<t.length;i++)
            for(int j=0;j<t[i].length;j++)
                t0[i][j]=t[i][j];
        return t0;
    }
    /**
     * La méthode comparerTableaux renvoie un boolean, pour savoir si deux tableaux 1D d'entiers ont la même largeur/longueur.
     * @param t1, est un tableau d'entiers 1D, renvoyant au premier tableau à comparer.
     * @param t2, est un tableau d'entiers 1D, renvoyant au tableau à comparer avec t1.
     * @return un objet de type boolean, qui permet de savoir si les deux tableaux ont la même longueur ET la même largeur.
     */
     
    public static boolean comparerTableaux(int[] t1,int[] t2){
        if(t1.length!=t2.length)
            return false;
        
        for(int i=0;i<t1.length;i++)
            if(t1[i]!=t2[i])
                return false;
        
        return true;
    }
    
    /**
     * La méthode distanceDeuxPoint renvoie un entier se rapportant à la distance entre deux cases du plateau.
     * @param plateau est un tableau d'entiers 2D sur lequel on calcule la distance entre les deux points.
     * @param c1, est la première case pour faire la distance entre deux cases. 
     * @param c2, est la deuxième case pour faire la distance entre deux cases.                                     
     * @param contourneMurs, est un objet de type boolean, permet de savoir si l'on prend en compte les murs pour la distance entre c1 et c2.
     * @return un objet de type entier renvoyant à la distance entre deux cases du plateau.
     */
     
    public static int distanceDeuxPoints(int[][] plateau,int[] c1,int[] c2,boolean contourneMurs){
		return Math.abs(genererPortee(plateau,c1,contourneMurs)[c2[0]][c2[1]]);
	}
}
