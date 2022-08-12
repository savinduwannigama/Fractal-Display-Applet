
public class Complex { //used to create objects which represents a complex number
					   //contains methods which perform necessary operations on complex numbers
	private double re, im; //real and imaginary parts of the complex number
	
	//constructors
	public Complex(double real, double imaginary) {
		this.re = real;
		this.im = imaginary;
	}
	public Complex() {}
	
	//getters and setters
	public void setReal(double re) {
		this.re = re;
	}
	void setImaginary(double im) {
		this.im = im;
	}
	public double getReal() {
		return this.re;
	}
	public double getImaginary() {
		return this.im;
	}
	
	//returns the square of the absolute value of the object
	public double getAbsoluteValueSquared() {
		return Math.pow(this.re, 2) + Math.pow(im, 2);
	}
	
	//squares the complex number given as the argument
	public static void squareComplexNumber(Complex z) {
		double imaginary, real;
		imaginary = 2*z.getImaginary()*z.getReal();
		real = Math.pow(z.getReal(), 2) - Math.pow(z.getImaginary(), 2);
		z.setImaginary(imaginary);
		z.setReal(real);
	}
	
	//adds z1 and z2, and stores in z3
	public static void addComplexNumbers(Complex z3, Complex z2, Complex z1) {
		z3.setImaginary(z1.getImaginary() + z2.getImaginary());
		z3.setReal(z1.getReal() + z2.getReal());
	}
	
	//sets z1 = z2
	public static void makeEqual(Complex z1, Complex z2) {
		z1.setImaginary(z2.getImaginary());
		z1.setReal(z2.getReal());
	}
}
