/**Fenêtre principale du jeu
 */
import javax.swing.JFrame;
public class Frame extends JFrame{
	static Panel panel ;
	static int x = 0 ;
	static keyListener kListener = new keyListener();
	int j1 ;
	int j2;
	public Frame(Plateau monde,perso J1 , perso J2 ,int j1 , int j2) {
		this.panel = new Panel (monde,J1,J2,kListener,J1.classe,this.j2);
		this.setVisible(true);
		this.setSize(1300, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
	}
}

  
  
 
  

