package ai;

import java.util.List;
import commmon.Card;
import commmon.MaxFourInt;

public class SpadesModule implements Module {

	private CardTracker tracker;

	public void addTracker(CardTracker tracker) {
		this.tracker = tracker;
	}

	public Card getMove(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer) {
		Card returnCard = null;

		if (playedCards[leadPlayer.getValue()] == null && hand.contains(Card.QUEEN_SPADES)) {
				returnCard = getCardThatCanBeVoided(hand);
				// System.out.println("LEAD CARD RETURNED BY QUEEN MODULE HAVE
				// QUEEN AND RETURN VOIDED CARD " + returnCard);
				// return returnCard;

			if (returnCard == null) {
					returnCard = tracker.getHighestNonLosableCard(hand, true);
					// System.out.println("LEAD CARD RETURNED BY QUEEN MODULE
					// HAVE QUEEN AND RETURN NONELOSABLECARD " + returnCard);
					// return returnCard;
			}

			if (returnCard == null) {
					returnCard = getLowestCard(hand);
//					 System.out.println("LEAD CARD RETURNED BY QUEEN MODULE
//					 HVE QUEEN AND RETURN LOWEST CARD " + returnCard);
//					 return returnCard;
			}
		} else if (playedCards[leadPlayer.getValue()] != null && playedCards[leadPlayer.getValue()].getSuit() == Card.SPADES) {

			returnCard = getHighestSpadeIfPlayingLast(playedCards, hand);

			if (returnCard == null && hand.contains(Card.QUEEN_SPADES)) {
				returnCard = getHighestSpade(hand);
			}

			if (returnCard == null && !hand.contains(Card.QUEEN_SPADES)) {
				returnCard = getHighestSpadeWithoutQueenInHand(playedCards, hand, leadPlayer);
			}
		}

		return returnCard;

	}

	public Card checkForQueenAndReturn(List<Card> hand) {
		if (hand.contains(Card.QUEEN_SPADES)) {
			return Card.QUEEN_SPADES;
		}
		return null;
	}

	public Card getHighestSpadeWithoutQueenInHand(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer) {
		Card returnCard = null;
		int highestValue = 0;
		int valueToBeat = playedCards[leadPlayer.getValue()].getValue();

		for (int i = 0; i < playedCards.length; i++) {
			// Check if anyone has played a higher card than the lead.
			if (playedCards[i] != null && playedCards[i].getSuit() == Card.SPADES
					&& playedCards[i].getValue() > valueToBeat) {
				valueToBeat = playedCards[i].getValue();
			}
		}

		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			if (card.getSuit() == Card.SPADES) {
				int value = card.getValue();
				if (value < valueToBeat) {
					if (returnCard == null) {
						returnCard = card;
					} else {
						if (value > returnCard.getValue()) {
							returnCard = card;
						}
					}
				}
			}
		}

		if (returnCard == null) {
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i) != Card.ACE_SPADES && hand.get(i) != Card.KING_SPADES
						&& hand.get(i) != Card.QUEEN_SPADES && hand.get(i).getSuit() == Card.SPADES) {
					Card tempCard = hand.get(i);
					int tempValue = tempCard.getValue();
					if (tempValue > highestValue) {
						highestValue = tempValue;
						returnCard = tempCard;
					}
				}
			}
		}

		return returnCard;
	}

	public Card getHighestSpadeIfPlayingLast(Card[] playedCards, List<Card> hand) {
		Card returnCard = null;
		int numOfPlayersPlayed = 0;
		int points = 0;
		boolean isAceorKingPlayed = false;

		for (int i = 0; i < playedCards.length; i++) {
			if (playedCards[i] != null) {
				if (playedCards[i] == Card.KING_SPADES || playedCards[i] == Card.ACE_SPADES) {
					isAceorKingPlayed = true;
				}
				points = points + playedCards[i].getPoints();
				numOfPlayersPlayed++;
			}
		}

		// if Ace of King of Spades has been played, play the queen if you have
		// it
		if (isAceorKingPlayed) {
			return checkForQueenAndReturn(hand);
		}

		// if less then 3 points are up for taking then play Ace/King/Highest
		// card to get rid of a danger card
		if (numOfPlayersPlayed == 3 && points < 3) {
			if (hand.contains(Card.ACE_SPADES)) {
				return Card.ACE_SPADES;
			}
			if (hand.contains(Card.KING_SPADES)) {
				return Card.KING_SPADES;
			}
			returnCard = getHighestSpade(hand);
		}

		return returnCard;
	}

	public Card getHighestSpade(List<Card> hand) {
		Card returningCard = null;
		int highestValue = 0;
		Card tempCard = null;
		int tempValue = 0;

		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i) != Card.QUEEN_SPADES && hand.get(i).getSuit() == Card.SPADES) {
				tempCard = hand.get(i);
				tempValue = tempCard.getValue();
				if (tempValue > highestValue) {
					highestValue = tempValue;
					returningCard = tempCard;
				}
			}
		}

		if (returningCard == null) {
			returningCard = checkForQueenAndReturn(hand);
		}
		return returningCard;
	}

	public boolean isThereEnoughCoverForQueen(List<Card> hand) {
		int numOfSpadeCards = tracker.numberOfTimesSpadesIsLead();

		for (int i = 0; i < hand.size(); i++) {
			char suitPlayed = hand.get(i).getSuit();
			if (suitPlayed == Card.SPADES) {
				numOfSpadeCards++;
			}
		}
		return numOfSpadeCards >= 3;
	}

	private Card getCardThatCanBeVoided(List<Card> hand) {
		Card returnCard = null;
		int numOfClubs = 0;
		int numOfDiamonds = 0;
		int numOfHearts = 0;

		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getSuit() == Card.CLUBS) {
				numOfClubs++;
			} else if (hand.get(i).getSuit() == Card.DIAMONDS) {
				numOfDiamonds++;
			} else if (hand.get(i).getSuit() == Card.HEARTS) {
				numOfHearts++;
			}
		}

		if (numOfClubs == 1) {
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).getSuit() == Card.CLUBS) {
					returnCard = hand.get(i);
				}
			}
		} else if (numOfDiamonds == 1) {
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).getSuit() == Card.DIAMONDS) {
					returnCard = hand.get(i);
				}
			}
		} else if (numOfHearts == 1) {
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).getSuit() == Card.HEARTS) {
					returnCard = hand.get(i);
				}
			}
		}
		return returnCard;
	}

	private Card getLowestCard(List<Card> hand) {
		int lowestValue = 15;
		Card lowestValueCard = null;

		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			int value = card.getValue();
			if (value < lowestValue) {
				lowestValue = value;
				lowestValueCard = card;
			}
		}
		return lowestValueCard;
	}

}
