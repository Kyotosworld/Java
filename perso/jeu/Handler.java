import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> list = new LinkedList<GameObject>();

    public void add(GameObject go) {
        list.add(go);
    }
    public void remove(GameObject go) {
        list.remove(go);
    }

    public void tick() {
        GameObject temp;
        for (int i=0; i<list.size(); i++) {
            temp = list.get(i);
            temp.tick();
        }
    }

    public void render(Graphics g) {
        GameObject temp;
        for (int i=0; i<list.size(); i++) {
            temp = list.get(i);
            temp.render(g);
        }
    }
}