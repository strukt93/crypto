package math;

public class EuclideanAlgorithm {
	int gcd;
	int x;
	int y;

	public EuclideanAlgorithm(int x, int y, boolean recursive) {
		if (x > y) {
			helper(x, y, recursive);
		} else {
			helper(y, x, recursive);
		}
	}

	public void helper(int x, int y, boolean recursive) {
		gcd = (recursive) ? calculateRecursively(x, y) : calculate(x, y);
	}

	public int calculate(int x, int y) {
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

	public int calculateRecursively(int x, int y) {
		if (y == 0)
			return x;
		else
			return calculateRecursively(y, x % y);
	}

	public String toString() {
		return "The GCD of " + x + " and " + y + " is " + gcd;
	}
}
