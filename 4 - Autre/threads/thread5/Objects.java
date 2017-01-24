import java.util.Random;

class Objects {

    class Data {
        Random r = new Random();
        private int n;
        // true if data is ready to be updated, or is being updated
        // false if data is ready to be retrieved, or is being retrieved
        private boolean empty = true;

        synchronized int getN() {
            Thread t = Thread.currentThread();
            while(empty) {
                try {
                    System.out.format("Thread %s\t\tConsumer waiting...\n", t.getName());
                    wait();
                    System.out.format("Thread %s\t\tConsumer woken up\n", t.getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // toggle status: it's safe because we own the intrinsic lock at this moment
            empty = true;
            notifyAll();
            System.out.format("Thread %s\t\tConsumer got %d\n", t.getName(), n);
            return n;
        }

        synchronized void setN() {
            Thread t = Thread.currentThread();
            while(!empty) {
                try {
                    System.out.format("Thread %s\t\tRetriever waiting...\n", t.getName());
                    wait();
                    System.out.format("Thread %s\t\tRetriever woken up\n", t.getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // toggle status: it's safe because we own the intrinsic lock at this moment
            empty = false;
            notifyAll();

            n = r.nextInt(11);
            System.out.format("Thread %s\t\tRetriever set n to %d\n", t.getName(), n);
        }
    }

    class Consumer extends Thread {
        Data data;

        Consumer(Data d) {
            data = d;
        }

        @Override
        public void run() {
            Thread t = Thread.currentThread();
            System.out.format("Thread %s\t\tConsumer %s has retrieved: data = %d\n", t.getName(), this, data.getN());
        }
    }

    class Retriever extends Thread {
        Consumer[] consumers;
        Data data;

        Retriever(Consumer[] c, Data d) {
            consumers = c;
            data = d;
        }

        @Override
        public void run() {
            Thread t = Thread.currentThread();

            for (Consumer c: consumers) {
                System.out.format("Thread %s\t\tRetriever notes that consumer %s is  %s\n", t.getName(), c, c.getState());
                data.setN();
                System.out.format("Thread %s\t\tAfter setting, retriever notes that consumer %s is  %s\n", t.getName(), c, c.getState());
            }
        }
    }
}