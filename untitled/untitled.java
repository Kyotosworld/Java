package untitled;

public class untitled extends Object {

    public static void main(String[] args) {
        String[] nomVilles = {"Paris", "Berlin", "Rome",
                              "Copenhague", "Londres"};
        String[] nomPays = {"France", "Allemagne", "Italie",
                            "Pays-Bas", "Angleterre"};
        String[] nomMonum = {"la Tour Eiffel", "la Porte de Brandebourg", "la Chappelle Sixtine",
                             "le Big Ben", "(le monument de Copenhague)"};
        int[] nbrHab = {100000, 200000, 300000, 400000, 500000, 600000};

        Ville[] array = new Ville[5];

        for (int i=0; i<array.length; i++) {
            if (i%2 == 0)
                array[i] = new Ville(nomVilles[i], nbrHab[i], nomPays[i]);
            else
                array[i] = new Capitale(nomVilles[i], nbrHab[i], nomPays[i], nomMonum[i]);
        }

        for (Object v: array)
            System.out.println(v.toString());
        ((Capitale)array[0]).hello();
        System.out.println(Ville.nbrVilles);
    }
}
