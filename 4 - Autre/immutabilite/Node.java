class Node {

    private int val;
    private Node ref;

    Node(int i, Node n) {
        val = i;
        ref = n;
    }

    int getVal() {
        return val;
    }
    Node getRef() {
        return ref;
    }
    void setVal(int i) {
        val = i;
    }
    void setRef(Node n) {
        ref = n;
    }

    public String toString() {
        String selfHash = String.valueOf(System.identityHashCode(this));
        String value = String.valueOf(val);
        String refHash = String.valueOf(System.identityHashCode(ref));
        return "(" + value + " [" + selfHash + ":" + refHash + "])";
    }

}
