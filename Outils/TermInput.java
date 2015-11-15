package Outils;
import java.util.Scanner;
import java.lang.Math;

public class TermInput {

    private static Scanner  scanner  = new Scanner(System.in);
    private static boolean  done     = false;
    private static String[] sentDef  = {"Entrez un booléen (true/false): ",
                                "Entrez un entier signé sur un byte (byte sur [-"+  (int)(Math.pow(2, 8) -1) +"; "+ (int)Math.pow(2, 8)  +"]): ",
                                "Entrez un entier signé sur 2 bytes (short sur [-"+ (int)(Math.pow(2, 16)-1) +"; "+ (int)Math.pow(2, 16) +"]): ",
                                "Entrez un entier signé sur 4 bytes (int sur [-"+   (int)(Math.pow(2, 32)-1) +"; "+ (int)Math.pow(2, 32) +"]): ",
                                "Entrez un entier signé sur 8 bytes (long sur [-"+  (int)(Math.pow(2, 64)-1) +"; "+ (int)Math.pow(2, 64) +"]): ",
                                "Entrez un réel signé sur 4 bytes (float sur [-"+   (int)(Math.pow(2, 32)-1) +"; "+ (int)Math.pow(2, 32) +"]): ",
                                "Entrez un réel signé sur 8 bytes (double sur [-"+  (int)(Math.pow(2, 64)-1) +"; "+ (int)Math.pow(2, 64) +"]): ",
                                "Entrez une lettre (a-z/A-Z): ",
                                "Entrez une chaîne de caractères: "};

    public static boolean getBoolean() {
        String input = new String();
        while (!done) {
            System.out.print(sentDef[0]);
            input = scanner.nextLine();
            if (input.equals("true")) {
                done = true;
                return true;
            }
            else if (input.equals("false")) {
                done = false;
                return false;
            }
            else
                System.out.println("Erreur: L'entrée ne correspond pas au type spécifié");
        }
        return false;                                   // Normalement jamais atteint
    }

    public static int getInt() {
        int input = 0;
        while (!done) {
            System.out.print(sentDef[3]);
            try {
                input = scanner.nextInt();
                done = true;
            } catch (java.util.InputMismatchException e) {
                if (e.equals("java.InputMismatchException"))
                    System.out.println("L'entrée ne correspond pas au type demandé");
                else
                    System.out.println("L'entrée n'est pas comprise dans l'intervalle spécifié");
                scanner.next();
                continue;
            }
        }
        return input;
    }

    public static String getString() {
        System.out.print(sentDef[8]);
        String input = scanner.nextLine();
        return input;
    }
}