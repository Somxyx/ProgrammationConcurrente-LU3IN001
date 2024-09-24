package pc;

public class Scalaire extends Thread{
	private MatriceEntiere m1;
	private MatriceEntiere m2;
	private int scal;
	
	public Scalaire (MatriceEntiere m1, MatriceEntiere m2, int scal) {
		this.m1=m1;
		this.m2=m2;
		this.scal=scal;
	}
	
	
	public void run() {
		int res = MatriceEntiere.produitLigneColonne(this.m1, this.i, this.m2, this.j);
		this.m.setElem(this.i, this.j, res);
		
	}
}