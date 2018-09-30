package cohortExercise6;

import java.util.concurrent.atomic.AtomicInteger;

public class FirstError {
	
	
	public void getAtom() {
		
	}
	
	public static void main(String args[]){  
		
		int numberofThreads = 10000;
		A[] threads = new A[numberofThreads];
	
		for (int i = 0; i < numberofThreads; i++) {
			threads[i] = new A();
			threads[i].start();
		}
		
		try {
			for (int i = 0; i < numberofThreads; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			System.out.println("some thread is not finished");
		}
		
		System.out.print("The result is ... ");
		System.out.print("wait for it ... ");
		A B = new A();
		System.out.println(B.getCount());		
	}
}

class A extends Thread {
	
	
	public static AtomicInteger count = new AtomicInteger(0);
	public void run () {
		int myCounter = count.incrementAndGet();
//		FirstError.count++;
		System.out.println(myCounter);
	}	
	public AtomicInteger getCount() {
		return count;
	}
}

/*
AtomicInteger x = new AtomicInteger(0)
x.incrementAndGet() //increments x by 1 atomically
*/