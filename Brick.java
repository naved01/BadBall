import javax.swing.ImageIcon;
import java.util.Random;

public class Brick extends Sprite {
	
 /* ************* variables ************* */

	private int HP; 	
	private boolean isExtraStrong;
	private boolean destroyed;
	private	ImageIcon ii;
	
 /* ************* constructor ************* */
 
	public Brick(int X, int Y, int HP) {
		
		this.X = X;
		this.Y = Y; 
		destroyed = false;
		this.isExtraStrong = isExtraStrong;

		this.HP = HP;
		String fileName = "brick" + HP + ".png"; 
		ii = new ImageIcon(fileName);

		image = ii.getImage();
		
		i_width = image.getWidth(null);
		i_height = image.getHeight(null);				
	}

 /* ************* methods ************* */
	
	public boolean isDestroyed() {
		return destroyed;
	}
	
	public void setDestroyed(boolean val) {
		destroyed = val;
	}
	
	public void removeHP() {
		this.HP -= 1;
	}
	
	public int getHP() {
		return this.HP;
	}
}