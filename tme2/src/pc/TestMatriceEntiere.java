package pc;

public class TestMatriceEntiere {
	public static void main (String[]args) {
        int[][] tab1 = {{1, 5, 3},{9, 7, 6},{7, 10, 9}};
        int[][] tab2 = {{9, 9, 7},{8, 5, 4},{3, 2, 6}};
        
        MatriceEntiere mat1 = new MatriceEntiere(tab1.length, tab1[0].length);
        MatriceEntiere mat2 = new MatriceEntiere(tab2.length, tab2[0].length);
	
        for (int i = 0; i < tab1.length; i++) {
            for (int j = 0; j < tab1[0].length; j++) {
                mat1.setElem(i, j, tab1[i][j]);
                mat2.setElem(i, j, tab2[i][j]);
            }
        }
        
        long startTime = System.currentTimeMillis();
        MatriceEntiere produit = null;
		try {
			produit = mat1.produit(mat2);
		} catch (TaillesNonConcordantesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
        System.out.println("Temps méthode produit : " + (endTime - startTime) + " ms");
        
        
        startTime = System.currentTimeMillis();
        MatriceEntiere produitMT = null;
		try {
			produitMT = mat1.produitMT(mat2);
		} catch (TaillesNonConcordantesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endTime = System.currentTimeMillis();
        System.out.println("Temps méthode produitMT : " + (endTime - startTime) + " ms");
        
   
		boolean b = true;
        for (int i = 0; i < produit.nbLignes(); i++) {
            for (int j = 0; j < produit.nbColonnes(); j++) {
                if (produit.getElem(i, j) != produitMT.getElem(i, j)) {
                    b = false;
                    break;
                }
            }
        }
        
        if (b) {
            System.out.println("résultats des deux methodes identiques.");
        } else {
            System.out.println("résultats des deux méthodes différents.");
        }

        
	}
}
	
