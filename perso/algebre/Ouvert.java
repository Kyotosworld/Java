/******************************************************************************
 * Ensemble                                                                   *
 *                                                                            *
 * @author  Gabriel Forien                                                    *
 * @version 0.1                                                               *
 ******************************************************************************/
class Ensemble {

    /* Définissent le type de bornes */
    private Inegalite typeInf;
    private Inegalite typeSup;
    /* Définissent les bornes */
    private double inf;
    private double sup;


   /***************************************************************************
    * Ensemble                                                                *
    ***************************************************************************
    * @param typeInf Définit l'existence ou non d'une borne inférieure,       *
    *                borne qui est atteinte ou non                            *
    * @param typeSup                                                          *
    * @param inf Borne inférieure si elle est définie                         *
    * @param sup                                                              *
    ***************************************************************************/
    Ensemble(Inegalite typeInf, Inegalite typeSup, double inf, double sup) {
        this.typeInf = typeInf;
        this.typeSup = typeSup;
        this.inf = inf;
        this.sup = sup;
    } /* FIN Ensemble */

   /***************************************************************************
    * toString                                                                *
    ***************************************************************************
    * @return Représentation du type [a ; b]                                  *
    ***************************************************************************/
    String toString() {
        String s = "";
        if (this.typeInf != Inegalite.INDEFINI) {
            s += (this.typeInf == Inegalite.INFERIEUR)? "] ": "[";
            s += String.format("%.2f", this.inf);
        }
        else {
            s += "]-oo"
        }
        s += " ; "
        if (this.typeSup != Inegalite.INDEFINI) {
            s += String.format("%.2f", this.sup);
            s += (this.typeInf == Inegalite.SUPERIEUR)? "[ ": "]";
        }
        else {
            s += "+oo["
        }
        return s;
    } /* FIN toString */


    /****************************
     *                          *
     *        Accesseurs        *
     *                          *
     ****************************/
    Inegalite getTypeInf() {
        return this.typeInf;
    }
    Inegalite getTypeSup() {
        return this.typeSup;
    }
    double getInf() {
        return this.inf;
    }
    double getSup() {
        return this.sup;
    }

}
