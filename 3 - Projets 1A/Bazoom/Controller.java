//Numero 6:
// On importe ce dont on a besoin.
import java.awt.Graphics;
import java.util.ArrayList;


public class Controller {
    
    /* Ici on utilise les ArrayList que l'on a pas vu en classe mais on en a besoin pour supprimer les elements morts.
       On cree donc 4 lites private des deux types de monstres et deux types de flammes.
    */
    
    
	private ArrayList<Flamme> f = new ArrayList<Flamme>();
	Flamme TempFlamme;
	Flamme TempFlamme2;
    
    private ArrayList<Flamme2> f2 = new ArrayList<Flamme2>();
	Flamme2 TempFlamme3;
	Flamme2 TempFlamme4;
	
	private ArrayList<Monstre> m = new ArrayList<Monstre>();
	 Monstre TempMonstre;
	 Monstre TempMonstre2;
	 Monstre TempMonstre3;
	 
	 
	private ArrayList<Monstre2> m2 = new ArrayList<Monstre2>();
	 Monstre2 TempM;
	 Monstre2 TempM2;
	 
	 
	 
	 
	
	
	/*
        On applique le tick pour chaque flamme et on les supprime quand elles sortent de la carte.
        Le tick sera boucle avec la trame donc applique a chaque image.
        On a des boucles for pour appliquer les ticks a chaque element de la liste d'indice i.
    */
	public void tick(){
		for(int i =0; i<f2.size();i++){
			
			TempFlamme3=f2.get(i);
			TempFlamme3.tick();
            
            if(TempFlamme3.getX() >1310)
                supprimerFlamme2(TempFlamme3);
            if(TempFlamme3.getX()<40)
                supprimerFlamme2(TempFlamme3);
            if(TempFlamme3.getY()>700)
                supprimerFlamme2(TempFlamme3);
            if(TempFlamme3.getY()<30)
                supprimerFlamme2(TempFlamme3);
            
            
            
		}
        
        
        for(int i =0; i<f.size();i++){
			TempFlamme=f.get(i);
			TempFlamme.tick();
        
            if(TempFlamme.getX() >1310)
                supprimerFlamme(TempFlamme);
            if(TempFlamme.getX()<40)
                supprimerFlamme(TempFlamme);
            if(TempFlamme.getY()>700)
                supprimerFlamme(TempFlamme);
            if(TempFlamme.getY()<30)
                supprimerFlamme(TempFlamme);
            
            
		}
        
        //On a choisit de gerer la collision avec le mur ici. Car il faut le boucler.
		Scene.p1.mur();
		
        
		
		
		
	}
	
	
	//Getter et Stter des ArrayList car private.
	public ArrayList<Monstre> getM() {
		return m;
	}



	public void setM(ArrayList<Monstre> m) {
		this.m = m;
	}
	
	public ArrayList<Monstre2> getM2() {
		return m2;
	}



	public void setM2(ArrayList<Monstre2> m2) {
		this.m2 = m2;
	}
	


    /*
     Dessine les flammes. Les monstres sont aussi dessine ici mais de maniere aleatoire.
     On gere les collisions avec le mur des monstres.
     On gere la collision des flammes du personnage avec les ennemis qui supprime l'ennemi si c'est le cas et on ajoute 1 au compteur et au nombre de monstre tue.
     On gere la collision des ( deux ) flammes des ennemis avec le personnage UNIQUEMENT. On lui enlève une vie si c'est le cas.
    */
    public void draw(Graphics g){
		for(int i =0; i<f.size();i++){
			TempFlamme=f.get(i);
			TempFlamme.draw(g);
		}
        for(int i =0; i<f2.size();i++){
			
			TempFlamme4=f2.get(i);
            TempFlamme4.draw(g);
			
		}
		for(int i =0;i<m.size();i++){
			TempMonstre = m.get(i);
			if(Main.i%100==0)
			TempMonstre.deplacement();
			
			if((Main.n*1+100*i)%300 == 0)
			TempMonstre.tire();
			
			TempMonstre.mur();
			
			TempMonstre.draw(g);
		}
		for(int i =0;i<m2.size();i++){
			TempM = m2.get(i);
			if(Main.i%100==0)
			TempM.deplacement();
			
			if((Main.n*1+100*i)%300 == 0)
			TempM.tire();
			
			TempM.mur();
			
			TempM.draw(g);
		}
		
		for(int i =0;i<m.size();i++){
			for(int j=0;j<f.size();j++){
				TempMonstre2=m.get(i);
				
				TempFlamme2=f.get(j);
				
				if(TempFlamme2.collision(TempMonstre2)){
					supprimerMonstre(TempMonstre2);
					supprimerFlamme(TempFlamme2);
					Scene.compteur++;
					Scene.Mort++;
					
					
					
				}
				
			}
		}
		
		for(int i =0;i<m2.size();i++){
			for(int j=0;j<f.size();j++){
				
				TempM2=m2.get(i);
				TempFlamme2=f.get(j);
				
				if(TempFlamme2.collisionM(TempM2)){
					supprimerMonstre2(TempM2);
					supprimerFlamme(TempFlamme2);
					Scene.compteur2++;
					Scene.Mort++;
					
					
					
				}
				
			}
		}
		for(int j=0;j<f2.size();j++){
			
			TempFlamme4=f2.get(j);
			if(TempFlamme4.collision2()){
				supprimerFlamme2(TempFlamme4);
				Scene.p1.vie--;
				if(Scene.p1.vie==0)
				Personnage.m=1;
				
			}
			
				
			
		}	
			
				
		
		
		
		
		
	}
	
	// Methode ajout et suppression des monstres/flammes.
	
	public   void ajouterMonstre2(Monstre2 mm){
		m2.add(mm);
		
	}
	public  void supprimerMonstre2(Monstre2 mm){
		m2.remove(mm);
	}
	
	public   void ajouterMonstre(Monstre mm){
		m.add(mm);
	}
	public  void supprimerMonstre(Monstre mm){
		m.remove(mm);
	}
	
	public void ajouterFlamme(Flamme ff){
		f.add(ff);
	}
	public void supprimerFlamme(Flamme ff){
		f.remove(ff);
	}
    
    public void ajouterFlamme2(Flamme2 ff){
		f2.add(ff);
	}
	public void supprimerFlamme2(Flamme2 ff){
		f2.remove(ff);
	}
}
