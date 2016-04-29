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
    	boolean b = true;
        this.taille = taille;

        this.plateau = new Pion[taille][taille];
        // plateau.[]   = colonne = x
        // plateau.[][] = ligne   = y
        
        // les pions blancs sont placés sur les quatre premières lignes, les noirs sur les
        // quatre dernières. Ils sont placés en décalage les blancs par rapport aux noirs.
        for (int i=0; i<taille; i++) {
            for (int j=0; j<4; j++) {
            	if (b)
                	this.plateau[i][j] = new Pion(true);
                else
                	this.plateau[i][taille-1-j] = new Pion(false);
                b = !b;
            }
            b = !b;
        }
        /*
        this.plateau[0][0] = new Pion(false);
        this.plateau[0][0].evoluer();
        this.plateau[1][1] = new Pion(true);
        this.plateau[2][2] = new Pion(false);
        this.plateau[4][4] = new Pion(true);
        */
    }

    public void deplacer(int x1, int y1, int x2, int y2) {
        if (this.plateau[x2][y2] != null)
            System.out.println(Jeu.VERT+"MANGE"+Jeu.RESET);
        this.plateau[x2][y2] = this.plateau[x1][y1];
        this.plateau[x1][y1] = null;
        if (x2 == taille-1)
            this.plateau[x2][y2].evoluer();
    }

    public void afficher() {
        String bord="  ", ligne, s;

        // les bords supérieurs et inférieurs doivent faire la même largeur que n lettres
        // et n espaces puisque chaque lettre est suivie d'un espace
        // de plus les bords gauche et droite occupent une largeur d'environ 3 colonnes
        for(int i=1; i<=taille*2+3; i++)
            bord += '-';

        System.out.println(bord);
        // a chaque tour de boucle on construit une ligne puis on l'affiche
        // on rappelle que i correspond aux colonnes et j aux lignes

        for(int j=taille-1; j>=0; j--) {
            ligne = "";
            for(int i=0; i<taille; i++) {
                // si la position contient un pion initialisé, on récupère son toString
                // sinon l'absence de pion se traduira par une référence inexistante,
                // dans ce dernier cas on affichera une case vide
                s = (plateau[i][j] == null)? " ": plateau[i][j].toString();
                ligne += s+" ";
            }
            System.out.println(((j+1)%10)+" | "+ligne+"|");
        }
        System.out.println(bord);

        s = "    ";
        for (int i=1; i<=taille; i++)
        	s += (i%10)+" ";
        System.out.println(s);
    }

    public Pion getPion(int x, int y) {
        if (  x >= 0 && x < taille
           && y >= 0 && y < taille)
            return plateau[x][y];
        else
            return null;
    }
}
