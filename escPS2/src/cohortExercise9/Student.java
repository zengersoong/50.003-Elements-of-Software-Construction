package cohortExercise9;

public class Student implements Employee {

	private String name;
	private float GPA;
	
	public Student (String name, float GPA) {
		this.name = name;
		this.GPA = GPA;
	}
	
	public String getName() {
		return name;
	}

	public float getGPA() {
		return GPA;
	}


	@Override
	public void accept(SUTD visitor) {
		 visitor.visit(this);
	}

}
