/**Classe permettant de détecter la saisie d'une touche du clavier avec la fenêtre java au premier plan
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class keyListener implements KeyListener{
	public int action ;
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	if(e.getKeyCode() == 38) this.action = 8;
	if(e.getKeyCode() == 40) this.action = 5;
	if(e.getKeyCode() == 101) this.action = 5;
	if(e.getKeyCode() == 37) this.action = 7;
	if(e.getKeyCode() == 39) this.action = 9;
	if(e.getKeyCode() == 97) this.action = 1;
	if(e.getKeyCode() == 98) this.action = 2;
	if(e.getKeyCode() == 99) this.action = 3;
	if(e.getKeyCode() == 100)this.action = 4;
	if(e.getKeyCode() == 32) this.action = 6;	
	}
	public void keyTyped(KeyEvent e){
	}
}
