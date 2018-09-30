package cohortExercise4;

abstract class Pizza {
	private String type;
	
	public void setType(String type) {
		this.type=type;
	}
	public String getType() {
		return type;
	}
	public void displayPizzaType() {
		System.out.println("This is a "+type+ " pizza.");
	}

}
