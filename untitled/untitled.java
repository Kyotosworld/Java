/* Voilà le fichier de test
 * Untitled.java
 */

public class untitled {
    // Voilà un commentaire sur une seule ligne

    public static void main(String[] args) {
        System.out.print("Hello World !\n");
        // c'est égal à
        System.out.println("Hello World !");

        int i = 0;
        int j = 567;
        int k = i+j;
        i = j = k = 567;
        System.out.println("Le résultat est j = "+j);

        boolean trueOrFalse = false;
        byte temperature = 127;

        short vitesse = 32767;

        int tempe = 284949827;
        float pi = 3.14f;                                                   // f est important : 0.5 est par défaut un double

        long anneeLumiere = 44345679;
        double unTiers = 0.33333333333333333;

        char caract = 'A';
        String maChaine = "Voici ma chaîne de caractères";
        String maChaine2 = new String("a");
        String maChaine3 = new String();

        System.out.println(maChaine+maChaine3+maChaine2);
    }
}
