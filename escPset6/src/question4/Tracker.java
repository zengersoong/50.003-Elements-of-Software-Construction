package question4;

import java.util.Map;

class test extends Thread {
	Tracker tracker;
	
	public test (Tracker tra) {
		this.tracker = tra;
	}
	
	public void run () {
		question4.Tracker.MutablePoint loc = tracker.getLocation("somestring");
		loc.x = -1212000;
	}
}

//is this class thread-safe?
public class Tracker {
	//@guarded by ???
	private final Map<String, MutablePoint> locations;
	
	//the reference locations, is it going to be an escape?
	private Tracker(Map<String, MutablePoint> locations) {
		//Need to make a mutatble location
		this.locations = locations;
	}
	
	//is this an escape?
	private synchronized Map<String, MutablePoint> getLocations () {
		
		return locations;
	}
	
	//is this an escape?
	public synchronized MutablePoint getLocation (String id) {
		MutablePoint loc = locations.get(id);
		
		return loc;
	}
	
	public synchronized void setLocation (String id, int x, int y) {
		//String are immutatble data type, 
		
		MutablePoint loc = locations.get(id);
		MutablePoint returnLoc  = new MutablePoint(loc.x,loc.y);
		
		if (returnLoc == null) {
			throw new IllegalArgumentException ("No such ID: " + id);			
		}
		
		returnLoc.x = x;
		returnLoc.y = y;
	}
	
	//this class is not thread-safe (why?) and keep it unmodified.
	class MutablePoint {
		public int x, y;

		public MutablePoint (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public MutablePoint (MutablePoint p) {
			this.x = p.x;
			this.y = p.y;
		}
	}
	
		
	
}
