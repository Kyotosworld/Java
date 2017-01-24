final class ImmutableNode {

    private final int val;
    private final ImmutableNode ref;

    ImmutableNode(int i, ImmutableNode n) {
        val = i;
        ref = n;
    }

    // copy-class constructor
    // allows to create a copy of a ImmutableNode object: same properties but different references
    ImmutableNode(ImmutableNode n) {
        val = n.getVal();
        ref = n.getRef();
    }

    int getVal() {
        return val;
    }

    // returns a defensive copy instead of the actual ref: same properties but different references
    ImmutableNode getRef() {
        // there is an endless loop
        return new ImmutableNode(ref);
    }

    public String toString() {
        String selfHash = String.valueOf(System.identityHashCode(this));
        String value = String.valueOf(val);
        String refHash = String.valueOf(System.identityHashCode(ref));
        return "(" + value + " [" + selfHash + ":" + refHash + "])";
    }
}
