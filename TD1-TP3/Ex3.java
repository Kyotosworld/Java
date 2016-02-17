package TD3;

public class Ex3 {
    public static void main(String[] args) {
        final int n = 20;
        double u = 0.333333333333333333333;
        int i = 0;

        for (i=0; i<20; i++) {
            u = 7*u - 2;
            System.out.println("u("+ i +") = "+ u);
        }
    }
}
