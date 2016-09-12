import java.util.Scanner;

/**
 * La classe Jeu permet de faire tourner le jeu de combat, de l'afficher, et permet aux utilisateurs d'interagir sur le terminal pour jouer.
 * @author Isis Lorenzo , Theo Durand, Loan Grimm
 */
 
public class Jeu {
	
	public static void main (String args[]) {
		
		
			//Page d'accueil
        System.out.println("                ****    Bienvenue dans l'arene Star Wars !!    ****\n\n Vous devrez battre votre adversaire a l'aide de votre sabre et de votre force.");
        System.out.println("               Prets ? Alors choisissez votre Jedi :");	
        System.out.println("\n1 : Maitre Yoda	 |   2 : Rey       |  3 : Dark Vador      | 4 : Luke Skywalker");
        System.out.println("\nPV : 100         | PV : 110        | PV : 80              | PV : 120    ");
        System.out.println("Force init. : 80%| Force init.: 20%| Force init. : 40%    | Force init. : 60% ");
        System.out.println("Deg Sabre : 10-20| Deg Sabre: 7-15 | Deg Sabre : 7-15     | Deg Sabre : 7-15");
        System.out.println("Deg Force : 40   | Deg Force : 35  | Deg Force : 60       | Deg Force : 40");
        System.out.println(" Jedi le plus    | Regagne 1/2 de  | Attaque Force tres   |Regagne 20% de force");
        System.out.println("puissant au sabre| sa force en PV  |puissante et meilleure|par tour (au lieu de");
        System.out.println("                 |(au lieu de 1/3) |chance de parer (1/2) |  15%)");
        System.out.println("\n                    Autre chiffre : Aleatoire");
         
			//Choix du Jedi
        System.out.println("\nJoueur 1, a toi de choisir [1-4]:");
        Scanner lecture1 = new Scanner(System.in);
        int numero1 = lecture1.nextInt();
        Jedi joueur1 = new Jedi (numero1);
        System.out.println("			Joueur 1, tu incarneras "+joueur1.getNom());
        
        System.out.println("\nJoueur 2, a toi de choisir [1-4]:");
        Scanner lecture2 = new Scanner(System.in);
        int numero2 = lecture2.nextInt();
        Jedi joueur2 = new Jedi (numero2);
        System.out.println("			Joueur 2, tu incarneras "+joueur2.getNom());
        
        if (joueur1.getNom() == joueur2.getNom()){
			joueur1.setNom(" 1 ");
			joueur2.setNom(" 2 ");
		}
        
			//Choix des touches d'actions
        System.out.println("\nChoisissez vos touches pour les actions suivantes : ");
        System.out.println("    1 : Attaque au sabre \n    2 : Attaque par la force \n    3 : Soin par la force ");
        char[] actions = new char[6];  //Tableau stockant les touches d'actions des 2 joueurs
        
        System.out.println(joueur1.getNom()+", a toi de choisir :\n (rentre tes 3 touches a la suite, puis selectionne 'Entree')"); 
        Scanner lecture3 = new Scanner(System.in);
		String toucheJ1 = lecture3.nextLine();    
        for (int i=0;i<3;i++){        		
			actions[i]=toucheJ1.charAt(i);
		}
        System.out.println("\n"+joueur2.getNom()+", a toi de choisir :\n (rentre tes 3 touches a la suite, puis selectionne 'Entree') :"); 
        Scanner lecture4 = new Scanner(System.in);
		String toucheJ2 = lecture4.nextLine();    
        for (int i=0;i<3;i++){        		
			actions[i+3]=toucheJ2.charAt(i);
		}
		
		String touchesJoueur1 ="   "+actions[0]+" : Attaque au sabre \n   "+actions[1]+" : Attaque par la force (seulement si force complete)\n   "+actions[2]+" : Soins PV (1/3 de la force -ou 1/2 pour Rey-)";
        String touchesJoueur2 ="   "+actions[3]+" : Attaque au sabre \n   "+actions[4]+" : Attaque par la force (seulement si force complete)\n   "+actions[5]+" : Soins PV (1/3 de la force -ou 1/2 pour Rey-)";
		
			// Affichage selon les actions
        
         String sabreJ1 = 
         "                    /--\\              \\\\      /--\\ "+"\n"
        +"                    \\ o/               \\\\     \\- / "+"\n"
        +"                   /####\\               \\\\   /####\\ "+"\n"
        +"                  |####\\#\\               \\\\ /#/####| "+"\n"
        +"                  |####|\\#\\==========     \\\\#/|####| "+"\n"
        +"                 |## ##|                      |## ##| "+"\n"
        +"                 |#  ##|                      |##  #| "+"\n";
        
        String sabreJ2 = 
         "                 /--\\      //            /--\\ "+"\n"
        +"                 \\ -/     //             \\o / "+"\n"
        +"                /####\\   //             /####\\ "+"\n"
        +"               |####\\#\\ //             /#/####| "+"\n"
        +"               |####|\\#//   ==========/#/|####| "+"\n"
        +"              |## ##|                    |## ##| "+"\n"
        +"              |#  ##|                    |##  #| "+"\n";
        
        String forceJ1 =
         "                                          /xx\\"+"\n"
        +"                / @ @\\                    \\~ /"+"\n"
        +"                \\  - /                   /#####\\"+"\n"
        +"                /####\\___               /#|####|#\\"+"\n"
        +"               |#########{             /#/|####|\\#\\"+"\n"
        +"               |####|                    |## ##| "+"\n"
        +"              |## ##|                   |##   ##|"+"\n"
        +"              |#  ##|                    "+"\n";
        
          
        String forceJ2 =
         "                   /xx\\"+"\n"
        +"                   \\ ~/                 /à à \\"+"\n"
        +"                 /#####\\                \\ -  /"+"\n"
        +"               /#|####|#\\            ___/####\\ "+"\n"
        +"              /#/|####|\\#\\          }########| "+"\n"
        +"                 |## ##|                |####|"+"\n"
        +"                |##   ##|               |## ##|"+"\n"
        +"                                        |##  #|"+"\n";
        
         String soinJ1 =
         "               /^^\\                            "+"\n"
        +"               \\u /                   #### ####"+"\n"
        +"             /#####\\                  #########"+"\n"
        +"            /#|####|#\\                 ####### "+"\n"
        +"           /#/|####|\\#\\                 #####"+"\n"
        +"             |## ##|                     ###"+"\n"
        +"            |##   ##|                     #"+"\n";
         
         String soinJ2 =
         "                                          /^^\\"+"\n"
        +"                  #### ####               \\u /"+"\n"
        +"                  #########             /#####\\"+"\n"
        +"                   #######             /#|####|#\\"+"\n"
        +"                    #####             /#/|####|\\#\\"+"\n"
        +"                     ###                 |## ##| "+"\n"
        +"                      #                 |##   ##|"+"\n";
        
        String gagneJ1 =
		"              *  *  *"+"\n"
        +"          *     /^^\\    *     "+"\n"
        +"        *       \\u /      *       =======\\\\           "+"\n"
        +"       *      /#####\\      *              \\#\\    __    "+"\n"
        +"       *     /#|####|#\\    *      ###\\__ __\\#\\__/  \\   "+"\n"
        +"       *    /#/|####|\\#\\   *          ##|#####   | X  "+"\n"
        +"        *     |## ##|     *           ##|#####__ | X   "+"\n"
        +"          *  |##   ##|  *         ###/     /#/  \\__/   "+"\n"
        +"              *  *  *                     /#/";
        
        String gagneJ2 =
		"                                              *  *  *"+"\n"
        +"                                          *     /^^\\    *"+"\n"
        +"                   //=======            *       \\u /      *"+"\n"
        +"           __    /#/                   *      /#####\\      *"+"\n"
        +"          /  \\__/#/___ _/###           *     /#|####|#\\    *"+"\n"
        +"           X |   #####|##              *    /#/|####|\\#\\   *"+"\n"
        +"           X |__ #####|##               *     |## ##|     *"+"\n"
        +"          \\__/  \\#\\     \\###              *  |##   ##|  *"+"\n"
        +"                 \\#\\                          *  *  *  ";  
        
       
			//Tours de jeu
			

        int n = 0;   //Compteur de tours (pair -> J1 ; impair -> J2)    
        while( joueur1.getPtsDeVie()>0 && joueur2.getPtsDeVie()>0 ){
			
			//Affichage des statistiques et des personnages en début de tour
			 
			 System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+joueur1.getNom() + "			VS			" + joueur2.getNom()+"\n"
			+joueur1.getPtsDeVie() +" PV	  			     			" + joueur2.getPtsDeVie() +" PV"+"\n"
			+"Force : "+joueur1.getForce() +"%	  		     			Force : " + joueur2.getForce() +"%"+"\n"
			+"                 /--\\      //       \\\\      /--\\ "+"\n"
			+"                 \\ -/     //         \\\\     \\- / "+"\n"
			+"                /####\\   //           \\\\   /####\\ "+"\n"
			+"               |####\\#\\ //             \\\\ /#/####| "+"\n"
			+"               |####|\\#//               \\\\#/|####| "+"\n"
			+"              |## ##|                       |## ##| "+"\n"
			+"              |#  ##|                       |##  #| "+"\n");
        
        
			 if (n%2==0){
				 System.out.println("                         "+joueur1.getNom()+",\n          a toi de jouer !! Quelle action choisis-tu ?\n");				 
				 			//Actions J1
				 			
				 System.out.println(touchesJoueur1);
				 Scanner pif = new Scanner(System.in);
			     char coup = pif.nextLine().charAt(0);
									//Attaque au sabre
			     if (coup==actions[0]){ 			
					 int deg = joueur1.coupSabre();
					 System.out.println(sabreJ1);
					 System.out.println("\n		"+joueur1.getNom()+" attaque au sabre !  Ce coup fait "+ deg+" degats.");
					 int chance =3;
					 if (joueur2.getNom()== "Dark Vador"){
						 chance = 2;
					 }
					 System.out.println("\n"+joueur2.getNom()+", tu as 1 chance sur "+chance+" de parer. Tentes-tu de parer ce coup ?\n            ( /!\\ Si tu echoues, tu passes ton tour !! )");
					 System.out.println("  "+actions[3]+" : Oui\n  "+actions[4]+" : Non");
					 System.out.println();
					 Scanner lect = new Scanner(System.in);
					 char pare = lect.nextLine().charAt(0);
					 if (pare == actions[3]){
						 System.out.println(joueur2.getNom()+" tente de parer !");
						 if (joueur2.parerCoup()){
							 System.out.println("\n		"+joueur1.getNom()+", ton attaque a ete paree !\n"+joueur2.getNom()+" n'encaisse aucun degat.");
							 System.out.println();
						 } else {
							 System.out.println("\n		Parade echouee : "+joueur1.getNom()+", ton attaque touche !\n"+joueur2.getNom()+" encaisse "+deg +" degats... Et passe son tour !");
							 System.out.println();
							 joueur2.setPtsDeVie(deg);
							 n++;
						 }
					 } else if (pare == actions[4]){
						 System.out.println("\n		"+joueur1.getNom()+", ton attaque touche !\n"+joueur2.getNom()+" encaisse "+deg +" degats. \n");
						 joueur2.setPtsDeVie(deg);
					 } else {
						 System.out.println(joueur2.getNom()+", tu n'as pas fait une touche valide...\n    Tant pis, tu passes ton tour ! \n");
						 joueur2.setPtsDeVie(deg);
						 n++;
					 }
									//Attaque par la force
				 } else if (coup==actions[1]){		
					 if (joueur1.getForce()==100){						 
						 int deg = joueur1.utiliseForce();
						 System.out.println(forceJ1);
						 System.out.println("\n		"+joueur1.getNom()+" utilise la force ! \n Sa puissance s'eleve a "+ deg +" Midi-chloriens.");
						 System.out.println(joueur2.getNom()+" encaisse "+deg +" degats.");
						 System.out.println();
						 joueur2.setPtsDeVie(deg);
					 }else{
						 System.out.println(" Tu etais pourtant prevenu... \n Ta force est incomplete jeune Padawan, tu passes ton tour !! ");						 
					 }
									//Soin par la force
				 } else if (coup==actions[2]){     
					 System.out.println(soinJ1);
					 System.out.println(joueur1.regagnerVie());
									//Touche invalide
				 } else {
						 System.out.println(joueur1.getNom()+", tu n'as pas fait une touche valide...\n    Tant pis, tu passes ton tour ! \n");
				 }
				 
			
			 }else{
				 System.out.println("                         "+joueur2.getNom()+",\n          a toi de jouer !! Quelle action choisis-tu ?\n");	
								//Actions J2
								
				 System.out.println(touchesJoueur2);
				 Scanner pif = new Scanner(System.in);
			     char coup = pif.nextLine().charAt(0);
									//Attaque au sabre
			     if (coup==actions[3]){			
					 int deg = joueur2.coupSabre();
					 System.out.println(sabreJ2);
					 System.out.println("\n		"+joueur2.getNom()+" attaque au sabre !  Ce coup fait "+ deg+" degats.");
					 int chance = 3;
					 if (joueur1.getNom()== "Dark Vador"){
						 chance = 2;
					 }
						 System.out.println("\n"+joueur1.getNom()+", tu as 1 chance sur "+chance+" de parer. Tentes-tu de parer ce coup ?\n            ( /!\\ Si tu echoues, tu passes ton tour !! )");
					 System.out.println("  "+actions[0]+" : Oui\n  "+actions[1]+" : Non");
					 Scanner lect = new Scanner(System.in);
					 char pare = lect.nextLine().charAt(0);
					 if (pare == actions[0]){
						 System.out.println(joueur1.getNom()+" tente de parer !");
						 if (joueur1.parerCoup()){
							 System.out.println("\n		"+joueur2.getNom()+", ton attaque a ete paree !\n"+joueur1.getNom()+" n'encaisse aucun degat.");
							 System.out.println();
						 } else {
							 System.out.println("\n		Parade echouee : "+joueur2.getNom()+", ton attaque touche !\n"+joueur1.getNom()+" encaisse "+deg +" degats... Et passe son tour !");
							 System.out.println();
							 joueur1.setPtsDeVie(deg);
							 n++;
						 }
					 } else if (pare == actions[1]){
						 System.out.println("\n		"+joueur2.getNom()+", ton attaque touche !\n"+joueur1.getNom()+" encaisse "+deg +" degats. \n");
						 joueur1.setPtsDeVie(deg);
					 } else {
						 System.out.println(joueur1.getNom()+", tu n'as pas fait une touche valide...\n    Tant pis, tu passes ton tour ! \n");
						 joueur1.setPtsDeVie(deg);
						 n++;
					 }
									//Attaque par la force
				 } else if (coup==actions[4]){		
					 if (joueur2.getForce()==100){
						 int deg = joueur2.utiliseForce();
						 System.out.println(forceJ2);
						 System.out.println("\n		"+joueur2.getNom()+" utilise la force ! \n Sa puissance s'eleve a "+ deg +" Midi-chloriens.");
						 System.out.println(joueur1.getNom()+" encaisse "+deg +" degats.");
						 joueur1.setPtsDeVie(deg);
					 }else{
						 System.out.println("\n Tu etais pourtant prevenu... \n Ta force est incomplete jeune Padawan, tu passes ton tour !! ");
						 
					 }
									//Soin par la force
				 } else if (coup==actions[5]){       
					System.out.println(soinJ2);
					System.out.println(joueur2.regagnerVie());
									//Touche invalide
				 } else {
						 System.out.println(joueur2.getNom()+", tu n'as pas fait une touche valide...\n    Tant pis, tu passes ton tour ! \n");
				 }
				
			}
			
			joueur1.regagnerForce();
			joueur2.regagnerForce();
			n++;
			System.out.println("Tapez 'Entree' pour finir le tour");
				//Ces deux lignes permettent d'attendre que l'utilisateur fasse "Entree" avant de finir le tour
			Scanner finTour = new Scanner(System.in);
			String fin = finTour.nextLine();
			}
			
		
			//Affichage de fin
		 
		if (joueur1.getPtsDeVie()<=0){
			System.out.println("\n\n\n\n\n\n\n\n\n\n    Bravo "+joueur2.getNom()+", tu remportes avec bravoure ce combat de Jedi... \n\n				 FELICITATIONS !!! \n\n\n");
			System.out.println(gagneJ2);
			
		}
		if (joueur2.getPtsDeVie()<=0){
			System.out.println("\n\n\n\n\n\n\n\n\n\n    Bravo "+joueur1.getNom()+", tu remportes avec bravoure ce combat de Jedi... \n\n				 FELICITATIONS !!! \n\n\n");
			System.out.println(gagneJ1);  
		}
		
	}


}
