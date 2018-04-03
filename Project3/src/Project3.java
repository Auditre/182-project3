/***************************************************************
  Project Number 3 - Comp Sci 182 - Data Structures (w/ Swing)
  Start Code - Build your program starting with this code
               Card Game
  Copyright 2005-2016 Christopher C. Ferguson
  This code may only be used with the permission of Christopher C. Ferguson
***************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Project3 extends JFrame implements ActionListener {
	
	
	
	
  private static int winxpos=0,winypos=0;      // place window here


  private JButton fishButton, shuffleButton, exitButton, newButton, cheatButton, testButton;
  private JTextField inputBox, historyLog;
  private CardList theDeck = null;
  private JPanel northPanel;
  private MyPanel centerPanel;
  private static JFrame myFrame = null;
  CardList playerHand = new CardList(0), playerPairs = new CardList(0),
  		dealerHand = new CardList(0), dealerPairs = new CardList(0);
  

  ////////////              MAIN      ////////////////////////
  public static void main(String[] args) {
     Project3 tpo = new Project3();
  }

  ////////////            CONSTRUCTOR   /////////////////////
  public Project3 ()
  {
	  
        myFrame = this;                 // need a static variable reference to a JFrame object
        northPanel = new JPanel();
        
        
        
        northPanel.setBackground(Color.white);
        inputBox = new JTextField("Enter the rank of the card you're looking for into this box and hit \"Go Fish\"!",20);
        northPanel.add(inputBox);
        
        fishButton = new JButton("Go Fish");
        northPanel.add(fishButton);
        fishButton.addActionListener(this);
        
        //THIS BUTTON IS USELESS
//        testButton = new JButton("testroonie");
//        northPanel.add(testButton);
//        testButton.addActionListener(this);
        
        //THIS BUTTON IS ALSO USELESS
//        shuffleButton = new JButton("Shuffle");
//        northPanel.add(shuffleButton);
//        shuffleButton.addActionListener(this);
        
        cheatButton = new JButton("Cheat");
        northPanel.add(cheatButton);
        cheatButton.addActionListener(this);
        
        newButton = new JButton("New Deck");
        northPanel.add(newButton);
        newButton.addActionListener(this);
        
        exitButton = new JButton("Exit");
        northPanel.add(exitButton);
        exitButton.addActionListener(this);
        
        historyLog = new JTextField("The game has started.");
        northPanel.add(historyLog);
        
        
        getContentPane().add("North",northPanel);

        centerPanel = new MyPanel();
        getContentPane().add("Center",centerPanel);

        initilizeGame();
        
        
        
        
        

        setSize(1000,1200);
        setLocation(winxpos,winypos);

        setVisible(true);
   }
  
  public void initilizeGame() {
	  
	  //INITILIZES HANDS AND PAIRS
	  playerHand = new CardList(0);
	  playerPairs = new CardList(0);
	  dealerHand = new CardList(0);
	  dealerPairs = new CardList(0);

      //INITIALIZES THE DECK AND SHUFFLES IT
      theDeck = new CardList(52);
      theDeck.shuffle();
      
      //DEALS 5 CARDS TO PLAYER HAND
      for(int i = 0; i<=5; i++) 
      	playerHand.insertCard(theDeck.deleteCard(i));
      
      //DEALS 5 CARDS TO DEALER HAND
      for(int i = 0; i<=5; i++)
      	dealerHand.insertCard(theDeck.deleteCard(i));
      
      //FLIPS DEALER'S HAND OVER FACE DOWN. CODE REUSE FOR SINGLE INSTANCE OF THE CHEAT BUTTON.
      Card current = dealerHand.getFirstCard();
      
      while(current != null) {
	        if(current.getIsFaceDown() == false) {
	        	current.setCardImage(Project3.load_picture("images/gbCard52.gif"));
	        	current.setIsFaceDown(true);
	        }
	        else {
	        	current.setCardImage(Project3.load_picture("images/gbCard" + current.getCardnum() + ".gif"));
	        	current.setIsFaceDown(false);
	        }
	        current = current.getNextCard();
      }
  }

  ////////////   BUTTON CLICKS ///////////////////////////
  public void actionPerformed(ActionEvent e) {

      if (e.getSource()== exitButton) {
        dispose(); System.exit(0);
      }
      
      
      if (e.getSource()== fishButton) {
        String inputmsg = inputBox.getText();

        
        
        
        
        
        repaint();
      }
      
      //For testing
      if(e.getSource() == testButton) {
    	  String inputmsg = inputBox.getText();
    	  inputBox.setText("testaroonie");
    	  
    	  repaint();
      }
      
      //Shuffles the deck
       if (e.getSource()== shuffleButton) {
        String inputmsg = inputBox.getText();
        inputBox.setText("Shuffle");
//        playerHand.shuffle();
        repaint();
      }
       
       
       //Flips the first card. Needs to be changed to flip the CPU's hand only. Need to get the layout right first.
       if (e.getSource()== cheatButton) {
        inputBox.setText("Turn Cheat ON/OFF (show/hide)");
        
        Card current = dealerHand.getFirstCard();
        
        while(current != null) {
	        if(current.getIsFaceDown() == false) {
	        	current.setCardImage(Project3.load_picture("images/gbCard52.gif"));
	        	current.setIsFaceDown(true);
	        }
	        else {
	        	current.setCardImage(Project3.load_picture("images/gbCard" + current.getCardnum() + ".gif"));
	        	current.setIsFaceDown(false);
	        }
	        current = current.getNextCard();
        }
        
        //load the card with image 52. check which one it is to determine if you're just flipping it back or not
        
        repaint();
      }
       
       
       if (e.getSource()== newButton) {
        inputBox.setText("New Deck");
        initilizeGame();
        repaint();
      }
  }


// This routine will load an image into memory
//
public static Image load_picture(String fname)
{
        // Create a MediaTracker to inform us when the image has
        // been completely loaded.
        Image image;
        MediaTracker tracker = new MediaTracker(myFrame);


        // getImage() returns immediately.  The image is not
        // actually loaded until it is first used.  We use a
        // MediaTracker to make sure the image is loaded
        // before we try to display it.

        image = myFrame.getToolkit().getImage(fname);

        // Add the image to the MediaTracker so that we can wait
        // for it.
        tracker.addImage(image, 0);
        try { tracker.waitForID(0); }
        catch ( InterruptedException e) { System.err.println(e); }

        if (tracker.isErrorID(0)) { image=null;}
        return image;
}
// --------------   end of load_picture ---------------------------

class MyPanel extends JPanel {

 ////////////    PAINT   ////////////////////////////////
  public void paintComponent (Graphics g) {
    //
	 
    int xpos = 105, ypos = 25;
    g.drawString("This is ALL 52 cards in the Deck!", 25, 15);
    if (playerHand == null) 
    	return;
    Card current = dealerHand.getFirstCard();
    while (current!=null) {
       Image tempimage = current.getCardImage();
       g.drawImage(tempimage, xpos, ypos, this);
       // note: tempimage member variable must be set BEFORE paint is called
       xpos += 80;
       if (xpos > 900) {
          xpos = 105; ypos += 105;
       }
       current = current.getNextCard();
    }
    
    
    xpos = 105; ypos = 550;
    current = playerHand.getFirstCard();
    while(current != null) {
    	Image tempimage = current.getCardImage();
        g.drawImage(tempimage, xpos, ypos, this);
        // note: tempimage member variable must be set BEFORE paint is called
        xpos += 80;
        if (xpos > 900) {
           xpos = 105; ypos += 105;
        }
        current = current.getNextCard();
    	
    	
    	
    	
    }
  }
}

}    // End Of class Project3



