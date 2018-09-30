package classDemo;

public class MyStack {
	//-----step one -----//
	private final int maxSize;
	private volatile long[] stackArray;
	private volatile int top; 
	
	public MyStack(int s) { 
		maxSize = s;
	    stackArray = new long[maxSize];
	    top = -1;
	}
	
	public synchronized void push(long j) {
		while (isFull()) {
			try {
				wait();
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		stackArray[++top] = j;
		notifyAll();			
	   
	}
	}
	public synchronized long  pop() {
		long toReturn; 
		while (isEmpty()) {
			try {
				wait();
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		toReturn = stackArray[top--];
		notifyAll();			
	    return toReturn;
	}
	
	public synchronized long peek() {
		long toReturn = -1 ;
		while(isEmpty()) {
			try {
				wait();
				
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	    toReturn=stackArray[top];
	    notifyAll();			
	    
		}
		return toReturn;
	}

	public synchronized boolean isEmpty() {
		return (top == -1);
	}
	
	public synchronized boolean isFull() {
		return (top == maxSize - 1);
	}
}