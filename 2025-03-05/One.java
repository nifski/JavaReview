public class One {
	public static void main(String[] args) {
		int x = 100;
		One y = new One();
		Two z = new Two();
		int d1 = y.doIt1(x);
		int d2 = z.doIt2(x);
		int d3 = Three.doIt3(x);
		System.out.println(x + " " + d1 + " " + d2 + " " + d3);
	}
	
	public int doIt1(int num) {
		int num2 = num * 2;
		return num2;
	}	
}

public class Two {
	public Two() {}
	public int doIt2(int num) {
		int num2 = num * 2;
		return num2;
	}
}

public class Three {
	public static int doIt3(int num) {
		int num2 = num * 2;
		return num2;
	}
}