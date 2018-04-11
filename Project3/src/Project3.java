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
  private int playerScore = 0, dealerScore = 0;
  private static JFrame myFrame = null;
  CardList playerHand = new CardList(0), playerPairs = new CardList(0),
  		dealerHand = new CardList(0), dealerPairs = new CardList(0);
  private boolean isPlayerTurn = true;
  

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
        testButton = new JButton("testroonie");
        northPanel.add(testButton);
        testButton.addActionListener(this);
        
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
        
        
        
        
        

        setSize(1500,1500);
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
	        flipCard(current);
	        current = current.getNextCard();
      }
      
    //FLIPS THE DECK OVER FACE DOWN. CODE REUSE FOR SINGLE INSTANCE OF THE CHEAT BUTTON.
      current = theDeck.getFirstCard();
      while(current != null) {
    	  	flipCard(current);
	        current = current.getNextCard();
    }
      
      
      //CHECK FOR INITIAL PAIRS
//      checkForPairs();
  }
  
  
//  public void checkForPairs() {
//	  
//	  Card current = playerHand.getFirstCard();
//      while(current != null) {
//    	  Card current2 = current.getNextCard();
//    	  while(current2 != null) {
//    		  if(current.getRank() == current2.getRank() && current.getCardnum() != current2.getCardnum()) {
//    			  playerPairs.insertCard(playerHand.deleteCard(current.getCardnum()));
//    			  playerPairs.insertCard(playerHand.deleteCard(current2.getCardnum()));
//    			  
//    		  }
//    		  current2 = current2.getNextCard();
//    	  }
//    	  current = current.getNextCard();
//      }
//      
//      
//	  
//	  
//  }

  
  public void fish(){
		while(playerHand != null || dealerHand != null){
			playerFish();
			repaint();
			if(isPlayerTurn == false) {
				dealerFish();
				repaint();
			}
			if(playerHand == null)
				inputBox.setText("Player wins!");
			else if(dealerHand == null)
				inputBox.setText("Dealer wins!");
		} // end of while
		
	} // end of fish

  
  public void playerFish(){
	    int rankToLookFor = Integer.parseInt(inputBox.getText());
		boolean playerHasCard = false;
		boolean dealerHasCard = false;
		int numOfC1 = -1;
		int numOfC2 = -1;
		Card current = playerHand.getFirstCard();
		Card current2 = dealerHand.getFirstCard();
		
		//Check player's deck for the card
		int counter = 0;
		while(current != null){
			if(current.getRank() == rankToLookFor){
				numOfC1 = counter;
				playerHasCard = true;	
				break;
			}
			counter++;
		current = current.getNextCard();
		} // end of first while

		
		//Check dealer's deck for the card
		counter = 0;
		while(current2 != null){
			if(current2.getRank() == rankToLookFor){
				numOfC2 = counter;
				dealerHasCard = true;
				break;
			}
			counter++;
			current2 = current2.getNextCard();
		} // end of second while

		
		//Resolve who had the card
		if(playerHasCard) 
			if(dealerHasCard) {
				
				playerPairs.insertCard(playerHand.deleteCard(numOfC1));
				playerPairs.insertCard(dealerHand.deleteCard(numOfC2));	
				flipCardUp(playerPairs.getFirstCard());
				flipCardUp(playerPairs.getFirstCard().getNextCard());
				playerScore++;
				repaint();
			}else {
				playerHand.insertCard(theDeck.deleteCard(0));
				flipCardUp(playerHand.getFirstCard());
				inputBox.setText("Go Fish!");
				if(playerHand.getFirstCard().getRank() == current.getRank()) {
					playerPairs.insertCard(playerHand.deleteCard(numOfC1+1));
					playerPairs.insertCard(playerHand.deleteCard(0));
					flipCardUp(playerPairs.getFirstCard());
					flipCardUp(playerPairs.getFirstCard().getNextCard());
					playerScore++;
					inputBox.setText("Drew a pair!");
					repaint();
				}
					
			}
		 else
			inputBox.setText("You don't have that Card!");
		
			



		this.isPlayerTurn = false;
	} // end of player fish
  
  
  public void dealerFish(){			//DEALERFISH DOES NOT WORK THE SAME WAY AS PLAYERFISH. DEALER DOES NOT USE THE TEXT BOX AND
	  	int rankToLookFor = UserInput.randomWithRange(1, 13);
		boolean playerHasCard = false;
		boolean dealerHasCard = false;
		int numOfC1 = -1;
		int numOfC2 = -1;
		Card current = dealerHand.getFirstCard();
		Card current2 = playerHand.getFirstCard();
		
		//Check how many cards the dealer has
		int counter = 0;
		while(current != null){
			if(current.getRank() == rankToLookFor) {
				numOfC1 = counter;
				dealerHasCard = true;
			}
			counter++;
		current = current.getNextCard();
			if(current == null && dealerHasCard == false) {
				rankToLookFor = UserInput.randomWithRange(1, 13);
				current = dealerHand.getFirstCard();
			}
		} // end of first while

		
		//Check player deck for the card
		counter = 0;
		while(current2 != null){
			if(current2.getRank() == rankToLookFor){
				numOfC2 = counter;
				playerHasCard = true;
				break;
			}
			counter++;
			current2 = current2.getNextCard();
		} // end of second while

		
		//Resolve who had the card
		if(playerHasCard) {
				dealerPairs.insertCard(playerHand.deleteCard(numOfC2));
				dealerPairs.insertCard(dealerHand.deleteCard(numOfC1));	
				flipCardUp(dealerPairs.getFirstCard());
				flipCardUp(dealerPairs.getFirstCard().getNextCard());
				dealerScore++;
			}else {
				inputBox.setText("Dealer Fished.");
				
				if(dealerHand.getFirstCard().getIsFaceDown()) {
					dealerHand.insertCard(theDeck.deleteCard(0));
					flipCardDown(dealerHand.getFirstCard());
					inputBox.setText("lolnope");
				}else
					dealerHand.insertCard(theDeck.deleteCard(0));
					

				if(dealerHand.getFirstCard().getRank() == current.getRank()) {
					dealerPairs.insertCard(dealerHand.deleteCard(numOfC1+1));
					dealerPairs.insertCard(dealerHand.deleteCard(0));
					flipCardUp(dealerPairs.getFirstCard());
					flipCardUp(dealerPairs.getFirstCard().getNextCard());
					dealerScore++;
					inputBox.setText("Dealer drew a pair!");
					repaint();
					
				}
			}

		this.isPlayerTurn = true;


	} // end of dealer fish
  
  
  
  
  
  
  ////////////   BUTTON CLICKS ///////////////////////////
  public void actionPerformed(ActionEvent e) {

      if (e.getSource()== exitButton) {
        dispose(); System.exit(0);
      }
      
      
      if (e.getSource()== fishButton) {
        String inputmsg = inputBox.getText();
        
        fish();
//        playerFish();
        repaint();
      }
      
      //For testing
      if(e.getSource() == testButton) {
    	  String inputmsg = inputBox.getText();
    	  inputBox.setText("Add card");
    	  theDeck.getFirstCard().setCardImage(Project3.load_picture("images/gbCard" + theDeck.getFirstCard().getCardnum() + ".gif"));
    	  playerHand.insertCard(theDeck.deleteCard(0));
    	  
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
	        flipCard(current);
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

  
  public void flipCard(Card current) {
	  if(current.getIsFaceDown() == false) {
      	current.setCardImage(Project3.load_picture("images/gbCard52.gif"));
      	current.setIsFaceDown(true);
      }
      else {
      	current.setCardImage(Project3.load_picture("images/gbCard" + current.getCardnum() + ".gif"));
      	current.setIsFaceDown(false);
      }
	  
  }
  
  public void flipCardUp(Card current) {
	  current.setCardImage(Project3.load_picture("images/gbCard" + current.getCardnum() + ".gif"));
	  current.setIsFaceDown(false);
	  
  }
  public void flipCardDown(Card current) {
	  current.setCardImage(Project3.load_picture("images/gbCard52.gif"));
	  current.setIsFaceDown(true);
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
	 
	  
	//INITILAIZE POSITIONS AND DECK
    int xpos, ypos;
    Card current;
    g.drawString("This is ALL 52 cards in the Deck!", 25, 15);
    if (playerHand == null) 
    	return;
    
    

    //DRAW SCORES
    xpos = 10; ypos = 25;
    g.drawString("Dealer Score", xpos, ypos);
    g.drawString(""+dealerScore, xpos, ypos+10);
    
    xpos = 10; ypos = 760;
    g.drawString("Player Score", xpos, ypos);
    g.drawString(""+playerScore, xpos, ypos+10);
    
    
    
    //DISPLAY DECK
    xpos = 105; ypos = 340;
    current = theDeck.getFirstCard();
    while(current != null) {
    	Image tempimage = current.getCardImage();
        g.drawImage(tempimage, xpos, ypos, this);
        // note: tempimage member variable must be set BEFORE paint is called
        xpos += 30;
        if (xpos > 900) {
           xpos = 105; ypos += 105;
        }
        current = current.getNextCard();	
    }
    
    
    //DISPLAY DEALER'S HAND
    xpos = 105; ypos = 25;
    current = dealerHand.getFirstCard();
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
    
    //DISPLAY PLAYER'S HAND
    xpos = 105; ypos = 760;
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
    
    
    
    //DISPLAY PLAYER PAIRS
    xpos = 980; ypos = 760;
    current = playerPairs.getFirstCard();
    while(current != null) {
    	Image tempimage = current.getCardImage();
        g.drawImage(tempimage, xpos, ypos, this);
        // note: tempimage member variable must be set BEFORE paint is called
        xpos += 30;
        if (xpos > 1070) {
           xpos = 980; ypos += 105;
        }
        current = current.getNextCard();	
    }
    
    
    
    
    //DISPLAY DEALER PAIRS
    xpos = 980; ypos = 25;
    current = dealerPairs.getFirstCard();
    while (current!=null) {
       Image tempimage = current.getCardImage();
       g.drawImage(tempimage, xpos, ypos, this);
       // note: tempimage member variable must be set BEFORE paint is called
       xpos += 30;
       if (xpos > 1070) {
          xpos = 980; ypos += 105;
       }
       current = current.getNextCard();
    }
    
    
    
    
  }
}

}    // End Of class Project3



