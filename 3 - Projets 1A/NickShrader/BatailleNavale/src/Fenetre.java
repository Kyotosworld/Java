import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import java.util.ArrayList;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;

public class Fenetre extends JFrame implements AdjustmentListener, ActionListener {

	private JPanel contentPane;
	private RepereCanvas cvJoueur;
	private RepereCanvas cvAdversaire;
	private JLabel lblJoueur;
	private JLabel lblAdversaire;
	private JEditorPane dtrpnMessages;
	private JScrollBar sbXJoueur;
	private JScrollBar sbYJoueur;
	private JScrollBar sbXAdversaire;
	private JScrollBar sbYAdversaire;
	private JButton btnTirer;
	private JButton btnPlacer;
	private JButton btnTourner;

	private boolean horizontal;
	private boolean myturn;
	private boolean last = false;
	private boolean waiting = false;
	private boolean ready = false;
	private Repere joueur;
	private Repere adversaire;
	private ArrayList<ActionListener> actionList = new ArrayList<ActionListener>();

	private Champ bateau = Champ.pa; // Variable d'etat

	/**
	 * Fenetre pricipal du jeu
	 * 
	 * @param title
	 *            Titre du XWindow
	 * @param joueur
	 *            Repere du joueur
	 * @param adversaire
	 *            Repere de l'adversaire
	 */
	public Fenetre(String title, Repere joueur, Repere adversaire, boolean myturn) {
		this.joueur = joueur;
		this.adversaire = adversaire;
		this.myturn = myturn;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(title);
		setBounds(100, 100, 522, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblAdversaire = new JLabel("Adversaire");
		lblAdversaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdversaire.setBounds(12, 11, 220, 14);
		contentPane.add(lblAdversaire);

		dtrpnMessages = new JEditorPane();
		dtrpnMessages.setEditable(false);
		dtrpnMessages.setText("Placez le " + bateau);
		dtrpnMessages.setBounds(12, 320, 500, 44);
		contentPane.add(dtrpnMessages);

		cvAdversaire = new RepereCanvas(adversaire, false);
		cvAdversaire.setBounds(10, 31, 220, 220);
		contentPane.add(cvAdversaire);

		cvJoueur = new RepereCanvas(joueur, true);
		cvJoueur.setBounds(272, 31, 220, 220);
		contentPane.add(cvJoueur);

		sbYAdversaire = new JScrollBar(JScrollBar.VERTICAL, 0, 1, 0, 10);
		sbYAdversaire.addAdjustmentListener(this);
		sbYAdversaire.setBounds(236, 31, 17, 220);
		contentPane.add(sbYAdversaire);

		sbXAdversaire = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 10);
		sbXAdversaire.setEnabled(false);
		sbXAdversaire.addAdjustmentListener(this);
		sbXAdversaire.setBounds(12, 256, 220, 17);
		contentPane.add(sbXAdversaire);

		btnTirer = new JButton("Tirer");
		btnTirer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adversaire.frappe(sbXAdversaire.getValue(), sbYAdversaire.getValue());
				change_etat();
			}
		});
		btnTirer.setEnabled(false);
		btnTirer.setBounds(12, 285, 89, 23);
		contentPane.add(btnTirer);

		sbYJoueur = new JScrollBar(JScrollBar.VERTICAL, 0, 1, 0, 10);
		sbYJoueur.addAdjustmentListener(this);
		sbYJoueur.setBounds(495, 31, 17, 220);
		contentPane.add(sbYJoueur);

		sbXJoueur = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 10);
		sbXJoueur.addAdjustmentListener(this);
		sbXJoueur.setBounds(272, 257, 220, 17);
		contentPane.add(sbXJoueur);

		lblJoueur = new JLabel("Joueur");
		lblJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoueur.setBounds(272, 11, 220, 14);
		contentPane.add(lblJoueur);

		btnPlacer = new JButton("Placer");
		btnPlacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				joueur.placerBateaux(sbXJoueur.getValue(), sbYJoueur.getValue(), horizontal, bateau);
				change_etat();
			}
		});
		btnPlacer.setEnabled(false);
		btnPlacer.setBounds(272, 285, 89, 23);
		contentPane.add(btnPlacer);

		btnTourner = new JButton("Tourner");
		btnTourner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				horizontal = !horizontal;
				cvJoueur.printFantome(
						joueur.placerFantome(sbXJoueur.getValue(), sbYJoueur.getValue(), horizontal, bateau));
				update_controls();
			}
		});
		btnTourner.setBounds(423, 285, 89, 23);
		contentPane.add(btnTourner);
		update_controls();
	}

	private void update_controls() {
		btnTirer.setEnabled(adversaire.valideFrappe(sbXAdversaire.getValue(), sbYAdversaire.getValue())
				&& !bateau.getBateau() && ready && !waiting && myturn);
		btnPlacer.setEnabled(joueur.valideBateaux(sbXJoueur.getValue(), sbYJoueur.getValue(), horizontal, bateau)
				&& bateau.getBateau() && !ready);
		btnTourner.setEnabled(bateau.getBateau() && !ready);
		sbXJoueur.setEnabled(!ready);
		sbYJoueur.setEnabled(!ready);
		sbYAdversaire.setEnabled(ready);
		sbXAdversaire.setEnabled(ready);
		if (joueur.compteBateaux() == 0 && ready) {
			dtrpnMessages.setText("Vous avez perdu!");
			arrete_tout();
		}
		if (adversaire.compteBateaux() != 0 && ready && myturn)
			cvAdversaire.printFantome(adversaire.frappeFantome(sbXAdversaire.getValue(), sbYAdversaire.getValue()));
	}

	private void arrete_tout() {
		btnPlacer.setEnabled(false);
		for (ActionListener al : btnPlacer.getActionListeners())
			btnPlacer.removeActionListener(al);
		btnTirer.setEnabled(false);
		for (ActionListener al : btnTirer.getActionListeners())
			btnTirer.removeActionListener(al);
		btnTourner.setEnabled(false);
		for (ActionListener al : btnTourner.getActionListeners())
			btnTourner.removeActionListener(al);
	}

	private void change_etat() {
		if (bateau == Champ.pa)
			bateau = Champ.crois;
		else if (bateau == Champ.crois)
			bateau = Champ.ctorp;
		else if (bateau == Champ.ctorp)
			bateau = Champ.sm;
		else if (bateau == Champ.sm)
			bateau = Champ.torp1;
		else if (bateau == Champ.torp1)
			bateau = Champ.torp2;
		else if (bateau == Champ.torp2) {
			ready = true;
			if (!last) {
				waiting = true;
				runActionListeners();
			} else
				runActionListeners();
			dtrpnMessages.setText("");
			bateau = Champ.frappe;
		} else if (bateau == Champ.frappe && adversaire.compteBateaux() != 0) {
			bateau = Champ.frappe;
			myturn = !myturn;
			runActionListeners();
			dtrpnMessages.setText("Bateaux restants: " + adversaire.compteBateaux());
		} else if (bateau == Champ.frappe && adversaire.compteBateaux() == 0) {
			bateau = Champ.frappe;
			arrete_tout();
			dtrpnMessages.setText("Vous avez gangné!");
			runActionListeners();
		}
		if (bateau.getBateau())
			dtrpnMessages.setText("Placez le " + bateau);
		update_controls();
	}

	/**
	 * Eventhandler for Scrollbars
	 * 
	 * @param arg0
	 *            What event we are talking about? Let's party, dude?
	 */
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		if (arg0.getSource() == sbXJoueur || arg0.getSource() == sbYJoueur)
			cvJoueur.printFantome(joueur.placerFantome(sbXJoueur.getValue(), sbYJoueur.getValue(), horizontal, bateau));
		else if (arg0.getSource() == sbXAdversaire || arg0.getSource() == sbYAdversaire)
			cvAdversaire.printFantome(adversaire.frappeFantome(sbXAdversaire.getValue(), sbYAdversaire.getValue()));
		update_controls();
	}

	public void setMyTurn(boolean b) {
		myturn = b;
	}

	/**
	 * Add event handler
	 * 
	 * @param l
	 *            ActionListener implemntation
	 */
	public void addActionListener(ActionListener l) {
		actionList.add(l);
	}

	private void runActionListeners() {
		for (ActionListener l : actionList)
			l.actionPerformed(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (ready && !waiting) {
			myturn = !myturn;
			update_controls();
		}
		if (!ready)
			last = true;
		else if (waiting) {
			waiting = false;
			update_controls();
		} else if (!myturn) {
			change_etat();
		}
	}
}
