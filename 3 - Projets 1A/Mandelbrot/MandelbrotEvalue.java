import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
public class MandelbrotEvalue extends JFrame {

   private final int WIDTH = 800;
   private final int HEIGHT = 600;
   private BufferedImage fractal;
   private double zoom = 150;
   private int maxIter = 500;


   private double xPos = WIDTH / 2;
   private double yPos = HEIGHT / 2;

   private double zx; 
   private double zy;
   private double cX;
   private double cY;
   private double t;
   
   public static void main(String[] args) {

        new MandelbrotEvalue().setVisible(true);
   }
   
     public MandelbrotEvalue(){
        super("Mandelbrot Set");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        ComputeFractal();
     }

     public void ComputeFractal(){
        fractal = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < fractal.getHeight(); y++) {
             for (int x = 0; x < fractal.getWidth(); x++) {
                     zx = zy = 0;
                     cX = (x - xPos)/zoom;
                     cY = (y - yPos)/zoom;
                     int iter;
                  for (iter = 0; iter < maxIter&&zx * zx + zy * zy < 4; iter++) {
                      t = zx * zx - zy * zy + cX;
                      zy = 2.0 * zx * zy + cY;
                      zx = t;
                  }

                   if (iter == maxIter) {
                      Color color = new Color(0, 0, 0);
                      fractal.setRGB(x, y, color.getRGB());
                   }else{
                      double r = iter | (iter << 2);
                      while (r > 255) 
                          { r-=255; }
                      double g = iter | (iter << 4);
                      while (g > 255) 
                          { g-=255; }
                      double b = iter | (iter << 8); 
                      while (b > 255) 
                          { b-=255; }
                          
                         Color color = new Color((int) r, (int) g, (int) b);
                         fractal.setRGB(x, y, color.getRGB());
                   }
             }
       }
                         repaint();
    }
      public void paint(Graphics g) {

           g.drawImage(fractal, 0, 0, this);
      }
      
      
}
