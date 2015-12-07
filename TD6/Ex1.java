package TD6;
import java.lang.Math;

public class Ex1 {

    /** Methode afficheTableau
    *   @param t le tableau
    */
    public static void afficheTableau(int[] t) {
        String s = "";
        for (int i: t)
            s += String.valueOf(i) + " ";
        s = s.substring(0, s.length()-1);
        System.out.println(s);
    }

    /** Methode afficheTableau2
    *   @param t le tableau
    */
    public static void afficheTableau2(int[] t) {
        String s = "";
        for (int i: t)
            if (i != 0)
                s += String.valueOf(i) + " ";
        s = s.substring(0, s.length()-1);
        System.out.println(s);
    }

    /** Methode sommeTableau
    *   @param t le tableau
    *   @return la somme des elements du tableau
    */
    public static int sommeTableau(int[] t) {
        int s = 0;
        for (int i: t)
            s += i;
        return s;
    }

    /** Methode moyenneTableau
    *   @param t le tableau
    *   @return le quotient de la somme des elements du tableau, par le nombre d'elements
    */
    public static double moyenneTableau(int[] t) {
        double m = 0;
        for (int i: t)
            m += i;
        return m/t.length;
    }

    /** Methode ecartTypeTableau calcule l'ecart-type sigma d'un ensemble de valeurs
    *   @param t le tableau
    *   @return l'ecart-type des valeurs du tableau
    *   l'ecart-type est donne par la formule:
    *   sigma = sqrt( 1/n * sum(k=1, n) (x(k) - x)*(x(k) - x))
    *   ou x est la moyenne de l'ensemble des valeurs
    */
    public static double ecartTypeTableau(int[] t) {
        double sig = 0;
        double moy = 0;
        double sum = 0;

        moy = moyenneTableau(t);
        for (int i: t)
            sum += Math.pow((i-moy), 2);
        sig = Math.sqrt(sum/t.length);
        return sig;
    }
}

