import java.math.BigInteger;
import java.util.Random;

// Peter's RSA Code
public class PrivateKeySemiPrimes {
    static BigInteger e;
    static BigInteger d;
    static BigInteger phi;
    static BigInteger n;

    PrivateKeySemiPrimes() {
        boolean relative = false;
        boolean isLarge = false;
        BigInteger p = BigInteger.ONE;
        BigInteger q = BigInteger.ONE;
        n = BigInteger.valueOf(10);
        Random rnd = new Random();
        while (isLarge == false) {
            p = BigInteger.probablePrime(30, rnd);
            q = BigInteger.probablePrime(30, rnd);
            n = p.multiply(q);
            if (n.bitLength() < 70 && n.bitLength() > 50) {
                isLarge = true;
            }
        }
        phi = n.subtract(p).subtract(q).add(BigInteger.ONE);
        e = phi.add(BigInteger.ONE);
        while (relative == false) {
            e = new BigInteger(30, rnd);
            if (e.gcd(phi).equals(BigInteger.ONE)) {
                relative = true;
            }
        }
        d = e.modInverse(phi);

    }

    public static BigInteger N() {
        return n;
    }

    public static BigInteger Phi() {
        return phi;
    }

    public static BigInteger E() {
        return e;
    }

    public static BigInteger D() {
        return d;
    }

    public static BigInteger EncryptToM(BigInteger s, BigInteger n, BigInteger e) {
        BigInteger m = s.modPow(e, n);
        return m;
    }

    public static BigInteger DecryptToS(BigInteger m, BigInteger n, BigInteger d) {
        BigInteger s = BigInteger.ONE;
        s = m.modPow(d, n);
        return s;
    }
}
