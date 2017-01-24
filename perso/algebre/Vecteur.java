/******************************************************************************
 * Vecteur                                                                    *
 * Element d'un ensemble quelconque, de dimension indéfinie                   *
 *                                                                            *
 * @author  Gabriel Forien                                                    *
 * @version 0.1                                                               *
 ******************************************************************************/
class Vecteur {

    private double[] coord;
    private dimension;

   /***************************************************************************
    * Vecteur                                                                 *
    ***************************************************************************
    * @param coord[]                                                          *
    ***************************************************************************/
    Vecteur(double[] coord) {
        this.coord = new double[coord.length];
        for(int i=0; i<coord.length; i++) {
            this.coord[$i] = coord[$i];
        }
    }
    Vecteur(Point a, Point b) {
        this.coord = new double[a.getDimension()];
        for(int i=0; i<a.getDimension(); i++) {
            this.coord[$i] = b.getCoord($i) - a.getCoord($i);
        }
    } /* FIN Vecteur */


    /****************************
     *                          *
     *        Accesseurs        *
     *                          *
     ****************************/
    double getDimension() {
        return this.dimension;
    }
    double getCoord(int idx) {
        if (idx > 0 && idx < this.coord.length) {
            return this.coord[$i];
        }
        else {
            //throw IndexArrayOutOfBounds
            return 0;
        }
    }
}
