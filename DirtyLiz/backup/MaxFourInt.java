
public class MaxFourInt {
	
	private int value;
	
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
	
	public MaxFourInt next(){		
		return new MaxFourInt(value+1);		
	}
	
	public int getValue(){
		return value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
