package TP9;
import Outils.IO;

public class Test {

	/** main
	*	teste les methodes de classe Ex1, en recevant un tableau et en le triant
	*/
    public static void main(String[] args) {
        int[] tab = new int[10];

        System.out.println("Entrez 10 valeurs dans le tableau:");
        for (int i=0; i<tab.length; i++) {
            System.out.println("Tableau ["+ i +"]:");
            tab[i] = IO.getInt();
        }

        System.out.println("Tri du tableau:");
        for (int i=0; i<tab.length; i++) {
        	Ex1.positionPlusPetit(tab, i);
        	System.out.println("Itération n°"+(i+1));
        	afficheTableau(tab);
        }
    }

    /** Methode afficheTableau
    *   @param t le tableau
    */
    public static void afficheTableau(int[] t) {
        String s = "";
        for (int i: t)
            s += String.valueOf(i) + " ";
        s = s.substring(0, s.length()-1);
        System.out.println(s);
    }
}