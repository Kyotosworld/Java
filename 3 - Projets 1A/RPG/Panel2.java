/**Classe permettant l'affichage du menu à droite du plateau
 */
import java.awt.*;
import javax.swing.*;
public class Panel2 extends JPanel{
	int j1;
	int j2;
	Plateau monde ;

	public Panel2 (Plateau t) {
		this.monde = t;
	}
	public void paintComponent (Graphics g){
		g.setColor(new Color (255,255,255));
		g.fillRect(0,0,this.getSize().width,this.getSize().height);
		g.setColor(new Color (0,0,0));
		ImageIcon fondmenu = new ImageIcon("photos/fondmenu.png");
		ImageIcon archermenu = new ImageIcon("photos/archermenu.png");
		ImageIcon berserkermenu = new ImageIcon("photos/berserkermenu.png");
		ImageIcon magemenu = new ImageIcon("photos/magemenu.png");
		ImageIcon voleurmenu = new ImageIcon("photos/voleurmenu.png");
		ImageIcon paladinmenu = new ImageIcon("photos/paladinmenu.png");
		g.drawImage(fondmenu.getImage(),0,0,null);	
		if (this.monde.j1==1){
			g.drawImage(paladinmenu.getImage(),225,60,null);	
		}
		if (this.monde.j1==2){
			g.drawImage(archermenu.getImage(),225,60,null);	
		}
		if (this.monde.j1==3){
			g.drawImage(voleurmenu.getImage(),225,60,null);	
		}
		if (this.monde.j1==4){
			g.drawImage(berserkermenu.getImage(),225,60,null);	
		}
		if (this.monde.j1==5){
			g.drawImage(magemenu.getImage(),225,60,null);	
		}
		if (this.monde.j2==1){
			g.drawImage(paladinmenu.getImage(),775,60,null);	
		}
		if (this.monde.j2==2){
			g.drawImage(archermenu.getImage(),775,60,null);	
		}
		if (this.monde.j2==3){
			g.drawImage(voleurmenu.getImage(),775,60,null);	
		}
		if (this.monde.j2==4){
			g.drawImage(berserkermenu.getImage(),775,60,null);	
		}
		if (this.monde.j2==5){
			g.drawImage(magemenu.getImage(),775,60,null);	
		}
	}
}
		
