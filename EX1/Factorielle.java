import java.util.Scanner;

public class Factorielle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;

        System.out.println("5! = "+factorielle5());
        
        System.out.print("Entrez n: ");
        n = sc.nextInt();
        System.out.println(n+"! = "+factorielle(n));
    }

    public static int factorielle5() {
        return 1*2*3*4*5;
    }

    public static int factorielle(int n) {
        int f = 1;
        for(; n>1; n--)
            f *= n;
        return f;
    }
}
