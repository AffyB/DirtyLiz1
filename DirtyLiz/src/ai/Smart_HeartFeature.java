package ai;

import java.util.ArrayList;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class Smart_HeartFeature extends SmartArtificialIntelligence {

	Player connectedPlayer;
	private PlaySafeModuleRefactored safeModule;
	private SpadesModule spadeModule;
	private HeartsModule heartsModule;
	private LeadModule leadModule;
	private PlayLastModule playLastModule;
	
	public Smart_HeartFeature(CardTracker cardTracker) {
		super(cardTracker);
		safeModule = new PlaySafeModuleRefactored();
		spadeModule = new SpadesModule();
		heartsModule = new HeartsModule();
		leadModule = new LeadModule();
		playLastModule = new PlayLastModule();
		
	}

	//calls many modules to return best card
	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer, MaxFourInt currentPlayer) {
		Card returnCard = null;
		leadModule.addTracker(cardTracker);
		spadeModule.addTracker(cardTracker);
		heartsModule.addTracker(cardTracker);
		playLastModule.addTracker(cardTracker);
		
		returnCard = spadeModule.getMove(playedCards, hand, leadPlayer);
		
		if(returnCard == null){
			returnCard = leadModule.getMove(playedCards, hand, leadPlayer);
		}
		
		if(returnCard == null){
			returnCard = heartsModule.getMove(playedCards, hand, leadPlayer);
		}
		
		if(returnCard == null){
			returnCard = playLastModule.getMove(playedCards, hand, leadPlayer);
		}
		
		if(returnCard == null){
			returnCard = safeModule.getMove(playedCards, hand, leadPlayer); 
		}

		if(returnCard == null){
			returnCard = spadeModule.checkForQueenAndReturn(hand);
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
