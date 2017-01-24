/******************************************************************************
 * Inegalite                                                                  *
 *                                                                            *
 * @author  Gabriel Forien                                                    *
 * @version 0.1                                                               *
 ******************************************************************************/
enum Inegalite {
    INDEFINI,
    INFERIEUR,
    INFERIEUR_EGAL,
    SUPERIEUR,
    SUPERIEUR_EGAL;

   /***************************************************************************
    * toString                                                                *
    * Représente une inégalité dans le sens de lecture ->                     *
    ***************************************************************************
    * @return si défini: > / >= ou < / <=, INDEFINI sinon                     *
    ***************************************************************************/
    String toString() {
        String s = "";
        switch (this) {
            case INDEFINI:
                s  = "INDEFINI";
                break;
            case INFERIEUR:
                s  = "<";
                break;
            case INFERIEUR_EGAL:
                s  = "<=";
                break;
            case SUPERIEUR:
                s  = ">";
                break;
            case SUPERIEUR_EGAL:
                s  = ">=";
                break;
        }
        return s;
    } /* FIN toString */
