package untitled;

public class Ville extends Object {
    
    private String ville  = "";
    private String pays   = "";
    private int habitants = 0;

    public static int nbrVilles = 0;


    public Ville(String ville, String pays, int habitants) {
        System.out.println("Instance de Ville créée !");
        this.ville = ville;
        this.pays = pays;
        this.habitants = habitants;
        nbrVilles++;
    }

    public void description() {
        System.out.println(ville +" est une ville de "+ habitants +" située en "+ pays);
    }
}