import secrets
import data.Binary as Binary

class FeistelCipher:
    def __init__(self, plaintext):
        self.plaintext = plaintext
        self.cipher_text_blocks = []
        self.key = self.generate_key()
        self.subkey = self.key
        self.plaintext_blocks = []
        self.generate_plaintext_blocks()


    def generate_key(self):
        key = ""
        for i in range(0, 4):
            rand = str(secrets.randbelow(1000))
            key += Binary.to_binary(rand)
        return key[0: 32]

    def generate_subkey(self, direction):
        if direction:
            self.subkey = Binary.circular_shift_left(self.subkey)
        else:
            self.subkey = Binary.circular_shift_right(self.subkey)
        return self.subkey

    def generate_plaintext_blocks(self):
        word = ""
        for char in self.plaintext:
            if len(word) == 4:
                self.plaintext_blocks.append(Binary.to_binary(word))
                word = ""
            word += char
        self.plaintext_blocks.append(Binary.pad_zeros_left(Binary.to_binary(word), 32))

    def f(self, right_hand, subkey):
        return Binary.xor(right_hand, subkey)

    def perform_round(self, left_hand, right_hand, direction):
        new_right_hand = Binary.xor(left_hand, self.f(right_hand, self.generate_subkey(direction)))
        left_hand = right_hand
        right_hand = new_right_hand
        return left_hand, right_hand

    def encrypt(self):
        ciphertext = ""
        for plaintext_block in self.plaintext_blocks:
            left_hand = plaintext_block[0: 16]
            right_hand = plaintext_block[16:]
            for i in range(0, 16):
                new_block = self.perform_round(left_hand, right_hand, True)
                left_hand = new_block[0]
                right_hand = new_block[1]
            self.cipher_text_blocks.append(right_hand + left_hand)
            ciphertext += Binary.to_bytes(right_hand + left_hand)
        self.generate_subkey(True)
        print("Encrypted Text: " + ciphertext)

    def decrypt(self):
        plaintext = ""
        while self.cipher_text_blocks:
            ciphertext_block = self.cipher_text_blocks.pop()
            left_hand = ciphertext_block[0: 16]
            right_hand = ciphertext_block[16:]
            for i in range(0, 16):
                new_block = self.perform_round(left_hand, right_hand, False)
                left_hand = new_block[0]
                right_hand = new_block[1]
            plaintext = Binary.to_bytes(right_hand + left_hand) + plaintext
        print("Decrypted Text: " + plaintext)


f = FeistelCipher("Test1")
f.encrypt()
f.decrypt()
