/**
* Decrit un point du champ de force de coordonnees (x,y) dans la base cartesienne
*/
public class Point {
	/**
	* Projection sur l'axe x
	*/
	public double x;
	/**
	* Projection sur l'axe y
	*/
	public double y;
	/**
	* @param x Coordonnee x
	* @param y Coordonnee y
	*/
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}