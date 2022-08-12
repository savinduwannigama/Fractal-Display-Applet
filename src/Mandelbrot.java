import java.awt.Color;


public class Mandelbrot { //contains methods which perform operations required to find the Mandelbrot set
	
	protected static Complex z = new Complex(), z1 = new Complex(), z2 = new Complex(); 
	
	//returns the number of iterations to get ABS(z)>2
	//will be called from the public method getPointColor
	protected static int isElement(int iterations) {
		int iter = 0;
		//Complex z1 = new Complex(0, 0), z2 = new Complex();
		for(iter=0; iter<iterations; iter++) {
			Complex.squareComplexNumber(z1); //z1 = z1^2;
			Complex.addComplexNumbers(z2, z1, z); //z2 = z1 + z; 
			if(z2.getAbsoluteValueSquared()>(double)4) //TRY WITHOUT THE TYPE CASTING
				break;
			Complex.makeEqual(z1, z2);  //sets z1 = z2
		}
		return iter;
	}
	
	//chooses the color depending on the total number of iterations and number of iterations taken
	protected static Color chooseColor(int iter, int iterations) {
		//int variation = 0; //give a value between 0 and number of iterations
		if(iter >= iterations) 
			return Color.black;
		else
			//return Color.getHSBColor((iter+variation)*360f/(iterations+variation), (iter+variation)*100f/(iterations+variation), (iter+variation)*100f/(iterations+variation));
			//return Color.white;
			return Color.getHSBColor(iter*4f/iterations,1,iter/(iter+5f));
	}

	
	//public method called from the Fractal class, returns the relevant color for the point
	public static Color getPointColor(Point p, int iterations) {
		p.mapToComplexPlane(z); //maps the point p in the canvas to the complex value in the region of interest
		z1.setImaginary(0);
		z1.setReal(0);
		int iter = isElement(iterations);
		return chooseColor(iter, iterations);
	}
}
