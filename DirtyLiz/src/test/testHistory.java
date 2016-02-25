package test;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import ai.PlayHighAI;
import ai.PlayLowAI;
import ai.RandomAI;
import gamelogic.History;
import gamelogic.Player;

public class testHistory {

	private Player[] players = new Player[4]; 
	private History history = History.getHistory();   
	
	@Before
	public void setUp() {
		players[0] = new Player(new RandomAI());
		players[1] = new Player(new PlayHighAI());
		players[2] = new Player(new PlayLowAI());
		players[3] = new Player(new RandomAI());
		players[0].addToScore(10);
		players[1].addToScore(20);
		players[2].addToScore(30);
		players[3].addToScore(40);
	}
	
	@Test
	public void test() {
		history.addToHistory(players);
		history.writeToFile();
		players[0].addToScore(79);
		players[1].addToScore(84);
		players[2].addToScore(90);
		players[3].addToScore(100);
		history.addToHistory(players);
		history.writeToFile();

	}

}
