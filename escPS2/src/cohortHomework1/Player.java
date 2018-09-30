package cohortHomework1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Player{
	
	static int[][] winCombinations = {{1,2,3}, {4,5,6}, {7,8,9}, {1,5,9},{3,5,7},{1,4,7},{2,5,8},{3,6,9}};
	private  ArrayList<Integer>movesMade;
	private ticTackToeGame mGame;
	
	
	public Player(ticTackToeGame g) {
		mGame = g;
		movesMade = new ArrayList<Integer>();
		
	}
	/*
	 * ticTackToeBoard
	 * 1,2,3
	 * 4,5,6
	 * 7,8,9
	 */
	public boolean makeMove(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Please make your move, 1-9 integer");
		int n = sc.nextInt();
		if (mGame.getBoardInfo(n)) {
			this.movesMade.add(n);
			return true;
		}else {
			System.out.println("Please make your move, 1-9 integer");
			this.makeMove();
		}
		return false;
		
		
	}
	public boolean Checkwinner() {

		for ( int[] arr : winCombinations ) {
			if ( movesMade.contains(arr[0]) && movesMade.contains(arr[1]) && movesMade.contains(arr[2]) ) 
				return true;
		}
		return false;
		
	}
	

}
