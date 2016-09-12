/**Fenêtre du menu
 */
import javax.swing.JFrame;


public class Frame2 extends JFrame{
	static Panel2 panel ;
	static int x = 0 ;
	static keyListener kListener = new keyListener();
	int j1 ;
	int j2;
	
	public Frame2(Plateau monde) {
		this.panel = new Panel2 (monde);
		this.setVisible(true);
		this.setSize(1300, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		

	
	}
}
