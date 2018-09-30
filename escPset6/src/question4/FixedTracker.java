package question4;



import java.util.HashMap;
import java.util.Map;

class test1 extends Thread {
	FixedTracker tracker;
	
	public test1 (FixedTracker tra) {
		this.tracker = tra;
	}
	
	public void run () {
		FixedTracker.MutablePoint loc = tracker.getLocation("somestring");
		loc.x = -1212000;
	}
}

//is this class thread-safe?Probably not
public class FixedTracker {
	//@guarded by this
	private final Map<String, MutablePoint> locations;
	

	public FixedTracker(Map<String, MutablePoint> locations) {
		this.locations = deepCopy(locations);
	}

	
	//is this an escape?Perhaps
	public synchronized Map<String, MutablePoint> getLocations () {
		return deepCopy(locations);
	}
	
	//is this an escape?
	public synchronized MutablePoint getLocation (String id) {
		MutablePoint loc = deepCopy(locations).get(id);
		return loc;
	}
	
	public synchronized void setLocation (String id, int x, int y) {
		MutablePoint loc = locations.get(id);
		
		if (loc == null) {
			throw new IllegalArgumentException ("No such ID: " + id);			
		}
		
		loc.x = x;
		loc.y = y;
	}
	private Map< String , MutablePoint > deepCopy(Map<String, MutablePoint> original){
		Map<String,MutablePoint> clonedHashMap = new HashMap<>();
		for(Map.Entry<String, MutablePoint> entry : original.entrySet()){
			clonedHashMap.put(entry.getKey(),entry.getValue());
		}
		return clonedHashMap;
	}
	
	
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