package ai;

import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class Smart_ShootTheMoonOnHeart extends SmartArtificialIntelligence {

	/** Shoot the Moon module added to Hearts AI **/
	
	Player connectedPlayer;
	private PlaySafeModuleRefactored safeModule;
	private SpadesModule queenModule;
	private HeartsModule heartsModule;
	private LeadModule leadModule;
	private PlayLastModule playSmartHigh;
	private ShootingMoonModule shootTheMoon; 
	
	public Smart_ShootTheMoonOnHeart(CardTracker cardTracker) {
		super(cardTracker);
		safeModule = new PlaySafeModuleRefactored();
		queenModule = new SpadesModule();
		heartsModule = new HeartsModule();
		leadModule = new LeadModule();
		playSmartHigh = new PlayLastModule();
		shootTheMoon = new ShootingMoonModule();
		
	}

	public Card getMove(Card[] playedCards, List<Card> hand, Player[] players, MaxFourInt leadPlayer, MaxFourInt currentPlayer) {
		Card returnCard = null;
		leadModule.addTracker(cardTracker);
		queenModule.addTracker(cardTracker);
		heartsModule.addTracker(cardTracker);
		playSmartHigh.addTracker(cardTracker);

		
		returnCard = shootTheMoon.getMove(playedCards, hand, leadPlayer, players, currentPlayer);
		
		if(returnCard == null){
			returnCard = queenModule.getMove(playedCards, hand, leadPlayer);
		}
		
		if(returnCard == null){
			returnCard = leadModule.getMove(playedCards, hand, leadPlayer);
		}
		
		if(returnCard == null){
			returnCard = heartsModule.getMove(playedCards, hand, leadPlayer);
		}
		
		if(returnCard == null){
			returnCard = playSmartHigh.getMove(playedCards, hand, leadPlayer);
		}
		
		if(returnCard == null){
			returnCard = safeModule.getMove(playedCards, hand, leadPlayer); 
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
		String ai = "Smart_ShootTheMoonFeatureAI";
		return ai;	
	}
	
	public boolean isSmart() {
		return true;
	}

	public void connectPlayer(Player player) {
		connectedPlayer = player;		
	}

}
