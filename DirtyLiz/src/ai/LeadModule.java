package ai;

import java.util.ArrayList;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;

public class LeadModule implements Module {

	private CardTracker tracker;

	public void addTracker(CardTracker tracker) {
		this.tracker = tracker;
	}

	public Card getMove(Card[] playedCards, List<Card> hand, MaxFourInt leadPlayer) {
		Card returnCard = null;

		if (playedCards[leadPlayer.getValue()] != null) {
			// Someone else has already lead
			return returnCard;
		}
		returnCard = getLeadSpade(hand);
		
		if(returnCard == null){
			System.out.println("REACHES HERE HIGHDIAMONDORCLUB IN LEAD");
			returnCard = getHighClubOrDiamond(hand);
		}
		
		if(returnCard == null){
			System.out.println("REACHES HERE NONELOSABLECARD IN LEAD");
			returnCard = tracker.getNonLosableCard(hand);
		} 
		
		if (returnCard == null){
			System.out.println("REACHES HERE PLAYLOWEST IN LEAD");
			returnCard = getLowestCard(hand);
		}
		
		if (returnCard == null){
			System.out.println("NULLLLL");
		}
		System.out.println("LEAD CARD RETURNED BY LEAD MODULE " + returnCard);
		return returnCard;
	}

	public Card getLeadSpade(List<Card> hand) {
		Card returnCard = null;
		int spadeValue = 0;
		
		if(hand.contains(Card.QUEEN_SPADES) || hand.contains(Card.ACE_SPADES) || hand.contains(Card.KING_SPADES)){
			return null;
		}else{
			for(int i=0; i<hand.size(); i++){
				Card currentCard = hand.get(i);
				if(currentCard.getSuit() == Card.SPADES){
					if(currentCard.getValue() > spadeValue){
						spadeValue = currentCard.getValue();
						returnCard = currentCard;
					}
				}
			}
		}
		return returnCard;
	}
	
	public Card getHighClubOrDiamond(List<Card> hand) {
		int diamondsLead = tracker.numberOfTimesDiamondsIsLead();
		int clubsLead = tracker.numberOfTimesClubsIsLead();
		
		Card highestClub = null;
		Card highestDiamond = null;
		int highestDiamondValueSoFar = 0;
		int highestClubValueSoFar = 0;
		Card currentCard;
		for(int i=0; i<hand.size(); i++) {
			currentCard = hand.get(i);
			if(currentCard.getSuit() == Card.DIAMONDS && currentCard.getValue() > highestDiamondValueSoFar) {
				highestDiamondValueSoFar = currentCard.getValue();
				highestDiamond = currentCard;
			} else if(currentCard.getSuit() == Card.CLUBS && currentCard.getValue() > highestClubValueSoFar) {
				highestClubValueSoFar = currentCard.getValue();
				highestClub = currentCard;
			}
		}
		
		if(diamondsLead < 2 && clubsLead < 2 ) {
			if(highestClubValueSoFar > highestDiamondValueSoFar) {
				return highestClub;
			} else {
				return highestDiamond;
			}
		} else if(diamondsLead < 2) {
			return highestDiamond;
		} else if(clubsLead < 2) {
			return highestClub;
		} else {
			return null;
		}
	}
	
	public Card getLowestCard(List<Card> hand){
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
