package gamelogic;

import java.util.ArrayList;
import java.util.Random;

import ai.CardTracker;
import ai.PlayHighAI;
import ai.PlayLowAI;
import ai.Smart_BaseOfAllAI;
import ai.Smart_HeartFeature;
import ai.Smart_ShootTheMoonOnLow;
import ai.Smart_SpadeFeature;
import ai.Smart_ShootTheMoonOnHeart;
import ai.PlayRandomAI;
import commmon.Card;
import commmon.Deck;
import commmon.MaxFourInt;
import ai.ArtificialIntelligence;

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

	private ArtificialIntelligence findAI(String s) {
		String[] comboBoxArray = { "Play High AI", "Play Low AI", "Play Random AI", "Play My Smart AI", "Play Smart Queen AI", "Play Smart Heart AI", "Play Shoot The Moon With Heart AI", "Play Shoot The Moon On Low AI"};

		if (s.equals(comboBoxArray[0])) {
			return new PlayHighAI();
		} else if (s.equals(comboBoxArray[1])) {
			return new PlayLowAI();
		} else if (s.equals(comboBoxArray[2])) {
			return new PlayRandomAI();
		} else if (s.equals(comboBoxArray[3])) {
			return new Smart_BaseOfAllAI(trackCards);
		} else if(s.equals(comboBoxArray[4])) {
			return new Smart_SpadeFeature(trackCards);
		} else if(s.equals(comboBoxArray[5])){
			return new Smart_HeartFeature(trackCards);
		} else if(s.equals(comboBoxArray[6])) {
			return new Smart_ShootTheMoonOnHeart(trackCards); 
		}
		return new Smart_ShootTheMoonOnLow(trackCards);
	}

	public void start() {
		for (int i = 1; i <= gameAmount; i++) {
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

		while (shouldStillPlay()) {
			playRound();
			dealCards();
			firstPlayer = firstPlayer.next();
		}
		history.addToHistory(players);
		findWinnerAndLoser();

		for (int i = 0; i < players.length; i++) {
			players[i].resetScore();
		}
	}

	private void findWinnerAndLoser() {
		// TODO: CHECK FOR DRAWS
		// KLUDGE: don't initialise positions as 0, could cause bugs (but
		// shouldn't).
		int losingPosition = 0;
		int winningPosition = 0;
		int losingPoints = 99;
		int winningPoints = 120;

		for (int i = 0; i < players.length; i++) {
			int score = players[i].getScore();
			if (score < winningPoints) {
				winningPosition = i;
				winningPoints = score;
			}
			if (score > losingPoints) {
				losingPosition = i;
				losingPoints = score;
			}
		}

		players[winningPosition].win();
		players[losingPosition].lose();
	}

	private void playRound() {
		leadPlayer = firstPlayer;
		while (players[firstPlayer.getValue()].shouldPlay()) {
			playOnce();
			playedCards = new Card[4];
		}
		 System.out.println("PLAYER 1 SCORE: " + players[0].getScoreForCurrentHand());
		 System.out.println("PLAYER 2 SCORE: " + players[1].getScoreForCurrentHand());
		 System.out.println("PLAYER 3 SCORE: " + players[2].getScoreForCurrentHand());
		 System.out.println("PLAYER 4 SCORE: " + players[3].getScoreForCurrentHand());
		 System.out.println("END OF ROUND");
		 System.out.println("*************************************************");
		 System.out.println("*************************************************");
		 System.out.println("*************************************************");
		 System.out.println("*************************************************");

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
		playedCards[currentPlayer.getValue()] = players[currentPlayer.getValue()].promptMove(playedCards, players, leadPlayer, currentPlayer);
		trackCards.addToCards(playedCards[currentPlayer.getValue()], true);
		System.out.println("Player " + currentPlayer.getValue() + "played " + playedCards[currentPlayer.getValue()]);
		currentPlayer = currentPlayer.next();

		playedCards[currentPlayer.getValue()] = players[currentPlayer.getValue()].promptMove(playedCards, players, leadPlayer, currentPlayer);
		trackCards.addToCards(playedCards[currentPlayer.getValue()], false);
		System.out.println("Player " + currentPlayer.getValue() + "played " + playedCards[currentPlayer.getValue()]);
		currentPlayer = currentPlayer.next();

		playedCards[currentPlayer.getValue()] = players[currentPlayer.getValue()].promptMove(playedCards, players, leadPlayer, currentPlayer);
		trackCards.addToCards(playedCards[currentPlayer.getValue()], false);
		System.out.println("Player " + currentPlayer.getValue() + "played " + playedCards[currentPlayer.getValue()]);
		currentPlayer = currentPlayer.next();

		playedCards[currentPlayer.getValue()] = players[currentPlayer.getValue()].promptMove(playedCards, players, leadPlayer, currentPlayer);
		trackCards.addToCards(playedCards[currentPlayer.getValue()], false);
		System.out.println("Player " + currentPlayer.getValue() + "played " + playedCards[currentPlayer.getValue()]);

		Card losingCard = playedCards[leadPlayer.getValue()];
		int losingPlayer = leadPlayer.getValue();

		// KLUDGE
		MaxFourInt playerPointer = new MaxFourInt(losingPlayer);

		for (int i = 1; i < playedCards.length; i++) {
			playerPointer = playerPointer.next();
			int pointer = playerPointer.getValue();
			if (playedCards[pointer].getSuit() == losingCard.getSuit()) {
				if (playedCards[pointer].getValue() > losingCard.getValue()) {
					losingCard = playedCards[pointer];
					losingPlayer = playerPointer.getValue();
				}
			}
		}
		 System.out.println("------------------------------------------");
		 System.out.println("The losing player is " + losingPlayer + "and they played " + losingCard);
		 System.out.println("------------------------------------------");
		 System.out.println("------------------------------------------");

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

		if (player1Score >= END || player2Score >= END || player3Score >= END || player4Score >= END) {
			return false;
		}
		return true;
	}

}
