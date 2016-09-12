import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.UIManager;
import java.util.Scanner;

public class OMSI_Info {


    public static double [] initialisationTeta (double [] teta, double pasCircu){
        int i = 0;
        while (i < teta.length){
            teta[i] = Math.PI - (pasCircu*i);
            i++;
        }
        return teta;
    }
    
    public static double [][] initialisationXy (double [][] xy, double pasCircu, double R, double [] teta){
        int i = 0;
        while (i < xy.length){
            xy[i][0] = R * Math.cos(teta[i]) + R;
            xy[i][1] = R * Math.sin(teta[i]);
            i++;
        }
        return xy;
    }
    
    public static double [][] initialisationFxfy (double [][] fxfy, double pasCircu, double [][] xy){
        int i = 0;
        while (i<fxfy.length){
            fxfy[i][0] = xy[i][0]*xy[i][1]*pasCircu;
            fxfy[i][1] = (xy[i][1] + 2)*pasCircu;
            i++;
        }
        return fxfy;
    }
    
    public static void main(String[] args) {
		
        Scanner sc= new Scanner (System.in);
        System.out.println("Preferes tu voir les lignes de champs de façon numerique (0) ou plutot de facon analytique (1) ou encore veux tu regarder la circulation (2) ou alors jouer a la Planete INSA (3) ?");
        int val = sc.nextInt();
        
        /** Cas 0 : Numerique **/
        
        if (val==0){
            FenetrePrincipale frame = new FenetrePrincipale();
            Visualisation courbe = frame.getCourbe();
            double x0 = 0;
            double y0 = -2;
            double pas = 0.0001;
            double b = 15.;
            int nbPoint = (int)(b-y0);
            
            System.out.println("Bonjour, combien de lignes de champs voulez vous ? ");
            int nbLignes = sc.nextInt();
            System.out.println("Xo ?");
            x0 = sc.nextDouble();
            if (x0 < 0.5){
                x0 = 0.5;
                System.out.println("x0 ne doit pas être inferieur a 0.5. x0 = 0.5");
            }
            System.out.println("Yo ?");
            y0 = sc.nextDouble();
            if (y0 < -1){ // y0 ne doit pas être proche de la constante : 2.
                y0 =-1;
                System.out.println("y0 ne doit pas etre proche de la constante : 2. y0 = -2.1");
            }
            
            LigneDeForce [] tabLignes = new LigneDeForce [nbLignes]; // On cree un tableau avec autant de lignes de forces que demande par l'utilisateur.
            int i = 0;
            while (i< nbLignes){
                tabLignes [i] = new LigneDeForce (b, nbPoint, pas, 2, x0 + 0.2 * i, y0);
                i++;
            }
            
            /* On implemente 3 tableaux avec les valeurs de x, y et la couleur selon la ligne de force pour, ensuite, utiliser 1 seule fois la methode dessine
             * de la classe Visualisation. Celle-ci ne pouvant être utilise qu'une seule fois, sinon elle efface tout.
             */
            
            i = 0;
            double [] x = new double [(int)(nbPoint/pas)*nbLignes]; 
            double [] y = new double [(int)(nbPoint/pas)*nbLignes];
            int [] color = new int [(int)(nbPoint/pas)*nbLignes];
            int u = 0;
            while (u < nbLignes){
                i=0;
                while (i < (int)(nbPoint/pas)){
                    x[u*(int)(nbPoint/pas) + i] = tabLignes[u].ligne[i].Fx;
                    y[u*(int)(nbPoint/pas) + i] = tabLignes[u].ligne[i].Fy;
                    color[u*(int)(nbPoint/pas) + i] = u;
                    i++;
                }
                u++;
            }
            courbe.dessine(x,y,color);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
            frame.setVisible(true);
        }
        
        /** Cas 1 : Analytique **/
        
        else if (val==1){
            FenetrePrincipale frame = new FenetrePrincipale();
            Visualisation courbe = frame.getCourbe();
            double a = -2.;
            double b = 15.;
            double pas = 0.001;
            int nbPoint = (int)(b-a);
            
            System.out.println("Bonjour, combien de lignes de champs voulez vous ? ");
            int nbLignes = sc.nextInt();
            LigneDeForce [] tabLignes = new LigneDeForce [nbLignes]; // On cree un tableau avec autant de lignes de forces que demande par l'utilisateur.
            int i = 0;
            while (i< nbLignes){
                tabLignes [i] = new LigneDeForce (a, b, i*2 +1, nbPoint, pas);
                i++;
            }
            
            /* On implemente 3 tableaux avec les valeurs de x, y et la couleur selon la ligne de force pour, ensuite, utiliser 1 seule fois la methode dessine
             * de la classe Visualisation. Celle-ci ne pouvant être utilise qu'une seule fois, sinon elle efface tout.
             */
            
            i = 0;
            double [] x = new double [(int)(nbPoint/pas)*nbLignes];
            double [] y = new double [(int)(nbPoint/pas)*nbLignes];
            int [] color = new int [(int)(nbPoint/pas)*nbLignes];
            int u = 0;
            while (u < nbLignes){
                i=0;
                while (i < (int)(nbPoint/pas)){
                    x[u*(int)(nbPoint/pas) + i] = tabLignes[u].ligne[i].Fx;
                    y[u*(int)(nbPoint/pas) + i] = tabLignes[u].ligne[i].Fy;
                    color[u*(int)(nbPoint/pas) + i] = u;
                    i++;
                }
                u++;
            }
            courbe.dessine(x,y,color);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
            frame.setVisible(true);
        }
        
        /** Partie Circulation **/
        
        else if (val == 2){
            double pasCircu = 0.000001;
            double R = 10;
            double [] teta = new double [(int)(Math.PI / (2*pasCircu))];
            double [][] xy = new double [(int)(Math.PI / (2*pasCircu))][2];
            double [][] fxfy = new double [(int)(Math.PI / (2*pasCircu))][2];
        
            teta = initialisationTeta(teta, pasCircu);
            xy = initialisationXy(xy, pasCircu, R, teta);
            fxfy = initialisationFxfy(fxfy, pasCircu, xy);
            int i = 0;
            double somme = 0;
            while (i<teta.length){
                somme = somme + ((fxfy[i][0] * Math.sin(teta[i]) - fxfy[i][1] * Math.cos(teta[i])) * R);
                i++;
            }
            double valTh = Math.pow(R,3)*(Math.PI/4 - (1./3)) + Math.pow(R,2)/2 + 2 * R;
            System.out.println("La circulation vaut : "+somme); 
            System.out.println("La valeur théorique est : "+valTh);     
        }
        
        /** Partie Planete **/
        
        else if (val == 3){
            FenetrePrincipaleBis frame = new FenetrePrincipaleBis(); // Classe modifie pour aller avec notre programme
            VisualisationBis courbe = frame.getCourbe(); // idem
            double xPlanete = 3;
            double yPlanete = 3;
            double mPlanete = 1000000;
            double xVaisseau = 0;
            double xV = 0;
            double yV = 0;
            double yVaisseau = 0;
            double vVaisseau = 50;
            double angleVaisseau = 1.2;
            double mVaisseau = 10;
            double pas = 0.01;
            double pasT = 0.0001;
            int i = 0;
            double temps = 30;
            while (i < (int)(temps/pasT) && xV < 15 && yV < 15){
                try {
                    Thread.sleep(10);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                xV = xVaisseau;
                yV = yVaisseau;
                if (xV < xPlanete && yV < yPlanete){ // Zone bas gauche
					xVaisseau = ((mPlanete * Math.pow(i*pasT,2))/(2*Math.pow(Math.cos(Math.atan((3-yV)/(3-xV)))*Math.sqrt(Math.pow((xPlanete-xV)/pas,2)+Math.pow((yPlanete - yV)/pas,2)),2))) + vVaisseau * Math.cos(angleVaisseau)*(i*pasT); // Resultat analytique pour la position du vaisseau en x.
					yVaisseau = ((mPlanete * Math.pow(i*pasT,2))/(2*Math.pow(Math.sin(Math.atan((3-yV)/(3-xV)))*Math.sqrt(Math.pow((xPlanete-xV)/pas,2)+Math.pow((yPlanete - yV)/pas,2)),2))) + vVaisseau * Math.sin(angleVaisseau)*(i*pasT); // Resultat analytique pour la position du vaisseau en y.
				}
				else if (xV >= xPlanete && yV <= yPlanete){ // Zone bas droite
					xVaisseau = (-1*(mPlanete * Math.pow(i*pasT,2))/(2*Math.pow(Math.cos(Math.acos(((3-xV))/Math.sqrt(Math.pow((xPlanete-xV)/pas,2)+Math.pow((yPlanete - yV)/pas,2))))*Math.sqrt(Math.pow((xPlanete-xV)/pas,2)+Math.pow((yPlanete - yV)/pas,2)),2))) + vVaisseau * Math.cos(angleVaisseau)*(i*pasT);
					yVaisseau = ((mPlanete * Math.pow(i*pasT,2))/(2*Math.pow(Math.sin(Math.acos((3-xV)/Math.sqrt(Math.pow((xPlanete-xV)/pas,2)+Math.pow((yPlanete - yV)/pas,2))))*Math.sqrt(Math.pow((xPlanete-xV)/pas,2)+Math.pow((yPlanete - yV)/pas,2)),2))) + vVaisseau * Math.sin(angleVaisseau)*(i*pasT);
				}
				else if (xV >= xPlanete && yV > yPlanete){ // Zone haut droite
					xVaisseau = (-1*(mPlanete * Math.pow(i*pasT,2))/(2*Math.pow(Math.cos(Math.atan((3-yV)/(3-xV)))*Math.sqrt(Math.pow((xPlanete-xV)/pas,2)+Math.pow((yPlanete - yV)/pas,2)),2))) + vVaisseau * Math.cos(angleVaisseau)*(i*pasT);
					yVaisseau = (-1*(mPlanete * Math.pow(i*pasT,2))/(2*Math.pow(Math.sin(Math.atan((3-yV)/(3-xV)))*Math.sqrt(Math.pow((xPlanete-xV)/pas,2)+Math.pow((yPlanete - yV)/pas,2)),2))) + vVaisseau * Math.sin(angleVaisseau)*(i*pasT);
				}
				else if (xV < xPlanete && yV >= yPlanete){ // Zone haut gauche
					xVaisseau = ((mPlanete * Math.pow(i*pasT,2))/(2*Math.pow(Math.cos(Math.atan((3-yV)/(3-xV)))*Math.sqrt(Math.pow((xPlanete-xV)/pas,2)+Math.pow((yPlanete - yV)/pas,2)),2))) + vVaisseau * Math.cos(angleVaisseau)*(i*pasT);
					yVaisseau = (-1*(mPlanete * Math.pow(i*pasT,2))/(2*Math.pow(Math.sin(Math.atan((3-yV)/(3-xV)))*Math.sqrt(Math.pow((xPlanete-xV)/pas,2)+Math.pow((yPlanete - yV)/pas,2)),2))) + vVaisseau * Math.sin(angleVaisseau)*(i*pasT);
				}
                System.out.println(xVaisseau);
                System.out.println(yVaisseau);
                courbe.dessine(xPlanete,yPlanete,xVaisseau, yVaisseau);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
                frame.setVisible(true);
                i++;
            }
        }
        
        else {
            System.out.println("Il fallait mettre 0 ou 1 ou 2 ou 3...");
        }  
    }
}
