package TD5;
import Outils.IO;

public class Ex3 {
    public static void main(String[] args) {
        int sup = IO.getInt(3, "None", "Entrez la borne superieure: ");
        double prop = proportionJumeaux(sup);
        System.out.println("Proportion de nombre supérieure dans [1, "+ sup +"] = "+ prop +"%");
    }
    
    public static boolean estPremier(int nombre) {
        int i = 0;
        for (i=2; i<nombre; i++) {
            if (nombre%i == 0)
                return false;
        }
        return true;
    }
    
    public static boolean sontJumeaux(int p, int q) {
        if (estPremier(p) && estPremier(q) && p<=q)
            return true;
        else
            return false;
    }

    public static double proportionJumeaux(int n) {
        int i = 0;
        int j = 0;
        int nombreJumeaux = 0;

        for (i=1; i<=n-2; i++) {
            if (sontJumeaux(i, i+2))
                nombreJumeaux += 2;
        }
        if (n >= 7)
            nombreJumeaux--;
        return (nombreJumeaux*100/n);
    }
}
