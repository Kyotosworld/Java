package TP6;

public class Ex2 {

    /** Methode rechercheTableau
     *  @param t un tableau d'entiers
     *  @param v un entier a rechercher
     *  @return la position du premier element correspondant
     *  retourne -1 si l'element n'est pas dans le tableau
     */
    public static int rechercheTableau(int[] t, int v) {
        for (int i=0; i<t.length; i++)
            if (t[i] == v)
                return i;
        return -1;
    }

    /** Methode valMaxTableau
    *   @param t le tableau
    *   @return la valeur du plus grand element du tableau
    */
    public static int valMaxTableau(int[] t) {
        int max = t[0];
        for (int i: t)
            if (i > max)
                max = i;
        return max;
    }

    /** Methode posMaxTableau
    *   @param t le tableau
    *   @return l'index du plus grand element du tableau
    */
    public static int posMaxTableau(int[] t) {
        int posMax = 0;
        int max = t[0];
        for (int i=0; i<t.length; i++)
            if (t[i] > max)
                posMax = i;
        return posMax;
    }
}
