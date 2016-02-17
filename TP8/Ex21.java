package TP8;

public class Ex21 {

    public static void main(String[] args) {
        int[] t = {12, 13, 56, 32, 48, 95, 7, 1, 12, 59, 63 ,15 ,69 ,100, 5, 12, 15, 97, 55, 1};
        for (int i=0; i<t.length; i++)
            echangePositions(t, i, positionPlusPetit(t, i));
        for (int i: t)
            System.out.println(i);
    }

    public static void echangePositions(int[] t, int p1, int p2) {
        int temp = t[p1];
        t[p1] = t[p2];
        t[p2] = temp;
    }

    public static int positionPlusPetit(int[] t, int p) {
        for (int i=p; i<t.length; i++)
            if (t[i] < t[p])
                p = i;
        return p;
    }
}