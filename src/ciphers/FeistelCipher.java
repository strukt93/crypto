package ciphers;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Stack;

public class FeistelCipher {
	String plainText;
	String cipherText;
	String key;
	Stack<String> keys;
	ArrayList<String> blocks;

	public FeistelCipher(String plainText) {
		this.plainText = plainText;
		cipherText = "";
		// key = generateKey();
		keys = new Stack<String>();
		blocks = new ArrayList<String>();
		generateBlocks();
	}

	public String generateSubKey() {
		String subKey = xor(key, generateKey());
		keys.push(subKey);
		return subKey;
	}

	public String padWithZeros(String binary) {
		while (binary.length() < 32)
			binary += "0";
		return binary;
	}

	public void generateBlocks() {
		String bytes = "" + plainText.charAt(0);
		for (int i = 1; i < plainText.length(); i++) {
			if (i % 4 == 0) {
				String binary = toBinary(bytes);
				blocks.add(binary);
				// blocks.add((binary.length() < 32) ? "0" + binary : binary);
				bytes = "";
			}
			bytes += plainText.charAt(i);
		}
		blocks.add(padWithZeros(toBinary(bytes)));
		String out = "";
		for (int j = 0; j < blocks.size(); j++) {
			String x = blocks.get(j);
			System.out.println(x);
			out += toBytes(x);
		}
		System.out.println(out);
	}

	public String generateKey() {
		SecureRandom sr = new SecureRandom();
		String s = "";
		while (s.length() < 32) {
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

	public String toBytes(String binary) {
		String ret = "";
		for (int i = 0; i < 4; i++) {
			int val = Integer.parseInt(binary.substring(8 * i, (i + 1) * 8), 2);
			ret += (char) val;
		}
		return ret;
	}

	public String toBinary(String s) {
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

	public static void main(String[] args) {
		FeistelCipher fc = new FeistelCipher("Hello my name IS ASD 123123 .,?< !@#$%#$^%^*");
	}
}
