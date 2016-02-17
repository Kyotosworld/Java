package TD1_TP3;

public class Ex2 {
    public static void main(String[] args) {
        final int n = 200;
        int u = 500;
        int i = 0;

        for (i=0; i<n; i++) {
            if (u%2 == 1)
                u = 3*u + 1;
            else
                u = (int)(u/2);
            System.out.println("u("+ i +") = "+ u);
        }
    }
}
