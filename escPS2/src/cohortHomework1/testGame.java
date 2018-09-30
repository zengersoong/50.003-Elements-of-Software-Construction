package cohortHomework1;

import java.util.Random;

public class testGame {
	
	public static void main(String[] args) {
		
		TickTackToeFactory fac1 = new TickTackToeFactory();
		ticTackToeGame newGame = fac1.getNewGame();
		Player player1 =new Player(newGame);
		Player player2 =new Player(newGame);
		Random ran = new Random();
		int turnSwitch = ran.nextInt(1);
		while(true) {
			if(turnSwitch==0) {
				System.out.println("Player 1's turn:");
				player1.makeMove();
				turnSwitch=1;
				if(player1.Checkwinner()) {
					System.out.println("Player 1 won!");
					break;
				};
			}else if (turnSwitch==1) {
				System.out.println("Player 2's turn:");
				player2.makeMove();
				turnSwitch=0;
				if(player2.Checkwinner()) {
					System.out.println("Player 2 won!");
					break;
				};
				
			}
			
		
		
		
	}
		System.out.println("game ends");
	}
}
