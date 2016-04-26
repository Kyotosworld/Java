public class Pion {

    private String etat = "p";
    private boolean couleur;

    /** Pion
     *  Appelé sans paramètres, couleur = blanc
     *  De même que par défaut, etat    = pion
     * @param couleur true = blanc; false = noir
     * */
    public Pion(boolean couleur) {
        this.couleur = couleur;
    }
    public Pion() {
        this(true);
    }

    /** evolution
     *  Quand un pion atteint le bout du plateau de dames, il devient
     *  une dame, symbolisé par "D"
     */
    public void evoluer() {
        this.etat = "D";
    }

    /** toString
     *  <!>FONCTIONNE UNIQUEMENT DANS UN TERMINAL LINUX<!>
     *  Le code couleur est de la forme \033[0;3Xm
     *  ou X est la couleur choisie, notamment 9 = blanc, 1 = rouge
     *  On doit terminer la séquence de caractère colorés par un code de fin
     *  qui remet à zéro les couleurs
     * @return la représentation du pion : p ou D selon son etat
     *         et coloré selon la couleur du pion
     **/
    public String toString() {
        String codeCouleur = (this.couleur)? "\033[0;39m": "\033[0;31m";
        String codeFin     = "\033[0;00m";
        return codeCouleur+this.etat+codeFin;
    }

}
