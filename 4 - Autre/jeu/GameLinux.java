import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.Image;

public class GameLinux extends Game {

    private Image image;

    public GameLinux() {
        w = new Window(WIDTH, HEIGHT+37, TITLE, this);
    }

    @Override
    public void render() {
        if (image == null) {
            image = createImage(WIDTH, HEIGHT);
        }

        Graphics g = image.getGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH, HEIGHT);

        handler.render(g);

        g.drawImage(image, 0, 0, this);
    }
}