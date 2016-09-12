public class Damier{
	public  Pion[][] plateau; 
	//private Pion p;

	public Damier(){
		this.plateau=new Pion[10][10];
		/*creer les pions pour le joueur noir*/
		for(int i=0;i<4;i++){
			for(int j=0;j<10;j++){
				if(i%2==0&&j%2==1){
					this.plateau[i][j]=new Pion(true,true,true);
				}
				if(i%2==0&&j%2==0){
					this.plateau[i][j]=new Pion(false,true,true);
				}
				if(i%2==1&&j%2==0){
					this.plateau[i][j]=new Pion(true,true,true);
				}
				if(i%2==1&&j%2==1){
					this.plateau[i][j]=new Pion(false,true,true);
				}
			}
		}
		/*creer les pions pour le joueur blanc*/	
		for(int i=6;i<10;i++){
			for(int j=0;j<10;j++){
				if((i%2==0)&&(j%2==1)){
					this.plateau[i][j]=new Pion(true,false,true);
				}
				if((i%2==0)&&(j%2==0)){
					this.plateau[i][j]=new Pion(false,false,true);
				}
				if((i%2==1)&&(j%2==0)){
					this.plateau[i][j]=new Pion(true,false,true);
				}
				if((i%2==1)&&(j%2==1)){
					this.plateau[i][j]=new Pion(false,true,true);
				}
			}
		}
		for(int i=4;i<6;i++){
			for(int j=0;j<10;j++){
				this.plateau[i][j]=new Pion(false,true,true);
			}
		}
	}
	
	
	/* le deplacement de la spire*/
	public boolean avanceSpire(int i,int j,int a,int b){
		boolean acceptabilite=true;//pour verifier si la position choissie est acceptable
		if((this.plateau[i][j].sireOuDame==true)&&(a<10)&&(b<10)&&(i!=a)&&(j!=b)){
			if(this.plateau[i][j].noirOuBlanc==true){//determiner que c'est a quel joueur
				if((a==i+1)&&((b==j-1)||(b==j+1))&&(this.plateau[a][b].viOuMo==false)){
					this.plateau[a][b].noirOuBlanc=this.plateau[i][j].noirOuBlanc;
					this.plateau[a][b].viOuMo=true;
					this.plateau[a][b].sireOuDame=true;
					this.plateau[i][j].viOuMo=false;
					System.out.println("Sire avance");
				}
				else{
					acceptabilite=false;
				}
				if(a==9){
					this.plateau[a][b].sireOuDame=false;//la promotion de la spire
				}
			}
			else{
				if((a==i-1)&&((b==j-1)||(b==j+1))&&(this.plateau[a][b].viOuMo==false)){
					this.plateau[a][b].noirOuBlanc=this.plateau[i][j].noirOuBlanc;
					this.plateau[a][b].viOuMo=true;
					this.plateau[a][b].sireOuDame=true;
					this.plateau[i][j].viOuMo=false;
					System.out.println("Sire avance");
				}
				else{
					acceptabilite=false;
				}
				if(a==0){
					this.plateau[a][b].sireOuDame=false;//le promotion de la spire
				}
			}
		}
		else{
			acceptabilite=false;
		}
		return acceptabilite;
	}
	
	
	/*le deplacement de la dame*/
	public boolean avanceDame(int i,int j,int a,int b){
		boolean acceptabilite=true;////pour verifier si la position choissie est acceptable
		if((i!=a)&&(j!=b)&&(this.plateau[i][j].sireOuDame==false)&&(a<10)&&(b<10)&&((((b-j)/(a-i)==1))||((b-j)/(a-i)==-1))){
			boolean acceptabilite2=true;
			int q=i;
			int p=j;
			while(Math.abs(q-a)!=0){
				q=q+((a-i)/Math.abs(a-i));
				p=p+((b-j)/Math.abs(b-j));
				if(this.plateau[q][p].viOuMo==true){
					acceptabilite2=false;
					break;
				}
			}
			if(acceptabilite2==true){
				this.plateau[a][b].noirOuBlanc=this.plateau[i][j].noirOuBlanc;
				this.plateau[a][b].viOuMo=true;
				this.plateau[a][b].sireOuDame=false;
				this.plateau[i][j].viOuMo=false;
				System.out.println("Dame avance");
			}
			else{
				acceptabilite=false;
			}
		}
		else{
			acceptabilite=false;
		}
		return acceptabilite;
	}
	
	
	public boolean captureSpire(int i,int j,int a,int b){
		boolean acceptabilite=true;
		if((i!=a)&&(j!=b)&&(this.plateau[i][j].sireOuDame==true)&&(((b-j)/(a-i)==1)||((b-j)/(a-i)==-1))&&(this.plateau[(i+a)/2][(j+b)/2].viOuMo==true)&&(this.plateau[a][b].viOuMo==false)&&(this.plateau[(i+a)/2][(j+b)/2].noirOuBlanc!=this.plateau[i][j].noirOuBlanc)){
			this.plateau[a][b].noirOuBlanc=this.plateau[i][j].noirOuBlanc;
			this.plateau[a][b].viOuMo=true;
			this.plateau[a][b].sireOuDame=true;
			this.plateau[i][j].viOuMo=false;
			this.plateau[(i+a)/2][(j+b)/2].viOuMo=false;
			System.out.println("sire capture");
			if((a==9)&&(this.plateau[i][j].noirOuBlanc==true)){
					this.plateau[a][b].sireOuDame=false;//le promotion de la spire
			}
			if((a==0)&&(this.plateau[i][j].noirOuBlanc==false)){
					this.plateau[a][b].sireOuDame=false;//le promotion de la spire
			}
		}
		else{
			acceptabilite=false;
		}
		return acceptabilite;
	}
	
	
	public boolean captureDame(int i,int j,int a,int b){
		boolean acceptabilite=true;
		if((i==a+2*((i-a)/Math.abs(i-a)))&&(j==b+2*((j-b)/Math.abs(j-b)))){
			this.plateau[a][b].noirOuBlanc=this.plateau[i][j].noirOuBlanc;
			this.plateau[i][j].viOuMo=false;
			this.plateau[a][b].viOuMo=true;
			this.plateau[a][b].sireOuDame=this.plateau[i][j].sireOuDame;
			this.plateau[a+((i-a)/Math.abs(i-a))][b+((j-b)/Math.abs(j-b))].viOuMo=false;
		}
		if((i!=a)&&(j!=b)&&(this.plateau[i][j].sireOuDame==false)&&(a<10)&&(b<10)&&(((b-j)/(a-i)==1)||((b-j)/(a-i)==-1))&&(this.plateau[i][j].noirOuBlanc!=this.plateau[a+((i-a)/Math.abs(i-a))][b+((j-b)/Math.abs(j-b))].noirOuBlanc) ){
			acceptabilite=avanceDame(i,j,a+(2*((i-a)/Math.abs(i-a))),b+(2*((j-b)/Math.abs(j-b))));
			if(acceptabilite==false){
			}
			else{
				this.plateau[a+2*((i-a)/Math.abs(i-a))][b+2*((j-b)/Math.abs(j-b))].viOuMo=false;
				this.plateau[a][b].noirOuBlanc=this.plateau[i][j].noirOuBlanc;
				this.plateau[i][j].viOuMo=false;
				this.plateau[a][b].viOuMo=true;
				this.plateau[a][b].sireOuDame=this.plateau[i][j].sireOuDame;
				this.plateau[a+((i-a)/Math.abs(i-a))][b+((j-b)/Math.abs(j-b))].viOuMo=false;
				System.out.println("Dame capture");
			}
		}
		else{
			acceptabilite=false;
		}
		return acceptabilite;
	}
}			
	
