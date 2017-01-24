package TD2_TP7;

public class Ex3 {

    /** Methode supprCar
     *  Renvoie une nouvelle chaine ou toutes les occurences du caractere c
     *  ont ete supprimees
     *
     *  @param s la chaine ou supprimer les occurences de c
     *  @param c le caractere a supprimer
     *  @return la nouvelle chaine
     */
    public static String supprCar(String s, char c) {
        StringBuffer sb = new StringBuffer(s);
        for (int i=0; i<sb.length(); i++) {
            if (sb.charAt(i) == c) {
                sb.delete(i,i+1);
                i--;
            }
        }
        return sb.toString();
    }

    /** Methode supprCar2
     *  Renvoie une nouvelle chaine ou toutes les occurences du caractere
     *  ont ete supprimees, mais sans utiliser de StringBuffer, simplement en generant
     *  une nouvelle chaine a partir d'un tableau de caracteres
     *
     *  @param s la chaine ou supprimer les occurences de c
     *  @param c le caractere a supprimer
     *  @return la nouvelle chaine
     */
    public static String supprCar2(String s, char c) {
        char[] sb = new char[s.length()];
        String newS;
        int compteur = 0;

        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) != c) {
                sb[compteur] = s.charAt(i);
                compteur++;
            }
        }

        newS = new String(sb);
        return newS;
    }
}