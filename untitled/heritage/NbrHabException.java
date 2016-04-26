package untitled.heritage;

class NbrHabException extends Exception {
    
    public NbrHabException() {
        System.out.println("-------------------------------------------------------");
        System.out.println("| Ville instanciée avec un nombre d'habitants négatif |");
        System.out.println("-------------------------------------------------------");
    }
}