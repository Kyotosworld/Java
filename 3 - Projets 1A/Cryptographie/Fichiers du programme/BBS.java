/**
 * Gère la génération aléatoire
 * 
 * @author Clément Florant / Paul Caranton / Swann Duboz / Brendan Cador
 *
 */
public class BBS{
	/**
	 * Dit si un nombre est premier ou non
	 * 
	 * @param p Le nombre testé
	 * @return True si le nombre est premier, false s'il ne l'est pas
	 */
	public boolean estPremier(long p)
	{
		if (p == 1) // 1 n'est pas premier
		{
			return false;
		}
		if (p % 2 == 0) // On teste d'abord si p est pair
		{
			return false;
		}
		for (int i = 3; i < Math.sqrt(p); i +=2) // On teste ensuite tous les nombres impairs inférieurs à la racine carrée de p
		{
			if (p % i == 0)
			{
				return false;
			}
		}
		return true;
	}
	/**
	 * Génère des grands nombres premiers congrus à 3 modulo 4
	 * 
	 * @return Un grand nombre premier nécéssaire pour itérer la suite du BBS
	 */
	public long genGrandNbPremier()
	{
		boolean next = false; // Variable de sortie de boucle
		long nombre = 0; // Le nombre à renvoyer
		while (next == false) // Tant que le nombre ne convient pas, on en génère un nouveau
		{
			nombre = (long)(Math.random()*10000); // On génère un long compris entre 0 et 10000
			if ((nombre - 3) % 4 == 0) // On teste d'abord si ce nombre est congru à 3 modulo 4 (test plus court que le suivant)
			{
				if (estPremier(nombre)) // On teste ensuite si ce nombre est premier
				{
					next = true; // Si le nombre répond aux critères, on peut sortir de la boucle
				}
			}
		}
		return nombre;
	}
	/**
	 * Renvoie un tableau de boolean de taille entrée en paramètre générés aléatoirement grâce au BBS
	 * 
	 * @param nbBits La taille du tableau de boolean générés
	 * @return Un tableau de boolean généré aléatoirement
	 */
	public boolean[] nombreAleatoireBBS(int nbBits)
	{
		boolean[] bitsAlea = new boolean [nbBits]; // Le tableau à renvoyer
		long p = genGrandNbPremier();
		long q = genGrandNbPremier();
		long n = p*q; // Le BBS nécessite le produit de deux grands nombres premiers congrus à 3 modulo 4 pour démarrer
		long s = (long)(Math.random()*(n-2))+1; // s, appelé la "graine", cette valeur doit être comprise entre 1 et n-1
		long x = (s*s)%n; // Initialisation de la suite du BBS avec le valeurs initiales
		for (int i = 0; i < nbBits; i ++) // On génère NbBits bits dans notre tableau
		{
			if (x % 2 == 0) // Si x est pair, le bit généré sera un 1 ou true
			{
				bitsAlea[i] = true;
			}
			else // Sinon, le bit généré sera un 0 ou false
			{
				bitsAlea[i] = false;
			}
			x = (x*x)%n; // On itère la suite au rang n+1
		}
		return bitsAlea;
	}
}
