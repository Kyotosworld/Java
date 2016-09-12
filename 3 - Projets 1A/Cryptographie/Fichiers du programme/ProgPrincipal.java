import java.util.Scanner;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.InputMismatchException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.IIOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
/**
 * Classe principale du programme, gérant son exécution.
 * 
 * @author Brendan Cador / Swann Duboz / Paul Caranton / Clément Florant
 *
 */
public class ProgPrincipal{
    /**
     * Introduction lancée au démarrage du programme, avec affichage du titre. Puis, choix du type de saisie (texte dans concole, texte dans fichier, image).
     * 
     * @return renvoie le choix du type de saisie fait par l'utilisateur
     */
    public static int intro(){ // Introduction lancée au début du programme, et choix du type de saisie
        System.out.println("Bienvenue dans Crypto, le programme de cryptographie !"); 
        titre(); // Affichage du nom du programme stylisé
        System.out.println();
        System.out.println();
        int choixTypeMessage = 0;
        while (choixTypeMessage <= 0 || choixTypeMessage >= 4) //Teste si le choix fait par l'utilisateur est correct
        {
        	choixTypeMessage = menuChoixFichier(); //Récupère le choix fait par l'utilisateur pour le type de saisie à crypter/décrypter
        	if (choixTypeMessage <= 0 || choixTypeMessage >= 4)
        	{
        		System.out.println("Merci de recommencer."); //Si choix incorrect, afficher ceci
        		System.out.println();
        		System.out.println();
        	}
        }
        return choixTypeMessage; //Renvoie le choix de l'utilisateur
    }
    /**
     * Affiche le titre du programme stylisé en ASCII art
     * 
     */
    public static void titre(){ //Titre du programme stylisé en ASCII art
        System.out.println();
        System.out.println(" ______     ______     __  __     ______   ______   ______");
        System.out.println("/\\  ___\\   /\\  == \\   /\\ \\_\\ \\   /\\  == \\ /\\__  _\\ /\\  __ \\  ");
        System.out.println("\\ \\ \\____  \\ \\  __<   \\ \\____ \\  \\ \\  _-/ \\/_/\\ \\/ \\ \\ \\/\\ \\ ");
        System.out.println(" \\ \\_____\\  \\ \\_\\ \\_\\  \\/\\_____\\  \\ \\_\\      \\ \\_\\  \\ \\_____\\ ");
        System.out.println("  \\/_____/   \\/_/ /_/   \\/_____/   \\/_/       \\/_/   \\/_____/");
        System.out.println();
    }
    /**
    * Choix du type de fichier à crypter
    * 
    * @throws InputMismatchException si on rentre par exemple du texte au lieu de nombres
    * @return Une valeur correspondant au type de fichier choisi
    */
    public static int menuChoixFichier()
    {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Pour commencer, que voulez-vous crypter/decrypter ?");
    	System.out.println();
    	System.out.println("1 - Du texte (vous devrez entrer ce texte)");
    	System.out.println("2 - Du texte stocké dans un fichier texte appelé <message>");
    	System.out.println("3 - Une image (seul format accepté : BMP)");
    	System.out.println();
    	System.out.println("!Attention! : Les fichiers à crypter doivent se trouver dans le dossier contenant les fichiers du programme");
    	System.out.println();
    	try
    	{
    		return sc.nextInt(); // On retourne une valeur qui doit correspondre à celles proposées dans le menu
    	}
    	catch (InputMismatchException e) // Gestion de l'exception
    	{
    		System.out.println();
    		System.out.println("Entrée incorrecte. Veuillez entrer un nombre compris entre 1 et 3.");
    		return 0;
    	}
	}
	/**
	 * Initialise les chaines de caractères entrées par l'utilisateur qui serviront au cryptage
	 * 
	 * @return L'objet Crypto qui servira au cryptage
	 */
    public static Crypto initCryptoTexte()
    {
    	String phrase; // La chaine de caractères qui contiendra le message
        String motDePasse; // La chaine de caractères qui contiendra le mot de passe
    	System.out.println("Entrez la chaine de caractères avec laquelle vous voulez travailler :");
    	System.out.println();
        System.out.println("ATTENTION : Sur Windows, les accents ne sont pas gérés par la console !");
        System.out.println();
    	Scanner sc = new Scanner(System.in, "UTF8");
    	phrase = sc.nextLine();
    	System.out.println();
    	System.out.println("Ensuite, merci d'entrer la cle correspondant au message à coder ou à décoder :");
    	System.out.println();
    	motDePasse = sc.nextLine();
    	return new Crypto(phrase, motDePasse); // On crée un nouvel objet Crypto avec les paramètres entrés par l'utilisateur
    }
	/**
	 * Initialise les chaines de caractères lues dans un document texte qui serviront au cryptage
	 * 
	 * @return L'objet Crypto qui servira au cryptage
	 */
    public static Crypto initCryptoFichierTexte()
    {
    	boolean entreeCorrecte = false; // Variable de condition de la boucle
    	Scanner sc = new Scanner(System.in);
    	String message = ""; // La chaine de caractères qui contiendra le message
    	String motDePasse = ""; // La chaine de caractères qui contiendra le mot de passe
    	message = importFichierTXT(true); // On importe le message depuis un fichier texte appelé "message"
    	while (entreeCorrecte == false) // Tant que l'entrée de l'utilisateur est incorrecte
    	{
    		System.out.println();
        	System.out.println("Voulez-vous aussi importer le mot de passe depuis un document texte nommé <mdp> ?");
        	System.out.println();
        	System.out.println("1 - Oui");
        	System.out.println("2 - Non");
    		try
    		{
    			int choix = sc.nextInt();
    			if (choix == 1)
    			{
    				motDePasse = importFichierTXT(false); // On importe la clé depuis un fichier texte
    				entreeCorrecte = true; // On peut sortir de la boucle
    			}
    			else if (choix == 2)
    			{
    				sc = new Scanner(System.in);
    				System.out.println();
    				System.out.println("Dans ce cas, merci d'entrer la cle correspondant au message à coder ou à décoder :"); // On demande à l'utilisateur d'entrer la clé
    				System.out.println();
    				motDePasse = sc.nextLine();
    				entreeCorrecte = true; // On peut sortir de la boucle
    			}
    			else
    			{
    				System.out.println("Entree incorrecte.");
    				System.out.println();
    			}
    		}
    		catch (InputMismatchException e)
    		{
    			System.out.println("Entree incorrecte.");
    			sc = new Scanner(System.in);
    		}
    	}
    	return new Crypto(message, motDePasse); // On crée un nouvel objet Crypto avec les données du fichier texte
    }
    /**
	 * Importe directement le contenu d'un fichier texte
	 *
	 * @param choix Représente le fichier que l'on veut lire (message.txt ou mdp.txt)
	 * @throws FileNotFoundException si le fichier est introuvable
	 * @throws IOException si la lecture est impossible
	 * @return Une chaine de caractères correspondant au contenu du fichier texte
	 */
    public static String importFichierTXT(boolean choix)
    {
    	System.out.println();
    	System.out.println("Lecture dans le fichier en cours ...");
    	System.out.println();
    	String chaine = "", nomFichier = ""; // chaine contiendra le contenu du fichier texte, nomFichier contiendra le nom du fichier texte
    	if (choix) // Si on a choisi d'importer le message
    	{
    		nomFichier = "message.txt";
    	}
    	else // Si on a choisi d'importer le mot de passe
    	{
    		nomFichier = "mdp.txt";
    	}
    	int n = 0;
    	DataInputStream fichier = null; // Contiendra un poiteur vers le fichier
    	try
    	{
    		byte[] buf = new byte[1]; // Contiendra successivement chaque caractère du fichier
    		fichier = new DataInputStream(new BufferedInputStream(new FileInputStream(new File (nomFichier)))); // Création du pointeur vers le fichier
    		while ((n = fichier.read(buf)) >= 0) // Tant que l'allocation est possible on continue de lire le fichier
    		{
    			for (byte bit : buf) {
    	               chaine += (char) bit; // On ajoute chaque caractère à la chaine
    	            }
    			buf = new byte[1]; // On réinitialise le caractère à chaque itération
    		}
    	}
    	catch (FileNotFoundException e)
    	{
    		e.printStackTrace();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    	finally
    	{
    		if (fichier != null)
    		{
    			try
    			{
    				fichier.close(); // On désalloue le pointeur
    			}
    			catch (IOException e)
    	    	{
    	    		e.printStackTrace();
    	    	}
    		}
    	}
    	System.out.println("Lecture terminée !");
    	System.out.println();
    	return chaine;
    }
    /**
     * Ecrit une chaine de caractères dans un fichier texte
     * 
     * @param texte Le texte écrit dans le fichier
     * @param name Le nom du fichier
     */
    public static void ecrireTexteFichier(String texte, String name)
    {
    	System.out.println();
    	System.out.println("Ecriture dans le fichier en cours ...");
    	System.out.println();
    	File f = new File(name); // Contient un pointeur vers le fichier dans lequel on va écrire
    	try
    	{
    		Writer ecrire = new OutputStreamWriter(new FileOutputStream(f), "UTF-8"); // On crée un nouvel objet Writer auquel on associe le fichier
    		ecrire.append(texte); // On écrit la chaine de caractère dans le fichier
    		ecrire.close(); // On désalloue le pointeur
    		System.out.println("Ecriture réussie !");
    	}
    	catch (FileNotFoundException e)
    	{
    		System.out.println("Erreur dans l'écriture du fichier");
    		System.out.println();
    	}
    	catch (IOException e)
    	{
    		System.out.println("Erreur dans l'écriture du fichier");
    		System.out.println();
    	}
    }
    /**
     * Affichage d'un menu avec tous les choix nécessaires au programme : 
     * 	
     * 		Afficher, modifier, importer ou exporter la chaîne de travail ou le mot de passe, crypter ou décrypter.
     * 
     * @param Cryptage objet contenant les chaînes de caractères et les méthodes de cryptage/décryptage.
     * @return false tant que l'utilisateur veut faire des opérations, true quand l'utilisateur veut quitter le programme.
     */
    public static boolean menuPrincipalTexteEntre(Crypto Cryptage){
        Scanner sc = new Scanner(System.in);
        System.out.println();// Affichage du menu.
        System.out.println("Vous voici dans le menu de CryptoTexte.");
        System.out.println();
        System.out.println("Options disponibles :");
        System.out.println();
        System.out.println("1 - Afficher la chaîne de caractères de travail.");
        System.out.println("2 - Afficher le mot de passe de codage.");
        System.out.println("3 - Modifier la chaîne de caractères de travail.");
        System.out.println("4 - Modifier le mot de passe de codage.");
        System.out.println("5 - Crypter la chaîne de caractères.");
        System.out.println("6 - Décrypter la chaîne de caractères.");
        System.out.println("7 - Importer un message d'un document texte nommé <message>.");
        System.out.println("8 - Importer un mot de passe d'un document texte nommé <mdp>.");
        System.out.println("9 - Écrire la chaine de caractères et le mot de passe dans des fichiers.");
        System.out.println("0 - Quitter le programme.");
        try
        {
        	boolean entreeCorrecte = false; //Permet de vérifier que la méthode de chiffrement est gérée par l'objet Crypto.
        	switch(sc.nextInt()){
            	case 1 :
            		Cryptage.affichagePhrase();// Affiche la chaîne de travail.
                break;
            	case 2 :
            		Cryptage.affichageMotDePasse();// Affiche le mot de passe.
                break;
            	case 3 :
            		System.out.println();
            		System.out.println("Merci d'entrer la nouvelle chaîne de travail :");
            		System.out.println();
            		sc.nextLine();
            		Cryptage.modifPhrase(sc.nextLine()); // Modifie la chaîne de travail avec la nouvelle fournie par l'utilisateur.
                break;
            	case 4 :
            		System.out.println();
            		System.out.println("Merci d'entrer le nouveau mot de passe :");
            		System.out.println();
            		sc.nextLine();
            		Cryptage.modifMotDePasse(sc.nextLine()); // Modifie le mot de passe avec le nouveau fourni par l'utilisateur.
                break;
            	case 5 :
            		while (entreeCorrecte == false)
            		{
            			System.out.println();
            			System.out.println("Choisissez la méthode de cryptage :");
            			System.out.println();
            			System.out.println("1 - Vigenère UTF-8");
                        System.out.println("2 - Vigenère lisible");
                        System.out.println("3 - Cesar");            			
                        System.out.println(); // Affichage des différentes options de cryptage.
            			try // Vérifie si tout se passe bien, c'est-à-dire que l'utilisateur saisi bien un nombre.
            			{
            				boolean success = Cryptage.encoder(sc.nextInt()); // Crypte la chaîne de travail si tout s'est bien passé.
            				entreeCorrecte = true;
            				if (success == false) // Vérifie si l'option choisie est bien compatible avec les méthodes de chiffrement disponibles.
            				{
            					System.out.println("Entree incorrecte.");
            					entreeCorrecte = false;
            				}
            			}
            			catch (InputMismatchException e)
            			{
            				System.out.println("Entree incorrecte."); // Si l'entrée n'est pas un nombre entier, affiche un message d'erreur, puis redemande une nouvelle entrée.
            				sc = new Scanner(System.in);
            			}
            		}
                break;
            	case 6 :
            		while (entreeCorrecte == false)
            		{
            			System.out.println();
            			System.out.println("Choisissez la méthode de decryptage :");
            			System.out.println();
            			System.out.println("1 - Vigenère UTF-8");
                        System.out.println("2 - Vigenère lisible");
                        System.out.println("3 - Cesar");            			
                        System.out.println(); // Affichage des différentes options de décryptage.
            			try // Vérifie si tout se passe bien, c'est-à-dire que l'utilisateur saisi bien un nombre.
            			{
            				boolean success = Cryptage.decoder(sc.nextInt()); // Crypte la chaîne de travail si tout s'est bien passé.
            				entreeCorrecte = true;
            				if (success == false) // Vérifie si l'option choisie est bien compatible avec les méthodes de déchiffrement disponibles.
            				{
            					System.out.println("Entree incorrecte.");
            					entreeCorrecte = false;
            				}
            			}
            			catch (InputMismatchException e)
            			{
            				System.out.println("Entree incorrecte."); // Si l'entrée n'est pas un nombre entier, affiche un message d'erreur, puis redemande une nouvelle entrée.
            				sc = new Scanner(System.in);
            			}
            		}
                break;
            	case 7:
            		Cryptage.modifPhrase(importFichierTXT(true)); // Importer une nouvelle chaîne de travail depuis un fichier.
            	break;
            	case 8:
            		Cryptage.modifMotDePasse(importFichierTXT(false)); // Importer une nouveau mot de passe depuis un fichier.
            	break;
            	case 9:
            		entreeCorrecte = false; // Variable de fin de boucle.
            		while (entreeCorrecte == false)
            		{
            			System.out.println();
                		System.out.println("Voulez-vous écrire le message dans un fichier texte ?");
                		System.out.println();
                		System.out.println("1 - Oui");
                		System.out.println("2 - Non");
                		try
                		{
                			int choix = sc.nextInt(); // Récupération du choix de l'utilisateur.
                			if (choix == 1) // Si l'utilisateur choisit d'écrire la chaîne de travail dans un fichier.
                			{
                				String name;
                				System.out.println();
                				System.out.println("Comment voulez-vous l'appeler ?");
                				sc = new Scanner(System.in); 
                				name = sc.nextLine(); // Récupération du nom pour le fichier contenant la chaîne de caractères de travail.
                				name += ".txt"; // Ajout de l'extension.
                				ecrireTexteFichier(Cryptage.getPhrase(), name); // Ecrire le fichier contenant la chaîne de caractères de travail.
                				entreeCorrecte = true; // Sortir de la boucle.
                			}
                			else if (choix == 2) // Si l'utilisateur refuse d'écrire la chaîne de travail dans un fichier.
                			{
                				entreeCorrecte = true; // Sortir de la boucle.
                			}
                			else // Si l'utilisateur rentre un mauvais choix, mais qui est quand même un nombre.
                			{
                				System.out.println("Entree incorrecte.");
                			}
                		}
                		catch (InputMismatchException e) // Si l'utilisateur entre une autre valeur qu'un entier.
                		{
                			System.out.println("Entree incorrecte.");
            				sc = new Scanner(System.in);
                		}
            		}
            		entreeCorrecte = false; // Condition de fin de boucle.
            		while (entreeCorrecte == false)
            		{
            			System.out.println();
                		System.out.println("Voulez-vous écrire le mot de passe dans un fichier texte ?");
                		System.out.println();
                		System.out.println("1 - Oui");
                		System.out.println("2 - Non");
                		try
                		{
                			int choix = sc.nextInt(); // Récupération du choix de l'utilisateur.
                			if (choix == 1) // Si l'utilisateur choisit d'écrire le mot de passe dans un fichier.
                			{
                				String name;
                				System.out.println();
                				System.out.println("Comment voulez-vous l'appeler ?");
                				sc = new Scanner(System.in);
                				name = sc.nextLine(); // Récupération du nom pour le fichier contenant le mot de passe.
                				name += ".txt"; // Ajout de l'extension
                				ecrireTexteFichier(Cryptage.getMotDePasse(), name); // Ecrire le fichier contenant le mot de passe.
                				entreeCorrecte = true; // Sortir de la boucle.
                			}
                			else if (choix == 2) // Si l'utilisateur refuse d'écrire le mot de passe dans un fichier.
                			{
                				entreeCorrecte = true; // Sortir de la boucle
                			}
                			else // Si l'utilisateur rentre un mauvais choix, mais qui est quand même un nombre.
                			{
                				System.out.println("Entree incorrecte.");
                			}
                		}
                		catch (InputMismatchException e) // Si l'utilisateur entre une autre valeur qu'un entier.
                		{
                			System.out.println("Entree incorrecte.");
            				sc = new Scanner(System.in);
                		}
            		}
                break;
            	case 0 :
            		return true; // Fermer le programme
            	default :
            		System.out.println();
            		System.out.println("Option indisponible, recommencez."); // Si choix de l'utilisateur dans le menu différent de ceux disponibles, afficher ce message d'erreur
            		System.out.println();
                break;
        	}
        }
        catch (InputMismatchException e)
        {
        	System.out.println("Entrée incorrecte, veuillez entrer un nombre compris entre 0 et 9."); // Si choix de l'utilisateur dans le menu n'est pas un entier, afficher ce message d'erreur
        	System.out.println("Merci de recommencer.");
        }
        return false;
    }
	/**
	 * Importe une image en mémoire au format BMP
	 * 
	 * @throws IOException si la lecture est impossible
	 * @return Un objet Image correspondant à l'image à traiter
	 */
    public static Image importImage()
    {
    	String name = ""; // Contiendra le nom de l'image à importer
    	File f = null; // Contiendra un pointeur vers le fichier contenant l'image
    	BufferedImage image = null; // Contiendra l'image en mémoire
    	boolean entreeCorrecte = false; // Variable de condition de la boucle
    	Scanner sc = new Scanner(System.in);
    	while (entreeCorrecte == false)
    	{
    		System.out.println();
    		System.out.println("Quel est le nom de l'image que vous voulez utiliser ? (au format BMP uniquement)");
    		System.out.println();
    		name = sc.nextLine(); // On récupère le nom de l'image
    		name += ".BMP"; // On lui ajoute l'extension BMP
    		try
    		{
    			f = new File (name); // On associe le pointeur f au fichier contenant l'image
    			image = ImageIO.read(f); // On stocke l'image en mémoire
    			entreeCorrecte = true; // On peut sortir de la boucle
    		}
    		catch (IOException e)
    		{
    			System.out.println("Image introuvable.");
    			System.out.println();
    		}
    	}
    	return new Image(f, image, name);
    }
	/**
	 * L'interface de cryptage d'image
	 * 
	 * @param image L'image BMP traitée par le programme
	 * @throws InputMismatchException si on entre du texte au lieu de nombres
	 * @return True pour quitter le programme false pour continuer
	 */
    public static boolean menuPrincipalImage(Image image)
    {
    	Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Bienvenue dans CryptoImage");
        System.out.println();
        System.out.println("Options disponibles :");
        System.out.println();
        System.out.println("1 - Crypter l'image.");
        System.out.println("2 - Decrypter l'image.");
        System.out.println("3 - Importer une nouvelle image.");
        System.out.println("4 - Plus d'informations sur ce système de chiffrement");
        System.out.println("0 - Quitter");
        System.out.println();
    	try
    	{
    		switch (sc.nextInt()) // Gère les différentes possibilités de choix de l'utilisateur
    		{
    			case 1: // Crypter l'image
					String name = ""; // Contiendra le nom de l'image qui sera cryptée
    				System.out.println("Vous avez choisi de crypter l'image. Veuillez entrer le nom de l'image cryptée :");
    				sc.nextLine();
    				name = sc.nextLine(); // On récupère le nom de l'image cryptée
    				System.out.println();
    				System.out.println("L'image cryptée ainsi que la clé se trouveront dans un fichier .BMP dans le dossier du programme");
    				System.out.println();
    				image.crypt(name); // On crypte l'image en lui donnant le nom entré
    			break;
    			case 2: // décrypter l'image
    				System.out.println("Vous avez choisi de décrypter l'image. Veuillez entrer le nom de l'image décryptée :");
    				sc.nextLine();
    				name = sc.nextLine(); // On récupère le nom de l'image décryptée
    				System.out.println("L'image decryptée se trouvera dans un fichier .BMP dans le dossier du programme");
    				System.out.println();
    				image.decrypt(name); // On décrypte l'image en lui donnant le nom entré
    			break;
    			case 3: // Importer une nouvelle image
    				image = importImage(); // On appelle la fonction importImage
        		break;
    			case 4: // Obtenir plus d'informations
    				infos(); // On appelle la fonction infos
        		break;
    			case 0: // Quitter
    				return true;
        		default:
        			System.out.println("Entrée incorrecte, veuillez entrer un nombre compris entre 0 et 5.");
        			System.out.println("Merci de recommencer.");
        		break;
    		}
    	}
    	catch (InputMismatchException e)
        {
        	System.out.println("Entrée incorrecte, veuillez entrer un nombre compris entre 0 et 5.");
        	System.out.println("Merci de recommencer.");
        }
        return false;
    }
    /**
     * Affiche un texte explicatif concernant la méthode de chiffrement utilisée
     * 
     * @author Clément Florant
     */
    public static void infos()
    {
    	System.out.println();
    	System.out.println("Le système de chiffrement que vous utilisez grâce à ce programme est celui");
    	System.out.println("du <masque jetable> ou aussi appelé chiffrement de Vernam.");
    	System.out.println("Il utilise exactement le même principe que celui de");
    	System.out.println("Vigénère à l'exception qu'ici, la taille de la clé est identique");
    	System.out.println("et s'adapte à celle du message ce qui évite des problèmes de sécurité liés");
    	System.out.println("à la répétition de la clé dans le message chiffré. Ici, chaque morceau de clé");
    	System.out.println("n'apparait qu'une seule fois. Néanmoins, cela nécéssite une clé de taille");
    	System.out.println("pouvant être très importante. Il est donc impensable pour");
    	System.out.println("un utilisateur normal de devoir rentrer lui-même cette clé manuellement.");
    	System.out.println("C'est pourquoi la clé de ce type de système de chiffrement est générée");
    	System.out.println("automatiquement et aléatoirement par l'ordinateur. Néanmoins, pour");
    	System.out.println("garantir une sécurité optimale, nous avons opté pour un algorithme");
    	System.out.println("de génération de nombres pseudo-aléatoires cryptographiquement sûr");
    	System.out.println("appelé le BBS (Blum Blum Shub). En effet, l'ordianteur ne pouvant");
    	System.out.println("pas physiquement générer des suites de nombres réellement aléatoires,");
    	System.out.println("il génère des nombres dits pseudo-aléatoires qui résultent de calculs");
    	System.out.println("plus ou moins déterministes. Il est ainsi techniquemnt possible de");
    	System.out.println("retrouver la suite de nombres l'ordinateur que l'ordinateur a générée.");
    	System.out.println("Le BBS est conçu pour éviter ce type de problèmes. Bien que plus lent");
    	System.out.println("à l'execution qu'un algorithme de génération aléatoire classique, celui-ci");
    	System.out.println("garantit un niveau de sécurité élevé du à la difficulté de la résolution");
    	System.out.println("de problèmes mathématiques complexes (un peu comme le RSA). Il est donc");
    	System.out.println("plus difficile de retrouver la suite de nombres générés pseudo-aléatoirement");
    	System.out.println("par l'ordinateur avec cette méthode qu'avec une méthode classique.");
    	System.out.println("Pour plus d'informations :");
    	System.out.println("https://crypto.junod.info/bbs.pdf");
    }
    /**
     * Premier menu de l'interface du programme
     * 
     * @param args Les arguments de la ligne de commande java
     */
    public static void main(String[] args){
        System.setProperty( "file.encoding", "UTF-8" );
        boolean fin = false; // La variable de condition de fin de programme
        int choixTypeMessage = 0; // Représente le choix du type d'entrée à crypter
        choixTypeMessage = intro(); // On initialise le programme avec l'appel à la fonction intro
        switch (choixTypeMessage) // Gère les différentes possibilités de choix de l'utilisateur
        {
        	case 1:
        		Crypto instance;
        		instance = initCryptoTexte(); // On crée un nouvel objet de type Crypto qui contiendra les caractéristiques du message
        		while(!fin) // boucle de fin de programme
        		{
        			fin = menuPrincipalTexteEntre(instance); // On appelle le menu principal du texte
        		}
        	break;
        	case 2:
        		Crypto instance2 = initCryptoFichierTexte(); // On crée un nouvel objet de type Crypto qui contiendra les caractéristiques du message
        		while(!fin) // boucle de fin de programme
        		{
        			fin = menuPrincipalTexteEntre(instance2); // On appelle le menu principal du texte
        		}
        	break;
        	case 3:
        		Image image = importImage(); // On crée un nouvel objet de type Image qui contiendra les caractéristiques de l'image
        		while(!fin) // boucle de fin de programme
        		{
        			fin = menuPrincipalImage(image); // On appelle le menu principal de l'image
        		}
        	break;
        	default:
        	break;
        }
    }
}
