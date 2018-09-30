package cohortExercise7;

import java.util.concurrent.atomic.AtomicInteger;

public class LockStaticVariables {
	public static volatile int saving = 5000;
	public static volatile int cash = 0;
	
	
	public static void main(String args[]){   	
		int numberofThreads = 10000;
		WD[] threads = new WD[numberofThreads];
	
		for (int i = 0; i < numberofThreads; i++) {
			threads[i] = new WD();
			threads[i].start();
		}
		
		try {
			for (int i = 0; i < numberofThreads; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			System.out.println("some thread is not finished");
		}
		
		System.out.println("The cash result is: " + LockStaticVariables.cash);
		
		System.out.println("The saving result is: " + LockStaticVariables.saving);
	}
}

class WD extends Thread {	
	static boolean stopThread=true;
	public void run () {
		
		synchronized(WD.class) {
		if(LockStaticVariables.saving >= 1000) {

				LockStaticVariables.saving = LockStaticVariables.saving - 1000;
				LockStaticVariables.cash = LockStaticVariables.cash + 1000;
				
			System.out.println("Transfering...");
			}
		}
			
		}
//
//	if(stopThread)break;
//	if (n.remainder(init).compareTo(zero) == 0) {
//		System.out.println("Got it: " + init);
//		stopThread=true;
	
}


