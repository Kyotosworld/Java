package TD6;
import Outils.IO;

public class Test {

    public static void main(String[] args) {
        int[] tab = new int[10];

        System.out.println("Entrez 10 valeurs dans le tableau:");
        for (int i=0; i<tab.length; i++) {
            System.out.println("Tableau ["+ i +"]:");
            tab[i] = IO.getInt();
        }

        System.out.print("\n------------------------------------------------\n"
                        +"                Exercice 1\n"
                        +"------------------------------------------------\n");
        Ex1.afficheTableau(tab);
        Ex1.afficheTableau2(tab);
        System.out.println("Somme = "+ Ex1.sommeTableau(tab));
        System.out.println("Moyenne = "+ Ex1.moyenneTableau(tab));
        System.out.println("Ecart-type = "+ Ex1.ecartTypeTableau(tab));

        System.out.print("------------------------------------------------\n"
                        +"                Exercice 2\n"
                        +"------------------------------------------------\n");
        System.out.println("Index du premier 2 dans le tableau: "+ Ex2.rechercheTableau(tab, 2));
        System.out.println("Valeur maximale: "+ Ex2.valMaxTableau(tab)
                          +" à l'index "+ Ex2.posMaxTableau(tab));

        System.out.print("------------------------------------------------\n"
                        +"                Exercice 3\n"
                        +"------------------------------------------------\n");
        System.out.println("Tableau est-il trié ?\n-1 -> décroissant\n0 -> non trié\n1 -> croissant");
        System.out.println("Réponse: "+ Ex3.estTrie(tab));

        System.out.print("\n------------------------------------------------\n"
                        +"                Exercice 4\n"
                        +"------------------------------------------------\n");
        System.out.println("Distribution des valeurs de l'intervalle [1,6] dans le tableau");
        Ex4.afficheDistribution(tab);
        Ex4.diagrammeBaton(tab);
    }
}
