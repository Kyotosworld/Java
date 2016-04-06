import java.lang.Math;

public class Aleatoire {

    public static void main(String[] args) {
        random(10000);
    }

    public static void random(int n) {
        double[] t = new double[n];
        double m=0;
        double S=0;
        double Q=0;
        double V=0;
        double sigma=0;

        for (int i=0; i<n; i++)
            t[i] = Math.random();
        
        for(int i=0; i<n; i++) {
            S += t[i];
            Q += t[i]*t[i];
        }
        m = S/n;
        V = (Q/n) - m*m;
        sigma = Math.sqrt(V);

        System.out.println("n = "+n +"\nm ="+m +"\ns = "+sigma);
    }


}
