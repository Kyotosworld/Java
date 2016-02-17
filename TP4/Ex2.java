package TP4;

public class Ex2 {

    public static int calculerSucc(int n) {
        if (n%2 == 1)
            return 3*n + 1;
        else
            return (int)(n/2);
    }

    public static void afficherPremiersTermes(int n, int iter) {
        int i = 0;

        for (i=0; i<iter; i++) {
            if (n%2 == 1)
                n = 3*n + 1;
            else
                n = (int)(n/2);
            System.out.println("u("+ i +") = "+ n);
        }
    }

    public static void afficherJusquaConvergence(int n) {
        int i = 0;

        System.out.println("u("+ i +") = "+ n);
        while (n != 1) {
            if (n%2 == 1)
                n = 3*n + 1;
            else
                n = (int)(n/2);
            i++;
            System.out.println("u("+ i +") = "+ n);
        }
    }

    public static void main(String[] args) {
        System.out.println("Résultat de afficherPremiersTermes(500, 10)");
        afficherPremiersTermes(500, 10);

        System.out.println("\nRésultat de afficherJusquaConvergence(500)");
        afficherJusquaConvergence(500);
    }
}
