package TP4;

public class Ex12 {

    public static String estPair(int n) {
        if (n%2 == 0)
            return "N est pair";
        else
            return "N est impair";
    }

    public static void main(String[] args) {
        final int N = 765;
        String pairImpair = "";

        pairImpair = estPair(N);
        System.out.println(pairImpair);
    }
}
