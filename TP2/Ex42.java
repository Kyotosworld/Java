package TD2;
import java.util.Scanner;

public class Ex42 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int factorielle = 0;
        long f = 1;
        int i = 0;

        System.out.print("Calculer la factorielle d'un nombre entre 1 et 20: ");
        factorielle = sc.nextInt();
        if (factorielle > 20)
            factorielle = 20;
        for (i=factorielle; i>0; i--)
            f *= i;
        System.out.println(factorielle +"! = "+ f);
    }
}
