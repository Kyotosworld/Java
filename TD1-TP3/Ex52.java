package TD3;
import java.util.Scanner;

public class Ex52 {
    public static void main(String[] args) {
        int resultat = 0;
        int essai = 0;
        int borneSup = 1000;
        int borneInf = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("Algorithme décrivant la méthode de dichotomie: pensez à un nombre entre 0 et 999");
        System.out.println("Si le nombre que je propose est inférieur à votre nombre entrez 0");
        System.out.println("Si le nombre que je propose est supérieur à votre nombre entrez 1");
        System.out.println("Enfin si j'ai deviné le bon nombre, entrez 2");

        while (resultat != 2) {
            essai = (int)((borneSup + borneInf)/2);
            System.out.print("Je pense au nombre "+ essai +" (0/1/2): ");
            resultat = sc.nextInt();
            switch (resultat) {
                case 0:
//                  System.out.println("Le nombre secret est plus grand que "+ essai);
                    borneInf = essai;
                    break;
                case 1:
//                  System.out.println("Le nombre secret est plus petit");
                    borneSup = essai;
                    break;
                case 2:
                    System.out.println("Oui ! J'ai trouvé le nombre secret !");
                    break;
            }
        }
    }
}
