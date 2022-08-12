import java.awt.Color;

public class Julia extends Mandelbrot { //contains methods which perform operations required to find the Julia set
	
	//overloading the getPointColor method in the Mandelbrot class
	public static Color getPointColor(Point p, int iterations, Complex c) {
		p.mapToComplexPlane(z1); //maps the point p in the canvas to the complex value in the region of interest
		Complex.makeEqual(z, c); //sets z = c
		int iter = isElement(iterations);
		return chooseColor(iter, iterations);
		
	}
}
