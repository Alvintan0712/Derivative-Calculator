import java.math.BigInteger;

public class Variable extends Node {
    private BigInteger expo;
    private BigInteger coe;

    public Variable(BigInteger coe, BigInteger expo) {
        this.coe = coe;
        this.expo = expo;
    }

    public Node derivative() {
        BigInteger coe = this.coe.multiply(this.expo);
        BigInteger expo = this.expo.subtract(BigInteger.ONE);
        return new Variable(coe, expo);
    }

    @Override
    public String toString() {
        if (coe.equals(BigInteger.ZERO)) {
            return "0";
        } else if (expo.equals(BigInteger.ZERO)) {
            return coe.toString();
        } else if (expo.equals(BigInteger.ONE) && coe.equals(BigInteger.ONE)) {
            return "x";
        } else if (expo.equals(BigInteger.ONE)) {
            return coe.toString() + "*x";
        } else if (coe.equals(BigInteger.ONE)) {
            return "x**" + expo.toString();
        } else {
            return coe.toString() + "*x**" + expo.toString();
        }
    }
}
