/**Classe permettant l'affichage du jeu
 */
import java.awt.*;
import javax.swing.*;
public class Panel extends JPanel {
	Plateau monde ;
	perso J1;
	perso J2;
	keyListener kListener;
	boolean menu = false;
	int joueur1;
	int joueur2;
	public Panel (Plateau t,perso a , perso b , keyListener k ,int j1 , int j2 ) {
		this.monde = t ;
		this.J1 = a;
		this.J2 = b;
		this.kListener = k;
		this.joueur1=j1;
		this.joueur2=j2;
	}
	public void paintComponent (Graphics g) { //Définition des images du jeu
		if (monde.endGame == false){
			g.setColor(new Color (255,255,255));
			g.fillRect(0,0,this.getSize().width,this.getSize().height);
			g.setColor(new Color (0,0,0));						
			ImageIcon grass = new ImageIcon("photos/grass.png");
			ImageIcon grass2 = new ImageIcon("photos/grass2.png");
			ImageIcon flower1 = new ImageIcon("photos/flower1.png");
			ImageIcon flower2 = new ImageIcon("photos/flower2.png");
			ImageIcon water = new ImageIcon("photos/water.png");
			ImageIcon bridge = new ImageIcon("photos/bridge.png");
			ImageIcon rock = new ImageIcon("photos/rock.png");
			ImageIcon P1 = new ImageIcon("");
			ImageIcon P2 = new ImageIcon("");
			ImageIcon fond = new ImageIcon("photos/fond.jpg");
			ImageIcon carP1=new ImageIcon("");
			ImageIcon carP2=new ImageIcon("");
			if (J1.classe ==1){ carP1 = new ImageIcon("photos/paladin.png");
				P1 = new ImageIcon("photos/jetonpaladin.png");}
			if (J1.classe ==2){ carP1 = new ImageIcon("photos/archer.png");
				P1 = new ImageIcon("photos/jetonarcher.png");}
			if (J1.classe ==3){ carP1 = new ImageIcon("photos/voleur.png");
				P1 = new ImageIcon("photos/jetonvoleur.png");}
			if (J1.classe ==4){ carP1 = new ImageIcon("photos/berserker.png");
				P1 = new ImageIcon("photos/jetonberserker.png");}
			if (J1.classe ==5){ carP1 = new ImageIcon("photos/mage.png");
				P1 = new ImageIcon("photos/jetonmage.png");}
			if (J2.classe ==1){ carP2 = new ImageIcon("photos/paladin.png");
				P2 = new ImageIcon("photos/jetonpaladin.png");}
			if (J2.classe ==2){ carP2 = new ImageIcon("photos/archer.png");
				P2 = new ImageIcon("photos/jetonarcher.png");}
			if (J2.classe ==3){ carP2 = new ImageIcon("photos/voleur.png");
				P2 = new ImageIcon("photos/jetonvoleur.png");}
			if (J2.classe ==4){ carP2 = new ImageIcon("photos/berserker.png");
				P2 = new ImageIcon("photos/jetonberserker.png");}
			if (J2.classe ==5){ carP2 = new ImageIcon("photos/mage.png");
				P2 = new ImageIcon("photos/jetonmage.png");}
			ImageIcon boite = new ImageIcon("photos/boite.png");
			g.drawImage(fond.getImage(),0,0,null);
			int x= 0;
			int y=0;
			for (int i = 0; i < monde.plateauMod.length; i++) {
				for (int j = 0; j < monde.plateauMod[0].length; j++) {
					if (monde.plateauMod[i][j] == 5 || (monde.plateauMod[i][j] == 1 || monde.plateauMod[i][j] == 2) ){
						g.drawImage(flower1.getImage(),x,y,null);
					}
					if (monde.plateauMod[i][j] == 6 || (monde.plateauMod[i][j] == 1 || monde.plateauMod[i][j] == 2 )){
						g.drawImage(flower2.getImage(),x,y,null);
					}
					if (monde.plateauMod[i][j] == 7 ||(monde.plateauMod[i][j] == 1 || monde.plateauMod[i][j] == 2 )){
						g.drawImage(grass2.getImage(),x,y,null);
					}
					if (monde.plateauMod[i][j] == 8 || (monde.plateauMod[i][j] == 1 || monde.plateauMod[i][j] == 2 )){
						g.drawImage(water.getImage(),x,y,null);
					}
					if (monde.plateauMod[i][j] == 9 || (monde.plateauMod[i][j] == 1 || monde.plateauMod[i][j] == 2 )){
						g.drawImage(bridge.getImage(),x,y,null);
					}
					if (monde.plateauMod[i][j] == 0 || (monde.plateauMod[i][j] == 1 || monde.plateauMod[i][j] == 2 )){
						g.drawImage(grass.getImage(),x,y,null);
					}
					if (monde.plateauMod[i][j] == 4 || (monde.plateauMod[i][j] == 1 || monde.plateauMod[i][j] == 2 )){
						g.drawImage(flower2.getImage(),x,y,null);
					}
					if (i==8 && (j==4 || j==8 || j==12)){
					g.drawImage(bridge.getImage(),x,y,null);
					}
					if (monde.plateauMod[i][j] == 1 ){
						g.drawImage(P1.getImage(),x,y,null);
					}
					if (monde.plateauMod[i][j] == 2){
						g.drawImage(P2.getImage(),x,y,null);
					}
					if (monde.plateauMod[i][j] == 3){
						g.drawImage(rock.getImage(),x,y,null);
					}
					x = x + 30;
				}
				y = y + 30;
				x = 0;
			}
			g.setColor(new Color(255,255,255)); //Affichage en jeu
			g.setFont(new Font("Arial", Font.BOLD,15));
			g.drawImage(carP1.getImage(),530,0,null);
			g.drawString("classe : "+nom(J1,0),670,30);
			g.drawString("point de vie : "+J1.PV,670,48);
			g.drawString("force : "+J1.force,670,66);
			g.drawString("intelligence : "+J1.intelligence,670,84);
			g.drawString("defense : "+J1.defense,670,102);
			g.drawString("vitesse : "+J1.vitesse,670,120);
			g.drawString("chance : "+J1.chance,670,138);
			g.drawString("PA : "+J1.PA,670,156);
			g.drawString("sort 1 : "+nom(J1,1),550,174);
			g.drawString("sort 2 : "+nom(J1,2),550,192);
			g.drawString("sort 3  :"+nom(J1,3),550,210);
			g.drawString("sort 4 : "+nom(J1,4),550,228);
			g.drawImage(carP2.getImage(),530,250,null);
			g.drawString("classe : "+nom(J2,0),670,280);
			g.drawString("point de vie : "+J2.PV,670,298);
			g.drawString("force : "+J2.force,670,316);
			g.drawString("intelligence : "+J2.intelligence,670,334);
			g.drawString("defense : "+J2.defense,670,352);
			g.drawString("vitesse : "+J2.vitesse,670,370);
			g.drawString("chance : "+J2.chance,670,388);
			g.drawString("PA : "+J2.PA,670,406);
			g.drawString("sort 1 : "+nom(J2,1),550,424);
			g.drawString("sort 2 : "+nom(J2,2),550,442);
			g.drawString("sort 3  :"+nom(J2,3),550,460);
			g.drawString("sort 4 : "+nom(J2,4),550,478);
			g.drawImage(boite.getImage(),0,540,null);
			g.drawString(monde.ligne1,25,570);
			g.drawString(J1.ligne1,25,570);
			g.drawString(monde.ligne2,25,585);
			g.drawString(J2.ligne1,25,570);
			g.drawString(monde.ligne3,25,600);
			g.drawString(J1.ligne3,25,597);
			g.drawString(J2.ligne3,25,597);
			g.drawString(J1.ligne4,25,609);
			g.drawString(J2.ligne4,25,609);
			g.drawString(J1.ligne5,25,621);
			g.drawString(J2.ligne5,25,621);
			g.drawString(J1.ligne6,25,633);
			g.drawString(J2.ligne6,25,633);
		} else {
			this.removeAll(); //Affichage de fin de jeu
			g.setColor(new Color (255,255,255));
			g.fillRect(0,0,this.getSize().width,this.getSize().height);
			g.setColor(new Color (0,0,0));
				ImageIcon archerpodium = new ImageIcon("photos/archerpodium.png");
				ImageIcon berserkerpodium = new ImageIcon("photos/berserkerpodium.png");
				ImageIcon magepodium = new ImageIcon("photos/magepodium.png");
				ImageIcon voleurpodium = new ImageIcon("photos/voleurpodium.png");
				ImageIcon paladinpodium = new ImageIcon("photos/paladinpodium.png");
				ImageIcon podiumP1 = new ImageIcon("photos/podiumP1.png");
				ImageIcon podiumP2 = new ImageIcon("photos/podiumP2.png");
				if (J2.estvivant() == false){
					g.drawImage(podiumP1.getImage(),0,-50,null);
					if (J1.classe ==1){ g.drawImage(paladinpodium.getImage(),690,80,null);;}
					if (J1.classe ==2){ g.drawImage(archerpodium.getImage(),690,80,null);;}
					if (J1.classe ==3){ g.drawImage(voleurpodium.getImage(),690,80,null);;}
					if (J1.classe ==4){ g.drawImage(berserkerpodium.getImage(),690,80,null);;}
					if (J1.classe ==5){ g.drawImage(magepodium.getImage(),690,80,null);;}
					if (J2.classe ==1){ g.drawImage(paladinpodium.getImage(),450,150,null);;}
					if (J2.classe ==2){ g.drawImage(archerpodium.getImage(),450,150,null);;}
					if (J2.classe ==3){ g.drawImage(voleurpodium.getImage(),450,150,null);;}
					if (J2.classe ==4){ g.drawImage(berserkerpodium.getImage(),450,150,null);;}
					if (J2.classe ==5){ g.drawImage(magepodium.getImage(),450,150,null);;}
				}
				if (J1.estvivant() == false){
					g.drawImage(podiumP2.getImage(),0,-50,null);
					if (J2.classe ==1){ g.drawImage(paladinpodium.getImage(),690,80,null);;}
					if (J2.classe ==2){ g.drawImage(archerpodium.getImage(),690,80,null);;}
					if (J2.classe ==3){ g.drawImage(voleurpodium.getImage(),690,80,null);;}
					if (J2.classe ==4){ g.drawImage(berserkerpodium.getImage(),690,80,null);;}
					if (J2.classe ==5){ g.drawImage(magepodium.getImage(),690,80,null);;}
					if (J1.classe ==1){ g.drawImage(paladinpodium.getImage(),450,150,null);;}
					if (J1.classe ==2){ g.drawImage(archerpodium.getImage(),450,150,null);;}
					if (J1.classe ==3){ g.drawImage(voleurpodium.getImage(),450,150,null);;}
					if (J1.classe ==4){ g.drawImage(berserkerpodium.getImage(),450,150,null);;}
					if (J1.classe ==5){ g.drawImage(magepodium.getImage(),450,150,null);;}
				}
			}
		}
		public String nom (perso J, int i) {
			String nom ="";
			if (i==0){
				if (J.classe ==1){
					nom = "Paladin";
				}
				if (J.classe ==2){
					nom = "Archer";
				}
				if (J.classe ==3){
					nom = "Voleur";
				}
				if (J.classe ==4){
					nom = "Berserker";
				}
				if (J.classe ==5){
					nom = "Mage";
				}
			}
			if (i==1){
				if (J.classe ==1){
					nom = "Tranche, 3 PA , 1 Po";
				}
				if (J.classe ==2){
					nom = "Tir direct , 3 PA , "+J.t[2][3]+" Po";
				}
				if (J.classe ==3){
					nom = "Coudboul, 3 PA , 1 Po ";
				}
				if (J.classe ==4){
					nom = "Frappe, 2 PA , 1 Po";
				}
				if (J.classe ==5){
					nom = "Poussette, 3 PA , 1 Po";
				}
			}
			if (i==2){
				if (J.classe ==1){
					nom = "Renfort, 2 PA , [" +countCooldown(J,0) +"]";
				}
				if (J.classe ==2){
					nom = "Vue percante, 2 PA , [" +countCooldown(J,0) +"]";
				}
				if (J.classe ==3){
					nom = "Jure wallah j'ai pas vole, 4 PA , [" +countCooldown(J,0) +"]";
				}
				if (J.classe ==4){
					nom = "Furie, 3 PA , [" +countCooldown(J,0) +"]";
				}
				if (J.classe ==5){
					nom = "Soin, 4 PA , [" +countCooldown(J,0) +"]";
				}
			}
			if (i==3){
				if (J.classe ==1){
					nom = "Rage, 2 PA , [" +countCooldown(J,1) +"]";
				}
				if (J.classe ==2){
					nom = "Fleche de recul, 3 PA , "+J.t[2][1]+ " Po , [" +countCooldown(J,1) +"]";
				}
				if (J.classe ==3){
					nom = "Esquive de la police, 4 PA , [" +countCooldown(J,1) +"]";
				}
				if (J.classe ==4){
					nom = "Concentation, 3 PA , [" +countCooldown(J,1) +"]";
				}
				if (J.classe ==5){
					nom = "Glyphe, 3 PA , 8 Po , [" +countCooldown(J,1) +"]";
				}
			}
			if (i==4){
				if (J.classe ==1){
					nom = "Impact, 6 PA , 2 Po , [" +countCooldown(J,2) +"]";
				}
				if (J.classe ==2){
					nom = "Tir puissant, 5 PA , "+J.t[2][2]+ " Po , [" +countCooldown(J,2) +"]";
				}
				if (J.classe ==3){
					nom = "Double lame, 6 PA , 1 Po , [" +countCooldown(J,2) +"]";
				}
				if (J.classe ==4){
					nom = "Sacrifice, 5 PA , 1 Po , [" +countCooldown(J,2) +"]";
				}
				if (J.classe ==5){
					nom = "Souffle du dragon, 5 PA , 5 Po , [" +countCooldown(J,2) +"]";
				}
			}
			return nom;
		}
		public int countCooldown (perso J , int sort) {      /* sort = 0 pour le soutien , sort = 1 pour le sort 3 , sort = 2 pour le sort spe*/
			int res = -J.tour + J.t[0][sort] + J.t[1][sort];
			if (res >=0){
				return res;
			}
		return 0;
	}		
}
