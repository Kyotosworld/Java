import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Curseur {
	
	public int x, y;
	public int xSelect, ySelect;
	public boolean select;
	public int pionSelect;
	public int deplacementSelect;
	
	
	public Curseur () {
		x = 4;
		y = 4;
		xSelect = 0;
		ySelect = 0;
		select = false;
		pionSelect = -1;
		deplacementSelect = -1;
	}
}

