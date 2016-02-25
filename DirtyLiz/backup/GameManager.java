import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random; 

public class GameManager {

	private Card[] playedCards;
	private Player[] players;
	ArrayList<ArrayList<Card>> hands;
	private Deck deck;
	private int gameAmount;
	private MaxFourInt firstPlayer;
	private MaxFourInt leadPlayer;
	public static final int END = 100;
	public static final int ALL_POINTS = 36;
	private History history; 
	private CardTracker trackCards;
	private String nameOfFile;

	public GameManager(int gameAmount, String AI1, String AI2, String AI3, String AI4, String nameOfFile) {
		this.gameAmount = gameAmount;
		this.nameOfFile = nameOfFile;
		history = History.getHistory(); 
		trackCards = CardTracker.getTracker();
		players = new Player[4];
		
		players[0] = new Player(findAI(AI1)); 
		players[1] = new Player(findAI(AI2));
		players[2] = new Player(findAI(AI3));
		players[3] = new Player(findAI(AI4));

	}
	
	private ArtificialIntelligence findAI(String s){
		String[] comboBoxArray = {"Play High AI", "Play Low AI", "Play Random AI", "Play Smart AI 1", "Play Smart AI 2"};
		
		if(s.equals(comboBoxArray[0])){
			return new PlayHighAI();
		}
		else if(s.equals(comboBoxArray[1])){
			return new PlayLowAI();
		}
		else if(s.equals(comboBoxArray[2])){
			return new RandomAI();
		}
		else if(s.equals(comboBoxArray[3])){
			return new SmartAI1(trackCards);
		}
		return new SmartAI2(trackCards);
	}
	
	public void start(){
		for(int i=1; i <= gameAmount; i++){
			runGame();
		}
		history.nameTheFile(nameOfFile);
		history.writeToFile();
	}

	
	private void setUp() {
		deck = Deck.getDeck();
		playedCards = new Card[4];
		dealCards();
	}
	
	private void dealCards() {
		deck.shuffle();
		hands = deck.deal();
		players[0].giveCards(hands.get(0));
		players[1].giveCards(hands.get(1));
		players[2].giveCards(hands.get(2));
		players[3].giveCards(hands.get(3));
	}

	public void runGame() {
		Random rand = new Random();
		int randomNumber = rand.nextInt(4);
		firstPlayer = new MaxFourInt(randomNumber);
		setUp();
			
		while(shouldStillPlay()){
			playRound();
			dealCards();
			firstPlayer = firstPlayer.next();
		}
		history.addToHistory(players);
		findWinnerAndLoser();
		
		for(int i=0; i<players.length; i++) {
			players[i].resetScore();
		}
	}
	
	private void findWinnerAndLoser() {
		//TODO: CHECK FOR DRAWS
		//KLUDGE: don't initialise positions as 0, could cause bugs (but shouldn't).
		int losingPosition = 0;
		int winningPosition = 0;
		int losingPoints = 99;
		int winningPoints = 120;
		
		for(int i=0; i<players.length; i++) {
			int score = players[i].getScore();
			if(score<winningPoints) {
				winningPosition = i;
				winningPoints = score;
			}
			if(score>losingPoints) {
				losingPosition = i;
				losingPoints = score;
			}
		}
		
		players[winningPosition].win();
		players[losingPosition].lose();
	}
	
	private void playRound() {
		leadPlayer = firstPlayer;
		while(players[firstPlayer.getValue()].shouldPlay()) {
			playOnce();
		}

		checkShootingTheMoon();
		addPoints();
		trackCards.newGame();
	}

	private void checkShootingTheMoon() {
		int playerPosition = -1;
		boolean isThereAShooter = false;
		for (int i = 0; i < players.length; i++) {
			if (players[i].getScoreForCurrentHand() == ALL_POINTS) {
				playerPosition = i;
				isThereAShooter = true;
			}
		}
		if (isThereAShooter) {
			for (int i = 0; i < players.length; i++) {
				if (i == playerPosition) {
					players[i].shootMoon(true);
				} else {
					players[i].shootMoon(false);
				}
			}
		}
	}
	
	private void addPoints() {
		for (int i = 0; i < players.length; i++) {
			players[i].endHand();
		}
	}

	private void playOnce() {
		MaxFourInt currentPlayer = leadPlayer;
		playedCards[currentPlayer.getValue()] = players[currentPlayer.getValue()].promptMove(playedCards, players);
		trackCards.addToCards(playedCards[0]);
		currentPlayer = currentPlayer.next();
		
		playedCards[currentPlayer.getValue()] = players[currentPlayer.getValue()].promptMove(playedCards, players);
		trackCards.addToCards(playedCards[1]);
		currentPlayer = currentPlayer.next();
		
		
		playedCards[currentPlayer.getValue()] = players[currentPlayer.getValue()].promptMove(playedCards, players);
		trackCards.addToCards(playedCards[2]);
		currentPlayer = currentPlayer.next();
		
		playedCards[currentPlayer.getValue()] = players[currentPlayer.getValue()].promptMove(playedCards, players);
		trackCards.addToCards(playedCards[3]); 

		Card losingCard = playedCards[0];
		int losingPlayer = leadPlayer.getValue();
		
		
		//KLUDGE
		MaxFourInt playerPointer = new MaxFourInt(losingPlayer);
		
		for(int i=1; i < playedCards.length; i++){
			playerPointer = playerPointer.next();
				if(playedCards[i].getSuit() == losingCard.getSuit()){
					if(playedCards[i].getPoints() > losingCard.getPoints()){
						losingCard = playedCards[i];
						losingPlayer = playerPointer.getValue();
					}
				}
				
			}
		
		int totalScore = calculateScores(playedCards);
		
		leadPlayer = new MaxFourInt(losingPlayer);
		players[losingPlayer].addToScore(totalScore);
		
	}

	private int calculateScores(Card[] playedCards) {
			
		int totalScore = playedCards[0].getPoints();
		totalScore += playedCards[1].getPoints();
		totalScore += playedCards[2].getPoints();
		totalScore += playedCards[3].getPoints();
		
		return totalScore;
	}
	
	private boolean shouldStillPlay() {
		
		int player1Score = players[0].getScore();
		int player2Score = players[1].getScore();
		int player3Score = players[2].getScore();
		int player4Score = players[3].getScore();
		
		if(player1Score >= END || player2Score >= END || player3Score >= END || player4Score >= END){
			return false;
		}
		return true;
	}
	
}
