package TD5;

public class Ex2 {

    public static double solde;
    public static final double TAUX_INTERETS = 2.5;
    public static final double TAXE_INTERETS = 30;

    public static void main(String[] args) {
        double totalInterets = 0;
        ouvrirCompte(2000);

        // Janvier
        debiterCompte(200);
        totalInterets += calculerInteretsMois();

        // Février
        crediterCompte(500);
        totalInterets += calculerInteretsMois();

        // Mars
        debiterCompte(1000);
        totalInterets += calculerInteretsMois();

        // Avril
        crediterCompte(800);
        debiterCompte(350);
        totalInterets += calculerInteretsMois();

        // Mai
        totalInterets += calculerInteretsMois();

        // Juin
        crediterCompte(200);
        totalInterets += calculerInteretsMois();

        // Juillet
        debiterCompte(135);
        totalInterets += calculerInteretsMois();

        // Aout
        crediterCompte(400);
        totalInterets += calculerInteretsMois();

        // Septembre
        crediterCompte(200);
        totalInterets += calculerInteretsMois();

        // Octobre
        debiterCompte(100);
        totalInterets += calculerInteretsMois();

        // Novembre
        debiterCompte(150);
        totalInterets += calculerInteretsMois();

        // Decembre
        crediterCompte(300);
        totalInterets += calculerInteretsMois();

        solde += calculerInteretsNets(totalInterets);
        afficherCompte();
    }

    public static void afficherCompte() {
        System.out.println("Le compte possède un solde de "+ solde +"euros ");
    }
    public static void ouvrirCompte(double versement) {
        solde = versement;
    }
    public static void crediterCompte(double montant) {
        solde += montant;
    }
    public static void debiterCompte(double montant) {
        if (montant <= solde)
            solde -= montant;
    }
    public static double calculerInteretsMois() {
        return (TAUX_INTERETS/100)*solde;
    }
    public static double calculerInteretsNets(double interetsBruts) {
        return (100-TAXE_INTERETS)*interetsBruts/100;
    }
}
