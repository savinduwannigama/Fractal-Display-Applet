import javax.swing.JFrame;

public class SetGUI { //this class contains the static methods which makes a JFrame and creates the Fractal object calling the relevant constructor
	//method which makes the JFrame
	private static void newFrame(String setName, Fractal frac) {
		JFrame frame = new JFrame(setName+" set by E/17/369");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(frac);
		frame.setVisible(true);
		frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
	}
	
	//overloaded method to create the Fractal object calling the relevant constructor
	public static void makeFrame(String setName) {
		Fractal frac = new Fractal(setName);
		newFrame(setName, frac);
	}
	
	//overloaded method to create the Fractal object calling the relevant constructor
	public static void makeFrame(String setName, double re, double im, int iter) {
		Fractal frac = new Fractal(setName, re, im, iter);
		newFrame(setName, frac);
	}
	
	//overloaded method to create the Fractal object calling the relevant constructor
	public static void makeFrame(String setName, double r_l, double r_u, double i_l, double i_u) {
		Fractal frac = new Fractal(setName, r_l, r_u, i_l, i_u);
		newFrame(setName, frac);
	}
	
	//overloaded method to create the Fractal object calling the relevant constructor
	public static void makeFrame(String setName, double r_l, double r_u, double i_l, double i_u, int iter) {
		Fractal frac = new Fractal(setName, r_l, r_u, i_l, i_u, iter);
		newFrame(setName, frac);
	}
}
