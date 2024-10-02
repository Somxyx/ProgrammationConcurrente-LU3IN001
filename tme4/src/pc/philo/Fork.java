package pc.philo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
	private Lock lock = new ReentrantLock();
	
	public void acquire () {
		try {
			lock.lockInterruptibly();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
    }
	
	
	public void release () {
		lock.unlock();
	}
}
