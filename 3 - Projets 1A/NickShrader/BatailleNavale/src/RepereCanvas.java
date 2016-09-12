import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepereCanvas extends Canvas implements ActionListener {
	private Repere repere;
	private boolean self;
	private boolean undrawn = true;

	/**
	 * AWT-Canvas pour dessiner un repere.
	 * 
	 * @param r
	 *            repere a dessiner
	 * @param self
	 *            c'est le repere du joueur? (Ou de l'adversaire?)
	 */

	public RepereCanvas(Repere r, boolean self) {
		repere = (r != null) ? r : new Repere(); // Evite des problemes avec
													// WindowBuilder
		repere.addActionListener(this);
		this.self = self;
	}

	/**
	 * Function auxiliere pour dessier avec des instructions elementaires
	 * 
	 * @param g
	 *            destination, nomarlement le Graphics du Canvas
	 * @param c
	 *            champ a dessiner, soit celui du repere, soir un champ fantome
	 */
	private void draw(Graphics g, Champ[][] c) {
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.CYAN);
		g.fillRect(1, 1, 218, 218);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 219, 219);
		g.fillRect(0, 0, 20, 20);
		for (int i = 1; i <= 11; i++) {
			g.drawLine(20, 20 * i, 219, 20 * i);
			g.drawLine(20 * i, 20, 20 * i, 219);
		}
		for (int i = 0; i <= 9; i++) {
			g.drawChars(new char[] { (char) ('A' + i) }, 0, 1, 20 * i + 23, 17);
			g.drawChars(Integer.toString(i).toCharArray(), 0, 1, 5, 20 * i + 17);
		}
		g.drawChars(Integer.toString(10).toCharArray(), 0, 2, 0, 217);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (c[i][j].getBateau() && self) {
					g.setColor(Color.GRAY);
					g.fillRect(20 * (i + 1), 20 * (j + 1), 20, 20);
				} else if (c[i][j] == Champ.touche) {
					g.setColor(Color.RED);
					g.fillRect(20 * (i + 1), 20 * (j + 1), 20, 20);
				} else if (c[i][j] == Champ.frappe) {
					g.setColor(Color.WHITE);
					g.fillOval(20 * (i + 1), 20 * (j + 1), 20, 20);
				} else if (c[i][j] == Champ.fantome) {
					g.setColor(Color.BLUE);
					g.fillRect(20 * (i + 1), 20 * (j + 1), 20, 20);
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		if (self && undrawn)
			draw(g, repere.placerFantome(0, 0, false, Champ.pa));
		else
			draw(g, repere.getRepere());
		undrawn = false;
	}

	@Override
	public void update(Graphics g) {
		draw(g, repere.getRepere());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		paint(this.getGraphics());
	}

	public void printFantome(Champ[][] c) {
		draw(this.getGraphics(), c);
	}
}
