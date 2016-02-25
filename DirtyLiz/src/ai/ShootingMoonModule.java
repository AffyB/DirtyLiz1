package ai;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class ShootingMoonModule {
	
	public boolean shootingTheMoon(List<Card> hand, Player[] players, MaxFourInt leadPlayer){
		if(isAllOtherPlayerScoresZero(players, leadPlayer)){
			if(players[leadPlayer.getValue()].getScoreForCurrentHand()>20){
				//if(totalRating(hand)>=15 || hand.size()<5){
				return true;
				//}
			}
		}
		return false;
	}
	
	public int totalRating(List<Card> hand){
		int ratingOfHand = 0;
		for(int i=0; i<hand.size(); i++){
			ratingOfHand = ratingOfHand + hand.get(i).getValue();
		}
		return ratingOfHand;
	}
	
	public boolean isAllOtherPlayerScoresZero(Player[] players, MaxFourInt leadPlayer){
		for(int i=0; i<players.length; i++){
			if(!players[i].equals(leadPlayer.getValue())){
				if(players[i].getScoreForCurrentHand()>0){
					return false;
				}
			}
		}
		return true;
	}
}