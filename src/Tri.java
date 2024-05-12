import java.math.BigInteger;

public class Tri extends Node {
    private BigInteger coe;
    private BigInteger expo;
    private boolean iscos;
    private Node factor;

    public Tri(Node factor, BigInteger coe, BigInteger expo, boolean iscos) {
        this.coe = coe;
        this.expo = expo;
        this.iscos = iscos;
        this.factor = factor;
    }

    public Node derivative() {
        Operator op = new Operator('*');

        Node left = factor.derivative();
        op.setLeft(left);

        BigInteger coe = this.coe.multiply(this.expo);
        BigInteger expo = this.expo.subtract(BigInteger.ONE);
        if (iscos) {
            coe = coe.multiply(BigInteger.valueOf(-1));
        }
        Node right;
        if (expo.equals(BigInteger.ZERO)) {
            right = new Tri(factor, coe, BigInteger.ONE, !iscos);
            op.setRight(right);
            return op;
        }
        right = new Operator('*');
        op.setRight(right);
        right.setLeft(new Tri(factor, coe, expo, iscos));
        right.setRight(new Tri(factor, BigInteger.ONE, BigInteger.ONE, !iscos));
        op.setRight(right);
        return op;
    }

    @Override
    public String toString() {
        String tri = iscos ? "cos((" + factor.show(factor) + "))" :
                             "sin((" + factor.show(factor) + "))";
        if (coe.equals(BigInteger.ZERO)) {
            return "0";
        } else if (expo.equals(BigInteger.ZERO)) {
            return coe.toString();
        } else if (expo.equals(BigInteger.ONE) && coe.equals(BigInteger.ONE)) {
            return tri;
        } else if (expo.equals(BigInteger.ONE)) {
            return coe.toString() + "*" + tri;
        } else if (coe.equals(BigInteger.ONE)) {
            return tri + "**" + expo.toString();
        } else {
            return coe.toString() + "*" + tri + "**" + expo.toString();
        }
    }
}
