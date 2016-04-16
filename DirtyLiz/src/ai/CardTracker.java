package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commmon.Card;

public class CardTracker {

	private ArrayList<Card> trackedCards;
	private static CardTracker THE_CARD_TRACKER;
	private Map<Character, Integer> leadSuits;
	private ArrayList<Card> heartsPlayed;
	private ArrayList<Card> spadesPlayed;
	private ArrayList<Card> clubsPlayed;
	private ArrayList<Card> diamondsPlayed;
	private ArrayList<Card> heartsAvailable;
	private ArrayList<Card> spadesAvailable;
	private ArrayList<Card> clubsAvailable;
	private ArrayList<Card> diamondsAvailable;
	public static final Card[] ALL_HEARTS = new Card[] { Card.ACE_HEARTS, Card.TWO_HEARTS, Card.THREE_HEARTS,
			Card.FOUR_HEARTS, Card.FIVE_HEARTS, Card.SIX_HEARTS, Card.SEVEN_HEARTS, Card.EIGHT_HEARTS, Card.NINE_HEARTS,
			Card.TEN_HEARTS, Card.JACK_HEARTS, Card.QUEEN_HEARTS, Card.KING_HEARTS };
	public static final Card[] ALL_SPADES = new Card[] { Card.ACE_SPADES, Card.TWO_SPADES, Card.THREE_SPADES,
			Card.FOUR_SPADES, Card.FIVE_SPADES, Card.SIX_SPADES, Card.SEVEN_SPADES, Card.EIGHT_SPADES, Card.NINE_SPADES,
			Card.TEN_SPADES, Card.JACK_SPADES, Card.QUEEN_SPADES, Card.KING_SPADES };
	public static final Card[] ALL_CLUBS = new Card[] { Card.ACE_CLUBS, Card.TWO_CLUBS, Card.THREE_CLUBS,
			Card.FOUR_CLUBS, Card.FIVE_CLUBS, Card.SIX_CLUBS, Card.SEVEN_CLUBS, Card.EIGHT_CLUBS, Card.NINE_CLUBS,
			Card.TEN_CLUBS, Card.JACK_CLUBS, Card.QUEEN_CLUBS, Card.KING_CLUBS };
	public static final Card[] ALL_DIAMONDS = new Card[] { Card.ACE_DIAMONDS, Card.TWO_DIAMONDS, Card.THREE_DIAMONDS,
			Card.FOUR_DIAMONDS, Card.FIVE_DIAMONDS, Card.SIX_DIAMONDS, Card.SEVEN_DIAMONDS, Card.EIGHT_DIAMONDS,
			Card.NINE_DIAMONDS, Card.TEN_DIAMONDS, Card.JACK_DIAMONDS, Card.QUEEN_DIAMONDS, Card.KING_DIAMONDS };
 
	private CardTracker() {
		THE_CARD_TRACKER = this;
		leadSuits = new HashMap<Character, Integer>();
		leadSuits.put(Card.CLUBS, 0);
		leadSuits.put(Card.SPADES, 0);
		leadSuits.put(Card.HEARTS, 0);
		leadSuits.put(Card.DIAMONDS, 0);
		trackedCards = new ArrayList<Card>();
		spadesPlayed = new ArrayList<Card>();
		diamondsPlayed = new ArrayList<Card>();
		heartsPlayed = new ArrayList<Card>();
		clubsPlayed = new ArrayList<Card>();
		heartsAvailable = new ArrayList<Card>();
		spadesAvailable = new ArrayList<Card>();
		clubsAvailable = new ArrayList<Card>();
		diamondsAvailable = new ArrayList<Card>();
		clubsAvailable.addAll(Arrays.asList(ALL_CLUBS));
		spadesAvailable.addAll(Arrays.asList(ALL_SPADES));
		heartsAvailable.addAll(Arrays.asList(ALL_HEARTS));
		diamondsAvailable.addAll(Arrays.asList(ALL_DIAMONDS));
		
	}

	//cardTracker is a singleton to avoid overriding contents when adding to it
	public static CardTracker getTracker() {
		if (THE_CARD_TRACKER == null) {
			return new CardTracker();
		} else {
			return THE_CARD_TRACKER;
		}
	}

	//add played card to the trackere
	public void addToCards(Card cardPlayed, boolean wasLead) {
		trackedCards.add(cardPlayed);
		if (wasLead) {
			// System.out.println(cardPlayed + " cardTracker cardplayed");
			// System.out.println(cardPlayed.getSuit() + " cardTracker
			// cardplayed suit");
			int amountLead = leadSuits.get(cardPlayed.getSuit());
			leadSuits.put(cardPlayed.getSuit(), amountLead + 1);
		}
		removeCardFromAvailableCards(cardPlayed);
		findSuitableList(cardPlayed.getSuit()).add(cardPlayed);
	}

	//removes played card from list of available cards
	//keeps track of what cards are still left to be played
	public void removeCardFromAvailableCards(Card cardPlayed) {
		char suit = cardPlayed.getSuit();
		findSuitableListForAvailableCards(suit).remove(cardPlayed);
	}

	//based on all info, returns highest card in players hand which is guaranteed to beaten by another card in another player's hand
	public Card getHighestNonLosableCard(List<Card> hand, boolean isQueenPresentInHand) {
		int highestValueSoFar = 0;
		Card returnCard = null;

		for (int i = 0; i < hand.size(); i++) {
			Card currentCard = hand.get(i);
			if (isThereTwoOrFewerLowerCards(currentCard, hand) && currentCard.getValue() > highestValueSoFar) {
				if (isQueenPresentInHand) {
					if (currentCard.getSuit() != Card.SPADES) {
						returnCard = currentCard;
					}
				} else {
					returnCard = currentCard;
				}
			}
		}
		return returnCard;
	}

	//checks to see if there are lower cards still left to play
	// check specific card against available card list, no more then 2 lower then
	// it it will return true or false
	private boolean isThereTwoOrFewerLowerCards(Card cardToCheck, List<Card> hand) {
		ArrayList<Card> suitableList = findSuitableListForAvailableCards(cardToCheck.getSuit());
		ArrayList<Card> checkingList = new ArrayList<Card>();
		int howManyTimesMyCardIsBeaten = 0;

		for (int i = 0; i < suitableList.size(); i++) {
			Card card = suitableList.get(i);
			if (!hand.contains(card)) {
				checkingList.add(card);
			}
		}

		if (checkingList.size() == 0) {
			return false;
		}

		for (int i = 0; i < checkingList.size(); i++) {
			if (checkingList.get(i).getValue() < cardToCheck.getValue()) {
				howManyTimesMyCardIsBeaten++;
			}
		}

		if (checkingList.size() > 2) {
			return howManyTimesMyCardIsBeaten <= 2;
		} else if (checkingList.size() == 2) {
			return howManyTimesMyCardIsBeaten <= 1;
		} else if (checkingList.size() == 1) {
			return howManyTimesMyCardIsBeaten < 1;
		} else {
			return false;
		}
	}

	
	//matches suit to its corresponding array of played cards
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

	//matches suit to its corresponding array of available cards
	public ArrayList<Card> findSuitableListForAvailableCards(char suit) {
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

	//resets everything
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

	private void refreshAvailableCards() {
		clubsAvailable.clear();
		spadesAvailable.clear();
		heartsAvailable.clear();
		diamondsAvailable.clear();
		clubsAvailable.addAll(Arrays.asList(ALL_CLUBS));
		spadesAvailable.addAll(Arrays.asList(ALL_SPADES));
		heartsAvailable.addAll(Arrays.asList(ALL_HEARTS));
		diamondsAvailable.addAll(Arrays.asList(ALL_DIAMONDS));
	}
	
	//tracks the number of times a suit has been lead
	public int numberOfTimesSuitIsLead(char suit) {
		if(suit == Card.CLUBS) {
			return numberOfTimesClubsIsLead();
		} else if(suit == Card.DIAMONDS) {
			return numberOfTimesDiamondsIsLead();
		} else if(suit == Card.HEARTS) {
			return numberOfTimesHeartsIsLead();
		} else {
			return numberOfTimesSpadesIsLead();
		}
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

	//based on all information returns true or false if a heart is safe to lead 
	//the heart will be gauranteed to be beaten by another player
	public boolean isHeartSafeToLead(Card bestHeartInHand, List<Card> hand) {
		List<Card> heartsAvailableThatArentInMyHand = new ArrayList<Card>();
		int valueToBeat = bestHeartInHand.getValue();

		for (int i = 0; i < heartsAvailable.size(); i++) {
			Card heart = heartsAvailable.get(i);
			if (!hand.contains(heart)) {
				heartsAvailableThatArentInMyHand.add(heart);
			}
		}
		if (heartsAvailableThatArentInMyHand.size() == 0) {
			return false;
		}

		int howManyTimesMyCardIsBeaten = 0;
		for (int i = 0; i < heartsAvailableThatArentInMyHand.size(); i++) {
			if (heartsAvailableThatArentInMyHand.get(i).getValue() < valueToBeat) {
				howManyTimesMyCardIsBeaten++;
			}

		}

		if (heartsAvailableThatArentInMyHand.size() > 2) {
			return howManyTimesMyCardIsBeaten <= 2;
		} else if (heartsAvailableThatArentInMyHand.size() == 2) {
			return howManyTimesMyCardIsBeaten <= 1;
		} else if (heartsAvailableThatArentInMyHand.size() == 1) {
			return howManyTimesMyCardIsBeaten < 1;
		} else {
			return false;
		}
	}

}
