package com.test.bank.service;

import com.test.bank.model.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 This class execute function for get a list of Transaction;
*/
public class ExecuteService {

    //  List of all transactions from file CSV;
    private List<Transaction> transactions;

    // Getting list of all transactions from file CSV;
    public List<Transaction> getListAllTransactions(String path){
        transactions = new ArrayList<Transaction>();
        for (String line : getLineString(path)) {
            String[] array = getArray(line);
            if (array != null) {
                Transaction trans = new Transaction();
                trans.setID(array[0].trim());
                trans.setDate(getDate(array[1].trim()));
                trans.setAmount(Double.parseDouble(array[2].trim()));
                trans.setMerchant(array[3].trim());
                trans.setType(getBooleanFromString(array[4].trim()));
                if (trans.isType()) trans.setIDReversal(null);
                else trans.setIDReversal(array[5].trim());
                transactions.add(trans);
            }
        }
        return transactions;
    }

    // Reading data from file CSV;
    public List<String> getLineString(String path) {
        List<String> list = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        }catch (IOException ignored){}
        return list;
    }


    public Date getDate(String date) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return formatter.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public boolean getBooleanFromString(String str) {
        return str.equals("PAYMENT");
    }

    // Get   strings  from file CSV and put in array ;
    public String[] getArray(String line) {
        String[] array = line.split(",");

        // We find string containing "ID, Date, Amount, Merchant, Type, Related Transaction";
        if (array[1].trim().equalsIgnoreCase("Date")) {
            return null;
        }
        return array;
    }

}
