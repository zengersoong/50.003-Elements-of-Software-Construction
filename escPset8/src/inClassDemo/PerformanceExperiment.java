package inClassDemo;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class PerformanceExperiment {
    private static final int NUM_THREADS = 100;
    //private static BigInteger n = new BigInteger("1127451830576035879");
    //private static BigInteger n = new BigInteger("4294967297");
    private static BigInteger n = new BigInteger("239839672845043");

    public static void main(String[] args) throws InterruptedException {
    	BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable> (1000);    	
    	
    	for (int i = 0; i < 10; i++) {
    		queue.add(new Runnable () {
    			public void run() {
    				factor(n);
    			}    			
    		});
    	}
            
    	Thread[] threads = new Thread[NUM_THREADS];
    	
    	long startTime = System.currentTimeMillis();
        for (int i = 0; i < NUM_THREADS; i++) {
        	threads[i] = new Slave(queue);
        	threads[i].start();
        }
        
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }      
        long endTime = System.currentTimeMillis();
        System.out.println("Time spent: " + (endTime - startTime));
    }    
    
    public static BigInteger factor(BigInteger n) {
		BigInteger i = new BigInteger("2");
		BigInteger zero = new BigInteger("0");
		
		while (i.compareTo(n) < 0) {			
			//System.out.println("trying" + i);
			if (n.remainder(i).compareTo(zero) == 0) {
				return i;
			}
			
			i = i.add(new BigInteger("1"));
		}
		
		assert(false);
		return null;
	}
}

class Slave extends Thread {
    private final BlockingQueue<Runnable> queue;
    
    public Slave (BlockingQueue<Runnable> queue) {
    	this.queue = queue;
    }
    
    public void run() {
    	while (true) {
    		try {
        		Runnable task = queue.poll();
        		task.run();    			
    		}
    		catch (Exception e) {
    			break;
    		}
    	} 
    }
}