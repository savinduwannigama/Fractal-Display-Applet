
public class Point { //used to create objects which represents a point on the canvas
				     //contains attributes necessary to map the point to the region of interest in the complex plane
	private double x, y, imUpperLimit, reLowerLimit, yScaleFactor, xScaleFactor; //these are the attributes required
	
	//constructor
	public Point(int width, int height, double reUpperLimit, double reLowerLimit, double imUpperLimit, double imLowerLimit) {
		this.imUpperLimit = imUpperLimit;
		this.reLowerLimit = reLowerLimit;
		this.yScaleFactor = (imUpperLimit - imLowerLimit)/(double)height;
		this.xScaleFactor = (reUpperLimit - reLowerLimit)/(double)width;
	}
	
	//setter and getter for x and y
	public void setXY(int x, int y) {
		this.x = (double)x;
		this.y = (double)y;
	}
	
	//getters for x and y
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	//maps the point on the canvas to a complex number in the REGION OF INTEREST
	//will be called from the public method getPointColor
	//takes the region of interest as arguments CAN GIVE THE REGION OF INTEREST AS AN ATTRIBUTE 
	public void mapToComplexPlane(Complex z) {
		double im, re;
		im = this.imUpperLimit - (this.y * this.yScaleFactor);
		re = this.reLowerLimit + (this.x * this.xScaleFactor);
		z.setReal(re);
		z.setImaginary(im);
	}
}
