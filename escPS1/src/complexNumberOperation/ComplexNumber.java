/*Done by Zenger Soong*/
package complexNumberOperation;

public class ComplexNumber {
	private double a;
	private double b;
	
	
	
	
//------Constructors below------//
	
public ComplexNumber() {
		this(0, 0);
	}
//take in two doubles
public ComplexNumber(double a, double b) {
	this.a = a;
	this.b = b;
}

//return the imaginery part of the complex number
public double getImaginery() {
	return b;
}
//return the real part of the complex number
public double getReal() {
	return a;
}
//------Below are all the operations of a complex number------//


/*addition*/
public ComplexNumber add(ComplexNumber secondComplexNumber) { // returns a ComplexNumber 
	return new ComplexNumber(a + secondComplexNumber.a, 
		b + secondComplexNumber.b); 
}
public ComplexNumber subtract(ComplexNumber secondComplexNumber) {
	return new ComplexNumber(a - secondComplexNumber.a, 
			b - secondComplexNumber.b); 
}
/* Multiply*/
public ComplexNumber multiply(ComplexNumber secondComplexNumber) {
	return new ComplexNumber(a * secondComplexNumber.a - b * secondComplexNumber.b, // (a+ib)(c+id) since i**2 is -1, i**2(bd) would be a negative 
		b * secondComplexNumber.a + a * secondComplexNumber.b);
}

/*Divide*/
//using the Conjugate method to simplify the division
public ComplexNumber divide(ComplexNumber secondComplexNumber) {
	return new ComplexNumber((a * secondComplexNumber.a + b * secondComplexNumber.b) /
		(Math.pow(secondComplexNumber.a, 2) + Math.pow(secondComplexNumber.b, 2)),
		(b * secondComplexNumber.a - a * secondComplexNumber.b) /
		(Math.pow(secondComplexNumber.a, 2) +  Math.pow(secondComplexNumber.b, 2)));
}
 //return a string in the format of a+ib
/* Using the ? : Operator here */
// if b is true , return a +"" else(a+ib )
@Override
public String toString() {
return b == 0 ? a + "" : "(" + a + " + " + b + "i)";
}
	

}
