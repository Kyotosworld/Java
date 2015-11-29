package untitled;

public class untitled extends Object {

    public static void main(String[] args) {
        Ville a = new Ville("a", "b", 12);
        Ville b = new Ville("a", "b", 12);
        Ville c = new Ville("a", "b", 12);

        a.description();
        System.out.println(Ville.nbrVilles);
    }
}
