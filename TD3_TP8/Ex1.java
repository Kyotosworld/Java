package TD3_TP8;

public class Ex1 {

    public static void remplaceRef(int[] tableau, int a, int n, int b) {
    	for (int i=a; i<tableau.length; i++)
    		tableau[i] = b;
    }

    public static int[] remplaceCopie(int[] tableau, int a, int n, int b) {
    	int[] tableau2 = new int[tableau.length];

    	for (int i=0; i<a; i++)
    		tableau2[i] = tableau[i];
    	for (int i=a; i<tableau.length; i++)
    		tableau2[i] = b;

    	return tableau2;
    }
}
