import javax.swing.ImageIcon;

public class Ball extends Sprite {
	
 /* ************* variables ************* */
 
	private int xdir;
	private int ydir;
	private int ds;
	private	ImageIcon ii;
	
 /* ************* constructor ************* */
 
	public Ball(int ds) {
		super();
		this.ds = ds;
		xdir = -1 * ds;
		ydir = -1 * ds;
		
		ii = new ImageIcon("ball.png");
		image = ii.getImage();
		
		i_width = image.getWidth(null);
		i_height = image.getHeight(null);
		
		resetState();
	}

 /* ************* methods ************* */
 
    /*
    * move(): moves the ball by changing the X and Y position.
    */
	public void move() {
		X += xdir;
		Y += ydir;
		
		//check if ball hits the left wall
		if (X <= 0) {
			setXDir(ds);
		}
		
		//check if the ball hits the right wall
		if (X >= ScreenWidth - i_width) {
			X = ScreenWidth - i_width;
			setXDir(-1 * ds);
		}
		
		//check if the ball hits the ceiling
		if (Y <= 0) {
			setYDir(ds);
		}
	}
	
	//getters and setters 
	
	public void setXDir(int x) {
		xdir = x;
	}
	
	public void setYDir(int y) {
		ydir = y;
	}
	
	public int getYDir() {
		return ydir;
	}
	
    /*
    * resetState(): place the ball back on the initial position
    */
	public void resetState() {
		X = ScreenWidth - 85;
		Y = ScreenHeight - 25;
	}
	
}