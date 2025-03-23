public class LocalVariablesExample {
	public static void meth1(String message1) {
		System.out.println("Calling meth1...");
		
		String message2 = "This is a second message.";
		System.out.println(message1);
		System.out.println(message2);
	}
	
	public static void meth2(String num) {
		num = "two";
	}
		
	
	public static void main (String[] args) {
		System.out.println("Calling main...");
		
		meth1("This is my first message.");
		// String message3 = message1;
		// String message4 = message2;
		
		String num = "one";
		meth2(num);
		System.out.println("num = " + num);
		
		if(true) {
			String num2;
			num2 = "three";
			System.out.println("num2 = " + num2);
		}
		System.out.println("num = " + num);
	}
	
	
}