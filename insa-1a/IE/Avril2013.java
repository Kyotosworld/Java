package IE;

public class Avril2013 {

/*  1. Compréhension algorithmique

Q 1.1
    Explications:
    ------------------------------------------------------------------------------------
    On initialise 3 constantes, et d

    On envoie d à la méthode loop, qui ne renvoie rien, et on ne récupère aucune valeur
    On affiche d

    On affiche le minimum de C et A

    On envoie A et B à increment() qui sont recupérées A L'ENVERS: A -> b et B -> a
    On les incrémente et on les affiche

    On affiche de nouveau A et B dont les valeurs n'ont pas changé
    ------------------------------------------------------------------------------------

	Le programme affiche:
	0
	6
	6 14
	8 3


 	2. Compréhension algorithmique

Q 2.1
	public static boolean isPalindrome(String pal) {
		StringBuffer strbuf = new StringBuffer(pal.length());
		strbuf = strbuf.append(pal);
		strbuf = strbuf.reverse();

		String test = strbuf.toString();
		if (test.equals(pal)) {
			return true;
		} else {
			return false;
		}
	}

Q 2.2 