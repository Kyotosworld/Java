import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
/**
 * Gère les images traitées par le programme
 * 
 * @author Clément Florant / Paul Caranton / Swann Duboz / Brendan Cador
 * 
 */
public class Image{ // Classe qui contiendra tous les attributs et methodes de l'image traitée par le programme
	private File f; // Le pointeur fichier dans lequel est stocké l'image
	private BufferedImage image; // L'objet BufferedImage permet de traiter l'image en mémoire
	private String name; // Le nom de l'image à traiter
	
	/**
	 * @param f Le fichier où se trouve l'image
	 * @param image L'image en mémoire
	 * @param name Le nom du fichier
	 */
	public Image(File f, BufferedImage image, String name) // Le constructeur de Image
	{
		this.f = f;
		this.image = image;
		this.name = name;
	}
	/**
	 * La méthode de cryptage d'une image. Génère une nouvelle image BMP cryptée du nom choisi par l'utilisateur
	 * 
	 * @param name Le nom du fichier qui contiendra l'image cryptée
	 */
	public void crypt(String name)
	{
		System.out.println("Cryptage en cours ...");
		System.out.println();
		try
		{
			File f = new File ("cle.BMP"); // Une nouvelle clé est générée automatiquement à chaque nouveau cryptage
			File f2 = new File (name+".BMP"); // La nouvelle image choisie par l'utilisateur
			BufferedImage cle = new BufferedImage(image.getWidth() , image.getHeight(),image.getType()); // On crée l'image mémoire clé
			BufferedImage imageCryptee = new BufferedImage(image.getWidth() , image.getHeight(),image.getType()); // On crée l'image mémoire imageCryptée
			BBS gen = new BBS(); // On crée un nouvel objet BBS
			for (int x = 0; x < image.getWidth(); x ++) // On parcourt toute l'image traitée pixel par pixel
			{
				for (int y = 0; y < image.getHeight(); y ++)
				{
					int rand = 0; // Le niveau de rouge
			        int rand2 = 0; // Le niveau de vert
			        int rand3 = 0; // Le niveau de bleu
			    	boolean[]bits = gen.nombreAleatoireBBS(8); // Le niveau de rouge généré aléatoirement sur 8 bits
			    	boolean[]bits2 = gen.nombreAleatoireBBS(8); // Le niveau de vert généré aléatoirement sur 8 bits
			    	boolean[]bits3 = gen.nombreAleatoireBBS(8); // Le niveau de bleu généré aléatoirement sur 8 bits
			    	for (int i = 0; i < 8; i ++) // Conversion du tableau de 8 bits en un entier compris entre 0 et 256
			        {
			        	if (bits[i])
			        	{
			        		rand += (int)Math.pow(2, i);
			        	}
			        	if (bits2[i])
			        	{
			        		rand2 += (int)Math.pow(2, i);
			        	}
			        	if (bits3[i])
			        	{
			        		rand3 += (int)Math.pow(2, i);
			        	}
			        }
					int rgb = ((rand<<16)+(rand2<<8)+rand3); // variable rgb regroupe toutes les valeurs de couleur en une
					cle.setRGB(x, y, rgb); // On définit chaque pixel de la clé
					imageCryptee.setRGB(x, y, (image.getRGB(x, y)+rgb)%(int)Math.pow(2, 24)); // On ajoute chaque valeur RVB de chaque pixel de l'image traitée à celles de la clé modulo 256^3
				}
			}
			this.image = imageCryptee; // L'image traitée est maintenant cryptée
			ImageIO.write(cle, "BMP", f); // Création du fichier clé.BMP
			ImageIO.write(this.image, "BMP", f2); // Création du fichier BMP contenant l'image cryptée
			System.out.println("Cryptage terminé avec succès !");
			System.out.println();
		}
		catch (IOException e) // Au cas où la lecture/écriture dans un des fichiers soit impossible
		{
			e.printStackTrace();
		}
	}
	/**
	 * La méthode de décryptage d'une image. Génère une nouvelle image décryptée du nom choisi par l'utilisateur
	 * 
	 * @param name Le nom du fichier qui contiendra l'image décryptée
	 */
	public void decrypt(String name)
	{
		System.out.println("Décryptage en cours ...");
		System.out.println();
		try
		{
			File f = new File (name+".BMP"); // La nouvelle image qui sera décryptée
			BufferedImage imageDecryptee = new BufferedImage(image.getWidth() , image.getHeight(),image.getType()); // Nouvelle image mémoire pour l'image décryptée
			BufferedImage cle = ImageIO.read(new File("cle.BMP")); // On récupère la clé en mémoire
			for (int x = 0; x < image.getWidth(); x ++) // On parcourt l'ensemble de l'image
			{
				for (int y = 0; y < image.getHeight(); y ++)
				{
					// On applique l'opération inverse que pour le cryptage
					if (image.getRGB(x, y)-cle.getRGB(x, y) >= 0) // Si on tombe sur une valeur positive, c'est qu'il n'y a pas eu de modulo au cryptage. La valeur obtenue est la bonne.
					{
						imageDecryptee.setRGB(x, y, image.getRGB(x, y)-cle.getRGB(x, y)); // On "enlève" la clé de l'image cryptée
					}
					else // Sinon, il y eu un modulo et la valeur RVB est négative. Il faut lui rajouter la valeur du modulo.
					{
						imageDecryptee.setRGB(x, y, image.getRGB(x, y)-cle.getRGB(x, y)+(int)Math.pow(2, 24)); // On "enlève" la clé de l'image cryptée
					}
				}
			}
			this.image = imageDecryptee; // L'image traitée est maintenant décryptée
			ImageIO.write(this.image, "BMP", f); // Création du fichier BMP contenant l'image décryptée
			System.out.println("Décryptage terminé avec succès !");
			System.out.println();
		}
		catch (IOException e) // Au cas où la lecture/écriture dans un des fichiers soit impossible
		{
			e.printStackTrace();
		}
	}
}
