package ai;

public abstract class SmartArtificialIntelligence implements ArtificialIntelligence {

	/** abstract class implenting interface specifically for smart AIs to gain access to tracker **/

	protected CardTracker cardTracker;

	public SmartArtificialIntelligence(CardTracker cardTracker) {
		this.cardTracker = cardTracker;
	}
}
