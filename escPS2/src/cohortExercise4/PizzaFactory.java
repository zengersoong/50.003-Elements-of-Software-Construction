package cohortExercise4;


import cohortExercise2.UFOEnemyShip;

public class PizzaFactory {
	public static Pizza getPizza(String pizzatype){
	      if(pizzatype == null){
		         return null;
		      }		
		      if(pizzatype.equalsIgnoreCase("cheese")){
		         return new CheesePizza();
		         
		      } else if(pizzatype.equalsIgnoreCase("greek")){
		         return new GreekPizza();
		         
		      } else if(pizzatype.equalsIgnoreCase("pepperoni")){
		         return new PepperoniPizza();
		      }
		      
		      return null;
		   }

	}


