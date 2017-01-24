class Friend {
    private String name="";
    private Friend friend;

    Friend(String s) {
        name = s;
    }
    String getName() {
        return name;
    }
    void setFriend(Friend f) {
        friend = f;
    }

    void bow() {
        Thread t = Thread.currentThread();
        System.out.format("Thread %s %s: Friend %s bowing to %s\n",  t.getName(), t.getState(), this, friend);
        friend.bowBack();
    }

    void bowBack() {
        Thread t = Thread.currentThread();
        System.out.format("Thread %s %s: Friend %s bowing back to %s\n",  t.getName(), t.getState(), this, friend);
    }
}