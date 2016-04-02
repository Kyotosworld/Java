import java.lang.Math;

public class GameOfLife {

    public static void afficherMonde(boolean[][] monde) {
        for (int i=0; i<monde.length; i++) {
            for (int j=0; j<monde[0].length ;j++)
                System.out.print(" "+(monde[i][j]? "X": "")+" ");
            System.out.println("");
        }
    }

    public static boolean[][] genererMonde(int x, int y) {
        boolean[][] monde = new boolean[x][y];
        for(int i=1; i<x-1; i++)
            for(int j=1; j<y-1; j++)
                monde[i][j] =false;
        return monde;
    }


    public static boolean[][] genererMondeAleatoire(int x, int y) {
        boolean[][] monde = new boolean[x][y];
        for(int i=1; i<x-1; i++)
            for(int j=1; j<y-1; j++)
                monde[i][j] = (Math.random() >= 0.40)? true: false;
        return monde;
    }

    public static int nombreDeVoisins(boolean[][] monde, int x, int y) {
        int voisins = 0;
        if (x>1) {
                if (y>1)
                    voisins = (monde[x-1][y-1])? voisins+1: voisins;
                voisins = (monde[x-1][y])? voisins+1: voisins;
                if (y<monde[0].length-2)
                    voisins = (monde[x-1][y+1])? voisins+1: voisins;
            }
        if (y>1)
            voisins = (monde[x][y-1])? voisins+1: voisins;
        if (y<monde[0].length-2)
            voisins = (monde[x][y+1])? voisins+1: voisins;
        if (x<monde.length-2) {
                if (y>1)
                    voisins = (monde[x+1][y-1])? voisins+1: voisins;
                voisins = (monde[x+1][y])? voisins+1: voisins;
                if (y<monde[0].length-2)
                    voisins = (monde[x+1][y+1])? voisins+1: voisins;
            }
        return voisins;
    }
    
    public static boolean devientVivant(boolean[][] monde, int x, int y) {
        int v = nombreDeVoisins(monde, x, y);
        if (v == 3 || (v == 2 && monde[x][y]))
            return true;
        else
            return false;
        /*if (v<2 || v>3)
            return false;
        else if (v==2)
            return monde[x][y];
        else if (v==3)
            return true;
        return false;*/
    }
    
    public static boolean[][] itererMonde(boolean[][] monde) {
        boolean[][] nMonde = new boolean[monde.length][monde[0].length];
        for(int i=0; i<monde.length; i++)
            for(int j=0; j<monde[0].length; j++)
                nMonde[i][j] = devientVivant(monde, i, j);
        return nMonde;
    }
    
    public static void main(String[] args) {
        boolean[][] m = genererMondeAleatoire(20,20);

        // Glider
        m[11][11] = true;
        m[12][12] = true;
        m[13][12] = true;
        m[13][11] = true;
        m[13][10] = true;

        for (int i=1; i<1000; i++) {
            Affichage.afficherMonde(m);
            m = itererMonde(m);
        }
    }
}

