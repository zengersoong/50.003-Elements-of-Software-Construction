package cohortExercise2;

public class DemoShipFactory {
	public static void main(String[] args) {
			//Create a new shipFactory
	      EnemyShipFactory factory1 = new EnemyShipFactory();
	      //create two ships
	      EnemyShip ship1 = factory1.getShip("Regular");
	      EnemyShip ship2 = factory1.getShip("Rocket");
	      ship1.setName("titan1");
	      ship1.displayEnemyShip();
	      double infoDmg1 = ship1.getDamage();
	      System.out.println(infoDmg1);
	      //using same factory to manufacture the next ship but its a different ship
	      ship2.setName("Titan2");
	      ship2.displayEnemyShip();
	      double infoDmg2 =ship2.getDamage();
	      System.out.println(infoDmg2);
	      /*
	       * by using these Regular and rocket are just "names" exposed to the user to create the ships, 
	       * but they do not directly know the logic. as objects are created from a factory class
	       */
}
}
