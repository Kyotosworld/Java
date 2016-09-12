/**Classe qui gère tout concernant les personnages, leurs sorts etc...
/**Classe qui gère tout concernant les personnages, leurs sorts etc...
 */	
	public class perso {
	public int PV ;
	public int force ;
	public int intelligence ;
	public int defense ;
	public int chance;
	public int vitesse ;
	public int PA;
	public int classe;
	public int tour;
	public int[][] t = new int [3][4]; /*[][0] : sort 2 ;[][1] : sort 3 ;[][2] : sort 4 ;[][3] : sort 1 ;*/
	public int k =1 ; // multiplicateur de degat du berserker (passe a 2 quand le sort de soutien est lancé)
	public boolean reussite ;
	public boolean reussitec ; //Réussite critique
	public String ligne1="";
	public String ligne2="";
	public String ligne3="";
	public String ligne4="";
	public String ligne5="";
	public String ligne6="";
	public String ligne7="";
	public String ligne8="";
	public String ligne9="";
	public String ligne10="";
	public double sound = 0; /*Son utilisé*/
	public perso (int a ) {  /*Choix du perso et définition des caracteristiques*/
		this.classe = a;
		this.tour=1;
		if (a==1) {  /*Paladin*/
			this.PV = 1500;
			this.force =75;
			this.intelligence =40; 
			this.defense = 45;
			this.chance = 4;
			this.vitesse =20;
			this.PA = 10;	
			t[2][0]=0;	
			t[2][1]=0;		
			t[2][2]=2;	
			t[2][3]=1;			
		}
		if (a==2) {	/*Archer*/		
			this.PV = 1250;
			this.force =75;
			this.intelligence =50; 
			this.defense = 20;
			this.chance = 6;
			this.vitesse =35;
			this.PA = 10;
			t[2][0]=0;
			t[2][1]=3;
			t[2][2]=4;
			t[2][3]=5;
		}
		if (a==3) { /*Voleur*/
			this.PV = 1300;
			this.force =65;
			this.intelligence =50; 
			this.defense = 25;
			this.chance = 6;
			this.vitesse =30;
			this.PA = 12;	
			t[2][0]=0;	
			t[2][1]=2;		
			t[2][2]=1;	
			t[2][3]=1;		
		}
		if (a==4) { /*Berserker*/
			this.PV = 1450;
			this.force =60;
			this.intelligence =45; 
			this.defense = 25;
			this.chance = 5;
			this.vitesse =25;
			this.PA = 10;	
			t[2][0]=0;	
			t[2][1]=0;		
			t[2][2]=1;	
			t[2][3]=1;		
		}
		if (a==5) { /*Mage*/
			this.PV = 1450;
			this.force = 40;
			this.intelligence = 80; 
			this.defense = 30;
			this.chance = 6;
			this.vitesse =25;
			this.PA = 10;	
			t[2][0]=0;	
			t[2][1]=8;		
			t[2][2]=5;	
			t[2][3]=1;		
		}
	}
	
	public int degats () { /*Gère les attaques de base des personnages, soustraction des PA, determine les degats infligés */
		int PAu ; /* PA utilisés */
		int degats = 0;
		if (this.classe == 1) { /*Tranche (Paladin)*/
			PAu = 3;
			if (this.PA < PAu) {
				this.ligne1="Vous n'avez pas assez de PA";
			} else {
				this.PA = this.PA - PAu ;
				degats = lancerdedes(100,this.force,1 ) ;
				if(this.reussite){
					SoundManager.play("sfx/paladinQ1.wav");
				}
			}
		}
		if (this.classe == 2) { /*Tir direct (archer)*/
				PAu = 3;
			if (this.PA < PAu) {
				this.ligne1="Vous n'avez pas assez de PA";
			} else {
				this.PA = this.PA - PAu ;
				degats = lancerdedes(70,this.force,1 ) ;
				if(this.reussite){
					SoundManager.play("sfx/archerQ.wav");
				}
			}		
		}
		if (this.classe == 3) { /*Coudboul (Voleur)*/
				PAu = 3;
			if (this.PA < PAu) {
				this.ligne1="Vous n'avez pas assez de PA";
			} else {
				this.PA = this.PA - PAu ;
				degats = lancerdedes(85,this.force,1 ) ;
				if(this.reussite){
					SoundManager.play("sfx/coudboul.wav");
				}
			}
		}
		if (this.classe == 4) { /*Frappe (Berserker)*/
			PAu = 2;
			if (this.PA < PAu) {
				this.ligne1="Vous n'avez pas assez de PA";
			} else {
				this.PA = this.PA - PAu ;
				degats = lancerdedes((70*k),this.force,1 ) ;
				if(this.reussite){
					SoundManager.play("sfx/berserkQ.wav");
				}
			}
			this.k = 1 ;
		}
		if (this.classe == 5) { /*Poussette (Mage)*/
				PAu = 3;
			if (this.PA < PAu) {
				this.ligne1="Vous n'avez pas assez de PA";
			} else {
				this.PA = this.PA - PAu ;
				degats = lancerdedes(70,this.force,1) ;
				if(this.reussite ){
                    SoundManager.play("sfx/poussette2.wav");
				}
			}
		}
		return degats;
		
	}
	
	public int lancerdedes (int deg , int car,int sort) {  /* Détermine la reussite ou l'echec des attaques, retourne les degats correspondants */
		int val =(int) (Math.random()*100) ;
		int res =0;
		int a = (int) car/10 + this.chance;
		int k = 1;
		if (val > car && val <=90+this.chance){
			this.ligne1=val+"/100  =>  Echec :(";
			this.reussite = false;
			this.reussitec = false;
		}
		if (val <= a) {
			res = deg*2 ;
			this.ligne1=val+"/100  =>  Coup critique :O";
			this.repliqueCC(sort);
			this.reussite = true;
			this.reussitec = true;
		}
		if (val <= car && val > a){
			res = deg ;
			this.ligne1=val+"/100  =>  Coup :)";
			this.reussite = true;
			this.reussitec = false;
		}
		if (val<100 && val>90+this.chance){
			int degaffiche = deg - this.defense*deg/100;
			this.ligne1=val+"/100  =>  Echec critique, vous perdez "+degaffiche+" PV :'(";
			this.repliqueEC(sort);
			this.PV =(int) (this.PV - deg + this.defense*deg/100) ;
			this.reussite = false;
			this.reussitec = true;	
		}
		return res;
	}
	

	public void soutien (){   /* Gère les sorts de soutien des personnages, soustraction de PA, amélioration des caractéristiques */
		int PAu ;
		if (this.classe == 1){ /*Renfort (Paladin)*/
			
			if(this.cooldownsort2()) { 
				PAu = 2;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					this.defense = this.defense +lancerdedessoutien(20,intelligence);
					if(this.reussite){
						SoundManager.play("sfx/paladindef.wav");
						this.t[0][0]=this.tour;
						this.reussite=false;
						this.t[1][0]=4;
					}
				}
			} else {
			this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}
		if (this.classe == 2){ /*Vue percante (Archer)*/
			if(this.cooldownsort2()) { 
				PAu = 2;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					int a = lancerdedessoutien(3,intelligence);
					t[2][0]=0+a;	
					t[2][1]=3+a;		
					t[2][2]=4+a;	
					t[2][3]=5+a;
					if(this.reussite){	
						SoundManager.play("sfx/archerpo.wav");
						this.t[0][0]=this.tour;
						this.reussite=false;
						this.t[1][0]=4;
					}
				}
			} else {
			this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
			
		}
		if (this.classe == 3) { /*Jure Wallah j'ai pas volé (Voleur)*/	
			if(this.cooldownsort2()) { 
			PAu = 4;
			if (this.PA < PAu) {
				this.ligne1="Vous n'avez pas assez de PA";
				this.t[0][0]=0;
			} else {
				this.PA = this.PA - PAu ;
				int a = lancerdedessoutien(5,intelligence);
				this.intelligence = this.intelligence +a;
				this.force = this.force +a;
				if(this.reussite){
					SoundManager.play("sfx/voleurjurewlh.wav");
					this.t[0][0]=this.tour;
					this.t[1][0]=5;
				}
			}
		} else {
			this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}
		if (this.classe == 4) { /*Furie (Berserker)*/
			if(this.cooldownsort2()) { 
				PAu = 3;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					this.k = 1+lancerdedessoutien(1,intelligence) ;
					if(this.reussite){
					SoundManager.play("sfx/berserkrage.wav");
					this.t[0][0]=this.tour;
					this.reussite=false;
					this.t[1][0]=4;
					}
				}
			} else {
			this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}	
		if (this.classe == 5) { /*Soin (Mage)*/
			if(this.cooldownsort2()) { 
				PAu = 4;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					this.PV = this.PV + lancerdedessoutien(60,intelligence) ;
					if(this.reussite){
						if (this.PV > 1250 ) {
						this.PV = 1250;
						}
					SoundManager.play("sfx/heal.wav");
					this.t[0][0]=this.tour;
					this.t[1][0]=3;
					this.reussite=false;
				}
				}
			} else {
			this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}	
	}
	
	public int sort3() {
		int PAu ;
		int degats = 0;
		if (this.classe ==1) { /*Rage (Paladin)*/
				if(this.cooldownsort3()) { 
				PAu = 2;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					this.force = this.force +lancerdedessoutien(20,intelligence);
					if(this.reussite){
						SoundManager.play("sfx/paladinforce.wav");
						this.t[0][1]=this.tour;
						this.t[1][1]=4;
						this.reussite=false;
					}
				}
			} else {
				this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}
		if (this.classe ==2) { /*Fleche de recul (Archer)*/
			if(this.cooldownsort3()){
					PAu = 3;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					degats = lancerdedes(50,this.intelligence,3 ) ;
					if(this.reussite){
						SoundManager.play("sfx/archerrecul.wav");
						this.t[0][1]=this.tour;
						this.t[1][1]=3;
					}
				}	
			} else {	
				this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}
		if (this.classe ==3) { /*Esquive de la police (Voleur)*/
			if(this.cooldownsort3()) { 
				PAu = 4;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					this.defense = this.defense +lancerdedessoutien(75,intelligence);
					if(this.reussite){
						SoundManager.play("sfx/voleuresquivepopo.wav");
						this.t[0][1]=this.tour;
						this.t[1][1]=6;
						this.reussite=false;
					}
				}	
			} else {
				this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}
		if (this.classe ==4) { /*Concentration (Berserker)*/
			if(this.cooldownsort3()) { 
				PAu = 3;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					this.chance = this.chance +lancerdedessoutien(2,intelligence);
					if(this.reussite){
						SoundManager.play("sfx/berserkconcentration.wav");
						this.reussite=false;
						this.t[0][1]=this.tour;
						this.t[1][1]=4;
					}
				}
			} else {
				this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}
		if(this.classe ==5) { /* Glyphe (Mage)*/
			PAu = 3;
			if(this.cooldownsort3()) { 
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					lancerdedessoutien(0,intelligence);
					if (this.reussite){
						SoundManager.play("sfx/glypheshort.wav");
						this.t[0][1]=this.tour;
						this.t[1][1]=3;
					}			
				}
			} else {
				this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}
		return degats;
	}
	
	public int sortspe() {
		int PAu;
		int degats = 0;
		if (this.classe == 1) { /*Impact (Paladin)*/
			if(this.cooldownsortspe()) { 
				PAu = 6;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					degats = lancerdedes(300,this.force,4 ) ;
					if(this.reussite){
						SoundManager.play("sfx/paladinult.wav");
						this.t[0][2]=this.tour;
						this.t[1][2]=6;
					}
				}
			} else {
				this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}
		if (this.classe == 2) { /*Tir puissant (Archer)*/	
			if(this.cooldownsortspe()) { 
				PAu = 5;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					degats = lancerdedes(250,this.force,4 ) ;
					if(this.reussite){
						SoundManager.play("sfx/archerult.wav");
						this.t[0][2]=this.tour;
						this.t[1][2]=6;
					}
				}
			} else {
				this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}
		if (this.classe == 3) { /*Double lame (Voleur)*/	
			if(this.cooldownsortspe()) { 
				PAu = 6;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					degats = lancerdedes(200,this.force,4 ) + lancerdedes(200,this.intelligence,4); 
					if(this.reussite){
						SoundManager.play("sfx/voleurdoublelame.wav");
						this.t[0][2]=this.tour;
						this.t[1][2]=6;
					}
				}
			} else {
				this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}
		if (this.classe == 4) { /*Sacrifice (Berserker)*/	
			if(this.cooldownsortspe()) { 
				PAu = 5;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				} else {
					this.PA = this.PA - PAu ;
					degats = lancerdedes(450,this.force,4 ) ;
					if(this.reussite){
						SoundManager.play("sfx/berserkult.wav");
						this.t[0][2]=this.tour;
						this.t[1][2]=6;
						this.PV = this.PV -100;
					}
				}
			} else {
				this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}
		if (this.classe == 5) { /*Souffle du dragon (Mage)*/
			if(this.cooldownsortspe()) { 
				PAu = 5;
				if (this.PA < PAu) {
					this.ligne1="Vous n'avez pas assez de PA";
				}else{
					this.PA = this.PA - PAu ;
					degats = lancerdedes(250,this.intelligence,4 ) ;
					if(this.reussite){
						SoundManager.play("sfx/Katon2.wav");
						this.t[0][2]=this.tour;
						this.t[1][2]=6;
					}
				}
			} else {
				this.ligne1="Vous ne pouvez pas encore lancer ce sort";
			}
		}
		return degats;
	}
		
	public int lancerdedessoutien (int deg ,int car) {
		int val =(int) (Math.random()*100) ;
		int res =0;
		if (val > car+this.chance){
			this.ligne1=val+"/100  =>  Echec :(";
			reussite = false;
		}
		if (val <= car+this.chance) {
			res = deg ;
			this.ligne1=val+"/100  =>  Reussite :)";
			reussite = true;
		}
		return res ;
	}
	public boolean passertour () {
		if (this.classe ==3){
			this.PA=12;
		}else{
			this.PA=10;
		}
		this.tour=this.tour+1;
		return true;
	}
	
	//Debuff des sorts de soutien après leur durée achevée et gestion des cooldowns
	public void testsoutienpaladin () {
		if (this.tour==t[0][0]+2  && t[1][0]!=0 && this.classe==1){
			this.defense=this.defense-20;
		}
	}
	public void testsoutienarcher () {
		if (this.tour==t[0][0]+2  && t[1][0]!=0 && this.classe==2){
			t[2][0]=0;	
			t[2][1]=3;		
			t[2][2]=4;	
			t[2][3]=5;			
		}
	}
	public void testsoutienvoleur () {
		if (this.tour==t[0][0]+2  && t[1][0]!=0 && this.classe==3){
			this.intelligence=this.intelligence-5;
			this.force=this.force-5;
			t[1][0]=0;
		}
	}
	public void testsort3paladin () {
		if (this.tour==t[0][1]+2  && t[1][1]!=0 && this.classe==1){
			this.force=this.force-20;
		}
	}
	public void testsort3voleur () {
		if (this.tour==t[0][1]+1  && t[1][1]!=0 && this.classe==3){
			this.defense=this.defense-75;
		}
	}
	public void testsort3berserker () {
		if (this.tour==t[0][1]+2  && t[1][1]!=0 && this.classe==4){
			this.chance=this.chance-2;
		}
	}
	
	//cooldown des differents sorts
	public boolean cooldownsort2(){
		if ((this.tour>=t[0][0]+t[1][0] ) || t[1][0]==0){
			return true ;
		}
		return false;
	}	
	public boolean cooldownsort3(){
		if ((this.tour>=t[0][1]+t[1][1] ) || t[1][1]==0){
			return true ;
		}
		return false;
	}
	public boolean cooldownsortspe(){
		if ((this.tour>=t[0][2]+t[1][2] ) || t[1][2]==0){
			return true ;
		}
		return false;
	}
	
	public boolean estvivant () { 
		if (this.PV <= 0) {
			return false ;
		} else { 
			return true ;
		}
	}
	public void debutdetour () { /*Toutes les action effectuées en début de tour (gestion des cooldowns, remise à normale des stats...)*/
		testsoutienpaladin();
		testsoutienarcher();
		testsoutienvoleur();
		testsort3paladin();
		testsort3voleur();
		testsort3berserker();
		testpassifberserker();
		passifberserker();
	}		
	public void passifberserker () { //active le passif du Berserker (+1 de force tout les 50 PVs perdus en fin de tour)
		if (this.classe==4){
			int a = (int) ((1750-this.PV)/50);
			this.force = this.force + a ;
		}
	}
	
	public void testpassifberserker () {
		if (this.classe ==4){
			this.force = 60;
		}
	}
	public int getvitesse (){ //Récuperation de la vitesse du personnage
		int a = this.vitesse ;
		return a;
	}
	public String nom () {//affiche le nom du personage
		String nom ="";
			if (this.classe ==1){
				nom = "Paladin";
			}
			if (this.classe ==2){
				nom = "Archer";
			}
			if (this.classe ==3){
				nom = "Voleur";
			}
			if (this.classe ==4){
				nom = "Berserker";
			}
			if (this.classe ==5){
				nom = "Mage";
			}
	return nom;
	}
	
	public void repliqueEC (int sort){ //Phrase affichée lors d'un échec critique
		if (this.classe==1){
			if (sort==1){
				this.ligne3="Fatigue de sa cuite d'hier soir prise a la taverne avec Dede, demoralise par sa separation la semaine derniere avec Cunegonde et affaibli par le manque ";
				this.ligne4="flagrant d'entrainement a cause de ses journees entieres passees derriere son ecran d'ordinateur. Le paladin essaye de se trancher les veines avec son epee ";
				this.ligne5="en criant le nom de sa bien aimee perdue...  ";
			}else{
				this.ligne3="Lorsqu'il a marche dans une crotte d'Orc ce matin en allant chercher son pain, le paladin savait que ce ne serait pas une bonne journee. Il a ensuite vu un ";
				this.ligne4="chat noir, a marche sous une echelle, n'a pas fait circuler une chaine de mail a propos de la dame blanche, et a ouvert son parapluie a l'interieur juste apres ";
				this.ligne5="avoir casse son miroir, c'est avec ces 132 annees de malheur accumulees que le paladin leve son epee au dessus de sa tete et que celle-ci lui retombe sur ";
				this.ligne6="l'epaule.";
			}
		}
		if (this.classe==2){
			if(sort==1){
				this.ligne3="L'orc ,la biere deguste hier a la taverne et regurgite par l'Archer plus tard dans la nuit surement car il n'etait pas frais ou bien plutot a cause de cette ";
				this.ligne4="bouteille d'alcool fort dans laquelle macerait plusieurs frelons et que le joueur a descendu d'une traite pour montrer qu'il etait un vrai bonhomme a ce frimeur ";
				this.ligne5="de nain qui venait de le battre au babyfoot. C'est dans cet etat lamentable que l'Archer essaye de tirer sa fleche, ouvre la mauvaise main et se prend son ";
				this.ligne6="arc dans la tronche.";
			}else{	
				this.ligne3="Lors de sa soiree d'hier soir, apres avoir ingurgite une bouteille d'alcool fort ou macerait plusieurs frelons, l'Archer totalement bourre n'a pas pu s'empecher";
				this.ligne4="d'aller se moquer d'un nain sur la taille de son anatomie, lorque cette joute verbale a degenere l'Archer ivre mort a degaine son sandwich au thon au lieu de ";
				this.ligne5="sa dague et s'est pris un low-kick dans la hanche qui l'a mis KO. C'est avec cette hanche en mauvais etat que l'elfe glisse sur ses propres cheveux, s'etale sur";
				this.ligne6=" le dos, libere ses 3 fleches au dessus de lui et celles-ci retombent et viennent se loger dans differentes parties fort douloureuses de son corps.";
			}
		}
		if (this.classe==3){
			if (sort==1){
				this.ligne3="Le voleur croit appercevoir son ex au loin, dire qu'elle l'a plaquee pour son ami Felix, elle est si belle dans sa petite robe a fleur... Cela lui rappelle "; 
				this.ligne4="leurs vacances a la Baule, ils y avaient fait de la banane... C'est finalement elle la seule personne qui a un jour reussi a lui voler quelque chose, son";
			}else{
				this.ligne5="coeur... L'esprit embrume, le voleur confond son ennemi avec un arbre et se brise le nez en voulant lui donner un violent Coudboul.";
				this.ligne3="La kleptomanie du voleur prenant le dessus, il essaye de derober le rubis depassant de la poche de son adversaire, mais n'ayant pas assez de mains puisqu'il doit";
				this.ligne4="deja manier ses deux lames, il n'arrive pas a tout faire en meme temps et s'entaille le bras et la cuisse.";
			}
		}
		if (this.classe==4){
			if(sort==1){
				this.ligne3="Fatigue par sa titanesque seance de muscu a la salle ce matin pendant laquelle il a pratique un genocide des biceps pour impressionner une petite qui faisait";
				this.ligne4="des squats, le Berserker ne parvient pas a lever son bras pour frapper son ennemi et son enorme avant bras lui retombe en plein sur la face. ";
			}else{
				this.ligne3="Lorsque le Berserker se concentre pour puiser dans ses forces vitales afin de lancer son ultime attaque son esprit est attire par l'image de Garry, son escargot ";
				this.ligne4="de compagnie qui est mort ce matin. Ce deces tragique laissant un profond vide dans le coeur du Berserker, celui-ci puise dans son energie vitale mais n'a ";
				this.ligne5="plus la force de frapper son adversaire.";
			}
		}
		if(this.classe==5){
			if(sort==1){
				this.ligne3="Son ancienne vie de croupier à Las Vegas le hantant encore, le Mage leve son baton et rapproche son ennemi tel un gros tas de jeton au lieu de le repousser. ";
				this.ligne4="Celui-ci en profite pour lui assener un violent coup de boule des familles et lui brise le nez.";
			}else{
				this.ligne3="Encore fatigue de son match de quidditch d'hier contre l'En avant Guimgamp et surtout à cause de la troisieme mi-temps qui a ete tres rude, personne ne peut  ";
				this.ligne4="resister a cette excellente biere des moines trappistes. Il s'emmele les mains, perd sa concentration et tenant son baton à l'envers  sa boule de feu lui explose  ";
				this.ligne5="au visage et lui brule au passage les cheveux et les sourcils.";
			}
		}
	}
	public void repliqueCC (int sort){ //Phrase affichée lors d'un coup critique
		if (this.classe==1) {
			if (sort ==1) {
				this.ligne3="Le moral du paladin est excellent, il a certes ete quitte par Cunegonde la semaine derniere mais il a reussi a serrer la petite AJ Lee lors de sa soiree d'hier.";
				this.ligne4="Une nuit des plus torrides , un petit dejeuner pris au lit a 11h ainsi qu'une serie de pompes a un doigt claquees dans le dos lui confere cette excellente condition"; 
				this.ligne5="psychologique et physique, 135 kg au developpe à la salle maggle, et le paladin souleve son enorme epee au dessus de sa tete et assene ";
				this.ligne6="un coup fulgurant droit dans la face de son adversaire !";				
			}else{
				this.ligne3="Le paladin, dans un moment d'emotion pendant ce combat difficille voit defiler dans sa tete moultes personnages tous plus epiques les uns que les autres, ";
				this.ligne4="tous ces gens qui l'ont fait rire lui et son ami Beber : Fatal Bazooka, Jesus, l'homme de la mer noire, Josue, le pape qui attend sa soeur, Julien Lepers, Kadoc,"; 
				this.ligne5="2G1O, Ahmed, Chuck Norris, The Rock, Eddy Malou, Le cannibal russe, Rambo, Shia LaBeouf, John Cena, Vald, Bob Ross, Ribery, JCVD et biensur l'unique,";
				this.ligne6="Alexandre Astier ! Tous ces modeles lui donnant pletor de courage et de force, il cree une onde de choc titanesque en enfoncant son epee dans le sol !";
			}
		}
		if (this.classe==2){
			if(sort==1){
				this.ligne3="Ayant participe a un stage de tir a l'arc dans lequel intervenait notamment Oliver Queen et Robin de bois, il a perfectionne sa technique de tir et il est ";
				this.ligne4="maintenant capable de tirer plus loin, plus vite, les yeux bandes, en grand ecart (merci JCVD), sur un pied tel un flamand rose, et meme sur les mains en ";
				this.ligne5="tirant avec les pieds, ce qui est totalement inutile mais entre nous tellement classe. L'Archer libere donc sa fleche qui fonce droit sur sa cible et se ";
				this.ligne6="loge directement dans son epaule.";
			}else{
				this.ligne3="L'Archer ayant deja regarde la trilogie du seigneur des anneaux en version longue plusieurs fois et enchaine sur la trilogie le Hobbit, il a etudie parfaitement";
				this.ligne4="la technique de Legolas pour tirer trois fleches en même temps. C'est ainsi que l'archer prend bien son temps (merci le tour par tour) pour encocher ses trois ";
				this.ligne5="fleches et pour viser son adversaire. Ainsi les trois fleches partent et fusent sur l'ennemi et le transperce de part en part.";
			}
		}
		if (this.classe==3){
			if (sort == 1){
				this.ligne3="Le voleur etant un fan de Zizou inconsiderable, il a regarde la finale de la coupe du monde 2006 une centaine de fois pour assimiler la technique parfaite.";
				this.ligne4="il s'est ensuite entraine des jours durant sur sa petite soeur Kevina ce qui lui a permis de comprendre toutes les facettes de Coudboul, ancienne technique";
				this.ligne5="de combat Guatemalteque. Grace a cet apprentissage, il reussit parfaitement son coup et brise l'integralite des cotes de son adversaire. ";
			}else{
				this.ligne3="La Kleptomanie du voleur atteignant un sommet colossal, sa motivation augmente en voyant tous les bijous que possede son adversaire, il entre en mode rage,";
				this.ligne4="des rubis pleins les yeux. Il se souvient tout ce que lui a appris son parrain Kenji lorsqu'ils faisaient tous les deux les poches des touristes sous la ";
				this.ligne5="tour Eiffel. Il degaine ses deux dagues, fait deux larges entailles dans le corps de son ennemi et en un tournemain le soulage de tous ses bijoux, allege  ";
				this.ligne6="sa bourse et lui derobe meme son piercing Prince Albert.";
			}
		}
		if (this.classe==4){
			if (sort==1) {
				this.ligne3="On ne sait toujours pas par quel magie le Berserker a reussi a penetrer la matrice dans laquelle il a frappe le lanceur du de de 100 jusqu'a ce que celui ci ";
				this.ligne4="accepte de lui accorder un coup critique mais il en resulte tout de meme que lorque le Berserker revient sur le plateau son adversaire a perdu enormement de";
				this.ligne5="PV sans qu'il ait besoin de lever le doigt.Comme quoi la diplomatie musclee finit toujours par payer. Un vieux sage a dit un jour : si la force ne resoud ";
				this.ligne6="pas tes problemes, c'est probablement que tu ne tapes pas assez fort et ca le Berserker l'a bien compris !";
			}else{
				this.ligne3="Apparemment le Berserker n'est pas une brute sans cervelle, c'est aussi un intellectuel et il a semble-t-il recemment appris le codage Java, il a surement ";
				this.ligne4="passe plusieurs heures au centre IF pour obtenir ces competences. Il a reussi a penetrer la matrice, Tank ne l'a meme pas vu passer, et a modifie le code ";
				this.ligne5="du de de 100 pour obtenir ce coup critique. C'est ainsi que le Berserker puise sans aucun effort dans ses forces vitales pour infliger un coup titanesque ";
				this.ligne6="à son adversaire !";
			}
		}
		if (this.classe==5){
			if (sort == 1){
				this.ligne3="Son ancienne vie de joueur de cricket durant laquelle la Mage s'est entraine a manier le baton lui confere une habilete surhumaine qu'il n'a absolument pas ";
				this.ligne4="herite de son grand-pere moine shaolin Guatemalteque puisque celui-ci ne se battait pas mais brassait de la biere. En revanche il a peut-etre deja rencontre ";
				this.ligne5="Donatello, enfin il a deja rencontre un homme habille en vert avec un bandeau violet, on vous laisse juger si c'etait vraiment Donatello. Il en resulte neanmoins";
				this.ligne6="que le Mage est tres habile avec un baton ce qui lui permet d'assener un violent coup dans l'abdomen de son adversaire, ce qui le repousse en arriere.";
			}else{
				this.ligne3="La veille au soir le Mage a assiste au rassemblement du corbeau pendant lequel il s'est ennuye a mourir, les duels etaient interminables et il n'y avait meme pas ";
				this.ligne4="d'alcool. Le Mage a donc lance ses deux sorts les plus impressionants, le coup du pirate et le coup du cocotier puis il est parti, laissant derriere lui les autres ";
				this.ligne5="participants bouche bee. Ayant ainsi economise ses points de magie il peut donc puiser dans les plus profonds tenebres de son etre et declencher une boule de feu";
				this.ligne6="surpuissante !";
			}
		}
	}
}
