import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/*
 * Brick Breaker by Anthony Som
 * ICS4U1 Rozenberg
 * June 15, 2021
 * Version 1
 * Brick Breaker Summative game
 * This is the Bricks Class.
 * In here, I initialized  the brick array and used graphics to visually create the bricks.
 */

public class Bricks {

	//initializing brick array, height and width.
	public int brick[][];
	public int brickWidth;
	public int brickHeight;

	public Bricks(int row, int col) {
		brick = new int[row][col]; // Initialize bricks
		for (int i = 0; i < brick.length; i++) {
			for (int j = 0; j < brick[0].length; j++) {
				brick[i][j] = 1;

			}
		}

		brickWidth = 540 / col;
		brickHeight = 150 / row;

	}
	


	public void draw(Graphics2D g) {
		for (int i = 0; i < brick.length; i++) {
			for (int j = 0; j < brick[0].length; j++) {
				if (brick[i][j] > 0) {
					//fills the colour of the bricks
					g.setColor(Color.white);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					//separates the bricks
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					
					//graphically places the brick  on the JFRAME
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}

			}
		}
	}
	public void setBrickValue(int value, int row, int col) {
		brick[row][col] = value;
		
	}

}
