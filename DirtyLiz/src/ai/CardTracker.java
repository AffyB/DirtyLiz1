package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commmon.Card;

public class CardTracker {

	private ArrayList<Card> trackedCards = new ArrayList<Card>();
	private static CardTracker THE_CARD_TRACKER;
	private Map<Character, Integer> leadSuits;
	private ArrayList<Card> heartsPlayed = new ArrayList<Card>();
	private ArrayList<Card> spadesPlayed = new ArrayList<Card>();
	private ArrayList<Card> clubsPlayed = new ArrayList<Card>();
	private ArrayList<Card> diamondsPlayed = new ArrayList<Card>();
	private ArrayList<Card> heartsAvailable = new ArrayList<Card>();
	private ArrayList<Card> spadesAvailable = new ArrayList<Card>();
	private ArrayList<Card> clubsAvailable = new ArrayList<Card>();
	private ArrayList<Card> diamondsAvailable = new ArrayList<Card>();
	private static final Card[] ALL_HEARTS = new Card[] { Card.ACE_HEARTS, Card.TWO_HEARTS, Card.THREE_HEARTS,
			Card.FOUR_HEARTS, Card.FIVE_HEARTS, Card.SIX_HEARTS, Card.SEVEN_HEARTS, Card.EIGHT_HEARTS, Card.NINE_HEARTS,
			Card.TEN_HEARTS, Card.JACK_HEARTS, Card.QUEEN_HEARTS, Card.KING_HEARTS};
	private static final Card[] ALL_SPADES = new Card[] { Card.ACE_SPADES, Card.TWO_SPADES, Card.THREE_SPADES,
			Card.FOUR_SPADES, Card.FIVE_SPADES, Card.SIX_SPADES, Card.SEVEN_SPADES, Card.EIGHT_SPADES, Card.NINE_SPADES,
			Card.TEN_SPADES, Card.JACK_SPADES, Card.QUEEN_SPADES, Card.KING_SPADES};
	private static final Card[] ALL_CLUBS = new Card[] { Card.ACE_CLUBS, Card.TWO_CLUBS, Card.THREE_CLUBS,
			Card.FOUR_CLUBS, Card.FIVE_CLUBS, Card.SIX_CLUBS, Card.SEVEN_CLUBS, Card.EIGHT_CLUBS, Card.NINE_CLUBS,
			Card.TEN_CLUBS, Card.JACK_CLUBS, Card.QUEEN_CLUBS, Card.KING_CLUBS};
	private static final Card[] ALL_DIAMONDS = new Card[] { Card.ACE_DIAMONDS, Card.TWO_DIAMONDS, Card.THREE_DIAMONDS,
			Card.FOUR_DIAMONDS, Card.FIVE_DIAMONDS, Card.SIX_DIAMONDS, Card.SEVEN_DIAMONDS, Card.EIGHT_DIAMONDS, Card.NINE_DIAMONDS,
			Card.TEN_DIAMONDS, Card.JACK_DIAMONDS, Card.QUEEN_DIAMONDS, Card.KING_DIAMONDS};

	private CardTracker() {
		THE_CARD_TRACKER = this;
		leadSuits = new HashMap<Character, Integer>();
		leadSuits.put(Card.CLUBS, 0);
		leadSuits.put(Card.SPADES, 0);
		leadSuits.put(Card.HEARTS, 0);
		leadSuits.put(Card.DIAMONDS, 0);
	}

	public static CardTracker getTracker() {
		if (THE_CARD_TRACKER == null) {
			return new CardTracker();
		} else {
			return THE_CARD_TRACKER;
		}
	}

	public void addToCards(Card cardPlayed, boolean wasLead) {
		trackedCards.add(cardPlayed);
		if (wasLead) {
			// System.out.println(cardPlayed + " cardTracker cardplayed");
			// System.out.println(cardPlayed.getSuit() + " cardTracker
			// cardplayed suit");
			int amountLead = leadSuits.get(cardPlayed.getSuit());
			leadSuits.put(cardPlayed.getSuit(), amountLead + 1);
		}
		removeCardFromAvailableCard(cardPlayed);
		findSuitableList(cardPlayed.getSuit()).add(cardPlayed);
	}
	
	public void removeCardFromAvailableCard(Card cardPlayed){
		char suit = cardPlayed.getSuit();
		findSuitableListForAvailableCards(suit).remove(cardPlayed);		
	}
	
	public Card getNonLosableCard(List<Card> hand) {
		if(hand.size() > 7) {
			return getNonLosableCardForBigHand(hand);
		} else {
			return getNonLosableCardForSmallHand(hand);
		}
	}

	private Card getNonLosableCardForBigHand(List<Card> hand) {
		int highestValueSoFar = 0;
		Card returnCard = null;
		
		for (int i = 0; i < hand.size(); i++) {
			Card currentCard = hand.get(i);
			if(isThereTwoOrFewerLowerCards(currentCard, hand) && currentCard.getValue() > highestValueSoFar){
				returnCard = currentCard;			
			}			
		}
		return returnCard;
	}
	
	private Card getNonLosableCardForSmallHand(List<Card> hand) {
		int lowestValueSoFar = 0;
		Card returnCard = null;
		
		for (int i = 0; i < hand.size(); i++) {
			Card currentCard = hand.get(i);
			if(isThere2OrHigherLowerCards(currentCard, hand) && currentCard.getValue() < lowestValueSoFar){
				returnCard = currentCard;			
			}			
		}
		return returnCard;
	}
	
	private boolean isThereTwoOrFewerLowerCards(Card currentCard, List<Card> hand){
		ArrayList<Card> checkingList = findSuitableListForAvailableCards(currentCard.getSuit());
		int lowerValues = 0;
		
		for(int i=0; i<checkingList.size(); i++){
			if(!hand.contains(checkingList.get(i))){
				if(checkingList.get(i).getValue() < currentCard.getValue()){
					lowerValues++;
				}
			}
		}
		return lowerValues <= 2;
	}
	
	private boolean isThere2OrHigherLowerCards(Card currentCard, List<Card> hand){
		ArrayList<Card> checkingList = findSuitableListForAvailableCards(currentCard.getSuit());
		int higherValues = 0;
		
		for(int i=0; i<checkingList.size(); i++){
			if(!hand.contains(checkingList.get(i))){
				if(checkingList.get(i).getValue() > currentCard.getValue()){
					higherValues++;
				}
			}
		}
		return higherValues <= 2;
	}
	
	//check specific card against available cardlist, no more then 2 lower then it it will return true or false 

	private ArrayList<Card> findSuitableList(char suit) {
		if (suit == Card.HEARTS) {
			return heartsPlayed;
		} else if (suit == Card.SPADES) {
			return spadesPlayed;
		} else if (suit == Card.DIAMONDS) {
			return diamondsPlayed;
		} else if (suit == Card.CLUBS) {
			return clubsPlayed;
		}
		return null;
	}
	
	private ArrayList<Card> findSuitableListForAvailableCards(char suit) {
		if (suit == Card.HEARTS) {
			return heartsAvailable;
		} else if (suit == Card.SPADES) {
			return spadesAvailable;
		} else if (suit == Card.DIAMONDS) {
			return diamondsAvailable;
		} else if (suit == Card.CLUBS) {
			return clubsAvailable;
		}
		return null;
	}

	public ArrayList<Card> getPlayedCards() {
		return trackedCards;
	}

	public void newGame() {
		trackedCards.clear();
		heartsPlayed.clear();
		clubsPlayed.clear();
		diamondsPlayed.clear();
		spadesPlayed.clear();
		refreshAvailableCards();
		leadSuits = new HashMap<Character, Integer>();
		leadSuits.put(Card.CLUBS, 0);
		leadSuits.put(Card.SPADES, 0);
		leadSuits.put(Card.HEARTS, 0);
		leadSuits.put(Card.DIAMONDS, 0);
	}

	private void refreshAvailableCards(){
		clubsAvailable.addAll(Arrays.asList(ALL_CLUBS));
		spadesAvailable.addAll(Arrays.asList(ALL_SPADES));
		heartsAvailable.addAll(Arrays.asList(ALL_HEARTS));
		diamondsAvailable.addAll(Arrays.asList(ALL_DIAMONDS));
	}

	public int numberOfTimesHeartsIsLead() {
		return leadSuits.get(Card.HEARTS);
	}

	public int numberOfTimesClubsIsLead() {
		return leadSuits.get(Card.CLUBS);
	}

	public int numberOfTimesDiamondsIsLead() {
		return leadSuits.get(Card.DIAMONDS);
	}

	public int numberOfTimesSpadesIsLead() {
		return leadSuits.get(Card.SPADES);
	}
	
	public boolean isTheBestHeartInHandBeatable(Card bestHeartInHand, List<Card> hand){
		List<Card> heartsAvailableThatArentInMyHand = new ArrayList<Card>();
		int valueToBeat = bestHeartInHand.getValue();
		
		for(int i=0; i<heartsAvailable.size(); i++){
			Card checkingHeartCard = heartsAvailable.get(i);
			if(!hand.contains(checkingHeartCard)){
				heartsAvailableThatArentInMyHand.add(checkingHeartCard);
			}
		}
		
		for(int i=0; i<heartsAvailableThatArentInMyHand.size(); i++){
			if(heartsAvailableThatArentInMyHand.get(i).getValue() > valueToBeat)
				return true;
		}
		
		return false;
	}

}
