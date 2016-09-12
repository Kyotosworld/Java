import java.util.Scanner;

/**
 * La classe TestJedi permet de tester toutes les methodes crees dans la classe Jedi, avant de les utiliser dans la classe Jeu
 * @author Isis Lorenzo , Theo Durand, Loan Grimm
 */
 
public class TestJedi {
    public static void main(String[] args){
        
        //Ecran d'accueil
        System.out.println("Bienvenue dans l'arene Star Wars !! Choisissez votre Jedi :");
        System.out.println(" 1 : Maitre Yoda\n 2 : Rey\n 3 : Dark Vador\n 4 : Luke Skywalker");
        System.out.println("    /--\\      //             \\\\      /--\\ ");
        System.out.println("    \\ -/     //               \\\\     \\- / ");
        System.out.println("   /####\\   //                 \\\\   /####\\ ");
        System.out.println("  |####\\#\\ //                   \\\\ /#/####| ");
        System.out.println("  |####|\\#//                     \\\\#/|####| ");
        System.out.println(" |## ##|                             |## ##| ");
        System.out.println(" |#  ##|                             |##  #| ");
		
		//Choix du Jedi
        System.out.println("Joueur 1, a vous de choisir [1-4]:");
        Scanner lecture1 = new Scanner(System.in);
        int numero1 = lecture1.nextInt();
        Jedi joueur1 = new Jedi (numero1);
        System.out.println("Joueur 2, a vous de choisir [1-4]:");
        Scanner lecture2 = new Scanner(System.in);
        int numero2 = lecture2.nextInt();
        Jedi joueur2 = new Jedi (numero2);
        
        //Description des joueurs
         
        System.out.println(joueur1.getNom() + "			VS			" + joueur2.getNom());
        System.out.println(joueur1.getPtsDeVie() +" PV	  			     			" + joueur2.getPtsDeVie() +" PV");
        System.out.println("Force : "+joueur1.getForce() +"%	  		     			Force : " + joueur2.getForce() +"%");
        
        //Tests des attaques/parades
        
        int degSabre = joueur1.coupSabre() ;
        System.out.println("\nCoup de sabre : " +joueur2.getNom()+" perd " + degSabre + " points de vie");
        System.out.println(joueur2.getNom() + " tente de parer l'attaque : ");
        if(joueur2.parerCoup()){
             System.out.println("Parade reussie !!");
        } else {
            System.out.println("Parade echouee !!");
            joueur2.setPtsDeVie(degSabre);
        }
        
        int degForce = joueur2.utiliseForce();
        System.out.println(joueur2.getNom() + " utilise la FORCE !!!\n" + joueur1.getNom()+ " perd " + degForce + " points de vies.");
        joueur1.setPtsDeVie(degForce);
        
        //Tests du regain de la vie et du regain de force
        
        System.out.println(joueur1.regagnerVie());
        
        joueur1.regagnerForce();
        joueur2.regagnerForce();
        
        //Vérification des changements
        System.out.println(joueur1.getNom() + "			VS			" + joueur2.getNom());
        System.out.println(joueur1.getPtsDeVie() +" PV	  			     			" + joueur2.getPtsDeVie() +" PV");
        System.out.println("Force : "+joueur1.getForce() +"%	  		     			Force : " + joueur2.getForce() +"%");
        
    }
}
