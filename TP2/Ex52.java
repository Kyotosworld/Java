package TD2;
import java.lang.Math;

public class Ex52 {
    public static void main(String[] args) {
        double x1 = 1;
        double x2 = 4;
        double x3 = 0;

        double fx1 = 0;
        double fx2 = 0;
        double fx3 = 0;

        double e = 0.000001;
        int i    = 0;

        while (Math.abs(x1 - x2) > e) {
            x3 = (x1+x2)/2;

            if (memeSigne(f(x1), f(x3)))
                x1 = x3;
            else
                x2 = x3;
            i++;
        }

        System.out.println("Nous avons eu besoin de "+ i + " itérations pour trouver x avec une précision de "+ e);
    }

    public static double f(double x) {
        return x*x + 3 * Math.PI * (x*Math.cos(x) + Math.sin(x));
    }

    public static boolean memeSigne(double a, double b) {
        return ((a<0 && b<0) || (a>0 && b>0))? true: false; 
    }
}
