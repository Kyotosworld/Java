class HeritageThread extends Thread {

    boolean isRunning = true;

    /*public HeritageThread() {
        System.out.println("Hello from constructor");
    }*/

    public void start() {
        System.out.println("Hello from method start");
        isRunning = true;
    }

    public void run() {
        System.out.println("Hello from method run: " + isRunning);
        long t0Animer = System.nanoTime();
        double deltaAnimer = 0;
        long frequenceAnimer = 100;
        double periodeAnimer = 1000000000/frequenceAnimer;

        long t0FPS = System.nanoTime();
        double deltaFPS = 0;
        long frequenceFPS = 1;
        double periodeFPS = 1000000000;
        int fps = 0;
        
        long t;

        while (isRunning) {
            t = System.nanoTime();
            deltaAnimer += t - t0Animer;
            t0Animer = t;
            while (deltaAnimer >= periodeAnimer) {
                animer();
                deltaAnimer -= periodeAnimer;
            }

            deltaFPS += t - t0FPS;
            t0FPS = t;
            if (deltaFPS >= periodeFPS) {
                System.out.println("FPS : "+fps);
                deltaFPS -= periodeFPS;
                fps=0;
            }

            afficher();
            fps++;
        }
    }

    public void animer() {}
    public void afficher() {
        //System.out.println("Hello from a Thread");
    }

    public static void main(String[] args) {
        System.out.println("Hello from method main");
        new HeritageThread().start();
    }
}