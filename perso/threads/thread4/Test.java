class Test {

    public static void main(String[] args) {
        lauchLockedFriends2();
    }

    static void lauchFriends() {
        Friend a = new Friend("a");
        Friend b = new Friend("b");
        a.setFriend(b);
        b.setFriend(a);

        new Thread(new Runnable() {
            public void run() {
                a.bow();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                b.bow();
            }
        }).start();

        // Not blocked, but they don't respect any rule: we can't be sure that they behave as expected
    }

    static void lauchSynchronizedFriends() {
        SynchronizedFriend a = new SynchronizedFriend("a");
        SynchronizedFriend b = new SynchronizedFriend("b");
        a.setFriend(b);
        b.setFriend(a);

        new Thread(new Runnable() {
            public void run() {
                a.bow();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                b.bow();
            }
        }).start();

        // Blocked in a deadlock situation
    }

    static void lauchLockedFriends() {
        LockedFriend a = new LockedFriend("a");
        LockedFriend b = new LockedFriend("b");
        a.setFriend(b);
        b.setFriend(a);

        new Thread(new Runnable() {
            public void run() {
                a.bow();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                b.bow();
            }
        }).start();

        // Blocked in a deadlock situation
    }

    static void lauchLockedFriends2() {
        Object lock =new Object();
        LockedFriend a = new LockedFriend("a", lock);
        LockedFriend b = new LockedFriend("b", lock);
        a.setFriend(b);
        b.setFriend(a);

        new Thread(new Runnable() {
            public void run() {
                a.bow();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                b.bow();
            }
        }).start();

        // Blocked in a deadlock situation, with the same lock
    }

    static void lauchOtherFriends() {
        Thread t = Thread.currentThread();
        OtherFriend a = new OtherFriend();
        OtherFriend b = new OtherFriend();
        a.setFriend(b);
        b.setFriend(a);

        System.out.format("Thread %s %s: lauching friends\n", t.getName(), t.getState());
        new Thread(a).start();
        new Thread(b).start();

        // Blocked in a sort of livelock situation
        // they're not really active but they both wait for the other to do something
        // and print messages: they're not blocked by thread synchronization
    }
}