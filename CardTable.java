import javax.swing.*;
import java.awt.*;

//controls the positioning of the panels and cards of the GUI
//Ryan
class CardTable extends JFrame
{
  static int MAX_CARDS_PER_HAND = 56;
  static int MAX_PLAYERS = 2;  //for now, we only allow 2 person games
  
  private int numCardsPerHand;
  private int numPlayers;

  public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;

  //constructor
  public CardTable(String title, int numCardsPerHand, int numPlayers)
  {
    //check if input is below max value allowed
    if(numCardsPerHand <= MAX_CARDS_PER_HAND)
    {
      this.numCardsPerHand = numCardsPerHand;
    }
    else
    {
      //Print error message
      System.out.println("numCardsPerHand above max value.");
      //Terminate program
      System.exit(1);
    }
    
    if(numPlayers <= MAX_PLAYERS)
    {
      this.numPlayers = numPlayers;
    }
    else
    {
      //Print error message
      System.out.println("numPlayers above max value");
      //Terminate program
      System.exit(1);
    }
    
    setTitle(title);

    //layout
    setLayout(new GridLayout(3, 1));

    //initialize JPanels
    pnlComputerHand = new JPanel();
    pnlPlayArea = new JPanel();
    pnlHumanHand = new JPanel();

    //add JPanels to JFrame
    //computerHand top
    add(pnlComputerHand);
    //playArea middle
    add(pnlPlayArea);
    //humanHand bottom
    add(pnlHumanHand);

    //add layouts to compHand and humHand
    pnlComputerHand.setLayout(new FlowLayout());
    pnlHumanHand.setLayout(new FlowLayout());
  }

  //accessors
  public int getNumCardsPerHand()
  {
    return numCardsPerHand;
  }

  public int getNumPlayers()
  {
    return numPlayers;
  }

  public JPanel getPnlComputerHand()
  {
    return pnlComputerHand;
  }

  public JPanel getPnlHumanHand()
  {
    return pnlHumanHand;
  }

  public JPanel getPnlPlayArea()
  {
    return pnlPlayArea;
  }
}