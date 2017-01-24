public class Test {

    public static void main (String[] args) throws InterruptedException {
        System.out.println("THREAD "+Thread.currentThread().getId()+" : Creating new thread");

        Thread a = new Thread(new ImplementationRunning());
        System.out.println("THREAD "+Thread.currentThread().getId()+" : Starting new thread");

        a.start();
        double t0 = System.currentTimeMillis();

        System.out.println("THREAD "+Thread.currentThread().getId()+" : Beginning loop: ");
        while(a.isAlive()) {
            System.out.println("THREAD "+Thread.currentThread().getId()+" : Trying to join a");
            a.join(1000);
            if (System.currentTimeMillis() - t0 > 10000) {
                System.out.println("THREAD "+Thread.currentThread().getId()+" : INTERRUPTING a");
                a.interrupt();
                System.out.println("THREAD "+Thread.currentThread().getId()+" : Trying to join a");
                a.join();
                System.out.println("THREAD "+Thread.currentThread().getId()+" : a joined");
            }
        }
    }
}