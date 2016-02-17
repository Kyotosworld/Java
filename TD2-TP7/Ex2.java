package TD7;

public class Ex2 {

    /** Methode compterLaLettre
     *  @param s la chaine ou chercher c
     *  @param c le caractere recherche
     *  @return le nombre de fois ou c apparait dans s
     */
    public static int compterLaLettre(String s, char c) {
        int compteur = 0;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == c)
                compteur++;
        }
        return compteur;
    }
}