import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Visualisation extends JPanel {
    
    public double [] x;
    public double [] y;
    public int [] color;
  
    public static double yMin=-1;
    public static double yMax=10;
    
    public static double xMin=-1;
    public static double xMax=10;
        
    public static int numeroCouleur=0;

    public Color[] C = {Color.black,Color.gray,Color.blue,Color.cyan,Color.green,
                    Color.magenta,Color.orange,Color.pink,Color.red,Color.yellow};
        
    public void dessine(double [] x, double [] y, int [] color){
		this.x = x; // Tableau comportant toutes les valeurs de x de toutes les lignes de forces.
        this.y = y; // Tableau comportant toutes les valeurs de y de toutes les lignes de forces (meme emplacement que la valeur de x associee dans le tableau x).
        this.color = color; // Tableau comportant la couleur pour differencier les lignes de forces (meme emplacement que la valeur de x et y associee dans les tableaux x et y).
        repaint();
    }
	
    // constructeur du graphique
    public Visualisation() {
        super();
	}
		
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAxes(g);
        int i = 0;
        while (i<x.length){
            g.setColor(C[color[i]]);
            g.drawOval(toScreenX(x[i]), toScreenY(y[i]), 2, 2);
            i++;
        }          	
	}


  
  private int toScreenX(double x) {
     return (int)((double)((x-xMin)*(getWidth()-1))/(double)((xMax-xMin)));
  }

  private int toScreenY(double y) {
    return getHeight()-1-(int)((double)((y-yMin)*(getHeight()-1))/(double)((yMax-yMin)));
  }
   
    private void drawAxes(Graphics g){
    g.setColor(new Color(0,125,0));
    // Axe horizontal
      g.drawLine(toScreenX(-1),toScreenY(0),toScreenX(10),toScreenY(0));     

    // Axe vertical
      g.drawLine(toScreenX(0),toScreenY(-1),toScreenX(0),toScreenY(10));  
  }
}

