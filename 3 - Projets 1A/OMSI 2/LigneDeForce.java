public class LigneDeForce {
	
    public PointDeForce [] ligne;
    
    /* Methode Analytique */
	public LigneDeForce (double a, double b, double K, int nbPoint, double pas) { 
        ligne = new PointDeForce[(int)(nbPoint/pas)];
        int i = 0;
        double y = a;
        while (i < (int)(nbPoint/pas)){
            ligne[i] = new PointDeForce (y, K);
            i++;
            y += pas;
        }
	}
    
    /* Methode Numerique */
    public LigneDeForce (double b, int nbPoint, double pas, double constante, double x0, double y0) {
        ligne = new PointDeForce[(int)(nbPoint/pas)];
        int i = 0;
        double y = y0;
        double x = x0;
        while (i < (int)(nbPoint/pas)){
            ligne[i] = new PointDeForce (x,y,pas,constante);
            y = ligne[i].Fy;
            x = ligne[i].Fx;
            i++;
        }
	}
    
}

