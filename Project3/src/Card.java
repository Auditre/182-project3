import java.awt.Image;

/*****************************************************************
   Class Card, the derived class each card is one object of type Card
   May be placed in a file named Card.java
******************************************************************/

class Card extends Link {
  private Image cardimage;
  private int rank = 0;
  private int cardnum;
  private boolean isFaceDown = false;
  

  public Card (int cardnumber) {
	this.cardnum = cardnumber;
    cardimage = Project3.load_picture("images/gbCard" + cardnum + ".gif");
    // code ASSUMES there is an images sub-dir in your project folder
    
    //ACES
    if(cardnum == 0 ||cardnum == 13 ||cardnum == 26 ||cardnum == 39) {
    	this.rank = 1;
    }
    //TWOS
    else if(cardnum == 1 ||cardnum == 14 ||cardnum == 27 ||cardnum == 40) {
    	this.rank = 2;
    }
    //THREES
    else if(cardnum == 2 ||cardnum == 15 ||cardnum == 28 ||cardnum == 41) {
    	this.rank = 3;
    }
    //FOURS
    else if(cardnum == 3 ||cardnum == 16 ||cardnum == 29 ||cardnum == 42) {
    	this.rank = 4;
    }
    //FIVES
    else if(cardnum == 4 ||cardnum == 17 ||cardnum == 30 ||cardnum == 43) {
    	this.rank = 5;
    }
    //SIXES
    else if(cardnum == 5 ||cardnum == 18 ||cardnum == 31 ||cardnum == 44) {
    	this.rank = 6;
    }
    //SEVENS
    else if(cardnum == 6 ||cardnum == 19 ||cardnum == 32 ||cardnum == 45) {
    	this.rank = 7;
    }
    //EIGHTS
    else if(cardnum == 7 ||cardnum == 20 ||cardnum == 33 ||cardnum == 46) {
    	this.rank = 8;
    }
    //NINES
    else if(cardnum == 8 ||cardnum == 21 ||cardnum == 34 ||cardnum == 47) {
    	this.rank = 9;
    }
    //TENS
    else if(cardnum == 9 ||cardnum == 22 ||cardnum == 35 ||cardnum == 48) {
    	this.rank = 10;
    }
    //JACKS
    else if(cardnum == 10 ||cardnum == 23 ||cardnum == 36 ||cardnum == 49) {
    	this.rank = 11;
    }
    //QUEENS
    else if(cardnum == 11 ||cardnum == 24 ||cardnum == 37 ||cardnum == 50) {
    	this.rank = 12;
    }
    //KINGS
    else if(cardnum == 12 ||cardnum == 25 ||cardnum == 38 ||cardnum == 51) {
    	this.rank = 13;
    }
    else {
    	System.out.println("No card rank found");
    	System.exit(-1);
    }
    
    
    
    if (cardimage == null) {
      System.out.println("Error - image failed to load: images/gbCard" + cardnum + ".gif");
      System.exit(-1);
    }
  }
  public Card getNextCard() {
    return (Card)next;
  }
  
  public void setRank(int r) {
	  this.rank = r;
	  
  }
  
  public int getRank() {
	  return rank;
  }
  
// This method should never have to be used
//  public void setCardnum(int cardnumber) {
//	  this.cardnum = cardnumber;
//  }
  
  public int getCardnum() {
	  return this.cardnum;
  }
  
  public Image getCardImage() {
    return cardimage;
  }
  
  public void setCardImage(Image im) {
	  this.cardimage = im;
	  
  }
  
  public boolean getIsFaceDown() {
	  return this.isFaceDown;
  }
  
  public void setIsFaceDown(boolean face) {
	  this.isFaceDown = face;
  }
  
  
  
} 