import java.util.Scanner;
/**
* Méthodes de calcul et définition de la Force étudiée
*/
public class Force {
	// DEFINITIONS
    /**
	* @param a Point où la force est observée
	* @return Coordonnee de la force en x (Pour le point a)
	*/
	public static double f_x (Point a) {
        return a.x*a.y;
    }
	/**
	* @param a Point où la force est observée
	* @return Coordonnee de la force en y (Pour le point a)
	*/
	public static double f_y (Point a) {
        return a.y+2;
    }
    /**
	* @param a Point où la force est observée
	* @return Norme de la force (Pour le point a)
	*/
    public static double norme (Point a) {
		return Math.sqrt(Math.pow(f_x(a),2)+Math.pow(f_y(a),2));
	}

	// NUMERIQUE
	/**
	* @param h "pas" : ecart entre les points
	* @param p0 Point precedent
	* @return Coordonnee x1 du point P1 suivant, suivant la force
	*/
	public static double next_x (double h, Point p0) {
		return p0.x + h*f_x(p0)/norme(p0);
	}
	/**
	* @param h "pas" : ecart entre les points
	* @param p0 Point precedent
	* @return Coordonnee y1 du point P1 suivant, suivant la force
	*/
	public static double next_y (double h, Point p0) {
		return p0.y + h*f_y(p0)/norme(p0);
	}
    /**
	* Applique la primitive de F
	* @param y coordonnee d'un point en y
	* @param K Constante integration
	* @return coordonnee du point en x par la primitive
	*/
	public static double primitive_x (double K, double y) {
		return Math.exp(y)/(K*Math.pow((y+2),2));
	}
	
	/**
	 * CIRCULATION ANALYTIQUE
	 */
	public static double circulation (Point A, Point B) {
		double R = B.x - A.x; 
		return (Math.pow(R,3)*(Math.PI/4-1/3)+Math.pow(R,2)/2+2*R);
	}
			
	/**
	* CIRCULATION NUMERIQUE
	*/
	public static double circnumerique (Point A, Point B) {
		double 	R = B.x - A.x,
				dt = 0.00001, // dTHETA
				circ = 0,
				t = Math.PI/2; // THETA
		Point dp;
		
		while (t<Math.PI) { // Sommons de PI sur deux à PI
			dp = new Point(B.x+R*Math.cos(t),R*Math.sin(t));
			circ+=R*dt*( - f_y(dp)*Math.cos(t) + f_x(dp)*Math.sin(t) );
			t+=dt;
		}
		return circ;
	}

	public static void calculerCirculation () {
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------------------------------------------------");
		System.out.println("Circulation entre A (xA,0) et B (R,R) ");
		System.out.println("Abscisse de depart, xA = ");
		double xA = sc.nextDouble();
		System.out.println("R le rayon de la courbe, R = ");
		double r = sc.nextDouble();
		Point A = new Point(xA,0);
		Point B = new Point(xA+r,r);
		System.out.println("Circulation analytique est de "+(Force.circulation(A,B)));
		System.out.println("Circulation numerique est de "+(Force.circnumerique(A,B)));
	}

}
