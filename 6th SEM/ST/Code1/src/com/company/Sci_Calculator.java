package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Sci_Calculator extends JFrame {
    private JButton AsinButton, AcosButton, AtanButton;
    private JButton PlusButton, MinusButton, MulButton, DivButton, Plus_Minus;
    private JButton X2Button, X3Button, XYButton, XInvButton;
    private JButton OpenBracket, CloseBracket;
    private JButton Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, PiButton;
    private JButton SinButton, CosButton, TanButton;
    private JButton nPrButton, nCrButton, NFactButton;
    private JButton TwoRoot, ThreeRoot, YRoot;
    private JButton DELButton, CLRButton, EXITButton, ANSButton;
    private JButton LogButton, Log10Button;
    private JButton TanhButton, CoshButton, SinhButton;
    private JButton ExpButton;
    private JButton Dot;
    private JTextField text;
    private javax.swing.JPanel JPanel;
    private JTextField AnsText;


    Sci_Calculator() {
        add(JPanel);
        setTitle("Scientific Calculator");
        setSize(800, 350);

        //Todo: digits and π
        One.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "1"); }
        });
        Two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "2"); }
        });
        Three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "3"); }
        });
        Four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "4"); }
        });
        Five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "5"); }
        });
        Six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "6"); }
        });
        Seven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "7"); }
        });
        Eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "8"); }
        });
        Nine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "9"); }
        });
        Zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "0"); }
        });
        PiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "π"); }
        });
        // --------------------------------------------------------------

        //Todo: Trigonometry Functions
        SinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "sin"); }
        });
        CosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "cos"); }
        });
        TanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "tan"); }
        });
        SinhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "sinh"); }
        });
        CoshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "cosh"); }
        });
        TanhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "tanh"); }
        });
        AsinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "asin"); }
        });
        AcosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "acos"); }
        });
        AtanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "atan"); }
        });
        // --------------------------------------------------------------


        //Todo: Root, Power, Inverse
        X2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText()+"^2"); }
        });
        X3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText()+"^3"); }
        });
        XYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText()+"^"); }
        });
        XInvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText()+"1/"); }
        });
        TwoRoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText()+"2√"); }
        });
        ThreeRoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText()+"3√"); }
        });
        YRoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText()+"√"); }
        });
        ExpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText()+"EXP"); }
        });
        // --------------------------------------------------------------

        //Todo: Log and Log10
        LogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "log"); }
        });
        Log10Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "log10"); }
        });
        // --------------------------------------------------------------


        //Todo: Dot and bracket
        OpenBracket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "("); }
        });
        CloseBracket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + ")"); }
        });
        Dot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "."); }
        });
        // --------------------------------------------------------------

        //Todo: nPr,nCr,n!
        nPrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "P"); }
        });
        nCrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "C"); }
        });
        NFactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "!"); }
        });
        // --------------------------------------------------------------

        //Todo: Operators
        PlusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "+"); }
        });
        MinusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "-"); }
        });
        MulButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "*"); }
        });
        DivButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "/"); }
        });
        Plus_Minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { text.setText(text.getText() + "±"); }
        });
        // --------------------------------------------------------------

        //Todo: Other Buttons
        DELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("");
            }
        });
        CLRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text.getText().length()>0) {
                    text.setText(text.getText().substring(0, text.getText().length() - 1));
                }
            }
        });
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ANSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CheckEquation(text.getText())) {
                    double Answer=EquationSolver(text.getText());
                    AnsText.setText(Double.toString(Answer));
                } else {
                    JOptionPane.showMessageDialog(JPanel, "Invalid Input");
                }
            }
        });
    }
    boolean CheckEquation(String eq) {
        // Your code goes here
        return true;
    }
    double EquationSolver(String eq) {
        double res=0.0;
        // Your code goes here
        return res;
    }
}