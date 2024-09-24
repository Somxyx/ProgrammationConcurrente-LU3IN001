package pc.countwords;

import java.io.IOException;

public class WordCounter extends Thread {
	static int [] wordCount;
	private String file;
	private int indice;
	
	public WordCounter (int val, String file, int indice) {
		wordCount= new int [val]; 
		this.file= file;
		this.indice=indice;
	}
	
	public void run() {
		try {
			int nbMots = WordCount.countWords(file);
			wordCount[indice] = nbMots;
			System.out.println("nombre mots : "+ nbMots);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}