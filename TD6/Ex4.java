package TD6;

public class Ex4 {

    /** Methode afficheDistribution
     *  @param t un tableau d'entiers compris dans l'intervalle  [1, 6]
     */
    public static void afficheDistribution(int[] t) {
        int[] count = {0, 0, 0, 0, 0, 0};
        for (int i=0; i<t.length; i++) {
            count[t[i]-1]++;
        }
        for (int i=0; i<count.length; i++) {
            System.out.println(i+1 +" : "+ String.valueOf(count[i]));
        }
    }

    /** Methode diagrammeBaton
     *  @param t un tableau d'entiers compris dans l'intervalle  [1, 6]
     */
    public static void diagrammeBaton(int[] t) {
        String[] count = {"", "", "", "", "", ""};
        for (int i=0; i<t.length; i++) {
            count[t[i]-1] += "*";
        }
        for (int i=0; i<count.length; i++) {
            System.out.println(i+1 +" : "+ count[i]);
        }
    }
}
