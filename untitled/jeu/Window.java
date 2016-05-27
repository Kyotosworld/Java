import java.awt.Canvas;
import javax.swing.JFrame;

public class Window extends Canvas {

    public Window(int width, int height, String name, Game game) {
        JFrame window = new JFrame();

        window.setTitle(name);
        window.setSize(width, height);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.add(game);
        window.setVisible(true);
        game.start();
    }
}