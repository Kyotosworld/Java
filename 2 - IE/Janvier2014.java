package IE;
import java.util.Scanner;

public class Janvier2014 {

/*  1. Architecture et codage (8 pts)

Q 1.1
    8 bits

    Sous Windows: dir
    Sous Linux  : ls

Q 1.2
    On peut représenter 2^10 valeurs donc l'intervalle est [0; 2^10 -1] = [0; 1023]

    1100   = 14 = 12 = C
    100101 = 45 = 37 = 25
    11000  = 24 = 20 = 14
    11110  = 36 = 30 = 1E

Q 1.3
    17(10) = 10001(2)
    Donc  = 010001(2) sur 6 bits (on rajoute simplement un 0 devant)
    On inverse tous les bits à gauche du premier 1:
    -17(10) = 101111 en complément à 2

    100(10) = 1100100(2)
    On répète la même opération:
    -100(10) = 0011100(2) en complément à 2 mais il est codé sur 7 bits
    = 011100(2) en complément à 2 sur 6 bits


    2. Algorithmique et Java: fonctions mathématiques (12 pts)

Q 2.1
    La classe doit être écrite dans le fichier FonctionsMathematiques.java

    On le compile avec: javac FonctionsMathematiques.java

    On l'exécute avec: java FonctionsMathematiques
*/

//  Q 2.2
    public static int minimum(int a, int b) {
        if (a <= b) {
            return a;
        }
        else {
            return b;
        }
    }

//  Q 2.3
    public static double valeurAbsolue(double x) {
        if (x >= 0) {
            return x;
        }
        else {
            return -x;
        }
    }

//  Q 2.4
    public static boolean divise(int a, int b) {
        if (a == 0) {
            return false;
        }
        else if (b%a == 0) {
            return true;
        }
        else {
            return false;
        }
    }

//  Q 2.5
    public static void afficherMultiples(int a, int n) {
        int compteur = 0;
        System.out.print(n + " premiers multiples de " +a +" :");
        for (compteur=1; compteur<=n; compteur++) {
            System.out.println(compteur +"x"+ a +" = "+ a*compteur);    //->    2x10 = 20
//          System.out.println(a*compteur);                             //->    20
        }
    }


/*  Q 2.6
    déterminer le Plus Grand Commun Diviseur de a et b:
    - on cherche un nombre qui est diviseur de a et b en même temps
    - donc le PGCD doit être <= min(a,b)
    - et le reste de a/pgcd et le reste de b/pgcd doivent être = 0
    - il faut qu'il soit le plus grand possible

    Algorithme:
        pgcd <- min(a,b)
        tant que (pgcd n'est pas diviseur de a et b)
            pgcd = pgcd -1
        renvoie pgcd

    pgcd n'est pas diviseur de a et b <=> reste(a/pgcd) différent de 0 OU reste(b/pgcd) différent de 0
                                     <=> (a%pgcd != 0) OU (b%pgcd != 0)
                                     <=> ((a%pgcd != 0) || b%pgcd != 0))

*/
    public static int pgcd(int a, int b) {
        int pgcd = 0;
        int compteur = 0;

//      Si il n'y a qu'une instruction après un if/elseif/else, on peut l'écrire sans { et }
        if (a == 0 && b == 0)       // si a et b == 0   -> PGCD = 0
            return 0;
        else if (a == b)            // si a == b        -> PGCD = a = b
            return a;
        else if (a < b)             // ensuite on cherche lequel est le minimum
            pgcd = a;
        else
            pgcd = b;

        while ((a%pgcd != 0) || (b%pgcd != 0))
            pgcd--;
        return pgcd;
    }


/*  Q 2.7
    ATTENTION: l'énoncé contient une faute !
    u(n+1) = (u(n) + a/n )/2
    au lieu de u(n+1) = (u(n) + a/2 )/2
*/
    public static double racineCarree(int a, double precision) {
        double u = a;
        double valTest = 0;

        if (a < 0)              // si a est négatif on ne cherche pas sa racine carrée !
            return -1.0;

        valTest = (u*u - a > 0) ? (u*u - a): (-u*u + a);
/*      la ligne précédente est équivalente à:
        if (u*u - a > 0) {                                          valTest est la précision actuelle de la racine
            valTest = u*u -a;
        else
            valTest = u*u +a;
*/
        if (valTest <= precision)                                   // si u correspond déjà avec une assez bonne précision à la racine carrée
            return u;                                               // on le renvoie

        while (valTest > precision) {                               // sinon on utilise la suite, tant que l'incertitude est trop grande
            u = (u + a/u) / 2;                                      // on calcule u(n+1)
            valTest = (u*u - a > 0) ? (u*u - a): (-u*u + a);        // on remet à jour l'incertitude dans valeurTest
            System.out.println("u = " + u + "\tvalTest = " + valTest);
        }

        return u;
    }

//  Q 2.8
    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        int min = 0;
        int pgcd = 0;
        double sqrt = 0.0;
        String div = new String();

//      Non-nécessaire
//      --------------------------------------------------------------------
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez 2 entiers, x et y");
        System.out.print("x = ");
        x = sc.nextInt();
        System.out.print("y = ");
        y = sc.nextInt();
        System.out.println("");
//      --------------------------------------------------------------------

//      pour chaque méthode, on stocke le resultat dans une variable et on affiche
        min = minimum(x,y);
        System.out.println("min(" + x + "," + y + ") = " + min +"\n");          //-> min(35, 12) = 12

        div = (divise(x, y)) ? x +" divise bien "+ y: x +" ne divise pas "+ y;
        System.out.println(div +"\n");

        afficherMultiples(x,10);
        System.out.println("");

        pgcd = pgcd(x,y);
        System.out.println("pgcd(" + x + "," + y + ") = " + pgcd +"\n");

        sqrt = racineCarree(x, 0.001);
        System.out.println("sqrt(" + x + ") = " + sqrt +"\n");

//      on peut aussi tester la fonction valeur absolue
        System.out.println("|sqrt(" + x + ")| = " + valeurAbsolue(sqrt));
        System.out.println("|-sqrt(" + x + ")| = " + valeurAbsolue(-sqrt));
    }
}
