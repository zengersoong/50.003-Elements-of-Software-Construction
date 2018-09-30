package cohortExercise9;

public class AdminStuff implements Employee {

	private String name;
	private float efficiency;
	
	public AdminStuff (String name, float efficiency) {
		this.name = name;
		this.efficiency = efficiency;
	}
	
	public String getName() {
		return name;
	}

	public float getEfficiency() {
		return efficiency;
	}
	@Override
	public void accept(SUTD visitor) {
		visitor.visit(this);
	}
}

