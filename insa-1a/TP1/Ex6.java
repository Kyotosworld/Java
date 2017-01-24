package TP1;
import java.lang.Math;
import java.util.Scanner;

public class Ex6 {
    /*  Exercice 6 :
        Recoit en entree un mot-cle comme "carre" ou "racine"
        et applique cette operation au nombre qui suit
        - creation d'une instance de classe Scanner
        - recuperation du texte dans une chaine de caractere
        - test de la chaine de caracteres
        - operation
        - renvoi du resultat
    */
    public static int nbrOperations = 0;

    private int nombre = 0;
    private String operation = "";

    public Ex6() {
        boolean booleenNombre = false;
		int intOperation = 0;

        intOperation = traitementChaine();
    }

    public int traitementChaine() {
        boolean booleenOperation = false;
        Scanner scannerOperation = new Scanner(System.in);

        System.out.println("Voulez-vous effectuer un CARRE ou une RACINE ?");
        do {
            System.out.print("Effectuer un: ");
            this.operation = scannerOperation.nextLine();
            this.operation = this.operation.intern();         // on place la chaine de caractere dans le pool pour pouvoir la comparer
            if (this.operation == "CARRE" ^ this.operation == "RACINE") {
                booleenOperation = true;
            } else {
                System.out.println("Erreur: Vous devez entrer \"CARRE\" ou \"RACINE\" !");
            }
        } while (!booleenOperation);
		
		return (this.operation == "CARRE")? 1: 2;
    }
}
