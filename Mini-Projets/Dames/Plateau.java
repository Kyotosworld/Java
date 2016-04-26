public class Plateau {

    private int taille = 5;
    private Pion[][] plateau;

    /** constructeur Plateau
     * @param taille un entier strictement supérieur à 4
     *               on aura pris soin de filtrer l'entrée clavier pour que taille corresponde
     *               Plateau() ne gère pas les cas où taille est inférieur ou égal 4
     * @return un plateau rempli avec deux rangées de pions de chaque couleur
     */
    public Plateau(int taille) {
        this.taille = taille;
        this.plateau = new Pion[taille][taille];
        for (int i=0; i<2; i++) {
            for (int j=0; j<taille; j++) {
                // les pions blancs sont placés sur les deux premières lignes
                this.plateau[i][j] = new Pion(true);
                // les pions noirs sont placés sur les deux dernières lignes
                this.plateau[taille-1-i][j] = new Pion(false);
            }
        }
    }

    public void deplacer(int x1, int y1, int x2, int y2) {
        this.plateau[x2][y2] = this.plateau[x1][y1];
        this.plateau[x1][y1] = null;
        if (x2 == taille-1)
            this.plateau[x2][y2].evoluer();
    }

    public void afficher() {
        String bord="", ligne, s;

        // les bords supérieurs et inférieurs doivent faire la même largeur que n lettres
        // et n espaces puisque chaque lettre est suivie d'un espace
        // de plus les bords gauche et droite occupent une largeur d'environ 3 colonnes
        for(int i=1; i<=taille*2+3; i++)
            bord += '-';

        // code correspondant à <CTRL>+L = effacer l'écran
        System.out.print("\033[H\033[2J");
        System.out.println(bord);
        for(int i=taille-1; i>=0; i--) {
            ligne = "";
            for(int j=0; j<taille; j++) {
                // si la position contient un pion initialisé, on récupère son toString
                // sinon l'absence de pion se traduira par une référence inexistante,
                // dans ce dernier cas on affichera une case vide
                s = (plateau[i][j] == null)? " ": plateau[i][j].toString();
                ligne += s+" ";
            }
            // -> "p   p p p   p   p "
            // il y a un espace à la fin mais pas au début de ligne
            // donc on l'ajoute à l'affichage
            System.out.println("| "+ligne+"|");
        }
        System.out.println(bord);
    }

    public Pion getPion(int x, int y) {
        if (  x >= 0 && x < taille
           && y >= 0 && y < taille)
            return plateau[x][y];
        else
            return null;
    }
}
