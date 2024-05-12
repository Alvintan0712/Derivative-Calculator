import java.math.BigInteger;

public class Constant extends Node {
    private BigInteger coe;
    private BigInteger expo;

    public Constant(BigInteger coe) {
        this.coe = coe;
        this.expo = BigInteger.ZERO;
    }

    public Constant(String str) {
        coe = new BigInteger(str);
        expo = BigInteger.ZERO;
    }

    @Override
    public String toString() {
        return coe.toString();
    }

    public Node derivative() {
        return new Constant(BigInteger.ZERO);
    }
}
