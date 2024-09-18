package pc.countwords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class WordCounter implements Runnable {
	private String file;
	int [] wordCount;
	private int index;

	public WordCounter (String file, int val, int[] wordCount) {
		this.file=file;
		this.wordCount = wordCount;
		this.index = val;
	}

	@Override
	public void run() {
		try {
			wordCount[index] = WordCount.countWords(file);
//			System.out.println("nombre mots : "+ countWords(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

public class WordCount {

	public static int countWords(String filename) throws IOException {
		long startTime = System.currentTimeMillis();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			int total = 0;
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				total += line.split("\\s+").length;
			}
			System.out.println("Time for file "+filename+" : "+(System.currentTimeMillis()-startTime) + " ms for "+ total + " words");
			return total;
		}
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		int [] wordCount = new int[args.length];
		String file;
				for (int i = 0; i < args.length; i++) {
					Thread t1 = new Thread(new WordCounter(file,i,args[i]));
					t1.start();
					
					try {
						wordCount[i] = countWords(args[i]);
					} catch (IOException e) {
						System.err.println("Error reading file: " + args[i]);
						e.printStackTrace();
					}
				}



		System.out.println("Word count:" + Arrays.toString(wordCount));
		int total = 0;
		for (int count : wordCount) {
			total += count;
		}
		System.out.println("Total word count:" + total);
		System.out.println("Total time "+(System.currentTimeMillis()-startTime) + " ms");
	}

}
