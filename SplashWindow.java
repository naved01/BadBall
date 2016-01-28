
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

public class SplashWindow extends JWindow {
	
 /* ************* constructors ************* */
 
    /*
    * input:
	* 	filename: name of the image for the splash screen
	*	f: frame onto which to place the splash screen
	*	waitTime: the duration of the splash screen  	
    */ 
    public SplashWindow(String filename, Frame f, int waitTime) {
        super(f);
        JLabel l = new JLabel(new ImageIcon(filename));
        getContentPane().add(l, BorderLayout.CENTER);
        pack();
        Dimension screenSize =
          Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = l.getPreferredSize();
		//place the splash screen on the window
        setLocation(screenSize.width/2 - (labelSize.width/2),
                    screenSize.height/2 - (labelSize.height/2) + 10);
		// allow the user to close the splash screen by clicking on it
        addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				setVisible(false);
				dispose();
			}
		});
        final int pause = waitTime;
        final Runnable closerRunner = new Runnable() {
			public void run() {
				setVisible(false);
				dispose();
			}
		};
		
		//display the window for a certain period of time and then close
        Runnable waitRunner = new Runnable() {
			public void run() {
				try {
					Thread.sleep(pause);
					SwingUtilities.invokeAndWait(closerRunner);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
        setVisible(true);
        Thread splashThread = new Thread(waitRunner, "SplashThread");
        splashThread.start();
    }
}