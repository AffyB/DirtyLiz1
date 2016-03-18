package ai;

import java.util.ArrayList;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class Smart_HeartFeature extends SmartArtificialIntelligence {

	Player connectedPlayer;
	private PlaySafeModuleImprove safeModule;
	private QueenOfSpadesModule queenModule;
	private HeartsModule heartsModule;
	private LeadModule leadModule;
	
	public Smart_HeartFeature(CardTracker cardTracker) {
		super(cardTracker);
		safeModule = new PlaySafeModuleImprove();
		queenModule = new QueenOfSpadesModule();
		heartsModule = new HeartsModule();
		leadModule = new LeadModule();
		
	}

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt currentPlayer) {
		Card returnCard = null;
		leadModule.addTracker(cardTracker);
		queenModule.addTracker(cardTracker);
		
		returnCard = queenModule.getMove(playedCards, hand, currentPlayer);
		
		if(returnCard == null){
			returnCard = leadModule.getMove(playedCards, hand, currentPlayer);
		}

		if(returnCard == null){
			returnCard = heartsModule.getMove(playedCards, hand, currentPlayer);
		}
		
		if(returnCard == null){
			returnCard = safeModule.getMove(playedCards, hand, currentPlayer); 
		}

		if(returnCard == null){
			returnCard = queenModule.checkForQueenAndReturn(hand);
		}
		
		if(returnCard == null){
			returnCard = heartsModule.findHighestHeart(hand);
		}
		
		if(returnCard == null){
			returnCard = safeModule.playHighestValueCardIfNotLead(hand);
		}
		
		return returnCard;
	}

	public String toString(){
		String ai = "Smart_HeartFeatureAI";
		return ai;	
	}
	
	public boolean isSmart() {
		return true;
	}

	public void connectPlayer(Player player) {
		connectedPlayer = player;		
	}

}
