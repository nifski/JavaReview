class Player {
	public int number;
	
	public void guess() {
		number = (int) (Math.random() * 10);
	}
}

class GuessGame {
	public Player p1;
	public Player p2;
	public Player p3;
	
	public void startGame() {
		// create three Player objects and
		p1 = new Player();
		p2 = new Player();
		p3 = new Player();
		
		int guessp1 = 0;
		int guessp2 = 0;
		int guessp3 = 0;
		
		boolean p1isRight = false;
		boolean p2isRight = false;
		boolean p3isRight = false;
		
		int targetNumber = (int) (Math.random() * 10);
		System.out.println("I'm thinking of a number between 0 and 9 ... ");
		
		while(true){
			System.out.println("Number to guess is " + targetNumber);
			
			p1.guess();
			p2.guess();
			p3.guess();
			
			guessp1 = p1.number;
			System.out.println("Player one guessed " + guessp1);
			
			guessp2 = p2.number;
			System.out.println("Player two guessed " + guessp2);
			
			guessp3 = p3.number;
			System.out.println("Player three guessed " + guessp3);
			
			if (guessp1 == targetNumber) {
			    p1isRight = true;
			}
			
			if (guessp2 == targetNumber) {
			    p2isRight = true;
			}
			
			if (guessp3 == targetNumber) {
			    p3isRight = true;
			}
			
			if (p1isRight || p2isRight || p3isRight) {
				System.out.println("We have a winner!"); 
				System.out.println("Player one got it right? " + p1isRight);
				System.out.println("Player two got it right? " + p2isRight);
				System.out.println("Player three got it right?" + p3isRight); 
				System.out.println("Game is over.");
				break; // game over, so break out of the loop
				
			} else {
				System.out.println("Players will have to try again.");
			}							
		}					
	}
	
}

class MyMath {
	public static int timesTwo(int number) {
		return 2 * number;
	}
}

public class GameLauncher {
	public static void main(String[] args) {
		GuessGame gg = new GuessGame();
		gg.startGame();
		
		int result = MyMath.timesTwo(2);
		System.out.println("2 x 2 = " + result);
	}
}