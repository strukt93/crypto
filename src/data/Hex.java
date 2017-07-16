package data;

public class Hex {
	/*
	 * Returns a string with the supplied targetLength as it's length value with
	 * paddings of zeros towards the left or right Supply direction = true for
	 * padding towards the left
	 */
	public static String padWithZeros(String hex, int targetLength, boolean direction) {
		if (direction)
			return padWithZerosLeft(hex, targetLength);
		return padWithZerosRight(hex, targetLength);
	}

	/*
	 * Helper function for when the value of the direction parameter is set to
	 * true.
	 */
	public static String padWithZerosLeft(String hex, int targetLength) {
		while (hex.length() < targetLength) {
			hex = "0" + hex;
		}
		return hex;
	}
	/*
	 * Helper function for when the value of the direction parameter is set to
	 * false.
	 */

	public static String padWithZerosRight(String hex, int targetLength) {
		while (hex.length() < targetLength) {
			hex += "0";
		}
		return hex;
	}

	public static int intToHex(int n) {
		return Integer.parseInt(Integer.toString(n, 16));
	}

	public static String stringToHex(String in) {
		String out = "";
		for (int i = 0; i < in.length(); i++) {
			out += Integer.toHexString((int) in.charAt(i));
		}
		return out;
	}

	public static String hexToBinary(String hex) {
		String s = Integer.toString(Integer.parseInt(hex, 16), 2);
		while (s.length() != 8)
			s = "0" + s;
		return s;
	}

	public static String binaryToHex(String binary) {
		return Long.toString(Long.parseLong(binary, 2), 16);
	}
}
