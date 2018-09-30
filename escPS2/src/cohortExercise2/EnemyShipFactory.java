package cohortExercise2;

public class EnemyShipFactory {
	   //use getShape method to get object of type shape 
	   public static EnemyShip getShip(String ShipType){
	      if(ShipType == null){
	         return null;
	      }		
	      if(ShipType.equalsIgnoreCase("Regular")){
	         return new UFOEnemyShip();
	         
	      } else if(ShipType.equalsIgnoreCase("Big")){
	         return new BigUFOEnemyShip();
	         
	      } else if(ShipType.equalsIgnoreCase("Rocket")){
	         return new RocketEnemyShip();
	      }
	      
	      return null;
	   }
}
