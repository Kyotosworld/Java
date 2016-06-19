package TD1_TP3;

public class Ex1 {
    public static void main(String[] args) {
        final int n = 15;
        int s = 0;
        int i = 1;

        for (i=0; i<=n; i++)
            s += i;
        System.out.println("Somme avec for: s = " + s);

        s = 0;
        i = 1;
        while (i<=n) {
            s += i;
            i++;
        }
        System.out.println("Somme avec while: s = " + s);

        s = 0;
        i = 1;
        do {
            s += i;
            i++;
        } while (i<=n);
        System.out.println("Somme avec do while: s = " + s);
    }
}
