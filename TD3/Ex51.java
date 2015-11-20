package TD3;
import java.util.Scanner;
import java.lang.Math;

public class Ex51 {
    public static void main(String[] args) {
        final int resultat = (int)(Math.random()*1000);
        int essai = 0;
        Scanner sc = new Scanner(System.in);

        while (essai != resultat) {
            System.out.print("Entrez un nombre: ");
            essai = sc.nextInt();
            if (essai > resultat)
                System.out.println("Le nombre secret est plus petit");
            else if (essai < resultat)
                System.out.println("Le nombre secret est plus grand");
        }
        System.out.println("Bravo ! Le nombre secret était bien "+ resultat);
    }
}
