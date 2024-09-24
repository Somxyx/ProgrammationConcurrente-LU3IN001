package pc;

@SuppressWarnings("serial")
public class TaillesNonConcordantesException extends Exception{
	public TaillesNonConcordantesException(String message) {
		super("Probleme : "+ message);
	}
	

}


/*String mat ="[ ";
for (int i=0; i<this.nbLignes();i++) {
	mat += "[ ";
	for (int j=0; j<this.nbColonnes();j++) {
		mat +=this.matrice[i][j] + " ";
	}
	mat +=" ]" + "\n";
}
return mat; 

sum += this.getElem(i,k) * m.getElem(k,j) ;
				}
			mat.setElem(i,j,sum);*/