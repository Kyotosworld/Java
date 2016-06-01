package TD2_TP7;

public class Test {

    public static void main(String[] args) {
        String[] ex = new String[11];
        for (int i=0; i<ex.length; i++)
            ex[i] = "\n------------------------------------------------\n"
                   +"                Exercice "+i+"\n"
                   +"------------------------------------------------\n";

        System.out.print(ex[1]);
        Ex1.parcourirChaine(args[0]);

        System.out.print(ex[2]);
        System.out.println("Occurences de e: "+ Ex2.compterLaLettre(args[0], 'e'));

        System.out.print(ex[3]);
        System.out.println("Supprimer les e: "+ Ex3.supprCar(args[0], 'e'));

        System.out.print(ex[4]);
        System.out.println("Enlever caractères non-alphanumérques: "+ Ex4.enleverNonAlphanumerique(args[0]));

        System.out.print(ex[5]);
        System.out.println("Supprimer les e multiples: "+ Ex5.supprimerDoublons(args[0], 'e'));

        System.out.print(ex[6]);
        System.out.println("Nombre de mots: "+ Ex6.compterLesMots(args[0]));

        System.out.print(ex[7]);
        System.out.println("Chaine inversée: "+ Ex7.inverserChaine(args[0]));

        System.out.print(ex[8]);
        System.out.println("Est-ce un palindrome: "+ Ex8.estUnPalindrome(args[0]));

        System.out.print(ex[10]);
        System.out.println("Traduction en décimal: "+ Ex10.traduireUnBinaireEnDecimal(args[0]));
    }
}
