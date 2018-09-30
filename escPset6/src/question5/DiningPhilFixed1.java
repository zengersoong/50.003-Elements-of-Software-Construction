

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilFixed1 {
	private static int N = 5;
	
	
	public static void main (String[] args) throws Exception {	
		Philosopher1[] phils = new Philosopher1[N];
		Fork1[] forks = new Fork1[N];
		ReentrantLock lock =new ReentrantLock();

		for (int i = 0; i < N; i++) {
			forks[i] = new Fork1(i);
		}

		for (int i = 0; i < N; i++) {
			phils[i] = new Philosopher1 (i, forks[i], forks[(i+N-1)%N],lock);
			phils[i].start();
		}
	}
}

class Philosopher1 extends Thread {
	private final int index;
	private final Fork1 left;
	private final Fork1 right;
	private ReentrantLock lock;
	
	public Philosopher1 (int index, Fork1 left, Fork1 right,ReentrantLock lock) {
		this.index = index;
		this.left = left;
		this.right = right;
		this.lock=lock;
	}
	
	public void run() {
		
		Random randomGenerator = new Random();
		
		try {
			while(true){
				Thread.sleep(1000);
				System.out.println("Phil " + index + " finishes thinking.");
				boolean flag = lock.tryLock(1000, TimeUnit.MILLISECONDS);
				
				if(flag){
					try{
					left.pickup();				
					System.out.println("Phil " + index + " picks up left fork.");
					right.pickup();
					System.out.println("Phil " + index + " picks up right fork.");
					Thread.sleep(randomGenerator.nextInt(1000)); //eating
					System.out.println("Phil " + index + " finishes eating.");
					left.putdown();
					System.out.println("Phil " + index + " puts down left fork.");
					right.putdown();							
					System.out.println("Phil " + index + " puts down right fork.");
			}finally{
				lock.unlock();
			}
		}else{
			System.out.println("Phil " + index + " waiting to get fork.");
		}
				
			}	

		} catch (InterruptedException e) {
			System.out.println("Don't disturb me while I am sleeping, well, thinking.");
		} 
	}
}

class Fork1 {
	private final int index;
	private boolean isAvailable = true;
	
	
	public Fork1 (int index) {
		this.index = index;
	}
	
	public synchronized void pickup () throws InterruptedException {
		
		while (!isAvailable) {
			wait();
		}
		
		isAvailable = false;
		notifyAll();
	
	}
	
	public synchronized void putdown() throws InterruptedException {
		
		while (isAvailable) {
			wait();
		}

		isAvailable = true;
		notifyAll();
	
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