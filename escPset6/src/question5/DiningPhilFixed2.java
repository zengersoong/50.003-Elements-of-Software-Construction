package question5;

import java.util.Random;
/*
 * this method ultilises the lock on fork object.
 * Synchronizing the method to get reference to the get available
 * Syncrhonizing the method set available
 */

public class DiningPhilFixed2 {
	private static int N = 5;
	
	public static void main (String[] args) throws Exception {	
		Philosopher2[] phils = new Philosopher2[N];
		Fork2[] forks = new Fork2[N];

		for (int i = 0; i < N; i++) {
			forks[i] = new Fork2(i);
		}

		for (int i = 0; i < N; i++) {
			phils[i] = new Philosopher2 (i, forks[i], forks[(i+N-1)%N]);
			phils[i].start();
		}
	}
}

class Philosopher2 extends Thread {
	private final int index;
	private final Fork2 left;
	private final Fork2 right;
	
	public Philosopher2 (int index, Fork2 left, Fork2 right) {
		this.index = index;
		this.left = left;
		this.right = right;
	}
	
	public void run() {
		Random randomGenerator = new Random();
		try {
			while (true) {
				Thread.sleep(randomGenerator.nextInt(100)); //not sleeping but thinking
				System.out.println("Phil " + index + " finishes thinking.");
				left.pickup();				
				System.out.println("Phil " + index + " picks up left fork.");
				right.pickup();
				System.out.println("Phil " + index + " picks up right fork.");
				Thread.sleep(randomGenerator.nextInt(100)); //eating
				System.out.println("Phil " + index + " finishes eating.");
				left.putdown();
				System.out.println("Phil " + index + " puts down left fork.");
				right.putdown();							
				System.out.println("Phil " + index + " puts down right fork.");
			}
		} catch (InterruptedException e) {
			System.out.println("Don't disturb me while I am sleeping, well, thinking.");
		} 
	}
}

class Fork2 {
	private final int index;
	private volatile boolean isAvailable = true;
	
	public Fork2 (int index) {
		this.index = index;
	}
	
	public synchronized void pickup () throws InterruptedException {
		
		while (!getIsAvailable()) {
			wait();
		}
		synchronized(this) {
		setAvailable(false);
		notifyAll();
	}
	}
	
	public synchronized void putdown() throws InterruptedException {
		
		while (getIsAvailable()) {
			wait();
		}
		synchronized(this) {
		setAvailable(true);
		notifyAll();
		}
	}
	public synchronized boolean getIsAvailable() {
		return isAvailable;
	}
	public synchronized void setAvailable(boolean b) {
		isAvailable=b;
	}
	
	public String toString () {
		if (isAvailable) {
			return "Fork " + index + " is available.";			
		}
		else {
			return "Fork " + index + " is NOT available.";						
		}
	}
}