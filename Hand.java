/*Written by Justin, reviewed by Ryan
hand of the user playing
November, 2021
*/

class Hand 
{
  // public value of max cards
  public static final int MAX_CARDS = 50;
  // private variables
  
  private Card[] myCards;
  private int numCards;

  // default constructor
  public Hand() 
  {
    myCards = new Card[MAX_CARDS];
    numCards = 0;
  }

  // removes all the cards in a hand
  public void resetHand() 
  {
    myCards = new Card[MAX_CARDS];
    numCards = 0;
  }

  // boolean to add card to hand
  public boolean takeCard(Card card) 
  {
    boolean take = false;
    if (numCards < MAX_CARDS)
    {
      myCards[numCards] = new Card(card.getValue(), card.getSuit());
      numCards++;
      take = true;
    }
    return take;
  }

  // returns and removes the card in the top occupied position of the array.
  public Card playCard() 
  {
    Card played = new Card(myCards[numCards - 1].getValue(), myCards[numCards - 1].getSuit());
    myCards[numCards - 1] = null;
    numCards--;
    return played;
  }

  //This will remove the card at a location and slide all of the cards down one spot in the myCards array. 
  public Card playCard(int cardIndex)
  {
    if ( numCards == 0 ) //error
    {
         //Creates a card that does not work
      return new Card('M', Card.Suit.SPADES);
    }
      //Decreases numCards.
    Card card = myCards[cardIndex];
      
    numCards--;
    for(int i = cardIndex; i < numCards; i++)
    {
      myCards[i] = myCards[i+1];
    }
    myCards[numCards] = null;
      
    return card;
  }

  // a stringizer that the client can use to display the entire hand.
  public String toString() 
  {
    String s = "Hand = ( ";
      for(int i =  0; i < numCards; i++)
      {
        s += myCards[i].toString() + ", ";
      } 
      // if statement to delete the , and space at the end of the string
      if(numCards > 0 )
      { 
        String s2 = s.substring(0, s.length() - 2);
        return s2 + " )";
      }
      // return an empty hand
      return s + ")";
  }
  
  // accessor return numCards value
  public int getNumCards()
  {
    return numCards;
  }

  // Accessor for an individual card
  public Card inspectCard(int k)
  {
    Card card;
    if( k < numCards) 
    {
      // return card if k is valid
      card = new Card(myCards[k]);
      return card;
    } 
    else
    // should throw error flag true if k is not valid
      card = new Card('B');
      return card;
  }

  //add the following method
  void sort()
  {
    Card.arraySort(myCards, numCards);
  }
} // end hand class