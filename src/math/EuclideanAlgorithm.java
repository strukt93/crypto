package math;
public class EuclideanAlgorithm {
	int gcd;
	int x;
	int y;

	public EuclideanAlgorithm(int x, int y) {
		if (x > y) {
			gcd = calc(x, y);
		} else {
			gcd = calc(y, x);
		}
	}

	public int calc(int x, int y) {
		this.x = x;
		this.y = y;
		int remainder = x % y;
		while (remainder != 0) {
			x = y;
			y = remainder;
			remainder = x % y;
		}
		return y;
	}

	public String toString() {
		return "The GCD of " + x + " and " + y + " is " + gcd;
	}

	public static void main(String[] args) {
		EuclideanAlgorithm ea = new EuclideanAlgorithm(21, 7);
		System.out.println(ea.toString());
	}
}
