package cohortHomework1;

import java.util.ArrayList;

public class TickTackToeFactory {
	ArrayList<Integer> universalBoard;
	public static ticTackToeGame getNewGame() {
		ArrayList<Integer> universalBoard = new ArrayList<Integer>();
		ticTackToeGame game1 = new ticTackToeGame();
		return game1;
	}
	

}
