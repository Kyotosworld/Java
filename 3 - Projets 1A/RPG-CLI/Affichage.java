import java.util.Scanner;

/**
* Cette classe s'occupe d'afficher tout ce que l'utilisateur peut voir sur l'interface du terminal.
*/
public class Affichage {
    private static final int TAILLE_TERMINAL=80;
    private static char[][][] patterns={
        {
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
        },
        {
            {'|','|','|','|'},
            {'|','|','|','|'},
            {'|','|','|','|'},
        },
        {
            {'0','0','0','0'},
            {'0','0','0','0'},
            {'0','0','0','0'},
        },
        {
            {'x',' ',' ','x'},
            {' ','x','x',' '},
            {'x',' ',' ','x'},
        },
        {
            {' ','O',' ','1'},
            {'-','H','-',' '},
            {'/',' ','\\',' '},
        },
        {
            {' ','O',' ','2'},
            {'-','H','-',' '},
            {'/',' ','\\',' '},
        }
    
    };
    /** 
     * La méthode plateau est une méthode de type void, permettant d'obtenir l'affichage du plateau.
     * @param p est un paramètre de type int[][] se rapportant au plateau sur lequel les deux joueurs vont évoluer.
     * @param numJoueurTour est un objet de type entier permettant de connaitre quel joueur effectue ses actions durant ce tour.
     * @param joueurs est un tableau d'entier 1D de type Personnage, contenant les objets de tous les personnages joueurs. 
     * @param  statutPersonnages, il s'agit d'un tableau contenant les PV, PA et PM des personnages à l'action précédente pour obtenir les différences de PV, PA et PM d'un personnage entre deux actions.
     * @see Plateau #table
     */
     
    public static void plateau(int[][] p,int numJoueurTour,Personnage[] joueurs,int[][] statutPersonnages) {

        int[][] plateau = Portee.recopierTableau(p);
        
        
        for(int i=0;i<joueurs.length;i++) {
            plateau[joueurs[i].getposX()][joueurs[i].getposY()]=4+joueurs[i].numJoueur;
        }

        
        viderEcran();
        
		System.out.print("  -");
		for(int i=0;i<plateau[0].length;i++)
			System.out.print("--"+(char)(65+i)+"--");
		System.out.println();
		
        for(int j=0;j<plateau.length;j++) {
            
            
            
            for(int i=0;i<3;i++) {
                for(int k=0;k<plateau[0].length;k++) {
                    if(k==0 && i==1 && (j+1)<10)
                        System.out.print("  "+(j+1));
                    else if(k==0 && i==1 && (j+1)<100)
                        System.out.print(" "+(j+1));
                    else if(k==0)
                        System.out.print("  |");
                    for(int l=0;l<4;l++)
                        if(plateau[j][k]>=0)
                            System.out.print(patterns[plateau[j][k]][i][l]);
                        else
                            System.out.print(patterns[0][i][l]);
                    System.out.print("|");
                }
                System.out.println();
            }
            
            System.out.print("  -");
            for(int i=0;i<plateau[0].length;i++)
                System.out.print("-----");
            System.out.println();
        }
        
        
        for(int i=0;i<joueurs.length;i++) {
            System.out.print("|  Joueur "+(i+1)+" ("+Codex.nomsTypes[joueurs[i].type]+")");
            if(numJoueurTour==i)
				System.out.print(" -> Ton tour !");
			System.out.println();
            System.out.print("|  PV : "+joueurs[i].pv);
            if(statutPersonnages[i][0]!=joueurs[i].pv)
				System.out.print(" (diff: "+(joueurs[i].pv-statutPersonnages[i][0])+")");
			System.out.println();
            System.out.print("|  PA : "+joueurs[i].pa);
            if(statutPersonnages[i][1]!=joueurs[i].pa)
				System.out.print(" (diff: "+(joueurs[i].pa-statutPersonnages[i][1])+")");
			System.out.println();
            System.out.print("|  PM : "+joueurs[i].pm);
            if(statutPersonnages[i][2]!=joueurs[i].pm)
				System.out.print(" (diff: "+(joueurs[i].pm-statutPersonnages[i][2])+")");
			System.out.println();
            //System.out.println("|  Position : "+joueurs[i].getposX()+" "+joueurs[i].getposY());
            System.out.println();
			statutPersonnages[i][0]=joueurs[i].pv;
			statutPersonnages[i][1]=joueurs[i].pa;
			statutPersonnages[i][2]=joueurs[i].pm;
            /*
            for(int j=0;j<joueurs[i].pa;j++)
                System.out.print("*");
            System.out.println();
            System.out.print("|  <");
            for(int j=0;j<joueurs[i].pv/4;j++)
                System.out.print("=");
            System.out.print(">\tPM : ");
            for(int j=0;j<joueurs[i].pm;j++)
                System.out.print("*");
            System.out.println();
            System.out.println("|");
            */
        }  
        
        System.out.println("|  COMPETENCES");
        
        for(int i=1;i<joueurs[numJoueurTour].competences.length;i++) {
            System.out.print("|  "+(i)+" ("+Codex.sorts[joueurs[numJoueurTour].competences[i]][5]+" PA)\t : (portée : "+Codex.sorts[joueurs[numJoueurTour].competences[i]][8]+" cases)\t"+Codex.nomsSorts[joueurs[numJoueurTour].competences[i]]);
            if(Codex.sorts[joueurs[numJoueurTour].competences[i]][6]!=0)
                System.out.print(" (utilise aussi PM : "+Codex.sorts[joueurs[numJoueurTour].competences[i]][6]+")");
            System.out.println();
        }

    }
    
    private static void afficherMiniPlateau(int[][] p){
        char[] miniPatterns={' ','M','o','x'};
        for(int k=0;k<TAILLE_TERMINAL/2-p[0].length/2-1;k++)
                System.out.print(' ');
        for(int k=0;k<p[0].length+2;k++)
                System.out.print('-');
            System.out.println();
        for(int i=0;i<p.length;i++) {
            for(int k=0;k<TAILLE_TERMINAL/2-p[0].length/2-1;k++)
                System.out.print(' ');
            System.out.print('|');
            for(int j=0;j<p[0].length;j++){
                if(p[i][j]==-1||p[i][j]==-2)
                    System.out.print('A');
                else
                    System.out.print(miniPatterns[p[i][j]]);
            }
            System.out.println('|');
        }
        for(int k=0;k<TAILLE_TERMINAL/2-p[0].length/2-1;k++)
                System.out.print(' ');
        for(int k=0;k<p[0].length+2;k++)
            System.out.print('-');
    }
    
    /**
     * La methode victoire est une methode de type void permettant d'afficher un message de victoire lorsqu'un joueur a gagné.
     * @param numJoueur, renvoie au numéro de joueur victorieux.
     */
    public static void victoire(int numJoueur) {
        System.out.println("Le joueur "+(numJoueur+1)+" a gagné !");
    }
    
    /**
    * La methode selectPersonnage est une méthode de type void permetant d'afficher un écran listant les types personnages.
    * @param numJoueur, une variable de type entier, qui renvoie à un numéro de joueur qui choisit son personnage.
    */
     
    public static void selectPersonnage(int numJoueur) {
        viderEcran();
        for(int i=0;i<Codex.nomsTypes.length;i++){
            centrerTexte(i+" : "+Codex.nomsTypes[i]);
            System.out.println(i+" : "+Codex.descTypes[i]);
            System.out.println();
        }
        System.out.println("Joueur "+(numJoueur+1)+" :\ntapez le numéro correspondant au type de personnage que vous souhaitez.");
    }     
    
    /**
    * La methode selectPersonnage est une méthode de type void permetant d'afficher un écran listant les types plateaux que l'on souhaite utiliser.
    * @see Codex #plateaux
    */
    public static void selectPlateau() {
        viderEcran();
        for(int i=0;i<Codex.nomsPlateaux.length;i++){
            System.out.println();
            centrerTexte(i+" : "+Codex.nomsPlateaux[i]);
            System.out.println();
            afficherMiniPlateau(Codex.plateaux[i]);
            System.out.println();
        }
        System.out.println("Tapez le numéro correspondant au plateau que vous souhaitez.");
    }
    
    private static String[] listeMessageId={"Compétence utilisée !",
        "Déplacé !",
        "Hors de portée !",
        "Pas assez de PA !",
        "Pas assez de PM !",
        "Tour passé !",
        "Pas de commande reconnue : tape \"regles\" pour afficher les regles du jeu",
        "Début du jeu",
        "Hors du plateau !",
        "C'est un mur !",
        "Oups, tu es tombé dans un trou...",
        "Tu es sur un piège ! Tu perds des PV à chaque début de tour !",
        "ATTENTION : tu es au dessus d'un trou ! Si tu ne te déplace pas, tu mourras à la fin du tour !"
    };
    
    /**
    * La methode afficherMessage est une méthode de type void permetant d'afficher un message en fonction du numéro du message.
    * @param messageId est un entier qui contient le numéro du message.
    */
    public static void afficherMessage(int messageId) {
        System.out.println(listeMessageId[messageId]);
    }
    
    /**
    * La methode afficherRegles est une méthode de type void permettant d'afficher les règles du jeu.
    */
    public static void afficherRegles() {
        
        viderEcran();
        centrerTexte("Règles du jeu\n\n");
        System.out.println("Bienvenue à toi, joueur intrépide,\n"
        +"Si tu joues à notre jeu, tu dois savoir certaines choses : \n\n"

        +"Le combat se joue à deux, trouve un copain. Tu pourras ainsi le défier sur une des moult maps proposées, en incarnant un des 4 personnages suivants : Aina, Achille, Nikita, Estelle.\n\n"
        +"Chacun de vous a un certain nombre de points de vie (PV), points de mouvement (PM) et points d'actions (PA). Le but est de faire mordre la poussière à ton adversaire. Celui qui n'a plus de PV décède dans d'atroces souffrances, et perd le jeu. Les PA et PM quant à eux, te servent respectivement à lancer des sorts et te déplacer.\n\n"

        +"Pour utiliser une de tes compétences il te suffit de taper \"competence\" suivi du numéro (ces derniers sont indiqués en bas de l'écran à chaque tour). Par exemple : \"competence 1\".\n\n"
        +"Il est à noter que chaque compétence a une portée limitée ! Dans le cas particulier d'une téléportation, il te faudra indiquer la case sur laquelle tu voudras te déplacer (par exemple \"competence 4 B4\".\nNote : les sorts passifs n'ont pas d'effet lorsqu'ils sont utilisés.\n"
        +"Pour un simple déplacement, indique \"deplacement\" et les coordonnées de la case visée. Attention, rappelle-toi que tu es limité en points de déplacements et d'actions.\n"
        +"Pour passer au joueur suivant, il te suffit de taper \"fin\".\n\n"

        +"Sur les différentes maps, tu rencontreras différents obstacles tels des murs, pièges et trous.\n"
        +"Voici les différentes représentations des obstacles :\n\n"

        +"     Mur : ||||        Trou : 0000          Piège : x  x\n"
        +"           ||||               0000                   xx \n"
        +"           ||||               0000                  x  x\n\n"
        
        +"Tu dois contourner les murs pour te déplacer, si tu es sur un trou à la fin d'un tour tu meurs instantanément, et le piège t'enlève des PV à chaque début de tour si tu es dessus !\n\n"
        
        +"Ah une dernière chose, si tu es familier avec le jeu, tu peux utiliser les raccourcis \"c\" pour competence et \"d\" pour deplacement. ;) \n\n"

        +"BONNE CHANCE!!!");
        System.out.print("Tapez sur \"Entrée\" pour continuer\n");
        Scanner sc=new Scanner(System.in);
        sc.nextLine();
    }
    
    private static void viderEcran(){
        for(int i=0;i<100;i++)
            System.out.println();
    }
    
    private static void centrerTexte(String texte){
        int longueurTexte=texte.length();
        for(int i=0;i<TAILLE_TERMINAL/2-longueurTexte/2;i++)
            System.out.print(' ');
        
        System.out.println(texte);
    }

}
