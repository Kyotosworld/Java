package untitled.heritage;

public class Ville extends Object {
    
    private String ville;
    private String pays;
    private int habitants;

    public static int nbrVilles = 0;


    public Ville(String ville, int habitants, String pays) throws NbrHabException {
        this.ville = ville;
        this.pays = pays;
        if (habitants < 0) {
            throw new NbrHabException();
        }
        this.habitants = habitants;
        nbrVilles++;
    }
    public Ville() {
        this.ville = "Inconnu";
        this.habitants = 0;
        this.pays = "France";
        nbrVilles++;
    }

    public String toString() {
        return this.ville +" est une ville de "+ this.habitants +" habitants située en "+ this.pays;
    }
}