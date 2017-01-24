class Test {

    public class Counter {
        protected int c=0;
        public int n=10000;
        public Counter(int n) {
            this.n = n;
        }
        public Counter() {}
        public int getC() {
            return c;
        }
        public void increment() {
            int cTemp = c;
            for (int i=0; i<n; i++) {
                c++;
                if (c == cTemp+1) {
                    cTemp++;
                } else {
                    System.out.println("THREAD "+Thread.currentThread().getId()+": erreur incrementation: c = "+cTemp +" -> "+c);
                }
            }
        }
        public void decrement() {
            int cTemp = c;
            for (int i=0; i<n; i++) {
                c--;
                if (c == cTemp-1) {
                    cTemp--;
                } else {
                    System.out.println("THREAD "+Thread.currentThread().getId()+": erreur decrementation: c = "+cTemp +" -> "+c);
                }
            }        }
    }

    public class CounterSynchrone extends Counter {
        public CounterSynchrone(int n) {
            super(n);
        }
        public CounterSynchrone() {
            super();
        }
        @Override
        public synchronized int getC() {
            return super.getC();
        }
        @Override
        public synchronized void increment() {
            super.increment();
        }
        @Override
        public synchronized void decrement() {
            super.decrement();       
        }
    }

    public static boolean testNonGaranti() {
        Test.Counter cInMain = new Test().new Counter();

        // les deux opérations sont lancées en même temps
        Thread t = new HeritageThread("ModifieCounter", cInMain);
        t.start();
        cInMain.increment();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Resultat: " + (cInMain.getC() == 0));      // potentiellement false
        return cInMain.getC() == 0;
    }

    public static boolean testGaranti1() {
        Test.Counter cInMain = new Test().new Counter();

        // une opération est faite avant que l'autre soit lancée par start()
        cInMain.increment();
        Thread t = new HeritageThread("ModifieCounter", cInMain);
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Resultat: " + (cInMain.getC() == 0));      // true
        return cInMain.getC() == 0;
    }

    public static boolean testGaranti2() {
        Test.Counter cInMain = new Test().new Counter();

        // une opération est arrêtée avec join() avant que l'autre soit exécutée
        Thread t = new HeritageThread("ModifieCounter", cInMain);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cInMain.increment();

        System.out.println("Resultat: " + (cInMain.getC() == 0));      // true
        return cInMain.getC() == 0;
    }

    public static boolean testGaranti3() {
        Test.CounterSynchrone csInMain = new Test().new CounterSynchrone();

        // les deux opérations sont lancées en même temps sur un object synchronisé
        Thread t = new HeritageThread("ModifieCounter", csInMain);
        t.start();
        csInMain.increment();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Resultat: " + (csInMain.getC() == 0));     // true
        return csInMain.getC() == 0;
    }

    public static void main(String[] args) {
        //System.out.println(t.getName()+": "+t.getState()+"\t\t\t"+Thread.currentThread().getName()+": "+Thread.currentThread().getState());
        while (testGaranti3());
    }
}