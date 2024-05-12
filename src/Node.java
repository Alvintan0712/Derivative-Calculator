public class Node {
    private Node left;
    private Node right;
    private boolean flag;

    public Node() {
        left = null;
        right = null;
        flag = false;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }

    public String show(Node node) {
        String str = "";
        if (node != null) {
            if (node instanceof Operator) {
                str += "(";
            }
            str += show(node.getLeft());
            str += node.toString(); // System.out.print(node.toString());
            str += show(node.getRight());
            if (node instanceof Operator) {
                str += ")";
            }
        }
        return str;
    }

    public Node derivative() {
        return null;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node node) {
        left = node;
    }

    public void setRight(Node node) {
        right = node;
    }
}
