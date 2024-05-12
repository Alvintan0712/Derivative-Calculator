public class Operator extends Node {
    private char op;

    public Operator(char op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return Character.toString(op);
    }

    public char getOp() {
        return op;
    }

    public Node derivative() {
        if (this.op == '*') {
            Node left = new Operator('*');
            left.setLeft(this.getLeft().derivative());
            left.setRight(this.getRight());

            Node right = new Operator('*');
            right.setLeft(this.getLeft());
            right.setRight(this.getRight().derivative());

            Operator op = new Operator('+');
            op.setFlag(true);
            op.setLeft(left);
            op.setRight(right);
            return op;
        } else {
            Operator op = new Operator(this.getOp());
            op.setLeft(this.getLeft().derivative());
            op.setRight(this.getRight().derivative());
            op.setFlag(this.getFlag());
            return op;
        }
    }
}
