import java.util.ArrayList;

public class DotCom{
	private ArrayList<String> locationCells;
	
	public void setLocationCells(ArrayList<String> loc) {
		locationCells = locs;
	}
		
	public String checkYourself(String userInput) {
		String result = "miss";
		
		int index = locationCells.indexOf(userInput)
		if (index >= 0) {
			locaionCells.remove(index);
			if (locationCells.isEmpty()){
				result = "kill";
			} else {
				result = "hit";
			}
		}
			return result;
	}
}