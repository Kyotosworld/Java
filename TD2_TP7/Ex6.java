package TD2_TP7;
import java.util.StringTokenizer;

public class Ex6 {

    /** Methode compterLesMots
     *  @param s la phrase
     *  @return le nombre de mots
     */
    public static int compterLesMots(String s) {
        StringTokenizer st = new StringTokenizer(s);
        return st.countTokens();
    }
}