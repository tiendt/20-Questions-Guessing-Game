package UnrestrictedGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class UnrestrictedApplication {
	/**
	 * Create a JFrame that holds the GuessingGame.
	 * 
	 **/
	public static void main( String[] args )
	{
		JFrame guiFrame;
			
		// create a new JFrame to hold the unrestricted guessing game
		guiFrame = new JFrame( "20 Questions Travel Quiz");
		
		// set size
		guiFrame.setSize( 700, 700 );

		guiFrame.add(new UnrestrictedGuessingGameView(), BorderLayout.SOUTH);

		// exit normally on closing the window
		guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
		guiFrame.setVisible( true );
	}
}
