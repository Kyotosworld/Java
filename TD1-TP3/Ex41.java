package TD3;

public class Ex41 {
    public static void main(String[] args) {
        final int n = 50;
        double u = 1.0;
        int i = 0;

        for (i=0; i<n; i++) {
            u = 1 + (1/u);
            System.out.println("u("+ i +") = "+ u);
        }
    }
}
