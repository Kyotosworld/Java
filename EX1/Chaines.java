class DM2{

    public static void main(String [] args){ 
        // Question 1
        int nb1 = 12345 ; 
        String chaine1 = intToString(nb1) ; 
        System.out.println("Question 1 :") ; 
        System.out.println("Le nombre "+nb1+" est converti en "+chaine1+" qui est une chaine de caracteres.") ; 
        System.out.println() ; 
        
        // Question 2 
        String chaine2 = "12345" ; 
        int nb2 = stringToInt(chaine2) ; 
        System.out.println("Question 2 :") ; 
        System.out.println("La chaine de caracteres "+chaine2+" est converti en "+nb2+" qui est un entier.") ; 
        System.out.println() ; 
        
        // Question 3 
        String chaine3 = "12345" ; 
        float nb3 = stringToInt(chaine3) ; 
        System.out.println("Question 3 :") ; 
        System.out.println("La chaine de caracteres "+chaine3+" est converti en "+nb3+" qui est de type float.") ; 
        System.out.println() ; 
        
        // Question 4 
        String chaine4 = " Mars eille" ; 
        System.out.println("Question 4 :") ; 
        afficheMajuscules(chaine4) ; 
        System.out.println() ; 
        
        // Question 5 
        String chaine51 = "Une pierre" ; 
        String chaine52 = "Un ballon" ; 
        String chaine53 = " Une fleur" ; 
        String chaine54 = "Bonjour !" ; 
        System.out.println("Question 5 :") ; 
        String rep1 = memeDebut(chaine51,chaine52) ; 
        System.out.println("La reponse est "+rep1) ; 
        String rep2 = memeDebut(chaine51,chaine53) ; 
        System.out.println("La reponse est "+rep2) ;
        String rep3 = memeDebut(chaine51,chaine54) ; 
        System.out.println("La reponse est "+rep3) ;  
        System.out.println() ; 
        
        // Question 6
        String chaine61 = "abcd" ;
        String chaine62 = "abcd" ; 
        String chaine63 = "ABcd" ;
        System.out.println("Question 6 :") ; 
        comparaisonEgale(chaine61,chaine62) ; 
        comparaisonEgale(chaine61,chaine63) ;
        System.out.println() ;    
        
        // Question 7
        String chaine71 = "Un ballon" ; 
        String chaine72 = "Un" ; 
        String chaine73 = "on" ; 
        String chaine74 = " Un ballon." ;
        String chaine75 = "le" ;
        System.out.println("Question 7 :") ; 
        comparaisonChaine(chaine71,chaine72) ; 
        comparaisonChaine(chaine71,chaine73) ;
        comparaisonChaine(chaine74,chaine72) ;
        comparaisonChaine(chaine74,chaine73) ; 
        comparaisonChaine(chaine74,chaine75) ;   
        System.out.println() ; 
        
        System.out.println(" " == "") ; 
        // Question 8
        String chaine81 = "Il fait beau aujourd'hui !" ; 
        String chaine82 = "a" ; 
        String chaine83 = "eau" ;
        String chaine84 = "un" ;
        System.out.println("Question 8 :") ;
        String res81 = suppressionBoutChaine(chaine81,chaine82) ;
        String res82 = suppressionBoutChaine(chaine81,chaine83) ; 
        String res83 = suppressionBoutChaine(chaine81,chaine84) ;
        System.out.println(chaine81+" privee de "+chaine82+" est :   "+res81) ;
        System.out.println(chaine81+" privee de "+chaine83+" est :   "+res82) ;
        System.out.println(chaine81+" privee de "+chaine84+" est :   "+res83) ;
        System.out.println() ; 
    }

    public static String intToString(int nombre) {
        return String.valueOf(nombre);
    }
    public static int stringToInt(String s1){
        return Integer.parseInt(s1);
    }
    public static float StringToFloat(String s1){
        return Float.parseFloat(s1);
    }
    public static void afficheMajuscules(String chaine){
        System.out.println(chaine.trim().toUpperCase());
    }
    public static String memeDebut (String s1, String s2){
        return (s1.charAt(0) == s2.charAt(0))? "Oui": "Non";
    }
    public static void comparaisonEgale(String s1, String s2){
        System.out.println((s1 == s2)? "Yes": "No");
        System.out.println(s1.equals(s2));
        System.out.println(s1.compareTo(s2));
    }
    public static void comparaisonChaine(String s1, String s2){
        System.out.println(s1.startsWith(s2));
        System.out.println(s1.endsWith(s2));
        System.out.println(s1.contains(s2));
    }
    public static String suppressionBoutChaine(String s1, String s2) {
        if (!s1.contains(s2))
            return s1;
        
        String s3 = "";
        int count1 = 0;
        int count2 = 0;
        int offset = 0;
        boolean found = true;

        for (int i=0; i<s1.length(); i++) {
            found = true;
            count2 = 0;
            for (count1=i; count2<s2.length(); count1++) {
                if(s1.charAt(count1) != s2.charAt(count2)) {
                    found = false;
                    break;
                }
                count2++;
            }
            if (found) {
                offset = i;
                break;
            }
        }
        for (int i=0; i<s1.length(); i++) {
            if (i<offset || i>=offset+s2.length())
                s3 += s1.charAt(i);
        }

        return suppressionBoutChaine(s3, s2);
    }
}
