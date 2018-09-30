package cohortExercise2;

abstract class EnemyShip {
	
	private String name;
	// sudiptac: damage factor distinguishes a ship
	private double amtDamage;
	
	public String getName() { return name; }
	public void setName(String newName) { name = newName; }
	
	public double getDamage() { return amtDamage; }
	public void setDamage(double newDamage) { amtDamage = newDamage; }
	
	public void displayEnemyShip(){		
		System.out.println(getName() + " is on the screen");
	}
}