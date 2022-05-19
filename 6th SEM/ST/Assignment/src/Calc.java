import java.util.Scanner;
import java.util.Stack;

public class Calc {
    static Scanner sc = new Scanner(System.in);
    static int topA=0;
    static int topB=-1;
    static char[] A= new char[100];
    static Node[] B= new Node[100];
    static boolean solve=true;

    public static void main(String[] args) {
        String str = sc.nextLine();
        str = SolveEquation(str);
        System.out.println(str);
    }

    static String SolveEquation(String str) {
        boolean Invalid = CheckEquation(str);
        if (Invalid)
            return "Invalid Input";
        ConvertInfixToPost(str);
        double res = FindAns();
        String Ans = "Math Error!!";
        if (solve)
            Ans = String.format("%.3f", res);
        return Ans;
    }

    static double FindAns() {
        Stack<Double> st = new Stack<Double>();
        for (int i = 0; i <= topB; i++) {
            if (CheckOperator(B[i].op)) {
                try {
                    double y = st.pop(), x = st.pop();
                    if (B[i].op == '+') {
                        st.push(x + y);
                    } else if (B[i].op == '-')
                        st.push(x - y);
                    else if (B[i].op == 'x')
                        st.push(x * y);
                    else if (B[i].op == '÷')
                        st.push(x / y);
                } catch (Exception e) {
                    solve = false;
                    return -1;
                }
            } else {
                try {
                    st.push(B[i].number);
                } catch (Exception e) {
                    solve = false;
                    return -1;
                }
            }
        }
        double res=st.pop();
        return res;
    }

    static void ConvertInfixToPost(String str) {
        str = "(" + str + ")";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (CheckDigit(c)) {
                String x="";
                while(i<str.length() && (CheckDigit(str.charAt(i)) || str.charAt(i)=='.')) {
                    x=x+str.charAt(i);
                    i++;
                }
                pushB('@',Double.parseDouble(x));
                i--;
            } else if (c == '(')
                pushA(c);
            else if (c == ')')
                checkbrakets();
            else
                InsertOperator(c);
        }
    }

    static char popA() {
        if (topA < 0)
            return '@';
        topA--;
        return A[topA + 1];
    }

    static void pushA(char c) {
        topA++;
        A[topA] = c;
    }

    static void pushB(char c,double x) {
        topB++;
        B[topB]=new Node(c,x);
    }

    static void checkbrakets() {
        while (A[topA] != '(') {
            char x = popA();
            if (x == '@')
                break;
            pushB(x,-1);
        }
        popA();
    }

    static void InsertOperator(char c) {
        while (topA >= 0 && priority(A[topA]) >= priority(c)) {
            char x = popA();
            pushB(x,-1);
        }
        pushA(c);
    }

    static int priority(char c) {
        if(c=='(')
            return 0;
        if (c == '+' || c == '-')
            return 1;
        return 2;
    }

    static boolean CheckEquation(String str) {
        boolean dot = false, op = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '.' && dot) {
                return true;
            }
            if (CheckOperator(c)) {
                op = true;
                dot = false;
            } else if (CheckDigit(c)) {
                op = false;
            } else if (c == '.') {
                dot = true;
            } else {
                return true;
            }
        }
        return (op && dot) || op;
    }

    static boolean CheckOperator(char c) {
        return (c == '+' || c == '-' || c == 'x' || c == '÷');
    }

    static boolean CheckDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    static class Node {
        char op;
        double number;
        public Node(char op, double number) {
            this.op = op;
            this.number = number;
        }
    }

}
