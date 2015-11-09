import java.util.Scanner;

public class IEJanvier2014 {

    public static int minimum(int a, int b) {
        if (a <= b)
            return a;
        else
            return b;
    }

    public static double valeurAbsolue(double x) {
        if (x >= 0)
            return x;
        else
            return -x;
    }

    public static boolean divise(int a, int b) {
        if (a == 0)
            return false;
        else if (b%a == 0)
            return true;
        else
            return false;
    }

    public static void afficherMultiples(int a, int n) {
        int compteur = 0;
        System.out.print(n + " premiers multiples de " +a +" :");
        for (compteur=1; compteur<=n; compteur++) {
            System.out.println(compteur+ "x" +a +" = " +a*compteur);
        }
    }

    public static int pgcd(int a, int b) {
        int min = 0;
        int compteur = 0;
 
        if (a == 0 && b == 0)
            return 0;
        else if (a == b)
            return a;
        else if (a < b)
            min = a;
        else
            min = b;

        while(a%min != 0 || b%min != 0)
            min--;
        return min;
    }

    public static double racineCarree(int a, double precision) {
        double u = a;
        double valTest = 0;

        if (a < 0)
            return -1.0;

        valTest = (u*u - a > 0) ? (u*u - a): (-u*u + a);
        if (valTest <= precision)
            return u;

        while (valTest > precision) {
            u = (u + a/u) / 2;
            valTest = (u*u - a > 0) ? (u*u - a): (-u*u + a);
            System.out.println("u = " + u + "\tvalTest = " + valTest);
        }

        return u;
    }

    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        int min = 0;
        int pgcd = 0;
        double sqrt = 0.0;
        String div = new String();

        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez 2 entiers, x et y");
        boolean inputTest = false;
        do {
            try {
                System.out.print("x = ");
                x = sc.nextInt();
                System.out.print("y = ");
                y = sc.nextInt();
                System.out.println("");
                inputTest = true;
            } catch (java.util.InputMismatchException err) {
                System.out.println("Erreur: rentrez 2 entiers !");
                sc.next();
                continue;
            }
        } while (!inputTest);

        min = minimum(x,y);
        System.out.println("min(" + x + "," + y + ") = " + min +"\n");

        div = (divise(x, y)) ? x +" divise bien "+ y: x +" ne divise pas "+ y;
        System.out.println(div +"\n");

        afficherMultiples(x,10);
        System.out.println("");

        pgcd = pgcd(x,y);
        System.out.println("pgcd(" + x + "," + y + ") = " + pgcd +"\n");

        sqrt = racineCarree(x, 0.001);
        System.out.println("sqrt(" + x + ") = " + sqrt +"\n");

        System.out.println("|sqrt(x)| = " + valeurAbsolue(sqrt));
        System.out.println("|-sqrt(x)| = " + valeurAbsolue(-sqrt));
    }
}