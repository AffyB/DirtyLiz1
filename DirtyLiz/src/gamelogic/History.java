package gamelogic;

import java.io.*;


public class History {
	
	private StringBuilder historyBuilder;
	private Player[] players;
	private static History history;
	private boolean needsHeader;
	private String nameOfFile;
	
	private History(){
		historyBuilder = new StringBuilder();
		needsHeader = true;
	}
	
	//creates singleton object for History to avoid overwriting content
	public static History getHistory() {
		if(history != null) {
			return history;
		} else {
			return new History();
		}
	}
	
	//adds scores to the history biulder before writing 
	public void addToHistory(Player[] players){
		this.players = players;
		if(needsHeader) {
			addHeaders();
			needsHeader = false;
		}
		historyBuilder.append(players[0].getScore());
		historyBuilder.append(",");
		historyBuilder.append(players[1].getScore());
		historyBuilder.append(",");
		historyBuilder.append(players[2].getScore());
		historyBuilder.append(",");
		historyBuilder.append(players[3].getScore());
		historyBuilder.append("\n");
	}
	
	//if called first time, headers are added for readability 
	private void addHeaders() {
		historyBuilder.append("Player1,Player2,Player3,Player4\n");
		historyBuilder.append(players[0].getAI().toString() + ",");
		historyBuilder.append(players[1].getAI().toString() + ",");
		historyBuilder.append(players[2].getAI().toString() + ",");
		historyBuilder.append(players[3].getAI().toString());
		historyBuilder.append("\n");
	}
	
	//if called first time, footers are added to sum up statistics for the player 
	private void addFooters() {
		historyBuilder.append("\n");
		historyBuilder.append("WINS\n");
		historyBuilder.append(players[0].getWins());
		historyBuilder.append(",");
		historyBuilder.append(players[1].getWins());
		historyBuilder.append(",");
		historyBuilder.append(players[2].getWins());
		historyBuilder.append(",");
		historyBuilder.append(players[3].getWins());
		historyBuilder.append("\n");
		historyBuilder.append("LOSSES\n");
		historyBuilder.append(players[0].getLosses());
		historyBuilder.append(",");
		historyBuilder.append(players[1].getLosses());
		historyBuilder.append(",");
		historyBuilder.append(players[2].getLosses());
		historyBuilder.append(",");
		historyBuilder.append(players[3].getLosses());
		historyBuilder.append("\n");
		historyBuilder.append("SHOOT THE MOONS\n");
		historyBuilder.append(players[0].getShootTheMoon());
		historyBuilder.append(",");
		historyBuilder.append(players[1].getShootTheMoon());
		historyBuilder.append(",");
		historyBuilder.append(players[2].getShootTheMoon());
		historyBuilder.append(",");
		historyBuilder.append(players[3].getShootTheMoon());
	}
	
	//if name of file is empty (retrieved from GUI), names the file the default name
	public void nameTheFile(String fileName){
		if(fileName.equals("")){
			nameOfFile = "DirtyLiz Results Spreadsheet.csv";
		}else{
			nameOfFile = fileName+".csv";
		}
		System.out.println(nameOfFile);
		
	}
	
	//writes all content to a csv file
	public void writeToFile(){
		addFooters();
		
		FileWriter fileWriter = null; 
		try{
			fileWriter = new FileWriter(nameOfFile);
			fileWriter.append(historyBuilder);
		}catch(Exception e){
			System.out.println("Error");
			e.printStackTrace();
		}finally{
			try{
				fileWriter.flush();
				fileWriter.close();
			}catch (IOException e){
				System.out.println("Error closing");
				e.printStackTrace();
			}
		}
	}
	
}
