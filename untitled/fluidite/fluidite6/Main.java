//package untitled.resolution;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NumberFormatException;

public class Main {

	public static final int SIZE_Y_CONST = 37;

	public static int sizeX;
	public static int sizeY;
	public static int resol;

    public static void main(String[] args) throws java.util.InputMismatchException {
        try {
            sizeX = Integer.parseInt(args[0], 10);
            sizeY = Integer.parseInt(args[1], 10);
            resol = Integer.parseInt(args[2], 10);
            if(sizeX < 100 || sizeY < 100 || resol < 1)
                throw new InputMismatchException();
        } catch (ArrayIndexOutOfBoundsException |
                 InputMismatchException |
                 NumberFormatException err) {
            System.out.println("Usage: java untitled.resolution.Panneau tailleX tailleY resolution");
            System.out.println("Où tailleX, tailleY et resolution sont des entiers positifs");
            System.exit(1);
        }

        JFrame f = new JFrame();
        f.setTitle("untitled.resolution");
        f.setSize(sizeX, sizeY+SIZE_Y_CONST);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Boule b = new Boule(20);

        Panneau p = new Panneau(b);
        f.setContentPane(p);
        f.setVisible(true);

        System.out.println();
        mainLoop(p, b);
    }

    public static void mainLoop(Panneau p, Boule b) {
    	boolean state = false;

    	while(true) {
     		if (b.getX()+b.getR() == p.getWidth() || b.getX() == 0) {
     			state = !state;
     		}
     		if (state) {
            	b.setX(b.getX()+1);
            	b.setY(b.getY()+1);
            } else {
            	b.setX(b.getX()-1);
            	b.setY(b.getY()-1);
            }
            p.repaint();            

	    		try {
	            	Thread.sleep(15);
	            } catch (InterruptedException err) {
	            	err.printStackTrace();
	            	System.exit(2);
	            }
        }    
    }

}
