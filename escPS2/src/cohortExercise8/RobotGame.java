package cohortExercise8;



public class RobotGame {

	public static void main(String[] args) {

		Robot r1 = new Robot("Big Robot");
		Robot r2 = new Robot("George v.2.1");
		Robot r3 = new Robot("R2");

		r1.setBehavior("aggressive");
		r2.setBehavior("defensive");
		r3.setBehavior("normal");
		
		r1.behave();
		r2.behave();
		r3.behave();

		//change the behaviors of each robot.
		r1.setBehavior("defensive");
		r2.setBehavior("normal");
		r3.setBehavior("aggressive");
		
		r1.behave();
		r2.behave();
		r3.behave();
	}
}