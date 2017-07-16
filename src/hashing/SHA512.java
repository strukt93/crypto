package hashing;

import java.util.ArrayList;
import java.util.HashMap;

import data.Binary;
import data.Hex;

/*This is my implementation of the SHA512 hash function, which
 * is a part of the SHA2 standard.*/
public class SHA512 {
	final String addCons = "428A2F98D728AE22 7137449123EF65CD B5C0FBCFEC4D3B2F E9B5DBA58189DBBC 3956C25BF348B538 59F111F1B605D019 923F82A4AF194F9B AB1C5ED5DA6D8118 D807AA98A3030242 12835B0145706FBE 243185BE4EE4B28C 550C7DC3D5FFB4E2 72BE5D74F27B896F 80DEB1FE3B1696B1 9BDC06A725C71235 C19BF174CF692694 E49B69C19EF14AD2 EFBE4786384F25E3 0FC19DC68B8CD5B5 240CA1CC77AC9C65 2DE92C6F592B0275 4A7484AA6EA6E483 5CB0A9DCBD41FBD4 76F988DA831153B5 983E5152EE66DFAB A831C66D2DB43210 B00327C898FB213F BF597FC7BEEF0EE4 C6E00BF33DA88FC2 D5A79147930AA725 06CA6351E003826F 142929670A0E6E70 27B70A8546D22FFC 2E1B21385C26C926 4D2C6DFC5AC42AED 53380D139D95B3DF 650A73548BAF63DE 766A0ABB3C77B2A8 81C2C92E47EDAEE6 92722C851482353B A2BFE8A14CF10364 A81A664BBC423001 C24B8B70D0F89791 C76C51A30654BE30 D192E819D6EF5218 D69906245565A910 F40E35855771202A 106AA07032BBD1B8 19A4C116B8D2D0C8 1E376C085141AB53 2748774CDF8EEB99 34B0BCB5E19B48A8 391C0CB3C5C95A63 4ED8AA4AE3418ACB 5B9CCA4F7763E373 682E6FF3D6B2B8A3 748F82EE5DEFB2FC 78A5636F43172F60 84C87814A1F0AB72 8CC702081A6439EC 90BEFFFA23631E28 A4506CEBDE82BDE9 BEF9A3F7B2C67915 C67178F2E372532B CA273ECEEA26619C D186B8C721C0C207 EADA7DD6CDE0EB1E F57D4F7FEE6ED178 06F067AA72176FBA 0A637DC5A2C898A6 113F9804BEF90DAE 1B710B35131C471B 28DB77F523047D84 32CAAB7B40C72493 3C9EBE0A15C9BEBC 431D67C49C100D4C 4CC5D4BECB3E42B6 597F299CFC657E2A 5FCB6FAB3AD6FAEC 6C44198C4A475817";
	String origMessage;
	String digest;
	HashMap<Character, String> abcdefgh;
	ArrayList<String> message;

	public SHA512(String m) {
		this.origMessage = m;
		message = new ArrayList<String>();
		abcdefgh = new HashMap<Character, String>();
		abcdefgh.put('a', "6A09E667F3BCC908");
		abcdefgh.put('b', "BB67AE8584CAA73B");
		abcdefgh.put('c', "3C6EF372FE94F82B");
		abcdefgh.put('d', "A54FF53A5F1D36F1");
		abcdefgh.put('e', "510E527FADE682D1");
		abcdefgh.put('f', "9B05688C2B3E6C1F");
		abcdefgh.put('g', "1F83D9ABFB41BD6B");
		abcdefgh.put('h', "5BE0CD19137E2179");
		parseMessage();
	}

	private void parseMessage() {
		String binRep = Binary.toBinary(origMessage) + "1";
		int decimalLength = getOriginalLengthDec();
		for (int i = 896 - decimalLength - 1; i > 0; i--) {
			binRep += "0";
		}
		String length = Binary.intToBinary(decimalLength);
		while (length.length() != 128) {
			length = "0" + length;
		}
		binRep += length;
		fillArrayList(binRep);
	}

	private void fillArrayList(String binRep) {
		for (int i = 0; i < 16; i++) {
			String s = Hex.binaryToHex(binRep.substring(i * 64, (i + 1) * 64));
			s = Hex.padWithZeros(s, 16, true);
			message.add(s);
		}
	}

	/*
	 * Returns the number of bits in the original message in a hexadecimal
	 * format.
	 */
	private int getOriginalLengthHex() {
		String binary = Binary.toBinary(origMessage);
		return Hex.intToHex(binary.length());
	}

	/*
	 * Returns the number of bits in the original message in a decimal format.
	 */

	private int getOriginalLengthDec() {
		String binary = Binary.toBinary(origMessage);
		return binary.length();
	}

	public static void main(String[] args) {
		SHA512 sha = new SHA512("abc");
	}
}