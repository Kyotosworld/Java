class SynchronizedFriend extends Friend {

    SynchronizedFriend(String s) {
        super(s);
    }

    @Override
    synchronized void bow() {
        super.bow();
    }

    @Override
    synchronized void bowBack() {
        super.bowBack();
    }
}