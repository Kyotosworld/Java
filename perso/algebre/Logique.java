/******************************************************************************
 * Logique                                                                    *
 *                                                                            *
 * @author  Gabriel Forien                                                    *
 * @version 0.1                                                               *
 ******************************************************************************/
class Logique {

   /***************************************************************************
    * appartient                                                              *
    * Décrit l'appartenance d'un point à un ensemble                          *
    ***************************************************************************
    * @param p                                                                *
    * @param e                                                                *
    * @return                                                                 *
    ***************************************************************************/
    static bool appartient(Point p, Figure e) {

    } /* FIN appartient */

   /***************************************************************************
    * appartient                                                              *
    * Définit l'appartenance d'une abscisse à un ensemble                     *
    ***************************************************************************
    * @param x                                                                *
    * @param e                                                                *
    * @return                                                                 *
    ***************************************************************************/
    static bool appartient(double x, Ensemble e) {
        bool a = true;
        switch (e.getTypeInf()) {
            case INFERIEUR:
                a = (a & (e.getInf() < x));
                break;
            case INFERIEUR_EGAL:
                a = (a & (e.getInf() <= x));
                break;
            case INDEFINI:
                break;
            case DEFAULT:
                a = false;
                //throw ExceptionLogique
                //borne inferieur avec inegalite superieure
                break;
        }
        switch (e.getTypeSup()) {
            case INFERIEUR:
                a = (a & (x < (e.getSup()));
                break;
            case INFERIEUR_EGAL:
                a = (a & (x <= e.getSup()));
                break;
            case INDEFINI:
                break;
            case DEFAULT:
                a = false;
                //throw ExceptionLogique
                break;
        }
        return a;
    } /* FIN appartient */
}
