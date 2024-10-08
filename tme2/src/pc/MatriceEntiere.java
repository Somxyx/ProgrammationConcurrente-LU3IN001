package pc;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MatriceEntiere {
	private int[][] matrice;
	
	public MatriceEntiere(int lig, int col) {
		matrice = new int [lig][col];
		for(int i=0;i<lig;i++) {
			for (int j=0;j<col;j++) {
				matrice[i][j]=0;
			}
		}
	}
	
	public int getElem(int lig, int col) {
		return matrice[lig][col];
	}
	
	public void setElem(int lig, int col, int val) {
		matrice[lig][col]=val;
	}
	
	public int nbLignes() {
		return matrice.length;
	}
	
	public int nbColonnes() {
		return matrice[0].length;
	}
	
	public static MatriceEntiere parseMatrix(File fichier) throws IOException{
		try { 
			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new FileReader(fichier));
			int nbLig = Integer.parseInt(in.readLine());
			int nbCol = Integer.parseInt(in.readLine());
			MatriceEntiere mat = new MatriceEntiere(nbLig,nbCol);
			
			for (int i=0;i<nbLig;i++) {
				String []mots=in.readLine().split(" ");
				for(int j=0;j<nbCol;j++) {
					mat.setElem(i,j,Integer.parseInt(mots[j]));
				}
			}
			return mat;
			
		} catch(Exception e) {
			throw new IOException (e);
		}
	}
	
	
	public String toString() {
		StringBuilder res = new StringBuilder();
		
		for (int i = 0 ; i < this.nbLignes() ; i++) {
			for (int j = 0 ; j < this.nbColonnes(); j++) {
				res.append(this.matrice[i][j] + "\t");
			}
			res.append("\n");
		}
		return res.toString();
	}
	
	public boolean equals(Object o) {
		if(this==o)
			return true;
		if(o==null)
			return false;
		if (getClass() !=o.getClass())
			return false;
		MatriceEntiere other =(MatriceEntiere) o;
		return this.nbColonnes() == other.nbColonnes() && this.nbLignes()==other.nbLignes();
		
	}
	

	
	public MatriceEntiere ajoute(MatriceEntiere m) throws TaillesNonConcordantesException{
			 if(this.nbLignes()!=( m.nbLignes()) || this.nbColonnes()!=( m.nbColonnes())){
		            throw new TaillesNonConcordantesException("Tailles nonconcordantes");
		     }
			 
			// Crée une nouvelle matrice pour stocker le résultat de l'addition
			 MatriceEntiere mat = new MatriceEntiere(this.nbLignes(),this.nbColonnes());
			 
			// Additionne les éléments correspondants des deux matrices
			 for(int i = 0; i<m.nbLignes();i++) {
					for(int j = 0; j<m.nbColonnes();j++) {
							mat.setElem(i,j,m.getElem(i,j)+this.getElem(i,j)) ;
					}			
			 }
			 return mat;
		 }
	
	
	public MatriceEntiere produit (MatriceEntiere m) throws TaillesNonConcordantesException{
		if(this.nbColonnes()!= (m.nbLignes())){
            throw new TaillesNonConcordantesException("dimensions nonconcordantes");
        }
		
		// Crée une nouvelle matrice pour stocker le résultat
		 MatriceEntiere mat = new MatriceEntiere(this.nbLignes(),m.nbColonnes());
		 
		// Calcule le produit matriciel
		for(int i=0; i<this.nbLignes();i++) {
			for(int j = 0; j<m.nbColonnes();j++) {
				for(int k = 0; k<this.nbColonnes();k++) {
					mat.matrice[i][j] += this.matrice[i][k] * m.matrice[k][j];
				}
			}
		}
		return mat;
	}
	
	
	public MatriceEntiere transposee() {
		MatriceEntiere mat = new MatriceEntiere(this.nbColonnes(),this.nbLignes());
			
		// Intervertit les lignes et les colonnes
		for(int i = 0; i<this.nbLignes();i++){
			for(int j = 0; j<this.nbColonnes();j++){
				mat.setElem(j,i,this.getElem(i,j));
			} 
		}
		return mat;
	}
	
	
	public MatriceEntiere produitParScalaireMT(int n) {
			MatriceEntiere res = new MatriceEntiere(this.nbLignes(), this.nbColonnes());
		
			class ProduitParLigne extends Thread{
				private int l;
				
				public ProduitParLigne(int l) {
					this.l=l;
				}
				
				public void run() {
					for (int j = 0; j < res.nbColonnes(); j++) {
	                    res.matrice[l][j] = matrice[l][j] * n;
	                    
	                }
				}
			}
			
	        Thread[] th = new ProduitParLigne[res.nbLignes()];
	        for (int i = 0; i < res.nbLignes(); i++) {
	            th[i] = new ProduitParLigne(i);
	            th[i].start();
	        }
	        return res;
		}
	
	
	public MatriceEntiere produitMT(MatriceEntiere m)throws TaillesNonConcordantesException{
		if(this.nbColonnes()!= ( m.nbLignes())) throw new TaillesNonConcordantesException("dimensions nonconcordantes");
        
		MatriceEntiere mat = new MatriceEntiere(this.nbLignes(),m.nbColonnes());
		
		class ProduitParCellule implements Runnable{
			private int l;
			private int c; 
			
			public ProduitParCellule(int l, int c) {
				this.l=l;
				this.c=c;
			}
			
			public void run() {
				int s = 0;
                for (int k = 0; k < matrice[0].length; k++) {
                    s += matrice[l][k] * m.matrice[k][c];
                }
                mat.matrice[l][c] = s;
			}
		
		}
		
			Thread[][] th = new Thread[this.nbLignes()][m.nbColonnes()];

	        for (int i = 0; i < this.nbLignes(); i++) {
	            for (int j = 0; j < m.nbColonnes(); j++) {
	                th[i][j] = new Thread(new ProduitParCellule(i, j));
	                th[i][j].start();
	            }
	        }
		return mat;	
	}
}




















