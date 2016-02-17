package TD2_TP7;

public class Ex1 {

    /** Methode parcourirChaine: affiche la chaine s verticalement
     *  @param s la chaine a parcourir
     */
    public static void parcourirChaine(String s) {
        for (int i=0; i<s.length(); i++) {
            System.out.println(s.charAt(i));
        }
    }
}