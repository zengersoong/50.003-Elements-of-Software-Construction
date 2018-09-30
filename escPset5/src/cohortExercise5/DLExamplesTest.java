package cohortExercise5;

import java.util.HashSet;
import java.util.Set;


/*
 * Deadlock is inevitable here, as the synchronized method setLocation requires the dispatcher to return an output while,
 * synchronized getImage() method requires the taxi to return an output, and since both are waiting for one another forever 
 * it is a deadlock.
 */

public class DLExamplesTest{
	private static int N = 100;
	
	public static void main(String[] args) {
		Dispatcher2[] dis2 = new Dispatcher2[N];
		Taxi2[] tax = new Taxi2[N];

		for (int i = 0; i < N; i++) {
			dis2[i] = new Dispatcher2();
		}

		for (int i = 0; i < N; i++) {
			tax[i] = new Taxi2 (dis2[i]);
			dis2[i].start();
			tax[i].start();
		}
	}
}

class Taxi2 extends Thread{
    Point2 location;
	private Point destination;
    private final Dispatcher2 dispatcher;

    public Taxi2(Dispatcher2 dis) {
        this.dispatcher = dis;
    }

	public synchronized Point2 getLocation() {
        return location;
    }

    public synchronized void setLocation(Point2 pointer) {
        this.location = pointer;
        if (pointer.equals(destination))
            dispatcher.notifyAvailable(this);
    }

    public synchronized Point getDestination() {
        return destination;
    }
    public void run() {
    	while(true) {
    	try {
    		Point2 pointer = new Point2();
			this.setLocation(pointer);
    		
    	}finally {
    		System.out.println("eh eh why are you Rounning?");

    	}
    	}
    	
    }
}

class Dispatcher2 extends Thread{
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher2() {
        taxis = new HashSet<Taxi>();
        availableTaxis = new HashSet<Taxi>();
    }

    public void notifyAvailable(Taxi2 taxi2) {
		// TODO Auto-generated method stub
		
	}

	public synchronized void addTaxi(Taxi taxi) {
        taxis.add(taxi);
    }

    public synchronized void notifyAvailable(Taxi taxi2) {
        availableTaxis.add(taxi2);
    }

    public synchronized Image getImage() {
        Image image = new Image();
        for (Taxi t : taxis)
            image.drawMarker(t.getLocation());
        return image;
    }
    public void run() {
    	
    	
    	try {
    		this.getImage();
    		
    	} finally {
    		System.out.println("is deadlocked yo");
    	}
    		
    	
    }
    
}

class Image2 extends Thread{
    public void drawMarker(Point p) {
    }
}

class Point2 {
	
}

