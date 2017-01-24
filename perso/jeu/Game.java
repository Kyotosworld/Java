import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final String TITLE = "Pac-Man";
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 700;

    protected Thread thread;
    protected boolean running = false;
    protected Graphics g;
    protected Window w;
    protected Handler handler;

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        handler = new Handler();
        w = new Window(WIDTH, HEIGHT, TITLE, this);

        this.addKeyListener(new EntreeClavier(handler));

        // creation des personnages;
        handler.add(new PacMan(50, 50, ID.PACMAN));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void run() {
        long time0         = System.nanoTime();
        long now           = 0;
        
        double amountTicks = 60.0;
        double delta       = 0;
        
        long timer         = System.currentTimeMillis();
        int frames         = 0;

        this.requestFocus();
        while (running) {
            now = System.nanoTime();
            delta += (now - time0) * 60.0 / 100000000;
            time0 = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();   
            }
            frames++;

            if (System.currentTimeMillis() - timer >= 1000) {
                timer = System.currentTimeMillis();
                System.out.println("FPS : "+frames);
                frames=0;
            }
        }
        System.out.println("Quitting.");
    }

    public void tick() {
        handler.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }


}