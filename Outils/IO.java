package Outils;
import java.util.Scanner;
import java.lang.Math;

public class IO {

    private static String[] def      = {"un booleen (oui/non)",
                                        "un nombre entier relatif (-1, 0, 1, 2, 3...)",
                                        "un entier signe sur 4 bytes (int dans [-"+   (int)(Math.pow(2, 32)-1) +"; "+ (int)Math.pow(2, 32) +"])",
                                        "un reel signe sur 8 bytes (double dans [-"+  (int)(Math.pow(2, 64)-1) +"; "+ (int)Math.pow(2, 64) +"])",
                                        "une lettre (a-z/A-Z)",
                                        "une chaine de caracteres"};

    public static boolean getBoolean(String ask, String[] check) {
        Scanner scanner = new Scanner(System.in);
        String  input   = new String();

        while (true) {
            System.out.print(ask);
            input = scanner.nextLine();

            if (input.equals(check[0]))
                return true;
            else if (input.equals(check[1]))
                return false;

            System.out.println("Erreur: entrez "+ check[0] +" ou "+ check[1]);
        }
//        return true;                                   UnreachableStatement
    }

    public static boolean getBoolean(String ask) {
        return getBoolean(ask, new String[] {"oui", "non"});
    }

    public static boolean getBoolean() {
        return getBoolean("Entrez "+ def[0] +": ", new String[] {"oui", "non"});
    }


    public static int getInt(int inf, int sup, String ask) {
        Scanner scanner    = new Scanner(System.in);
        boolean done       = false;
        int i              = 0;
        int input          = 0;
        int[] inter        = {};
        String errorString = "";

        if (inf >= sup)
            throw new java.lang.NegativeArraySizeException();
        for (i=0; i<(sup-inf); i++) {
            inter[i] = inf;
            inf++;
        }

        while (!done) {
            System.out.print(ask);
            try {
                input = scanner.nextInt();
            } catch (java.util.InputMismatchException error) {
                errorString = "" + error;
                System.out.println((errorString.equals("java.util.InputMismatchException")? "Erreur: entrez "+ def[1]: "Erreur: entrez "+ def[2]));
                scanner.next();
                continue;
            }
            for (int ele : inter) {
                if (ele == input)
                    done = true;
            }
            if (!done)
                System.out.println(input +" n'est pas compris dans ["+ inf +"; "+ sup);
        }
        return input;
    }

    public static int getInt(int inf, int sup) {
        return getInt(inf, sup, "Entrez "+ def[1] +" compris entre "+ inf +" et "+ sup +": ");
    }


    public static int getInt(String infNotDefined, int sup, String ask) {
        Scanner scanner    = new Scanner(System.in);
        boolean done       = false;
        int input          = 0;
        String errorString = "";

        while (!done) {
            System.out.print(ask);
            try {
                input = scanner.nextInt();
            } catch (java.util.InputMismatchException error) {
                errorString = "" + error;
                System.out.println((errorString.equals("java.util.InputMismatchException")? "Erreur: entrez "+ def[1]: "Erreur: entrez "+ def[2]));
                scanner.next();
                continue;
            }
            if (input <= sup)
                done = true;
            else
                System.out.println("Erreur:"+ input +" est strictement superieur a "+ sup);
        }
        return input;
    }

    public static int getInt(String infNotDefined, int sup) {
        return getInt("None", sup, "Entrez "+ def[1] +" inferieur a "+ sup +": ");
    }


    public static int getInt(int inf, String supNotDefined, String ask) {
        Scanner scanner    = new Scanner(System.in);
        boolean done       = false;
        int input          = 0;
        String errorString = "";

        while (!done) {
            System.out.print(ask);
            try {
                input = scanner.nextInt();
            } catch (java.util.InputMismatchException error) {
                errorString = "" + error;
                System.out.println((errorString.equals("java.util.InputMismatchException")? "Erreur: entrez "+ def[1]: "Erreur: entrez "+ def[2]));
                scanner.next();
                continue;
            }
            if (input >= inf)
                done = true;
            else
                System.out.println("Erreur: "+ input +" est strictement inferieur a "+ inf);
        }
        return input;
    }

    public static int getInt(int inf, String supNotDefined) {
        return getInt(inf, "None", "Entrez "+ def[1] +" superieur a "+ inf +": ");
    }


    public static int getInt(String ask) {
        Scanner scanner    = new Scanner(System.in);
        boolean done       = false;
        int input          = 0;
        String errorString = "";

        while (!done) {
            System.out.print(ask);
            try {
                input = scanner.nextInt();
                done = true;
            } catch (java.util.InputMismatchException error) {
                errorString = "" + error;
                System.out.println((errorString.equals("java.util.InputMismatchException")? "Erreur: entrez "+ def[1]: "Erreur: entrez "+ def[2]));
                scanner.next();
                continue;
            }
        }
        return input;
    }

    public static int getInt() {
        return getInt("Entrez "+ def[1] +": ");
    }


    public static String getString(String ask) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ask);
        return scanner.nextLine();
    }

    public static String getString() {
        return getString("Entrez "+ def[5] +": ");
    }

}