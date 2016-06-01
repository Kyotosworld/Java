package TD1_TP3;

public class Ex2bis {
    public static void main(String[] args) {
        final int n = 15;
        int u = 500;
        int i = 0;

        while (u != 1) {
            if (u%2 == 1)
                u = 3*u + 1;
            else
                u = (int)(u/2);
            i++;
            System.out.println("u("+ i +") = "+ u);
        }
    }
}
