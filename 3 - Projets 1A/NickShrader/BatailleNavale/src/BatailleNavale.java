import java.awt.EventQueue;
import javax.swing.UIManager;

public class BatailleNavale {
	private static Repere j1;
	private static Repere j2;
	private static Fenetre app_j1;
	private static Fenetre app_j2;

	public static void main(String[] args) {
		j1 = new Repere();
		j2 = new Repere();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app_j1 = new Fenetre("Joueur 1", j1, j2, true);
					app_j1.setVisible(true);
					app_j1.setLocation(0, 0);
					app_j2 = new Fenetre("Joueur 2", j2, j1, false);
					app_j2.setVisible(true);
					app_j2.setLocation(0, 408);
					app_j1.addActionListener(app_j2);
					app_j2.addActionListener(app_j1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
