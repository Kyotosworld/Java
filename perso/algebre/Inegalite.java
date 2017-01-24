/**------------------------------------------------------------------------------------------
| Inegalite.java                                                                            |
|                                                                                           |
|                                                                                           |
|                                                                                           |
| @author Gabriel Forien                                                                    |
| @version 0.1                                                                              |
--------------------------------------------------------------------------------------------*/
enum Inegalite {
    INDEFINI,
    INFERIEUR,
    INFERIEUR_EGAL,
    SUPERIEUR,
    SUPERIEUR_EGAL;

    /**------------------------------------------------------------------------------------------
    | toString                                                                                  |
    |-------------------------------------------------------------------------------------------|
    | Représente une inégalité (toujours de gauche à droite)                                    |
    |                                                                                           |
    | @return  >, >=, <, <=, ou INDEFINI                                                        |
    --------------------------------------------------------------------------------------------*/
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
}