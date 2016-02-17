package TP9;

public class Ex1 {

    public static void main(String[] args) {

     }

    public static void echangePositions(int[] t, int p1, int p2) {
        int temp = t[p1];
        t[p1] = t[p2];
        t[p2] = temp;
    }

    public static void positionPlusPetit(int[] t, int p) {
        int min = p;
        for (int i=p; i<t.length; i++) {
//            System.out.println("i = "+i +"\t\tt[i] = "+t[i]);
            if (t[i] < t[min]) {
//                System.out.println("Ancien min: t["+ min +"]="+t[min]+"\t\tNouveau min: t["+ i +"]="+t[i]);
                min = i;
                i = (p-1);
            }
        }
        echangePositions(t, p, min);
    }
}