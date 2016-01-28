import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {
	
 /* ************* variables ************* */
 
	protected int X;
	protected int Y;
	protected int i_width;
	protected int i_height;
	protected int ScreenHeight;
	protected int ScreenWidth;
	protected Image image;

 /* ************* constructor ************* */
 
	public Sprite() {
		ScreenHeight = Constants.HEIGHT - 22;
		ScreenWidth = Constants.WIDTH;
	}
	
 /* ************* methods ************* */
 
 	//getters and setters
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public int getWidth() {
		return i_width;
	}
	
	public int getHeight() {
		return i_height;
	}
	
	Image getImage() {
		return image;
	}
	
	public int getScreenHeight() {
		return ScreenHeight;
	}
	
	public int setScreenWidth() {
		return ScreenWidth;
	}	
	
	public void setX(int X) {
		this.X = X;
	}
	
	public void setY(int Y) {
		this.Y = Y;
	}
	
	public void setScreenHeight(int height) {
		ScreenHeight = height;
	}
	
	public void setScreenWidth(int width) {
		ScreenWidth = width;
	}
	
	Rectangle getRect() {
		return new Rectangle(X, Y, image.getWidth(null), image.getHeight(null));
	}
	
}