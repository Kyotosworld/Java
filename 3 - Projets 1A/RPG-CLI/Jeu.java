/**
Voici la classe principale du jeu. C'est elle qu'il faut exécuter pour pouvoir jouer.
*/
public class Jeu {
    /**
     * un objet de type int, il s'agit du nombre de joueurs.
     */
    public static final int NOMBRE_JOUEURS=2;
    
    /**
     * un objet de type int, il s'agit du nombre de points d'action que chaque personnage possède au début d'un tour de jeu.
     */ 
    public static final int NOMBRE_PA_TOUR=2;
    /**
     * un objet de type int, il s'agit du nombre de points de vie qu'un personnage perd lorsqu'il se déplace sur un piège.
     */
    public static final int DEGATS_PIEGE=50;
    
    /**
     * un objet de type int, il s'agit du nombre de points de vie qu'un personnage perd lorsqu'il se déplace sur un trou.
     */ 
    public static final int DEGATS_TROU=5000;
    
    /**
     * Il s'agit du main, la méthode faisant fonctionner le jeu
     * @param args argument nécessaire à la compilation de la classe mais non utilisé.
     */
    public static void main(String[] args) {
        
        //NOTE : j'ai codé le main comme s'il pouvait y avoir plus que 2 joueurs
        
        //INITIALISATION DES VARIABLES ET CONSTANTES
        Personnage[] joueurs=new Personnage[NOMBRE_JOUEURS]; //Tableau contenant les objets déclarés
        
        int tour=0; //c'est au tour du joueur 0 (affiché joueur 1) au début 
        int nbJoueursEnVie; //nombre de joueurs dont le personnage a encore des PV
        boolean victoire=false; //si "true", il y a victoire d'un des joueurs, la partie est terminée
        int victorieux=0; //contient le numéro du joueur à la FIN de la partie
        int erreurAction;
        int messageId=7;
        
        int[][] statutPersonnages=new int[NOMBRE_JOUEURS][3];
        //FIN D'INITIALISATION DES VARIABLES ET CONSTANTES
        
        Affichage.afficherRegles(); //Affiche les règles avant le début du jeu ;)
        
        //DEBUT DE SELECTION DU PLATEAU
        Affichage.selectPlateau();
        Plateau plateau= new Plateau(Commande.demandeNombre());
        //FIN DE SELECTION DU PLATEAU
        
        //DEBUT DE SELECTION DU PERSONNAGE POUR CHAQUE JOUEUR
        for(int i=0;i<NOMBRE_JOUEURS;i++) {
           Affichage.selectPersonnage(i); //affiche l'écran de sélection des personnages
            joueurs[i]=new Personnage(Commande.demandeNombre(),plateau,i); //demande un nombre correspondant au type de personnage puis l'affecte au joueur i
        }
        //FIN DE SELECTION DU PERSONNAGE POUR CHAQUE JOUEUR
        
        //BOUCLE DES TOURS DE CHAQUE JOUEUR JUSQU'A VICTOIRE
        while(!victoire) {
			statutPersonnages[tour][0]=joueurs[tour].pv;
			statutPersonnages[tour][1]=joueurs[tour].pa;
			statutPersonnages[tour][2]=joueurs[tour].pm;
            if(joueurs[tour].pa<Codex.types[joueurs[tour].type][2])
                joueurs[tour].ajoutePA(NOMBRE_PA_TOUR);
            
            if(joueurs[tour].competences[4]==9) {
				joueurs[tour].force=Codex.types[joueurs[tour].type][6]+(Codex.types[joueurs[tour].type][0]-joueurs[tour].pv)*2;
			}
            
            if(plateau.estSurPiege(joueurs[tour]) && joueurs[tour].competences[4]!=12) //estSurUnPiege() vérifie si le personnage est sur un piege
                joueurs[tour].ajoutePV(-DEGATS_PIEGE); //enlève des PV si c'est le cas

            do {
                Affichage.plateau(plateau.getTable(),tour,joueurs,statutPersonnages); //rappel : la variable tour contient le numéro du joueur auquel c'est le tour
                

                if(plateau.estSurTrou(joueurs[tour]) && joueurs[tour].competences[4]!=12){ //estSurUnTrou() vérifie si le personnage est sur un trou
                    messageId=12; //préviens le joueur
                }
                
                Affichage.afficherMessage(messageId);
                
                //Liste les joueurs pour savoir s'il ne reste qu'un joueur en vie.
                nbJoueursEnVie=0;
                for(int i=0;i<joueurs.length;i++) {
                    if(joueurs[i].pv>0) {
                        nbJoueursEnVie++;
                        victorieux=i;
                    }
                }
                if(nbJoueursEnVie==1) {
                    victoire=true;
                    break;
                }
                messageId=Commande.action(joueurs[tour],joueurs[(tour+1)%NOMBRE_JOUEURS],plateau.getTable());
                if(messageId==5) {
                    break; //si Commandes.action() vaut 5, le joueur veut passer son tour
                }
            } while(true);
            
            if(plateau.estSurTrou(joueurs[tour]) && joueurs[tour].competences[4]!=12){ //estSurUnTrou() vérifie si le personnage est sur un trou
                joueurs[tour].ajoutePV(-DEGATS_TROU); //enlève des PV si c'est le cas
                messageId=11;
            }
            
            joueurs[tour].reinitialisePM();
            tour=(tour+1)%NOMBRE_JOUEURS; //C'est au tour du joueur suivant !
        }
        //FIN DE LA BOUCLE DES TOURS
        
        //LE JOUEUR VICTORIEUX EST AFFICHE
        Affichage.victoire(victorieux);
        //FIN DU PROGRAMME
    }
}
