package ai;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import commmon.Card;

public class CardTracker {

	private ArrayList<Card> trackedCards = new ArrayList<Card>();
	private static CardTracker THE_CARD_TRACKER;
	private Map<Character,Integer> leadSuits;
	
	private CardTracker() {
		THE_CARD_TRACKER = this;
		leadSuits = new HashMap<Character,Integer>();
		leadSuits.put(Card.CLUBS, 0);
		leadSuits.put(Card.SPADES, 0);
		leadSuits.put(Card.HEARTS, 0);
		leadSuits.put(Card.DIAMONDS, 0);
	}
	
	public static CardTracker getTracker() {
		if(THE_CARD_TRACKER == null) {
			return new CardTracker();
		} else {
			return THE_CARD_TRACKER;
		}
	}
	
	public void addToCards(Card cardPlayed, boolean wasLead){
		trackedCards.add(cardPlayed);		
		if(wasLead) {
			int amountLead = leadSuits.get(cardPlayed.getSuit());
			leadSuits.put(cardPlayed.getSuit(), amountLead+1);
		}
	}
	
	public ArrayList<Card> getPlayedCards(){
		return trackedCards;		
	}
	
	public void newGame(){
		trackedCards.clear();
		leadSuits = new HashMap<Character,Integer>();
		leadSuits.put(Card.CLUBS, 0);
		leadSuits.put(Card.SPADES, 0);
		leadSuits.put(Card.HEARTS, 0);
		leadSuits.put(Card.DIAMONDS, 0);
	}
	
	public int numberOfTimesHeartsIsLead(){
		return leadSuits.get(Card.HEARTS);
	}
	
	public int numberOfTimesClubsIsLead(){
		return leadSuits.get(Card.CLUBS);
	}
	
	public int numberOfTimesDiamondsIsLead(){
		return leadSuits.get(Card.DIAMONDS);
	}
	
	public int numberOfTimesSpadesIsLead(){
		return leadSuits.get(Card.SPADES);
	}
	
}
