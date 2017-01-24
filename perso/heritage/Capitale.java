package untitled.heritage;

class Capitale extends Ville{

    private String monument;

    public Capitale(String ville, int habitants, String pays, String monument) throws NbrHabException {
        super(ville, habitants, pays);
        this.monument = monument;
    }
    public Capitale() {
        super();
        this.monument = "inconnu";
    }

    public String toString(){
        return super.toString() +" et dont le monument est "+ this.monument;
    }
    public void hello() {
        System.out.println("hello");
    }
}