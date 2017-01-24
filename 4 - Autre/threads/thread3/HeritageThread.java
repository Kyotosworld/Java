class HeritageThread extends Thread {
    Test.Counter cInThread;

    public HeritageThread(String name, Test.Counter c) {
        super(name);
        cInThread = c;
    }

    public void run() {
        cInThread.decrement();
    }
}