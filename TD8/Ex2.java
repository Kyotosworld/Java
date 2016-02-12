package TD8;

public class Ex2 {

    public static void echangePositions(int[] t, int a, int b) {
        int temp = t[a];
        t[a] = t[b];
        t[b] = temp;
    }

    public static int positionPlusPetit(int[] t, int p) {
        int min = p;

        for (int i=p; i<t.length; i++)
            min = (t[i] < t[min])? i: min;

        return min;
    }

    public static void main(String[] args) {
    	int[] t = {21, 54, 7, 90, 23, 5, 0, 12, 8};
    	int min = 0;

    	for (int i=0; i<t.length; i++) {
    		min = positionPlusPetit(t, i);
    		if (min != i)
    			echangePositions(t, i, min);
    	}
    	for (int i: t)
    		System.out.println(i);
    }
}