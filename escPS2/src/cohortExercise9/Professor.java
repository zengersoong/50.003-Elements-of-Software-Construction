package cohortExercise9;

public class Professor implements Employee {
	private String name;
	private int no_of_publications;

	public Professor (String name, int no_of_publications) {
		this.name = name;
		this.no_of_publications = no_of_publications;
	}
	
	public String getName () {
		return name;
	}

	public int getNo_of_publications() {
		return no_of_publications;
	}

	@Override
	public void accept(SUTD visitor) {
		visitor.visit(this);
	}

}
