package cohortExercise6;



import java.util.ArrayList;
import java.util.List;

public class Topic implements Subject {

	private List<Observer> observers;
	private String message;
	private boolean changed;
	private final Object MUTEX= new Object();
	private String owner;
	
	public Topic(){
		this.observers=new ArrayList<>();
	}
	@Override
	public void register(Observer obj) {
		if(obj == null) throw new NullPointerException("Null Observer");
		synchronized (MUTEX) {
		if(!observers.contains(obj)) observers.add(obj);
		}
	}

	@Override
	public void unregister(Observer obj) {
		synchronized (MUTEX) {
		observers.remove(obj);
		}
	}

	@Override
	public void notifyObservers() {
		List<Observer> observersLocal = null;
		//synchronization is used to make sure any observer registered after message is received is not notified
		synchronized (MUTEX) {
			if (!changed)
				return;
			observersLocal = new ArrayList<>(this.observers);
			this.changed=false;
		}
		for (Observer obj : observersLocal) {
			obj.update();
		}

	}

	@Override
	public Object getUpdate(Observer obj) {
		return this.message;
	}
	
	//method to post message to the topic
	public void uploadMessage(String msg,String name,boolean edit,int postid){
		String owner="empty";
		if(edit==false) {
			System.out.println(postid+":"+ msg +"\n");
			owner=name;
			this.message = msg;
			this.changed = true;
			notifyObservers();
		}
		//New message posted \\
	
		//method is edited by poster
		if(edit==true) {
			System.out.println("Message has been edited-by original author: " + name);
		System.out.println(postid+":"+ msg+ "\n" );
		this.message = msg;
		this.changed = true;
		notifyObservers();
		
		}
		
		
		
	}
	

}