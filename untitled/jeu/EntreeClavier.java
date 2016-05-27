import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EntreeClavier extends KeyAdapter {

    private Handler handler;

    public EntreeClavier(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        GameObject pacman = handler.list.get(0);

        switch(key) {
            case KeyEvent.VK_UP:
                if (pacman.getVY() == 0)
                    pacman.setVY(1);
            break;
            case KeyEvent.VK_DOWN:
            break;
            case KeyEvent.VK_LEFT:
            break;
            case KeyEvent.VK_RIGHT:
            break;
        }
    }
    public void keyReleased(KeyEvent event) {}
    public void keyTyped(KeyEvent event) {}
}