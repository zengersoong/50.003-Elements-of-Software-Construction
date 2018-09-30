/*Done by Zenger Soong*/
package complexNumberOperation;

import java.util.Scanner;

public class ComplexOperation {
	public static void main(String[] args) {

		
		System.out.print("Enter the first real number for first complex number: ");
		ComplexNumber complexNumber1 = getComplex();
		System.out.print("Enter the second real number for the second complex number: ");
		ComplexNumber complexNumber2 = getComplex();

		// displays the result of their addition, subtraction, 
		// multiplication, division, and absolute value
		/**Implementation of a choice string being printed out **/
		while(true) {
		System.out.print("Do you want to add them together? \n Press 1\n");
		System.out.print("Do you want to subtract the first complex Number with the second? \n Press 2\n");
		System.out.print("Do you want to multiply them together? \n Press 3\n");
		System.out.print("Do you want to divide the first by the second complex number? \n Press 4\n");
		Scanner inputChoice =  new Scanner(System.in);
		String choice = inputChoice.next();
		int chosen =  Integer.parseInt(choice);
		// Stop invalid inputs
		if (chosen>4 ||chosen<1 ||chosen!=(int)chosen) {
			System.out.println("Number choice invalid! Please re-enter your choice of operation"); 
		}
		
		
		switch(chosen) {
		
		case 1:
		System.out.print("Result is "+ complexNumber1 + " + " + complexNumber2 + " = \n");
		print(complexNumber1.add(complexNumber2));
		break;
		case 2:
		System.out.print("Result is "+complexNumber1 + " - " + complexNumber2 + " = \n"); 
		print(complexNumber1.subtract(complexNumber2));
		break;
		case 3:
		System.out.print("Result is "+complexNumber1 + " * " + complexNumber2 + " = \n"); 
		print(complexNumber1.multiply(complexNumber2));
		break;
		case 4:
		System.out.print("Result is "+complexNumber1 + " / " + complexNumber2 + " = \n"); 
		print(complexNumber1.divide(complexNumber2));
		break;

		}
		}
	}

	/** Display result */
	public static void print(ComplexNumber n) {
		if (n.getImaginery() == 0)
			System.out.println(n.getReal());
		else
			if(n.getImaginery()<0)System.out.println(n.getReal()+ n.getImaginery()+"i"); // remove redundant "-" if imaginary already negative
			else {
			System.out.println(n.getReal() + " + " + 
			n.getImaginery() + "i");
			}
	}
// Need to implement a better RE version
	/** Return user input as a complex number */
	public static ComplexNumber getComplex() {
		// Create a Scanner object
		Scanner input = new Scanner(System.in);
		double[] c = new double[2];
		for (int i = 0; i < c.length; i++) {
		 	c[i] = input.nextDouble(); 
		 	if(i==0)System.out.println("Please enter imaginary part");
		}
		return new ComplexNumber(c[0], c[1]); 
	}

}
