package IE;
import java.lang.Math;

public class Avril2015 {

/*  1. Compréhension de programme (4 pts)

Q 1.1
    Explications:
    ----------------------------------------------------------------------------
    La deuxième méthode beta n'ont aucune importance:
    beta() n'est appelée nulle part
    alpha() est appelée dans omega() mais le résultat n'est stocké nul part

    dans Omega():
        -4 < 0 donc
            b = -4 + 10 = 6
        afficher("a=" + 10 + " b=" + 6)
        revoie b

    dans main():
        q = b = 6
        afficher("Resultat final:" + 6)
    ----------------------------------------------------------------------------
    Le programme affiche donc:
    a= 10 b= 6
    Resultat final: 6


Q 1.2
    Explications:
    ----------------------------------------------------------------------------
    On a un tableau de 5 cases initialisé dans main()
    On l'envoie à lambda() MAIS ON NE STOCKE NUL PART le résultat
    Par conséquent tab n'est PAS modifié
    Ensuite on l'envoie à delta() qui:
        affiche la taille du tableau
        affiche sur une seule ligne "t[0] | t[1] | t[2] | t[3] | t[4] |"
    ----------------------------------------------------------------------------
    Le programme affiche donc:
    5
    0|1|2|3|4|
*/


//  2. Cosinus hyperbolique au voisinage de 0 (10 pts)

//Q 2.1
    public static double puissance(double x, int n) {
        if (n == 0)
            return 1;                                       // par convention

        else if (n > 0)
            for (int i=0; i<n; i++) {
                x *= x;
            }

        else
            for (int i=0; i>n; i++) {
                x /= x;
            }
        return x;
    }

//Q 2.2
    public static double factorielle(int n) {
        int f = 1;
        if (n < 0)
            return 0;
        else if (n == 0)
            return 1;            
        else
            for (int i=1; i<=n; i++) {
                f *= i;
            }
            return f;
    }

//Q 2.3
    public static double cosinusHyperZero(double x, int n) {
        double dl = 0;
        if (n<0)
            return 0;
        else
            for (int i=0; i<=n; i++) {
                dl += puissance(x, 2*i) / factorielle(2*i);
            }
            return dl;
    }


//  3. Système de recommandation (6 pts)

//Q 3.1
    public static void afficheTableau2D(int[][] t) {
        String l = "";
        for (int i=0; i<t[0].length; i++) {
            l += "--";
        }

        for (int i=0; i<t.length; i++) {
            System.out.println(l);
            for (int j=0; j<t[i].length; j++) {
                System.out.print(t[i][j]+"|");
            }
            System.out.println("");
        }
        System.out.println(l);
    }

    public static int poids(int[] t) {
        int p = 0;
        for (int i=0; i<t.length; i++) {
            if (t[i] != 0)
                p++;
        }
        return  p;
    }

    public static int[] ouBitABit(int[] t1, int[] t2) {         // on suppose t1 == t2
        int[] t3 = new int[t1.length];
        for (int i=0; i<t1.length; i++) {
            if (t1[i] == 0 && t2[i] == 0)
                t3[i] = 0;
            else
                t3[i] = 1;
        }
        return t3;
    }

    public static double similariteJaccard(int[] t1, int[] t2) {
        double num = poids(t1) + poids(t2) - poids(ouBitABit(t1,t2));
        double denom = poids(ouBitABit(t1,t2));
        return num / denom;
    }

    public static int rechercheSimilaire(int[] nClient, int[][] clients) {
        int max = 0;
        for (int i=0; i<clients.length; i++) {
            if (similariteJaccard(nClient, clients[i]) > similariteJaccard(nClient, clients[max]))
                max = i;
        }
        return max;
    }

    public static int[][] genererTableauProfilAleatoire(int artcDispo, int nbrProfils, int nbrArtcProfils) {
        int[][] t = new int[nbrProfils][artcDispo];
        for (int i=0; i<nbrProfils; i++) {
            for (int j=0; j<artcDispo; j++) {
                // il n'est pas necessaire de simplifier la generation des profils aleatoires
                // en supposant qu'ils contiennent tous un nombre t d'articles achetes
                if (Math.random() < 0.5f)
                    t[i][j] = 0;
                else
                    t[i][j] =  1;
            }
        }
        return t;
    }

    public static void main(String[] args) {
        int[][] tableau = genererTableauProfilAleatoire(10, 20, 4);
        afficheTableau2D(tableau);

        int[] nouveauClient = {0, 1, 0, 1, 0, 0, 1, 0, 0, 1};
        System.out.println("Nouveau Client:");

        int iClientSimilaire = rechercheSimilaire(nouveauClient, tableau);
        System.out.println("Le client le plus similaire au nouveau profil est à l'indice "+ iClientSimilaire);
    }
}
