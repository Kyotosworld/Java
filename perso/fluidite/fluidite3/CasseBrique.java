 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
import javax.swing.*;
import java.awt.*;
 
public class CasseBrique {
 
    public CasseBrique() {
 
        JFrame frame = new JFrame("Casse brique v1.0");
        frame.setSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jeu jeu = new Jeu(100, 100, 2, 0);
        frame.add(jeu);
        frame.setVisible(true);
        jeu.init();
    }
 
    public static void main(String[] arg) {
        // Lancement dans l'EDT.
        SwingUtilities.invokeLater(new Runnable() {
 
            /**
             * {@inheritDoc}
             */
            @Override
            public void run() {
                new CasseBrique();
            }
        });
    }
}
