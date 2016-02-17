package TP2;
import java.lang.Math;
import java.text.DecimalFormat;

public class Ex51 {
    public static void main(String[] args) {
        double x1 = 1;
        double x2 = 4;
        double x3 = 0;

        double fx1 = 0;
        double fx2 = 0;
        double fx3 = 0;

        double e = 0.000001;

        DecimalFormat df = new DecimalFormat ();
        df.setMaximumFractionDigits(6);
        df.setMinimumFractionDigits(3);

        while (Math.abs(x1 - x2) > e) {
            x3 = (x1+x2)/2;
//            System.out.println("x1 = "+ x1 + "\t\tx2 = "+ x2 +"\t\tx3 = "+ x3);
//            System.out.println("fx1 = "+ f(x1) + "\t\tfx2 = "+ f(x2) +"\t\tfx3 = "+ f(x3));

            if (memeSigne(f(x1), f(x3)))
//              System.out.println("fx1 est du signe de fx3");
                x1 = x3;
            else
//              System.out.println("fx2 est du signe de fx3");
                x2 = x3;
        }

        System.out.println("Pour "+ Double.parseDouble(df.format(f(x1))) +"  < f(x) < "+ Double.parseDouble(df.format(f(x2))));
        System.out.println("On a "+ Double.parseDouble(df.format(x1)) +" <   x  < "+ Double.parseDouble(df.format(x2)));
    }

    public static double f(double x) {
        return x*x + 3 * Math.PI * (x*Math.cos(x) + Math.sin(x));
    }

    public static boolean memeSigne(double a, double b) {
        return ((a<0 && b<0) || (a>0 && b>0))? true: false; 
    }
}
