package com.test.bank.model;

import java.util.Date;

public class Transaction {

    private String ID;
    private Date date;
    private double amount;
    private String merchant;
    private boolean type;
    private String IDReversal;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getIDReversal() {
        return IDReversal;
    }

    public void setIDReversal(String IDReversal) {
        this.IDReversal = IDReversal;
    }
}
