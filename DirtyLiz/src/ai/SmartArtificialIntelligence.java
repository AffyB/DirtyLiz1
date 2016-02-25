package ai;

public abstract class SmartArtificialIntelligence 
	implements ArtificialIntelligence {

	protected CardTracker cardTracker;

	public SmartArtificialIntelligence(CardTracker cardTracker) {
		this.cardTracker = cardTracker;
	}
}
