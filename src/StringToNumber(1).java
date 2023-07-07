import java.math.BigInteger;
import java.util.Random;
import java.util.BitSet;
// Peter's RSA Code
// Converts a String of Digits to a String or a string of numbers to a string of texts.
public class StringToNumber extends PrivateKeySemiPrimes {

    public String Encrypt(String input, BigInteger n, BigInteger e) {
        PrivateKeySemiPrimes holder = new PrivateKeySemiPrimes();
        String output = "";
        byte[] bytes = input.getBytes();
        BitSet bitsets = BitSet.valueOf(bytes);
        long[] longs = bitsets.toLongArray();
        BigInteger[] bigs = new BigInteger[longs.length];
        for (int x = 0; x < longs.length; x++) {
            bigs[x] = BigInteger.valueOf(longs[x]);
            bigs[x] = holder.EncryptToM(bigs[x], n, e);
            output = output + bigs[x];
            if(x<longs.length-1){
                output = output + ",";
            }
        }
        return output;
    }

    public String Decrypt(String input, BigInteger n, BigInteger d) {
        PrivateKeySemiPrimes holder = new PrivateKeySemiPrimes();
        String[] bignumbers = input.split(",");
        long[] longints = new long[bignumbers.length];
        for (int y = 0; y <  bignumbers.length; y++) {
            BigInteger bigs = new BigInteger(bignumbers[y]);
            longints[y] = (holder.DecryptToS(bigs, n, d)).longValueExact();
        }
        BitSet bitsets = BitSet.valueOf(longints);
        byte[] bytes = bitsets.toByteArray();
        String output = new String(bytes);
        return output;
    }
}