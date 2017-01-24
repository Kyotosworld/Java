
/*-------------------------------------------------------------------------------------------------------*/
    /**--------------------------------------------------------------------------------------------------
     * main
     * Crée un polynôme et teste les fonctionnalités de la classe
     *
     * @param args Arguments reçus de la ligne de commande
     *---------------------------------------------------------------------------------------------------*/
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("-                       Polynome.java                        -");
        System.out.println("--------------------------------------------------------------");
        System.out.println("");

        Polynome p1 = new Polynome(new double[] {-6, -8, 1, 4, 1});

        System.out.println("Q1)");
        System.out.println("p1 : x |-> " + p1);
        System.out.println("");

        System.out.println("Q2)");
        System.out.println(valPoly(p1, 528.56456));
        System.out.println(valPolyHorner(p1, 528.56456));
        System.out.println("");

        System.out.println("Q3)");
        Polynome p2 = derivePoly(p1);
        System.out.println("p2 : x |-> " + p2);
        System.out.println("");

        System.out.println("Q4)");
        String e = "\u03b5";
        System.out.println(e+"=10E-4\tx0 = -4\t\tp1(x)=0 => x= " + String.valueOf(polyNewton(p1, -4, 0.0001)));
        System.out.println(e+"=10E-3\tx0 = -2\t\tp1(x)=0 => x= " + String.valueOf(polyNewton(p1, -2, 0.1)));
        System.out.println(e+"=10E-5\tx0 =  0\t\tp1(x)=0 => x= " + String.valueOf(polyNewton(p1, 0, 0.1)));
        System.out.println(e+"=10E-7\tx0 =  2\t\tp1(x)=0 => x= " + String.valueOf(polyNewton(p1, 2, 0.1)));
        System.out.println("");

        System.out.println("Q5)");
        String sommeAN = "\u03a3\u2090\u207f";
        System.out.println("a=-5\tn=5\tN=100000\t"+sommeAN+"p1(a)*(n-a)/N = " + String.valueOf(integreRectangle(p1, -5, 5, 100000)));
        System.out.println("");

        System.out.println("Q6)");
        String integraleAN = "\u222b\u2090\u207f";
        System.out.println("a=-5\tn=5\t\t\t"+integraleAN+"p1(t)dt = " + integrePoly(p1, -5, 5));
        System.out.println("");

        System.out.println("Q7)");
        String deltaStr = "\u0394";
        Polynome p3 = new Polynome(new double[] {5, 16, -9, 1});
        double IExacte = integrePoly(p3, 0, 7);
        double IApprox = integreRectangle(p3, 0, 7, 50);
        double erreur = IExacte - IApprox;
        System.out.println("p3 : x |-> " + p3);
        System.out.println(integraleAN + " = " + IExacte);
        System.out.println(sommeAN + " = " + IApprox);
        System.out.println(deltaStr + " = " + erreur);
        System.out.println("");

        System.out.println("Q8)");
        System.out.println("Erreur.csv");
        System.out.println("--------------------------------------------------------------");
        System.out.println("N; f(N); erreur");
        for(int n=50; n<450; n+=5) {
            System.out.println(n+"; " + integreRectangle(p3,0,7,n)+"; " + ((integrePoly(p3,0,7) - integreRectangle(p3,0,7,n)*1000))+"; ");
        }
        System.out.println("--------------------------------------------------------------");
        System.out.println("");

    } /* FIN main */