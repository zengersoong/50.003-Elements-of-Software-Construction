package CohortExercise4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;



/*
 * 
 */
public class GeneralisedFuzzer {
	public static ArrayList<String> arr = new ArrayList();
	public static ArrayList<String> arrNew = new ArrayList();
	
	public String readFile(String fileName) {
		String line = null;
		
		
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);
            	

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
               // System.out.println(line);
                String Line =line;
                arr.add(Line);
            } 
            System.out.println(arr);

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
		return line;
    
		
		
		
	}
	//Swap function here
	public static String Swap(String Original) {
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
	
	
	public static String bitFlip(String original) {
		String finalStr="";
		byte[] mybytes = original.getBytes();
		for ( int n =0 ; n < mybytes.length; n++) {
			mybytes[n] = (byte) ~mybytes[n];
		}
		String result = new String(mybytes);
//		for(int i=0; i<original.length();i++) {
//			Character chars=original.charAt(i);
//			String oneChar=chars.toString();
//			String binary = new BigInteger(oneChar.getBytes()).toString(2);
//			Integer binaryInt = Integer.parseInt(binary);
//			Integer flipped = ~binaryInt;
//			String flippedString = flipped.toString();
//			String flippedBack = new String(new BigInteger(flippedString, 2).toByteArray());
//			finalStr = finalStr+flippedBack;
//		}
		return result;
		
//		String binary = new BigInteger(original.getBytes()).toString(2);
//		char[] chars = new char[binary.length()];
//		for (int i=0; i<binary.length();i++) {
//			chars[i]=(char)(binary.charAt(i) ^ 1);
//		}
//		String flipped = new String(chars);
//		System.out.println(flipped);
////		Integer binaryInt = Integer.parseInt(binary);
////		
////		//System.out.println(binaryInt);
////		Integer flipped = ~binaryInt;
////		String flippedString = flipped.toString();
//		String flippedBack = new String(new BigInteger(flipped, 2).toByteArray());
//		return flippedBack;
	}
	
	public static String trim(String original) {
		int lengthOg = original.length();
//		Random randTrimStart =new Random();
//		int RTS = randTrimStart.nextInt(lengthOg);
		Random randTrimEnd = new Random();
		int RTE = randTrimEnd.nextInt(lengthOg);
		String Trimmed = original.substring(0, RTE);
		return Trimmed;
	}
	
	
	
	
	public static void main(String[] args) {
		GeneralisedFuzzer GF = new GeneralisedFuzzer();
		GF.readFile("/home/zenger/eclipse-workspace/escPset4/src/CohortExercise4/test.txt");
		//System.out.println(arr.get(0));
		for(int i=0;i<arr.size();i++) {
			Random choice = new Random();
			int nextChoice = choice.nextInt(3);
			switch(nextChoice) {
			case 0:
				System.out.println("Line #"+i+" is using mutator: Swap");
				arrNew.add(Swap(arr.get(i)));
				//System.out.println(arrNew.get(i));
				break;
			case 1:
				System.out.println("Line #"+i+" is using mutator: Bitflip");
				arrNew.add(bitFlip(arr.get(i)));
				System.out.println(arrNew.get(i));
				break;
			case 2:
				System.out.println("Line #"+i+" is using mutator: Trimmed");
				arrNew.add(trim(arr.get(i)));
				//System.out.println(arrNew.get(i));
				break;
			default:
				System.out.println("ERROR HAS HAPPENED!");
				
			}
			System.out.println(arrNew);
				
		}
	}
	
	
	
	
	

}
