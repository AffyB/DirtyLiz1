import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private static ArrayList<Card> notShuffled;
	private static ArrayList<Card> shuffled;
	private static Deck deckObject = null;

	private Deck() {
		notShuffled = Card.getDeck();
		shuffled = new ArrayList<Card>();
		shuffle();
	}
	
	public ArrayList<Card> getCards(){
		return shuffled;
	}

	public void shuffle() {
		notShuffled = Card.getDeck();
		shuffled.clear();
		int cardsLeft = 52;
		Random rng = new Random();

		while (!notShuffled.isEmpty()) {
			Card temp = notShuffled.get(rng.nextInt(cardsLeft));
			shuffled.add(temp);
			notShuffled.remove(temp);
			cardsLeft--;
		}
	}

	private Card[] dealRound() {
		Card[] round = new Card[4];

		for (int i = 0; i < round.length; i++) {
			round[i] = shuffled.get(0);
			shuffled.remove(0);
		}
		return round;
	}
	
	public ArrayList<ArrayList<Card>> deal(){
		ArrayList<Card> player1 = new ArrayList<Card>();
		ArrayList<Card> player2 = new ArrayList<Card>();
		ArrayList<Card> player3 = new ArrayList<Card>();
		ArrayList<Card> player4 = new ArrayList<Card>();
		
		ArrayList<ArrayList<Card>> dealtCards = new ArrayList<ArrayList<Card>>();
		dealtCards.add(player1);
		dealtCards.add(player2);
		dealtCards.add(player3);
		dealtCards.add(player4);
		
		Card[] top4; 		
		while(!(shuffled.isEmpty())){
			top4 = dealRound();
			player1.add(top4[0]);
			player2.add(top4[1]);
			player3.add(top4[2]);
			player4.add(top4[3]);		
		}
		
		return dealtCards;
		
	}

	public static Deck getDeck() {
		if (deckObject == null) {
			deckObject = new Deck();
			return deckObject;
		} else {
			return deckObject;
		}
	}

	public boolean isEmpty() {
		boolean full = true;
		
		if(deckObject == null){
			full = false;
		}
		
		return full;
	}

}
