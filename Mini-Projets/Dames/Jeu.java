/** Jeu
 *  Classe principal du projet de jeu de dames
 *
 *  TO DO LIST :* méthode en-tête qui nettoie l'écran et affiche un en tête constant
 *              * entrer les coordonnées du point sous la forme "3 4"
 *              * pouvoir changer la forme sous laquelle on entre les coordonnées
 *              * pouvoir changer les couleurs
 *
 * @author Gabriel Forien
 */
public class Jeu {

    public static final int TAILLE_DEFAUT = 10;
    public static final String NOIR   = "\033[0;30m";
    public static final String ROUGE  = "\033[0;31m";
    public static final String JAUNE  = "\033[0;32m";
    public static final String VERT   = "\033[0;33m";
    public static final String BLEU   = "\033[0;34m";
    public static final String VIOLET = "\033[0;35m";
    public static final String CYAN   = "\033[0;36m";
    public static final String BLANC  = "\033[0;37m";
    public static final String INTRO  = "-------------------------------------------\n"+
                                        "               Jeu de Dames                \n"+
                                        "-------------------------------------------\n";
    public static final String MENU   = "[1] Jouer\n"+
                                        "[2] Paramètres\n"+
                                        "[3] Quitter\n";
    public static final String MENU2  = "[1] Changer les couleurs\n"+
                                        "[2] Changer les valeurs par défaut\n"+
                                        "[0] Retour\n";
    public static final String FIN    = "Au revoir !\n";


    public static int taille = TAILLE_DEFAUT;
    public static Plateau p;

    public static void main(String[] args) {
        System.out.print(INTRO);
        menu();
        System.out.print(FIN);
    }

    public static void jeu() {
        boolean joueur = true;
        boolean gagne  = false;
        int x = 0;
        int y = 0;

        p = new Plateau(taille);

        while (!gagne) {
            p.afficher();
            System.out.println("Sélectionnez un pion.");
            x = IO.getInt(1, taille, "x: ");
            y = IO.getInt(1, taille, "y: ");
            p.deplacer(x-1, y-1, x, y);
        }
    }

    /** menu
     *  Affiche un menu textuel et permet une navigation dans celui-ci
     *  Des menus pouvant être imbriqué, et des entrées ajoutés au cours du développement,
     *  on favorise la modularité, la facilité de modification de ce menu:
     *  - les textes des menus sont des constantes définies: on en ajoute ou supprime facilement
     *  - on navigue au moyen de switch + boucles infines et de labels: la structure est simple
     *  - on saisit son choix avec IO.getInt qui filtre l'entrée clavier
     *
     *  Chaque menu ou menu imbriqué possède une texte et un label correspondants (MENUX et menuX:)
     *  Le numéro X correspond à l'imbrication par exemple:
     *      menu principal                       = MENU / menu:
     *      menu principal - entrée 2            = MENU2 / menu2:
     *      menu principal - entrée 2 - entrée 1 = MENU21 / menu21:
     *  Enfin, pour n'importe quel menu imbriqué, [0] = retour au menu précédent
     *  (L'entrée clavier étant filtrée, le cas default: du switch est ici inutile)
     */
    public static void menu() {
        int choix = -1;

        menu: while (true) {
            // au premier passage de boucle, on veut laisser le titre affiché
            if (choix != -1)
                System.out.print("\033[H\033[2J");
            System.out.print(MENU);
            choix = IO.getInt(1, 3, "Votre choix ? ");
            System.out.println();

            switch(choix) {
                case 1:
                    jeu();
                    break;

                case 2:
                    menu2: while (true) {
                        System.out.print("\033[H\033[2J");
                        System.out.print(MENU2);
                        choix = IO.getInt(0, 2, "Votre choix ? ");
                        System.out.println();
                        switch(choix) {
                            case 0:
                                break menu2;
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                    }
                    break;

                case 3:
                    break menu;
            }
        }
    }

}
