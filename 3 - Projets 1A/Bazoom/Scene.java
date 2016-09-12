
// Numero 2 :
// Ici on importe toutes les classes de base permettant ..


import java.awt.Color; // La couleur du texte
import java.awt.Font; // La police 
import java.awt.Graphics; // La variable Graphics

import javax.swing.ImageIcon; // Les variable images
import javax.swing.JPanel; // La methode JPanel








public class Scene extends JPanel{
	static Personnage p1 = new Personnage(500,500); // Nouveau personnage de position 500 en x et en y.
	static Controller c = new Controller(); // Nouveau controller.
	static int niveau = 1; // Initialise le niveau a 1.
	static int compteur =0; // Le compteur de monstre tues utile pour le passage de niveau.
    static int compteur2=0; // Le compteur du deuxieme type de monstre utile pour le passage de niveau.
	static int Mort = 0; // Le nombre de monstres tues.
	
	
	public void paintComponent(Graphics g){
	
        g.setFont(new Font("Joystix", Font.PLAIN, 18)); // On cree une police de type JoyStix de taille 18 qui n'est pas sur l'ordinateur mais c'est pas grave.
        g.setColor(new Color(0,0,0)); // On dit que les couleurs du texte qui suit sera noir.
        g.drawImage(new ImageIcon("map.png").getImage(), 0, -10, null); // On dessine la Map provenant de l'image map.png à une postion  x = 0, y=-10.
        
        
        //On utilise une petite astuce avec la variable i qui boucle pour lancer le jeu et faire clignote la phrase tant que le joueur n'a pas appuye sur p.
        if(Main.i>30&& Clavier.p!=1)
            g.drawString("Appuez sur P pour commencer une partie ! ", 500, 300);
        
        /*
          Une petite boucle pour ajouter l'image des coeurs en fonction de la vie du joueur, pour faire afficher les coeurs cote a cote on ajouter 15 * i
          au x.
        */
        
        for(int i =0; i<p1.vie;i++){
            g.drawImage(new ImageIcon("coeur.png").getImage(), 450+i*15, 0, null);
            
        
        }
        
        
        // Affiche le mot niveau suivit du niveau dans lequel est le joueur. On utilise Integer.toString pour transformer le int niveau en String et l'afficher.
        g.drawImage(new ImageIcon("niveau.png").getImage(), 135, -50, null);
        g.drawString(Integer.toString(niveau), 290, 15);
        
        
        // Pareil mais avec le nombre d'ennemi mort.
        g.drawImage(new ImageIcon("tue.png").getImage(), 1100, -50, null);
        g.drawString(Integer.toString(Mort), 1225, 18);
        
        
        
	
        /*
            Ici on utilise un if pour générer le nombre de monstres en fonction du niveau et uniquement quand ils sont tous mort.
            De plus, on ajoute une vie au joueur et on monte d'un niveau.
        */
        if(niveau == compteur || niveau-9==compteur2){
            
            compteur2=0;
            compteur=0;
            niveau++;
            p1.vie++;
            
            while(niveau!=c.getM().size())
            Scene.c.ajouterMonstre(new Monstre((int) (40+Math.random()*1135),(int) (Math.random()*565)));
            
            if(niveau>10){
                while(niveau-9 !=c.getM2().size())
                Scene.c.ajouterMonstre2(new Monstre2((int) (40+Math.random()*1135),(int) (Math.random()*565)));
            }
            
            
        }
  
	
	
	
        // On applique la methode draw du controller ( voir plus loin )
        c.draw(g);
        
        // Si le personne est encore en vie on le dessine, sinon on affiche l'image GAMEOVER avec le niveau atteint et le nombre de monstre tues.
        if(Personnage.m==0){
            p1.Draw(g);
        }else{
            g.drawImage(new ImageIcon("gameover.jpg").getImage(), -150, -50, null);
            g.setColor(new Color(254,254,254));
            g.drawString("Niveau : "+Integer.toString(niveau), 400, 600);
            g.drawString("Monstres tués : "+Integer.toString(Mort), 850, 600);
        }
	
	
	
	}
	
	
}
