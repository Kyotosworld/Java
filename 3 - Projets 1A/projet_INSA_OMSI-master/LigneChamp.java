import java.lang.Math;

/**
* Une ligne de champ s'affiche a l'ecran et est composee d'une multitude de points precalcules a l'avance.
* Caracterisee par : sa couleur (entier) et un tableau de points
*/
public class LigneChamp {
	/**
	* Constante K caracterisant la ligne de champ
	*/
	private final double K;
	/**
	* Selection de la couleur au moment de l'affichage
	*/
	private int color;
	/**
	* petit 'pas' de precision numerique (facteur du vecteur unitaire de la force)
	*/
	private final double H = 0.01;
	/**
	* Tableau de points calcules a l'initialisation de la courbe
	*/
	private Point[] p;

	// Constructeurs
	/**
	* Constructeur par defaut
	*/
	public LigneChamp(double K, int color) {
		this.K = K;
		this.color = color;
	}
 
    /**
    * version ANALYTIQUE
	* @param yMin y de depart de la ligne de champ
	* @param yMax y de fin de la fenetre 
    * @param pas recurrence de calcul de x en fonction de y 
    * rempli le tableau avec le calcul des points  de la force en  x pour un y donne (Version analytique via la primitive)
	*/
	public void init_A (double yMin, double yMax, double pas) {
		p = new Point[(int)((yMax-yMin)/pas)];
		double y0 = yMin;
		for (int i=0;i<p.length;i++) {
			p[i] = new Point(Force.primitive_x(this.K,y0),y0);
			y0+=pas;
		}
	} 
    
    /**
     * version NUMERIQUE
     * @param x0  abscisse du point de depart 
     * @param y0  ordonnee du point de depart
     * Remplit le tableau avec le calcul numerique des coordonnees de la force en x et y a partir d'un point donne (version numerique)
     */
    public void init_N(double x0, double y0) {
    	// Choix arbitraire du nombre de points dans la version numérique pour être sur de dépasser l'écran
        p = new Point [(int)(20/H)];
        // Point de départ
        p[0] = new Point (x0,y0);
        // Calcul des points suivants selon la version numérique
        for (int i=1;i<p.length;i++)
        	// Cherche le point suivant decale suivant le vecteur unitaire de la force
			p[i] = new Point(Force.next_x(H,p[i-1]), Force.next_y(H,p[i-1]));
        }
    
	// ACCESSEURS

	public int size() {
		return p.length;
	}

	public Point getPoint(int i) {
		if (i<p.length)
			return p[i];
		else
			return p[0];
	}

	public int getColor() {
	    return this.color;
	}

	public void setColor(int color) {
	    this.color = color;
	}
}
