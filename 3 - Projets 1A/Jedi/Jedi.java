/**
 * La classe Jedi permet de creer un personnage, de connaitre et de modifier ses caracteristiques, et de lui faire effectuer des actions
 * @author Isis Lorenzo , Theo Durand, Loan Grimm 
 */



public class Jedi{
    
    private int num;
    private String nom;
    private int ptsDeVie;
    private int force;
    private int degatSabre;
    private int degatForce;
    
    /**
     * Creation d'un Jedi, associe a un numero, duquel depend ses caracteristiques
     * @param num le numero du Jedi choisi par le joueur
     * @return un objet de type Jedi, avec Nom, Points de vie, Force, et les degats corespondants a la force et au sabre
    */
    
    public Jedi(int num) {
		if (num>4 || num<1){
			num = (int)(1+Math.random()*4);
		}
        if (num == 1){
            nom = "Maitre Yoda";
            ptsDeVie = 100;
            force = 80;
            degatSabre = 20;
            degatForce = 40;
        }
        if (num == 2){
            nom = "Rey";
            ptsDeVie = 110;
            force = 40;
            degatSabre = 15;
            degatForce = 35;
        }
        if (num == 3){
            nom = "Dark Vador";
            ptsDeVie = 80;
            force = 20;
            degatSabre = 15;
            degatForce = 60;
        }
        if (num == 4){
            nom = "Luke Skywalker";
            ptsDeVie = 120;
            force = 60;
            degatSabre = 15;
            degatForce = 40;
        }
    }
    
  
    // Getters 


    public String getNom(){
        return nom;
    }
    public int getPtsDeVie(){
        return ptsDeVie;
    }
    public int getForce() {
        return force;
    }
    
    //Setters
    
    /**
     * La methode setNom permet de differencier deux joueurs qui choisissent deux personnages identiques
     * @param s la chaine de caracteres a ajouter au nom
     * @return aucun
     */
    
    public void setNom(String s){
        nom = nom+s;
    }
    
    /**
     * La methode setPtsDeVie permet de modifier les points de vie d'un personnage, en fonction du coup de son adversaire
     * @param p l'entier correspondant aux degats recus
     * @return aucun
     */
    
    public void setPtsDeVie(int p){
		ptsDeVie = getPtsDeVie()-p;
    }
    
    /**
     * La methode setForce permet d'augmenter le pourcentage de force d'un joueur
     * @param f un entier correspondant a la force gagnee 
     * @return aucun
     */
     
    public void setForce(int f) {
        force = force+f;
    }
    
    /**
     * La methode coupSabre permet de donner un coup de sabre et de calculer les degats occasionnes
     * @param aucun
     * @return un entier contenant le nombre de degats occasionnes entre 50% et 100% du degat max
     *
     */
    
    // Methodes d'actions
    
    public int coupSabre(){
        int coup = degatSabre-((int)(Math.random()*0.5*degatSabre));
        return coup;

    }
            
    /**
     * La methode parerCoup permet d'esquiver un coup de sabre avec une probabilite d'une chance sur 3 de reussir ( et d'une chance sur 2 pour Dark Vador ).
     * @param aucun
     * @return un boolean traduisant la reussite ou l'echec de la parade
     *
     */
     
    public boolean parerCoup(){
		boolean reussite;
		if(nom == "Dark Vador"){
			reussite = Math.random()<0.5;
		}else{
			reussite = Math.random()<0.3;
		}
        return reussite;
    }
    
    /** 
     * La methode utiliseForce permet de faire une attaque puissante en utilisant la totalite de la force 
     * @param aucun
     * @return un entier correspondant aux degats occasionnes par la force (selon le Jedi) 
    */
    
    public int utiliseForce(){
        force = -15;
        return degatForce;
    }
    
    /**
     *La methode regagnerVie permet d'utiliser la force, quel que soit son niveau, afin de regagner des points de vie selon un rapport d' 1/3 ( ou 1/2 pour Rey )
     * @param aucun
     * @return un String decrivant le nombre de point de vie regagnes par le joueur.
     */
     
    public String regagnerVie(){
		int gain = 0;
		if(nom == "Rey"){
			gain = (int)((0.5)*force);
			ptsDeVie = gain + ptsDeVie;
			force = -15;
		}else{
			gain = (int)((0.33)*force);
			ptsDeVie = gain + ptsDeVie;
			force = -15;
		}
		return getNom()+" a utilise la force et regagne "+gain+" points de vie !";
	
	}
	
	/**
	 *La methode regagnerForce permet aux deux joueurs de regagner 15% de force a la fin de chaque tour ( 20% pour Luke Skywalker ), sans depasser 100%
	 *@param aucun
	 *@return aucun 
	 */
	 
	public void regagnerForce(){
		if(nom == "Luke Skywalker"){
				setForce(20);
			}else{
				setForce(15);
			}
		if(force>=100){
				setForce(-(force-100));
		}
	}		
	
	
}
        
            
    
    

