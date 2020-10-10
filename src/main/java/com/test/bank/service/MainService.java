package com.test.bank.service;

import com.test.bank.model.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainService {

    private final ExecuteService execute = new ExecuteService();


    // This method returns a list by the requested parameters;
    public List<Transaction> getResult(String path, String fromDate, String toDate, String merchant ){
        List<Transaction> result = new ArrayList<Transaction>();
        List<Transaction> listReversal = new ArrayList<Transaction>();
        try {
            List<Transaction> transactions = execute.getListAllTransactions(path);
            for (Transaction tr : transactions){
                if (tr.getMerchant().equalsIgnoreCase(merchant.trim()) && isDate(tr.getDate(),fromDate,toDate)){
                    result.add(tr);
                }
                if (tr.getMerchant().equalsIgnoreCase(merchant.trim()) && !tr.isType()){
                    listReversal.add(tr);
                }
            }

        }catch (Exception ignored){}

        for (Transaction transaction : listReversal) {
            for (int j = 0; j < result.size(); j++) {
                if (transaction.getIDReversal().equals(result.get(j).getID())) {
                    result.remove(result.get(j));
                } else if (transaction.getID().equals(result.get(j).getID())) {
                    result.remove(result.get(j));
                }
            }
        }
        return result;
    }

    public boolean isPath(String path) {
        File file = new File(path);
        return file.exists();
    }


    public double getAverageValue(List<Transaction> list){
        double value=0;
        for (Transaction tr : list){
            value += tr.getAmount();
        }
        return value/list.size();
    }

    public boolean isMatcher(String str){
        return str.matches("[0-9]\\d/[0-9]\\d/[0-9]\\d{3} [0-9]\\d:[0-9]\\d:[0-9]\\d");
    }

    // This  method checks if the current date matches the search range
    public boolean isDate(Date current, String fromDate, String toDate){
        Date from = execute.getDate(fromDate);
        Date to = execute.getDate(toDate);
        return current.getTime() >= from.getTime() && current.getTime() <= to.getTime();
    }

    // This method checks if exist a merchant in list
    public boolean isExistMerchant(String merchant, String path){
        List<Transaction> transactions = execute.getListAllTransactions(path);
        boolean isExist = false;
        for (Transaction tr : transactions){
            if (tr.getMerchant().equals(merchant.trim())){
                isExist = true;
                break;
            }
        }
        return isExist;
    }
}