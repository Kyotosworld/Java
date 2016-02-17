package TD7;

public class Ex7 {

    /** Methode inverserChaine
     *  @param s la chaine de caracteres
     *  @return la chaine inversee
     */
    public static String inverserChaine(String s) {
        StringBuffer sb = new StringBuffer(s);
        return sb.reverse().toString();
    }
}