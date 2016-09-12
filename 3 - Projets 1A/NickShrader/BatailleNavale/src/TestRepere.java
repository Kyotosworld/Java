public class TestRepere {
	public static void main(String[] args) {

		Repere test = new Repere();
		System.out.println(test.placerBateaux(2, 2, false, Champ.torp1));
		System.out.println(test.placerBateaux(5, 7, false, Champ.torp1));
		System.out.println(test.placerBateaux(4, 4, false, Champ.crois));
		System.out.println(test.placerBateaux(4, 7, false, Champ.sm));
		System.out.println(test.placerBateaux(6, 7, false, Champ.sm));

		int nbre = test.compteBateaux();
		System.out.println(nbre + " ");
		test.resetRepere();
		int n = 0;
		boolean valide;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				n++;
				if (n != 10) {
					valide = test.placerBateaux(i, j, false, Champ.crois);
					System.out.print(valide + " ");
					test.resetRepere();
				} else {
					valide = test.placerBateaux(i, j, false, Champ.crois);
					n = n - 10;
					System.out.println(valide + " ");
					test.resetRepere();
				}
			}
		}

		System.out.println(" ");
		System.out.println(" ");
		test.resetRepere();
		test.placerBateaux(3, 4, true, Champ.torp2);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				n++;
				if (n != 10) {
					valide = test.valideBateaux(i, j, false, Champ.torp1);
					System.out.print(valide + " ");
				} else {
					valide = test.valideBateaux(i, j, false, Champ.torp1);
					n = n - 10;
					System.out.println(valide + " ");
				}
			}
		}
		System.out.println(" ");
		test.resetRepere();
		test.placerBateaux(3, 4, true, Champ.torp2);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				n++;
				if (n != 10) {
					boolean touche = test.valideFrappe(i, j);
					System.out.print(touche + " ");
				} else {
					boolean touche = test.valideFrappe(i, j);
					n = n - 10;
					System.out.println(touche + " ");
				}
			}
		}

	}
}
