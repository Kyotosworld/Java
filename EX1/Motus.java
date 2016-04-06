package EX1;

import Outils.IO;

public class Motus {

    public static final int ESSAIS = 6;                 // nombre de tentatives autorisées
    public static final char AUTRE_POSITION = '+';      // caractère entré présent dans le mot, à une autre position
    public static final char ABSENT = '!';              // caractère entré absent du mot


    /** main
     *  Methode principale du jeu
     *  Définit les variables, initialise le tableau et lance la boucle
     *  d'exécution, affiche le succès ou la défaite du joueur.
     */
    public static void main(String[] args) {
    
        int tentative = 0;                              // tentative actuelle
        boolean trouve = false;                         // mot secret trouvé
        String mot;                                     // mot secret
        String essai;                                   // proposition du joueur
        char[][] tab;                                   // tableau de caractères


        // Initialisation du mot secret et du tableau en conséquence
        mot = IO.getString("Entrez le mot secret: ");
        tab = new char[ESSAIS][mot.length()];
        for (int i=0; i<ESSAIS; i++)
            for(int j=0; j<mot.length(); j++)
                // le tableau est rempli avec des .
                tab[i][j] = '.';


        // BOUCLE PRINCIPALE: tant que le joueur n'a pas trouvé
        //                    et qu'il lui reste des tentatives
        while (!trouve && tentative < ESSAIS) {
            // "Interface"
            System.out.println();
            System.out.println("Tentatives restantes: "+ (ESSAIS-tentative));
            // On récupère ce qu'entre l'utilisateur
            essai = IO.getString("Mot en "+mot.length()+" lettres: ");

            // Traitement de la réponse de l'utilisateur
            traitement(tab, mot, essai, tentative);

            // Affichage du tableau modifié
            affichage(tab);

            if (mot.equals(essai))
                trouve = true;
            
            tentative++;
        }
        // Une fois sorti de la boucle, on regarde s'il a réussi
        if (trouve) {
            System.out.println("Bravo !");
            System.out.println("Vous avez trouvé le mot secret en "+tentative+" tentatives.");
        }
        else {
            System.out.println("Raté !");
            System.out.println("Le mot était "+mot);
        }
    }


    /** methode traitement
     *  Modifie le tableau de caractères en fonction de la proposition de
     *  l'utilisateur
     * @param tab le tableau de caractères à modifier simplement par référence
     * @param mot mot secret
     * @param essai proposition de l'utilisateur
     * @param tentative tentative actuelle = ligne du tableau à modifier
     */
    public static void traitement(char[][] tab, String mot, String essai, int tentative) {
        if(essai.length() != mot.length())
            System.out.println("Erreur: veuillez entrer un mot de "+mot.length()+" lettres.\nTentative perdue...");
        else {
            for(int j=0; j<mot.length(); j++)                   // on modifie tous les caractères de la ligne
                if (mot.charAt(j) == essai.charAt(j))
                    tab[tentative][j] = essai.charAt(j);        // JUSTE: on affiche le caractère
                else if (contient(mot, essai.charAt(j)))
                    tab[tentative][j] = AUTRE_POSITION;         // MAUVAISE POSITION: on affiche un +
                else
                    tab[tentative][j] = ABSENT;                 // CARACTERE ABSENT DU MOT: on affiche un !
        }
    }

    /** methode contient
     *  Verifie qu'un mot contient un caractère
     * @param mot le mot à tester
     * @param c le caractère à chercher
     * @return true si le caractère est présent dans le mot
     */
    public static boolean contient(String mot, char c) {
        boolean b = false;
        for (int i=0; i<mot.length(); i++)
            if(mot.charAt(i) == c)
                b = true;
        return b;
    }

    /** methode affichage
     *  Affiche le tableau de caractères en entier, en espaçant les caractères
     * @param tab le tableau à afficher
     */
    public static void affichage(char[][] tab) {
        for(int i=0; i<ESSAIS; i++) {
            for(int j=0; j<tab[0].length; j++)
                System.out.print(tab[i][j]+" ");
            System.out.println();
        }
    }
}
