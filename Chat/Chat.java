import java.awt.Color;

public class Chat {
    private int age;
    private Color yeux;
    private Color fourrure;
    private int mental;

    public static final int TROP_HEUREUX = 4;
    public static final int HEUREUX = 3;
    public static final int PARTIELLEMENT_HEUREUX = 2;
    public static final int MALHEUREUX = 1;

    public static void main(String[] args) {
        String[] tableau = {"azerty", "uiop"};
        Chat monChat = new Chat(1, Color.BLUE, Color.RED, Chat.PARTIELLEMENT_HEUREUX);
        System.out.println("Mon chat est "+monChat.getMental());
        Chat monDeuxiemeChat = new Chat(1, Color.BLACK, Color.YELLOW);
        System.out.println("Mon chat est "+monDeuxiemeChat.getMental());
//        monDeuxiemeChat.main(tableau);                                                      // java.lang.StackOverflowError
    }

    public Chat(int age, Color yeux, Color fourrure) {
        this(age, yeux, fourrure, Chat.HEUREUX);
    }

    public Chat(int age, Color yeux, Color fourrure, int mental) {
        this.age = age;
        this.yeux = yeux;
        this.fourrure = fourrure;
        this.mental = mental;
    }

    public int getMental() {
        return mental;
    }

    public void setMental(int nouveauMental) {
        this.mental = nouveauMental;
    }
}
