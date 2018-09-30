package CohortExercise3;

import java.util.ArrayList;
import java.util.Random;

public class stringMutator {
	public static String Mutate(String Original) {
		String newString = "";
		int stringLength = Original.length();
		Random rand = new Random();
		Random rand1 = new Random();
		int randomSelect = rand.nextInt(stringLength-1); 
		int leftOrRight = rand1.nextInt(1);
		ArrayList stringArr = new ArrayList<Integer>();
		
		
		for(int i=0; i<stringLength;i++) {
			stringArr.add(Original.charAt(i));
		}
		//this is the last character you cannot swap with right most number
		if(randomSelect == stringLength) {
			char c1 = (char) stringArr.get(stringLength-1);
			char c2 = (char) stringArr.get(stringLength);
			stringArr.set(stringLength-1, c2);
			stringArr.set(stringLength, c1);
			newString = stringArr.toString();
		}else {
			//this is the first character you cannot switch with leftmost character
			if(randomSelect == 0) {
				char d1 = (char) stringArr.get(0);
				char d2 = (char) stringArr.get(1);
				stringArr.set(0, d2);
				stringArr.set(1, d1);
				newString = stringArr.toString();
				
			}else {
				char e1 = (char) stringArr.get(randomSelect);
				char e2 = (char) stringArr.get(randomSelect-1);
				char e3 = (char) stringArr.get(randomSelect+1);
				//SwitchWithLeft
				if(leftOrRight==0) {
					stringArr.set(randomSelect, e2);
					stringArr.set(randomSelect-1, e1);
					
					for(int i=0; i<stringArr.size();i++) {
//						System.out.println(stringArr.get(i));
						newString = newString+stringArr.get(i);
					}
				
				}else {
					stringArr.set(randomSelect, e3);
					stringArr.set(randomSelect+1, e1);
					
					
					for(int i=0; i<stringArr.size();i++) {
//						System.out.println(stringArr.get(i));
						newString = newString+stringArr.get(i);
					}
				}
			}
			
		}
		//System.out.println(stringArr);
		System.out.println(newString.toString());


		return newString;
		
	}
	
	public static void main(String[] args) {
		stringMutator sm = new stringMutator();
		sm.Mutate("testing");
	}

}
