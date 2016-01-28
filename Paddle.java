import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Paddle extends Sprite {
	
 /* ************* variables ************* */
 
	private int dx;
	private int dy;
	private int ds;
	private	ImageIcon ii;
	
 /* ************* constructor ************* */
 
	public Paddle(int ds) {
		super();
		
		dx = 0;
		dy = 0;
		this.ds = ds;					
	    ii = new ImageIcon("paddle.png");
		image = ii.getImage();	
		i_width = image.getWidth(null);
		i_height = image.getHeight(null);
		
		resetState();	
	}
	
 /* ************* methods ************* */

    /*
    * move(): moves the paddle by changing the X and Y position.
    */
	public void move() {
		X+= dx;
		Y+= dy;
		//check if the paddle hits the left wall
		if (X <= 0) {
			X = 0;
		}
		
		//check if the paddle hits the middle of the window
		//(we do not allow the paddle to go above the middle of 
		// the height of the screen)
		if (Y <= ScreenHeight/2) {
			Y = ScreenHeight/2;
		}
		
		// check if the paddle hits the floor
		if (Y >= ScreenHeight - 20) {
			Y = ScreenHeight - 20;
		}			
		
		//check if the paddle hits the right wall	
		if (X >= ScreenWidth - i_width) {
			X = ScreenWidth - i_width;
		}
	}
	
    /*
    * keyPressed(KeyEvent e): set the speed and the direction
							  of the paddle according to the 
							  keys pressed by the user
    */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT) {
			dx = -1 * ds;
		}
		if (key == KeyEvent.VK_RIGHT) {
			dx = ds;
		}
		if (key == KeyEvent.VK_UP) {
			dy = -1 * ds;
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = ds;
		}
	}

    /*
    * keyReleased(): reset the speed of the paddle to 0
    */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}	
	
    /*
    * resetState(): place the ball back on the initial position
    */
	public void resetState() {
		X = ScreenWidth - 100;
		Y = ScreenHeight - 20;
	}	
}