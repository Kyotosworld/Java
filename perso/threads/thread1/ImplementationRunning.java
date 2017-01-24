class ImplementationRunning implements Runnable {

    boolean isRunning = true;

    public ImplementationRunning() {
        System.out.println("THREAD "+Thread.currentThread().getId()+" : New implementation created");
    }

    public void run() {
        System.out.println("THREAD "+Thread.currentThread().getId()+" : running");
        long t0Animer = System.nanoTime();
        double deltaAnimer = 0;
        long frequenceAnimer = 2;
        double periodeAnimer = 1000000000/frequenceAnimer;

        long t0FPS = System.nanoTime();
        double deltaFPS = 0;
        double frequenceFPS = 1;
        double periodeFPS = 1000000000/frequenceFPS;
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
                //System.out.println("FPS : "+fps);
                deltaFPS -= periodeFPS;
                fps=0;
            }

            if (Thread.interrupted()) {
                System.out.println("THREAD "+Thread.currentThread().getId()+" : interrupted, we quit run() fonction");
                // it might be more serious to throw a InterruptedException
                // throw new InterruptedException();
                return;
            }

            afficher();
            fps++;
        }
    }

    public void animer() {
        System.out.println("THREAD "+Thread.currentThread().getId()+" : animer");
    }
    public void afficher() {
    }

    public static void main(String[] args) {
        System.out.println("Hello from method main, Thread "+Thread.currentThread().getId());
        (new Thread(new ImplementationRunning())).start();
    }
}