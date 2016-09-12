import java.util.Scanner;
public class Plateau {
	public int taille; //Taille du plateau
	public int [][] plateauMod; //Tableau d'entier représentant le plateau
	/*Position des joueurs*/
	public int X1;
	public int X2;
	public int Y1;
	public int Y2;
	/*Entiers représentant les joueurs*/
	public int j1;
	public int j2;
	public int lastcase1=0;
	public int lastcase2=0;
	boolean endGame = false;
	String ligne1="";
	String ligne2="";
	String ligne3="";
	public Plateau (int t) { //Entiers désignant les différentes entités présentes sur le terrain
		int vide = 0;
		int joueur1 = 1;
		int joueur2 = 2;
		int rocher = 3;
		int flower1 = 5;
		int grass2 = 7;
		int flower2=6;
		int water=8;
		int bridge=9;
		this.plateauMod = new int [t][t];
		for (int x = 0; x < t; x++){ //Placement des rochers autour du terrain
			this.plateauMod [0][x] = 3;
			this.plateauMod [t - 1][x] = 3;
			this.plateauMod [x][0] = 3;
			this.plateauMod [x][t - 1] = 3;
		}
	}
	public int [] getPos(int i) { //Récuperation de la position d'un joueur
		int [] t = new int [2];
		if (i==1){
			t[0] = X1;
			t[1] = Y1;	
		} else {
			t[0] = X2;
			t[1] = Y2;	
		}			
		return t;
	}
	public void debutJeu (int taille) { //Placement aléatoire des joueurus dans une zone délimitée
		X1 = 3 + ((int) (Math.random()*3));
		Y1 = 5 + ((int) (Math.random()*7));
		X2 = (taille - 4) - ((int) (Math.random()*3));
		Y2 = 5 + ((int) (Math.random()*7));
		this.plateauMod[X1][Y1] = 1;
		this.plateauMod[X2][Y2]	= 2;
		int rand;
		for (int i = 0; i < this.plateauMod.length; i++) {
			for (int j = 0; j < this.plateauMod[0].length; j++) {
				if (plateauMod[i][j] != 1 && plateauMod[i][j] != 2 && plateauMod[i][j] != 3){
					rand = (int) (Math.random()*100) ;
					if (rand < 3){plateauMod[i][j]=5;}
					if (3 <= rand  && rand<6){plateauMod[i][j]=6;}
					if (rand > 80){plateauMod[i][j]=7;}
					if (rand > 95){plateauMod[i][j]=3;}
				}
			}
		}
		for (int i = 0 ; i < this.plateauMod.length; i++){
			plateauMod[8][i] = 8;
		}
		plateauMod[8][4] = 9;
		plateauMod[8][8] = 9;
		plateauMod[8][12] = 9;
	}	
	public boolean deplacementJ1 (int direction) { //Gestion de déplacement de J1
		this.plateauMod[X1][Y1] = this.lastcase1;
		if (possibleDeplacementJ1(direction)){
			if (direction==8){
				this.lastcase1 = this.plateauMod[X1-1][Y1] ;
				X1=X1-1;
			}
			if (direction == 7) {
				this.lastcase1 = this.plateauMod[X1][Y1-1] ;
				Y1=Y1-1;
			}
			if (direction ==9){
				this.lastcase1 = this.plateauMod[X1][Y1+1] ;
				Y1++;	
			}
			if (direction ==5){
				this.lastcase1 = this.plateauMod[X1+1][Y1] ;
				X1++;	
			}
		}
		this.plateauMod[X1][Y1] = 1;
		return true;		
	}	
	public boolean possibleDeplacementJ1(int direction){ //Déplacement impossible sous certaines conditions
		if (direction ==5){
			if (this.plateauMod[X1+1][Y1] == 3 || this.plateauMod[X1+1][Y1] == 2 || this.plateauMod[X1+1][Y1] == 8 ){
				return false;
			}
		}
		if (direction==8){
			if (this.plateauMod[X1-1][Y1] == 3 || this.plateauMod[X1-1][Y1] == 2 || this.plateauMod[X1-1][Y1] == 8){
				return false;
			}
		}
		if (direction ==9){
			if (this.plateauMod[X1][Y1+1] == 3 || this.plateauMod[X1][Y1+1] == 2 || this.plateauMod[X1][Y1+1] == 8){
				return false;	
			}
		}
		if (direction == 7) {
			if (this.plateauMod[X1][Y1-1] == 3 || this.plateauMod[X1][Y1-1] == 2 || this.plateauMod[X1][Y1-1] == 8){
				return false;
			}
		}
		return true;
	}
	public boolean possibleDeplacementJ2(int direction){
		if (direction ==5){
			if (this.plateauMod[X2+1][Y2] == 3 || this.plateauMod[X2+1][Y2] == 1 || this.plateauMod[X2+1][Y2] == 8){
				return false;
			}
		}
		if (direction==8){
			if (this.plateauMod[X2-1][Y2] == 3 || this.plateauMod[X2-1][Y2] == 1 || this.plateauMod[X2-1][Y2] == 8){
				return false;
			}
		}
		if (direction ==9){
			if (this.plateauMod[X2][Y2+1] == 3 || this.plateauMod[X2][Y2+1] == 1 || this.plateauMod[X2][Y2+1] == 8){
				return false;	
			}
		}
		if (direction == 7) {
				if (this.plateauMod[X2][Y2-1] == 3 || this.plateauMod[X2][Y2-1] == 1 || this.plateauMod[X2][Y2-1] == 8){
				return false;
			}
		}
		return true;
	}
	public boolean deplacementJ2 (int direction) {
		if (possibleDeplacementJ2(direction)){
			this.plateauMod[X2][Y2] = this.lastcase2;			
			if (direction == 8) {		
				this.lastcase2 = this.plateauMod[X2-1][Y2];
				X2=X2-1;
			}
			if (direction == 7) {
				this.lastcase2 = this.plateauMod[X2][Y2-1];		
				Y2=Y2-1;
			}
			if (direction ==9){		
				this.lastcase2 = this.plateauMod[X2][Y2+1];
				Y2++;
			}
			if (direction ==5){	
				this.lastcase2 = this.plateauMod[X2+1][Y2];
				X2++;
			}
		}
		this.plateauMod[X2][Y2] = 2;
		return true;
	}
	public static void jouer (Plateau monde , perso J1 , perso J2 , Frame frame , keyListener kListener , int joueur ){	//Ecran de début de jeu lorsque le joueur n'a pas effectué d'action
		boolean tour = true;
		J1.ligne1=J1.ligne2=J1.ligne3=J1.ligne4=J1.ligne5=J1.ligne6=J1.ligne7=J1.ligne8=J1.ligne9=J1.ligne10="";
		J2.ligne1=J2.ligne2=J2.ligne3=J2.ligne4=J2.ligne5=J2.ligne6=J2.ligne7=J2.ligne8=J2.ligne9=J2.ligne10="";
		monde.ligne1=J1.nom()+" a vous de jouer";
		monde.ligne2="utilisez les fleches pour bouger et les touches 1 a 4 pour lancer des sorts ";
		monde.ligne3="appuyez sur espace pour passer votre tour"	;
		J1.debutdetour();	
		frame.repaint();
		while (tour){ //Gestion de tous les sorts en fonction de la classe concernée
			while (kListener.action==0){ 
				System.out.println ("");
			}
			J1.ligne1=J1.ligne2=J1.ligne3=J1.ligne4=J1.ligne5=J1.ligne6=J1.ligne7=J1.ligne8=J1.ligne9=J1.ligne10="";
			J2.ligne1=J2.ligne2=J2.ligne3=J2.ligne4=J2.ligne5=J2.ligne6=J2.ligne7=J2.ligne8=J2.ligne9=J2.ligne10="";			
			monde.ligne1=monde.ligne3=monde.ligne2="";		
			testglyphe(monde, J1, kListener.action, joueur);
			if (kListener.action == 1) {
				if (portee(1,3,monde,J1,J2)){		
					frame.repaint();
					int deg = J1.degats();
					if (J1.reussite){
						int degf = (int) (deg - J2.defense*deg/100);
						J2.PV =J2.PV - degf;
						monde.ligne2=J2.nom()+" a perdu "+degf+" PV, il lui en reste "+J2.PV;
						J1.reussite=false;
						if (J1.classe==5 ){ 
							repousse(joueur,monde,J1,J2);
						}
						monde.ligne2=J2.nom()+" a perdu "+degf+" PV, il lui en reste "+J2.PV;
						frame.repaint();
					} else {
						frame.repaint();
					}
				} else {
					monde.ligne1="Votre ennemi est hors de portee";
					frame.repaint();
				}	
			}
			if (kListener.action == 2) {
                 if (J1.classe == 3 ){
                    J1.soutien();
                    if(J1.reussite){
                        J2.intelligence = J2.intelligence -2 ;
                        J2.force = J2.force -2;
                        System.out.println("cc");
                        frame.repaint();
                        J1.reussite = false;
                    }
                }else{
                J1.soutien();
				frame.repaint();
                }
			}
			if (kListener.action == 3) {
				if (J1.classe == 2){
					if (portee2(1,3,monde,J1,J2)){
						frame.repaint();
						int deg = J1.sort3();
						if (J1.reussite){
							int degf = (int) (deg - J2.defense*deg/100);
							J2.PV =J2.PV - degf;
							monde.ligne2=J2.nom()+" a perdu "+degf+" PV";
							frame.repaint();
							repousse(1,monde,J1,J2);
							monde.ligne2=J2.nom()+" a perdu "+degf+" PV";
							J1.reussite=false;
						} else {
							frame.repaint();
						}
					} else {
						monde.ligne1="Votre ennemi est hors de portee";
						frame.repaint();
					}	
				} 
				if (J1.classe == 5 ){
					if (portee(1,1,monde,J1,J2)){
						int deg = J1.sort3();
						if (J1.reussite){
							if(joueur ==1){
								placerglyphe(monde,2);
							} else {
								placerglyphe(monde,1);
							}
						}
						J1.reussite = false;
						frame.repaint();
					} else {		
						monde.ligne1="Votre ennemi est hors de portee";
						frame.repaint();
					}		
				}
				if (J1.classe == 1 || J1.classe == 3 || J1.classe ==4){					
					J1.sort3();
					frame.repaint();
				}
			}
			if(kListener.action == 4){
				if (portee(1,2,monde,J1,J2)){
					frame.repaint();
					int deg = J1.sortspe();
					if (J1.reussite){
						int degf = (int) (deg - J2.defense*deg/100);
						J2.PV =J2.PV - degf;
						monde.ligne2=J2.nom()+" a perdu "+degf+" PV, il lui en reste "+J2.PV;
						frame.repaint();
					} else {
						frame.repaint();
					}
				} else {
					monde.ligne1="Votre ennemi est hors de portee";
					frame.repaint();
				}	
			}	
			if (kListener.action == 7 ||kListener.action == 8 ||kListener.action == 9 ||kListener.action == 5 ){ //Gestion des déplacements aux flèches directionnelles
				if (J1.PA >= 2){		
					if (joueur == 1){			
						if(monde.possibleDeplacementJ1(kListener.action))	{
							monde.deplacementJ1(kListener.action);
							J1.PA = J1.PA -2 ;
							frame.repaint();								
						}
					} else {
						if(monde.possibleDeplacementJ2(kListener.action))	{
							monde.deplacementJ2(kListener.action);
							J1.PA = J1.PA -2 ;
							frame.repaint();								
						}
					}
				}
			}
			if(kListener.action == 6){
				tour = false;
				J1.passertour();
			}
			if (J2.estvivant() == false){
				tour=false;
			}
			kListener.action=0;
			frame.repaint();
		}	
		frame.repaint();
	}		
	public static int [] trie (int a,int b , int c1 , int c2 ){
		int stock ; 
		int [] t = new int [2];
		int val;
		if (a > b){
			t[0] = c1;
			t[1] = c2;
		}
		if (a < b){
			t[0] = c2;
			t[1] = c1;
		}
		if (a == b){
			val =(int) (Math.random()*100) ;
			if (val < 50){
				t[0] = c1;
				t[1] = c2;
			} else {
				t[0] = c2;
				t[1] = c1;
			}
		}
		return t;
	}	
	public static boolean portee (int J, int po, Plateau monde, perso J1, perso J2) { //Gestion de la portée des sorts en fonction de la position des deux joueurs
		int []t1 = monde.getPos(1);
		int []t2 = monde.getPos(2);
		if(J==1){
			if (Math.abs(t1[0]-t2[0])+Math.abs(t1[1]-t2[1]) <= J1.t[2][po]){
				return true;
			}
		}else{
			if (Math.abs(t1[0]-t2[0])+Math.abs(t1[1]-t2[1]) <= J2.t[2][po]){
				return true;
			}
		}
		return false ;
	}	
	public static void repousse (int J , Plateau monde , perso J1 ,perso J2){ //Gestion des sorts repoussant l'autre joueur
		int []t1 = monde.getPos(1);
		int []t2 = monde.getPos(2);
		if (J1.classe == 5) {
			if (J==1){
				if ( t1[0]-t2[0] == 0){
					if (t1[1] > t2[1]){ /*vers la gauche*/
						testglyphe(monde, J2, 7, 2);
						monde.deplacementJ2(7);
					} else {
						testglyphe(monde, J2, 9,2);
						monde.deplacementJ2(9); /*vers la droite*/
					}
				}
				if ( t1[1]-t2[1] == 0) {
					if (t1[0] > t2[0]){ /*vers le haut*/
						testglyphe(monde, J2, 8, 2);
						monde.deplacementJ2(8);
					} else {
						testglyphe(monde, J2, 5, 2);
						monde.deplacementJ2(5); /*vers le bas*/
					}
				}
			} else {
				if ( t1[0]-t2[0] == 0){
					if (t1[1] < t2[1]){ /*vers la gauche*/
						testglyphe(monde, J2, 7, 2);
						monde.deplacementJ1(7);
					} else {
						testglyphe(monde, J2, 9,2);
						monde.deplacementJ1(9); /*vers la droite*/
					}
				}
				if ( t1[1]-t2[1] == 0) {
					if (t1[0] < t2[0]){ /*vers le haut*/
						testglyphe(monde, J2, 8, 2);
						monde.deplacementJ1(8);
					} else {
						testglyphe(monde, J2, 5, 2);
						monde.deplacementJ1(5); /*vers le bas*/
					}
				}
			}
		}
		if (J1.classe == 2) {
			if ( t1[0]-t2[0] == 0){
				if (t1[1] > t2[1]){ /*vers la gauche*/
					testglyphe(monde, J2, 7, 2);
					monde.deplacementJ2(7);
					testglyphe(monde, J2, 7, 2);
					monde.deplacementJ2(7);
					testglyphe(monde, J2, 7, 2);
					monde.deplacementJ2(7);

				} else {
					testglyphe(monde, J2, 9, 2); /*vers la droite*/
					monde.deplacementJ2(9);
					testglyphe(monde, J2, 9, 2);
					monde.deplacementJ2(9); 
					testglyphe(monde, J2, 9, 2);
					monde.deplacementJ2(9);
				}
			}
			if ( t1[1]-t2[1] == 0) {
				if (t1[0] > t2[0]){ /*vers le haut*/
					testglyphe(monde, J2, 8, 2);
					monde.deplacementJ2(8);
					testglyphe(monde, J2, 8, 2);
					monde.deplacementJ2(8);
					testglyphe(monde, J2, 8, 2);
					monde.deplacementJ2(8);
				} else {
					testglyphe(monde, J2, 5, 2);/*vers le bas*/
					monde.deplacementJ2(5);
					testglyphe(monde, J2, 5, 2); 
					monde.deplacementJ2(5);
					testglyphe(monde, J2, 5, 2);
					monde.deplacementJ2(5);
				}
			}
		}
	}
	public static void testglyphe (Plateau monde , perso J1 , int dir, int joueur) { //Application des dégats de la glyphe
		int []t1 = monde.getPos(joueur);
		int deg = 60 - J1.defense*60/100;
		monde.ligne1="";
		monde.ligne2="";
		monde.ligne3="";
		if ( dir ==5){
			if (monde.plateauMod[t1[0]+1][t1[1]] == 4){
				J1.PV =(int) (J1.PV - deg);
				monde.ligne1= "J'ai glyphe chef !";
				monde.ligne2=J1.nom()+" a perdu "+deg+" PV, il lui en reste "+J1.PV;
				System.out.println(J1.PV);
				monde.plateauMod[t1[0]+1][t1[1]] =0;
			}			
		}
		if ( dir ==7){
			if (monde.plateauMod[t1[0]][t1[1]-1] == 4){
				J1.PV =(int) (J1.PV - deg);
				monde.ligne1= "J'ai glyphe chef !";
				monde.ligne2=J1.nom()+" a perdu "+deg+" PV, il lui en reste "+J1.PV;
				System.out.println(J1.PV);
				monde.plateauMod[t1[0]][t1[1]-1] =0;
			}
		}
		if ( dir ==8){
			if (monde.plateauMod[t1[0]-1][t1[1]] == 4){
				J1.PV =(int) (J1.PV - deg);
				monde.ligne1= "J'ai glyphe chef !";
				monde.ligne2=J1.nom()+" a perdu "+deg+" PV, il lui en reste "+J1.PV;
				System.out.println(J1.PV);
				monde.plateauMod[t1[0]-1][t1[1]] =0;
			}
		}
		if ( dir ==9){
			if (monde.plateauMod[t1[0]][t1[1]+1] == 4){
				J1.PV =(int) (J1.PV - deg);
				monde.ligne1= "J'ai glyphe chef !";
				monde.ligne2=J1.nom()+" a perdu "+deg+" PV, il lui en reste "+J1.PV;
				System.out.println(J1.PV);
				monde.plateauMod[t1[0]][t1[1]+1] =0;
			}
		}
	}
	public static void placerglyphe ( Plateau monde , int joueur) { //Placement de la glyphe du mage (2 pièges sur les 4 directions autour de l'adversaire)
		int [] t = monde.getPos(joueur);
		int compt =0;
		int rand =(int) (Math.random()*100);
		int x = t[0];
		int y = t[1];
		if (monde.plateauMod[x+1][y]!=1 && monde.plateauMod[x+1][y]!=2 && monde.plateauMod[x+1][y]!=3  && monde.plateauMod[x+1][y]!=8){ 
			monde.plateauMod[x+1][y]=6;
		}
		if (monde.plateauMod[x-1][y]!=1 && monde.plateauMod[x-1][y]!=2 && monde.plateauMod[x-1][y]!=3 && monde.plateauMod[x-1][y]!=8) { 
			monde.plateauMod[x-1][y]=6;
		}
		if (monde.plateauMod[x][y+1]!=1 && monde.plateauMod[x][y+1]!=2 && monde.plateauMod[x][y+1]!=3 && monde.plateauMod[x][y+1]!=8){ 
			monde.plateauMod[x][y+1]=6;
		}
		if (monde.plateauMod[x][y-1]!=1 && monde.plateauMod[x][y-1]!=2 && monde.plateauMod[x][y-1]!=3  && monde.plateauMod[x][y-1]!=8){ 
			monde.plateauMod[x][y-1]=6;
		}
		if (rand < 50) {
			x = t[0]+1;
		} else {
			y = t[1]-1;
		}		
		if (monde.plateauMod[x][y]!=1 && monde.plateauMod[x][y]!=2 && monde.plateauMod[x][y]!=3 && monde.plateauMod[x][y]!=8){ 
			monde.plateauMod[x][y]=4;
		}
		x = t[0];
		y = t[1];
		rand =(int) (Math.random()*100);
		if (rand < 50) {
			y = t[1]+1;		
		} else {
			x = t[0]-1;
		}
		if (monde.plateauMod[x][y]!=1 && monde.plateauMod[x][y]!=2 && monde.plateauMod[x][y]!=3 && monde.plateauMod[x][y]!=8){ 
			monde.plateauMod[x][y]=4;
		}	
	}
	public static boolean portee2 (int J,int po,Plateau monde,perso J1,perso J2) {
		int []t1 = monde.getPos(1);
		int []t2 = monde.getPos(2);
		if(J==1){
			if ((t1[0]==t2[0] || t1[1]==t2[1]) && Math.abs(t1[0]-t2[0])+Math.abs(t1[1]-t2[1]) <= J1.t[2][po]){
				return true;
			}
		} else {
			if ((t1[0]==t2[0] || t1[1]==t2[1]) && Math.abs(t1[0]-t2[0])+Math.abs(t1[1]-t2[1]) <= J2.t[2][po]){
				return true;
			}
		}
		return false ;
	}
	public static perso [] selection (Plateau monde) { //Menu de sélection des personnages
		Thread song = SoundManager.music("sfx/menu.wav");
		song.start();
		Frame2 framemenu = new Frame2(monde);
		keyListener kListener = new keyListener();		
		framemenu.addKeyListener(kListener);			
		while (kListener.action==0){ 
			System.out.println ("");
		}
		SoundManager.play("sfx/test1.wav");
		monde.j1 = kListener.action;
		perso J1 = new perso (monde.j1);
		kListener.action=0;
		framemenu.repaint();
		while (kListener.action==0 || kListener.action==monde.j1){ 
			System.out.println ("");
		}
		SoundManager.play("sfx/test1.wav");
		monde.j2 = kListener.action;
		perso J2 = new perso (monde.j2);
		framemenu.repaint();
		try {
			Thread.sleep(2000); //2 secondes
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		framemenu.setVisible(false);
		song.stop();
		perso [] t = new perso [2];
		t[0] = J1;
		t[1] = J2;
		return t;
	}	
	public String randomSong (){ //Chanson aléatoire pour le jeu
		int	rand = (int) (Math.random()*100) ;
		if (rand<20){
			return "sfx/song.wav";
		}
		if (rand<40){
			return "sfx/song2.wav";
		}
		if (rand<60){
			return "sfx/song3.wav";
		}
		if (rand<80){
		return "sfx/song4.wav";
		}
		return "sfx/troll.wav";
	}
}
