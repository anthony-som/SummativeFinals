import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;

/*
 * Brick Breaker by Anthony Som
 * ICS4U1 Rozenberg
 * June 15, 2021
 * Version 1
 * Brick Breaker Summative game
 * This class extends the JPanel of the main method. 
 * Uses methods, timer, action and  key listener, arrays, rectangles
 * Using Graphics, I set the background, borders, text, and rectangles wit hthe usage of filling and coordinates.
 * As well,  I created rectangles to indicate intersections between each object to allow for interactions.
 * The usage of the KeyListener was so that the player can control the board using the arrows keys. As well, use a key to restart
 * the gameplay.
 * 
 */

public class Game extends JPanel implements KeyListener, ActionListener {

	private boolean play = false; // When the code is run, makes it so it doesn't play immediately
	private int score = 0; // sets score

	private int totalBricks = 21; // sets # of bricks

	private Timer timer; // Sets speed of the ball
	private int delay = 1;

	private int playerX = 310; // starting position of the board

	// Sets the (x,y) of the ball
	private int ballx = 350; //x - starting position
	private int bally = 350;  //y - starting position
	private int ballxDirection = -1; 
	private int ballyDirection = -2;
	int startx = 90; //starting x  position for the starting screen
	int starty = 300; //starting  y position for the starting screen

	private Bricks brick;

	// constructor
	public Game() {
		brick = new Bricks(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this); // initialize object for timer
		timer.start();

	}

	public void paint(Graphics g) { // draws shape for ball, bricks, background
		// sets background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);

		// Drawing map
		brick.draw((Graphics2D) g);

		// sets borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(682, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);

		// board
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		// score board
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		//Starting screen
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 25));
		g.drawString("Welcome to Brick Breaker by Anthony Som", startx, starty);
		g.drawString("Click the Left or Right arrow to start the game", startx-10, starty+40);
		
		//set winning screen
		if(totalBricks <=0) {
			play = false;
			ballxDirection = 0;
			ballyDirection = 0;
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("You won!", 264, 300);
			
			g.setFont(new Font("arial", Font.BOLD, 20));
			g.drawString("Press Spacebar to play again", 205, 370);
		}
		
		
		
		// ball
		g.setColor(Color.yellow);
		g.fillOval(ballx, bally, 20, 20);
		
		//set game over screen
		if(bally > 570) { //if the ball is below the board
			play = false;
			ballxDirection = 0;
			ballyDirection = 0;
			g.setColor(Color.white); //set font colour
			g.setFont(new Font("arial", Font.BOLD, 30)); //set font
			g.drawString("Game Over", 264, 300); //print Game Over at (264,300)
			
			g.setFont(new Font("arial", Font.BOLD, 20));
			g.drawString("Press Spacebar to play again", 205, 370);
		}

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (play) {
			if (new Rectangle(ballx, bally, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballyDirection = -ballyDirection;
			}

			
		A:	for (int i = 0; i < brick.brick.length; i++) {
				for (int j = 0; j < brick.brick[0].length; j++) {
					if (brick.brick[i][j] > 0) { 
						int brickX = j * brick.brickWidth + 80;
						int brickY = i * brick.brickHeight + 50;
						int brickWidth = brick.brickWidth;
						int brickHeight = brick.brickHeight;
				
// Initializing rectangles for the bricks and ball
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballx, bally, 20, 20);
						Rectangle brickRect = rect;
// If the bricks and the ball intersect, minus the total number of bricks by 1 and add 5 to the score.
						if (ballRect.intersects(brickRect)) {
							brick.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;
// If brick and ball intersects, change direction of the ball
							if (ballx + 19 <= brickRect.x || ballx + 1 >= brickRect.x + brickRect.width) {
								ballxDirection = -ballxDirection;

							} else {
								ballyDirection = -ballyDirection;
							}
							
							break A; // break loop
						}

					}
				}
			}

			ballx += ballxDirection;
			bally += ballyDirection;

			if (ballx < 0) {
				ballxDirection = -ballxDirection;

			}
			if (bally < 0) {
				ballyDirection = -ballyDirection;

			}
			if (ballx > 670) {
				ballxDirection = -ballxDirection;
			}
		}

		repaint(); // calls the paint method to update paddle
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		startx = 10000;
		starty = 10000;

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 600) { // checks if paddle is in range
				playerX = 600;
			} else {
				moveRight(); // if it's out of range, call moveRight method
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX < 10) { // checks if paddle is in range
				playerX = 10;
			} else {
				moveLeft(); // if it's out of range, call moveLeft method
			}

		}
		
		//if spacebar is clicked, restart game
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(!play) { //if the game is finished
				play = true;
				
				//reset default
				ballx = 350; 
				bally = 350;
				ballxDirection = -1;
				ballyDirection = -2;
				playerX= 310;
				score = 0;
				totalBricks = 21;
				
				//generate bricks
				brick = new Bricks(3,7);
				
				repaint();
				
				
			}
			
		}

	}

	//If the game is running and the method is called, add 20 to the position of the board
	public void moveRight() {

		play = true;
		playerX += 20;
	}

	//If the game is running and the method is called, minus 20 to the position of the board
	public void moveLeft() {
		play = true;
		playerX -= 20;
	}

	@Override
	public void keyReleased(KeyEvent e) {

		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
