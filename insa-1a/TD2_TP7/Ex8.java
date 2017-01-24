package TD2_TP7;

public class Ex8 {

    /** Methode estUnPalindrome
     *  @param s la chaine de caracteres
     *  @return true si la chaine est un palindrome (espaces non compris)
     */
    public static boolean estUnPalindrome(String s) {
        StringBuffer sb = new StringBuffer(s);
        sb.setLength(s.length());
        for (int i=0; i<sb.length(); i++) {
            if (sb.charAt(i) == ' ') {
                sb.delete(i, i+1);
                i--;
            }
        }
        return sb.toString().equals(sb.reverse().toString());
    }
}