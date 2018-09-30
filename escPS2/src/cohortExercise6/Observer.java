package cohortExercise6;

import java.util.ArrayList;

public interface Observer {
	
	//method to update the observer, used by subject
	public void update();
	
	//attach with subject to observe
	public void setSubject(Subject sub);
	public void editPost(String newMsg,int id);
	public void writePost(String msg);
	public ArrayList<Integer> myPostHistoryID();
	public void showMyHistory();
	
}