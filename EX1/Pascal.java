public class Pascal {

    public static void main(String[] args) {
        pascal(20);
    }

    public static void pascal(int n) {
        int[][] t = new int[n+1][];
        for(int i=0; i<=n; i++) {
            t[i] = new int[i+1];
            t[i][0] = 1;
            t[i][i] = 1;
        }
        for (int i=2; i<=n; i++)
            for(int j=1; j<i; j++)
                t[i][j] = t[i-1][j-1] + t[i-1][j];
        for (int i=0; i<=n; i++) {
            for(int j=0; j<=i; j++)
                System.out.print(t[i][j]+" ");
            System.out.println();
        }
    }

}
