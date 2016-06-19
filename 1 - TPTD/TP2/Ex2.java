package TP2;

public class Ex2 {
    /* Ce programme illustre l'utilisation du type booleen dans des instructions conditionnelles.
     * Il permet d'identifier un arbre a partir d'observations (feuilles simples ou pas, alternees, etc) ;
     * Les observations sont codees sous forme de constantes de type booleen. La valeur d'une constante n'est pas modifiee durant l'execution du programme.
     * Nous supposons que la foret observee est constituee uniquement d'Erables, de Chenes, de Charmes, de Tilleuls et de Frenes.
     * Les regles utilisees sont une simplification de celles proposees par l'ONF a l'adresse :
     * http://www.onf.fr/activites_nature/++oid++8ac/@@display_advise.html
     */

    public static void main(String[] args) {
        // Declaration des constantes booleennes: les observations a un certain moment.
        // Ces observations n'evoluent pas pendant la duree du programme.
        final boolean FEUILLES_SIMPLES = true; // FEUILLES_SIMPLES vrai si l'arbre a des feuilles simples
        final boolean FEUILLES_ALTERNEES = true; // FEUILLES_ALTERNEES vrai s'il a des feuilles alternees
        final boolean BORD_DENTE = true; //  BORD_DENTE vrai si le bord des feuilles est dente
        final boolean DENTS_FINES = true; //DENTS_FINES vrai si les dents au bord des feuilles sont fines
        final boolean ECORCE_LISSE = true; //ECORCE_LISSE vrai si l'ecorce de l'arbre est lisse
        final boolean FEUILLE_COEUR = false; //FEUILLE_COEUR vrai si la feuille a la forme d'un coeur
        final boolean BOUT_POINTU = false ; //BOUT_POINTU vrai si le bout de la feuille est pointu
        final boolean FLEUR_ODORANTE = false ; //FLEUR_ODORANTE vrai si les fleurs sont odorantes

        if (FEUILLES_SIMPLES){
            System.out.println("Le specimen a des feuilles simples.");                                      // (1)
            if(FEUILLES_ALTERNEES){
                System.out.println("Il a des feuilles alternees.");                                         // (2)
                if (BORD_DENTE){
                    System.out.println("Il a des feuilles dentees.");                                       // (3)
                    if (DENTS_FINES && ECORCE_LISSE){
                        System.out.println("Les dents sont fines et l'ecorce est lisse");                   // (4)
                        System.out.println("Cela pourrait etre : un Charme (Hornbeam, en anglais) ");
                    }
                    if (FEUILLE_COEUR && BOUT_POINTU){
                        System.out.println("Elles ont la forme d'un coeur avec un bout pointu");            // (5)
                        System.out.println("Cela pourrait etre : un Tilleul (Linden Tree, en anglais) ");
                    }
                    if (FLEUR_ODORANTE) {
                        System.out.println("Ses fleurs sont odorantes");
                        System.out.println("C'est un Tilleul !");
                    }
                } else { //les bords de feuilles ne sont pas dentes
                    System.out.println("Il a des feuilles a bord lobe (et non dente).");                    // (6)
                    System.out.println("Cela pourrait etre : un Chene (Oak, en anglais)");
                }

            } else { // les feuilles ne sont pas alternees mais opposees
                System.out.println("Il a des feuilles opposees.");                                          // (7)
                System.out.println("Cela pourrait etre : un Erable (Maple, en anglais) ");
            }
        } else { //les feuilles ne sont pas simples ; elles sont composees
            System.out.println("Le specimen a des feuilles composees.");                                    // (8)
            System.out.println("Cela pourrait etre : un Frene (Ash tree, en anglais) ");
        }

        /*  Affichage final     : (1)(2)(3)(4)
            Valeurs proposees 1 : (1)(2)(6)
            Valeurs proposees 2 : (1)(2)(6)
            Valeurs proposees 3 : (1)(2)(3)(4)(5)
        */
        }
}
