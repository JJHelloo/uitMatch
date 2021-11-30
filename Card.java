//Written by Anna, reviewed by Jolene

class Card
{
  public enum Suit
  {
	CLUBS, DIAMONDS, HEARTS, SPADES
  }
  private char value;
  private boolean errorFlag;
  private Suit suit;
  public static char[] valueRanks = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', 'X'};

  //default constructor - no params passed
  public Card()
  {
    set('A', Suit.SPADES);    
  }
  //constructor - param1 passed
  public Card (char val)
  {
    set(val, Suit.SPADES); 
  }
  //constructor param2 passed
  public Card (Suit suit)
  {
    set('A', suit);
  }
  //constructor - all params passed
  public Card(char val, Suit suit)
  {
    set(val, suit);
  }
  //a copy constructor to create a dup of the original
  //using deep copy
  public Card (Card origCard)
  {
    if (origCard == null)
    {
      System.out.println("Error");
    }
    this.value = origCard.value;
    this.suit = origCard.suit; 
  }
  //Used to display the card
  //if errorFlag == true, return message as such instead of suit and value
  public String toString()
  {
    String invalid = "invalid";
    String aString = "";

    if (getErrorFlag() == true)
    {
      return invalid;
    }
    aString = String.valueOf(getValue()) + " of " + String.valueOf(getSuit());
    return aString;
  }
  
  //returns true if all fields are identical, else false
  public boolean equals(Card card)
  {
    if (value == card.getValue() && suit == card.getSuit() && errorFlag == card.errorFlag)
    {
        return true;
    }
    else
      return false;
  }

  //mutator that accepts legal values
  //takes the place of individual mutators for value and suit
  //use isValid() helper method helper
  public boolean set(char value, Suit suit)
  {
    //if the card is valid, set the values and set errorFlag to false
    if (isValid(value, suit))
    {
      //if good values are passed they are stored
      //error flag is set to false
      this.value = value;
      this.suit = suit;
      errorFlag = false;
    }
    //bad values set errorFlag to true
    else
      errorFlag = true;
    //always return true because errorFlag will mark a card invalid
    return true;
  }

  //sorts an array of Cards based on valueRanks
  //Ryan
  static void arraySort(Card[] cards, int arraySize)
  {
    //bubble sort
    for(int i = 0; i < arraySize - 1; i++)
    {
      for(int j = 0; j < arraySize - i - 1; j++)
      {
        //check if higher value
        if(cards[j].valueRankAsInt() > cards[j + 1].valueRankAsInt())
        {
          //swap cards in array
          Card temp = cards[j];
          cards[j] = cards[j + 1];
          cards[j + 1] = temp;
        }
      }
    }
  }

  //assigns numeric value to a card for arraySort
  //Ryan
  private int valueRankAsInt()
  {
    int returnInt = 0;

    for(int i = 0; i < valueRanks.length; i++)
    {
      if(value == valueRanks[i])
      {
        returnInt = i;
      }
    }

    return returnInt;
  }

  //helper method returns true or false
  private boolean isValid(char value, Suit suit)
  {
    //flags to check for a match
    boolean match = false;

    //possible values to iterate through and compare
    char[] valuesArray = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'X'};

    //check the value against every possibility
    for (int i=0; i < valuesArray.length; i++)
    {
      //flag when a match is found
      if (value == valuesArray[i])
      {
        match = true;
      } 
      //quit the for loop if a match is found
      if (match)
        break;
    }
    
    //return validity as true if a match was found for both value and suit, otherwise false
    if (match)
      return true;
    else
      return false;
  }

  //ACCESSORS//
  public Suit getSuit()
  {
    return suit;
  }

  public char getValue()
  {
    return value;
  }

  public boolean getErrorFlag()
  {
    return errorFlag;
  }
}