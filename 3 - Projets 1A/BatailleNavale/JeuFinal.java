import java.util.Scanner ;

public class JeuFinal{

    public static void main(String[] args) {
        Scanner sc= new Scanner (System.in);
        int t =10;
        
        Plateau MonPlateauJ1 = new Plateau(t);   //Plateau de J1 qui comportera les 4 états 1,2,3,4
        Plateau MonPlateauJ2 = new Plateau(t);   //Plateau de J2 qui comportera les 4 états 1,2,3,4
        Plateau PlateauDeJ2 = new Plateau(t);    //Plateau de J2 qui sera affiché à J1 qui comportera les positions testées
        Plateau PlateauDeJ1 = new Plateau(t);    //Plateau de J1 qui sera affiché à J2 qui comportera les positions testées
        
        System.out.println("Votre plateau est de dimension 10*10, chaque chiffre affiché corresond à l'état d'une case");
        System.out.println("Voici la signification des chiffres: 1=mer; 2=portion de bateau non touche; 3=zone sans bateau touchee; 4=portion de bateau touche ");
        creationTousLesBateaux(MonPlateauJ1,MonPlateauJ2);
        //Le plateau est affiché après chaque bateau placé

        int [] [] monMondeJ1 = MonPlateauJ1.getmonde();
        int [] [] monMondeJ2 = MonPlateauJ2.getmonde();
        int [] [] LeMondeDeJ1 = PlateauDeJ1.getmonde();
        int [] [] LeMondeDeJ2 = PlateauDeJ2.getmonde();
        
        boolean VictoireJ1 = false;
        boolean VictoireJ2 = false;
        boolean partiefinie = false;//nouveau booléen indiquant si quelqu'un a gagné la partie

		//INITIALISATION variables
        int [] coordonneesDeTir = {0,0};  
        int joueur = 1;
        
        //TOURS DE JEU
        while(partiefinie == false){
			
			if(joueur == 1){
				System.out.println();
				System.out.println("C'est le tour de J1");
        
				System.out.println("J1, voici votre plateau (Constatez les degats)");
				affichage(monMondeJ1); 
        
				System.out.println("J1, Voici votre vision du plateau de J2");
				affichage(LeMondeDeJ2);
        
				coordonneesDeTir = coordtir(LeMondeDeJ2);
            
				monMondeJ2 = actualisationMonPlateau(monMondeJ1,monMondeJ2,LeMondeDeJ1,LeMondeDeJ2,coordonneesDeTir[1],coordonneesDeTir[0],joueur);
				LeMondeDeJ2 = actualisationPlateauDe(monMondeJ1,monMondeJ2,LeMondeDeJ1,LeMondeDeJ2,coordonneesDeTir[1],coordonneesDeTir[0],joueur);
				System.out.println("J1, voici le resultat de votre tir");
				affichage(LeMondeDeJ2);
        
				VictoireJ1 = partiegagnepourJ1 (monMondeJ2);
            
				// La victoire de J1 est testée 
				if(VictoireJ1==true){
					partiefinie=true;
					joueur=3;  // si le joueur a gagné, le joueur 2 ne joue pas
				}
				else{ 
					joueur=2;
				}
			}

			if(joueur==2){
				System.out.println();
				System.out.println("C'est le tour de J2");
            
				System.out.println("J2, voici votre plateau (Constatez les degats)");
				affichage (monMondeJ2); 
        
				System.out.println("J2, Voici votre vision du plateau de J1");
				affichage (LeMondeDeJ1);
        
				coordonneesDeTir = coordtir(LeMondeDeJ1);
        
				monMondeJ1 = actualisationMonPlateau(monMondeJ1,monMondeJ2,LeMondeDeJ1,LeMondeDeJ2,coordonneesDeTir[1],coordonneesDeTir[0],joueur);
				LeMondeDeJ1 = actualisationPlateauDe(monMondeJ1,monMondeJ2,LeMondeDeJ1,LeMondeDeJ2,coordonneesDeTir[1],coordonneesDeTir[0],joueur);
				System.out.println("J2, voici le resultat de votre tir");
				affichage(LeMondeDeJ1);
        
				VictoireJ2 = partiegagnepourJ2 (monMondeJ1);
				if(VictoireJ2==true){
					partiefinie=true;
					joueur=3;
				}
				else{ 
					joueur=1;
				}
			}
        } //Fin du while
        
        if(VictoireJ1 == true){
            System.out.println("J1 vous avez gagne");
		}
        else if(VictoireJ2==true){
            System.out.println("J2 vous avez gagne");
        }
    }
       
   public static void creationTousLesBateaux(Plateau MonPlateauJ1,Plateau MonPlateauJ2){
        
        System.out.println("Joueur 1, entrez dans l'ordre Porte-Avions, Croiseur, Contre-torpilleur, Sous-marin, Torpilleur");
        System.out.println("");
        System.out.println("J1, creez votre Porte-Avions(taille=5)");
        Bateau Porteavions1 = creerbateau(1,MonPlateauJ1,5);
                
        System.out.println("J1, creez votre Croiseur(taille=4)");
        Bateau Croiseur1 = creerbateau(1,MonPlateauJ1,4);
                
        System.out.println("J1, creez votre Contre-Torpilleur(taille=3)");
        Bateau ContreTorpilleur1 = creerbateau(1,MonPlateauJ1,3);
        
        System.out.println("J1, creez votre Sous-Marin(taille=3)");
        Bateau SousMarin1 = creerbateau(1,MonPlateauJ1,3);
        
        System.out.println("J1, creez votre Torpilleur(taille=2)");
        Bateau Torpilleur1 = creerbateau(1,MonPlateauJ1,2);

        System.out.println("Joueur 2, entrez dans l'ordre Porte-Avions, Croiseur, Contre-torpilleur, Sous-marin, Torpilleur");
        System.out.println("");
        System.out.println("J2, creez votre Porte-Avions(taille=5)");
        Bateau Porteavions2 = creerbateau(2,MonPlateauJ2,5);
        
        System.out.println("J2, creez votre Croiseur(taille=4)");
        Bateau Croiseur2 = creerbateau(2,MonPlateauJ2,4);
        
        System.out.println("J2, creez votre Contre-Torpilleur(taille=3)");
        Bateau ContreTorpilleur2 = creerbateau(2,MonPlateauJ2,3);
        
        System.out.println("J2, creez votre Sous-Marin(taille=3)");
        Bateau SousMarin2 = creerbateau(2,MonPlateauJ2,3);
        
        System.out.println("J2, creez votre Torpilleur(taille=2)");
        Bateau Torpilleur2 = creerbateau(2,MonPlateauJ2,2);
        }
    
    public static void affichage (int[][]tab) {
        for (int i=0;i<tab.length;i++){
            for(int j=0; j<tab[0].length; j++){
                System.out.print(tab[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    
	public static Bateau creerbateau (int j, Plateau p, int longueur){
		// méthode qui retourne un bateau répondant à tous les critères du jeu
		// j est le numéro du joueur, à voir si on utilise ce paramètre ou pas
		 
		Scanner sc1= new Scanner (System.in);
		Scanner sc= new Scanner (System.in);
		// On demande désormais à l'utilisateur d'entrer les propriétés qu'il souhaite donner à son bateau
		  
		int t= longueur;
		System.out.println("Entrez la direction du bateau, tapez h pour horizontal ou v pour vertical");
        String d= sc.nextLine();
        while (d.equals("h")==false && d.equals("v")==false){
        System.out.println("Entrez la direction du bateau, tapez h pour horizontal ou v pour vertical");
        d= sc.nextLine();
        }
		System.out.println("Entrez la colonne souhaitee (0 a 9)");
		int x = sc1.nextInt();
		System.out.println("Entrez la ligne souhaitee (0 a 9)");
		int y = sc1.nextInt();
		
		// On vérifie maintenant que les premières coordonées entrées ne soient pas conflictuelles (chevauchement ou hors limite du plateau)
		
		if (d.equals("h")){  // si l'utilisateur a demandé un bateau placé horizontalement
			while ( (x>(10- t) ) || (chevauche(x,y,t,d,p))==true) {  // si le bateau chevauche ou bien si il dépasse, la coordonée x est redemandée
				System.out.println("Le bateau ainsi defini depasse les limites du tableau ou bien se chevauche avec un autre, entrez une nouvelle colonne");
				x = sc1.nextInt();
                System.out.println("Entrez la ligne souhaitee (0 a 9)");
                y = sc1.nextInt();
			}
			for (int i=x; i<x+t;i++) { // Une fois la coordonée validée, nous modifions le plateau afin de lui faire signifier qu'un bateau est présent;
				p.modifcompo(y,i,2);   
			}	
		
		}
        else if (d.equals("v")) {  // si l'utilisateur a demandé un bateau placé verticalement
			while ((y>10-t) || ( chevauche(x,y,t,d,p))==true ){ // si le bateau chevauche ou bien si il dépasse, la valeur est redemandée 
				System.out.println("Le bateau ainsi defini depasse les limites du tableau ou bien se chevauche avec un autre, entrez une nouvelle ligne");
				y = sc1.nextInt();
                System.out.println("Entrez la colonne souhaitee (0 a 9)");
                x = sc1.nextInt();
			}
			for (int i=y; i<y+t;i++){ //  Une fois la coordonée validée, nous modifions le plateau afin de lui faire signifier qu'un bateau est présent;
			p.modifcompo(i,x,2);
			}	
		}// Création du bateau + vérification de ses attributs
        
        int [] [] tab = p.getmonde();
		System.out.println("");
		System.out.println("Voici votre plateau apres avoir place ce bateau :");
		affichage(tab);
		System.out.println("");
		
		Bateau b = new Bateau(t,d,x,y);
		return b;  //renvoi du bateau
	}

	public static boolean chevauche (int x, int y, int longueur, String d, Plateau p) {  
		// Cette méthode renvoie true si aux coordonées (x,y) et aux coordonées suivantes (en fonction de la longueur et de la direction du bateau), entrés en paramètres, 
		// le plateau n'est pas occupé 
			
		for (int j=0; j<longueur;j++){  // une boucle sur la longueur du bateau 
			if (d.equals("h")){  
			// si l'utilisateur a demandé un bateau placé horizontalement, alors on vérifie le long de la colonne que aucun autre bateau n'est présent (ceci est signifié par un 2)
				if ( p.getval(y,x+j)==2 ) {
					return true;  // dès qu'une case est occupée, la méthode renvoie false et ne continue pas à vérifier pour le restant des coordonées
				}
			}else if (d.equals("v")){  
			// si l'utilisateur a demandé un bateau placé verticalement, alors on vérifie le long de la colonne que aucun autre bateau n'est présent (ceci est signifié par un 2)
				if (p.getval(y+j,x)==2) {
					return true;
				}
			}
		}
		return false ;  // si le bateau n'en chevauche aucun autre, alors la méthode renvoie true
	}


//Cette méthode est une boucle de test pour savoir si la partie est finie :
//elle est utilisée dans le main, dans la boucle de tour de jeu de joueur 2 

	public static boolean partiegagnepourJ2(int [][] monMondeJ1){
		//if joueur == 1   on n'utilise pas joueur car on intègrera la dimension de joueur dans la structure du main !
		boolean b = true;
		for(int i = 0 ; i < 10; i++ ){
			for(int j = 0 ; j <10 ; j++ ){
				if(monMondeJ1[i][j]==2){
					b = false; //si le plateau possède une case bateau non coulée alors la partie n'est pas encore gagnée
					return b;  //on peut alors directement retourner que la partie n'est pas terminée et sortir de la méthode
				}
			}
		}
		return b;
	}
	
//Cette méthode est une boucle de test pour savoir si la partie est finie :
//elle est utilisée dans le main, dans la boucle de tour de jeu de joueur 1

	public static boolean partiegagnepourJ1 (int [][] monMondeJ2){
		//if joueur == 1   on n'utilise pas joueur car on intègrera la dimension de joueur dans la structure du main !
		boolean b = true;
		for(int i = 0 ; i < 10; i++ ){
			for(int j = 0 ; j <10 ; j++ ){
				if(monMondeJ2[i][j]==2){
					b = false; //si le plateau possède une case bateau non coulée alors la partie n'est pas encore gagnée
					return b;  //on peut alors directement retourner que la partie n'est pas terminée et sortir de la méthode
				}
			}
		}
		return b;
	}


/*méthode qui renvoie un tableau de 2 nombres contenant les coordonnées de tir
 * on redemande les coordonnées tant qu'elles ne sont pas hors du plateau ou des positions ayant déjà fait l'objet d'un tir
 *  */
	public static int [] coordtir(int [] [] LeMondeDe){
	
		Scanner sc1 = new Scanner (System.in);
		Scanner sc2 = new Scanner (System.in);
		int[] tab = new int[2];
		System.out.println("Entrez le numero de la colonne");
		tab[0]= sc1.nextInt();
		System.out.println("Entrez le numero de la ligne");
		tab[1]= sc2.nextInt();
	
		while(tab[0]>9 || tab[1]>9 || LeMondeDe[tab[1]][tab[0]]==3 || LeMondeDe[tab[1]][tab[0]]==4){
			System.out.println("Les coordonnees ont deja ete entrees ou sont a l'exterieur du tableau.");
			System.out.println("Entrez le numero de la colonne");
			tab[0]= sc1.nextInt();
			System.out.println("Entrez le numero de la ligne");
			tab[1]= sc2.nextInt();  //Sinon on peut utiliser do while
		}
		return tab;
	}
/* On utilisera cette méthode 2 fois une fois pour chaque joueur avec comme paramètre le tableau du joueur adverse à chaque fois
 * */
 
//Cette méthode est utilisée dans le main après avoir actualisé le tableau MonMonde(suite à un tir)
	public static int [][] actualisationPlateauDe(int [][] monMondeJ1, int [][] monMondeJ2, int [][] LeMondeDeJ1, int [][] LeMondeDeJ2 , int ligne, int colonne, int joueur){
		int a = ligne;
        int b = colonne;
        int [][] TableauActualise = new int[10][10];
        
        if(joueur==1){
            if(monMondeJ2[a][b]==3){ //si la case de MonMonde actualisé est une case de mer qui a été touchée (état 3)
				LeMondeDeJ2[a][b]=3; //alors le tireur pourra voir que cette case est passée à l'état 3
			}
			else if(monMondeJ2[a][b]==4){
				LeMondeDeJ2[a][b]=4;
			}
            TableauActualise = LeMondeDeJ2;
		}	
		
		if(joueur==2){
			if(monMondeJ1[a][b]==3){
				LeMondeDeJ1[a][b]=3;
			}
			else if(monMondeJ1[a][b]==4){
				LeMondeDeJ1[a][b]=4;
			}
            TableauActualise = LeMondeDeJ1;
		}
        return TableauActualise;
	}
			
	public static int [][] actualisationMonPlateau(int [][] monMondeJ1, int [][] monMondeJ2, int [][] LeMondeDeJ1, int [][] LeMondeDeJ2 , int ligne, int colonne, int joueur){
		int a = ligne;
        int b = colonne;
        int [][] TableauActualise = new int[10][10];
        if(joueur==1){
			if(monMondeJ2[a][b]==1){ //si la case du plateau de J2 est une case de mer, on la change en une case de mer sur laquelle J1 a tiré
				monMondeJ2[a][b]=3;
			}
			else if(monMondeJ2[a][b]==2){//si la case du plateau de J2 comporte une portion de bateau, on la change en une case de porteion de bateau touchée
                monMondeJ2[a][b]=4;
			}
			TableauActualise = monMondeJ2;
		}
		else if(joueur==2){
			if(monMondeJ1[a][b]==1){
				monMondeJ1[a][b]=3;
			}
			else if(monMondeJ1[a][b]==2){
				monMondeJ1[a][b]=4;
			}
		TableauActualise = monMondeJ1;
		}
        return TableauActualise;
	}
}
