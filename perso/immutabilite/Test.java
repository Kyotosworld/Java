class Test {

    public static void main(String[] args) {
        ImmutableList l = new ImmutableList(5);
        System.out.println(l);
        l.append(5);
        System.out.println(l);
    }
}