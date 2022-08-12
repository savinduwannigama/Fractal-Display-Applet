import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;


@SuppressWarnings("serial")
public class Fractal extends JPanel {
	private String setName;
	private BufferedImage image; //will be used to create a PNG image of the Graph printed 
	private int frameHeight = 800, frameWidth = 800, iterations = 1000;
	private double reUpperLimit = 1, reLowerLimit = -1, imUpperLimit = 1, imLowerLimit = -1; //default values for the region of interest
	public Complex c = new Complex(-0.4, 0.6); //default values for c (for the julia set)
	
	//constructor to use if 5 arguments given for mandelbrot set
	public Fractal(String setName, double r_l, double r_u, double i_l, double i_u, int iter) {
		super();
		setPreferredSize(new Dimension(this.frameWidth, this.frameHeight));
		this.reLowerLimit = r_l;
		this.reUpperLimit = r_u;
		this.imLowerLimit = i_l;
		this.imUpperLimit = i_u;
		this.iterations = iter;
		this.setName = setName;
	}
	
	//constructor to use if 4 arguments given for mandelbrot set
	public Fractal(String setName, double r_l, double r_u, double i_l, double i_u) {
		super();
		setPreferredSize(new Dimension(this.frameWidth, this.frameHeight));
		this.reLowerLimit = r_l;
		this.reUpperLimit = r_u;
		this.imLowerLimit = i_l;
		this.imUpperLimit = i_u;
		this.setName = setName;
	}
	
	//constructor to use if 3 arguments given for Julia set
	public Fractal(String setName, double re, double im, int iter) {
		super();
		setPreferredSize(new Dimension(this.frameWidth, this.frameHeight));
		this.c.setReal(re);
		this.c.setImaginary(im);
		this.iterations = iter;
		this.setName = setName;
	}
	
	//constructor to use if no arguments given for both julia and mandelbrot
	public Fractal(String setName) {
		super();
		setPreferredSize(new Dimension(this.frameWidth, this.frameHeight));
		this.setName = setName;
	}
	
	//this does 2 tasks: 
	//1) prints the pattern as a graphic on the JPanel
	//2) generates a PNG file with the given pattern (will be saved inside the same folder) 
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//to create an image
		this.image = new BufferedImage(this.frameWidth, this.frameHeight, BufferedImage.TYPE_INT_RGB);
		Point p = new Point(this.frameWidth, this.frameHeight, this.reUpperLimit, this.reLowerLimit, this.imUpperLimit, this.imLowerLimit); //sets the plane for the point
		if(setName.contentEquals("Julia"))
			printGraphJulia(p, g);
		else
			printGraphMandelbrot(p, g);
		try {
		ImageIO.write(this.image, "PNG", new File("Fractals.png"));
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//to print the Mandelbrot set
	private void printGraphMandelbrot(Point p, Graphics g) {
		for(int h=0; h<this.frameHeight; h++) {
			for(int w=0; w<this.frameWidth; w++) {
				p.setXY(w, h);
				Color color = Mandelbrot.getPointColor(p, this.iterations);
				printPoint((Graphics2D)g, p, color);		
				this.image.setRGB(w, h, color.getRGB());
			}
		}
	}
	
	//to print the Julia set
	private void printGraphJulia(Point p, Graphics g) {
		for(int h=0; h<this.frameHeight; h++) {
			for(int w=0; w<this.frameWidth; w++) {
				p.setXY(w, h);
				Color color = Julia.getPointColor(p, this.iterations, this.c);
				printPoint((Graphics2D)g, p, color);		
				this.image.setRGB(w, h, color.getRGB());
			}
		}
	}
	
	//method to print a point on a given coordinate p
	private void printPoint(Graphics2D panel, Point p, Color color) {
		panel.setColor(color);
		panel.draw(new Line2D.Double(p.getX(), p.getY(), p.getX(), p.getY())); 
	}
	
	//print an error for wrong arguments
	private static void printError() {
		System.out.println("INVALID USAGE OF ARGUMENTS\n");
		System.out.println("USAGE:");
		System.out.println("\nTo plot the Mandelbrot set:");
		System.out.println("\t$java Fractal Mandelbrot <real axis minimum> <real axis maximum> <imaginary axis minimum> <imaginary axis maximum>");
		System.out.println("\t$java Fractal Mandelbrot <real axis minimum> <real axis maximum> <imaginary axis minimum> <imaginary axis maximum> <number of iterations per point>");
		System.out.println("\nTo plot the Julia set:");
		System.out.println("\t$java Fractal Julia <real part of C> <complex part of C> <number of iterations per point>");
		System.exit(0);
	}
	
	//main method/////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		if(args.length<1) { //Handles the error if the user does not specify which set to be printed
			printError();
		}
		
		else if("julia".contentEquals(args[0].toLowerCase())) { //Handles possibilities when the user chooses to print the Julia set
			if(args.length==1) { //to print the Julia set for default values
				SetGUI.makeFrame("Julia"); 
			}
			else if(args.length==4) { //to print the Julia set when the user gives the value for c and the number of iterations
				try {
					SetGUI.makeFrame("Julia", Double.parseDouble(args[1]), Double.parseDouble(args[2]), Integer.parseInt(args[3]));
				}catch(NumberFormatException e) {
					System.out.println(e);
					printError();
				}
			}
			else { //to handle the error when the user chooses to print the Julia set but gives and invalid number of arguments
				printError();
			}
		}
		else if("mandelbrot".contentEquals(args[0].toLowerCase())) { //Handles possibilities when the user chooses to print the Mandelbrot set
			if(args.length==1) { //to print the Mandelbrot set for default values
				SetGUI.makeFrame("Mandelbrot");
			}
			else if(args.length==5) { //to print the Julia set when the user gives the region of interest
				try {
					SetGUI.makeFrame("Mandelbrot", Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]));
				}catch(NumberFormatException e) {
					System.out.println(e);
					printError();
				}
			}
			else if(args.length==6) { //to print the Julia set when the user gives the region of interest and the number of iterations
				try {
					SetGUI.makeFrame("Mandelbrot", Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Integer.parseInt(args[5]));
				}catch(NumberFormatException e) {
					System.out.println(e);
					printError();
				}
			}
			else { //to handle the error when the user chooses to print the Mandelbrot set but gives and invalid number of arguments
				printError();
			}
		}
		else { //to handle the error when the user does not choose the the set to be printed correctly
			printError();
		}
	}
}
