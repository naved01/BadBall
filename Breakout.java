
import java.io.File;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JFrame;

public class Breakout extends JFrame {
	
 /* ************* constructor ************* */
 
	public Breakout(int FPS, int speed) {
                new SplashWindow("badball.png", this, 5000);
                //add the panel to the frame
                add(new Board(FPS, speed));
	           	setTitle("Bad Ball");
                //set the "close" button to close the frame
		        setDefaultCloseOperation(EXIT_ON_CLOSE);
                //set the minimum and maximum dimensions of the frame
		        setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		        setMaximumSize(new Dimension(450, 600));
	           	setLocationRelativeTo(null);
                //display the frame
	           	setVisible(true);
	}
	
 /* ************* methods ************* */
 
    /*
    * music(): takes a name of a file as an input,
               and plays the soundtrack in the background.
    */
	public static void music(String fileName) {
	  	try {
            //import the file
   	        Clip clip = AudioSystem.getClip();
			File file = new File(fileName);
        	AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
        	clip.open(inputStream);
            //play the file
        	clip.start(); 
        // print the error if something went wrong
        } catch (Exception e) {
        	System.err.println(e.getMessage());
      	}
	}
    
    /*
    * isInteger(): takes a string as an input,
               and checks if the string represents an integer
    */    
    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
	
	public static void main(String[] args) {
        
        // Uncomment to add the soundtrack
        //String fileName = "audio.mid";
		//music(fileName);	
        
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                
                //default values for FPS and speed
                int FPS = 25;
                int speed = 1;
                
                //check if the user provided FPS 
                if (args.length > 0) {
                    if (isInteger(args[0])) {
                        int temp = Integer.parseInt(args[0]);
                        if (temp >= 25 && temp <= 35) {
                            FPS = temp;
                        }
                    }
                }
                //check if the user provided speed
                if (args.length > 1) { 
                    switch (args[1]) {
                        case "slow":      speed = 2;
                                          break;
                        case "medium":    speed = 3;
                                          break;
                        case "fast":      speed = 4;
                                          break;
                        case "ultraFast": speed = 5;
                                          break;                
                    }
                }
                //create a new instance of the game
	  			Breakout game = new Breakout(FPS, speed);
			}
		});
	}
}