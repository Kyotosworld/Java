/** Jeu
 *  Classe principal du projet de jeu de dames
 *
 *  TO DO LIST :* verifier la possibilité de gagner
 *              * améliorer entree() avec try catch
 *              * pouvoir changer la forme sous laquelle on entre les coordonnées
 *              * pouvoir changer les couleurs
 *
 * @author Gabriel Forien
 */
public class Jeu {

    public static final int TAILLE_DEFAUT = 10;
    public static final String NOIR   = "\033[0;30m";
    public static final String ROUGE  = "\033[0;31m";
    public static final String VERT   = "\033[0;32m";
    public static final String JAUNE  = "\033[0;33m";
    public static final String BLEU   = "\033[0;34m";
    public static final String VIOLET = "\033[0;35m";
    public static final String CYAN   = "\033[0;36m";
    public static final String BLANC  = "\033[0;37m";
    public static final String RESET  = "\033[0;00m";
    public static final String EFFACE = "\033[H\033[2J";
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


    /** ModeEntree
     *  Définit les différentes façons dont un joueur peut "écrire" le déplacement
     *  d'un pion.
     *
     *  Directionnel : "x y d" où x est l'abscisse du pion à déplacer
     *                            y est l'ordonnéee du pion à déplacer
     *                            d est la direction dans laquelle le déplacer, en
     *                            tenant compte des règles.
     *  Dans le jeu de dames, avec un pion on peut se déplacer en avant à gauche, ou à droite
     *  donc d = "g" ou "d".
     *  Avec une dame par contre on peut se déplacer dans la diagonale souhaitée
     *  donc d = "hg"/"hd"/"bg"/"bd"
     *
     *  Coordonnees : "x1 y1 x2 y2"
     *  On indique les coordonnées du point de départ et du point d'arrivée
     */
    private enum ModeEntree {
        Directionnel, Coordonnees
    }
    private static ModeEntree mode = ModeEntree.Coordonnees;

    private enum EtatDuJeu {
        Victoire, Défaite, Neutre
    }
    private static int taille = TAILLE_DEFAUT;
    private static Plateau p;

    public static void main(String[] args) {
        menu();
        System.out.print(FIN);
    }

    /** entree
     *  filtre les coordonnées entrées à chaque tour de jeu, sans se soucier de l'état
     *  du jeu, on vérifie que :
     *  - l'entrée correspond au paramètre de jeu "mode"
     *  - on entre des entiers
     *  - on entre des coordonnées qui existent
     *  - les premières coordonnées correspondent à un pion
     *  - les deuxièmes coordonnées correspondent à une case vide
     *  le second filtrage est fait par entree2()
     * @param mode le mode de réponse choisi par l'utilisateur
     * @return un tableau d'entiers si tout se passe bien
     *         un tableau vide sinon
     *         jeu() teste le résultat avant de l'utiliser
     */
    public static int[] entree(String s) {
        String etat;
        boolean erreur = false;

        String[] arr = s.split(" ");
        int[] rep = new int[arr.length];

        switch(mode) {
            case Directionnel:
                try {
                    rep[0] = Integer.parseInt(arr[0].trim()) -1;
                    rep[1] = Integer.parseInt(arr[1].trim()) -1;
                    rep[2] = (arr[2].trim().equals("d"))? 1: 0;
                    // si le premier point n'est pas un pion -> s'il est null
                    erreur = (p.getPion(rep[0], rep[1]) == null)? true: erreur;
                } catch (java.lang.ArrayIndexOutOfBoundsException |
                         java.lang.NumberFormatException |
                         java.util.InputMismatchException err) {
                    erreur = true;
                }
                break;
            case Coordonnees:
                try {
                    // on doit avoir des couples de 2 variables, au moins un couple de départ
                    // et un d'arrivée, optionnellement d'autres couples intermédiaires
                    erreur = (rep.length%2 != 0 || rep.length < 4)? true: erreur;

                    for (int i=0; i<rep.length; i++)
                        rep[i] = Integer.parseInt(arr[i].trim()) -1;

                    // toutes les variables doivent appartenir à [0; taille[
                    for (int ele : rep)
                        erreur = (ele < 0 || ele >= taille)? true: erreur;

                    // le premier point doit correspondre à un pion
                    erreur = (p.getPion(rep[0], rep[1]) == null)? true: erreur;
                } catch (java.lang.ArrayIndexOutOfBoundsException |
                         java.lang.NumberFormatException | 
                         java.util.InputMismatchException err) {
                    erreur = true;
                }
                break;
        }

        if (erreur)
            rep = new int[0];
        return rep;
    }

    /** regles
     *  Vérifie que les coordonnées entrées correspondent aux règles du jeu de dames
     *  A savoir que:
     *  - on se déplace sur des cases vides
     *  Pour un pion :
     *  - on se déplace d'une case, vers la gauche ou la droite, en avançant forcément
     *  - ou on prend des pions adverses, en "sautant" par-dessus, dans n'importe quelle direction
     *  Pour une dame:
     */
    public static int[] regles(int[] coord, boolean joueur) {
        String etat;
        boolean erreur = false;
        int deltaX, deltaY, xPrecedent, yPrecedent;

        xPrecedent = coord[0];
        yPrecedent = coord[1];

        switch(mode) {
            case Directionnel:
                break;
            case Coordonnees:
                // les autres points doivent correspondre à des cases vides
                for (int i=2; i<coord.length; i=i+2)
                    erreur = (p.getPion(coord[i], coord[i+1]) != null)? true: erreur;
                
                // dans le cas du déplacement
                deltaY = coord[3] - coord[1];
                // <CONDITION DE DEPLACEMENT>
                if (coord.length == 4 && valAbs(deltaY) == 1) {
                    // on doit avoir : x2 = x1 +- 1
                    erreur = (valAbs(coord[2] - coord[0]) != 1)? true: erreur;
                    // le sens de déplacement dépend du camp
                    // les blancs avancent dans le sens du plateau -> y2 = y1 +1
                    // les noirs font l'inverse                    -> y2 = y1 -1
                    if (joueur)
                        erreur = (deltaY != 1)? true: erreur;
                    else
                        erreur = (deltaY != -1)? true: erreur;
                // dans le cas de la prise de pièces adverses
                } else {
                    for (int i=2; i<coord.length; i=i+2) {
                        deltaX = coord[i] - xPrecedent;
                        deltaY = coord[i+1]-yPrecedent;

                        // on vérifie qu'on avance de +-2 en x et +-2 en y
                        // (même un pion a le droit de "manger" un autre pion vers l'arrière)
                        erreur = (valAbs(deltaX) != 2)? true: erreur;
                        erreur = (valAbs(deltaY) != 2)? true: erreur;

                        // on vérifie qu'il y a un pion adverse sur la case sautée
                        // on fait un try catch : la case pourrait aussi être vide, on aurait alors
                        // une erreur puisqu'on appellerait la méthode getCouleur() d'une case vide
                        try {
                        erreur = ( p.getPion(xPrecedent+deltaX/2, yPrecedent+deltaY/2).getCouleur() ==
                                   p.getPion(coord[0], coord[1]).getCouleur() )? true: erreur;
                        } catch (java.lang.NullPointerException err) {
                            erreur = true;
                        }
                        xPrecedent = coord[i];
                        yPrecedent = coord[i+1];
                    }
                }
                break;
        }

        if (erreur)
            coord = new int[0];
        return coord;
    }

    public static int valAbs(int n) {
        return (n>=0)? n: -n;
    }

    public static void jeu() {
        boolean joueur = true;
        EtatDuJeu etat = EtatDuJeu.Neutre;
        boolean erreur = false;
        String  s;
        int[]   coord;              // correspond à rep[] dans entree()
        p = new Plateau(taille);

        while (etat == EtatDuJeu.Neutre) {
            // on efface l'ecran, on affiche l'en-tête puis le plateau
            System.out.print(EFFACE + INTRO);
            p.afficher();

            // tant que les coordonnées entrées ne sont pas valides
            do {
                erreur = false;
                s = IO.getString((!joueur?ROUGE:"")+"Déplacement: ");
                System.out.print(RESET);
                coord = entree(s);
                if (coord.length != 0)
                    coord = regles(coord, joueur);

                // erreur dans jeu() correspond à erreur dans entree()
                // elle est true si le tableau est vide
                // le tableau ne peut en aucun autre cas être vide
                erreur = (coord.length == 0)? true: false;
                if (erreur)
                    System.out.println(JAUNE+"Erreur : coordonnées non valides"+RESET);
            } while (erreur);

            switch(mode) {
                case Directionnel:
                    break;
                case Coordonnees:
                    p.deplacer(coord);
                    break;
            }

            etat = etat();

            joueur = !joueur;
        }
    }

    public static EtatDuJeu etat() {
        return EtatDuJeu.Neutre;
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
            System.out.print("\033[H\033[2J");
            System.out.print(INTRO);
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
                        System.out.print(INTRO);
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
