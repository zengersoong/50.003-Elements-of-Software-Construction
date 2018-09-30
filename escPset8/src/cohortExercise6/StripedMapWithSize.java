package cohortExercise6;

public class StripedMapWithSize {//synchronization policy: buckets[n] guarded by locks[n%N_LOCKS]
	private static final int N_LOCKS = 16;
	private final Node[] buckets;
	private final Object[] locks;
	
	public StripedMapWithSize (int numBuckets) {
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
		//todo: get the item with the given key in the map
        int hash = hash(key);
        synchronized (locks[hash%N_LOCKS]){
            for(Node n = buckets[hash]; n!=null; n=n.next){
                if(n.key.equals(key)){
                    return n.value;
                }
            }
        }
		
		return null;		
	}
	
	private final int hash (Object key) {
		return Math.abs(key.hashCode() % buckets.length);
	}
	
	public void clear () {
		//todo: remove all objects in the map
        for(int i=0; i<locks.length;i++) {
            synchronized (locks[i]) {
                int counter = i;
                while (i < buckets.length) { 
                    buckets[i] = null;
                    i += N_LOCKS; 
                }
            }

        }
	}

	public int size () {
        int count =0;
        for(int i=0; i<locks.length;i++) {
            synchronized (locks[i]) { 
                int counter = i;
                while (i < buckets.length) { 
                    Node n1 = buckets[i];
                    while(n1.next!=null){ 
                        count+=1; //add current node
                        n1=n1.next; 
                    }
                    count+=1; 
                    i += N_LOCKS; 
                }
            }
        }
        return count;
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
