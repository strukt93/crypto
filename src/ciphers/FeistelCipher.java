package ciphers;

import java.security.SecureRandom;
import java.util.Stack;

public class FeistelCipher {
	String plainText;
	String cipherText;
	String key;
	Stack<String> keys;

	public FeistelCipher(String plainText) {
		this.plainText = plainText;
		cipherText = "";
		key = generateKey();
		keys = new Stack<String>();
	}

	public String generateSubKey() {
		String subKey = xor(key, generateKey());
		keys.push(subKey);
		return subKey;
	}

	public String generateKey() {
		SecureRandom sr = new SecureRandom();
		String s = "";
		while (s.length() < 64) {
			s += sr.nextInt(2);
		}
		return s;
	}

	public String xor(String x, String y) {
		String z = "";
		for (int i = 0; i < x.length(); i++) {
			char c1 = x.charAt(i);
			char c2 = y.charAt(i);
			z += (c1 == c2) ? "0" : "1";
		}
		return z;
	}

	public String toBinary(String s) {
		byte[] bytes = s.getBytes();
		String out = "";
		for (byte b : bytes) {
			String bString = Integer.toBinaryString(b);
			if (bString.length() < 8)
				bString = "0" + bString;
			out += bString;
		}
		return out;
	}

	public String toBinary(byte[] bytes) {
		String out = "";
		for (byte b : bytes) {
			String bString = Integer.toBinaryString(b);
			if (bString.length() < 8)
				bString = "0" + bString;
			out += bString;
		}
		return out;
	}

	public String f(String rightHalf, String subKey) {
		return xor(rightHalf, subKey);
	}

	public void encrypt(String plainText) {
		String leftHalf = plainText.substring(0, 31);
		String rightHalf = plainText.substring(31, 64);
		for (int i = 0; i < 16; i++) {
			
		}
	}

	public static void main(String[] args) {
		FeistelCipher fc = new FeistelCipher("");
	}
}
