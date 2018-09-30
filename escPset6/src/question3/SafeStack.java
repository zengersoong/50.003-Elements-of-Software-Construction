package question3;

import java.awt.List;
import java.util.Stack;

public class SafeStack<E> extends Stack<Object>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private volatile long[] stackArray; //guarded by "this"
	
	private final int maxSize=10;
	//private volatile long[] stackArray;
	private volatile int top;


	public int getMaxSize() {
		return maxSize;
	}	
	
	public synchronized void pushIfNotFull(E e) {
		
			while (isFull()) {
				try {
					wait();
				}catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			stackArray[++top] = (long) e;
			notifyAll();			
		   
		}
		}
		
	
		
	private boolean isFull() {
		boolean toReturn=false;
		if(stackArray.length==maxSize) toReturn=true;
		return toReturn;
	}



	
	public synchronized long popIfNotEmpty(E e) {
			long toReturn; 
			
			while (isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			toReturn = stackArray[top--];
			notifyAll();			
		    return toReturn;
		}


		
	}

