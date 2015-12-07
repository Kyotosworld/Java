package TD6;

public class Ex3 {

    /** Methode estCroissant
     *  @param t un tableau d'entiers
     *  @return true si le tableau est trie par ordre croissant
     */
    public static boolean estCroissant(int[] t) {
        int a = t[0];
        for (int i=0; i<t.length; i++) {
            if (t[i] >= a)
                a = t[i];
            else
                return false;
        }
        return true;
    }

    /** Methode estDecroissant
     *  @param t un tableau d'entiers
     *  @return true si le tableau est trie par ordre decroissant
     */
    public static boolean estDecroissant(int[] t) {
        int a = t[0];
        for (int i=0; i<t.length; i++) {
            if (t[i] <= a)
                a = t[i];
            else
                return false;
        }
        return true;
    }

    /** Methode estTrie determine l'ordre de tri d'un tableau a partir des methodes precedentes
     *  @param t un tableau d'entiers
     *  @return 1 si le tableau est trie par ordre croissant
     *          0 si le tableau n'est pas trie
     *         -1 si le tableau est trie par ordre decroissant
     */
    public static int estTrie(int[] t) {
        if (estCroissant(t))
            return 1;
        else if (estDecroissant(t))
            return -1;
        else
            return 0;
    }
}
