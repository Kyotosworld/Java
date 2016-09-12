/**
 * Crypto est la classe parmettant le codage et le décodage de chaînes de caractères.
 * 
 * @author Clément Florant / Paul Caranton / Swann Duboz / Brendan Cador
 * @version 1.0
 */
 public class Crypto{
    private String phrase; //chaîne de travail.
    private String motdepasse; //mot de passe de codage.
    
    /**
     * Initialise les variables.
     * 
     * @param phrase contient la chaîne de travail
     * @param motdepasse contient le mot de passe de codage
     */
    public Crypto(String phrase, String motdepasse){
        this.phrase = phrase;
        this.motdepasse = motdepasse;
    }
    /**
     * Affiche la chaîne de travail.
     */
    public void affichagePhrase(){
        System.out.println(phrase); //Affiche le chaîne de travail.
    }
    /**
     * Affiche le mot de passe de codage.
     */
    public void affichageMotDePasse(){
        System.out.println(motdepasse); //Affiche le mot de passe de codage.
    }
    /**
     * Renvoie la chaîne de travail.
     * @return la chaîne de travail
     */
    public String getPhrase()
    {
    	return this.phrase;
    }
    /**
     * Renvoie le mot de passe.
     * @return la chaîne de travail
     */
    public String getMotDePasse()
    {
    	return this.motdepasse;
    }
    /**
     * Modifie le mot de passe.
     * 
     * @param motdepasse nouveau mot de passe
     */
    public void modifMotDePasse(String motdepasse){
        this.motdepasse = motdepasse; //Modifie le mot de passe.
    }
    /**
     * Modifie la chaîne de travail.
     * 
     * @param phrase nouvelle chaîne de travail
     */
    public void modifPhrase(String phrase){
        this.phrase = phrase; //Modifie la chaîne de travail.
    }
    /**
     * Code un caractère avec la méthode de César, puis renvoie le caractère codé.
     * 
     * @param c caractère à coder
     * @param n clé de codage, composée de chiffres uniquement
     * @return caractère codé avec la clé n
     */
    private char codeCharacter (char c, int n) {
        int a= (((int)c-97)+n)%26;
        while(a<0){a += 26;}
        a=a+97;
        char m=(char)a;
        return m;
    }
    /**
     * Décode un caractère avec la méthode de César, puis renvoie le caractère décodé.
     * 
     * @param c caractère à décoder
     * @param n clé de codage, composée de chiffres uniquement
     * @return caractère décodé avec la clé n
     */
    private char decodeCharacter (char c, int n) {
        int a= (((int)c-97)-n)%26;
        while(a<0){a += 26;}
        a=a+97;
        char m=(char)a;
        return m;
    }
    /**
     * Code une chaîne de caractère, en fonction de la méthode spécifiée (Vigenère UTF-8 ; Vigenère lisible ; César).
     * 
     * @param type type de chiffrement
     * @return renvoi true si la méthode de chiffrement existe, false sinon.
     * @throws NumberFormatException erreur renvoyée si le mot de passe utilisé n'est pas un nombre entier.
     */
    public boolean encoder(int type){
        char[] temp; // Déclaration d'un tableau temporaire qui va contenir la chaîne codée en formation.
        switch(type){ // Choix du type de chiffrement
            case 1: // Chiffrement Vigenère en utilisant tous les caractères UTF-8 jusqu'au 8366ème.
                temp = new char[this.phrase.length()]; // Initialisation de temp de la même taille que la chaîne à coder.
                for(int i = 0;i< this.phrase.length();i++){ // Pour chaque caractère, ...
                    temp[i] = (char)(((int)(this.phrase.charAt(i)) + (int)(this.motdepasse.charAt(i%(this.motdepasse.length()))))%8365); // ... on ajoute à la position du caractère de la chaîne à coder la position équivalente modulo 'la longueur du mot de passe' du mot de passe (le total modulo le nombre de caractères codables, afin d'éviter des caractères non pris en compte), qui est ensuite convertie en caractère puis envoyé dans temp
                }
                this.phrase = new String(temp); // Convertit temp en String, qui est ensuite attribué à phrase.
                return true;
            case 2: // Chiffrement Vigenère lisible, c'est-à-dire qui utilise tous les caractères courants du clavier (hors caractères obtenus via une combinaise Alt + ...).
                temp = new char[this.phrase.length()]; // Initialisation de temp de la même taille que la chaîne à coder.
                String dictionnaire = " azertyuiopqsdfghjklmwxcvbnAZERTYUIOPQSDFGHJKLMWXCVBN123456789&é\"'(-è_çà)=~ñ#{[|@]}^$ù*,;:!?./§%µâêîôûäëïöüÂÊÎÔÛÄËÏÖÜ£¤<>²\\"; // Création d'un dictionnaire comprenant tous les caractères que nous pouvons chiffrer.
                for(int i = 0;i< this.phrase.length();i++){ // Pour chaque caractère ...
                    temp[i] = dictionnaire.charAt((dictionnaire.indexOf(this.phrase.charAt(i)) + dictionnaire.indexOf(this.motdepasse.charAt(i%(this.motdepasse.length()))))%(dictionnaire.length())); // ... on ajoute à la position dans le dictionnaire du caractère de la chaîne à coder, la position équivalente modulo 'la longueur du mot de passe' du mot de passe (le total modulo le nombre de caractères codables, afin d'éviter des caractères non pris en compte), qui est ensuite convertie en caractère via le dictionnaire puis envoyé dans temp
                }
                this.phrase = new String(temp); // Convertit temp en String, qui est ensuite attribué à phrase.
                return true;
            case 3: //Chiffrement de César
                int motdepasseint; // Déclaration d'une variable pour stocker le mot de passe dans un int (car le chiffrement de César nécessite un nombre entier comme mot de passe).
                try
                {
                    motdepasseint = Integer.parseInt(motdepasse); // Essaye de parser le mot de passe en int
                }
                catch (NumberFormatException e)
                {
                    System.out.println();
                    System.out.println("Attention, votre mot de passe doit uniquement être composé de chiffres. Merci de le mofifier."); // Si mot de passe différent d'un nombre, annuler le codage et afficher ce message.
                    return true;
                }
                temp = new char [phrase.length()]; //Initialisation de temp de la même taille que la chaîne à coder
                for (int i=0; i<phrase.length(); i++){ 
                    temp [i]=phrase.charAt(i); // La chaîne de travail est copiée dans temp
                }
                for (int i=0; i<phrase.length(); i++){
                    temp [i]=codeCharacter(temp [i],motdepasseint); // Les caractères de temp sont un par un codé par codeCharacter
                }
                phrase = new String(temp); // Convertit temp en String, qui est ensuite attribué à phrase.
                return true;
            default :
                return false;
        }
    }
    /**
     * Décode une chaîne de caractère, en fonction de la méthode spécifiée (Vigenère UTF-8 ; Vigenère lisible ; César).
     * 
     * @param type type de chiffrement
     * @return renvoi true si la méthode de chiffrement existe, false sinon.
     * @throws NumberFormatException erreur renvoyée si le mot de passe utilisé n'est pas un nombre entier.
     */
    public boolean decoder(int type){
        char[] temp; // Déclaration d'un tableau temporaire qui va contenir la chaîne décodée en formation.
        int module;  //Cette varaible sert à remplacer le modulo '%' car il ne fonctionne pas pour les nombres négatifs.
        switch(type){ // Choix du type de chiffrement
            case 1: // Chiffrement Vigenère en utilisant tous les caractères UTF-8 jusqu'au 8366ème.
                temp = new char[this.phrase.length()]; // Initialisation de temp de la même taille que la chaîne à décoder.
                for(int i = 0;i<this.phrase.length();i++){ // Pour chaque caractère ...
                    module = ((int)(this.phrase.charAt(i)) - (int)(this.motdepasse.charAt(i%(this.motdepasse.length()))));// ... on soustrait à la position du caractère de la chaîne à coder la position équivalente modulo 'la longueur du mot de passe' du mot de passe
                    if(module<0) {module += 8365;} //Si le total est négatif, on ajoute 8365, afin de simuler le modulo.
                    temp[i] = (char)(module); // Enfin, la position est convertie en caractère qui est ensuite envoyé dans temp
                }
                this.phrase = new String(temp); // Convertit temp en String, qui est ensuite attribué à phrase.
                return true;
            case 2: // Chiffrement Vigenère lisible, c'est-à-dire qui utilise tous les caractères courants du clavier (hors caractères obtenus via une combinaise Alt + ...).
                temp = new char[this.phrase.length()]; // Initialisation de temp de la même taille que la chaîne à décoder.
                String dictionnaire = " azertyuiopqsdfghjklmwxcvbnAZERTYUIOPQSDFGHJKLMWXCVBN123456789&é\"'(-è_çà)=~ñ#{[|@]}^$ù*,;:!?./§%µâêîôûŷäëïöüÂÊÎÔÛŶÄËÏÖÜŸ£¤<>²\\"; // Création d'un dictionnaire comprenant tous les caractères que nous pouvons chiffrer.
                for(int i = 0;i< this.phrase.length();i++){ // Pour chaque caractère ...
                    module = (dictionnaire.indexOf(this.phrase.charAt(i)) - dictionnaire.indexOf(this.motdepasse.charAt(i%(this.motdepasse.length())))); // ... on soustrait à la position dans le dictionnaire du caractère de la chaîne à coder la position équivalente modulo 'la longueur du mot de passe' du mot de passe
                    if(module<0) {module += dictionnaire.length();} // Si le total est négatif, on ajoute la longueur du dictionnaire pour simuler un modulo.
                    temp[i] = dictionnaire.charAt(module); // Enfin, la position est convertie en caractère qui est ensuite envoyé dans temp
                }
                this.phrase = new String(temp); // Convertit temp en String, qui est ensuite attribué à phrase.
                return true;
            case 3: // Chiffrement de César
                int motdepasseint; // Déclaration d'une variable pour stocker le mot de passe dans un int (car le chiffrement de César nécessite un nombre entier comme mot de passe).
                try
                {
                    motdepasseint = Integer.parseInt(motdepasse); // Essaye de parser le mot de passe en int
                }
                catch (NumberFormatException e)
                {
                    System.out.println();
                    System.out.println("Attention, votre mot de passe doit uniquement être composé de chiffres. Merci de le mofifier.");  // Si mot de passe différent d'un nombre, annuler le codage et afficher ce message.
                    return true;
                }
                temp = new char [phrase.length()] ; // Initialisation de temp de la même taille que la chaîne à décoder.
                for (int i=0; i<phrase.length(); i++){
                    temp [i]=phrase.charAt(i); // La chaîne de travail est copiée dans temp
                }
                for (int i=0; i<phrase.length(); i++){
                    temp [i]=decodeCharacter(temp [i],motdepasseint); // Les caractères de temp sont un par un décodé par decodeCharacter
                }
                phrase = new String(temp); // Convertit temp en String, qui est ensuite attribué à phrase.
                return true;
            default :
                 return false;
        }
    }
}
