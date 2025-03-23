public class LoopExample {
	public static void main (String[] args) {
		int o = 1; // this is me declaring o as an integer and assigning number 1 to it
		System.out.println("before the loop"); 
		// I understand that this is me printing this is before the loop because nothing is looping yet
		while (o < 4) { // This is the start of the loop
			System.out.println("In the loop");
			System.out.println("Value of o is " + o);
			o = o + 1; 
			// I believe that the first iteration of the loop will be 1, therefore one plus 1 will be 2, so it will then print "in the loop" then the "Value of o is 2", then it goes on again 
			// prints "In the loop" then, Valuee of o is 3 because o is currently 2 and 2+1=3
			// then it goes again but realizes 3+1=4 and that is not what is declared as the parameter in loop. 
		}
		System.out.println("This is after the loop");
		// so it then prints "This is after the loop because that is after the loop"
	}
}