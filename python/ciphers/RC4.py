import secrets
import data.Binary as Binary


class RC4:
    def __init__(self, plaintext):
        self.plaintext = plaintext
        self.ciphertext = ""
        self.s = []
        self.s_dec = []
        self.k = []
        self.t = []
        self.initialize()
        self.initial_permutation()

    def initialize(self):
        self.s = [i for i in range(0, 256)]
        self.s_dec = [i for i in range(0, 256)]
        self.k = self.generate_k()
        self.t = self.k

    def initial_permutation(self):
        j = 0
        for i in range(0, 256):
            j = (j + self.s[i] + self.t[i]) % 256
            self.swap(i, j, True)
            self.swap(i, j, False)

    def encrypt(self):
        i = 0
        j = 0
        for char in self.plaintext:
            k = self.generate_encryption_stream(i, j)
            char_binary = Binary.to_binary(char)
            c = Binary.xor(char_binary, k)
            self.ciphertext += Binary.to_bytes(c)
            i += 1
            j += 1
        print("Encrypted Text: " + self.ciphertext)

    def decrypt(self):
        plaintext = ""
        i = 0
        j = 0
        for char in self.ciphertext:
            k = self.generate_decryption_stream(i, j)
            char_binary = Binary.to_binary(char)
            p = Binary.xor(char_binary, k)
            plaintext += Binary.to_bytes(p)
            i += 1
            j += 1
        print("Decrypted Text: " + plaintext)

    def generate_encryption_stream(self, i, j):
        i = (i + 1) % 256
        j = (j + self.s[i]) % 256
        self.swap(i, j, True)
        t = (self.s[i] + self.s[j]) % 256
        k = self.s[t]
        return Binary.to_binary(str(k))

    def generate_decryption_stream(self, i, j):
        i = (i + 1) % 256
        j = (j + self.s_dec[i]) % 256
        self.swap(i, j, False)
        t = (self.s_dec[i] + self.s_dec[j]) % 256
        k = self.s_dec[t]
        return Binary.to_binary(str(k))

    def generate_k(self):
        k = []
        while len(k) < 256:
            k.append(secrets.randbelow(1000))
        return k[0: 256]

    def swap(self, index_i, index_j, encrypt):
        if encrypt:
            self.s[index_i], self.s[index_j] = self.s[index_j], self.s[index_i]
        else:
            self.s_dec[index_i], self.s_dec[index_j] = self.s_dec[index_j], self.s_dec[index_i]


rc4 = RC4("Hello World")
rc4.encrypt()
rc4.decrypt()
