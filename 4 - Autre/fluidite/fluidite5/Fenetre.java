//package untitled.resolution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NumberFormatException;

public class Fenetre extends JFrame {

	public final int SIZE_Y_CONST = 37;

	public int sizeX = 500;
	public int sizeY = 500;
	public int resol;

    Boule b;
    Panneau p;

    public static void main(String[] args) {
        Fenetre f = new Fenetre();
    }


    public Fenetre() {
        this.setTitle("untitled.resolution");
        this.setSize(sizeX, sizeY);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.b = new Boule(20);
        this.p = new Panneau(b);

        this.setContentPane(p);
        this.setVisible(true);

        this.mainLoop();
    }

    public void mainLoop() {
    	boolean state = false;
        long time0 = System.currentTimeMillis();
        long time;
        long frames = 0;

        Image offscreen = createImage(sizeX, sizeY);
        Graphics buffered = offscreen.getGraphics();

    	while(true) {
     		if (this.b.getX()+this.b.getR() == this.sizeX || this.b.getX() == 0) {
     			state = !state;
     		}
     		if (state) {
            	this.b.setX(this.b.getX()+1);
            	this.b.setY(this.b.getY()+1);
            } else {
            	this.b.setX(this.b.getX()-1);
            	this.b.setY(this.b.getY()-1);
            }
            this.p.paintComponent(buffered);
            this.p.getGraphics().drawImage(offscreen, 0, 0, this);

    		time = System.currentTimeMillis();
    		frames++;
    		if (time - time0 >= 30) {
    			System.out.println("lol");
    			time0 = System.currentTimeMillis();
    			p.fps = frames;
    			frames = 0;
    		} else {
    			System.out.println("lil");
	    		try {
	    			System.out.println(30 - time + time0);
	            	Thread.sleep(30 - time + time0);
	            } catch (InterruptedException err) {
	            	err.printStackTrace();
	            	System.exit(2);
	            }
    		}
        }    
    }

}
