/******************************************************************************
 * FonctionVectorielle                                                        *
 *                                                                            *
 * @author  Gabriel Forien                                                    *
 * @version 0.1                                                               *
 ******************************************************************************/
class FonctionVectorielle {

    private Polynome X;
    private Polynome Y;
    private Polynome Z;
    private Domaine D;


  /***************************************************************************
    * FonctionVectorielle                                                     *
    ***************************************************************************
    * @param X Polynome associant la coordonnée x au paramètre t              *
    * @param Y                                                                *
    * @param Z                                                                *
    * @param D Domaine de définition du polynôme                              *
    ***************************************************************************/
    FonctionVectorielle(Polynome X, Polynome Y, Polynome Z, Domaine D) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        this.D = D;
    } /* FIN FonctionVectorielle */


    /****************************
     *                          *
     *        Accesseurs        *
     *                          *
     ****************************/
    Polynome getX() {
        return this.X;
    }
    Polynome getY() {
        return this.Y;
    }
    Polynome getZ() {
        return this.Z;
    }
    Domaine getD() {
        return this.D;
    }

}
