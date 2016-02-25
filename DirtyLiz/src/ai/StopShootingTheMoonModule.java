package ai;
import java.util.List;

import commmon.Card;
import commmon.MaxFourInt;
import gamelogic.Player;

public class StopShootingTheMoonModule {
	
	public boolean stopShootingTheMoon(List<Card> hand, Player[] players, MaxFourInt leadPlayer){

		if(players[leadPlayer.getValue()].getHandSize() > 6) {
			return false;
		}
		if(otherPlayerScores(players, leadPlayer)){
				//if(totalRating(hand)>=15 || hand.size()<5){
					return true;
				//}
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
	
	public boolean otherPlayerScores(Player[] players, MaxFourInt leadPlayer){
		int numberOfZeros = 0;
		Player possibleShooterPlayer = null;
		for (Player player : players) {
			 if(player.getScoreForCurrentHand() == 0) {
				 numberOfZeros++;
			 } else {
				 possibleShooterPlayer = player;
			 }
		}
		if(numberOfZeros == 3 && possibleShooterPlayer!=players[leadPlayer.getValue()]) {
			if(possibleShooterPlayer.getScoreForCurrentHand() > 20) {
				return true;
			}
		}
		return false;
	}		
}
