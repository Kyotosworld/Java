package untitled;

public class Ville extends Object {
    
    private String ville;
    private String pays;
    private int habitants;

    public static int nbrVilles = 0;


    public Ville(String ville, int habitants, String pays) {
        this.ville = ville;
        this.pays = pays;
        this.habitants = habitants;
        nbrVilles++;
    }
    public Ville() {
        this("Inconnu", 0, "France");
    }

    public String toString() {
        return this.ville +" est une ville de "+ this.habitants +" habitants située en "+ this.pays;
    }
}