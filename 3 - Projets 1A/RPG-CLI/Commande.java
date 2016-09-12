import java.util.Scanner;

/**
* Cette classe s'occupe de prendre en compte toutes les interactions entre le joueur et le programme, et dispose des méthodes de traitement des chaînes de caractères pour traduire l'information donnée par l'utilisateur.
*/

public class Commande{
	
	private static Scanner sc=new Scanner(System.in);
	
	/**
	 * La méthode de type int, demande un entier à l'utilisateur (permet par exemple au joueur de choisir le numéro de personnage que le joueur souhaite utiliser).
	 *@return l'entier donné par l'utilisateur (par exemple le numéro du type de personnage au début du jeu)
	 */
	 
	public static int demandeNombre(){
		return sc.nextInt();
	}
    
    private static String[] separerChaine(String s){
        int c=0;
        int j=0;
        for (int i=0 ;i<s.length() ; i++){
            if (s.charAt(i)==' '){
                c++;
                j=i+1;
            }
        }
        
        String[] res=new String[c+1];
        int debutDernierMot=0;
        int comptRes=0;
        for (int i=0 ;i<s.length() ; i++){
            if (s.charAt(i)==' '){
                res[comptRes]=s.substring(debutDernierMot,i);
                comptRes++;
                debutDernierMot=i+1;
            }
        }
        
        res[c]=s.substring(debutDernierMot,s.length());
        return res;
    }
    
    private static int[] transformerCoordonnees(String s){
        char[] lettres={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'};
        String[] chiffres={"1","2","3","4","5","6","7","8","9","10","11"};
        int[] coordonnees=new int[2];
        for (int i=0 ; i<lettres.length ; i++){
            if (s.charAt(0)==lettres[i]){
                coordonnees[1]=i;
            }
        }
        for (int i=0 ;i<chiffres.length ; i++){
            if (s.substring(1,s.length()).equals(chiffres[i])){
                coordonnees[0]=i;
            }
        }
        return coordonnees;
    }
	
    private static int stringtoInt (String s) {
        String[] chiffres={"0","1","2","3","4","5","6","7","8","9","10","11"};
        for (int i=0 ;i<chiffres.length ; i++){
            if (s.equals(chiffres[i])){
                return i;
            }
        }
        //System.out.println("stringtoInt -1");
        return -1;
    }

    
    /**
    * Demande à l'utilisateur de taper une commande.
    * @param lanceur variable de type Personnage qui contient l'objet du personnage en train d'agir
    * @param cible variable de type Personnage qui contient l'objet du personnage cible
    * @param p variable de type int[][] qui contient l'objet du plateau de jeu
    * @return un entier correspondant au numéro du message affiché.
    */
	public static int action(Personnage lanceur, Personnage cible, int[][] p ){
		String commande=sc.nextLine();
		String[] t=separerChaine(commande);
        
        //for(int i=0;i<t.length;i++)
            //System.out.println(t[i]);
        
        //System.out.println("t length:"+t.length);
			if(t[0].equalsIgnoreCase("competence") || t[0].equalsIgnoreCase("c")) {
                if(t.length!=2 && t.length!=3)
                    return 6;
                
                int numCompetence=stringtoInt(t[1]);
                
                if(numCompetence < 1 || numCompetence > 4)
                    return 6;
                
                if (t.length==2){
                    int[] t0=new int[2];
                    return lanceur.utiliserCompetence(lanceur.competences[numCompetence], cible, p, t0);
                } else if (t.length==3){
                    return lanceur.utiliserCompetence(lanceur.competences[numCompetence], cible, p, transformerCoordonnees(t[2])); 
                }
            } else if(t[0].equalsIgnoreCase("fin")) {
                System.out.println("tour passé");
                return 5;
            } else if(t[0].equalsIgnoreCase("deplacement") || t[0].equalsIgnoreCase("d")) {
				return lanceur.deplacer(transformerCoordonnees(t[1]),p);
			} else if (t[0].equalsIgnoreCase("regles")) {
                Affichage.afficherRegles();
            }
		return 6;
	}


}
