import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class DeckDemo {

	@Test
	public void test() {
		Deck testDeck = Deck.getDeck();
		ArrayList<Card> cards = testDeck.getCards();
		printDeck(cards);
		testDeck.shuffle();
		printDeck(cards);
		
		System.out.println("-----------------");
		
		ArrayList<ArrayList<Card>> dealtDeck = testDeck.deal();
		printHands(dealtDeck);
		
		System.out.println("-----------------");
		
		testDeck.shuffle();
		dealtDeck = testDeck.deal();
		printHands(dealtDeck);
		printDeck(testDeck.getCards());
		
	}
	
	private void printHands(ArrayList<ArrayList<Card>> dealtDeck){
		for(int i=0; i<dealtDeck.size(); i++){
			System.out.print("Player" + i + ": ");
			printDeck(dealtDeck.get(i));
		}	
	}
	
	private void printDeck(ArrayList<Card> cards){
		Collection<Card> allCards = Card.getDeck();
		System.out.print("Size: " + cards.size() + " ");
		System.out.print("[");
		for(int i=0; i< cards.size(); i++){
			System.out.print(cards.get(i).toString() + ",");
		}
		System.out.println("]");
		boolean hasEveryCard = cards.containsAll(allCards);
		System.out.println("Does it have all cards? " + hasEveryCard);
	}
	 
}
