/* 
Justin Johnson
November 2021
*/
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.border.*;
import java.util.*;

//phase 3
public class Main implements ActionListener
{
   static final int NUM_CARDS_PER_HAND = 7;
   static final int NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS];
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS];
   static JLabel[] winner = new JLabel[NUM_PLAYERS];
   static JButton[] humanCards = new JButton[NUM_CARDS_PER_HAND];
   private int numHumanCards = NUM_CARDS_PER_HAND;
   private int numCompCards = NUM_CARDS_PER_HAND;
   private int nextPlayer = 1; //0 == computer, 1 == player
   private boolean roundStarted = true;

   // call new CardGameOutline instance 
   CardGameOutline suitMatchGame = new CardGameOutline( 
      numPacksPerDeck, numJokersPerPack,  
      numUnusedCardsPerPack, unusedCardsPerPack, 
      NUM_PLAYERS, NUM_CARDS_PER_HAND);
   
   public static void main(String[] args)
   { 
    int numPacksPerDeck = 1;
    int numJokersPerPack = 2;
    int numUnusedCardsPerPack = 0;
    Card[] unusedCardsPerPack = null;

    // establish main frame in which program will run
    CardTable myCardTable 
        = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
    myCardTable.setSize(800, 700);
    myCardTable.setLocationRelativeTo(null);
    myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // show everything to the user
    myCardTable.setVisible(true);

    // deal 
    suitMatchGame.deal();

    // CREATE LABELS
    playLabelText[0] = new JLabel("Computer", JLabel.CENTER);
    playLabelText[1] = new JLabel("You", JLabel.CENTER);
    winner[0] = new JLabel("Computer Wins!", JLabel.CENTER);
    winner[1] = new JLabel("You Win!", JLabel.CENTER);
    winner[0].setVisible(false);
    winner[1].setVisible(false);

    //for displaying the cards played in the center panel
    for (int i = 0; i < NUM_PLAYERS; i++)
    {
      playedCardLabels[i] = new JLabel("   ");
    }

    //labels for computer hand
    for (int i = 0; i < NUM_CARDS_PER_HAND; i++)
    {
      computerLabels[i] = new JLabel(GUICard.getBackCardIcon());
    }

    //labels for human hand - buttons
    for (int i = 0; i < NUM_CARDS_PER_HAND; i++)
    {
      Icon cardIcon = GUICard.getIcon(suitMatchGame.getHand(1).inspectCard(i));
      humanCards[i] = new JButton(cardIcon);

      //set the actionCommand of the button to it's place in the row
      humanCards[i].setActionCommand(Integer.toString(i));

      //action listener
      humanCards[i].addActionListener(this);
    }

    //button for new round at end of game
    JButton newRound = new JButton("New Round");
    newRound.addActionListener(this);
    newRound.setVisible(false);

    //Borders
    Border computerHandBorder = BorderFactory.createTitledBorder("Computer Hand");
    Border playingAreaBorder = BorderFactory.createTitledBorder("Playing Area");
    Border humanHandBorder = BorderFactory.createTitledBorder("Your Hand");

    myCardTable.pnlComputerHand.setBorder(computerHandBorder);
    myCardTable.pnlPlayArea.setBorder(playingAreaBorder);
    myCardTable.pnlHumanHand.setBorder(humanHandBorder);

    // ADD LABELS TO PANELS
    //Computer Labels
    for (int i = 0; i < NUM_CARDS_PER_HAND; i++)
      myCardTable.pnlComputerHand.add(computerLabels[i]);

    //Playing Area
    myCardTable.pnlPlayArea.setLayout(new GridLayout(2, 4));
    myCardTable.pnlPlayArea.add(new JLabel("    "));
    myCardTable.pnlPlayArea.add(playedCardLabels[0]);
    myCardTable.pnlPlayArea.add(playedCardLabels[1]);
    myCardTable.pnlPlayArea.add(newRound);
    myCardTable.pnlPlayArea.add(winner[0]);
    myCardTable.pnlPlayArea.add(playLabelText[0]);
    myCardTable.pnlPlayArea.add(playLabelText[1]);
    myCardTable.pnlPlayArea.add(winner[1]);

    //Player Labels
    for (int i = 0; i < NUM_CARDS_PER_HAND; i++)
      myCardTable.pnlHumanHand.add(humanCards[i]);

    // show everything to the user
    myCardTable.setVisible(true);
   }

  //returns a new random card
  static Card randomCardGenerator()
  {
    int value = (int)(Math.random() * 14);
    int suitInt = (int)(Math.random() * 4);
    Card.Suit suit;

    if(suitInt == 0)
      suit = Card.Suit.CLUBS;
    else if(suitInt == 1)
      suit = Card.Suit.DIAMONDS;
    else if(suitInt == 2)
      suit = Card.Suit.HEARTS;
    else //3
      suit = Card.Suit.SPADES;

    return new Card(Card.valueRanks[value], suit);
  }

  public void actionPerformed(ActionEvent e)
  {
    Card humanPlayed;
    Card computerPlayed;

    if (e.actionCommand.equals("New Round"))
    {
      newRound();

      if(nextPlayer == 0) //if computer is going first
      {
        //play a computer card
        computerPlayed = new playComputerCard();
      }
      
      return;
    }

    int actionCard = Integer.parseInt(e.getActionCommand());

    if(roundStarted)
    {
      //player goes first
      if(nextPlayer == 1)
      {
        //play the human card
        humanPlayed = new Card(playHumanCard(actionCard));
        //play a computer card
        computerPlayed = new playComputerCard(humanPlayed);

        String computerCardSuit = String.valueOf(computerPlayed.getSuit());
        String humanCardSuit = String.valueOf(humanPlayed.getSuit());

        //computer wins if suits matching
        if(computerCardSuit.equals(humanCardSuit))
        {
          winner[0].setVisible(true);
        }

        //computer goes next
        nextPlayer = 0;
      }
      else //nextPlayer == 0. player goes 2nd
      {
        //play the human card
        humanPlayed = new playHumanCard(actionCard);

        String computerCardSuit = String.valueOf(computerPlayed.getSuit());
        String humanCardSuit = String.valueOf(humanPlayed.getSuit());

        //human wins if suits matching
        if(computerCardSuit.equals(humanCardSuit))
        {
          winner[1].setVisible(true);
        }

        //player goes next
        nextPlayer = 1;
      }
      
      //display newRound button
      roundStarted = false;
      newRound.setVisible(true);
    }
  }

  Card playHumanCard(int actionCard)
  {
    //call playCard(int, int)
    Card played = new Card(suitMatchGame.playCard(1, actionCard));

    //add card played to the PlayArea
    Icon playedIcon = GUICard.getIcon(played);
    playedCardLabels[1] = new Label(playedIcon);
    
    //reset actioncommand names
    for (int i = 0; i < numHumanCards; i++)
    {
      humanCards[i].setActionCommand(Integer.toString(i));
    }

    //remove JButton from JPanel
    pnlHumanHand.remove(humanCards[actionCard]);
    
    //remove button from humanCards
    numHumanCards--;
    for(int i = actionCard; i < numHumanCards; i++)
    {
      humanCards[i] = humanCards[i+1];
    }
    humanCards[numHumanCards] = null;

    return played;
  }

  Card playComputerCard(Card human)
  {
    //pick a random card to play
    int cardToPlay = (int)(Math.random() * numCompCards);

    //try to match suit if computer goes 2nd (if no card found uses above random card)
    if(nextPlayer == 1)
    {
      for(int i = 0; i < suitMatchGame.getHand(0).length; i++)
      {
        String computerCardSuit = String.valueOf(suitMatchGame.getHand(0).inspectCard(i).getSuit());
        String humanCardSuit = String.valueOf(human.getSuit());

        if(computerCardSuit.equals(humanCardSuit))
        {
          cardToPlay = i;
        }
      }
    }

    //call playCard(int, int)
    Card played = new Card(suitMatchGame.playCard(0, cardToPlay));

    //add card played to the PlayArea
    Icon playedIcon = GUICard.getIcon(played);
    playedCardLabels[0] = new Label(playedIcon);

    //remove JLabel from JPanel
    pnlComputerHand.remove(computerLabels[cardToPlay]);
    
    //remove label from computerLabels
    numCompCards--;
    for(int i = actionCard; i < numCompCards; i++)
    {
      computerLabels[i] = computerLabels[i+1];
    }
    computerLabels[numCompCards] = null;

    return played;
  }

  void newRound()
  {
    //remove the cards from the center
    for (int i = 0; i < NUM_PLAYERS; i++)
      playedCardLabels[i] = new JLabel("   ");
    
    //make newRound button invisible
    newRound.setVisible(false);

    //make winnerText invisible
    winner[0].setVisible(false);
    winner[1].setVisible(false);
    
    //round has been reset
    roundStarted = true;
  }
}