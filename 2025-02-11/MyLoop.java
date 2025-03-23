public class MyLoop {
	public static void main (String[] args) {
		double x = 1.59;
		System.out.println("before");
		while (x < 7.95) { // 5 * 1.59
			System.out.println("inside");
			System.out.println("the value of x = " + x);
			x = x + 0.8;
				}
	System.out.println("after");
	}
}
