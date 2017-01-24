class ImmutableList {

    private ImmutableNode[] list;

    ImmutableList(int length) {
        // we create an array filled with 0
        // where every Node points to the next
        // last Node points to null
        list = new ImmutableNode[length];
        list[length-1] = new ImmutableNode(0, null);
        for(int i = length-2; i>=0; i--) {
            list[i] = new ImmutableNode(0, list[i+1]);
        }
    }

    int get(int idx) throws ArrayIndexOutOfBoundsException {
        if (idx>=list.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            return list[idx].getVal();
        }
    }

    void append(int newElement) {
        // create a new array of size n+1 into list
        // and copy everything into it
        ImmutableNode[] temp = new ImmutableNode[list.length];
        System.arraycopy(list, 0, temp, 0, list.length);
        ImmutableNode[] list = new ImmutableNode[temp.length+1];
        System.arraycopy(temp, 0, list, 0, temp.length);

        ImmutableNode newImmutableNode = new ImmutableNode(newElement, null);
        list[list.length-2] = new ImmutableNode(list[list.length-2].getVal(), newImmutableNode);
        list[list.length-1] = newImmutableNode;
    }

    public String toString() {
        String s = "{ ";
        for (int i=0; i<list.length-1; i++) {
            s += list[i].toString() + ",\n";
        }
        s += list[list.length-1].toString() + " }";
        return s;
    }
}
