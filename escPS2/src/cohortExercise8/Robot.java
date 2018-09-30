package cohortExercise8;

public class Robot implements IBehaviour{
	
	String name;
	String behaviour;

	public Robot (String name)
	{
		this.name = name;
	}

	public void behave ()
	{
		
		if(this.behaviour=="aggressive") {
			System.out.println(this.name+" acts aggressively");
		}
		else if(this.behaviour=="defensive") {
			System.out.println(this.name+" acts defensively");
		}
		else if(this.behaviour=="normal") {
			System.out.println(this.name+" acts normally");
		}else {
			System.out.println("robot may have a invalid behaviour set or hav yet to find his behaviour");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	public void setBehavior(String behav) {
		this.behaviour=behav;
	}

	@Override
	public int moveCommand() {
		if(this.behaviour=="aggressive") {
			return 3;
		}
		if(this.behaviour=="defensive") {
			return -1;
		}
		if(this.behaviour=="normal") {
			return 1;
		}
		return 0;
	}
}
	



