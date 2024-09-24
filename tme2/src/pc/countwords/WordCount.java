package pc.countwords;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		List<Thread> th= new ArrayList<>();
		
		for (int i = 0; i < args.length; i++) {
			th.add(new WordCounter(args.length,args[i], i));
		}
		
		for(Thread t : th) {
			t.start();
		}
		
		for(Thread t : th) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Word count:" + Arrays.toString(WordCounter.wordCount));
		int total = 0;
		
		for (int count : WordCounter.wordCount) {
			total += count;
		}
		
		System.out.println("Total word count:" + total);
		System.out.println("Total time "+(System.currentTimeMillis()-startTime) + " ms");
	}
}
