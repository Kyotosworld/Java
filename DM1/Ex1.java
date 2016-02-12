class Ex1 {

    /**
     * methode puissance3: eleve un entier a son cube
     * @param un nombre entier
     * @return son cube
     */
    public static int puissance3(int n) {
        return n*n*n;    
    }

    /**
     * methode estDeArmstrong: teste si un nombre est un nombre d'Armstrong
     * @param le nombre a tester
     * @return true si le nombre est d'Armstrong, false sinon
     */
    public static boolean estDeArmstrong(int nbr) {
        String sNbr  = String.valueOf(nbr);
        int    somme = 0;

        for (int i=0; i<sNbr.length(); i++) {
            somme += puissance3(Character.getNumericValue(sNbr.charAt(i)));
        }

        return ((somme == nbr)? true: false);
    }

    /**
     * main
     * teste tous les nombres entre BORNEMIN et BORNEMAX et affiche un mesage de
     * la forme:
     * "Le nombre <nbr> est de Armstrong"
     */
    public static void main(String[] args) {
        final int BORNEMIN = 0;
        final int BORNEMAX = 999;

        for (int i=BORNEMIN; i<BORNEMAX; i++) {
            if (estDeArmstrong(i)) {
                System.out.println("Le nombre "+ i +" est de Armstrong.");
            }
        }
    }
}
