package cohortHomework1;

import java.util.ArrayList;

public class ticTackToeGame {
	ArrayList<Integer> universalBoard = new ArrayList<Integer>();
	
	int turnSwitch;//1 = player 1 , 2 = player 2
	
	
	public boolean getBoardInfo(int n) {
		if(universalBoard.contains(n)){
			System.out.println("spot has been taken! Try again!");
			return false;
		}else {
			if(n>=1 || n<=9){
				universalBoard.add(n);
				return true;
			}else {
				System.out.println("illegal move!");
				return false;
			}
			
		}
		
	}

}
