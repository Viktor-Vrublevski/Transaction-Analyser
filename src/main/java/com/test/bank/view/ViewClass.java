package com.test.bank.view;

import com.test.bank.model.Transaction;
import com.test.bank.service.MainService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

public class ViewClass implements ActionListener {

    private JTextField path;
    private JTextField fromDate;
    private JTextField toDate;
    private JTextField merchant;
    private JLabel result;

    private MainService service;

    public ViewClass() {
        JFrame jFrame = new JFrame("Transaction Analyser");
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(500, 300);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        Container container = jFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        container.setLayout(layout);
        Component jLabel1 = new JLabel("Enter path to file");
         path = new JTextField(27);
        Component jLabel2 = new JLabel("from Date");
         fromDate = new JTextField("",10);
        Component jLabel3 = new JLabel("to Date");
         toDate = new JTextField("",10);
        Component jLabel4 = new JLabel("Merchant");
         merchant = new JTextField(20);
        JButton button = new JButton("Enter");
         result = new JLabel();
        button.setSize(30,10);

        container.add(jLabel1);
        container.add(path);
        container.add(jLabel2);
        container.add(fromDate);
        container.add(jLabel3);
        container.add(toDate);
        container.add(jLabel4);
        container.add(merchant);
        button.addActionListener(this);
        container.add(button);
        container.add(result);

        layout.putConstraint(SpringLayout.WEST,jLabel1,10,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,jLabel1,10,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST, path,120,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH, path,10,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST,jLabel2,47,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,jLabel2,50,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST,fromDate,120,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,fromDate,50,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST,jLabel3,256,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,jLabel3,50,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST,toDate,306,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,toDate,50,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST,jLabel4,47,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,jLabel4,90,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST, merchant,120,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH, merchant,90,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST,button,225,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,button,130,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST,result,50,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,result,180,SpringLayout.NORTH,container);


        jFrame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        DecimalFormat format = new DecimalFormat("#.##");
           if (e.getActionCommand().equals("Enter")) {
               service  = new MainService();
               if (service.isPath(path.getText())) {
                   if (!service.isMatcher(fromDate.getText().trim())){
                       result.setText("Fill in the field \"from Date\" correctly. For example: 10/10/2010 10:10:01");
                   }else {
                       if (!service.isMatcher(toDate.getText().trim())) {
                           result.setText("Fill in the field \"to Date\" correctly. For example: 10/10/2010 10:10:01");
                       }else {
                           if (service.isExistMerchant(merchant.getText(),path.getText())) {
                               List<Transaction> transactions = service.getResult(path.getText(), fromDate.getText(),
                                       toDate.getText(), merchant.getText());
                               int quantity = transactions.size();
                               double averageValue = service.getAverageValue(transactions);
                               String text = "Number of transactions = " + quantity
                                       + "    ||    Average Transaction Value = " + format.format(averageValue) + " $";
                               result.setText(text);
                           }else {
                               result.setText("This merchant not exist in list");
                           }
                       }
                   }
               } else {
                   result.setText("File not found");
               }
           }
    }
}
