def calculate_gcd(x, y):
    """
    Calculates and returns the GCD for the supplied parameters
    using the Euclidean Algorithm.
    :param x: First integer
    :param y: Second integer
    :return: The GCD of x and y
    """
    if x < y:
        return calculate_gcd(y, x)
    if y == 0:
        return x
    return calculate_gcd(y, x % y)
