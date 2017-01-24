class Test {

    public static void main(String[] args) {
        launchObjects();
    }

    static void launchObjects() {
        Objects generator = new Objects();
        Objects.Data data = generator.new Data();

        Objects.Consumer[] arr = new Objects.Consumer[5];
        for (int i=0; i<5; i++)
            arr[i] = generator.new Consumer(data);

        Objects.Retriever retriever = generator.new Retriever(arr, data);

        for (Objects.Consumer c: arr) {
            c.start();
        }
        retriever.start();
    }
}