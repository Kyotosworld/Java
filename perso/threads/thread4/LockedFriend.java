class LockedFriend extends Friend {

    Object lock = new Object();

    LockedFriend(String s, Object o) {
        super(s);
        lock = o;
    }
    LockedFriend(String s) {
        super(s);
    }

    @Override
    void bow() {
        System.out.println("Synchronizing on "+lock);
        synchronized (lock) {  
            super.bow();
        }
    }

    @Override
    void bowBack() {
        System.out.println("Synchronizing on "+lock);
        synchronized (lock) {  
            super.bowBack();
        }
    }
}