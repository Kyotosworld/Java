public class Ex1 {

    public static void main(String[] args) {

    // Q1
    DroiteAffine d1 = new DroiteAffine(3, 2);
    DroiteAffine d2 = new DroiteAffine(-2, 3, -2);
    DroiteAffine d3 = new DroiteAffine(1, 2, 3, 8);
    System.out.println(d1 +"\n"+ d2 +"\n"+ d3);

    // Q2
    if(d1.getA() >= d2.getA())
        System.out.println(d1.getA() +"\t"+ d2.getA());
    else
        System.out.println(d2.getA() +"\t"+ d1.getA());

    // Q3
    d3.setB(42);
    System.out.println(d3);

    // Q4
    System.out.println(d1.calculeOrdonnee(3.5));
    System.out.println(d2.calculeOrdonnee(3.5));

    // Q5
    System.out.println(d1.estParalleleA(d2)?"Parallèles.":"Non parallèles.");
    System.out.println(d2.estParalleleA(d3)?"Parallèles.":"Non parallèles.");
    System.out.println(d1.estParalleleA(d3)?"Parallèles.":"Non parallèles.");

    // Q6
    System.out.println(d1.abscisseIntersectionAvec(d2) +";"+ d1.calculeOrdonnee(d1.abscisseIntersectionAvec(d2)));

    }


}
