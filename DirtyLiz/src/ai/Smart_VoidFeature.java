package ai;

import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class Smart_VoidFeature extends SmartArtificialIntelligence {

	Player connectedPlayer;
	private PlaySafeModuleImprove safeModule;
	private QueenOfSpadesModule queenModule;
	private HeartsModule heartsModule;
	private LeadModule leadModule;
	private VoidModule voidModule;
	
	public Smart_VoidFeature(CardTracker cardTracker) {
		super(cardTracker);
		safeModule = new PlaySafeModuleImprove();
		queenModule = new QueenOfSpadesModule();
		heartsModule = new HeartsModule();
		leadModule = new LeadModule();
		voidModule = new VoidModule();
		
	}

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt currentPlayer) {
		Card returnCard = null;
		leadModule.addTracker(cardTracker);
		voidModule.addTracker(cardTracker);
		queenModule.addTracker(cardTracker);
		heartsModule.addTracker(cardTracker);
		
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
			returnCard = voidModule.getMove(playedCards, hand, currentPlayer);
		}
		
		return returnCard;
	}

	public String toString(){
		String ai = "Smart_VoidFeatureAI";
		return ai;	
	}
	
	public boolean isSmart() {
		return true;
	}

	public void connectPlayer(Player player) {
		connectedPlayer = player;		
	}

}
