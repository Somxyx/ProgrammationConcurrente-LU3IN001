package pc.test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import pc.Repeat;

public class TestRepeat {
	
	@Test
	public void testComplexity() {
		long time = System.currentTimeMillis();
		
		@SuppressWarnings("unused")
		String rep = Repeat.repeat('a', 100000);
		
		long elapsed = System.currentTimeMillis() - time;
		System.out.println("Elapsed time: " + elapsed + "ms");
		assertTrue(elapsed < 3000);
	}
}
