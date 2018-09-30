package cohortExercise4;

public class DemoPizzaFactory {
	public static void main(String[] args) {
		PizzaFactory factory1 = new PizzaFactory();
		Pizza order1 = factory1.getPizza("cheese");
		Pizza order2 = factory1.getPizza("greek");
		Pizza order3 = factory1.getPizza("pepperoni");
		order1.displayPizzaType();
		order2.displayPizzaType();
	}

}
