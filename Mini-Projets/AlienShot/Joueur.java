public class Joueur {
	public int id;
	
	public int vie; //Vie du joueur
	public int position; //position verticale du joueur
	
	public int energie; //energie du joueur
	public int degat; //dégats infligés à l'adversaire
	
	public int nbMissile; // le nombre de missiles tirés
	public int[] positionMissilesX; // les positions des missiles tirés en x
	public int[] positionMissilesY; // les positions des missiles tirés en y

	
	public final int NB_MAX_MISSILES=100;
	public final int ENERGIE_TIR = 3; //energie enlevée pour un tir
	public final int VITESSE_DEPLACEMENT_TIR = 1; //Nombre de cases a chaque mouvement de missile
	public static int UNITE_DEPLACEMENT = 3; // defini le nombre de case dont les vaisseaux se deplacent a chaque mouvement
	
	//Constructeur avec la position et l'id comme paramètre
	public Joueur(int pos, int id){
		this.position = pos;
		this.id = id;
		
		 //valeurs par défaut
		this.vie = 100; //100/100
		this.energie = 50; //50/50
		this.degat=10;
				
		//on crée des tableaux de 100 int pour pouvoir gérer au max 100 missiles en même temps (par joueur)
		positionMissilesX = new int[this.NB_MAX_MISSILES];
		positionMissilesY = new int[this.NB_MAX_MISSILES];
		this.nbMissile = 0;
	}
	
	
	/*Méthode pour modifier la position du joueur
	 * @param haut vaut vrai si il se déplace vers le haut
	 * et faux s'il se déplace vers le bas
	 * prise en charge des position limites pour ne pas sortir de l'aire de jeu 
	 */
	public void deplace(Plateau monde,boolean haut){
		if((this.position >= monde.y - 4 - this.UNITE_DEPLACEMENT) && ( !haut)){ // Si il est tout en bas de l'écran : il ne peut que monter
			this.position = this.position;
		}else if((this.position <= 4 + this.UNITE_DEPLACEMENT) && (haut)){ // Si il est tout en haut de l'écran : il ne peut que descendre
			this.position = this.position;
		}else if((this.position != monde.y - 5) && (this.position != 4)){
			if(haut){
				this.position = this.position - this.UNITE_DEPLACEMENT;
			}else{
				this.position = this.position + this.UNITE_DEPLACEMENT;
			}
		}
        
        
	}
	
	
	/*Pour tirer un missile
	 * Il faut traiter le cas des deux joueurs
	 * On diminue l'énergie à chaque tir
	 * @param monde pour placer les missiles du j2
	 */
	public void tir(Plateau monde){
		//Si il a dépassé le nombre max de missile on le remet à zéro pour pouvoir recommencer à remplire les tableaux de positions a partir du début car les premiers missiles ne sont plus utilisé
		this.nbMissile = this.nbMissile % (this.NB_MAX_MISSILES-1);
		
		//Si c'est le j1 qui tire
		if(this.id==1){
			if(this.energie > this.ENERGIE_TIR){//Si il a asssez d'energie pour tirer
				//On le place devant le vaisseau 
				this.positionMissilesX[this.nbMissile] = 4; 
				this.positionMissilesY[this.nbMissile] = this.position;
			}
		}else if(this.id == 2){
			if(this.energie > this.ENERGIE_TIR){
				this.positionMissilesX[this.nbMissile] = monde.x - 5;
				this.positionMissilesY[this.nbMissile] = this.position;
			}
		}
		
		if(this.energie - this.ENERGIE_TIR > 0){
			this.energie = this.energie-this.ENERGIE_TIR;
		}else{
			this.energie = 0;
		}
		
		this.nbMissile++;
		
	}

	/*
	 * Méthode qui permet de modifier la position des missiles en fonction du joueur
	 * il faut gérer le cas ou deux missiles se rencontre :  on annule les 2
	 * @param monde nécessaire pour gérer les collisions
	 */
	public void deplacerTir(Plateau monde){		
		int ecart = 0; //Ecart (horizontal) entre deux missiles qui sont sur une même ligne
		int i=0, j=0;
		
		boolean trouve1 = true;
		boolean trouve2 = true;
		
		/*Cas des collisions de 2 missiles
		 * 1: On parcourt les positions en y (verticales) des missiles de j1 et j2
		 * 2: si ça correspond on teste les positions x
		 * 3: si ils sont à moins de 3 cases l'un de l'autre : on les enlève
		 * 4: sinon ils se déplacent normalment
		 */
		for(i=0; i<monde.j1.NB_MAX_MISSILES; i++){
			for(j=0; j<monde.j2.NB_MAX_MISSILES; j++){ //Toutes les combinaisons sont testées 
				if(monde.j1.positionMissilesY[i] == monde.j2.positionMissilesY[j]){ //Si deux missiles sont sur la même ligne
					ecart = monde.j2.positionMissilesX[j] - monde.j1.positionMissilesX[i];
					if(ecart<this.VITESSE_DEPLACEMENT_TIR - 1){ //Si les missiles sont à moins de ecart cases
						//On annule le missile du joueur 1
						monde.j1.positionMissilesX[i] = 0;
						monde.j1.positionMissilesY[i] = 0;
						
						//On annule le missile du joueur 2
						monde.j2.positionMissilesX[j] = 0;
						monde.j2.positionMissilesY[j] = 0;
					}
				}
			}
		}
		
		
		//Maintenant on peut déplacer les missiles restants
		//On parcourt les tableaux des positions
		for(i = 0; i<this.NB_MAX_MISSILES; i++){
			
			//Si c'est le joueur 1
			if(id==1 && this.positionMissilesX[i]!=0){
				this.positionMissilesX[i] = this.positionMissilesX[i] + this.VITESSE_DEPLACEMENT_TIR;
				
			}
            else if ( id==1 && this.positionMissilesX[i]==monde.x-1) {
                this.positionMissilesY[i] = 0;
            }
            else if(id == 2 && this.positionMissilesX[i]!=0){//Si c'est le joueur 2 : le missile se déplace vers la gauche
				this.positionMissilesX[i] = this.positionMissilesX[i] - this.VITESSE_DEPLACEMENT_TIR;;
			}
            else if ( id==2 && this.positionMissilesX[i]==0) {
                this.positionMissilesY[i] = 0;
            }
		}
		
		
		
		
	}
	
	
	
	//Une méthode pour diminuer la vie du joueur d'une certaine valeur
	public void diminuerVie(int viePerdu){
		this.vie = this.vie-viePerdu;
	}
	
	/*
	 * Une méthode qui va appeler la méthode diminuerVie si le joueur reçoit un missile de l'autre joueur
	 */
	public void recevoirDegat(Plateau monde){
		int i = 0;
		
		//Degats recu par j1
		if(id == 1){
			//On parcourt les position sur y(verticales) des missiles de j2
			for(i=0; i < monde.j2.positionMissilesY.length;i++){
				//On cherche les missiles qui sont sur la même ligne que le vaisseau (que tout le vaisseau pas seulement le centre)
				if((monde.j2.positionMissilesY[i] >= monde.j1.position - 3) && (monde.j2.positionMissilesY[i] <= monde.j2.position + 3)){
					//On cherche ensuite ceux qui sont juste devant le vaisseau du j1
                    int a = Math.abs( monde.j1.position-monde.j2.positionMissilesY[i]) ;
					if(monde.j2.positionMissilesX[i]<= 3 + this.VITESSE_DEPLACEMENT_TIR - a ){
						monde.j1.diminuerVie(monde.j2.degat);
                        monde.j2.positionMissilesX[i] = 0 ;
                        monde.j2.positionMissilesY[i] = 0 ;
					}
                    
				}
                else if ( monde.j2.positionMissilesX[i] == 0 ){
                    monde.j2.positionMissilesX[i] = 0 ;
                    monde.j2.positionMissilesY[i] = 0 ;
                }
			}
		}
		//Degats recus par j2
		else if(id==2){
			//Meme code mais appliqué a j2
			for(i=0; i < monde.j1.positionMissilesY.length; i++){
				if((monde.j1.positionMissilesY[i] >= monde.j2.position - 3) && (monde.j1.positionMissilesY[i] <= monde.j2.position + 3)){
					if((monde.j1.positionMissilesX[i] >= monde.x-4 -this.VITESSE_DEPLACEMENT_TIR  + Math.abs( monde.j2.position-monde.j1.positionMissilesY[i]) ) ){
						monde.j2.diminuerVie(monde.j1.degat);
                        monde.j1.positionMissilesX[i] = 0 ;
                        monde.j1.positionMissilesY[i] = 0 ;
					}
				}
                else if ( monde.j1.positionMissilesX[i] == monde.x-1 ){
                    monde.j1.positionMissilesX[i] = 0 ;
                    monde.j1.positionMissilesY[i] = 0 ;
                }
			}
		}
        
	}
	
	/*
	 * une méthode pour gérer le rechargement de l'énergie
	 */
	public void gainEnergie(int gain){
		//On test si on peut encore augmenter l'énergie (50 correspond à l'énergie Max)
		if(50 - this.energie >0){
			//Si on peut encore augmenter de la valeur donnée en paramètres : si un ajout de la valeur donnée ferai plus de 50 alors on ajoute seulement ce qui reste pour arriver à 50
			if(gain < 50 - this.energie){
				this.energie = this.energie + gain;
			}else{
				this.energie = 50;
			}
		}
	}
	
	
	/*
	 * Etant donné qu'on rencontre des difficultés pour gérer les touches aux claviers pour pouvoir déplacer les vaisseaux et tirer :
	 * on crée des méthodes pour faire une simulation du jeu
	 * Comme si c'était l'ordinateur qui jouait
	 * Nous on regarde la partie se dérouler
	 */
	
	//1ere méthode de simulation :: les déplacements aléatoires des vaisseaux (on essaye quand même de les faire se raprocher pour que la partie se termine au bout d'un moment pas trop long
	public void simDeplace(Plateau monde){
		double nb = Math.random();
		int diffPos;
		
		//Si c'est le j1
		if(this.id == 1){
			diffPos = this.position - monde.j2.position;
			
			//Si diffPos > 0 équivaut à J1 est en dessous de j2 : Plus de chance pour j1 de monter que de descendre
			if(diffPos > 0){
				if(nb>0.7){
					deplace(monde, true);
				}else if(nb<0.2){
					deplace(monde, false);
				}
			}//Si diffPos < 0 équivaut à J1 au dessus de j2 : plus de chance pour j1 de descendre que de monter
			else if (diffPos < 0){
				if(nb>0.7){
					deplace(monde, false);
				}else if(nb<0.2){
					deplace(monde, true);
				}
			}// Si j1 et j2 sont sur la même ligne : autant de chance de monter que de desendre
			else if(diffPos == 0){
				if(nb<=0.5){
					deplace(monde, true);
				}else{
					deplace(monde, false);
				}
			}
			
			
		}//Si c'est le j2
		else if(id == 2){
			diffPos = this.position - monde.j1.position;
			
			//Si diffPos > 0 équivaut à j2 en dessous de j1 : le joueur 2 à plus de chance de monter que de descendre
			if(diffPos > 0){
				if(nb>0.7){
					deplace(monde, true);
				}else if(nb<0.2){
					deplace(monde, false);
				}
			}//Si diffPos < 0 équivaut à j2 au dessus de j1 : le joueur 2 a plus de chance de descendre que de monter
			else if(diffPos < 0){
				if(nb>0.7){
					deplace(monde, false);
				}else if(nb<0.2){
					deplace(monde, true);
				}
			}//Si j1 et j2 sont sur la même ligne : autant de chance de monter que de descendre
			else if(diffPos == 0){
				if(nb <=0.5){
					deplace(monde, true);
				}else{
					deplace(monde, false);
				}
			}
		}
	}
	
	//2eme méthode de simulation :: déclenchement de tirs aléatoirement
	public void simTir(Plateau monde){
		double nb = Math.random();
		
		if(nb>0.95 && this.energie > this.ENERGIE_TIR){
			tir(monde);
		}
	}
	
	
}
