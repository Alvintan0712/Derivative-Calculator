import java.math.BigInteger;

public class ExprTree {
    private Node root;
    private Node head = null;
    private int len;
    private int idx = 0;
    private int cnt = 0;
    private String str;

    public ExprTree(String str) {
        len = str.length();
        this.str = str;
        if (str.equals("")) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        root = e(false);
        if (root == null) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        head = derivative(root);
    }

    public Node getHead() {
        return head;
    }

    public Node derivative(Node node) {
        if (node != null) {
            return node.derivative();
        }
        return null;
    }

    public Node getRoot() {
        return root;
    }

    public void show(Node node) {
        if (node != null) {
            if (node instanceof Operator) {
                System.out.print("(");
            }
            show(node.getLeft());
            System.out.print(node.toString());
            show(node.getRight());
            if (node instanceof Operator) {
                System.out.print(")");
            }
        }
    }

    private Node e(boolean bracket) {
        // 表达式 → 空白项 [加减 空白项] 项 空白项
        runSpace(); // 空白项
        if (idx >= len) {
            return null;
        }
        Node node;
        boolean flag = bracket;
        char c = getChar();
        if (c == '-') { // 加减
            node = new Operator('*');
            node.setLeft(new Constant("-1"));
            idx++;
            runSpace(); // 空白项
            if (idx >= len) {
                System.out.println("WRONG FORMAT!");
                System.exit(0);
            }
            node.setRight(t()); // 项
            if (node.getRight() == null) {
                System.out.println("WRONG FORMAT!");
                System.exit(0);
            }
        } else {
            idx += c == '+' ? 1 : 0; // 加减
            runSpace(); // 空白项
            if (idx >= len) {
                System.out.println("WRONG FORMAT!");
                System.exit(0);
            }
            node = t(); // 项
            if (node == null) {
                System.out.println("WRONG FORMAT!");
                System.exit(0);
            }
        } // node 可以是符号或项
        runSpace(); // 空白项
        return eprime(node, flag); // 表达式
    }

    private Node eprime(Node node, boolean bracket) { // 表达式
        // 表达式 → 表达式 加减 空白项 项 空白项
        boolean flag = bracket;
        if (idx >= len) {
            return node;
        }
        char c = getChar();
        if (c == ')') { // 表达式
            cnt--;
            if (cnt < 0) {
                System.out.println("WRONG FORMAT!");
                System.exit(0);
            }
            return node;
        }
        Operator op = null;
        if (c == '+' || c == '-') { // 加减
            op = new Operator(c);
            op.setLeft(node);
            op.setFlag(flag);
            flag = false;
            idx++;
        } else {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        runSpace(); // 空白项
        op.setRight(t()); // 项
        if (op.getRight() == null) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        runSpace(); // 空白项
        return eprime(op, flag); // 表达式
    }

    private Node t() {
        // 项 → [加减 空白项] 因子
        if (idx >= len) {
            return null;
        }
        char c = getChar();
        Node node = null;
        if (c == '+' || c == '-') { // 加减
            if (c == '-') {
                node = new Operator('*');
                node.setLeft(new Constant("-1"));
            }
            idx++;
        }
        runSpace(); // 空白项
        if (idx >= len) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        if (node == null) {
            node = f(); // 因子
        } else {
            node.setRight(f()); // 因子
        }
        return tprime(node);
    }

    private Node tprime(Node node) { // 项
        // 项 → 项 空白项 * 空白项 因子
        int temp = idx;
        runSpace(); // 空白项
        if (idx >= len) {
            idx = temp;
            return node;
        }
        char c = getChar();
        Operator op = null;
        if (c == '*') { // *
            op = new Operator(c);
            op.setLeft(node);
            idx++;
        } else {
            idx = temp;
            return node;
        }
        runSpace(); // 空白项
        op.setRight(f()); // 因子
        return tprime(op);
    }

    private Node f() {
        // 因子 → 变量因子 | 常数因子 | 表达式因子
        char c = getChar();
        if (Character.isDigit(c) || c == '-' || c == '+') {
            return new Constant(getNum());
        } else if (c == 'x') {
            idx++;
            return var();
        } else if (c == 's') {
            idx++;
            return sin();
        } else if (c == 'c') {
            idx++;
            return cos();
        } else if (c == '(') {
            idx++;
            cnt++;
            Node node = e(true);
            c = getChar();
            if (c == ')') {
                idx++;
                return node;
            } else {
                System.out.println("WRONG FORMAT!");
                System.exit(0);
            }
        } else {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        return null;
    }

    private Node sin() {
        char c = getChar();
        if (c != 'i') {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        idx++;
        c = getChar();
        if (c != 'n') {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        idx++;
        runSpace();
        c = getChar();
        if (c != '(') {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        idx++;
        runSpace();
        if (idx >= len) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        Node node = f();
        runSpace();
        c = getChar();
        Node f = node;
        if (c != ')') {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        idx++;
        return new Tri(f, BigInteger.ONE, getPow(), false);
    }

    private Node cos() {
        char c = getChar();
        if (c != 'o') {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        idx++;
        c = getChar();
        if (c != 's') {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        idx++;
        runSpace();
        c = getChar();
        if (c != '(') {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        idx++;
        runSpace();
        Node node = f();
        runSpace();
        c = getChar();
        Node f = node;
        if (c != ')') {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        idx++;
        return new Tri(f, BigInteger.ONE, getPow(), true);
    }

    private Node var() {
        return new Variable(BigInteger.ONE, getPow());
    }

    private BigInteger getPow() {
        runSpace();
        if (idx >= len) {
            return BigInteger.ONE;
        }
        int temp = idx;
        char c = getChar();
        if (c != '*') {
            return BigInteger.ONE;
        }
        idx++;
        c = getChar();
        if (c != '*') {
            idx = temp;
            return BigInteger.ONE;
        }
        idx++;
        runSpace();
        BigInteger n = getNum();
        if (n.compareTo(BigInteger.valueOf(50)) > 0) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        } else if (n.compareTo(BigInteger.valueOf(-50)) < 0) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        return n;
    }

    private BigInteger getNum() {
        char c = getChar();
        StringBuffer sb = new StringBuffer();
        if (c == '-' || c == '+') {
            sb.append(c);
            idx++;
        }
        for (c = getChar(); Character.isDigit(c); c = getChar()) {
            sb.append(c);
            idx++;
            if (idx >= len) {
                return new BigInteger(sb.toString());
            }
        }
        String s = sb.toString();
        if (s.equals("+") || s.equals("-") || s.equals("")) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        return new BigInteger(sb.toString());
    }

    private void runSpace() {
        if (idx >= len) {
            return;
        }
        char c = str.charAt(idx);
        while (idx < len && (c == ' ' || c == '\t')) {
            idx++;
            if (idx >= len) {
                return;
            }
            c = str.charAt(idx);
        }
    }

    private char getChar() {
        if (idx >= len) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        return str.charAt(idx);
    }
}
