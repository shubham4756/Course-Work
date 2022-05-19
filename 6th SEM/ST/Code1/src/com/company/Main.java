package com.company;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Sci_Calculator gui = new Sci_Calculator();
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.pack();
                gui.setVisible(true);
            }
        });
    }
}