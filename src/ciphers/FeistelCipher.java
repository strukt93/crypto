package ciphers;

import java.security.SecureRandom;
import java.util.ArrayList;

import data.Binary;

public class FeistelCipher {
	String plainText;
	String cipherText;
	String key;
	String subKey;
	ArrayList<String> blocks;

	public FeistelCipher(String plainText) {
		this.plainText = plainText;
		cipherText = "";
		key = generateKey();
		subKey = key;
		blocks = new ArrayList<String>();
		generateBlocks();
	}

	public void generateSubKey() {
		subKey = Binary.circularShiftLeft(subKey);
	}

	public void generateBlocks() {
		String bytes = "" + plainText.charAt(0);
		for (int i = 1; i < plainText.length(); i++) {
			if (i % 4 == 0) {
				String binary = Binary.toBinary(bytes);
				blocks.add(binary);
				bytes = "";
			}
			bytes += plainText.charAt(i);
		}
		blocks.add(Binary.padWithZeros(Binary.toBinary(bytes), 32, false));
	}

	public String generateKey() {
		SecureRandom sr = new SecureRandom();
		String s = "";
		while (s.length() < 32) {
			s += sr.nextInt(2);
		}
		return s;
	}

	public static void main(String[] args) {
		FeistelCipher fc = new FeistelCipher("Hello my name IS ASD 123123 .,?< !@#$%#$^%^*");
	}
}
