import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();

        ExprTree expr = new ExprTree(str);
        // expr.show(expr.getRoot());
        // System.out.println("");
        expr.show(expr.getHead());
    }
}
