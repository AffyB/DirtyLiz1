package commmon;

// very own numbering system as there are only 4 players 
// counts up to 3 (0,1,2,3) to account for all 4 players and iterates round
public class MaxFourInt {
	
	private int value;
	
	//denotes what player is playing
	public MaxFourInt(int value){	
		if(value < 0){
			this.value = 0;
		}
		else if(value > 3){
			this.value = 0;
		}
		else{
			this.value = value;
		}
	}
	
	//calls for next player to play in a round
	public MaxFourInt next(){		
		return new MaxFourInt(value+1);		
	}
	
	//since this my own class, i needed to specify how to get the int value
	public int getValue(){
		return value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
