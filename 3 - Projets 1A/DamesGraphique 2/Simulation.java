import java.util.Scanner;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.io.*; 
import java.awt.geom.*; 
import java.awt.image.*; 
import javax.imageio.ImageIO;

public class Simulation extends JFrame{
    
    Image wallpaper;
    Image  noir1;
    Image  blanc1;
    Image noir2;
    Image blanc2;
    Damier d;
    
    /*Decider l'ordre de joureurs aleatoirement,cad c'est qui commence au debut,et la convention est que les pions noirs commencent a deplacer au debut*/
    public static boolean DeciderOrdre(){
        boolean quiUtiliseNoir;
        double num=Math.random();
        if(num<0.5){
            System.out.println("Le joueur 1 utilise les pions noirs ,et le joueur 2 utilise les pions blancs.");
            quiUtiliseNoir=true;
        }
        else{
            System.out.println("Le joueur 1 utilise les pions blancs ,le joueur 2 utilise les pions noirs.");
            quiUtiliseNoir=false;
        }
        return quiUtiliseNoir;
    }
    
    
    public Simulation () {
        setTitle("Jeu de dames");
        setSize(800, 800);
        setLocation(0,0);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        Toolkit T=Toolkit.getDefaultToolkit(); 
        wallpaper = T.getImage ("damier.jpg");
        noir1=T.getImage ("noir.jpg");
        blanc1=T.getImage ("blanc.jpg");
        noir2=T.getImage ("noir2.jpg");
        blanc2=T.getImage("blanc2.jpg");

        
        
        d=new Damier();
        boolean quiUtiliseNoir=DeciderOrdre();
        boolean victoire=false;
        Scanner reader=new Scanner(System.in);
        int tDeNoir=0;
        int tDeBlanc=0;
        int t=0;


        /*examiner*/
        
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                d.plateau[i][j].viOuMo=false;
            }
        }
        d.plateau[1][1].viOuMo=true;
        d.plateau[3][3].viOuMo=true;
        d.plateau[5][5].viOuMo=true;    
        d.plateau[7][7].viOuMo=true;
        d.plateau[9][9].viOuMo=true;    
        d.plateau[1][1].noirOuBlanc=true;
        d.plateau[3][3].noirOuBlanc=false;
        d.plateau[5][5].noirOuBlanc=true;   
        d.plateau[7][7].noirOuBlanc=true;
        d.plateau[9][9].noirOuBlanc=false;
        d.plateau[9][9].sireOuDame=false;       
        d.plateau[7][7].sireOuDame=false;       
		
        /*examiner*/
            
        
        
        flag5:while(victoire==false){
            t=t+1;
            /*la partie importer*/
            /*c'est pour obtenir les donnees de la position importe par le joueur*/
            
            
            /*l'affichage NOirBlanc*/
            
            for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    if(d.plateau[i][j].viOuMo==true){
                        if(d.plateau[i][j].noirOuBlanc==true&&d.plateau[i][j].sireOuDame==true){
                            System.out.print("N1");
                        }
                        if(d.plateau[i][j].noirOuBlanc==false&&d.plateau[i][j].sireOuDame==true){
                            System.out.print("B1");
                        }
                        if(d.plateau[i][j].noirOuBlanc==true&&d.plateau[i][j].sireOuDame==false){
                            System.out.print("N2");
                        }
                        if(d.plateau[i][j].noirOuBlanc==false&&d.plateau[i][j].sireOuDame==false){
                            System.out.print("B2");
                        }
                    }
                    else{
                        System.out.print("//");
                    }
                    if(j==9){
                        System.out.println();

                    }
                }
            }
            
            repaint();
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            boolean v=true;
            int abscisseDePion=0;
            System.out.println("Importez l'ordonnee du pion que le joueur 1 veut deplacer ");
            while(v==true){
                try{
                    abscisseDePion=reader.nextInt()-1;//si ca marche pas,on entre dans catch et rentrer dans cette circulation
                    v=false;
                }
                catch(Exception e){
                    System.out.println("C'est faux, reimporter svp!");
                    reader.nextLine();
                }
            }
                
            int ordonneeDePion=0;       
            boolean n=true;
            System.out.println("Importez l'abscisse du pion que le joueur 1 veut deplacer ");
            while(n==true){
                try{
                    ordonneeDePion=reader.nextInt()-1;
                    n=false;
                }
                catch(Exception e){
                    System.out.println("C'est faux, reimporter svp!");
                    reader.nextLine();
                }
            }
                
            int abscisseArriver=0;
            boolean m=true;
            System.out.println("Importez l'ordonnee ou le joueur 1 veut arriver ");
            while(m==true){
                try{
                    abscisseArriver=reader.nextInt()-1;
                    m=false;
                }
                catch(Exception e){
                    System.out.println("C'est faux, reimporter svp!");
                    reader.nextLine();
                }
            }
                
                
            boolean y=true;
            int ordonneeArriver=0;
            System.out.println("Importez l'abscisse ou le joueur 1 veut arriver ");
            while(y==true){
                try{
                    ordonneeArriver=reader.nextInt()-1;
                    y=false;
                }
                catch(Exception e){
                    System.out.println("C'est faux, reimporter svp!");
                    reader.nextLine();
                }
            }
            
            
            if(((abscisseDePion==abscisseArriver)||(ordonneeDePion==ordonneeArriver))||(abscisseDePion>11)||(abscisseDePion<0)||(ordonneeDePion>11)||(ordonneeDePion<0)||(abscisseArriver>11)||(abscisseArriver<0)||(ordonneeArriver<0)||(ordonneeArriver>11)||(d.plateau[abscisseDePion][ordonneeDePion].viOuMo==false)||(d.plateau[abscisseArriver][ordonneeArriver].viOuMo==true)){//si les donnes sont inutiles,il faut reimporter et ce fois de la circulation est inutile aussi
                t=t-1;
                System.out.println("Réimporter");
                continue;
            }
                    /*partie importer*/
            
            
            
        
            /*la partie de determiner si il est possible de prendre la capture continue*/
            boolean commentDeplacer;
            System.out.println("Commencer avanceSpire");
            System.out.println();
            commentDeplacer=d.avanceSpire(abscisseDePion,ordonneeDePion,abscisseArriver,ordonneeArriver);
            if(commentDeplacer==false){
                System.out.println("avanceSpire echoue");
                System.out.println();
                System.out.println("Commencer avanceDame");
                System.out.println();
                commentDeplacer=d.avanceDame(abscisseDePion,ordonneeDePion,abscisseArriver,ordonneeArriver);
                if(commentDeplacer==false){
                    System.out.println("avanceDame echoue");
                    System.out.println();
                    System.out.println("Commencer captureSpire");
                    System.out.println();
                    commentDeplacer=d.captureSpire(abscisseDePion,ordonneeDePion,abscisseArriver,ordonneeArriver);
                    if(commentDeplacer==false){
						System.out.println("captureSpire echoue");
						System.out.println();
                        System.out.println("Commencer captureDame");
                        commentDeplacer=d.captureDame(abscisseDePion,ordonneeDePion,abscisseArriver,ordonneeArriver);
                        if(commentDeplacer==true){//si c'est une dame,ici c'est pour juger si la capture continue est possible
                            flag:for(int i=0;i<10;i++){
                                for(int j=0;j<10;j++){
                                    if((j!=abscisseArriver)&&(i!=ordonneeArriver)){
                                        Damier p=new Damier();
                                        for(int k=0;k<10;k++){
                                            for(int q=0;q<10;q++){
                                                p.plateau[k][q].viOuMo=d.plateau[k][q].viOuMo;
                                                p.plateau[k][q].sireOuDame=d.plateau[k][q].sireOuDame;
                                                p.plateau[k][q].noirOuBlanc=d.plateau[k][q].noirOuBlanc;
                                            }
                                        }
                                        //System.out.println("position wd'examiner");
                                        boolean acceptabilite2=p.avanceDame(abscisseArriver,ordonneeArriver,i+2*((abscisseArriver-i)/Math.abs(abscisseArriver-i)),j+2*((ordonneeArriver-j)/Math.abs(ordonneeArriver-j)));
                                        if(acceptabilite2==true){
                                            if((p.plateau[abscisseArriver][ordonneeArriver].sireOuDame==false)&&(((j-ordonneeArriver)/(i-abscisseArriver)==1)||(j-ordonneeArriver)/(i-abscisseArriver)==-1)&&(p.plateau[abscisseArriver][ordonneeArriver].noirOuBlanc!=p.plateau[i+((abscisseArriver-i)/Math.abs(abscisseArriver-i))][j+((ordonneeArriver-j)/Math.abs(ordonneeArriver-j))].noirOuBlanc) ){
                                                System.out.println("Vous pouvez encore capturer! Importez les donnees!");
                                                break flag;
                                            }
                                        }
                                    }       
                                }
                            }
                        }
                    }
                    else{//si c'est une sire
                        flag2:for(int i=0;i<10;i++){
                            for(int j=0;j<10;j++){
                                if((j!=abscisseArriver)&&(i!=ordonneeArriver)){
                                    Damier p=new Damier();
                                    for(int k=0;k<10;k++){
                                        for(int q=0;q<10;q++){
                                            p.plateau[k][q].viOuMo=d.plateau[k][q].viOuMo;
                                            p.plateau[k][q].sireOuDame=d.plateau[k][q].sireOuDame;
                                            p.plateau[k][q].noirOuBlanc=d.plateau[k][q].noirOuBlanc;
                                        }
                                    }
                                
                                    System.out.println(abscisseArriver);
                                    
                                    System.out.println(ordonneeArriver);
                                    System.out.println(j);
                                    if((p.plateau[abscisseArriver][ordonneeArriver].sireOuDame==true)&&(((j-ordonneeArriver)/(i-abscisseArriver)==1)||(j-ordonneeArriver)/(i-abscisseArriver)==-1)&&(p.plateau[(abscisseArriver+i)/2][(ordonneeArriver+j)/2].viOuMo==true)&&(d.plateau[i][j].viOuMo==false)&&(p.plateau[(abscisseArriver+i)/2][(ordonneeArriver+j)/2].noirOuBlanc!=p.plateau[abscisseArriver][ordonneeArriver].noirOuBlanc)){
                                        System.out.println("Vous pouvez encore capturer! Importez les donnees!");
                                        break flag2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            
            
            
            /*la partie de juger la victoire:deux cas:1.les pions de la meme couleurs sont tous ete captures 2.tous les pions de memes couleurs ne peuvent pas deplacer*/
            int nombreDeNoir=0;
            int nombreDeBlanc=0;
            for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    if(d.plateau[i][j].viOuMo==true){
                        if(d.plateau[i][j].noirOuBlanc==true){
                            nombreDeNoir=nombreDeNoir+1;
                        }
                        else{
                            nombreDeBlanc=nombreDeBlanc+1;
                        }
                    }
                }
            }
            if(nombreDeNoir==0){
                victoire=true;
                System.out.println("Le Blanc a gagne!");
                break flag5;
            }
            if(nombreDeBlanc==0){
                victoire=true;
                System.out.println("Le Noir a gagne!");
                break flag5;
            }
            
            /*juger si les pions noir ou les pions blancs peuvent encore deplacer,si non le jeu est termine*/
            boolean noirNeBougePas=true;
            boolean blancNeBougePas=true;
            flag3:while(noirNeBougePas==true){//le cas de noir
                for(int i=0;i<10;i++){
                    for(int j=0;j<10;j++){
                        if(d.plateau[i][j].noirOuBlanc==true){
                            for(int a=0;a<10;a++){
                                for(int b=0;b<10;b++){
                                    if(i!=a&&j!=b){//eviter la faute du denominateur 0
                                        if(d.plateau[i][j].sireOuDame==true){//si le pion est une sire
                                            if((a==i+1)&&((b==j-1)||(b==j+1))&&(d.plateau[a][b].viOuMo==false)){
                                                noirNeBougePas=false;
                                                break flag3;
                                            }
                                        }
                                        else{//si c'est une dame
                                            if((((b-j)/(a-i)==1)||(b-j)/(a-i)==-1)){
                                                boolean acceptabilite2=true;
                                                int q=i;
                                                int p=j;
                                                while(Math.abs(q-a)!=0){
                                                    q=q+((a-i)/Math.abs(a-i));
                                                    p=p+((b-j)/Math.abs(b-j));
                                                    if(d.plateau[q][p].viOuMo==true){
                                                        acceptabilite2=false;
                                                    }
                                                }
                                                if(acceptabilite2==true){//si acceptabilite2 est encore true apres cette circulation,cad cette dame peut encore deplacer a l'ailleur,le jeu n'est pas encore fini
                                                    noirNeBougePas=false;
                                                    break flag3;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            
            flag4:while(blancNeBougePas==true){//le cas de blanc
                for(int i=0;i<10;i++){
                    for(int j=0;j<10;j++){
                        if(d.plateau[i][j].noirOuBlanc==false){
                            for(int a=0;a<10;a++){
                                for(int b=0;b<10;b++){
                                    if(i!=a&&j!=b){
                                        if(d.plateau[i][j].sireOuDame==true){
                                            if((a==i-1)&&((b==j-1)||(b==j+1))&&(d.plateau[a][b].viOuMo==false)){
                                                blancNeBougePas=false;
                                                break flag4;
                                            }
                                        }
                                        else{
                                            if((((b-j)/(a-i)==1)||(b-j)/(a-i)==-1)){
                                                boolean acceptabilite2=true;
                                                int q=i;
                                                int p=j;
                                                while(Math.abs(q-a)!=0){
                                                    q=q+((a-i)/Math.abs(a-i));
                                                    p=p+((b-j)/Math.abs(b-j));
                                                    if(d.plateau[q][p].viOuMo==true){
                                                        acceptabilite2=false;
                                                    }
                                                }
                                                if(acceptabilite2==true){//si acceptabilite2 est encore true apres unecirculation,cad cette dame peut encore deplacer a l'ailleur,le jeu n'est pas encore fini
                                                    noirNeBougePas=false;
                                                    break flag4;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
                                            
    
    
    
            if(noirNeBougePas==true){
                victoire=true;
                System.out.println("Le Blanc a gagne!");
                break flag5;
            }
            if(noirNeBougePas==true){
                victoire=true;
                System.out.println("Le Noir a gagne!");
                break flag5;
            }
            /*la partie d'affichage*/
            //Affichage......
            System.out.println("t="+t);
            System.out.println("nombreDeBlanc="+nombreDeBlanc);
            System.out.println("nombreDeNoir="+nombreDeNoir);
        }
        System.out.println("Le jeu est determine");
    }
    
    
    public void paint (Graphics g) {
        g.drawImage(wallpaper,0,0,this);
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(d.plateau[i][j].viOuMo==true){
                    if((d.plateau[i][j].noirOuBlanc==true)&&(d.plateau[i][j].sireOuDame==true)){
                        g.drawImage (noir1,80*j,80*i,this);
                    }
                    if((d.plateau[i][j].noirOuBlanc==false)&&(d.plateau[i][j].sireOuDame==true)){
                        g.drawImage (blanc1,80*j,80*i,this);
                    }
                    if((d.plateau[i][j].noirOuBlanc==true)&&(d.plateau[i][j].sireOuDame==false)){
                        g.drawImage (noir2,80*j,80*i,this);
                    }
                    if((d.plateau[i][j].noirOuBlanc==false)&&(d.plateau[i][j].sireOuDame==false)){
                        g.drawImage (blanc2,80*j,80*i,this);
                    }
                }
            }   
        }
    }
    
    
    
    
    
    
    
    
    public static void main (String [] args) {
            Simulation s=new Simulation();
    }
    
}   
    
                            
                        
