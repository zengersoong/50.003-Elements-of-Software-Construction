package CohortExercise2;
import java.util.Random;
public class alphaNumericFuzzer {
	public String finalString="";
	public Integer length;
	
	
	
	
	public alphaNumericFuzzer() {
		
		Random rLength = new Random();
		length = rLength.nextInt(1024);
		
	}
	
	
	public void generateNextAlpha() {
		Random alphaChar = new Random();
		char c = (char) (alphaChar.nextInt(26) + 'a');
		
		finalString = finalString+c;
		
		
	}
	public void generateNextInteger() {
		Random rInteger = new Random();
		Integer integer = rInteger.nextInt(9);
		String stringInteger = integer.toString();
		finalString =finalString+stringInteger;
		
		
	}
	public String runFuzzer() {

		for (int i=0;i <this.length;i++) {
			Random boolAlphaOrInteger = new Random();
			int choice = boolAlphaOrInteger.nextInt(2);
			if (choice == 0) this.generateNextInteger();
			else this.generateNextAlpha();		
		}
		
		return finalString;
		
	}
	public static void main(String[] args) {
		alphaNumericFuzzer AZ = new alphaNumericFuzzer();
		System.out.println("The length of the string is "+ AZ.length+"\n"+"The generated String is :"+ "\n"+AZ.runFuzzer());
		//System.out.println((char)('a'+24));
		
	}
}
