package cohortExercise7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class BarrierTimer implements Runnable {
    private boolean started;
    private long startTime, endTime;

    public synchronized void run() {
        long t = System.nanoTime();
        if (!started) {
            started = true;
            startTime = t;
        } else
            endTime = t;
    }

    public synchronized void clear() {
        started = false;
    }

    public synchronized long getTime() {
        return endTime - startTime;
    }
}

public class CasCounterTestTest{
    private BarrierTimer timer = new BarrierTimer();
    protected static final ExecutorService pool = Executors.newCachedThreadPool();
    //TODO: set up the data object here
    AtomicInteger counter= new AtomicInteger(0);
    protected final int nTrials, nThreads;
    protected CyclicBarrier barrier;
    protected final int nIncrements = 10000;
    private LockCounter locked = new LockCounter();

    public CasCounterTestTest(int nThreads, int trials) {
    	this.nThreads = nThreads;
    	this.nTrials = trials;
        barrier = new CyclicBarrier(nThreads + 1, timer);
    }
    public void lockcounterTest(){
        barrier = new CyclicBarrier(nThreads+1,timer);
        try {
            timer.clear();
            for (int i = 0; i < nThreads; i++) {
                pool.execute(new Runnable () {
                    public void run() {
                        try {
                            barrier.await();
                            for (int i = 0; i <= nIncrements; i++) {
                                if (locked.getValue()<i){locked.increment();}
                            }
                            barrier.await();
                        } catch(InterruptedException e) {
                        	e.printStackTrace();
                        } catch (BrokenBarrierException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                });
            }
            barrier.await();
            barrier.await();
            System.out.print("Total Time (Lock-based counter): " + timer.getTime()+ "\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void test() {
        try {
            timer.clear();
            for (int i = 0; i < nThreads; i++) {
                pool.execute(new Runnable () {
					public void run() {
			            try {
							barrier.await();						
							for (int i = 0; i <= nIncrements; i++) {
							    //TODO: a
								counter.compareAndSet(i-1,i); 
                                
							}
							barrier.await();
						} catch (InterruptedException | BrokenBarrierException e) {
							e.printStackTrace();
						}
					}                	
                });
            }
            barrier.await();
            barrier.await();
            System.out.print("Total Time(CAS): " + timer.getTime()+ "\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.lockcounterTest();
    }

    public static void main(String[] args) throws Exception {
        int tpt = 100000; 
        for (int nThreads = 10; nThreads <= 100; nThreads += 10) {
        	CasCounterTestTest test = new CasCounterTestTest(nThreads, tpt);
                System.out.print("Number of threads tested: " + nThreads + "\n");
                test.test();
                System.out.println();
                Thread.sleep(1000);
        }
        CasCounterTestTest.pool.shutdown();
    }
}

class LockCounter {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int increment() {
        return value++;
    }
}