/******************************************************************************
 * Systeme                                                                    *
 *                                                                            *
 * @author  Gabriel Forien                                                    *
 * @version 0.1                                                               *
 ******************************************************************************/
class Systeme {

    private FonctionVectorielle[];

}
   /***************************************************************************
    * Systeme                                                                 *
    ***************************************************************************
    * @param segments                                                         *
    ***************************************************************************/
    Systeme(FonctionVectorielle[] segments) {
        bool coherent = true;
        double inf, sup;
        Inegalite typeInf, typeSup;
        for (int i=0; i<segments.lenght-1; i++) {
            gauche = segments[$i].getDomaine.getSup();
            droite = segments[$i+1].getDomaine.getInf();
            typeGauche = segments[$i].getDomaine.getTypeSup();
            typeDroite = segments[$i+1].getDomaine.getTypeSup();

            coherent =(   (gauche == droite) 
                       && ((typeGauche == Inegalite.INFERIEUR) && (typeDroite == Inegalite.INFERIEUR_EGAL)
                       ||  (typeGauche == Inegalite.INFERIEUR_EGAL) && (typeDroite == Inegalite.INFERIEUR)));
        }
    } /* FIN Systeme */
