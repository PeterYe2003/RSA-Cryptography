public class Sieve {
    // Peter's RSA Code
    // Peter's PrimeProject. This was used for Dixons but this might not be important anymore.
    boolean numbers[];
    public int countPrimes(int limit) {
        numbers = new boolean[limit + 1];
        int primes = limit - 1;
        int holder = 0;
        for (int x = 2; x <= Math.pow(limit, 0.5); x++) {
            if (numbers[x] == false) {
                // only need to check above x^2 because all factors of x less than x^x are factors of other prime numbers too.
                holder = x * x;
                while (holder <= limit) {
                    if (numbers[holder] == false) {
                        // number of primes go down by one each time I switch from false to true.
                        primes -= 1;
                        numbers[holder] = true;
                    }
                    holder += x;
                }
            }
        }
        return primes;
    }

    public int givePrimes() {
        int output =1;
        for (int x = output+1; x < numbers.length; x++) {
            if (numbers[x] == false) {
                output = x;
                break;
            }
        }
        return output;
    }
}