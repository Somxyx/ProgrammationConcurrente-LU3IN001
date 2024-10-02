package pc.philo;

public class Philosopher implements Runnable {
	private Fork left;
	private Fork right;

	public Philosopher(Fork left, Fork right) {
		this.left = left;
		this.right = right;
	}

	public void run() {
		while(!Thread.interrupted()) {
			this.think();
			left.acquire();
			System.out.println(Thread.currentThread().getName() + " has one fork");
			right.acquire();
			System.out.println(Thread.currentThread().getName() + " has one fork");
			this.eat();
			left.release();
			right.release();
		}
		
	}

	private void eat() {
		System.out.println(Thread.currentThread().getName() + " is eating");
	}

	private void think() {
		System.out.println(Thread.currentThread().getName() + " is thinking");
	}
}
