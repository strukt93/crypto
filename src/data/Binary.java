package data;

public class Binary {
	/*
	 * Returns a string with the supplied targetLength as it's length value with
	 * paddings of zeros towards the left or right Supply direction = true for
	 * padding towards the left
	 */
	public static String padWithZeros(String binary, int targetLength, boolean direction) {
		if (direction)
			return padWithZerosLeft(binary, targetLength);
		return padWithZerosRight(binary, targetLength);
	}

	/*
	 * Helper function for when the value of the direction parameter is set to
	 * true.
	 */
	public static String padWithZerosLeft(String binary, int targetLength) {
		while (binary.length() < targetLength) {
			binary = "0" + binary;
		}
		return binary;
	}
	/*
	 * Helper function for when the value of the direction parameter is set to
	 * false.
	 */

	public static String padWithZerosRight(String binary, int targetLength) {
		while (binary.length() < targetLength) {
			binary += "0";
		}
		return binary;
	}

	/*
	 * Returns the supplied string of a binary represented value after
	 * performing a circular shift lift.
	 */
	public static String circularShiftLeft(String s) {
		return s.substring(1, s.length()) + s.charAt(0);
	}

	/*
	 * Returns the supplied string of a binary represented value after
	 * performing a circular shift right.
	 */
	public static String circularShiftRight(String s) {
		return s.charAt(s.length() - 1) + s.substring(0, s.length() - 1);
	}

	/*
	 * Returns the obtained value after XOR-ing the two supplied binary
	 * representations.
	 */
	public static String xor(String x, String y) {
		String z = "";
		for (int i = 0; i < x.length(); i++) {
			char c1 = x.charAt(i);
			char c2 = y.charAt(i);
			z += (c1 == c2) ? "0" : "1";
		}
		return z;
	}

	/*
	 * Converts a binary representation to it's corresponding string of bytes.
	 * Assumes 32-bit blocks with 8-bit words.
	 */
	public static String toBytes(String s) {
		String ret = "";
		for (int i = 0; i < 4; i++) {
			int val = Integer.parseInt(s.substring(8 * i, (i + 1) * 8), 2);
			ret += (char) val;
		}
		return ret;
	}

	/*
	 * Converts a string of bytes to it's corresponding binary representation.
	 */
	public static String toBinary(String s) {
		byte[] bytes = s.getBytes();
		String out = "";
		for (byte b : bytes) {
			String bString = Integer.toBinaryString(b);
			while (bString.length() < 8)
				bString = "0" + bString;
			out += bString;
		}
		return out;
	}

	public static String intToBinary(int n) {
		return Integer.toString(n, 2);
	}

	/*
	 * Converts a hexadecimal string to it's binary string representation.
	 */
	public static String binaryToHex(String hex) {
		return Integer.toString(Integer.parseInt(hex, 2), 16);
	}
}
