import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tableau {

    public int[][] monde = new int[10][10];           // Plateau de jeu : 0 = vide, 1 = pion blanc, 2 = pion noir, 3 = dame blanche, 4 = dame noire
    public int taille = 10 ;
    public Pion[]pions = new Pion[40];
    public Curseur curseur = new Curseur();
    public boolean finDuJeu = false;
    public int tourDeJeu = 0;
    public boolean rafle = false;
    public int[] captureObl = new int[10];
    
    /**
	* Le constructeur Tableau permet la generation du plateau de jeu, muni des pions.
	*/
	
    public Tableau() {               
    
        generationMonde();
        generationPions();
        placerPions();
        
    }
    
	/**
	 * La methode generationMonde permet la generation du plateau, vide.
	 */
	 
    public void generationMonde() {      
        
        monde = new int[taille][taille];
        
        for (int x=0;x<taille;x++) {
            
            for (int y=0;y<taille;y++) {
                
                monde[x][y]=0;
                
            }
        }
    }
    
    /**
     * La methode generationPions permet de generer l'ensemble des pions.
     */
     
    public void generationPions() {           
    
        for (int i = 0; i < 40; i++) {
			if (i < 20) {
				pions[i] = new Pion(2);
			} else {
				pions[i] = new Pion(1);
			}
        }
    }
    
    /**
     * La methode placerPions permet de placer les differents pions sur le plateau de jeu.
     */
     
    public void placerPions() {       
    
        placerPionsBlancs();
        placerPionsNoirs();
        
    }
    
    /**
     * La methode placerPionsBlancs permet de placer les pions blancs sur les quatre lignes superieures du tableau, en quinconce.
     */
     
    public void placerPionsBlancs() {  
    
        int i = 0;
        
        for (int x = 1; x < taille; x += 2) {	// Place la 2e et 4e ligne
            
            for (int y = 0; y <= 2; y += 2) {
                
                monde[x][y]= 1;
                pions[20+i].x = x;
                pions[20+i].y = y;
                pions[20+i].couleur = 1;
                i++;
                
            }
        }
        
        for (int x = 0; x < taille; x += 2) {	// Place la 1e et 3e ligne
            
            for (int y = 1; y <= 3; y += 2) {
                
                monde[x][y]= 1;
                pions[20+i].x = x;
                pions[20+i].y = y;
                pions[20+i].couleur = 1;
                i++;
                
            }
        }
    }
    
     /**
     * La methode placerPionsNoirs permet de placer les pions noirs sur les quatre lignes inferieures du tableau, en quinconce.
     */
     
    public void placerPionsNoirs() {        
    
        int i = 0;
        
        for (int x = 0; x < taille; x += 2) {	// Place la 1e et 3e ligne
            
            for (int y = 9; y >= 7; y = y-2) {
        
                monde[x][y]= 2;			
                pions[i].x = x;
                pions[i].y = y;
                pions[i].couleur = 2;   
                i++;
                
            }
        }
        
        for (int x = 1; x < taille; x += 2) {	// Place la 2e et 4e ligne
            
            for (int y = 8; y >= 6; y = y-2) {
        
                monde[x][y]= 2;
                pions[i].x = x;
                pions[i].y = y;
                pions[i].couleur = 2;
                i++;
            
            }
        }
    }

	/**
	 * La methode finDePartie permet de terminer la partie lorsque tous les pions d'une couleur ont ete captures.
	 * @return false si le jeu est terminé, true sinon.
	 */
    public boolean finDePartie () {
        boolean jeu = true;
        int res = 0;
        int temp = 0;
        
        for(int i = 0; i < pions.length; i++){
            if (pions[i].couleur == 2 && pions[i].viv == false) {
                res++;
            }
            if (pions[i].couleur == 1 && pions[i].viv == false) {
                temp++;
            }
         }   
        if (res ==  20 || temp == 20){
            jeu = false;
        }
        return jeu;
    }
    
    /**
     * La methode deplaceCurseur permet de deplacer le curseur sur le plateau de jeu a l'aide des fleches directionnelles.
     *@param e 
     */
     
    public void deplaceCurseur (KeyEvent e) {
		if (e.getKeyCode() == e.VK_LEFT) { // Deplacement du curseur vers la gauche
			if (this.curseur.x == 0) {
				this.curseur.x = 9;
			} else {
				this.curseur.x -= 1;
			}
		} else if (e.getKeyCode() == e.VK_RIGHT) { // Deplacement du curseur vers la droite
			if (this.curseur.x == 9) {
				this.curseur.x = 0;
			} else {
				this.curseur.x += 1;
			}
		} else if (e.getKeyCode() == e.VK_DOWN) { // Deplacement du curseur vers le bas
			if (this.curseur.y == 9) {
				this.curseur.y = 0;
			} else {
				this.curseur.y += 1;
			}
		} else if (e.getKeyCode() == e.VK_UP) { // Deplacement du curseur vers le haut
			if (this.curseur.y == 0) {
				this.curseur.y = 9;
			} else {
				this.curseur.y -= 1;
			}
		} else if (e.getKeyCode() == e.VK_C) {
			if (this.trouvePion(this.curseur.x, this.curseur.y) != -1) {
				this.pions[this.trouvePion(this.curseur.x,this.curseur.y)].viv = false;
				monde[this.curseur.x][this.curseur.y] = 0;
			} 
		} else if (e.getKeyCode() == e.VK_ESCAPE && !this.finDePartie()) { 	// Permet l'arret du jeu en appuyant sur echap en fin de partie
			this.finDuJeu = true;
		} else if (e.getKeyCode() == e.VK_SPACE && !this.curseur.select) {  // Selection d'un pion avec le curseur
			if (this.tourDeJeu%2 == 0) {
				if (this.monde[this.curseur.x][this.curseur.y] == 1 || this.monde[this.curseur.x][this.curseur.y] == 3) {
					if (this.debutTourCaptureObligatoire()) {
						if (this.pions[this.trouvePion(this.curseur.x, this.curseur.y)].capturePossible) {	// Si les coordonnees du curseur correspondent a celles d'un pion, 
							this.curseur.select = true;																				// fixe les coordonnées du curseur pours les utiliser ensuite
							this.curseur.xSelect = this.curseur.x;
							this.curseur.ySelect = this.curseur.y;
							this.curseur.pionSelect = this.trouvePion(this.curseur.x, this.curseur.y);
							this.pions[this.curseur.pionSelect].deplacementPossible = this.depPossible(this.pions[this.curseur.pionSelect]);
						}
					} else {
						this.curseur.select = true;																				// fixe les coordonnées du curseur pours les utiliser ensuite
						this.curseur.xSelect = this.curseur.x;
						this.curseur.ySelect = this.curseur.y;
						this.curseur.pionSelect = this.trouvePion(this.curseur.x, this.curseur.y);
						this.pions[this.curseur.pionSelect].deplacementPossible = this.depPossible(this.pions[this.curseur.pionSelect]);
					}
				} 
			} else if (this.tourDeJeu%2 == 1) {
				if (this.monde[this.curseur.x][this.curseur.y] == 2 || this.monde[this.curseur.x][this.curseur.y] == 4) {			
					if (this.debutTourCaptureObligatoire()) {
						if (this.pions[this.trouvePion(this.curseur.x, this.curseur.y)].capturePossible) {	// Si les coordonnees du curseur correspondent a celles d'un pion, 
							this.curseur.select = true;																				// fixe les coordonnées du curseur pours les utiliser ensuite
							this.curseur.xSelect = this.curseur.x;
							this.curseur.ySelect = this.curseur.y;
							this.curseur.pionSelect = this.trouvePion(this.curseur.x, this.curseur.y);
							this.pions[this.curseur.pionSelect].deplacementPossible = this.depPossible(this.pions[this.curseur.pionSelect]);
						}
					} else {
						this.curseur.select = true;																				// fixe les coordonnées du curseur pours les utiliser ensuite
						this.curseur.xSelect = this.curseur.x;
						this.curseur.ySelect = this.curseur.y;
						this.curseur.pionSelect = this.trouvePion(this.curseur.x, this.curseur.y);
						this.pions[this.curseur.pionSelect].deplacementPossible = this.depPossible(this.pions[this.curseur.pionSelect]);
					}
				} 
			}
		}	/* Si le curseur a deja selectionné un pion */
		 else if (e.getKeyCode() == e.VK_SPACE && this.curseur.select && this.curseur.x == this.curseur.xSelect && this.curseur.y == this.curseur.ySelect && !this.rafle) { 
			this.curseur.select = false;									// Si on clic sur la meme case : deselectionne					
			this.curseur.xSelect = 0;
			this.curseur.ySelect = 0;
		} else if (e.getKeyCode() == e.VK_SPACE && this.curseur.select && (this.curseur.x != this.curseur.xSelect || this.curseur.y != this.curseur.ySelect)) {
			this.curseur.deplacementSelect = this.choixDeplacement(this.curseur.x, this.curseur.y, this.pions[this.curseur.pionSelect]);
			if (this.curseur.deplacementSelect == -1 && !this.rafle) {
				this.curseur.select = false;								// Si on clic sur une case vide : deselectionne
				this.curseur.xSelect = 0;
				this.curseur.ySelect = 0;
				this.curseur.pionSelect = -1;
			} else {
				if (this.pions[this.curseur.pionSelect].deplacementPossible[this.curseur.deplacementSelect][2] == 0) {
					this.pions[this.curseur.pionSelect].deplacement(this.pions[this.curseur.pionSelect].deplacementPossible[this.curseur.deplacementSelect][0], this.pions[this.curseur.pionSelect].deplacementPossible[this.curseur.deplacementSelect][1], this);
					this.curseur.select = false;
					this.curseur.xSelect = 0;								// Si on clic sur un mouvement possible du type deplacement simple:
					this.curseur.ySelect = 0;								// Appelle la fonction deplacement et remet le curseur a l'etat initial
					this.curseur.pionSelect = -1;
					this.curseur.deplacementSelect = -1;
					this.resetCaptureObligatoire();
					this.tourDeJeu++;
					this.captureObl = this.captureObligatoire();
					if (this.captureObl[0] != -1) {
						for (int i = 0; i < 10; i++) {
							if (this.captureObl[i] != -1) {
								this.pions[this.captureObl[i]].capturePossible = true;
							}
						}
					}
				} else if (this.pions[this.curseur.pionSelect].deplacementPossible[this.curseur.deplacementSelect][2] == 1) {
					this.pions[this.curseur.pionSelect].capture(this.pions[this.curseur.pionSelect].deplacementPossible[this.curseur.deplacementSelect][0], this.pions[this.curseur.pionSelect].deplacementPossible[this.curseur.deplacementSelect][1], this);
					this.pions[this.curseur.pionSelect].deplacementPossible = this.depPossible(this.pions[this.curseur.pionSelect]);
					if (this.pions[this.curseur.pionSelect].deplacementPossible[0][2] != 1) {
						this.curseur.select = false;				
						this.curseur.xSelect = 0;								// Si on clic sur un mouvement possible du type capture :
						this.curseur.ySelect = 0;								// Appelle la fonction capture et remet le curseur a l'etat initial
						this.curseur.pionSelect = -1;
						this.curseur.deplacementSelect = -1;
						this.rafle = false;
						this.resetCaptureObligatoire();
						this.tourDeJeu++;
						this.captureObl = this.captureObligatoire();
						if (this.captureObl[0] != -1) {
							for (int i = 0; i < 10; i++) {
								if (this.captureObl[i] != -1) {
									this.pions[this.captureObl[i]].capturePossible = true;
								}
							}
						}
					} else if (this.pions[this.curseur.pionSelect].deplacementPossible[0][2] == 1) {
						this.curseur.deplacementSelect = -1;
						this.rafle = true;
					}
				} 
			}
		}
	}
	
	/** 
	 * La methode cherche si, en debut de tour, un pion presenteune capture obligatoire
	 * @return int[] l'ensemble des rangs dans le tableau tableau.pions[] des pions presentant une capture obligatoire
	 */
	 
	public int[] captureObligatoire () {
		int[] numPionCapturePossible = new int[10];
		int k = 0;
		for (int i = 0; i < 40; i++) {
			if (this.pions[i].viv) {
				this.pions[i].deplacementPossible = this.depPossible(this.pions[i]);
				if (this.pions[i].deplacementPossible[0][2] == 1) {
					if (this.tourDeJeu%2 == 0 && this.pions[i].couleur == 1) {
						numPionCapturePossible[k] = i;
						k++;
					} else if (this.tourDeJeu%2 == 1 && this.pions[i].couleur == 2) {
						numPionCapturePossible[k] = i;
						k++;
					}
				}
			}
		}
		if (k < 10) {
			for (int p = k; k < 10; k++) {
				numPionCapturePossible[k] = -1;
			}
		}
		return numPionCapturePossible;
	}
	
	/**
	 * La methode verifie si au moins un pion presente une capture obligatoire en debut de tour
	 * @return boolean si oui ou non un pion presente une capture obligatoire
	 */
	 
	public boolean debutTourCaptureObligatoire () {
		int k = 0;
		for (int i = 0; i < 40; i++) {
			if (this.pions[i].capturePossible) {
				k++;
			}
		}
		if (k > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * La methode remet à false toutes les booleans capturePossible de tous les pions
	 */
	 
	public void resetCaptureObligatoire () {
		for (int i = 0; i < 40; i++) {
			this.pions[i].capturePossible = false;
		}
	}
	
	/** 
	 * La methode choixDeplacement permet de récupérer le numero du deplacement du pion dans le tableau des deplacements possibles du pion grace a ses coordonnees.
	 * @param x et y coordonnees du pion dans le tableau.
	 * @return numero le numero du deplacement.
	 */
	 
	public int choixDeplacement (int x, int y, Pion pion) {
		int numDeplacement = -1;
		for (int i = 0; i < pion.deplacementPossible.length; i++) {
			if (pion.deplacementPossible[i][0] == x && pion.deplacementPossible[i][1] == y) {
				numDeplacement = i;
			}
		}
		return numDeplacement;
	}
	
	/**
	 * La methode trouvePion permet de récupérer le numero du pion dans le tableau de pion du tableau de jeu grace a ses coordonnees.
	 *@param x et y coordonnees du pion dans le tableau.
	 *@return numero le numero du pion.
	 */
	 
	public int trouvePion (int x, int y) {
		int numero = -1;
		for (int i = 0; i < 40; i++) {
			if (this.pions[i].x == x && this.pions[i].y == y) {
				numero = i;
			}
		} 
		return numero;
	}
	
	/** 
	 * La methode recupere le nombre de pion de chaque couleur mort durant une partie
	 * @return int[] nombre de pion blanc mort, puis de pions noirs morts
	 */
	 
	public int[] trouvePionMort() {
		int noirMort = 0;
		int blancMort = 0;
		for (int i = 0; i < 40; i++) {
			if (!this.pions[i].viv) {
				if (this.pions[i].couleur == 1) {
					blancMort += 1;
				} else if (this.pions[i].couleur == 2) {
					noirMort += 1;
				}
			}
		}
		int[] resultat = {blancMort,noirMort};
		return resultat;
	}
	
	/**
	 * La methode depPossible permet de repertorier tous les deplacements realisables par le pion selectionne.
	 *@param pion le pion selectionne
	 *@return deppossible2 un tableau contenant trois colonnes : l'abscisse, l'ordonnée et s'il s'agit d'une capture ou non.
	 */ 
	
	public int[][] depPossible(Pion pion){
		if(!pion.dame && pion.viv){				// si le pion est vivant et n'est pas une dame.
			boolean mangeable = false;
			int[][] deppossible = new int[4][3];
			int k = 0;
			for(int i = -1 ; i<2 ; i+=2){
				for(int j = -1 ; j<2; j+=2){				// on parcourt les cases susceptibles d'être pleines dans le carre autour du pion.
					if(i+pion.x >= 1 && i+pion.x < taille-1 && j+pion.y >= 1 && j+pion.y < taille-1){				// on verifie si le pion peut capturer un pion adverse sans sortir du plateau
						if(monde[i+pion.x][j+pion.y] != 0 && monde[i+pion.x][j+pion.y]%2 != pion.couleur%2 && monde[2*i+pion.x][2*j+pion.y] == 0){				//on verifie si un pion adverse se situant autour du pion considere est capturable.
							mangeable = true;				
							deppossible[k][0] = 2*i+pion.x;				// les coordonnees de la case vide qui se trouve apres le pion adverse sont repertoriees.
							deppossible[k][1] = 2*j+pion.y;
							deppossible[k][2] = 1;						// le un signifie que le pion realise une capture.
							k++;
						}
					}
				}
			}
			if(!mangeable){				// si aucun pion adverse n'est capturable
				if(pion.couleur == 1){					// si le pion est blanc
					for(int i = -1 ; i<2 ; i+=2){				// on parcourt les deux cases situees dans diagonales inferieures
						if(i+pion.x >= 0 && i+pion.x < taille && 1+pion.y >= 0 && 1+pion.y < taille && monde[i+pion.x][1+pion.y] == 0){				// on verifie si le pion peut se deplacer sans sortir du tableau.
							deppossible[k][0] = i+pion.x;				// les coordonnees des cases vides sont repertoriezs. 
							deppossible[k][1] = 1+pion.y;
							deppossible[k][2] = 0;						// le zero indique que le pion realise un deplacement
							k++;
						}
					}					
				} else {				// si le pion est noir
					for(int i = -1 ; i<2 ; i+=2){				// on parcourt les deux cases situees dans le diagonales superieures
						if(i+pion.x >= 0 && i+pion.x < taille && -1+pion.y >= 0 && -1+pion.y < taille && monde[i+pion.x][-1+pion.y] == 0){				// on verifie que le pion peut se deplacer sans sortir du tableau.
							deppossible[k][0] = i+pion.x;				// les coordonnees des cases vides sont repertoriees.
							deppossible[k][1] = -1+pion.y;
							deppossible[k][2] = 0;
							k++;
						}
					}
				}
			}
			if(k != 0){				// si le pion peut realiser un/des deplacements ou capture.
				int[][] deppossible2 = new int[k][3];			// on cree un tableau de taille correspondant au nombre de deplacements/captures possibles. 
				for(int i = 0; i<k;i++){						// on parcourt les deux tableaux.
					deppossible2[i][0] = deppossible[i][0];		// on remplit les cases du tableau 2 avec les valeurs du tableau 1.
					deppossible2[i][1] = deppossible[i][1];
					deppossible2[i][2] = deppossible[i][2];
				}
				return deppossible2;							// on retourne le tableau 2.
			} else {				// si le pion peut realiser un/des deplacements ou capture, on remplit le tableau de -1, signigiant l'impossiblite de mouvement.
				int[][] deppossible2 = new int[1][3];			
				deppossible2[0][0] = -1;
				deppossible2[0][1] = -1;
				deppossible2[0][2] = -1;
				return deppossible2;
			} 
		} else if (pion.viv){								// Si le pion est une dame
			int[][] deppossible = new int[20][3];
			boolean mangeable = false;
			boolean danstableau = true;
			boolean aucunpionatteint = true;
			int k = 0;
			int tour;
			for(int l = 0;l < 4;l++){						// On distingue les quatre directions possibles
				tour = 0;
				aucunpionatteint = true;
				danstableau = true;
				while(danstableau && aucunpionatteint){			// Test si cette dame peut capturer un pion adverse
					tour++;
					switch(l) {									// Chaque cas du switch fonctionne de la meme maniere
						/* Direction : en bas a droite */
						case 0:						
							if(pion.x+tour >= 1 && pion.x+tour < taille-1 && pion.y+tour >= 1 && pion.y+tour < taille-1){ // Si le pion restera sur la tableau de jeu
								if(monde[pion.x+tour][pion.y+tour] != 0) { 	// Si la case suivante est non vide
									aucunpionatteint = false;				// On arrete le calcul des deplacements possibles pour cette direction
								} 
								/* Si il y a possibilité de capture */
								if(monde[pion.x+tour][pion.y+tour] != 0 && monde[pion.x+tour][pion.y+tour]%2 != pion.couleur%2 && monde[pion.x+tour+1][pion.y+tour+1] == 0){
									mangeable = true;				
									deppossible[k][0] = pion.x+tour+1;		// On fixe ce deplacement dans le tableau deppossible
									deppossible[k][1] = pion.y+tour+1;		
									deppossible[k][2] = 1;
									k++;
								}
							} else {										// Si la case suivante n'est pas dans le tableau
								danstableau = false;						// On arrete aussi le calcul des deplacements possibles pour cette direction
							}
						break;
						/* Direction : en haut a droite */
						case 1:
							if(pion.x+tour >= 1 && pion.x+tour < taille-1 && pion.y-tour >= 1 && pion.y-tour < taille-1){
								if(monde[pion.x+tour][pion.y-tour] != 0){
									aucunpionatteint = false;
								} 
								if(monde[pion.x+tour][pion.y-tour] != 0 && monde[pion.x+tour][pion.y-tour]%2 != pion.couleur%2 && monde[pion.x+tour+1][pion.y-tour-1] == 0){
									mangeable = true;
									deppossible[k][0] = pion.x+tour+1;
									deppossible[k][1] = pion.y-tour-1;
									deppossible[k][2] = 1;
									k++;
								}
							} else {
								danstableau = false;
							}
						break;
						/* Direction : en bas a gauche */
						case 2:
							if(pion.x-tour >= 1 && pion.x-tour < taille-1 && pion.y+tour >= 1 && pion.y+tour < taille-1){
								if(monde[pion.x-tour][pion.y+tour] != 0){
									aucunpionatteint = false;
								} 
								if(monde[pion.x-tour][pion.y+tour] != 0 && monde[pion.x-tour][pion.y+tour]%2 != pion.couleur%2 && monde[pion.x-tour-1][pion.y+tour+1] == 0){
									mangeable = true;
									deppossible[k][0] = pion.x-tour-1;
									deppossible[k][1] = pion.y+tour+1;
									deppossible[k][2] = 1;
									k++;
								}
							} else {
								danstableau = false;
							}
						break;
						/* Direction : en haut a gauche */
						case 3:
							if(pion.x-tour >= 1 && pion.x-tour < taille-1 && pion.y-tour >= 1 && pion.y-tour < taille-1){
								if(monde[pion.x-tour][pion.y-tour] != 0){
									aucunpionatteint = false;
								} 
								if(monde[pion.x-tour][pion.y-tour] != 0 && monde[pion.x-tour][pion.y-tour]%2 != pion.couleur%2 && monde[pion.x-tour-1][pion.y-tour-1] == 0){
									mangeable = true;
									deppossible[k][0] = pion.x-tour-1;
									deppossible[k][1] = pion.y-tour-1;
									deppossible[k][2] = 1;
									k++;
								}
							} else {
								danstableau = false;
							}
						break;
					}
				}
			}
			if (!mangeable){					// Si le calcul des possibilités de mouvements possibles de la dame verifie cette condition : 	
				k = 0;							// La dame ne peut capturer aucun pion : on liste donc tous les deplacements possibles dans deppossible
				for(int l = 0;l < 4;l++){
					tour = 0;
					aucunpionatteint = true;
					danstableau = true;
					while(danstableau && aucunpionatteint){
						tour++;
						switch(l){						// La distinction des cas se fait de la meme façon que pour la verification de la possibilité de capture
							case 0:						// Chaque cas du switch fonctionne de la meme maniere
								if(pion.x+tour >= 0 && pion.x+tour < taille && pion.y+tour >= 0 && pion.y+tour < taille) { // On test si la case suivante est dans le tableau
									if (monde[pion.x+tour][pion.y+tour] == 0){								
										deppossible[k][0] = pion.x+tour;			// Si la case suivante est vide, on fixe ce deplacement possible dans deppossible
										deppossible[k][1] = pion.y+tour;
										deppossible[k][2] = 0;
										k++;
									} else {
										aucunpionatteint = false;			// Si la case suivante n'est pas vide, on arrete le calcul des deplacements possibles pour cette direction
									}
								} else {
									danstableau = false;
								}
							break;
							case 1:
								if(pion.x+tour >= 0 && pion.x+tour < taille && pion.y-tour >= 0 && pion.y-tour < taille){
									if(monde[pion.x+tour][pion.y-tour] == 0){
										deppossible[k][0] = pion.x+tour;
										deppossible[k][1] = pion.y-tour;
										deppossible[k][2] = 0;
										k++;
									} else {
										aucunpionatteint = false;
									} 
								} else {
									danstableau = false;
								}	
							break;
							case 2:
								if(pion.x-tour >= 0 && pion.x-tour < taille && pion.y+tour >= 0 && pion.y+tour < taille){
									if(monde[pion.x-tour][pion.y+tour] == 0){
										deppossible[k][0] = pion.x-tour;
										deppossible[k][1] = pion.y+tour;
										deppossible[k][2] = 0;
										k++;
									} else {
										aucunpionatteint = false;
									} 
								} else {
									danstableau = false;
								}
							break;
							case 3:
								if(pion.x-tour >= 0 && pion.x-tour < taille && pion.y-tour >= 0 && pion.y-tour < taille){
									if(monde[pion.x-tour][pion.y-tour] == 0){
										deppossible[k][0] = pion.x-tour;
										deppossible[k][1] = pion.y-tour;
										deppossible[k][2] = 0;
										k++;
									} else {
										aucunpionatteint = false;
									} 
								} else {
									danstableau = false;
								}
							break;
						}
					}
				}
			}	
			if(k != 0){
				int[][] deppossible2 = new int[k][3];			// On stock le contenu de deppossible dans un tableau de taille convenable deppossible2
				for(int i = 0; i<k;i++){
					deppossible2[i][0] = deppossible[i][0];
					deppossible2[i][1] = deppossible[i][1];
					deppossible2[i][2] = deppossible[i][2];
				}
				return deppossible2;
			} else {											// Si aucun deplacement n'est possible, on renvoie un {{-1,-1,-1}}
				int[][] deppossible2 = new int[1][3];
				deppossible2[0][0] = -1;
				deppossible2[0][1] = -1;
				deppossible2[0][2] = -1;
				return deppossible2;
			}
		}		
		int[][] deppossible = new int[1][3];		
		deppossible[0][0] = -1;
		deppossible[0][1] = -1;
		deppossible[0][2] = -1;
		return deppossible; 
	}
}
