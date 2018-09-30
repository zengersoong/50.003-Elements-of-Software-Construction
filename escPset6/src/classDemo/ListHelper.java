package classDemo;

import java.util.ArrayList;
import java.util.Collections;

public class ListHelper<E> {
	public java.util.List<E> list = Collections.synchronizedList(new ArrayList<E>());
		
	//thread-safe
	public boolean putIfAbsent(E x) {
		synchronized (list) { //locking on the list itself
			boolean absent = !list.contains(x);
			if (absent) {
				list.add(x);
			}
			
			return absent;			
		}
	}
}