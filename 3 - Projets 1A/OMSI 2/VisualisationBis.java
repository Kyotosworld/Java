import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class VisualisationBis extends JPanel {
    
    public double xPlanete;
    public double yPlanete;
    public double xV;
    public double yV;
  
    public static double yMin=-1;
    public static double yMax=10;
    
    public static double xMin=-1;
    public static double xMax=10;
        
    public static int numeroCouleur=0;

    public Color[] C = {Color.black,Color.gray,Color.blue,Color.cyan,Color.green,
                    Color.magenta,Color.orange,Color.pink,Color.red,Color.yellow};
        
    public void dessine(double x, double y, double xV, double yV){
		this.xPlanete = x; 
        this.yPlanete = y;
        this.xV = xV;
        this.yV = yV;
        repaint();
    }
	
    // constructeur du graphique
    public VisualisationBis() {
        super();
	}
		
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlanete(g);
        g.setColor(new Color(0,10,0));
        g.drawOval(toScreenX(xV), toScreenY(yV), 5, 5);
	}


  
  private int toScreenX(double x) {
     return (int)((double)((x-xMin)*(getWidth()-1))/(double)((xMax-xMin)));
  }

  private int toScreenY(double y) {
    return getHeight()-1-(int)((double)((y-yMin)*(getHeight()-1))/(double)((yMax-yMin)));
  }
   
    private void drawPlanete(Graphics g){
    g.setColor(new Color(0,125,0));
    g.drawOval(toScreenX(xPlanete), toScreenY(yPlanete), 15, 15);
  }
}

