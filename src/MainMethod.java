import javax.swing.JFrame;

/*
 * Brick Breaker by Anthony Som
 * ICS4U1 Rozenberg
 * June 15, 2021
 * Version 1
 * Brick Breaker Summative game
 * This class is the main method of the game.
 * Initializes the JFrame of the game and adds the object from class Game onto the JFrame
 * 
 */

public class MainMethod {

	public static void main(String[] args) {
		
		//Initializing and creating the GUI for the Game
		JFrame obj = new JFrame(); //creates a JFrame
		Game gamePlay = new Game();
		obj.setBounds(10,10,700,600); //Sets the size of the JFrame
		obj.setTitle("Brick Breaker by Anthony Som");
		obj.setResizable(false);  //Doesn't allow to resize window
		obj.setVisible(true); //Sets frame to visible
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
		


	}

}
