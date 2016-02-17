package TD2;
import Outils.IO;
import java.util.Scanner;

public class Ex3 {

    public static void main(String[] args) {
        /* Déclaration et initialisation des constantes */
        /* Les poids sont en grammes */
        /* les prix en centimes d'euro */
        final int PRIX_KG_TOMATES   = 350;
        final int PRIX_KG_TOMME      = 1000;
        final int PRIX_KG_COMTE     = 1500;
        final int PRIX_KG_PATATES   = 20;

        final int PRIX_UNIT_YAOURT  = 50;
        final int PRIX_UNIT_ORANGE  = 21;
        final int PRIX_UNIT_PAIN    = 80;
        final int PRIX_UNIT_CAFE    = 110;

        final int POIDS_UNIT_YAOURT = 125;
        final int POIDS_UNIT_ORANGE = 150;
        final int POIDS_UNIT_PAIN   = 200;

        final boolean PROMOTION_TOMME_SAVOIE = false;

/*
        Scanner sc = new Scanner(System.in);
        int billets20 = sc.nextInt();
        int billets10 = sc.nextInt();
        int pieces2 = sc.nextInt();
*/
        int billets20 = Outils.IO.getInt(0, "None", "Combien ais-je de billets de 20€ ? ");
        int billets10 = Outils.IO.getInt(0, "None", "Combien ais-je de billets de 10€ ? ");
        int pieces2   = Outils.IO.getInt(0, "None", "Combien ais-je de pièces de 2€ ? ");

        int somme       = billets20*2000 + billets10*1000 + pieces2*200;                  //la somme qu'il y a dans mon porte monnaie
        int poidsPanier = 0;
        int coutOranges = 0;

        boolean acheteTomates = false;
        boolean acheteFromage = false;

        /* Début du programme */
        System.out.println("Je suis sur le marché...");
        System.out.println("*** Mon panier pèse "+ poidsPanier +"g et il me reste "+ somme +"c d'euros");

        /* Tomates */
        if (PRIX_KG_TOMATES < 200) {
            System.out.println("J'achète 3 Kg de tomates à " + PRIX_KG_TOMATES + "c le Kg");
            somme -= 3 * PRIX_KG_TOMATES;
            poidsPanier += 3000;
            acheteTomates = true;
        }

        if (PROMOTION_TOMME_SAVOIE) {
            System.out.println("J'achète 500 g de tomme à " + PRIX_KG_TOMME + "c le Kg");
            somme -= (int) 0.5 * PRIX_KG_TOMME;
            poidsPanier += 500;
            acheteFromage = true;
        } else if (PRIX_KG_COMTE < 1200) {
            System.out.println("J'achète 250 g de comté à " + PRIX_KG_COMTE + "c le Kg");
            somme -= (int) 0.25 * PRIX_KG_COMTE;
            poidsPanier += 250;
            acheteFromage = true;
        } else {
            System.out.println("J'achète 3 yaourts à " + PRIX_UNIT_YAOURT + "c l'unité");
            somme -= 3 * PRIX_UNIT_YAOURT;
            poidsPanier += 3 * POIDS_UNIT_YAOURT;
        }

        if ((somme > 10) || (acheteFromage == false))
            System.out.println("J'achète un café à " + PRIX_UNIT_CAFE + "c l'unité");
            somme -= PRIX_UNIT_CAFE;

        while (coutOranges <= 500) {
            somme -= PRIX_UNIT_ORANGE;
            poidsPanier += POIDS_UNIT_ORANGE;
            coutOranges += PRIX_UNIT_ORANGE;
            System.out.print("J'achète une orange à " + POIDS_UNIT_ORANGE + "c l'unité. ");
            System.out.println("J'ai dépensé " + coutOranges + "c en oranges");
        }

        if (!acheteTomates) {
            System.out.println("J'achète 3 Kg de pommes de terre à " + PRIX_KG_PATATES + "c le Kg");
            somme -= 3 * PRIX_KG_PATATES;
            poidsPanier += 3000;
        }
        if (acheteFromage) {
            System.out.print("J'achète une baguette de pain à " + POIDS_UNIT_PAIN + "c l'unité. ");
            somme -= PRIX_UNIT_PAIN;
            poidsPanier += POIDS_UNIT_PAIN;
        }

        System.out.println("*** J'ai terminé mon marché");
        System.out.println("*** Mon panier pèse " + poidsPanier +"g et il me reste " + somme + "c d'euro");
    }
}
