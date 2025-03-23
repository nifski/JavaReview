public class ReturnExample {
	public static void main (String[] args) {
		Math m = new Math();
		int n1 = 8;
		int n2 = 8 + 1;
		int r1 = m.addReturn(n1, n2);
		System.out.println(r1);
		m.addPrint(n1, n2);
	}
}

class Math{
	public int addReturn(int num1, int num2) {
		int result = num1 + num2;
		return result;
	}
	
	public void addPrint(int num1, int num2) {
		int result = num1 + num2;
		System.out.println(result);
	}
}