package pc.philo;

public class TestPhilo {

	public static void main (String [] args) {
		final int NB_PHIL = 5;
		Thread [] tPhil = new Thread[NB_PHIL];
		Fork [] tChop = new Fork[NB_PHIL];

		for(int i=0; i<NB_PHIL; i++) {
			tChop[i]=new Fork();
		}

		tPhil[0] = new Thread(new Philosopher(tChop[0],tChop[1]));
		tPhil[1] = new Thread(new Philosopher(tChop[1],tChop[2]));
		tPhil[2] = new Thread(new Philosopher(tChop[2],tChop[3]));
		tPhil[3] = new Thread(new Philosopher(tChop[3],tChop[4]));
		tPhil[4] = new Thread(new Philosopher(tChop[3],tChop[4]));
		
		for(Thread t : tPhil) {
			t.start();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(Thread t : tPhil) {
			t.interrupt();
			if (t.isInterrupted()) {
				System.out.println("mauvaise interruption");
				break;
			}
		}
		
		for(Thread t : tPhil) {
			try {
				t.interrupt();
				t.join();
	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Fin du programme");

	}
}






