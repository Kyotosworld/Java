import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener {
    private BorderLayout layoutMain;
    private JButton jButton_TraceChampGradient;
    private JPanel PanelVisu;
    private BorderLayout borderVisu;
    public Visualisation visu;

    public Fenetre() {
		BorderLayout layoutMain = new BorderLayout();
		jButton_TraceChampGradient = new JButton("Circulation");
		PanelVisu = new JPanel();
		borderVisu = new BorderLayout();

		visu = new Visualisation();
	    this.getContentPane().setLayout(layoutMain);
        this.setSize(new Dimension(600,600));
        this.setTitle("Circulation de champs et champs de forces");
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
       
        PanelVisu.setSize(new Dimension(485, 183));
        PanelVisu.setLayout(borderVisu);
        PanelVisu.add(jButton_TraceChampGradient,BorderLayout.SOUTH);
        jButton_TraceChampGradient.addActionListener(this);
       
        PanelVisu.add(visu, BorderLayout.CENTER);
        this.getContentPane().add(PanelVisu, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent arg0) {
        Force.calculerCirculation();
    }

}
