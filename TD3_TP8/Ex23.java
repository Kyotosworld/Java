package TD3_TP8;

public class Ex23 {

    /** Methode main
     *  Teste le tri fusion avec un tableau non-ordonné
     */
    public static void main(String[] args) {
        int[] t = {12, 13, 56, 32, 48, 95, 7, 12, 59, 63 ,15 ,69 ,100, 5, 1, 12, 15, 97, 55};
        trier(t);
        for (int i: t)
            System.out.println(i);
    }

    /** trier: tri fusion
     *         si le tableau est ordonné, on le renvoie directement
     *         sinon on le divise en deux et on trie ces tableaux:
     *         on rappelle trier() jusqu'à avoir des tableaux ordonnés
     *         puis on rassemble ces deux tableaux en un, en faisant attention aux indices
     *         on renvoie la référence du tableau
     * @param t tableau à trier
     */
    public static void trier(int[] t) {
        if (!estCroissant(t)) {
            int c1 = 0, c2 = 0;
            int[] t1 = divise(t, 1);
            int[] t2 = divise(t, 2);
            trier(t1);
            trier(t2);

            for (int i=0; i<t.length; i++) {
                if (c2 == -1) {
                    t[i] = t1[c1];
                    c1 = (c1 == t1.length-1)? -1: c1+1;
                } else if (c1 == -1) {
                    t[i] = t2[c2];
                    c2 = (c2 == t2.length-1)? -1: c2+1;
                } else if (t1[c1] <= t2[c2]) {
                    t[i] = t1[c1];
                    c1 = (c1 == t1.length-1)? -1: c1+1; 
                } else {
                    t[i] = t2[c2];
                    c2 = (c2 == t2.length-1)? -1: c2+1;
                }
            }
        }
    }

    /** divise: divise un tableau en deux, renvoie le 1er OU le 2nd
     *          si le tableau contient un nombre impair d'éléments, le 1er contiendra un
     *          un élément de plus que le second.
     *          divise() travaille sur des tableaux créés en mémoire, non des références
     * @param t tableau à séparer
     * @param num 1 ou 2: la partie du tableau qui sera renvoyée
     * @return tableau ordonné dans l'ordre croissant
     */
    public static int[] divise(int[] t, int num) {
        int[] d;
        // Longueur du 1er tableau, le plus grand: elle dépend de la parité du nombre d'éléments
        int l = (t.length%2 == 1)? (t.length/2)+1: t.length/2;
        if (num == 1) {
            d = new int[l];
            copie(t, d, 0, l);
        } else {
            d = new int[t.length/2];
            copie(t, d, l, t.length);
        }
        return d;
    }

    /** copie: remplit un tableau vide avec le contenu d'un autre
     * @param t1 tableau source
     * @param t2 tableau destinataire
     * @param offset index de la source à partir duquel on copie
     * @param limit index de la source jusqu'au quel copier (exclu)
     */
    public static void copie(int[] t1, int[] t2, int offset, int limit) {
        int i=0;
        for(int j=offset; j<limit; j++) {
            t2[i] = t1[j];
            i++;
        }
    }

    /** estCroissant
     * @param t tableau à inspecter
     * @return true si le tableau est ordonné dans l'ordre croissant
     */
    public static boolean estCroissant(int[] t) {
        for (int i=1; i<t.length; i++)
            if (t[i] < t[i-1])
                return false;
        return true;
    }
}
