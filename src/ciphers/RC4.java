package ciphers;

import data.Binary;

public class RC4 {
	String plainText;
	int[] s;
	int[] t;
	int[] k;

	public RC4(String plainText) {
		this.plainText = plainText;
		s = new int[256];
		t = new int[256];
		k = Binary.generateIntKey(256);
		initialize();
		initialPermutation();
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

	public void swap(int i, int j) {
		int temp = s[i];
		s[i] = s[j];
		s[j] = temp;
	}

	public static void main(String[] args) {
		RC4 rc = new RC4("");
	}
}
