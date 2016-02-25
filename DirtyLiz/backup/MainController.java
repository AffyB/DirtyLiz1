
public class MainController {
	
	private static MainController controller = null; 
	
	private MainController(){
		controller = this;
	}
	
	public static MainController getController(){
		if (controller == null){
			return new MainController();
		}
		return controller;
	}
	
	public void start(){
	}
	
	public String[] fillComboBox(){
		String[] comboBoxArray = {"Play High AI", "Play Low AI", "Play Random AI"};
		return comboBoxArray;	
	}

}
