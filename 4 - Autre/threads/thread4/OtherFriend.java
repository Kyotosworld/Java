class OtherFriend implements Runnable {
    private String state="";
    private String friendState = "";
    private OtherFriend friend;

    OtherFriend() {
        state = "standing up";
    }

    public void run() {
        long t0 = System.nanoTime();
        long s10 = 1000000000;          // 1s
        //s10 *= 10;

        Thread t = Thread.currentThread();
        System.out.format("Thread %s %s: OtherFriend %s %s\n",  t.getName(), t.getState(), this, state);
        System.out.format("Thread %s %s: OtherFriend %s %s\n",  t.getName(), t.getState(), friend, friend.getState());

        state = "bowing";
        while (true) {
            if (friendState.equals("")) {
                System.out.format("OtherFriend %s %s: thread %s %s\n", this, state, t.getName(), t.getState());
                friendState = friend.getState();
            }
            else if (friend.getState().equals("bowing") && friendState.equals("standing up")) {
                friendState = "bowing";
            }
            else if (friend.getState().equals("standing up") && friendState.equals("bowing")) {
                System.out.println("BREAK OUT");
                break;
            }
            else if (friend.getState().equals("standing up") && friendState.equals("standing up")) {
                System.out.format("PROBLEM: friend.getState() = %s  friendState = %s\n", friend.getState(), friendState);
                System.out.format("         no one is bowing down (thread %s)", t.getName());
            }
            else if (friend.getState().equals("bowing") && friendState.equals("bowing")) {
                if (System.nanoTime() - t0 > s10) {
                    t0 = System.nanoTime();
                    System.out.println("Both are bowing: livelock state");
                }
            }
        }
        state = "standing up";

        System.out.format("Thread %s %s: OtherFriend %s %s\n",  t.getName(), t.getState(), this, state);
        System.out.format("Thread %s %s: OtherFriend %s %s\n",  t.getName(), t.getState(), friend, friend.getState());
    }

    String getState() {
        return state;
    }
    void setFriend(OtherFriend f) {
        friend = f;
    }
}