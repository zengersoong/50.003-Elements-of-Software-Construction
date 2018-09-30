package cohortExercise6;

public class SocialMediaTester {

	public static void main(String[] args) {
		//create subject
		Topic topic = new Topic();
		
		//create observers
		Observer Alice = new TopicSubscriber("Alice");
		Observer Bob = new TopicSubscriber("Bob");
		Observer Cindy = new TopicSubscriber("Cindy");
		
		//register observers to the subject
		topic.register(Alice);
		topic.register(Bob);
		topic.register(Cindy);
		
		//attach observer to subject
		Alice.setSubject(topic);
		Bob.setSubject(topic);
		Cindy.setSubject(topic);
		
		
		Bob.writePost("hello");
		Alice.writePost("there");
		Bob.showMyHistory();
		
		Bob.editPost("Goodbye", 0);
		Alice.editPost("here", 0);
		Alice.showMyHistory();
		Bob.showMyHistory();
		Bob.myPostHistoryID();
	}
}