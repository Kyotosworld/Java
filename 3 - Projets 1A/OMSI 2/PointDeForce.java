public class PointDeForce {
	
    public double Fy;
    public double Fx;
    public double K;
    
    /* Methode Analytique */
	public PointDeForce (double Fy, double K) {
        this.Fy = Fy;
        this.K = K;
        this.Fx = (1/K)*Math.exp(Fy)*(1/Math.pow(Fy+2,2));
    }
    
    /* Methode Numerique */
    public PointDeForce (double x, double y, double pas, double constante) {
        if (x < 15){
            this.Fx = x + (x * y * pas);
            this.Fy = y + ((y + constante)*pas);
        }
        else {
            this.Fx = x;
            this.Fy = y;
        }
    }
}

