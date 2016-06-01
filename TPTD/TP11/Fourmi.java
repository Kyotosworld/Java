package TP11;

/**
 * Classe Fourmi
 * La définition simple d'une fourmi, càd sa position et sa direction mais elle ne peut pas avancer sans
 * un Plateau qui lui fournit la couleur de la case sur laquelle elle se trouve.
 * La classe AffichageFourmi se contetera d'utiliser les méthodes graphiques pour une fourmi, ainsi
 * que le Plateau
 */
public class Fourmi {
    // Position de la fourmi
    protected int x;   // abscisse
    protected int y;   // ordonnée
    // Orientation de la fourmi
    // 0: haut  1: droite   2: bas  3: gauche
    protected int d;


    /**
     * Constructeur
     * Si aucun paramètre n'est spécifié, on initialise tout à 0
     * On obtiendra une fourmi à l'origine du repère, orientée vers le haut
     */
    public Fourmi(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
    public Fourmi() {
        this(0,0,0);
    }


    /**
     * methode: void avance(booolean);
     *@param c: la couleur de la case sur laquelle est la fourmi
     * La méthode avance reçoit la couleur de la case sur laquelle se trouve la
     * fourmi au temps t, puis détermine la nouvelle orientation de la fourmi, et la
     * fait avancer d'une case dans cette direction.
     */
    public void avance(boolean c) {
        if (c)
            this.d = (this.d+1)%4;  // true : case noire : rotation à droite
        else
            this.d = (this.d+3)%4;  // 3 rotations à droite = 1 rotation à gauche

        switch (this.d) {
            /* ATTENTION:
             * On ne "dessine" pas la cellule dans un repère orthonormé mais dans des
             * cellules de tableaux, contrairement à ce que les coordonnées x, y amènent
             * à penser. C'est-à-dire que le résultat obtenu sera:
             *
             * (0,0) (0,1) (0,2) ... (0,n)
             * (1,0) (1,1) (1,2) ... (1,n)
             *   .     .     .    .    .
             *   .     .     .    .    .
             * (0,n) (1,n) (2,n) ... (n,n)
             */
            case 0:
                this.x--;
                break;
            case 1:
                this.y++;
                break;
            case 2:
                this.x++;
                break;
            case 3:
                this.y--;
                break;
        }
    }

    public int getX() {
        return this.x;
    }
    public void setX(int val) {
            this.x = val;
    }
    public int getY() {
        return this.y;
    }
    public void setY(int val) {
            this.y = val;
    }
    public int getD() {
        return this.d;
    }
    public void setD(int val) {
        if (val >= 0 && val <= 3)
            this.d = val;
        else
            System.out.println ("Erreur: la propriété 'd' n'a pas été modifiée");
    }
}