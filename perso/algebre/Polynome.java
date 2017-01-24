/**------------------------------------------------------------------------------------------
| Polynome.java                                                                             |
|                                                                                           |
| Représente une application de R^n dans R de la forme                                      |
| x |-> somme(i=0, n) ai * x^i                                                              |
| où a représente le coefficient et n le degré de P                                         |
|                                                                                           |
|                                                                                           |
| @author Gabriel Forien                                                                    |
| @version 0.1                                                                              |
--------------------------------------------------------------------------------------------*/
public class Polynome {

    //private int degre;
    private double[] coefficient;
    private Ensemble domaine;

    /**------------------------------------------------------------------------------------------
    | Polynome                                                                                  |
    |-------------------------------------------------------------------------------------------|
    |                                                                                           |
    |                                                                                           |
    |                                                                                           |
    --------------------------------------------------------------------------------------------*/
    Polynome(double[] a, Ensemble e) {
        coefficient = new double[a.length];
        for (int i=0; i<a; i++) {
            coefficient[$i] = a[$i]
        }
        domaine = e;
    } /* FIN Polynome */


    /**------------------------------------------------------------------------------------------
    | toString                                                                                  |
    |-------------------------------------------------------------------------------------------|
    | Ecrit le polynôme en respectant une certaine forme :                                      |
    |   écrit les puissances avec les caractères Unicode appropriés                             |
    |   si ai = 0: n'écrit pas, au lieu d'écrire 0x^i                                           |
    |   si ai est négatif: écrit -aix^i, au lieu d'écrire + -aix^i                              |
    |   si le polynome est nul, écrit 0                                                         |
    |                                                                                           |
    | @return Le polynôme écrit sous la forme d'une chaîne                                      |
    --------------------------------------------------------------------------------------------*/
    public String toString() {
        int i = 0;
        char[] exposants = { 0x2070, 0x00b9, 0x00b2, 0x00b3, 0x2074, 0x2075, 0x2076, 0x2077, 0x2078, 0x2079};
        boolean aucunCoeffNonNul = true;
        String chaine = "";
        String chaine2 = "";
        String coefficient = "";
        String xExposant = "";

        for (i=0; i<this.coord.length; i++) {
            coefficient = String.valueOf(Math.abs(this.coord[i]));

            // Définir l'exposant sous forme de caractères Unicode
            xExposant = "";
            for (int n=0; (int)(i/Math.pow(10,n)) >= 1; n++) {
                xExposant = String.valueOf(exposants[(int)(i/Math.pow(10,n))%10]) + xExposant;
            }
            xExposant = "x" + xExposant;
            if (i == 0) {
                xExposant = "";
            }

            if (this.coord[i] != 0) {
                if(aucunCoeffNonNul) {
                    chaine += String.valueOf(this.coord[i]) + xExposant;
                } else {
                    chaine += ((this.coord[i]>0)? " + ": " - ") + coefficient + xExposant;
                }
                aucunCoeffNonNul = false;
            }
        }

        return (chaine.length() == 0)? "0": chaine;
    } /* FIN toString */
}
