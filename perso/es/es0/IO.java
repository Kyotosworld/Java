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

    /** Methode getBoolean: recoit la confirmation ou le refus de l'utilisateur
     * @param ask la demande affichee a l'utilisateur
     * @param check la confirmation et le refus que doit entrer l'utilisateur
     *              (oui, yes, Yes, true, VRAI, etc..)
     * @return la confirmation ou le refus de l'utilisateur
     */
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
    }

    public static boolean getBoolean(String ask) {
        return getBoolean(ask, new String[] {"oui", "non"});
    }

    public static boolean getBoolean() {
        return getBoolean("Entrez "+ def[0] +": ", new String[] {"oui", "non"});
    }

    /** Methode getInt: recoit un nombre entier entre par l'utilisateur
     *  Les bornes specifiees sont incluses dans l'intervalle
     *  Elle est principalement definie par 4 methodes, il n'existe pas une
     *  seule methode principale qui est surchargee
     *  De plus, ces 4 methodes sont chacunes surchargees d'une methode, dans le
     *  cas ou le parametre ask n'est pas specifie.
     *
     *  Methode 1: entier dans [inf, sup]
     *             Sans specifier le prompt on l'appelle ainsi:
     *             int a = getInt(0, 100);
     *  Methode 2: entier inferieur ou egal a sup
     *             On l'appelle alors avec une chaine de caractere a la place parametre inf:
     *             int b = getInt("", 100);
     *  Methode 3: entier superieur ou egal a inf
     *             De la meme maniere:
     *             int c = getInt(0, "");
     *  Methode 4: entier relatif
     *             int d = getInt();
     */

    /** Surcharge getInt n°1
     * @param inf       la borne inferieure de l'intervalle des valeurs acceptees
     * @param sup       la borne superieure
     * @param ask       le message affiche a l'utilisateur (prompt)
     * @return          un entier appartenant a [inf; sup]
     */
    public static int getInt(int inf, int sup, String ask) {
        Scanner scanner    = new Scanner(System.in);
        boolean done       = false;
        int input          = 0;
        String errorString = "";

        if (inf >= sup)
            throw new java.lang.NegativeArraySizeException();

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
            if (input >= inf && input <= sup)
                done = true;
            if (!done)
                System.out.println(input +" n'est pas compris dans ["+ inf +"; "+ sup +"]");
        }
        return input;
    }

    public static int getInt(int inf, int sup) {
        return getInt(inf, sup, "Entrez "+ def[1] +" compris entre "+ inf +" et "+ sup +": ");
    }


    /** Surcharge getInt n°2
     * @param infNotDefined     la borne inferieure n'existe pas
     * @param sup               la borne superieure
     * @param ask               le message affiche a l'utilisateur (prompt)
     * @return                  un entier inferieur ou egal a sup
     */
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


    /** Surcharge getInt n°3
     * @param inf               la borne inferieure
     * @param supNotDefined     la borne superieure n'existe pas
     * @param ask               le message affiche a l'utilisateur (prompt)
     * @return                  un entier superieur ou egal a inf
     */
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


    /** Surcharge getInt n°4
     * @param ask               le message affiche a l'utilisateur (prompt)
     * @return                  un nombre relatif
     */
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


    /** Methode getString: recoit une chaine de caractere
     * @param ask       le message affiche a l'utilisateur (prompt)
     * @return          la chaine de caractere entree
     */
    public static String getString(String ask) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ask);
        return scanner.nextLine();
    }

    public static String getString() {
        return getString("Entrez "+ def[5] +": ");
    }

}
