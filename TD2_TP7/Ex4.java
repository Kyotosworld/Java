package TD2_TP7;

public class Ex4 {

    /** Methode enleverNonAlphanumerique
     *  @param s la chaine a modifier
     *  @return la chaine de caracteres privee de ses caracteres non-alphanumeriques
     */
    public static String enleverNonAlphanumerique(String s) {
        // un tableau d'octets qui permettra de reperer les caracteres non-alphanumeriques
        byte[] b = new byte[s.length()];

        // nouveau tableau qui sera rempli uniquement des caracteres alphanumeriques
        byte[] newB;

        // nouvelle chaine generee a partir du nouveau tableau d'octets
        String newS;
        int compteur = 0;

        // on convertit en un tableau d'octets la chaine passee en parametres
        try {
            b = s.getBytes("ASCII");
        }
        catch (Exception e){}

        // on calcule la taille de newB: si le caractere n'est pas alphanumérique, il ne correspond pas
        for (int i=0; i<b.length; i++) {
            // 48, 57, 65, etc..: bornes des sequences de caracteres alphanumeriques dans la table ASCII
            // 48-57: nombres
            // 65-90: majuscules
            // 97-122: minuscules
            if ((b[i] >= 48 && b[i] <= 57) || (b[i] >= 65 && b[i] <= 90) || (b[i] >= 97 && b[i] <= 122)) {
                compteur++;
            }
        }
        newB = new byte[compteur];

        // on remplit le nouveau tableau d'octets
        compteur = 0;
        for (int i=0; i<b.length; i++) {
            if ((b[i] >= 48 && b[i] <= 57) || (b[i] >= 65 && b[i] <= 90) || (b[i] >= 97 && b[i] <= 122)) {
                newB[compteur] = b[i];
                compteur++;
            }
        }

        // genere la chaine a partir d'un tableau d'octets
        newS = new String(newB);
        return newS;
    }
}