package ciphers;

import data.Binary;

public class RC4 {
	String plainText;
	int[] s;
	int[] t;
	int[] k;
	String cipherText;

	public RC4(String plainText) {
		this.plainText = plainText;
		s = new int[256];
		t = new int[256];
		k = Binary.generateIntKey(256);
		cipherText = "";
		initialize();
		initialPermutation();
		encrypt();
		decrypt();
	}

	public void initialize() {
		for (int i = 0; i < 256; i++) {
			s[i] = i;
			t[i] = k[i];
		}
	}

	public void initialPermutation() {
		int j = 0;
		for (int i = 0; i < 256; i++) {
			j = (j + s[i] + t[i]) % 256;
			swap(i, j);
		}
	}

	public String generateStream() {
		int i = 0, j = 0, t = 0;
		i = (i + 1) % 256;
		j = (j + s[i]) % 256;
		swap(i, j);
		t = (s[i] + s[j]) % 256;
		return Binary.toBinary(s[t] + "");
	}

	public void encrypt() {
		for (int i = 0; i < plainText.length(); i++) {
			String k = generateStream();
			String currentChar = Binary.toBinary(plainText.charAt(i) + "");
			String c = Binary.xor(currentChar, k);
			cipherText += Binary.toBytes(c);
		}
		System.out.println(cipherText);
	}

	public void decrypt() {
		String plain = "";
		for (int i = 0; i < cipherText.length(); i++) {
			String k = generateStream();
			String currentChar = Binary.toBinary(cipherText.charAt(i) + "");
			String p = Binary.xor(currentChar, k);
			plain += Binary.toBytes(p);
		}
		System.out.println(plain);
	}

	public void swap(int i, int j) {
		int temp = s[i];
		s[i] = s[j];
		s[j] = temp;
	}

	public static void main(String[] args) {
		RC4 rc = new RC4("test");
	}
}
