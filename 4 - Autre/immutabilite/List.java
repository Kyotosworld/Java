final class List {

    private Node[] list;

    List(int length) {
        list = new Node[length];
        list[length-1] = new Node(0, null);
        for(int i = length-2; i>=0; i--) {
            list[i] = new Node(0, list[i+1]);
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
        Node[] temp = new Node[list.length];
        System.arraycopy(list, 0, temp, 0, list.length);
        list = new Node[temp.length+1];
        System.arraycopy(temp, 0, list, 0, temp.length);

        Node newNode = new Node(newElement, null);
        list[list.length-2].setRef(newNode);
        list[list.length-1] = newNode;
    }

    int search(int element) {
        for (int i=0; i<list.length; i++) {
            if (list[i].getVal() == element) {
                return i;
            }
        }
        return -1;
    }

    int pop(int idx) throws ArrayIndexOutOfBoundsException {
        if (idx>=list.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            int valueToReturn = list[idx].getVal();
            Node[] temp = new Node[list.length-1];
            System.arraycopy(list, 0, temp, 0, idx);
            System.arraycopy(list, idx, temp, idx, (list.length-1-idx));
            list = new Node[list.length-1];
            System.arraycopy(temp, 0, list, 0, temp.length);
            return valueToReturn;
        }
    }

    void remove(int idx) throws ArrayIndexOutOfBoundsException {
        if (idx>=list.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            pop(idx);
        }
    }

    void sort() {
        int min = list[0].getVal();
        for (int i=0; i<list.length; i++) {
            
        }
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
