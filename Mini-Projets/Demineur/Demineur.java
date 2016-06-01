public class Demineur {
	public static void main (String[] args) {
		boolean[][] jeu1 = new boolean[20][20];
		System.out.println("Veuillez sélectionner votre niveau de difficulté ?");
		System.out.println("1=facile; 2=intermédiaire; 3=difficile");
		int niveau = ReadKeyboard.readIntLn();
		int nbBombes=0;
		if (niveau == 1)  {
			nbBombes=10;
			}
		else {
			if (niveau ==2){
			nbBombes=20;
			}
		else{
			nbBombes=30;
			}
			}	
			
		genererDemineur(jeu1,nbBombes);
		cases[][] jeu2 = booleanToCases(jeu1);
		afficherDemineur(jeu2);
		partie(jeu2);
	}	
	
	/**
	 * cette méthode met fin à la partie 
	 * elle détermine si la partie est gagnée
	 * si elle est perdue
	 * si le jeu est toujours actif
	 * elle ne renvoie rien
	 * prend en paramètre un tableau de cases
	 */
	public static void partie(cases[][] jeu2) {
		boolean victory=false;
		boolean lost=false;
		boolean game=true;	//boolean qui détermine si la partie est active
		while (game==true) {
			tour(jeu2);
			victory=victoire(jeu2);
			lost=defaite(jeu2);
		if ( victory ) {
			System.out.println("Bravo, vous avez gagné !");
			game=false;
		}
		else if ( lost ) {
			System.out.println("BOUM ! Retente ta chance !");
			game=false;
		}
		}
	}
	
	/**
	 * cette méthode représente le déroulement de chaque tour
	 * elle prend en paramètre un tableau de cases 
	 * elle ne renvoie rien
	 */
	public static void tour(cases[][] jeu2) {
		System.out.println("Révéler une case (1) - Déposer un drapeau (2)"); // demande à l'utilisateur de choisir s'il veut révéler une case ou déposer un drapeau

		int drap = ReadKeyboard.readIntLn();
		int x=0;
		int y=0;
		while ((drap!=1) && (drap!=2)) {
				System.out.println("Révéler une case (1) - Déposer un drapeau (2)");
				drap = ReadKeyboard.readIntLn();
			}
		if (drap==1) {
			System.out.println("Entrez les coordonnées de la case que vous souhaitez réveler :");
			System.out.println("Abscisse :");
			y = ReadKeyboard.readIntLn()-1; // le "-1" car dans la méthode affiche les numéros des colones commencent à 1
			System.out.println("Ordonnée :");
			x = ReadKeyboard.readIntLn()-1;	// le "-1" car dans la méthode affiche les numéros des lignes commencent à 1
		}
		else {
			System.out.println("Entrez les coordonnées de la case où vous voulez mettre un drapeau :");
			System.out.println("Abscisse :");
			y = ReadKeyboard.readIntLn()-1;	// le "-1" car dans la méthode affiche les numéros des colones commencent à 1
			System.out.println("Ordonnée :");
			x = ReadKeyboard.readIntLn()-1;	// le "-1" car dans la méthode affiche les numéros des lignes commencent à 1
		}
		if(jeu2[x][y].getBombe()){
				System.out.println("fin du jeu");
				
			}
	    
		reveler(x,y,drap,jeu2); 
		afficherDemineur(jeu2);
		
	}
	
	/**
	 * cette méthode permet de transformer un tableau boolean en tableau case
	 * elle prend en paramètre un tableau boolean
	 */
	public static cases[][] booleanToCases(boolean[][] jeu1) { //tableau boolean avec des bombes supposées déjà réparties grpace à la méthode genererDemineur
		cases[][] jeu2 = new cases[jeu1.length][jeu1[0].length]; //définition du tableau cases
		for(int i=0; i<jeu2.length; i++) {
			for (int j=0; j<jeu2[0].length; j++) {
				jeu2[i][j] = new cases();
			}
		}
		boolean a; // corresond aux bombes de chaque case
		int b;		//correspond aux nombre de voisins
		for (int i=0; i<jeu1.length; i++) {
			for (int j=0; j<jeu1[0].length; j++) {
				a=jeu1[i][j];
				b=nombredeVoisins(i, j, jeu1);
				jeu2[i][j].setX(i);	//on modifie les attributs qui correspondent aux attributs
				jeu2[i][j].setY(j);
				jeu2[i][j].setBombe(a);	//modification de l'attribut bombe
				jeu2[i][j].setNbVoisines(b);	//modification de l'attribut NbVoisines
				jeu2[i][j].setdrapeau(false);	//initialisation de l'attribut drapeau
				jeu2[i][j].setRevele(false);	//initialisation de l'attribut revele
			}
		}
		return jeu2;
	}	
	
	/** 
	 * cette methode renvoie une boolean qui détermine si le jeu est gagné
	 * elle prend en paramètre un tableau de cases
	 */	
	public static boolean victoire (cases[][] tab) {
		boolean a;  		//l'attribut revele de chaque case du tableau
		boolean b; 		 	//l'attribut bombe de chaque case du tableau
		boolean victory=true;
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[0].length; j++) {
				a=tab[i][j].getrevele();
				b=tab[i][j].getBombe();
				if ((a==false)&&(b==false)&&(victory))
					victory=false;
			}
		}
		return victory;
	}
	
	/**
	 * cette methode renvoie une boolean qui determine si le jeu est perdu
	 * elle prends en paramètre un tableau de cases
	 */
	public static boolean defaite (cases[][] tab) {
		boolean lost=false; //la variables à retournée
		boolean a;
		boolean b;
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[0].length; j++) {
				a=tab[i][j].getrevele();
				b=tab[i][j].getBombe();
				if ((a==true)&&(b)&&(lost==false))
					lost=true;
			}
		}
		return lost;
	}
	
	/**
	 * cette methode permet de compter le nombre de voisins
	 * elle prend en paramètre:
	 ***les coordonnées d'une case
	 ***un tableau de boolean
	 * elle renvoie un réel
	 */
	 	public static int nombredeVoisins(int x, int y, boolean[][] monde) {
	    int nbV=0; //initialisation de nombres de voisins
		boolean NW=false;
		boolean N=false;
		boolean NE=false;
		boolean W=false;
		boolean E=false;
		boolean SW=false;
		boolean S=false;
		boolean SE=false;
		if ((x>0)&&(y>0)) {
			NW=monde[x-1][y-1];
		}
		if ((x>0)) {
			N=monde[x-1][y];
		}
		if ((x>0)&&(y<monde[0].length-1)) {
			NE=monde[x-1][y+1];
		}
		if (y>0) {
			W=monde[x][y-1];
		}
		if (y<monde[0].length-1) {
			E=monde[x][y+1];
		}
		if ((y>0)&&(x<monde.length-1)) {
			SW=monde[x+1][y-1];
		}
		if (x<monde.length-1) {
			S=monde[x+1][y];
		}
		if ((y<monde[0].length-1)&&(x<monde.length-1)) {
			SE=monde[x+1][y+1];
		}
		if (NW)
			nbV++;
		if (N)
			nbV++;
		if (NE)
			nbV++;
		if (W)
			nbV++;
		if (E)
			nbV++;
		if (SW)
			nbV++;
		if (S)
			nbV++;
		if (SE)
			nbV++;
		return nbV;
	}
	
	/**
	 * cette methode répartit un nombre de bombes dans un tableau de boolean
	 * elle prend en paramètre:
	 ***tableau boolean
	 ***nombre de bombes
	 */
		public static void genererDemineur(boolean[][] monde, int n) {  // n = nombre de bombes désirées à introduire
		int c=0; 		//c est un compteur
		int x;    		// x et y des coordonnées pour les bombes
		int y;
		int h=monde.length;
		int l=monde[0].length;
		for (int i=0; i<monde.length; i++) {
			for (int j=0; j<monde[i].length; j++){
				monde[i][j]=false;		
			}
		}
		while(c<n) {
			x = (int)(Math.random()*(h-1)) + 1;
			y = (int)(Math.random()*(l-1)) + 1;
			if (monde[x][y] == false) {
				c += 1;
				monde[x][y]=true;
			}
		}
	}
	
	/**
	 * cette methode permet de révéler une case ou mettre un drapeau
	 * elle prend en paramètre:
	 ***les coordonnées x et y
	 ***le choix de l'utilisateur rev
	 ***un tableau de cases
	 */
	public static void reveler (int x , int y , int rev, cases[][] tab ){ //rev traduit le numéro introduit par l'utilisateur (1:Révéler 2:mettre drapeau)
		if(rev==1 && (!tab[x][y].getrevele())){
			tab[x][y].setRevele(true);
			if(tab[x][y].getnbvoisines() == 0) {
				revelation(x,y,tab);
			}
		}
		else if (rev==2 && (!tab[x][y].getrevele())) {
			tab[x][y].setdrapeau(true);
				
		}
		/*else {
			Ca ne marche pas donc il faut redemander à l'utilisateur une nouvelle donnée ( voir méthode tour)*/
		
	}
	
	/**
	 * cette méthode permet de révéler les cases qui se trouvent autour d'une case 
	 * elle prend en paramètre:
	 ***les coordonnées de la case x et y
	 ***un tableau de cases
	 */
	public static void revelation (int x, int y, cases[][] tab) {
		for (int i=x-1; i<=x+1; i++) {
			for (int j=y-1; j<=y+1; j++) {
				if ((j == y) && ( i == x)) { //la case en question ne nous intéresse pas
				}
				else {
					if ((i>=tab.length)||(i<0)||(j<0)||(j>=tab[0].length)) { //condition nécéssaire pour ne pas sortir du plateau
					}
					else {
						reveler(i,j,1,tab);
					}				
				}
			}
		}
	}
						
	
	/** 
	 * cette méthode permet d'afficher le démineur 
	 * elle prend en paramètre un tableau de type cases
	 */
	public static void afficherDemineur( cases[][] monde) {
		int a = 1;	//pour les colones
		int b = 1;	//pour les lignes
		System.out.print("  ");
		for (int k=0; k<monde.length;k++){ //on affiche le numéro de chaque colone en començcant par 1
			System.out.print(a +" ");
			a++;
		}
		System.out.println();
		System.out.print("  ");
		for (int k=0; k<monde.length;k++){ //on affiche le contour supérieur de la grille
			System.out.print("__");
		}
		System.out.println();
		for (int i=0; i<monde.length; i++){
			System.out.print ("| ");
			for (int j=0; j<monde[0].length; j++){
				if (monde[i][j].getrevele()){ //condition si la case est révélée
					if (monde[i][j].getBombe()) {
						System.out.print("B ");
					}
					else {
						System.out.print(monde[i][j].getnbvoisines() + " ");
					}
				}
				else {
					if (monde[i][j].getdrapeau()) { // si il y a un drapeau au niveau de la case
						System.out.print("P ");
					}
					else {
						System.out.print(". "); //les deux attributs revele et case sont false
					}
				}
			}
			System.out.print(" |"); 
			System.out.println(b); // à la fin de la deuxième boucle on affiche le numéro de la ligne
			b++;
			
		}
		System.out.print("  ");
		for (int k=0; k<monde.length;k++){ //limite du bas de la grille de jeu
			System.out.print("__"); 
		}
		System.out.println();
	}		
}
