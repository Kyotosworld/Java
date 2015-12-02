package untitled;

class Capitale extends Ville{

    private String monument;

    public Capitale(String ville, int habitants, String pays, String monument) {
        super(ville, habitants, pays);
        this.monument = monument;
    }
    public Capitale(String ville, int habitants, String pays) {
        this(ville, habitants, pays, "inconnu");
    }

    public String toString(){
        return " et dont le monument est "+ this.monument;
    }
    public void hello() {
        System.out.println("hello");
    }
}