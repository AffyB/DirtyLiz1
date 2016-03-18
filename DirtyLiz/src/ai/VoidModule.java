package ai;

import java.util.ArrayList;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;

public class VoidModule implements Module {

	private ArrayList<Character> suitsVoided = new ArrayList<Character>();
	private QueenOfSpadesModule queen = new QueenOfSpadesModule();
	private HeartsModule heart = new HeartsModule();
	private CardTracker tracker;

	public void addTracker(CardTracker tracker) {
		this.tracker = tracker;
	}

	public Card getMove(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer) {
		//System.out.println("VOOOIIDDD BEFORE HAAANNDDD " + hand);
		
		Card returnCard = null;
		queen.addTracker(tracker);
		heart.addTracker(tracker);

		char suit = playedCards[leadPlayer.getValue()].getSuit();
		
//		if(isVoid(suit,hand)){
//			if(queen.isThereEnoughCoverForQueen(hand) && heart.enoughHeartsCover(hand)){
//				if(hand.contains(Card.QUEEN_SPADES)){
//					returnCard = queen.checkForQueenAndReturn(hand);
//				}else{
//					returnCard = heart.findHighestHeart(hand);
//				}
//			}
//		} 
		
		//if(isVoid(suit, hand)){
			//if(returnCard == null){
				if(queen.isThereEnoughCoverForQueen(hand) == false){
					if(hand.contains(Card.QUEEN_SPADES)){
						returnCard = queen.checkForQueenAndReturn(hand);
					}
				}
			//}
		//}
		
		//if(isVoid(suit, hand)){
			if(returnCard == null){
				if(heart.enoughHeartsCover(hand) == false){
					returnCard = heart.findHighestHeart(hand);
				}
			}
		//}
		
		if(returnCard == null){
			if (hand.contains(Card.ACE_SPADES)) {
				returnCard = Card.ACE_SPADES;
			}
		}
		
		if(returnCard == null){
			if (hand.contains(Card.KING_SPADES)) {
				returnCard = Card.KING_SPADES;
			}
		}

		if(returnCard == null){
			returnCard = highestValueCard(hand);
		}
		
		
		
		//System.out.println("VOOIIIDDD AFTER HAANDDD" + hand);
		//System.out.println("VVOOOIIIDDD AFTER CARD " + returnCard);
		return returnCard;
	}

	public boolean isVoid(char suit, List<Card> hand) {
		boolean isVoid = true;

		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			char suitPlayed = card.getSuit();
			if (suitPlayed == suit) {
				isVoid = false;
			}
		}
		suitsVoided.add(suit);
		return isVoid;
	}

	public Card otherSuitCard(List<Card> hand) {
		Card returnCard = null;

//		if (enoughDiamondsCover(hand) == true && enoughClubsCover(hand) == true) {
//			returnCard = highestValueCard(hand);
//		} 
//		
//		if(returnCard == null){
//			if (enoughDiamondsCover(hand) == true && enoughClubsCover(hand) == false) {
//				returnCard = highestValueClubCard(hand);
//			}
//		}
//		
//		if(returnCard == null){
//			if (enoughDiamondsCover(hand) == false && enoughClubsCover(hand) == true) {
//				returnCard = highestValueDiamondCard(hand);
//			}
//		}
		
		if(returnCard == null){
			returnCard = highestValueCard(hand);
		}

		return returnCard;
	}

	public boolean enoughDiamondsCover(List<Card> hand) {
		boolean enoughDiamondsCover = false;

		int numOfLowDiamonds = 0;
		int numeOfMidDiamonds = 0;
		int numOfHighDiamonds = 0;
		int numOfDangerDiamonds = 0;

		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getValue() <= 5) {
				numOfLowDiamonds++;
			} else if (hand.get(i).getValue() > 5 && hand.get(i).getValue() <= 8) {
				numeOfMidDiamonds++;
			} else if (hand.get(i).getValue() > 8 && hand.get(i).getValue() <= 11) {
				numOfHighDiamonds++;
			} else if (hand.get(i).getValue() > 11) {
				numOfDangerDiamonds++;
			}
		}

		if (tracker.numberOfTimesDiamondsIsLead() <= 1) {
			if (numOfLowDiamonds >= 1 || numeOfMidDiamonds >= 1) {
				enoughDiamondsCover = true;
			}
		}

		if (tracker.numberOfTimesDiamondsIsLead() >= 2) {
			if (numOfLowDiamonds >= 1 || numeOfMidDiamonds >= 1) {
				enoughDiamondsCover = true;
			}
		}

		return enoughDiamondsCover;
	}

	public boolean enoughClubsCover(List<Card> hand) {
		boolean enoughClubsCover = false;

		int numOfLowClubs = 0;
		int numeOfMidClubs = 0;
		int numOfHighClubs = 0;
		int numOfDangerClubs = 0;

		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getValue() <= 5) {
				numOfLowClubs++;
			} else if (hand.get(i).getValue() > 5 && hand.get(i).getValue() <= 8) {
				numeOfMidClubs++;
			} else if (hand.get(i).getValue() > 8 && hand.get(i).getValue() <= 11) {
				numOfHighClubs++;
			} else if (hand.get(i).getValue() > 11) {
				numOfDangerClubs++;
			}
		}

		if (tracker.numberOfTimesClubsIsLead() <= 1) {
			if (numOfLowClubs >= 1 || numeOfMidClubs >= 1) {
				enoughClubsCover = true;
			}
		}

		if (tracker.numberOfTimesClubsIsLead() >= 2) {
			if (numOfLowClubs >= 1 || numeOfMidClubs >= 1) {
				enoughClubsCover = true;
			}
		}

		return enoughClubsCover;
	}

	public Card highestValueCard(List<Card> hand) {
		//System.out.println("reaches here");
		Card returnCard = null;
		int highestValue = 0;

		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			if (card.getSuit() != Card.SPADES) {
				int value = card.getValue();
				if (value > highestValue) {
					highestValue = value;
					returnCard = card;
				}
			}
		}
		if (returnCard == null) {
			for (int i = 0; i < hand.size(); i++) {
				Card card = hand.get(i);
				int value = card.getValue();
				if (value > highestValue) {
					highestValue = value;
					returnCard = card;
				}
			}
		}
		
		return returnCard;
	}

	public Card highestValueClubCard(List<Card> hand) {
		Card returnCard = null;
		int highestValue = 0;

		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			if (card.getSuit() == Card.CLUBS) {
				int value = card.getValue();
				if (value > highestValue) {
					highestValue = value;
					returnCard = card;
				}
			}
		}

		return returnCard;
	}

	public Card highestValueDiamondCard(List<Card> hand) {
		Card returnCard = null;
		int highestValue = 0;

		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			if (card.getSuit() == Card.DIAMONDS) {
				int value = card.getValue();
				if (value > highestValue) {
					highestValue = value;
					returnCard = card;
				}
			}
		}

		return returnCard;
	}

}
