package TD2_TP7;

public class Ex10 {

    /** Methode traduireUnBinaireEnDecimal
     *  @param s la chaine de caracteres censee etre un nombre binaire
     *  @return l'equivalent en base decimale
     */
    public static String traduireUnBinaireEnDecimal(String s) {
        int n = 0;

        try {
            n = Integer.parseInt(s, 2);
            return Integer.toString(n);
       	//	return String.valueOf(n);
        }
        catch (NumberFormatException e) {
        	return  "0";
        }
    }
}