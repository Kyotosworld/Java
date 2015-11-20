package TD3;
import java.lang.Math;

public class Ex42 {
    public static void main(String[] args) {
        final double E = 0.000000000000001;
        double uN = 1.0;
        double uNMoinsUn = 0.0;
        int i = 0;

        while (Math.abs(uN - uNMoinsUn) > E) {
            uNMoinsUn = uN;
            uN = 1 + (1/uN);
            i++;
        }
        System.out.println("phi = "+ uN +" après "+ i +" itérations");
    }
}
