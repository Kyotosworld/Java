/**------------------------------------------------------------------------------------------
| Operation.java                                                                            |
|                                                                                           |
| Contient les méthodes et constantes essentielles à l'utilisation des objets               |
|                                                                                           |
| @author Gabriel Forien                                                                    |
| @version 0.1                                                                              |
--------------------------------------------------------------------------------------------*/
class Operations {

    final static char[] PUISSANCE = { 0x2070, 0x00b9, 0x00b2, 0x00b3, 0x2074, 0x2075, 0x2076, 0x2077, 0x2078, 0x2079 };

    /**------------------------------------------------------------------------------------------
    | appartient                                                                                |
    |-------------------------------------------------------------------------------------------|
    |                                                                                           |
    |                                                                                           |
    |                                                                                           |
    --------------------------------------------------------------------------------------------*/
    static boolean appartient(Ensemble e, double x) {
        

    }
    static boolean appartient(Ensemble e, Ensemble f) {
        

    } /* FIN appartient */

    /**------------------------------------------------------------------------------------------
    | intersection                                                                              |
    |-------------------------------------------------------------------------------------------|
    |                                                                                           |
    |                                                                                           |
    |                                                                                           |
    --------------------------------------------------------------------------------------------*/
    static Ensemble intersection(Ensemble e, Ensemble f) {
        

    } /* FIN intersection */


    /**------------------------------------------------------------------------------------------
    | union                                                                                     |
    |-------------------------------------------------------------------------------------------|
    |                                                                                           |
    |                                                                                           |
    |                                                                                           |
    --------------------------------------------------------------------------------------------*/
    static Ensemble union(Ensemble e, Ensemble f) {
        

    } /* FIN union */


    /**------------------------------------------------------------------------------------------
    | exclut                                                                                    |
    |-------------------------------------------------------------------------------------------|
    |                                                                                           |
    |                                                                                           |
    |                                                                                           |
    --------------------------------------------------------------------------------------------*/
    static Ensemble exclut(Ensemble e, double x) {
        

    }
    static Ensemble exclut(Ensemble e, Ensemble f) {
        

    } /* FIN exclut */


    /**------------------------------------------------------------------------------------------
    | image                                                                                     |
    |-------------------------------------------------------------------------------------------|
    | Calcule l'image par le polynôme P d'une abscisse x donné                                  |
    |                                                                                           |
    | @param x l'abscisse à laquelle calculer la valeur du polynôme                             |
    | @return P(x)                                                                              |
    --------------------------------------------------------------------------------------------*/
    static double image(Polynome P, double x) {
        double y = 0;
        for (int i=0; i<P.coefficient.length; i++) {
            y += p.coefficient[i] * Math.pow(x, i);
        }
        return y;
    } /* FIN image */

    /**------------------------------------------------------------------------------------------
    | derive                                                                                    |
    |-------------------------------------------------------------------------------------------|
    | Renvoie le polynôme dérivé P'(x) = dP/dx (x)                                              |
    |                                                                                           |
    | @return P'(x)                                                                             |
    --------------------------------------------------------------------------------------------*/
    static Polynome derive(Polynome P) {
        Polynome pPrime;
        int degrePrime = (p.getDegre()-1 >= 1)? p.getDegre()-1: 1;
        double[] coordPrime = new double[degrePrime];

        for (int i=0; i<degrePrime; i++) {
            coordPrime[i] = 0;
        }
        for (int i=1; i<p.coord.length; i++) {
            coordPrime[i-1] = i * p.coord[i];
        }
        pPrime = new Polynome(coordPrime, P.getDomaine());

        return pPrime;
    } /* FIN derive */

    /**------------------------------------------------------------------------------------------
    | primitive                                                                                 |
    |-------------------------------------------------------------------------------------------|
    | Renvoie le polynôme Q tel que dQ/dx (x) = P(x)                                            |
    |                                                                                           |
    | @param p l'objet Polynome                                                                 |
    | @param c la constante d'intégration                                                       |
    | @return q tel que q' = p                                                                  |
    --------------------------------------------------------------------------------------------*/
    static Polynome primitive(Polynome P, double c) {
        Polynome P;
        int degrePrimitive = p.getDegre() + 1;
        double[] coordPrimitive = new double[degrePrimitive];

        coordPrimi[0] = c;
        for (double i=1; i<degrePrimitive; i++) {
            coordPrimitive[(int)i] = p.coord[(int)i-1] * (1.0/i);
        }
        P = new Polynome(coordPrimitive);

        return P;
    }
    static Polynome primitive(Polynome P) {
        primitive(P, 0);
    } /* FIN primitive */

    /**------------------------------------------------------------------------------------------
    | integrePoly                                                                               |
    |-------------------------------------------------------------------------------------------|
    | Renvoie le réel I correspondant à l'intégrale de P sur [a, b]                             |
    | D'après le théorème fondamental de l'algèbre:                                             |
    | Soit f une fonction continue par morceaux sur [a, b] et F sa primitive:                   |
    | I = intégrale(a, b) f(t)dt = F(b) - F(a)                                                  |
    |                                                                                           |
    | @param p l'objet Polynome                                                                 |
    | @param a la borne inférieure de l'intervalle                                              |
    | @param b la borne supérieure de l'intervalle                                              |
    | @return I la valeure réelle de l'intégrale de P                                           |
    |-------------------------------------------------------------------------------------------*/
    public static double integrePoly(Polynome p, double a, double b) {
        Polynome P = primitive(p, 0);   // on peut définir n'importe quelle constante: elle disparaitra
                                            // lorsqu'on calculera la différence
        return image(P, b) - image(P, a);
    } /* FIN integrePoly */
}