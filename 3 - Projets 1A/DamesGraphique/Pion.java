public class Pion {

    // attributs
    
    public int x, y;          // coordonnés
    
    public boolean viv;  // savoir si le pion est vivant ou pas  
    
    public boolean dame;    // état du pion : s'il s'agit d'une dame ou pas
    
    public int couleur;   // camp du pion: noir ou blanc
    
    public int[][] deplacementPossible;
    
    public boolean capturePossible;
    // constructeur
    public Pion (int c) {
		deplacementPossible = new int[20][4];
		dame = false;
		viv = true;
		couleur = c;
		capturePossible = false;
	}
   
    public boolean promotion (Tableau tableau){
        if (!this.dame) {
			if (couleur == 2 && y == 0){
				tableau.monde[this.x][this.y] += 2;
				return true;
			} else if(couleur == 1 && y == 9){
				tableau.monde[this.x][this.y] += 2;
				return true;
			}
			return false;
		}
		return true;
    }    
        
    public void capture (int x, int y, Tableau tableau){
		int xCapt = 0;
		int yCapt = 0;
		if (this.x - x < 0) {
			xCapt = x - 1;
		} else if (this.x - x > 0) {
			xCapt = x + 1;
		} if (this.y - y < 0) {
			yCapt = y - 1;
		} else if (this.y - y > 0) {
			yCapt = y + 1;
		}	
		tableau.pions[tableau.trouvePion(xCapt, yCapt)].viv = false;
		tableau.pions[tableau.trouvePion(xCapt, yCapt)].x = -1;
		tableau.pions[tableau.trouvePion(-1, yCapt)].y = -1;
		tableau.monde[xCapt][yCapt] = 0;
		this.deplacement(x, y, tableau);
		this.dame = this.promotion(tableau);
		
	}                       
    
    public void deplacement (int dx, int dy, Tableau tableau) {
		tableau.monde[this.x][this.y] = 0;
		this.x = dx;
		this.y = dy;
		if (this.couleur == 1 && !this.dame) {
			tableau.monde[this.x][this.y] = 1;
		} else if (this.couleur == 2 && !this.dame) {
			tableau.monde[this.x][this.y] = 2;
		} else if (this.couleur == 1 && this.dame) {
			tableau.monde[this.x][this.y] = 3;
		} else if (this.couleur == 2 && this.dame) {
			tableau.monde[this.x][this.y] = 4;
		}
		this.dame = this.promotion(tableau);
	}
} 
    
