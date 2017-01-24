/******************************************************************************
 * Polynome                                                                   *
 * Représente une application de R^n dans R de la forme                       *
 * x |-> somme(i=0, n) ai * x^i                                               *
 * où a représente le coefficient et n le degré de P                          *
 *                                                                            *
 * @author  Gabriel Forien                                                    *
 * @version 0.1                                                               *
 ******************************************************************************/
public class Polynome extends Vecteur {

    int degre;

    /***************************************************************************
    * Polynome                                                                *
    ***************************************************************************
    * @param coord[]                                                          *
    ***************************************************************************/
    Polynome(double[] coord) {
        super(coord);
        this.degre = coord.length;
    } /* FIN Polynome */

    /**--------------------------------------------------------------------------------------------------
     * image
     *---------------------------------------------------------------------------------------------------
     * Calcule l'image par le polynôme d'une abscisse x donné
     *
     * @param x l'abscisse à laquelle calculer la valeur du polynôme
     * @return p(x)
     *---------------------------------------------------------------------------------------------------*/
    public double image(double x) {
        double y = 0;
        for (int i=0; i<this.degre; i++) {
            y += this.coord[i] * Math.pow(x, i);
        }
        return y;
    } /* FIN image */


    /**--------------------------------------------------------------------------------------------------
     * valPolyHorner
     *---------------------------------------------------------------------------------------------------
     * Calcule P(x) pour un polynôme donné et un x donné, par la méthode de Horner
     *
     * @param p l'objet Polynôme
     * @param x l'abscisse à laquelle calculer la valeur du polynôme
     * @return p(x)
     *---------------------------------------------------------------------------------------------------*/
    public double image2(double x) {
        double y = this.coord[this.dimension-1];
        for (int i=this.dimension-2; i>=0; i--) {
            y = y * x + this.coord[i];
        }
        return y;
    } /* FIN valPolyHorner */


    /**--------------------------------------------------------------------------------------------------
     * polyNewton
     *---------------------------------------------------------------------------------------------------
     * Calcule x tel que P(X) = 0 pour un polynôme donné, un x de départ donné, et une précision donnée
     *
     * @param p l'objet Polynôme
     * @param x l'abscisse de depart
     * @param e lal précision de recherche
     * @return x tel que P(x) = 0
     *---------------------------------------------------------------------------------------------------*/
    public static double polyNewton(Polynome p, double x, double e) {

        while (Math.abs(valPoly(p, x)) > e) {
            x = x - valPoly(p, x)/valPoly(derivePoly(p), x);
        }

        return x;
    } /* FIN polyNewton */


    /**--------------------------------------------------------------------------------------------------
     * derivePoly
     *---------------------------------------------------------------------------------------------------
     * Renvoie le polynôme dérivé P'(x) pour un polynôme donné
     *
     * @param p l'objet Polynome
     * @return P' une instance de Polynome
     *---------------------------------------------------------------------------------------------------*/
    public static Polynome derivePoly(Polynome p) {
        Polynome pPrime;
        int degrePrime = (p.coord.length-1 >= 1)? p.coord.length-1: 1;
        double[] coordPrime = new double[degrePrime];

        for (int i=0; i<degrePrime; i++) {
            coordPrime[i] = 0;
        }
        for (int i=1; i<p.coord.length; i++) {
            coordPrime[i-1] = i * p.coord[i];
        }
        pPrime = new Polynome(coordPrime);

        return pPrime;
    } /* FIN derivePoly */


    /**--------------------------------------------------------------------------------------------------
     * primitivePoly
     *---------------------------------------------------------------------------------------------------
     * Renvoie le polynome P tel que P' = p avec une constante d'intégration égale à c
     *
     * @param p l'objet Polynome
     * @param c la constante d'intégration
     * @return q tel que q' = p
     *---------------------------------------------------------------------------------------------------*/
    public static Polynome primitivePoly(Polynome p, double c) {
        Polynome P;
        int degrePrimi = p.coord.length + 1;
        double[] coordPrimi = new double[degrePrimi];

        coordPrimi[0] = c;
        for (double i=1; i<degrePrimi; i++) {
            coordPrimi[(int)i] = p.coord[(int)i-1] * (1.0/i);
        }
        P = new Polynome(coordPrimi);

        return P;
    } /* FIN primitivePoly */


    /**--------------------------------------------------------------------------------------------------
     * integrePoly
     *---------------------------------------------------------------------------------------------------
     * Renvoie le réel I correspondant à l'intégrale de P sur [a, b]
     * D'après le théorème fondamental de l'algèbre:
     * Soit f une fonction continue par morceaux sur [a, b] et F sa primitive:
     * I = intégrale(a, b) f(t)dt = F(b) - F(a)
     *
     * @param p l'objet Polynome
     * @param a la borne inférieure de l'intervalle
     * @param b la borne supérieure de l'intervalle
     * @return I la valeure réelle de l'intégrale de P
     *---------------------------------------------------------------------------------------------------*/
    public static double integrePoly(Polynome p, double a, double b) {
        Polynome P = primitivePoly(p, 0);   // on peut définir n'importe quelle constante: elle disparaitra
                                            // lorsqu'on calculera la différence
        return valPoly(P, b) - valPoly(P, a);
    } /* FIN integrePoly


    /**--------------------------------------------------------------------------------------------------
     * integreRectangle 
     *---------------------------------------------------------------------------------------------------
     * Calcule la valeur de l'intégrale d'un polynôme P sur un intervalle [a, b] donné
     * par la méthode des rectangles
     *
     * @param p l'objet Polynôme
     * @param a la borne inférieure de l'intervalle
     * @param b la borne supérieure de l'intervalle
     * @param N le nombre de divisions de l'intervalle
     * @return l'aire sous la courbe du polynôme entre a et b, soit somme(k=a, b) k*f(k)
     *---------------------------------------------------------------------------------------------------*/
    public static double integreRectangle(Polynome p, double a, double b, int N) {
        N = (N<1)? 1: N;
        double I = 0;
        double delta = (b - a)/N;

        if (a == b) {
            return 0;
        }
        else if (a > b) {
            return (-1.0)*integreRectangle(p,b,a,N);
        }

        for (double k=0; k<N; k++) {
            I += delta * valPoly(p, (k + 0.5)*delta + a);
        }

        return I;
    } /* FIN integreRectangle */


    /**--------------------------------------------------------------------------------------------------
     * toString
     *---------------------------------------------------------------------------------------------------
     * Ecrit le polynôme en respectant une certaine forme :
     *  écrit les puissances avec les caractères Unicode appropriés
     *  si ai = 0: n'écrit pas, au lieu d'écrire 0x^i
     *  si ai est négatif: écrit -aix^i, au lieu d'écrire + -aix^i
     *  si le polynome est nul, écrit 0
     *
     * @return Le polynôme écrit sous la forme d'une chaîne
     *---------------------------------------------------------------------------------------------------*/
    public String toString() {
        int i = 0;
        char[] exposants = { 0x2070, 0x00b9, 0x00b2, 0x00b3, 0x2074, 0x2075, 0x2076, 0x2077, 0x2078, 0x2079};
        boolean aucunCoeffNonNul = true;
        String chaine = "";
        String chaine2 = "";
        String coefficient = "";
        String xExposant = "";

        for (i=0; i<this.coord.length; i++) {
            coefficient = String.valueOf(Math.abs(this.coord[i]));

            // Définir l'exposant sous forme de caractères Unicode
            xExposant = "";
            for (int n=0; (int)(i/Math.pow(10,n)) >= 1; n++) {
                xExposant = String.valueOf(exposants[(int)(i/Math.pow(10,n))%10]) + xExposant;
            }
            xExposant = "x" + xExposant;
            if (i == 0) {
                xExposant = "";
            }

            if (this.coord[i] != 0) {
                if(aucunCoeffNonNul) {
                    chaine += String.valueOf(this.coord[i]) + xExposant;
                } else {
                    chaine += ((this.coord[i]>0)? " + ": " - ") + coefficient + xExposant;
                }
                aucunCoeffNonNul = false;
            }
        }

        return (chaine.length() == 0)? "0": chaine;
    } /* FIN toString */
}
