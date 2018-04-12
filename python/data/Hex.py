def pad_zeros_left(hex_str, target_length):
    """
    Pads a string of bits with zeros until
    the desired string length, zeros are padded
    to the left of the string.
    :param hex_str: The initial string of bits
    :param target_length: The target length of the bit string
    :return: The result of appending zeros to the bit string
    """
    while len(hex_str) < target_length:
        hex_str = "0" + hex_str
    return hex_str


def pad_zeros_right(hex_str, target_length):
    """
    Pads a string of bits with zeros until
    the desired string length, zeros are padded
    to the right of the string.
    :param hex_str: The initial string of bits
    :param target_length: The target length of the bit string
    :return: The result of appending zeros to the bit string
    """
    while len(hex_str) < target_length:
        hex_str += "0"
    return hex_str


def to_hex(byte_str):
    """
    Converts a string of UTF-8 bytes to
    its equivalent string of hex bytes.
    :param byte_str: A byte string
    :return: A hex representation of the supplied string
    """
    return byte_str.encode().hex()


def to_bytes(hex_str):
    """
    Converts a string of hex bytes to
    its equivalent UTF-8 string representation.
    :param hex_str: A hex string
    :return: A UTF-8 representation of the supplied string
    """
    result = ""
    i = 0
    while i < len(hex_str):
        result += chr(int(hex_str[i: i + 2], 16))
        i += 2
    return result


def hex_to_binary(hex_str):
    """
    Converts a hex string to its equivalent
    binary string.
    :param hex_str: A hex string
    :return: The equivalent binary string
    """
    result = ""
    i = 0
    while i < len(hex_str):
        result += bin(int(hex_str[i: i + 2], 16))[2:].zfill(8)
        i += 2
    return result


def binary_to_hex(bit_str):
    """
    Converts a binary string to its equivalent
    hex string.
    :param bit_str: A binary string
    :return: A hex string equivalent to the binary string
    """
    if len(bit_str) % 8 != 0:
        return None
    result = ""
    out_len = int(len(bit_str) / 8)
    for i in range(0, out_len):
        result += hex(int(bit_str[i * 8: (i + 1) * 8], 2))[2:]
    return result


def int_to_hex(int):
    """
    Converts an integer to its equivalent
    in hex format.
    :param int: The integer to convert
    :return: The hex equivalent
    """
    return hex(int)[2:]

