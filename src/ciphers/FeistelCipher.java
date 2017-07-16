package ciphers;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Stack;

import data.Binary;

/*This is a sample implementation of the FeistelCipher.
 * The implementation assumes a block size of 32-bits.*/
public class FeistelCipher {
	String plainText;
	Stack<String> cipherTextBlocks;
	String key;
	String subKey;
	ArrayList<String> plainTextBlocks;

	public FeistelCipher(String plainText) {
		this.plainText = plainText;
		cipherTextBlocks = new Stack<String>();
		key = generateKey();
		subKey = key;
		plainTextBlocks = new ArrayList<String>();
		generateBlocks();
	}

	private String generateKey() {
		SecureRandom sr = new SecureRandom();
		String s = "";
		while (s.length() < 32) {
			s += sr.nextInt(2);
		}
		return s;
	}

	private String generateSubKey(boolean direction) {
		if (direction)
			subKey = Binary.circularShiftLeft(subKey);
		else
			subKey = Binary.circularShiftRight(subKey);
		return subKey;
	}

	private void generateBlocks() {
		String bytes = "" + plainText.charAt(0);
		for (int i = 1; i < plainText.length(); i++) {
			if (i % 4 == 0) {
				String binary = Binary.toBinary(bytes);
				plainTextBlocks.add(binary);
				bytes = "";
			}
			bytes += plainText.charAt(i);
		}
		plainTextBlocks.add(Binary.padWithZeros(Binary.toBinary(bytes), 32, false));
	}

	private String f(String rh, String subKey) {
		return Binary.xor(rh, subKey);
	}

	private String performRound(String lh, String rh, boolean encrypt) {
		String newrh = Binary.xor(f(rh, generateSubKey(encrypt)), lh);
		lh = rh;
		rh = newrh;
		return lh + "," + rh;
	}

	public void encrypt() {
		String cipherText = "";
		for (int i = 0; i < plainTextBlocks.size(); i++) {
			String block = plainTextBlocks.get(i);
			String lh = block.substring(0, 16);
			String rh = block.substring(16, 32);
			for (int j = 0; j < 16; j++) {
				String[] newBlock = performRound(lh, rh, true).split(",");
				lh = newBlock[0];
				rh = newBlock[1];
			}
			String temp = rh + lh;
			cipherTextBlocks.push(temp);
			cipherText += Binary.toBytes(temp);
		}
		generateSubKey(true);
		System.out.println("Encrypted Text: " + cipherText);
	}

	public void decrypt() {
		String originalText = "";
		while (!cipherTextBlocks.isEmpty()) {
			String block = cipherTextBlocks.pop();
			String lh = block.substring(0, 16);
			String rh = block.substring(16, 32);
			for (int i = 0; i < 16; i++) {
				String[] newBlock = performRound(lh, rh, false).split(",");
				lh = newBlock[0];
				rh = newBlock[1];
			}
			originalText = Binary.toBytes(rh + lh) + originalText;
		}
		System.out.println("Decrypted Text: " + originalText);
		System.out.println("Encryption made using a 32-bit block sized Feistel Cipher");
	}

	public static void main(String[] args) {
		FeistelCipher fc = new FeistelCipher("I love cookie <3");
		fc.encrypt();
		fc.decrypt();
	}
}
