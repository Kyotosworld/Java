package untitled.heritage;

public class Test extends Object {

    public static void main(String[] args) {
        String[] nomVilles = {"Paris", "Berlin", "Rome",
                              "Copenhague", "Londres"};
        String[] nomPays = {"France", "Allemagne", "Italie",
                            "Pays-Bas", "Angleterre"};
        String[] nomMonum = {"la Tour Eiffel", "la Porte de Brandebourg", "la Chappelle Sixtine",
                             "le Big Ben", "(le monument de Copenhague)"};
        int[] nbrHab = {100000, 200000, 300000, -400000, -500000, 600000};

        Ville[] array = new Ville[5];

        for (int i=0; i<array.length; i++)
            if (i%2 == 0)
                try {
                    array[i] = new Ville(nomVilles[i], nbrHab[i], nomPays[i]);
                } catch (NbrHabException e) {}
                finally {
                    if (array[i] == null)
                            array[i] = new Ville();
                }
            else
                try {
                    array[i] = new Capitale(nomVilles[i], nbrHab[i], nomPays[i], nomMonum[i]);
                } catch (NbrHabException e) {}
                finally {
                    if (array[i] == null)
                            array[i] = new Capitale();
                }

        for (Ville v: array)
            System.out.println(v.toString());
        System.out.println("Il existe "+Ville.nbrVilles+" villes");
    }
}
