package com.test.bank;

import com.test.bank.view.ViewClass;
import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;

public class MainClass {
    public static void main(String[] args) throws IOException, ParseException {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new ViewClass();
                }
            });
    }
}
