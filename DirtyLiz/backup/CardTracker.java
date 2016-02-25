import java.util.ArrayList;

public class CardTracker {

	private ArrayList<Card> trackedCards = new ArrayList<Card>();
	private static CardTracker THE_CARD_TRACKER;
	
	private CardTracker() {
		THE_CARD_TRACKER = this;
	}
	
	public static CardTracker getTracker() {
		if(THE_CARD_TRACKER == null) {
			return new CardTracker();
		} else {
			return THE_CARD_TRACKER;
		}
	}
	
	public void addToCards(Card cardPlayed){
		trackedCards.add(cardPlayed);		
	}
	
	public ArrayList<Card> getPlayedCards(){
		return trackedCards;		
	}
	
	public void newGame(){
		trackedCards.clear();
	}
	
	public void trackScores(){
		
	}
	
}
