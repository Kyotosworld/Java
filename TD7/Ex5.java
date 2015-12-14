package TD7;

public class Ex5 {

    /** Methode supprimerDoublons
     *  Renvoie une nouvelle chaine ou toutes les occurences multiples du caractere c
     *  ont ete supprimees
     *
     *  @param s la chaine ou supprimer les occurences de c
     *  @param c le caractere a supprimer
     *  @return la nouvelle chaine
     */
    public static String supprimerDoublons(String s, char c) {
        StringBuffer sb = new StringBuffer(s);
        boolean cSuccessif = false;

        if (s.length() < 2) {               // si la chaine contient moins de 2 caracteres
            return s;                       // il ne peut y avoir de doublons
        }
        else {
            sb.setLength(s.length());
            for (int i=1; i<sb.length(); i++) {
                if (sb.charAt(i) == c)
                    if (cSuccessif) {
                        sb.delete(i,i+1);
                        i--;
                    }
                    else
                        cSuccessif = true;
                else
                    cSuccessif = false;
            }
            return sb.toString();
        }
    }
}