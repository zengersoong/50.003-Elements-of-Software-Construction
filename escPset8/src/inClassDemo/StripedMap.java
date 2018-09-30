package inClassDemo;
/**
 * not finished yet
 * @author zenger
 *
 */
public class StripedMap {
	//synchronization policy: buckets[n] guarded by locks[n%N_LOCKS]
	private static final int N_LOCKS = 16;
	private final Node[] buckets;// a sequence of linked-list that is storing the objects
	private final Object[] locks; // 
	
	public StripedMap (int numBuckets) {
		buckets = new Node[numBuckets];
		locks = new Object[N_LOCKS];
		
		for (int i = 0; i < N_LOCKS; i++) {
			locks[i] = new Object();
		}
	}
	
    public Object put(Object key, Object value) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next)
                if (m.key.equals(key)) {
                    m.value = value;
                    return m.value;
                }
            buckets[hash] = new Node(key,value,buckets[hash]);
        }
        return null;
    }
	
	public Object get (Object key) {
		int hash = hash(key);
		synchronized (locks[hash % N_LOCKS]) {
			for (Node m = buckets[hash]; m != null; m = m.next)
                if (m.key.equals(key)) {
                	return  buckets[hash];
                }
         // get the item in the given key in the map
			
		}
		
		return null;		
	}
	
	private final int hash (Object key) {
		return Math.abs(key.hashCode() % buckets.length);
	}
	
	public void clear () {
		//todo: remove all objects in the map
		
		for (int i = 0 ; i<buckets.length; i++) {
			synchronized(locks[i%N_LOCKS]) {
				for (Node m = buckets[i]; m != null; m = m.next) {
	                buckets[i]=null;
	                }
			}
		}
	}

	public int size () {
		//todo: count the number of elements in the map
		int returnNum=0;
		for (int i =0; i < buckets.length; i++) {
			for (Node m = buckets [i]; m!= null; m=m.next) {
				returnNum++;
			}
		}
		return returnNum;
	}

    class Node {
        Node next;
        Object key;
        Object value;
        Node(Object key, Object value, Node next) {
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }
}

