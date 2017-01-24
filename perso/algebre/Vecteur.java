/******************************************************************************
 * Vecteur                                                                    *
 *                                                                            *
 * @author  Gabriel Forien                                                    *
 * @version 0.1                                                               *
 ******************************************************************************/
class Vecteur {

    private double x;
    private double y;
    //private double z;


   /***************************************************************************
    * Vecteur                                                                 *
    ***************************************************************************
    * @param x                                                                *
    * @param y                                                                *
    ***************************************************************************/
    Vecteur(double x, double y) {
        this.x = x;
        this.y = y;
        //this.z = z;
    }
    Vecteur(Point a, Point b) {
        this.x = b.getX() - a.getX();
        this.y = b.getY() - a.getY();
        //this.z = b.getZ() - a.getZ();
    } /* FIN Vecteur */


    /****************************
     *                          *
     *        Accesseurs        *
     *                          *
     ****************************/
    double getX() {
        return this.x;
    }
    double getY() {
        return this.y;
    }
}
