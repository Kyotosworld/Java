import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Repere {

	private Champ[][] repere;
	private ArrayList<ActionListener> actionList = new ArrayList<ActionListener>();

	public Repere() {
		repere = new Champ[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				repere[i][j] = Champ.vide;
			}
		}
	}

	public Champ[][] getRepere() {
		return repere;
	}

	public Champ[][] resetRepere() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				repere[i][j] = Champ.vide;
			}
		}
		return repere;
	}

	/**
	 * Determine si l'emplacement du bateau est possible avec les parametres
	 * suivants la coordonnée indiquée est un point de l'extrémité du bateau le
	 * bateau ira toujours vers le haut (vertical) ou vers la droite
	 * (horizontal) si le bateau que l'on veut placer coupe un autre bateau déjà
	 * sur repère, il ne sera pas placé
	 * 
	 * @param x
	 *            entier <Coordonnees verticales orientees vers le bas>
	 * @param y
	 *            entier <Coordonnees horizontale orientees vers la droite>
	 * @param vertical
	 *            boolean <Bateau place verticalement (true) ou horizontalement
	 *            (false)>
	 * @param c
	 *            Champ <Champ que l'on utilise>
	 * 
	 * @return true si l'emplacement du bateau est valide false si l'emplacement
	 *         diu bateau est invalide
	 */
	public boolean valideBateaux(int x, int y, boolean vertical, Champ c) {
		int sup;
		int n = 0;
		if (vertical == true) {
			sup = Math.min(c.getTaille(), x + 1);
			for (int i = 0; i < sup; i++) {
				if (repere[x - i][y] == Champ.vide) {
					n++;
				}
			}
			if ((c.getTaille() == sup) && (c.getTaille() == n)) {
				return true;
			}
		} else if (vertical == false) {
			sup = Math.min(c.getTaille(), 10 - y);
			for (int i = 0; i < sup; i++) {
				if (repere[x][y + i] == Champ.vide) {
					n++;
				}
			}
			if ((c.getTaille() == sup) && (c.getTaille() == n)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Indique en temps reel a l'utilisateur l'emplacement sur le repere du
	 * bateau qu'il est en train de placer
	 * 
	 * @param x
	 *            Coordonnees verticales orientees vers le bas
	 * @param y
	 *            Coordonnees horizontale orientees vers la droite
	 * @param vertical
	 *            Bateau place verticalement (true) ou horizontalement (false)
	 * @param c
	 *            Champ que l'on utilise
	 * 
	 * @return nouveau repere rep_n avec le fantome du bateau dessus
	 */
	public Champ[][] placerFantome(int x, int y, boolean vertical, Champ c) {
		int sup;
		Champ[][] rep_n = new Champ[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				rep_n[i][j] = repere[i][j];
		}
		if (vertical == true) {
			sup = Math.min(c.getTaille(), x + 1);
			for (int i = 0; i < sup; i++) {
				rep_n[x - i][y] = Champ.fantome;
			}
		} else if (vertical == false) {
			sup = Math.min(c.getTaille(), 10 - y);
			for (int i = 0; i < sup; i++) {
				rep_n[x][y + i] = Champ.fantome;
			}
		}
		return rep_n;
	}

	/**
	 * Place le bateau sur le repere de maniere definitive
	 * 
	 * @param x
	 *            Coordonnees verticales orientees vers le bas
	 * @param y
	 *            Coordonnees horizontale orientees vers la droite
	 * @param vertical
	 *            Bateau place verticalement (true) ou horizontalement (false)
	 * @param c
	 *            Champ que l'on utilise
	 * 
	 * @return true si l'emplacement du bateau est valide et modifie le repere
	 *         avec le bateau dessus false si l'emplacement du bateau est
	 *         invalide, l'utilisateur ne peut pas modifier le repere
	 */
	public boolean placerBateaux(int x, int y, boolean vertical, Champ c) {
		if (valideBateaux(x, y, vertical, c) == true) {
			if (vertical == true) {
				for (int i = 0; i < c.getTaille(); i++) {
					repere[x - i][y] = c;
				}
				runActionListeners();
				return true;
			} else if (vertical == false) {
				for (int i = 0; i < c.getTaille(); i++) {
					repere[x][y + i] = c;
				}
				runActionListeners();
				return true;
			}
		}
		return false;
	}

	/**
	 * Determine si les coordonnees de la frappe que l'on veut jouer sont
	 * valides
	 * 
	 * @param x
	 *            Coordonnees verticales orientees vers le bas
	 * @param y
	 *            Coordonnees horizontale orientees vers la droite
	 * 
	 * @return true si les coordonnees de la frappe son valide false si les
	 *         coordonnees sont invalides
	 */
	public boolean valideFrappe(int x, int y) {
		if ((repere[x][y] == Champ.frappe) || (repere[x][y] == Champ.touche) || (x < 0) || (x > 9) || (y < 0)
				|| (y > 9)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Indique en temps reel a l'utilisateur l'emplacement sur le repere de la
	 * frappe qu'il veut jouer
	 * 
	 * @param x
	 *            Coordonnees verticales orientees vers le bas
	 * @param y
	 *            Coordonnees horizontale orientees vers la droite
	 * 
	 * @return nouveau repere rep_n avec le fantome de la frappe dessus
	 */
	public Champ[][] frappeFantome(int x, int y) {
		return placerFantome(x, y, false, Champ.frappe);
	}

	/**
	 * Place la frappe sur le repere et joue le coup de l'utilisateur:
	 * 
	 * @param x
	 *            Coordonnees verticales orientees vers le bas
	 * @param y
	 *            Coordonnees horizontale orientees vers la droite
	 * 
	 */
	public void frappe(int x, int y) {
		if (!valideFrappe(x, y))
			return;
		else if (repere[x][y] != Champ.vide) {
			repere[x][y] = Champ.touche;
		} else {
			repere[x][y] = Champ.frappe;
		}
		runActionListeners();
	}

	/**
	 * Compte le nombre de bateaux restant sur le repere
	 * 
	 * @return Nombre de bateaux
	 */
	public int compteBateaux() {
		int s = 0;
		int n = 0;
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				s |= repere[i][j].getMask();
			}
		}
		for (int i = 0; i < 10; i++) {
			if ((s & (1 << i)) != 0) {
				n++;
			}
		}
		return n;

	}

	/**
	 * Add event handler
	 * 
	 * @param l
	 *            ActionListener implemntation
	 */
	public void addActionListener(ActionListener l) {
		actionList.add(l);
	}

	private void runActionListeners() {
		for (ActionListener l : actionList)
			l.actionPerformed(null);
	}
}
