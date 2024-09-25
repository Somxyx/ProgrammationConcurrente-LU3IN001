package pc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import pc.iter.SimpleList;
import pc.rec.SimpleListRec;
import pc.iter.SimpleListSync;
import pc.rec.SimplelistRecSync;

public class TestList {

	@Test
	public void testSimpleList() {
		IList<String> list = new SimpleList<>();

		runConcurrentTest(list, 10, 1000);
	}
	
	@Test 
	public void testSimpleListSync() {
		IList<String> list = new SimpleListSync<>();

		runConcurrentTest(list, 10, 1000);
	}

	@Test
	public void testSimpleListRec() {
		IList<String> list = new SimpleListRec<>();

		runConcurrentTest(list, 10, 1000);
	}
	
	@Test
	public void testSimpleListRecSync() {
		IList<String> list = new SimplelistRecSync<>();

		runConcurrentTest(list, 10, 1000);
	}

	public static void testList(IList<String> list) {
		// Tests des versions itératives
		list.add("Hello");
		list.add("World");
		System.out.println("Taille : " + list.size());
		assertEquals(2, list.size());
		System.out.println("Contient 'World' : " + list.contains("World"));
		assertEquals(true, list.contains("World"));
		assertEquals(false, list.contains("Master"));
		
		list.clear();
		assertEquals(0, list.size());
		System.out.println("Taille après clear : " + list.size());
	}

	private void runConcurrentTest(IList<String> list, int N, int M) {
		System.out.println("Running test of "+list.getClass().getSimpleName());
		testList(list);

		long startTime = System.currentTimeMillis();

		List<Thread> threads = new ArrayList<>();
		
		// Create threads to check contains for non-existent elements
		for(int i=0; i<N;i++) {
			threads.add(new Thread(new AddTask1(M, list)));
		}
		
		for(int i=0; i<N;i++) {
			threads.add(new Thread(new ContainsTask(M, list)));
		}		
		
		
		// Start all threads
		for(Thread t : threads) {
			t.start();
		}
		
		// Wait for all threads to finish
		for(Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		// Check that the list size is N * M
		assertEquals(list.size(), N*M);	
		assertEquals("List size should be N * M", N * M, list.size());
		
		long endTime = System.currentTimeMillis();
		System.out.println("Test completed in " + (endTime - startTime) + " milliseconds");
	}

	
	// TODO support pour les threads
		static class AddTask1 implements Runnable {
			private int M; 
			private IList<String> list;
			
			public AddTask1(int M, IList<String> list) {
				this.M=M;
				this.list=list;
			}
			
			@Override
			public void run() {
				for(int i=0; i<M;i++) { 
					list.add("Derya et Laulau");
				}
			}
		}
		
		static class ContainsTask implements Runnable {
			private int M; 
			private IList<String> list;
			
			public ContainsTask(int M, IList<String> list) {
				this.M=M;
				this.list=list;
			}
			
			@Override
			public void run() {
				for(int i=0; i<M;i++) { 
					list.contains("toto");
				}
			}
		}
}

