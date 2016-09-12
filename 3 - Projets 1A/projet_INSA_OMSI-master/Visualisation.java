import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.ArrayList;
/**
* Visualisation (ensemble des lignes de champ)
*/
public class Visualisation extends JPanel {

  /**
  * Lignes de champ a afficher
  */
  private LigneChamp[] lc = new LigneChamp[0];
    
  public final double yMin=-1;
  public final double yMax=10;
    
  public final double xMin=-1;
  public final double xMax=10;

  public Color[] C = {Color.black,Color.gray,Color.blue,Color.cyan,Color.green,
                  Color.magenta,Color.orange,Color.pink,Color.red,Color.yellow};
  
  // constructeur du graphique
  public Visualisation() {
    super();
  }

  /**
  * @param lc Ligne de champ a ajouter a l'affichage
  * Ajout d'une ligne de champ a l'affichage
  */
  public void addLine(LigneChamp lc) {
    LigneChamp[] tmp = new LigneChamp[this.lc.length+1];
    for (int i = 0; i<this.lc.length ; i++)
      tmp[i] = this.lc[i];
    tmp[this.lc.length] = lc;
    this.lc = new LigneChamp[tmp.length];
    this.lc = tmp;
  }

  /**
  * Dessin
  */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawAxes(g);
    g.setColor(C[1]);
    for (int i=0; i<lc.length; i++)
      drawLine(g, lc[i]);
  }

  /** Pour avoir la meilleure precision possible sur les calculs de toScreen, 
   *  ils sont faits en double precision puis ensuite convertis en int. 
   *  Pour ce qui est des bornes max, width-1 et height-1 correspondent 
   *  respectivement a l'indice d'abscisse et d'ordonnees maximum.
   * @return Coordonnee cartesienne en x convertie en coordonnee adaptee a l'ecran
   */
  private int toScreenX(double x) {
    return (int)((double)((x-xMin)*(getWidth()-1))/(double)((xMax-xMin)));
  }
  private int toScreenY(double y) {
    return getHeight()-1-(int)((double)((y-yMin)*(getHeight()-1))/(double)((yMax-yMin)));
  }
   
  /** Methode dessinant des axes sur un graphics pris en parametre. */
  private void drawAxes(Graphics g){
    g.setColor(new Color(255));
    // Axe horizontal
    g.drawLine(toScreenX(-1),toScreenY(0),toScreenX(10),toScreenY(0));     

    // Axe vertical
    g.drawLine(toScreenX(0),toScreenY(-1),toScreenX(0),toScreenY(10));  
  }

  /**
  * Methode de dessin d'une ligne de champ
  * Affiche successivement tous les points d'une ligne de champ
  */
  private void drawLine(Graphics g, LigneChamp lc) {
    while (lc.getColor()>=C.length)
      lc.setColor(lc.getColor()-C.length);
    g.setColor(C[lc.getColor()]);
    for (int i = 0; i<lc.size(); i++)
      g.drawOval(toScreenX(lc.getPoint(i).x), toScreenY(lc.getPoint(i).y), 3, 3);
  }
}

