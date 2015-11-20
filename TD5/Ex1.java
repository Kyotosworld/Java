package TD5;

public class Ex1 {
/* ALGORITHME ListerNombresParfaits
    
    Entrée:
    DECLARER VARIABLE borneInf      UN entier positif ou  nul
    DECLARER VARIABLE borneSup      UN entier positif non-nul
    DECLARER VARIABLE i             UN entier
    DECLARER VARIABLE j             UN entier
    DECLARER VARIABLE nombreParfait UN entier
    DECLARER VARIABLE nombreSecond  UN entier


    Traitement:
    POUR i ALLANT DE borneInf A borneSup
    DEBUT POUR
        SI parfait(i) == true
        DEBUT SI
            AFFICHER (i est un nombre parfait)
        FIN SI
    FIN POUR

    FONCTION parfait(ENTIER nombreParfait) RENVOIE booleen
    DEBUT FONCTION
        AFFECTER A nombreSecond LA VALEUR nombreParfait
        POUR j ALLANT DE 1 A nombreParfait - 1
        DEBUT POUR
            SI nombreParfait % j == 0
            DEBUT SI
                AFFECTER A nombreSecond LA VALEUR nombreSecond - j
            FIN SI
        FIN POUR

        SI nombreSecond == 0
        DEBUT SI
            RENVOIE true
        SINON
            RENVOIE false
        FIN SI
    FIN FONCTION
*/

/** @return vrai si p est un diviseur de n
 *      faux sinon
 */
    public static boolean divise(int n, int p) {
        if (n%p == 0)
            return true;
        else
            return false;
    }

/** @return vrai si n est un nombre parfait
 *      faux sinon
 */
    public static boolean parfait(int n) {
        int i = 1;
        int nSecond = n;

        for (i=1; i<n; i++) {
            if (divise(n, i))
                nSecond -= i;
        }

        if (nSecond == 0)
            return true;
        else
            return false;
    }

/** affiche la liste des nombres parfait entre 0 et max
 *  en appelant la méthode parfait pour vérifier si le nombre est parfait
 *  @param max: borne supérieure de l'intervalle
 */
    public static void afficheParfaits(int max) {
        int i = 0;
        String parfaits = "Nombres parfaits appartenant à [0; "+ max +"]:" ;
        
        for (i=0; i<=max; i++) {
            if (parfait(i))
                parfaits += "\n"+ i;
        }

        System.out.println(parfaits);
    }

    public static void main(String[] args) {
        System.out.println("Affiche les entiers parfaits entre 0 et une borne supérieure.");
        afficheParfaits(Outils.IO.getInt(0, "None", "Entrez la borne superieure: "));
    }
}
