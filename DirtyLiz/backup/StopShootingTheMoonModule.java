import java.util.List;

public class StopShootingTheMoonModule {
	
	public boolean stopShootingTheMoon(List<Card> hand, Player[] players, Player connectPlayer){
		if(otherPlayerScores(players, connectPlayer)){
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
	
	public boolean otherPlayerScores(Player[] players, Player connectPlayer){
		Player myPlayer = connectPlayer;
		
		for(int i=0; i<players.length; i++){
			if(!players[i].equals(myPlayer)){
				if(players[i].getScoreForCurrentHand()>=20){
					return true;
				}
			}
		}
		return false;
	}		
}
