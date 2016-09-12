import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetrePrincipale extends JFrame {
    private BorderLayout layoutMain;
    private JButton jButton_TraceChampGradient;
    private JPanel PanelVisu;
    private BorderLayout borderVisu;
    private Visualisation courbe;

    public FenetrePrincipale() {
		BorderLayout layoutMain = new BorderLayout();
		jButton_TraceChampGradient = new JButton("Circulation");
		PanelVisu = new JPanel();
		borderVisu = new BorderLayout();

		courbe = new Visualisation();
	    this.getContentPane().setLayout(layoutMain);
        this.setSize(new Dimension(600,600));
        this.setTitle("Circulation de champs et champs de forces");
       
        PanelVisu.setSize(new Dimension(485, 183));
        PanelVisu.setLayout(borderVisu);
        PanelVisu.add(jButton_TraceChampGradient,BorderLayout.SOUTH);
       
        PanelVisu.add(courbe, BorderLayout.CENTER);
        this.getContentPane().add(PanelVisu, BorderLayout.CENTER);
       
    }

	public Visualisation getCourbe(){
		return courbe;
	}

}
