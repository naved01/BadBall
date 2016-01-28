
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.Timer;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;


public class Board extends JPanel {
	
 /* ************* variables ************* */
 
	private Ball ball;	
	private Random rnd;
	private Timer timer;
	private Paddle paddle;
	private JButton newGameButton;
	private Brick bricks[];
	private int ds;
	private int FPS;
	private int life;
	private int score;
	private int level;
	private int BottomEdge;
	private int ScreenWidth;
	private int ScreenHeight;
	private boolean ingame;
	private Image panda;
	private ImageIcon i_panda;
	private String message = "Can you handle the bad ball?";

	private final int N_OF_BRICKS = 60;
	private final int buttonWidth = 80;
	private final int buttonHeight = 20;

 /* ************* constructor ************* */

    /*
    * input:
	* 	FPS: the rate of refreshing the screen in frames per second.
	*	speed: the speed of the ball (ds)	
    */ 
	public Board(int FPS, int speed) {
			
		//initialize the variables
		life = 3;
		score = 0;
		level = 0;
		ds = speed;	
		this.FPS = FPS;	
		ingame = false;			
		ScreenHeight = Constants.HEIGHT;
		ScreenWidth = Constants.WIDTH;
		BottomEdge = ScreenHeight - 10;		
		rnd = new Random();
		ball = new Ball(ds);
		paddle = new Paddle(ds);
		i_panda = new ImageIcon("panda.png");
		bricks = new Brick[N_OF_BRICKS];
		panda = i_panda.getImage();
		
		//handle the key input from the user
		addKeyListener(new 	KeyAdapter() {	
			public void keyReleased(KeyEvent e) {
				paddle.keyReleased(e);
			}
				
			public void keyPressed(KeyEvent e) {
				paddle.keyPressed(e);
			}
		});
		
		setLayout(null);
		setOpaque(true);				
		setFocusable(true);		
		setDoubleBuffered(true);
    	this.setBackground(new Color(244, 235, 247));
		//define the button that starts the game
		newGameButton = new JButton("GO!");
		newGameButton.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				gameInit();
		    }
		});
		//set the location for the button
		newGameButton.setBounds(ScreenWidth/2 - buttonWidth/2,ScreenHeight/2 - buttonHeight/2 + 80 ,buttonWidth, buttonHeight );
		//add the button to the panel
		add(newGameButton);
		//display the button
		newGameButton.setVisible(true);
		//set the timer
		timer  = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				resetVariables();
				if (ingame) {
					ball.move();
					paddle.move();
					checkCollision();
				}
				repaint();
			}
		}, 1000, 1000 / FPS);	
	}
	
 /* ************* methods ************* */

    /*
    * gameInit(): initializes the game
    */
	private void gameInit() {		
		//hide the button
		newGameButton.setVisible(false);	
		//reset the objects
		ball.resetState();
		paddle.resetState();
		int k = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 6; j++) {
				bricks[k] = new Brick(j*43 + 20, i*10 + 50, rnd.nextInt(3) + 1);
			    k++;
			}
		}
		ingame = true;		
	}

    /*
	* @Override
    * paintComponent(): main paint method
    */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    Font font = new Font("Verdana", Font.BOLD, 9);
		FontMetrics metr = this.getFontMetrics(font);
		Graphics2D g2d = (Graphics2D) g;		
		g2d.setColor(Color.BLACK);
		g2d.setFont(font);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		//if the game is on, redraw the game, 
		// otherwise, draw the main menu
		if (ingame) {
			drawObjects(g2d);
		}
		else {
			drawMainMenu(g2d);
		}
		//improves the smoothness of the graphics
		Toolkit.getDefaultToolkit().sync();
	}
	
    /*
    * drawObjects(): draws the main components of the game
    */
	private void drawObjects(Graphics2D g2d) {
		//display the ball
		g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight(), this);
		//display the paddle
		g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight(), this);	
		//display the bricks that are not destroyed yet.
		for (int i = 0; i < N_OF_BRICKS; i++) {
			if (!bricks[i].isDestroyed()) {
				g2d.drawImage(bricks[i].getImage(), bricks[i].getX(), bricks[i].getY(), bricks[i].getWidth(), bricks[i].getHeight(), this);
			}
		}
		String scorestr = "Score: " + score;
		String FPSstr = "FPS: " + FPS;
		String levelstr = "Lvl: " + level;
		String lifestr = "HP: " + life;
		//display the score
		g2d.drawString(scorestr, 10, 20); 
		//display the FPS
		g2d.drawString(FPSstr, 10, 30);
		//display the level
		g2d.drawString(levelstr, 80, 20);
		//display the life
		g2d.drawString(lifestr, 80, 30);
	}

    /*
    * drawMainMenu(): draws the initial menu where the user can choose to start a new game
    */
	private void drawMainMenu(Graphics2D g2d) {
		Font font = new Font("Verdana", Font.BOLD, 15);
		FontMetrics metr = this.getFontMetrics(font);
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(font);
		//display the message
		g2d.drawString(message, (ScreenWidth - metr.stringWidth(message)) / 2, 100);
		g2d.drawImage(panda, ScreenWidth/2 - 50, ScreenHeight/2 - 40, 100, 95, this);
	}

    /*
    * resetVariables(): checks if the width or height of the screen
						has been changed. If so, inform the objects of the change.
    */
	private void resetVariables() {
		
		//check if the width has been changed
		if (ScreenWidth != this.getWidth()) {
			int dx = (this.getWidth() - ScreenWidth)/2;
			ScreenWidth = this.getWidth();
			if (ingame) {
			    ball.setScreenWidth(ScreenWidth);
				paddle.setScreenWidth(ScreenWidth);
				int k = 0;
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 6; j++) {
						bricks[k].setX(bricks[k].getX() + dx);
						k++;
					}
				}
			}
			else {
				newGameButton.setBounds(ScreenWidth/2 - buttonWidth/2,ScreenHeight/2 - buttonHeight/2 + 80,buttonWidth, buttonHeight );
			}
		}
		//check if the height has been changed
		if (ScreenHeight != this.getHeight()) {
			ScreenHeight = this.getHeight();
			if (ingame) {
		    	ball.setScreenHeight(ScreenHeight);
				paddle.setScreenHeight(ScreenHeight);
				BottomEdge = ScreenHeight - 10;
			}
			else {
				newGameButton.setBounds(ScreenWidth/2 - buttonWidth/2,ScreenHeight/2 - buttonHeight/2 + 80,buttonWidth, buttonHeight );
			}
		}		
	}
 
     /*
    * stopGame(): stops the game by changing the ingame variable to "false"
    */
	private void stopGame() {
		ingame = false;
		if (life > 0) {
			newGameButton.setVisible(true);
		}
	}
	
     /*
    * checkCollision(): checks if the ball hit anything (brick or paddle).
						
    */
	private void checkCollision() {
		
		//if the ball is bellow the bottom line, the user looses.
		if (ball.getRect().getMaxY() > BottomEdge) {
			life--;
			if (life > 0) {
				message = "You Lost. Try Again.";
			}
			else {
				life = 0;
				message = "Your score: " + score + ". Goodbye.";
			}
			score = 0;
			stopGame();
		}
		//count all the destroyed bricks. If all bricks are destroyed, the user wins the game.
		for (int i = 0, j = 0; i < N_OF_BRICKS; i++) {
			if (bricks[i].isDestroyed()) {
				j++;
			}

			if (j == N_OF_BRICKS) {
				message = "You won. To the next Level!";
				level++;
				stopGame();
			}
		}
		//handle the collision between the paddle and the ball
		if ((ball.getRect()).intersects(paddle.getRect())) {
			int paddleLPos = (int) paddle.getRect().getMinX();
			int ballLPos = (int) ball.getRect().getMinX();
			
			int first = paddleLPos + 8;
			int second = paddleLPos + 16;
			int third = paddleLPos + 24;
			int fourth = paddleLPos + 32;
			
			//change the movement of the ball, based on where it hit the paddle
			if (ballLPos < first) {
				ball.setXDir(-1 * ds);
				ball.setYDir(-1 * ds);
			}
			
			if (ballLPos >= first && ballLPos < second) {
				ball.setXDir(-1 * ds);
				ball.setYDir(-1 * ball.getYDir());
			}
            
			if (ballLPos >= second && ballLPos < third) {
                ball.setXDir(0);
                ball.setYDir(-1 * ds);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                ball.setXDir(ds);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {
                ball.setXDir(ds);
                ball.setYDir(-1 * ds);
            }
		}
		//handle the collision between the ball and the bricks
		for (int i = 0; i < N_OF_BRICKS; i++) {
			if ((ball.getRect()).intersects(bricks[i].getRect())) {
				int ballLeft = (int) ball.getRect().getMinX();
				int ballHeight = (int) ball.getRect().getHeight();
				int ballWidth = (int) ball.getRect().getHeight();
				int ballTop = (int) ball.getRect().getMinY();
				
				Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
				Point pointLeft = new Point(ballLeft - 1, ballTop);
				Point pointTop = new Point(ballLeft, ballTop - 1);
				Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
				
				//change the direction of the ball based on where it hit the brick
				if (!bricks[i].isDestroyed()) {
					if (bricks[i].getRect().contains(pointRight)) {
						ball.setXDir(-1 * ds);
					}
					else if (bricks[i].getRect().contains(pointLeft))  {
						ball.setXDir(ds);
					}
					
					if (bricks[i].getRect().contains(pointTop)) {
						ball.setYDir(ds);
					}
					else if (bricks[i].getRect().contains(pointBottom)) {
						ball.setYDir(-1 * ds);
					}
					//if the brick is hit, increment the score
					score++;
					//hide the brick.
					bricks[i].removeHP();
					if(bricks[i].getHP() == 0) {
						bricks[i].setDestroyed(true);
					}	
				}
			}
		}
	}
}