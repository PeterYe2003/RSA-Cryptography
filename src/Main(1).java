import com.sun.source.tree.NewArrayTree;
// Peter's RSA Code
// I acknowledge using Stack Overflow and Java library.
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        PrivateKeySemiPrimes n = new PrivateKeySemiPrimes();
        StringToNumber RSA = new StringToNumber();
        BigInteger givenN = new BigInteger("796223553986269");
        BigInteger givenE = new BigInteger("604231177981243");
        String EncryptedMessage = "564747184990346";
        String DecryptedMessage = "Hello my friend";
        System.out.println(RSA.Decrypt(EncryptedMessage, givenN, Cracker(givenN, givenE)));
        System.out.println(RSA.Encrypt(DecryptedMessage,givenN,givenE));

    }
    public static BigInteger Cracker(BigInteger N, BigInteger E) {
        // Function that gives the private key when given N and E (public keys).
        double start = System.currentTimeMillis();
        BigInteger p = BigInteger.ZERO;
        BigInteger q = BigInteger.ZERO;
        BigInteger phi = BigInteger.ZERO;
        BigInteger x = N.sqrt().add(BigInteger.ONE);
        // Setting y slightly below the sqrt of (x^2 - N). This was an optimization because it saves me the runtime of running through tiny Ns that I know won't work.
        BigInteger y = (x.pow(2).subtract(N)).sqrt();
        while (x.pow(2).subtract(y.pow(2)).compareTo(N) != 0) {
            x = x.add(BigInteger.ONE);
            y = (x.pow(2).subtract(N)).sqrt();
        }
        p = x.add(y);
        q = x.subtract(y);
        phi = N.subtract(p).subtract(q).add(BigInteger.ONE);
        double end = System.currentTimeMillis();
        // returns run time in ms.
        System.out.println(end - start);
        return E.modInverse(phi);
    }
// Not complete. I was working on it and I'm so far only at the part where I kind of make the factorbase.
    public static void Dixon(int bits, BigInteger N, BigInteger E) {
        Sieve sieveuse = new Sieve();
        int factorbase = (int) (30 * Math.exp(0.08 * bits));
        BigInteger factorboard[][] = new BigInteger[sieveuse.countPrimes(factorbase) + 2][sieveuse.countPrimes(factorbase) + 2];
        // Creates a factorbase from 2 to # primes in factorbase - 3.
        for (int x = 2; x < sieveuse.countPrimes(factorbase) + 2; x++) {
            factorboard[x][0] = BigInteger.valueOf(sieveuse.givePrimes());
            System.out.println(factorboard[x][0]);
        }
        // Starting test case. Slighly over the sqrt of N.
        BigInteger testcase = N.sqrt().add(BigInteger.ONE);
        // y is the number of squares that we want to achieve.
        for (int y = 1; y < factorbase + 1; y++) {
            boolean counter = true;
            // the number that we are dividing
            BigInteger modded = testcase.pow(2).mod(N);
            while (counter == true) {
                for (int z = 2; z < factorboard.length; z++) {
                    while (modded.mod(factorboard[z][0]).equals(BigInteger.ZERO) == true) {
                        modded = modded.divide(factorboard[z][0]);
                        factorboard[z][y] = factorboard[z][y].add(BigInteger.ONE);
                    }
                }
                if (modded.equals(1) == true) {
                    counter = false;
                    factorboard[0][y] = testcase;
                    factorboard[1][y] = modded;
                }
            }
        }
        

    }
// Fully factors a BigInteger by a BigInteger. This was only used for Dixon but Dixon isn't complete so it's not really important.
    public static int fullFactor(BigInteger factorThis, BigInteger factor){
        int power = 0;
        while(factorThis.mod(factor).equals(BigInteger.ZERO) == true){
            power +=1;
        }
        return power;
    }

}

