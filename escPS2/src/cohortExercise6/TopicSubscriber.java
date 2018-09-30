package cohortExercise6;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class TopicSubscriber extends Topic implements Observer{
	
	private String name;
	private Subject topic;
	public ArrayList<Integer> listOfMessagesID;
	private static int postID=0;
	private Map<Integer, String> messageDatabase;
	
	
	public TopicSubscriber(String nm){
		this.name=nm;
		this.listOfMessagesID = new ArrayList<Integer>();
		this.messageDatabase = new TreeMap<Integer, String>();
		
	}
	@Override
	public void update() {
		String msg = (String) topic.getUpdate(this);
		if(msg == null){
			System.out.println(name+":: No new message");
		}else
		System.out.println(name+":: Consuming message::"+msg);
	}
	//this is just meant for showing postmessages ID user is allowed to edit
	@Override
	public ArrayList<Integer> myPostHistoryID(){
		System.out.println(this.listOfMessagesID);
		return this.listOfMessagesID;
		
	}

	@Override
	public void setSubject(Subject sub) {
		this.topic=sub;
	}
//	public void writeMessage(String msg) {
//		System.out.println(msg + "[This message is posted by " + this.name);
//		
//	}

@Override
public void editPost(String editedMsg,int ID) {
	if(this.listOfMessagesID.contains(ID)) {
		
		
		this.messageDatabase.remove(ID);
		this.uploadMessage(editedMsg,this.name,true,ID);
		this.messageDatabase.put(ID, editedMsg);
	}else {
		System.out.println("Invalid ID, this message doesn't belong to you, you cannot edit it!");
	}
		
}
@Override
public void writePost(String msg) {
/*	Unqiue id can be random as well, but to make it simple,
	just increment the +1 for each new message created
	Random ran = new Random();
 */

	this.messageDatabase.put(postID, msg);
	this.listOfMessagesID.add((int) postID);
	System.out.println(this.name+" posted: ");
	this.uploadMessage(msg,this.name,false,(int) postID);
	postID += 1;


}
//This prints out all the message id and its respective message

@Override
public void showMyHistory() {
	 String messages= this.messageDatabase.toString().replace("]","\n");
	System.out.println(this.name+"'s message history: " + messages + "\n");
	
}

}