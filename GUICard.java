import javax.swing.*;

//manages the reading and building of the card image icons
//Ryan, Jolene
class GUICard
{
  //14 - A,2,3,4,5,6,7,8,9,T,J,Q,K,X - (X == Joker)
  private static Icon[][] iconCards = new ImageIcon[14][4];
  private static Icon iconBack; //BK
  static boolean iconsLoaded = false;

  //store Icons in 2-D array
  static void loadCardIcons()
  {
    //only initialize once
    if(!iconsLoaded)
    {
      // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
      //Step through suits
      for (int k = 0; k < 14 ; k++)
      {
        //Step through values
        for (int j = 0; j < 4; j++)
        {
          //Use the file name to build the icon in the icon array
          String file = "images/" + turnIntIntoCardValue(k) + turnIntIntoCardSuit(j) + ".gif";
          iconCards[k][j] = new ImageIcon(file);
        }
      }

      //card back icon
      iconBack = new ImageIcon("images/BK.gif");

      iconsLoaded = true;
    }
  }

  // turns 0 - 13 into "A", "2", "3", ... "Q", "K", "X"
  static String turnIntIntoCardValue(int k)
  {
    String[] vals = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A", "X"};
    return vals[k];
  }
  
  // turns 0 - 3 into "C", "D", "H", "S"
  static String turnIntIntoCardSuit(int j)
  {
    String[] suits = {"C", "D", "H", "S"};
    return suits[j];
  }

  // turns "A", "2", "3", ... "Q", "K", "X" into 0 - 13
  static int valueAsInt(Card card)
  {
    int returnValue = -1;
    char[] vals = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', 'X'};
    
    for (int i = 0; i < vals.length; i++)
    {
      //check for matching char values
      if(vals[i] == card.getValue())
      {
        returnValue = i;
      }
    }

    return returnValue;
  }

  // turns Suit enum into 0 - 3
  static int suitAsInt(Card card)
  {
    int returnValue = -1;
    String[] suits = {"CLUBS", "DIAMONDS", "HEARTS", "SPADES"};

    for (int i = 0; i < suits.length; i++)
    {
      //check for matching string values
      if(suits[i].equals(String.valueOf(card.getSuit())))
      {
        returnValue = i;
      }
    }

    return returnValue;
  }

  //accessors
  static public Icon getIcon(Card card)
  {
    loadCardIcons();
    return iconCards[valueAsInt(card)][suitAsInt(card)];
  }

  static public Icon getBackCardIcon()
  {
    loadCardIcons();
    return iconBack;
  }
}