//Written by Jolene and Ryan
//Reviewed by Anna and Justin

import java.lang.Math;

class Deck
{
  //Variable declaration
  public static final int MAX_CARDS = 336; //6*56
  private static Card[] masterPack = new Card[56]; //added 4 spots for jokers
  private Card[] cards = new Card[MAX_CARDS];
  private int topCard = 0;
  private int numPacks = 0;

  //Default constructor that receives no packs to initialize the
  //deck with, calls Deck(int) with one pack, written by Jolene
  public Deck()
  {
    //Calls the Deck(int) constructor for one pack
    this(1);
  }

  //Constructor that receives a number of packs to
  //initialize the deck with, written by Jolene
  public Deck (int numPacks)
  {
    //initializes masterPack[]
    allocateMasterPack();

    //loop through masterPack[] 52 times, setting cards[i] to an 
    //object copy of masterPack[i], do this numPacks times
    for (int j = 0; j < numPacks; j++)
    {
      //Iterates through masterPack[]
      for (int i = 0; i < 52; i++)
      {
        //Creates an object copy of masterPack[i]
        cards[topCard] = new Card(masterPack[i].getValue(), masterPack[i].getSuit());
        topCard++;
      }
    }

    this.numPacks = numPacks;
  }

  //Re-populates the cards[] array with the desired number of decks
  //Written by Jolene
  public void init(int numPacks)
  {
    int count = 0;
    
    this.numPacks = numPacks;

    //Search through cards[] with masterPack[i].equals to see
    //if it's in there, if true flag with count++, when done,
    //add Cards in to cards[] as many times as the difference
    //between numPacks and count
    
    //Iterates through masterPack[]
    for (int j = 0; j < 52; j++)
    {
      //Iterates through cards[]
      for (int i = 0; i < topCard; i++)
      {
        //Checks for masterPack[j] inside of cards[i]
        if (cards[i].equals(masterPack[j]))
          count++;
      }

      //Determines how many of the masterPack[j] are missing from cards[]
      int missing = numPacks - count;

      //Replaces number of missing card from masterPack[] back into card[]
      for (; missing > 0; missing--)
      {
        //Creates an object copy of masterPack[j]
        cards[topCard] = new Card(masterPack[j].getValue(), masterPack[j].getSuit());
        //Increments topCard to account for added cards
        topCard++;
      }
    }
  }

  //Fills the masterPack array with one of each card in a deck
  //Written by Jolene
  private static void allocateMasterPack()
  {
    //Checks to see whether masterPack has been instantiated in this run of the program
    if(masterPack[0] == null)
    {
      //Char array to step through for intitializing value
      char[] vals = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 
        'T', 'J', 'Q', 'K'};
      
      //String array to step through for initializing suit
      String[] suits = {"CLUBS", "DIAMONDS", "HEARTS", "SPADES"};

      //Holds place in masterPack[] for initialization
      int i = 0;

      //Step through suits[]
      for (int j = 0; j < 4; j++)
      {
        //Step through values[]
        for (int k = 0; k < 13 ; k++)
        {
          //Add card to masterPack[]
          masterPack[i] = new Card(vals[k], Card.Suit.valueOf(suits[j]));
          i++;
        }
      }
    }
  }

  //mixes up the cards with the help of the standard random number generator
  //Ryan
  public void shuffle()
  {
    //loops through each card starting at the top (runs 1 less than topCard)
    for(int i = topCard - 1; i > 0; i--)
    {
      //pick card except top card (of cards remaining)
      int randomCard = (int)(Math.random() * i);

      //swap randomCard with top card
      Card temp = cards[i];
      cards[i] = cards[randomCard];
      cards[randomCard] = temp;
    }
  }

  //returns and removes the card in the top position of cards[]
  //Ryan
  public Card dealCard()
  {
    Card cardToDeal = null;
    //check if deck contains cards
    if(topCard > 0)
    {
      //account for empty spaces
      while(cards[topCard - 1] == null)
      {
        topCard--;
      }

      //copy top card, remove top card, decrement top card
      cardToDeal = new Card(cards[topCard - 1]);
      cards[topCard - 1] = null;
      topCard--;
    }
    
    return cardToDeal;
  }

  //make sure that there are not too many instances of the card in the deck if you add it.  Return false if there will be too many.  It should put the card on the top of the deck.
  public boolean addCard(Card card)
  {
    int count = 0;

    //Iterates through cards[]
    for (int i = 0; i < topCard; i++)
    {
      //Checks for card inside of cards[]
      if (card.equals(cards[i]))
        count++;
    }

    //Determines how many of the card are missing from cards[]
    if (count < numPacks)
    {
      //Creates an object copy of the card
      cards[topCard] = new Card(card);
      //Increments topCard to account for added cards
      topCard++;

      //card has been added to the top
      return true;
    }

    //there are already enough of this card in the deck
    return false;
  }

  //you are looking to remove a specific card from the deck.  Put the current top card into its place. 
  public boolean removeCard(Card card)
  {
    for(int i = 0; i < topCard; i++)
    {
      if(card.equals(cards[i]))
      {
        topCard--;
        cards[i] = new Card(cards[topCard]);
        cards[topCard] = null;
        return true;
      }
    }
    
    //card not found
    return false;
  }

  // put all of the cards in the deck back into the right order according to their values.
  public void sort()
  {
    Card.arraySort(cards, topCard);
  }

  //return the number of cards remaining in the deck.
  int getNumCards()
  {
    int numCards = 0;
    for (int i = 0; i < MAX_CARDS; i++)
    {
      if (cards[i] != null)
        numCards++;
    }
    return numCards;  
  }
  //accessor for the int topCard (number of cards in deck)
  //Ryan
  public int getTopCard()
  {
    return topCard;
  }

  //accessor for individual card. returns errorFlag if k is bad
  //Ryan
  public Card inspectCard(int k)
  {
    Card returnCard = null;
    
    //check if card is within deck
    if(k < topCard)
    {
      returnCard = new Card(cards[k]);
    }
    else
    {
      returnCard = new Card('Z'); //errorFlag == true
    }

    return returnCard;
  }
}