package UnrestrictedGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DataStructure.BinaryTree;
import DataStructure.BinaryTreeNode;
import DataStructure.DefaultBinaryTreeNode;
import GameIO.GuessingGameFileReader;

/**
* GuessingGameView has logic and GUI of the game
**/
public class UnrestrictedGuessingGameView extends JComponent{

	// instance properties
	private JLabel instructions;
	private GuessingGameFileReader reader = new GuessingGameFileReader();
	
	private BinaryTree<String> tree = reader.readCommutativeExpr( "guessingGame_question2.xml");
	private BinaryTreeNode<String> node =  tree.getRoot();
	private BinaryTreeNode<String> previousNode = null;
	private JLabel questionLabel = new JLabel();
	private JPanel northP;
	private JPanel centerP;
	private JPanel southP;
	
	private JTextField field1;
	private JTextField field2;
	private JTextField field3;
	
	private JButton submitB;
	
	//constructor
	public UnrestrictedGuessingGameView() {
		initGUI();
	}

	/**
	 * private method to create the GUI components
	 * returns the created main panel
	 */	
	private void initGUI() {
		/** use a BorderLayout **/
		setLayout(new BorderLayout());
		add(createNorthP(), BorderLayout.NORTH);
		add(createCenterP(), BorderLayout.CENTER);		
		add(createSouthP(), BorderLayout.SOUTH);		
		addInstruction();
		
	}

	/**
	* create a JPanel at the North of the frame
	* first, let it contain a background image
	**/
	private JPanel createNorthP() {
		
		northP = new JPanel();
		
		northP.setPreferredSize(new Dimension(710, 400));
		JLabel contentPane = new JLabel();
		ImageIcon backgroundImage = new ImageIcon("map.jpg");
		contentPane.setIcon( backgroundImage );
		contentPane.setLayout( new BorderLayout() );
		northP.add( contentPane, BorderLayout.NORTH );
		
		
		return northP;
	}
	/**
	 * create a JPanel at the Center of the frame
	 */
	private JPanel createCenterP() {
		
		centerP = new JPanel();
		centerP.setPreferredSize(new Dimension(710, 300));
		return centerP;
	}
	/**
	 * create a JPanel at the South of the frame
	 * add the Start button so that user can start playing
	 */	
	private JPanel createSouthP(){
		southP = new JPanel();
		southP.add(startButton());
		return southP;
	}

	/**
	 * a method to add instruction and topic panel to the Center Panel
	 */
	private void addInstruction(){
		centerP.setLayout(new GridLayout(3,1));
		
		/** create instructions and put at the top **/
		instructions = new JLabel("<html>Finding a country to visit on the next vacation? <br> Just with Yes and No answers, we can suggest a place for you! " + "</html>" );

		centerP.add(instructions);
	
		/**add topic panel**/
		centerP.add(createCountryPanel());
		
	}
	/**
	 * a method to add question panel to the Center Panel
	 * after removing everything in Center Panel
	 */
	private void addQuestions() {

		centerP.removeAll();
		centerP.validate();
		centerP.add(createQuestionPanel());
	}

	/**
	 * a method to add answer panel to the South Panel
	 * after removing everything in South Panel
	 */	
	private void addAnswers(){
		
		southP.removeAll();
		southP.validate();
		southP.add(createAnswerPanel());
	}

	/**
	 * a method to add panel for unrestricted part to the Center Panel
	 * after removing everything in Center Panel
	 */
	private void addUnrestricted(){
		centerP.removeAll();
		centerP.validate();
		centerP.add(createUnrestrictedP());
	}

	/**
	 * a method to change background photo in North Panel
	 * after removing previous photo
	 */	
	private void changeBackground(){
		northP.removeAll();
		northP.setPreferredSize(new Dimension(710, 330));
		JLabel contentPane = new JLabel();
		ImageIcon backgroundImage = new ImageIcon("fly2.gif");
		contentPane.setIcon( backgroundImage );
		contentPane.setLayout( new BorderLayout() );
		northP.add( contentPane, BorderLayout.NORTH );
	}
	
	/**
	 * a JPanel that contains 17 country names
	 */
	private JPanel createCountryPanel() {
		JPanel countryPanel = new JPanel (new GridLayout(3,6));
		
		countryPanel.add(new JLabel ("Thailand"));
		countryPanel.add(new JLabel ("Vietnam"));
		countryPanel.add(new JLabel ("Madagasca"));
		countryPanel.add(new JLabel("Australia"));
		countryPanel.add(new JLabel ("Switzerland"));
		countryPanel.add(new JLabel("NewZealand"));
		countryPanel.add(new JLabel ("Brazil"));
		countryPanel.add(new JLabel ("Chile"));
		countryPanel.add(new JLabel("Italy"));
		countryPanel.add(new JLabel ("India"));
		countryPanel.add(new JLabel ("France"));
		countryPanel.add(new JLabel ("Denmark"));
		countryPanel.add(new JLabel ("Singapore"));
		countryPanel.add(new JLabel("Finland"));
		countryPanel.add(new JLabel ("Canada"));
		countryPanel.add(new JLabel ("Korea"));
		countryPanel.add(new JLabel ("Mexico"));
		countryPanel.add(new JLabel ("EXCITED???"));
		
		return countryPanel;
	}
	
	/**
	* a start button for user to start the game
	* when button clicked, question and answer panels appear
	**/
	private JButton startButton(){
		// create a button
		JButton start = new JButton( "Let's start!" );	
		
		start.addActionListener (
				new ActionListener() {
					public void actionPerformed (ActionEvent e) {
						
						addQuestions();
						addAnswers();
						
						validate();
						repaint();
					}
					});
		return start;
	}
	
	/**
	* a panel that contains questions taken from the XML file
	**/
	private JPanel createQuestionPanel(){
		JPanel questionPanel = new JPanel();
		questionLabel.setFont(new Font("Nanum Pen Script", Font.BOLD, 30));
		questionLabel.setText(node.getData().toString());
		questionPanel.add(questionLabel);
		return questionPanel;
	}

	/**
	* a panel that contains answers taken from the XML file
	**/	
	private JPanel createAnswerPanel(){
		JPanel answerPanel = new JPanel(new GridLayout(1,2));
		answerPanel.add(yesButton());
		answerPanel.add(noButton());
		answerPanel.add(createSubmitPanel());
		submitB.setVisible(false);
		
		return answerPanel;
	}

	/**
	* a button associated with "yes" answer from users
	* when button clicked, if there is left child of the node is available, display text of that left child
	* otherwise, change background and display "Enjoy your trip"
	**/	
	private JButton yesButton(){
		// create a button
		JButton yesB = new JButton( "Yes" );	
		
		yesB.addActionListener (
				new ActionListener() {
					public void actionPerformed (ActionEvent e) {
						
						if (node.getLeftChild() != null){
							questionLabel.setText(node.getLeftChild().getData().toString());
							previousNode = node;
							node = node.getLeftChild();
						}
						else {
							changeBackground();
							submitB.setVisible(true);
							questionLabel.setText("Enjoy your trip!");
							questionLabel.setFont(new Font("Nanum Pen Script", Font.BOLD, 70));
						}
					}
					});
		return yesB;
	}

	/**
	* a button associated with "no" answer from users
	* when button clicked, if there is left child of the node is available, display text of that left child
	* otherwise, it means users want to add their own countries
	* add panel for unrestricted part
	**/
	private JButton noButton(){
		// create a button
		JButton noB = new JButton( "No" );	
		
		noB.addActionListener (
				new ActionListener() {
					public void actionPerformed (ActionEvent e) {
						
						if (node.getRightChild() != null){
							questionLabel.setText(node.getRightChild().getData().toString());
							previousNode = node;
							node = node.getRightChild();
						}
						else {
						
							addUnrestricted();
							changeBackground();
							validate();
							repaint();
						}				
					}
					});
		return noB;
	}
	
	/**a JPanel that contains questions and text field for unrestricted part**/
	private JPanel createUnrestrictedP() {
	
		JPanel unrestrictedP = new JPanel();
		unrestrictedP.setLayout(new GridLayout(3,2));
		JTextArea question1 = new JTextArea ("Enter the country");
		question1.setLineWrap(true);  
		question1.setWrapStyleWord(true);
		JTextArea question2 = new JTextArea ("Enter the question that distinguish your dream country with the suggested one");
		question2.setLineWrap(true); 
		question2.setWrapStyleWord(true);
		JTextArea question3 = new JTextArea ("Is your answer Yes or No for that question?");
		question3.setLineWrap(true); 
		question3.setWrapStyleWord(true);
		field1 = new JTextField();
		field2 = new JTextField();
		field3 = new JTextField();
		
		unrestrictedP.add(question1);
		unrestrictedP.add(field1);
		unrestrictedP.add(question2);
		unrestrictedP.add(field2);
		unrestrictedP.add(question3);
		unrestrictedP.add(field3);
		
		submitB.setVisible(true);
		//.add(createSubmitPanel(),  BorderLayout.SOUTH);
		
		return unrestrictedP;
	}
	
	/**
	*create submit button
	*when button clicked, add input from users to the binary tree
	*and restart the game
	**/
	private JButton createSubmitPanel() {
		submitB = new JButton("Submit");
		
		submitB.addActionListener (
				new ActionListener() {
					public void actionPerformed (ActionEvent e) {
						BinaryTreeNode<String> newNode = new DefaultBinaryTreeNode<String>(field2.getText());
						if (field3.getText().toLowerCase().equals("yes")) {
							newNode.setRightChild(node);
							newNode.setLeftChild(new DefaultBinaryTreeNode<String> (field1.getText()));
							
							if (node == previousNode.getLeftChild()) {
								previousNode.setLeftChild(newNode);
								
							}
							else {
								previousNode.setRightChild(newNode);
							}
							restartGame();
						}
						else {
							newNode.setLeftChild(node);
							newNode.setRightChild(new DefaultBinaryTreeNode<String> (field1.getText()));
							
							if (node == previousNode.getLeftChild()) {
								previousNode.setLeftChild(newNode);					
							}
							else {
								previousNode.setRightChild(newNode);
								restartGame();
							}
							
						}
					}
					});
		
		return submitB;
	}
	
	/**restart button**/
	private void restartGame() {
		removeAll();
		initGUI();
		node = tree.getRoot();
		previousNode = null;
		validate();
		repaint();
		
	}

}

