def pad_zeros_left(bit_str, target_length):
    """
    Pads a string of bits with zeros until
    the desired string length, zeros are padded
    to the left of the string.
    :param bit_str: The initial string of bits
    :param target_length: The target length of the bit string
    :return: The result of appending zeros to the bit string
    """
    while len(bit_str) < target_length:
        bit_str = "0" + bit_str
    return bit_str


def pad_zeros_right(bit_str, target_length):
    """
    Pads a string of bits with zeros until
    the desired string length, zeros are padded
    to the right of the string.
    :param bit_str: The initial string of bits
    :param target_length: The target length of the bit string
    :return: The result of appending zeros to the bit string
    """
    while len(bit_str) < target_length:
        bit_str += "0"
    return bit_str


def circular_shift_left(bit_str, num_rounds=1):
    """
    Does circular shift lefts on the supplied
    bit string "num_rounds" times.
    :param bit_str: The initial string of bits
    :param num_rounds: The number of circular shift rounds
    :return: The result of performing the circular shifts
    """
    while num_rounds > 0:
        bit_str = bit_str[1:] + bit_str[0]
        num_rounds -= 1
    return bit_str


def circular_shift_right(bit_str, num_rounds=1):
    """
    Does circular shift rights on the supplied
    bit string "num_rounds" times.
    :param bit_str: The initial string of bits
    :param num_rounds: The number of circular shift rounds
    :return: The result of performing the circular shifts
    """
    while num_rounds > 0:
        bit_str = bit_str[-1] + bit_str[:-1]
        num_rounds -= 1
    return bit_str


def xor(bit_str_x, bit_str_y):
    """
    Performs a logical XOR operation on the supplied
    bit strings.
    :param bit_str_x: The first bit string
    :param bit_str_y: The second bit string
    :return: The result of XORing the two bit strings
    """
    result = ""
    for x_char, y_char in zip(bit_str_x, bit_str_y):
        result += "0" if x_char == y_char else "1"
    return result


def to_bytes(bit_str):
    """
    Converts the supplied bit string to an equivalent
    string of bytes. Assumes string lengths that are
    multiples of 8.
    :param bit_str: The string of bits
    :return: An equivalent string of bytes
    """
    if len(bit_str) % 8 != 0:
        return None
    result = ""
    out_len = int(len(bit_str) / 8)
    for i in range(0, out_len):
        result += chr(int(bit_str[i * 8: (i + 1) * 8], 2))
    return result


def to_binary(byte_str):
    """
    Turns a byte string into a bit string by converting
    every byte to its equivalent binary representation.
    :param byte_str: The string of bytes
    :return: An equivalent string of bits
    """
    result = ""
    for byte in byte_str:
        result += bin(int.from_bytes(byte.encode(), 'big'))[2:].zfill(8)
    return result


def int_to_binary(int):
    """
    Converts an integer to its 8-bit equivalent
    in binary format.
    :param int: The integer to convert
    :return: The binary equivalent
    """
    return bin(int)[2:].zfill(8)
