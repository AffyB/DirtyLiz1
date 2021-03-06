package commmon;
import java.util.ArrayList;

public class Card {

  /** An array-list to represent all the cards in a deck */
  private static ArrayList<Card> fullDeck = new ArrayList<Card>();

  // ***************************
  // * Constants of every card *
  // ***************************

  public static final char HEARTS = 'H';
  public static final char CLUBS = 'C';
  public static final char SPADES = 'S';
  public static final char DIAMONDS = 'D';

  private final int value;
  private final char suit;
  private final int points;
  
  public static final Card ACE_HEARTS = new Card (14, HEARTS, 5);
  public static final Card TWO_HEARTS = new Card (2, HEARTS, 1);
  public static final Card THREE_HEARTS = new Card (3, HEARTS, 1);
  public static final Card FOUR_HEARTS = new Card (4, HEARTS, 1);
  public static final Card FIVE_HEARTS = new Card (5, HEARTS, 1);
  public static final Card SIX_HEARTS = new Card (6, HEARTS, 1);
  public static final Card SEVEN_HEARTS = new Card (7, HEARTS, 1);
  public static final Card EIGHT_HEARTS = new Card (8, HEARTS, 1);
  public static final Card NINE_HEARTS = new Card (9, HEARTS, 1);
  public static final Card TEN_HEARTS = new Card (10, HEARTS, 1);
  public static final Card JACK_HEARTS = new Card (11, HEARTS, 2);
  public static final Card QUEEN_HEARTS = new Card (12, HEARTS, 3);
  public static final Card KING_HEARTS = new Card (13, HEARTS, 4);
  
  public static final Card ACE_CLUBS = new Card (14, CLUBS, 0);
  public static final Card TWO_CLUBS = new Card (2, CLUBS, 0);
  public static final Card THREE_CLUBS = new Card (3, CLUBS, 0);
  public static final Card FOUR_CLUBS = new Card (4, CLUBS, 0);
  public static final Card FIVE_CLUBS = new Card (5, CLUBS, 0);
  public static final Card SIX_CLUBS = new Card (6, CLUBS, 0);
  public static final Card SEVEN_CLUBS = new Card (7, CLUBS, 0);
  public static final Card EIGHT_CLUBS = new Card (8, CLUBS, 0);
  public static final Card NINE_CLUBS = new Card (9, CLUBS, 0);
  public static final Card TEN_CLUBS = new Card (10, CLUBS, 0);
  public static final Card JACK_CLUBS = new Card (11, CLUBS, 0);
  public static final Card QUEEN_CLUBS = new Card (12, CLUBS, 0);
  public static final Card KING_CLUBS = new Card (13, CLUBS, 0);
  
  public static final Card ACE_SPADES = new Card (14, SPADES, 0);
  public static final Card TWO_SPADES = new Card (2, SPADES, 0);
  public static final Card THREE_SPADES = new Card (3, SPADES, 0);
  public static final Card FOUR_SPADES = new Card (4, SPADES, 0);
  public static final Card FIVE_SPADES = new Card (5, SPADES, 0);
  public static final Card SIX_SPADES = new Card (6, SPADES, 0);
  public static final Card SEVEN_SPADES = new Card (7, SPADES, 0);
  public static final Card EIGHT_SPADES = new Card (8, SPADES, 0);
  public static final Card NINE_SPADES = new Card (9, SPADES, 0);
  public static final Card TEN_SPADES = new Card (10, SPADES, 0);
  public static final Card JACK_SPADES = new Card (11, SPADES, 0);
  public static final Card QUEEN_SPADES = new Card (12, SPADES, 13);
  public static final Card KING_SPADES = new Card (13, SPADES, 0);
  
  public static final Card ACE_DIAMONDS = new Card (14, DIAMONDS, 0);
  public static final Card TWO_DIAMONDS = new Card (2, DIAMONDS, 0);
  public static final Card THREE_DIAMONDS = new Card (3, DIAMONDS, 0);
  public static final Card FOUR_DIAMONDS = new Card (4, DIAMONDS, 0);
  public static final Card FIVE_DIAMONDS = new Card (5, DIAMONDS, 0);
  public static final Card SIX_DIAMONDS = new Card (6, DIAMONDS, 0);
  public static final Card SEVEN_DIAMONDS = new Card (7, DIAMONDS, 0);
  public static final Card EIGHT_DIAMONDS = new Card (8, DIAMONDS, 0);
  public static final Card NINE_DIAMONDS = new Card (9, DIAMONDS, 0);
  public static final Card TEN_DIAMONDS = new Card (10, DIAMONDS, 0);
  public static final Card JACK_DIAMONDS = new Card (11, DIAMONDS, 0);
  public static final Card QUEEN_DIAMONDS = new Card (12, DIAMONDS, 0);
  public static final Card KING_DIAMONDS = new Card (13, DIAMONDS, 0);

  // ***********
  // * Methods *
  // ***********

  private Card(int value, char suit, int points) {
    this.value = value;
    this.suit = suit;
    this.points = points;

    fullDeck.add(this);
  }

  public int getValue() {
    return value;
  }

  public char getSuit() {
    return suit;
  }

  public int getPoints() {
    return points;
  }
  
  public static ArrayList<Card> getDeck() {
	  return (ArrayList<Card>)fullDeck.clone();
  }
  
  //needed to override toString() for easier readability for ace, jack, queen and king
  public String toString() {
	  String representation; 
	  String valueToString = Integer.toString(this.value);
	  
	  if(this.value == 13){
		  valueToString = "K";
	  }
	  else if (this.value == 12){
		  valueToString = "Q";
	  }
	  else if (this.value == 11){
		  valueToString = "J";
	  }
	  else if (this.value == 14){
		  valueToString = "A";
	  }
	 
	  representation = valueToString;
	  representation += this.suit;
	  
	  return representation;
  }

}
