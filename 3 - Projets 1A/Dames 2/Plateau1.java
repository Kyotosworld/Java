import java.util.Scanner;

public class Plateau1 {
	//Notre programme
	 public char [][] echiquier;
	 public char n = '+' ;		//pion noir
	 public char b = 'o' ;		//pion blanc
	 public char N = 'X' ;		//dame noire
	 public char B = 'O' ;		//dame blanche
	 public char c = '.' ;
	 
	 public int k = 10;		//coordonnée de la ligne de la case
 	 public int l = 10;		//coordonnée de la colonne de la case
 	 public int m = 10;		//coordonnée de la ligne de la case
 	 public int p = 10;		//coordonnée de la colonne de la case
 	 
 	 public int ka = 10;		//coordonnée de la ligne de la case
	 public int la = 10;		//coordonnée de la colonne de la case
	 public int ma  = 10;		//coordonnée de la ligne de la case
 	 public int pa = 10;		//coordonnée de la colonne de la case
 	 
 	 public int r = 0;		//coordonnée de la ligne de la case
	 public int t = 0;		//coordonnée de la colonne de la case
	 
 	 public boolean q = false; 	//variable qui indique si on peut ou non déplacer le pion
	 public boolean dep = false;	//variable qui indique si on peut deplacer la dame
	 
	 public int x = 0;
	 public int y = 0;
		
	 Scanner sc = new Scanner (System.in);
		
	 public int saisi = sc.nextInt();	
		 
	 public void initialisationPlateau (int ta){                 
	    	echiquier = new char [ta][ta];		//Initialisation de l'échiquier
		 
	        for(int i=0; i<ta; i++){
	            for(int j=0; j<ta; j++){
	                if((j%2==0 && i%2==0) || (j%2!=0 && i%2!=0)){
	                    echiquier [i][j] = c;
	                }
	            }
	        }
	         
	        for(int i=0; i<4; i=i+2){	    //Pion noir : '+'
	            for(int j=0; j<ta; j=j+2){
	                echiquier[i][j] = n;   
	            }
	        }
	        for(int i=1; i<4; i=i+2){
	            for(int j=1; j<ta; j=j+2){
	                echiquier[i][j] = n;   
	            }
	            
	        }

	        for(int i=6; i<ta; i=i+2){	        //Pion blanc : '-'
	            for(int j=0; j<ta; j=j+2){
	                echiquier[i][j] = b;  
	            }
	            
	        }
	        for(int i=7; i<ta; i=i+2){
	            for(int j=1; j<ta; j=j+2){
	                echiquier[i][j] = b;  
	            }
	            
	        }
	    
	        affichageEchiquier ();
	    }
	    
	 public void affichageEchiquier (){			
		 System.out.println();
		 System.out.println("   0 1 2 3 4 5 6 7 8 9");
		 for(int i = 0; i<10; i++){			//Affichage de l'echiquier
				//System.out.print(" - - - - - - - - - - ");
				//System.out.println();
				System.out.print(i + " |");
				for(int j=0; j<10; j++){
	            			System.out.print(echiquier[i][j]);
	                        System.out.print("|");
	        	}
				System.out.println();
		 }	//System.out.print(" - - - - - - - - - - ");
		 System.out.println();
	 }
	 
	 
	   public Plateau1 (String jeu){		
		   String joueur = "J1";
		   initialisationPlateau(10);
		   
		   while(gagner(echiquier) == 0){
			   
			   while(q == false){
				   System.out.print("Entrez le numéro de la ligne du pion à bouger.");
				   x = saisiCoordonneeX() ;
				   System.out.print("Entrez le numéro de la colonne du pion à bouger.");
				   y = saisiCoordonneeY() ;
				   
				   if(selectionnerDame(x, y, joueur) == false) { 
	
				   		deplacementPossiblePion(x, y, joueur);
				   		r = x;
				   		t = y;
				   		if(q){
				   		System.out.print("Entrez le numéro de la ligne de la nouvelle case du pion.");
						x = saisiCoordonneeX() ;
						System.out.print("Entrez le numéro de la colonne de la nouvelle case du pion.");
						y = saisiCoordonneeY() ;
				   		}  
						if(x==k && y==l){ 
							deplacer(r, t, x, y, ka, la, joueur);
						}else if(x==m && y==p){
							deplacer(r, t, x, y, ma, pa, joueur);
						}
						pionPasseDame (x, y, joueur);  
						
				   }else if(selectionnerDame(x, y, joueur)) {
					    r = x;
				   		t = y;
					   while(dep == false){
						   System.out.print("Entrez le numéro de la ligne de la nouvelle case du pion.");
						   x = saisiCoordonneeX() ;
						   System.out.print("Entrez le numéro de la colonne de la nouvelle case du pion.");
						   y = saisiCoordonneeY() ;
						   deplacementPossibleDame(x, y, joueur);
					   }
					   dep = false;
					    q = true;
				   }
			   }
			   affichageEchiquier ();
			   q = false;   
			   if(joueur == "J1"){
				   joueur = "J2";
			   }else if(joueur == "J2"){
				   joueur = "J1";
			   }
			   k=10;
			   l=10;
			   m=10;
			   p=10;
			   ka=10;
			   la=10;
			   ma=10;
			   pa=10;
			   if(gagner(echiquier) == 1){
				   System.out.println("Le joueur 1 a gagne.");
			   }else if(gagner(echiquier) == 2){
				   System.out.println("Le joueur 2 a gagne.");
			   }else {
				   System.out.println(joueur);
			   }  
		   }
	   }
	   
	   public int saisiCoordonneeX (){
		   int i = 0;
		   saisi = sc.nextInt();
		   i = saisi;
		   return i;
	   }
	   
	   public int saisiCoordonneeY(){
		   int j = 0;
		   saisi = sc.nextInt();
		   j = saisi;
		   return j;
	   } 
	   
	    public boolean selectionnerPion(int i, int j, String joueur){
	    	//verifier que le pion est bien sur la case
	    	if(i>=0 && i<=9 && j>=0 && j<=9){
		    	if(echiquier[i][j]==n &&joueur == "J1"){
		    		return true;
		    	}else if(echiquier[i][j]==b &&joueur == "J2"){
		    		return true;
		    	}
		    	else {
		    		System.out.println("Vous n'avez pas choisi de pion, recommencez.");
		    		return false;
		    	}
		    }else {
		    	System.out.println("Vous n'avez pas choisi de pion, recommencez.");
	    		//Le joueur n'a pas selectionner de pion, il recommence.
		    	return false;
		    }
	    }
	    
	    public boolean selectionnerDame(int i, int j, String joueur){
	    	//verifier que la dame est bien sur la case
	    	if(i>=0 && i<=9 && j>=0 && j<=9){
		    	if(echiquier[i][j]==N &&joueur == "J1"){
		    		return true;
		    	}else if(echiquier[i][j]==B &&joueur == "J2"){
		    		return true;
		    	}
		    	else {
		    		return false;
		    	}
		    }else {
		    	return false;
		    }
	    }
	    
	    public void deplacementPossiblePion(int i, int j, String joueur){		//Pour un pion
	    	
	    	if(selectionnerPion(x, y, joueur)){
	    		//Pour les pions noirs
		    	if(joueur == "J1"){
		    		if(i<9 && j<10){
		    			if(i<8 && j<8){		//Manger un pion vers le bas à droite ? Si non, possibilité de se déplacer vers la droite ?
				    		if(echiquier[i+1][j+1]==b && echiquier[i+2][j+2]==c){
				    			k=i+2;
				    			l=j+2;
				    			ka=i+1;
				    			la=j+1;
				    		}else if(echiquier[i+1][j+1]==c){
				    			k=i+1;
				    			l=j+1;
				    		}	
				    	}
		    			if(i<8 && j==8 || i==8 && j<9){
		    				if(echiquier[i+1][j+1]==c){
				    			k=i+1;
				    			l=j+1;
				    		}	
		    			}
		    			
				    	if(i<8 && j>1){		//Manger un pion vers le bas à gauche ? Si non, possibilité de se déplacer vers la gauche ?
				    		if(echiquier[i+1][j-1]==b && echiquier[i+2][j-2]==c){
				    			m=i+2;
				    			p=j-2;
				    			ma=i+1;
				    			pa=j-1;
				    		}else if(echiquier[i+1][j-1]==c){
				    			m=i+1;
				    			p=j-1;
				    		}	
				    	}
				    	if(i<8 && j==1 || i==8 && j>0){
				    		if(echiquier[i+1][j+1]==c){
				    			m=i+1;
				    			p=j-1;
				    		}	
				    	}
		    		}
		    	}	
		    	
		    	//Pour les pions blancs
		    	if(joueur == "J2"){
		    		if(i>0 && j<10){
		    			if(i>1 && j<8){		//Manger un pion vers le haut à droite ? Si non, possibilité de se déplacer vers la droite ?
				    		if(echiquier[i-1][j+1]==n && echiquier[i-2][j+2]==c){
				    			k=i-2;
				    			l=j+2;
				    			ka=i-1;
				    			la=j+1;
				    		}else if(echiquier[i-1][j+1]==c){
				    			k=i-1;
				    			l=j+1;
				    		}	
				    	}
		    			if(i>1 && j==8 || i==1 && j<9){
		    				if(echiquier[i+1][j+1]==c){
				    			k=i-1;
				    			l=j+1;
				    		}	
		    			}
		    			
				    	if(i>1 && j>1){		//Manger un pion vers le haut à gauche ? Si non, possibilité de se déplacer vers la gauche ?
				    		if(echiquier[i-1][j-1]==n && echiquier[i-2][j-2]==c){
				    			m=i-2;
				    			p=j-2;
				    			ma=i-1;
				    			pa=j-1;
				    		}else if(echiquier[i-1][j-1]==c){
				    			m=i-1;
				    			p=j-1;
				    		}	
				    	}
				    	if(i>1 && j==1 || i==1 && j>0){
				    		if(echiquier[i+1][j+1]==c){
				    			m=i-1;
				    			p=j-1;
				    		}	
				    	}
		    		}
		    	}
		    
		    	if(k!=10 && l!=10 && m!=10 && p!=10){
		    		System.out.println("Vous pouvez vous déplacer sur la case " + k + "," + l + " ou sur la case " + m + "," + p);
		    		q = true;
		    	}else if(k!=10 && l!=10){
			    	System.out.println("Vous pouvez vous déplacer sur la case " + k + "," + l);
			    	q = true;
		    	}else if(m!=10 && p!=10){
			    	System.out.println("Vous pouvez vous déplacer sur la case " + m + "," + p);
			    	q = true;
		    	}else if(k==10 && l==10 && m==10 && p==10){
			    	System.out.println("Vous ne pouvez vous pas déplacer le pion. Choisissez en un autre." );
			    	q = false;
		    	}
	    	}	
	    }
	    
	    public void deplacementPossibleDame(int i, int j, String joueur){		//Pour une dame
	    	//Tester toutes les possibilités
	    	if(echiquier[i][j] == c){
	    		int xi = 0;
	    		int xj = 0;
	    		if(joueur == "J1"){		//Possibilités pour la dame noire
	    			
	    			for(xi=0; xi<10; xi++){		
    					for(xj = 0; xj<10; xj++){
    						if(xi<i && xj<j && xi>=r && xj>=t){		//Deplacement vers le bas a droite
    							if(echiquier[xi][xj]==n || echiquier[xi][xj]==N){
    								dep = false;
    							}
    						}else {
    							if(xi<i-1 && xj<j-1 && xi>r && xj>t){
    								if(echiquier[i][j]==b || echiquier[i][j]==B){
    									dep = false;
    								}
    							}else if(echiquier[i-1][j-1]==b || echiquier[i-1][j-1]==B){
    								dep = true;
    								ka = i-1;
    								la = j-1;
    							}else{
    								dep = true;
    							}
    						}
    					}	
    				}
	    			
	    			for(xi=0; xi<10; xi++){		
    					for(xj = 9; xj>=0; xj--){
    						if(xi<i && xj>j && xi>r && xj<t){		//Deplacement vers le bas a gauche
    							if(echiquier[xi][xj]==n || echiquier[xi][xj]==N){
    								dep = false;				
    							}
    						}else {
    							if(xi<i-1 && xj<j+1 && xi>r && xj<t){
    								if(echiquier[i][j]==b || echiquier[i][j]==B){
    									dep = false;
    								}
    							}else if(echiquier[i-1][j+1]==b || echiquier[i-1][j+1]==B){
    								dep = true;
    								ka = i-1;
    								la = j+1;
    							}
    							else{
    								dep = true;
    							}
    						}
    					}		
	    			}
	    				
	    		for(xi=10; xi>0; xi--){		
					for(xj = 10; xj>=0; xj--){
						if(xi>i && xj<j && xi<=r && xj>=t){		//Deplacement vers le haut a droite
							if(echiquier[xi][xj]==n || echiquier[xi][xj]==N){
								dep = false;
							}
						}else {
							if(xi>i+1 && xj<j-1 && xi>r && xj<t){
								if(echiquier[i][j]==b || echiquier[i][j]==B){
									dep = false;
								}
							}else if(echiquier[i-1][j-1]==b || echiquier[i-1][j-1]==B){
								dep = true;
								ka = i-1;
								la = j-1;
							}else{
								dep = true;
							}
						}
					}	
				}
	    		
	    		for(xi=10; xi>0; xi--){		
					for(xj = 9; xj>=0; xj--){
						if(xi>i && xj>j && xi<r && xj<t){		//Deplacement vers le haut a gauche
							if(echiquier[xi][xj]==n || echiquier[xi][xj]==N){
								dep = false;				
							}
						}else {
							if(xi<i-1 && xj<j+1 && xi<r && xj<t){
								if(echiquier[i][j]==b || echiquier[i][j]==B){
									dep = false;
								}
							}else if(echiquier[i-1][j-1]==b || echiquier[i-1][j-1]==B){
								dep = true;
								ka = i-1;
								la = j-1;
							}
							else{
								dep = true;
							}
						}
					}		
    			}
	    
	    		deplacer(r, t, x, y, ka, la, joueur);	
	    		}
	    	
	    		if(joueur == "J2"){		//Possibilités pour la dame blanche
	    			for(xi=0; xi<10; xi++){		
    					for(xj = 0; xj<10; xj++){
    						if(xi<i && xj<j && xi>=r && xj>=t){		//Deplacement vers le bas a droite
    							if(echiquier[xi][xj]==b || echiquier[xi][xj]==B){
    								dep = false;
    							}
    						}else {
    							if(xi<i-1 && xj<j-1 && xi>r && xj>t){
    								if(echiquier[i][j]==n || echiquier[i][j]==N){
    									dep = false;
    								}
    							}else if(echiquier[i-1][j-1]==n || echiquier[i-1][j-1]==N){
    								dep = true;
    								ka = i-1;
    								la = j-1;
    							}else{
    								dep = true;
    							}
    						}
    					}	
    				}
	    			
	    			for(xi=0; xi<10; xi++){		
    					for(xj = 9; xj>=0; xj--){
    						if(xi<i && xj>j && xi>r && xj<t){		//Deplacement vers le bas a gauche
    							if(echiquier[xi][xj]==b || echiquier[xi][xj]==B){
    								dep = false;				
    							}
    						}else {
    							if(xi<i-1 && xj<j+1 && xi>r && xj<t){
    								if(echiquier[i][j]==n || echiquier[i][j]==N){
    									dep = false;
    								}
    							}else if(echiquier[i-1][j+1]==n || echiquier[i-1][j+1]==N){
    								dep = true;
    								ka = i-1;
    								la = j+1;
    							}
    							else{
    								dep = true;
    							}
    						}
    					}		
	    			}
	    				
	    		for(xi=10; xi>0; xi--){		
					for(xj = 10; xj>=0; xj--){
						if(xi>i && xj<j && xi<=r && xj>=t){		//Deplacement vers le haut a droite
							if(echiquier[xi][xj]==b || echiquier[xi][xj]==B){
								dep = false;
							}
						}else {
							if(xi>i+1 && xj<j-1 && xi>r && xj<t){
								if(echiquier[i][j]==n || echiquier[i][j]==N){
									dep = false;
								}
							}else if(echiquier[i-1][j-1]==n || echiquier[i-1][j-1]==N){
								dep = true;
								ka = i-1;
								la = j-1;
							}else{
								dep = true;
							}
						}
					}	
				}
	    		
	    		for(xi=10; xi>0; xi--){		
					for(xj = 9; xj>=0; xj--){
						if(xi>i && xj>j && xi<r && xj<t){		//Deplacement vers le haut a gauche
							if(echiquier[xi][xj]==b || echiquier[xi][xj]==B){
								dep = false;				
							}
						}else {
							if(xi<i-1 && xj<j+1 && xi<r && xj<t){
								if(echiquier[i][j]==n || echiquier[i][j]==N){
									dep = false;
								}
							}else if(echiquier[i-1][j-1]==n || echiquier[i-1][j-1]==N){
								dep = true;
								ka = i-1;
								la = j-1;
							}
							else{
								dep = true;
							}
						}
					}		
    			}
	    		deplacer(r, t, x, y, ka, la, joueur);
	    		}
	    	}else {
	    		dep = false;
	    	}
	    }
	    
	  
	    
	    public void pionPasseDame (int i, int j, String joueur){
	    	if(i==0 && j<10 && joueur == "J2"){
	    		if(echiquier[i][j] == b){		//Le pion blanc devient une dame
	    			echiquier[i][j] = B;
	    		}
	    	}
	    	if(i==9 && j<10 && joueur == "J1"){
	    		if(echiquier[i][j] == n){		//Le pion noir devient une dame
	    			echiquier[i][j] = N;
	    		}
	    	}
	    }
	    
	    public void deplacer(int i, int j, int k, int l, int ka, int la, String joueur){	//i et j coordonnees pion, k et l coordonnee nouvelle case du pion
	    	if(joueur == "J1" && echiquier[i][j] == n){				//Joueur 1
	    		echiquier[i][j]= c; 			//La case où se trouvait le pion devient vide
	    		echiquier[k][l]= n;				//Le pion noir est mis sur sa nouvelle case
	    	}else if(joueur == "J2" && echiquier[i][j] == b){		//Joueur 2
	    		echiquier[i][j]= c;				//La case où se trouvait le pion devient vide
	    		echiquier[k][l]= b;				//Le pion blanc est mis sur sa nouvelle case
	    	}else if(joueur == "J1" && echiquier[i][j] == N){		//Joueur 1
	    		echiquier[i][j]= c; 			//La case où se trouvait la dame devient vide
	    		echiquier[k][l]= N;				//La dame noire est mise sur sa nouvelle case
	    	}else if(joueur == "J2" && echiquier[i][j] == B){		//Joueur 2
	    		echiquier[i][j]= c;				//La case où se trouvait le dame devient vide
	    		echiquier[k][l]= B;				//La dame blanche est mise sur sa nouvelle case
	    	}
	    	if(ka!=10 && la!= 10){			//Si le pion ou la dame a mangé un pion adverse
	    		echiquier[ka][la]= c;			//La case du pion mangé est alors vide
	    	}
	    }
	    
	   public int gagner(char [][] jeu){
		   int compteurB = 0;		//variable : nombre de pions (et dames) blancs restant
		   int compteurN = 0;		//variable : nombre de pions (et dames) noirs restant
		   int gagne = 0;			//gagne =0 : le jeu continue
		   
		   for(int i=0; i<jeu.length; i++){
			   for(int j=0; j<jeu.length; j++){
				   if(jeu[i][j]==b){
					   compteurB = compteurB + 1;
				   }else if(jeu[i][j]==n){
					   compteurN = compteurN + 1;
				   }
				   if(jeu[i][j]==B){
					   compteurB = compteurB + 1;
				   }else if(jeu[i][j]==N){
					   compteurN = compteurN + 1;
				   }
			   }
		   }
		   
		   if(compteurB==0){
			   gagne = 1;		//Le joueur1 avec les pions noirs a gagné.
		   }else if(compteurN==0){
			   gagne = 2;		//Le joueur2 avec les pions blancs a gagné.
		   }
		   return gagne;
	   }

}
	    //Fin programme
	   
