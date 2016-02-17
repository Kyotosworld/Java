package TP2;

public class Ex41 {
    public static void main(String[] args) {
        int factorielle = 1;
        int i = 5;
        for (i=5; i>0; i--)
            factorielle *= i;
        System.out.println("5! = "+ factorielle);
    }
}
