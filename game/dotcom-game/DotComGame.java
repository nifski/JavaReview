public class DotComGame{
	public static void main(String[] args){
		int numOfGuesses = 0;
		GameHelper helper  = new GameHelper ();
	
		DotCom theDotCom = new DotCom();
		int randomNum = (int) (Math.random() * 5);
		int[] locations = {randomNum, randomNum+1, randomNum+2};//how do i make it the three consecutive cells
		int [3] locations2; 
		for ( i = 0; i < location2.length; i++) {
			locations2[i] = randomNum + i;
			
		}
		
		theDotCom.setLocationCells	(locations); // this is a link to the setlocation method that takes the parameters locs, meanin location
		boolean isAlive = true;
		
		while(isAlive == true) {
			String guess = helper.getUserInput("enter a number");
			String result = theDotCom.checkYourself(guess);
			numOfGuesses++;
			if (result.equals("kill")) {
				isAlive = false;
				System.out.println("You took" + numOfGuesses + "guesses");
			}
		}
	}
	
}



