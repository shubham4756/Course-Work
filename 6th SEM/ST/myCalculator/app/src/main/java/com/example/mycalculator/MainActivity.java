package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Stack;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn_dot, btn_div, btn_mul, btn_sub, btn_add, btn_ac, btn_ans;
    ImageButton btn_ce;
    TextView equation;
    boolean preDot = false;
    boolean preOp = true;
    int topA=0;
    int topB=-1;
    char[] A= new char[100];
    Node[] B= new Node[100];
    boolean solve=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn_dot = findViewById(R.id.btn_dot);
        btn_div = findViewById(R.id.btn_div);
        btn_mul = findViewById(R.id.btn_mul);
        btn_sub = findViewById(R.id.btn_sub);
        btn_add = findViewById(R.id.btn_add);
        btn_ac = findViewById(R.id.btn_ac);
        btn_ans = findViewById(R.id.btn_ans);
        btn_ce = findViewById(R.id.btn_ce);
        equation = findViewById(R.id.equ);

        btn_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preDot = false;
                preOp = true;
                topA = 0;
                topB = -1;
                solve = true;
                String str = equation.getText().toString();
                if (str.length() > 0) {
                    str = SolveEquation(str);
                    boolean checkDig = false, checDt = false;
                    for (int i = 0; i < str.length(); i++) {
                        if (CheckDigit(str.charAt(i)))
                            checkDig = true;
                        else if (str.charAt(i) == '.')
                            checDt = true;
                    }
                    if (checkDig)
                        preOp = false;
                    if (checDt)
                        preDot = true;
                    equation.setText(str);
                }
            }
        });

        btn_ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int len = equation.getText().toString().length();
                String str = equation.getText().toString();
                if (len > 0) {
                    if (str.charAt(len - 1) == '.')
                        preDot = false;
                    else if (str.charAt(len - 1) == '+' || str.charAt(len - 1) == '-' || str.charAt(len - 1) == 'x' || str.charAt(len - 1) == '÷')
                        preOp = false;
                    if(len==1) {
                        preOp=true;
                    }
                    equation.setText(str.substring(0, len - 1));
                }
            }
        });

        btn_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.setText("");
                solve=true;
                preDot = false;
                preOp = true;
                topA=0;
                topB=-1;
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!preDot) {
                    if(preOp) {
                        equation.setText(equation.getText().toString().concat("0"));
                    }
                    equation.setText(equation.getText().toString().concat("."));
                    preOp = true;
                }
                preDot = true;
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!preOp) {
                    equation.setText(equation.getText().toString().concat("+"));
                    preDot = false;
                }
                preOp = true;
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!preOp) {
                    equation.setText(equation.getText().toString().concat("-"));
                    preDot = false;
                }
                preOp = true;
                preDot = false;
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!preOp) {
                    equation.setText(equation.getText().toString().concat("x"));
                    preDot = false;
                }
                preOp = true;
                preDot = false;
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!preOp) {
                    equation.setText(equation.getText().toString().concat("÷"));
                    preDot = false;
                }
                preOp = true;
                preDot = false;
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.setText(equation.getText().toString().concat("0"));
                preOp = false;
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.setText(equation.getText().toString().concat("1"));
                preOp = false;
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.setText(equation.getText().toString().concat("2"));
                preOp = false;
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.setText(equation.getText().toString().concat("3"));
                preOp = false;
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.setText(equation.getText().toString().concat("4"));
                preOp = false;
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.setText(equation.getText().toString().concat("5"));
                preOp = false;
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.setText(equation.getText().toString().concat("6"));
                preOp = false;
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.setText(equation.getText().toString().concat("7"));
                preOp = false;
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.setText(equation.getText().toString().concat("8"));
                preOp = false;
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.setText(equation.getText().toString().concat("9"));
                preOp = false;
            }
        });
    }

    String SolveEquation(String str) {
        boolean Invalid = CheckEquation(str);
        if (Invalid)
            return "Invalid Input";
        ConvertInfixToPost(str);
        double res = FindAns();
        String Ans = "Math Error!!";
        if (solve) {
            Ans = String.format("%.3f", res);
        }
        return Ans;
    }

    double FindAns() {
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

    void ConvertInfixToPost(String str) {
        str = "(" + str + ")";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (CheckDigit(c) || c=='.') {
                String x="0";
                while(i<str.length() && (CheckDigit(str.charAt(i)) || str.charAt(i)=='.')) {
                    x=x+str.charAt(i);
                    i++;
                }
                try {
                    pushB('@', Double.parseDouble(x));
                } catch (Exception e) {
                    solve=false;
                }
                i--;
            } else if (c == '(')
                pushA(c);
            else if (c == ')')
                checkbrakets();
            else
                InsertOperator(c);
        }
    }

    char popA() {
        if (topA < 0)
            return '@';
        topA--;
        return A[topA + 1];
    }

    void pushA(char c) {
        topA++;
        A[topA] = c;
    }

    void pushB(char c,double x) {
        topB++;
        B[topB]=new Node(c,x);
    }

    void checkbrakets() {
        while (A[topA] != '(') {
            char x = popA();
            if (x == '@')
                break;
            pushB(x,-1);
        }
        popA();
    }

    void InsertOperator(char c) {
        while (topA >= 0 && priority(A[topA]) >= priority(c)) {
            char x = popA();
            pushB(x,-1);
        }
        pushA(c);
    }

    int priority(char c) {
        if(c=='(')
            return 0;
        if (c == '+' || c == '-')
            return 1;
        return 2;
    }

    boolean CheckEquation(String str) {
        boolean dot = false, op = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            System.out.println(c + " " + dot + " " + op);
            if (c == '.' && dot) {
                return true;
            } else if (CheckOperator(c)) {
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
        return (op && dot) || (op);
    }

    boolean CheckOperator(char c) {
        return (c == '+' || c == '-' || c == 'x' || c == '÷');
    }

    boolean CheckDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    class Node {
        char op;
        double number;
        public Node(char op, double number) {
            this.op = op;
            this.number = number;
        }
    }
}