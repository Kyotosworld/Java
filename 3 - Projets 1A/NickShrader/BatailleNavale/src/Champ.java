/*
 * vide: case vide (1case)
 * frappe: balise blanche (1case)
 * touche: balise rouge (1case)
 * torp1: torpilleur1 (2cases)
 * torp2: torpilleur2 (2cases)
 * sm: sous-marin (3cases)
 * ctorp: contre-torpilleur (3cases)
 * crois: croiseur (4cases)
 * pa: porte-avion (5cases)
*/
public enum Champ {
	fantome(0, false, ""), vide(1, false, ""), frappe(1, false, ""), touche(1, false, ""), torp1(2, true,
			"premier torpilleur"), torp2(2, true, "second torpilleur"), sm(3, true, "sous-marin"), ctorp(3, true,
					"contre-torpilleur"), crois(4, true, "croiseur"), pa(5, true, "porte-avion");
	private int taille;
	private boolean bateau;
	private String nom;

	private Champ(int taille, boolean bateau, String nom) {
		this.taille = taille;
		this.bateau = bateau;
		this.nom = nom;
	}

	public boolean getBateau() {
		return bateau;
	}

	public int getTaille() {
		return taille;
	}

	public String toString() {
		return nom;
	}

	public int getMask() {
		if (bateau)
			return 1 << ordinal();
		else
			return 0;
	}

}