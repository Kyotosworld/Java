package TD2;

public class Ex1 {
    public static void main(String[] args) {
        final int R = 50000;
        final int N = 2;
        int impot = 0;
        double rapport = R/N ;

        if ((rapport > 0) && (rapport <= 20000))
            impot = (int) (R*0.125 - 50*N);
        else if ((rapport > 20000) && (rapport <= 30000))
            impot = (int) (R*0.25 - 100*N);
        else if (rapport > 30000)
            impot = (int) (R*0.5 - 200*N);

        System.out.println("Pour un revenu annuel de " + R + " euros, avec " + N + " parts au foyer :");
        System.out.println("L'impot est de " + impot + " euros");
    }
}
