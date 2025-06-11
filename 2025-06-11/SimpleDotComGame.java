public class SimpleDotComGame{
	public static void main (String[] args){
		
	}
	
}

public class SimpleDotCom{
	int[]locationCells
	int numOfHits
		
	String checkYourself(String guess){}
		
	void setLocation (int[] loc){}
	
}
}

public class SimpleDotComTestDrive{
	public static void main(String[] args) {
		SimpleDotCom dot = new SimpleDotCom();
		
		int[] locations = {2,3,4};
		dot.setLocationCells(locations);
		
		String userGuess = "2";
		String result = dot.checkYourself(userGuess);
		String testResult = "failed";
		if (result.equals("hit")) {
			testResult = "passed";
		}
		
		System.out.println(testResult);
			
	}
}