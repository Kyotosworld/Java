/**
 * TP 11 : Fourmi de Langton
 * La fourmi de Langton est automate cellulaire mettant en avant l'émergence de  comportement
 * à partir d'un ensemble de règles très simples.
 * On en propose ici une implémentation en Java à travers 3 classes: Fourmi, Plateau, AffichageFourmi
 * et Simulation
 * On imagine une fourmi se déplaçant sur un repère othonormé, divisé en cases 1x1, et ces dernières
 * peuvent être soit blanches soit noires. Le comportement de la fourmi dépend de la couleur de la case sur
 * laquelle elle se trouve, mais elle-même change la couleur des cases
 *@author Gabriel Forien
 */
package TP11;
import Outils.IO;

/**
 * Classe Simulation
 * Gère et coordonne l'ensemble {Fourmi, Plateau, AffichageFourmi} pour que l'utilisateur puisse
 * faire l'expérience de la fourmi de Langton
 */
public class Simulation {

    public static void main(String[] args) {
        int taille, milieu;
        Fourmi f;
        Plateau p;

        System.out.println("**********************************");
        System.out.println("* TP Java 11 : Fourmi de Langton *");
        System.out.println("**********************************\n");
        taille = Outils.IO.getInt(2, "", "Entrez la taille du tableau : ");
        AffichageFourmi.setDelay(Outils.IO.getInt(1, "", "Entrez la durée d'un cycle, en ms : "));

        // on veut placer la fourmi au milieu du tableau, le milieu dépend de la parité de la taille
        // taille impaire : milieu = (taille/2)
        // taille paire : milieu = taille/2 -1;
        milieu = (taille%2 == 1)? taille/2: taille/2 -1;

        // on veut une fourmi orientée vers le haut
        f = new Fourmi(milieu, milieu, 0);

        p = new Plateau(taille, f);
        while (true) {
            AffichageFourmi.afficherMonde(p);
            p.update();
        }
    }

}