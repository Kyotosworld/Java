package TP11;

/**
 * Classe Plateau
 * Plateau carré de côté n et divisé en cases dont les positions vont de (0,0) dans le coin
 * en haut à gauche, à (n-1, n) en bas à droite
 * Le Plateau ne peut avoir une taille inférieure à 2, pour des raisons logiques
 * Le milieu du plateau dépend de la parité de n, en effet si n est pair, le milieu se
 * situe entre 4 cases, donc on définira comme milieu la case en bas à droite
 * C'est au plateau de "gérer" la fourmi pour qu'elle ne sorte pas des limites
 */
public class Plateau {

    protected int taille;
    protected boolean[][] monde;
    protected Fourmi f;
    public final int TAILLE_MIN = 2;
    public final int TAILLE_DEFAUT = 10;
    /**
     * Constructeur
     *@param taille: la longeur du côté du plateau
     *@param f: la fourmi qui se trouve dessus
     */
    public Plateau(int taille, Fourmi f) {
        // On recherche la plus grande coordonnée de la fourmi, en abscisse ou en ordonnée
        int pgCoord = (f.getX() >= f.getY())? f.getX(): f.getY();

        if (taille < TAILLE_MIN) {
            System.out.println("Erreur: le plateau doit avoir une taille minimale de 2x2");
            if (pgCoord >= TAILLE_MIN) {
                // le tableau commence à 0 donc on incrémente pgCoordprotected
                this.taille = pgCoord + 1;
                System.out.println("On ajuste la la taille à la position de la fourmi: taille = "+this.taille);
            } else {
                this.taille = TAILLE_DEFAUT;
                System.out.println("On définit la taille par défaut: taille = "+this.taille);
            }
        } else
            this.taille = taille;

        this.monde = new boolean[taille][taille];
        this.f = f;
    }
    public Plateau(int taille) {
        this(taille, new Fourmi());
    }
    public Plateau() {
        this(10, new Fourmi());
    }

    public void update() {
        int cX = f.getX();
        int cY = f.getY();
        //System.out.println("("+f.getX()+","+f.getY()+","+f.getD()+")");

        // on fait avancer la fourmi: elle peut dépasser du tableau puisque la classe Fourmi
        // n'a pas accès à la taille du tableau
        f.avance(this.monde[cX][cY]);
        // on corrige sa position si elle dépasse ou si elle est inférieure à 0
        f.setX((f.getX() < 0)? 0: f.getX());
        f.setX((f.getX() >= this.taille)? this.taille-1: f.getX());
        f.setY((f.getY() < 0)? 0: f.getY());
        f.setY((f.getY() >= this.taille)? this.taille-1: f.getY());

        // on change la couleur de la case sur laquelle se trouvait la fourmi avant d'avancer
        this.monde[cX][cY] = !this.monde[cX][cY];
    }

    public int getTaille() {
        return this.taille;
    }
    public void setTaille(int value) {
        if (value > this.taille)
            this.taille = value;
        else
            System.out.println("Erreur: la propriété 'taille' n'a pas été modifiée");
    }
    public boolean[][] getMonde() {
        return this.monde;
    }
    public void setMonde(boolean[][] value) {
        if (value.length >= this.monde.length && value.length == value[0].length)
            this.monde = value;
        else
            System.out.println("Erreur: la propriété 'monde' n'a pas été modifiée");
    }
    public Fourmi getF() {
        return this.f;
    }
    public void setF(Fourmi value) {
        if (value.getX() <= this.taille-1 && value.getY() <= this.taille-1)
            this.f = value;
        else
            System.out.println("Erreur: la propriété 'f' n'a pas été modifiée");
    }
}