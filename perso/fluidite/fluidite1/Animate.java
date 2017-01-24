import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
 
public class Animate extends JFrame implements Runnable
{
    JPanel panneau = new JPanel();
     
    //Designing the GUI
    public Animate()
    {
        //setBounds(500, 200, 500, 500);
        setSize(500, 500);
        setTitle("Animation");
        setContentPane(panneau);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //panneau.setBackground(Color.ORANGE);
        setVisible(true);
    }

	public void run() {
	  int x = 0;
	  int y = 250;
      boolean bX = false;
      boolean bY = true;
	 
	  Graphics g = panneau.getGraphics();
	  Image offscreen = createImage(getWidth(), getHeight());
	  Graphics buffered = offscreen.getGraphics();
	 
	  while(true) {
        if(x == 0 || x+50 == getWidth())
            bX = !bX;
        if(y == 0 || y+50 == getHeight())
            bY = !bY;
        
        if(bX)
            x++;
        else
            x--;
        if(bY)
            y++;
        else
            y--;

        System.out.println(x+" "+getWidth()+" "+y+" "+getHeight());
	    buffered.setColor(Color.WHITE);
	    buffered.fillRect(0, 0, getWidth(), getHeight());
	    buffered.setColor(Color.RED);
	    buffered.fillOval(x, y, 50, 50);
		     
	    try {
	      Thread.sleep(20);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
		     
	    g.drawImage(offscreen, 0, 0, this);
	  }
	}
    public static void main(String args[])
    {
         
        Animate animation = new Animate();
         
        Thread t = new Thread(animation);
        t.start();
    }
}
