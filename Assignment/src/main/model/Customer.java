package main.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String customerId;
    private long balance;
    private List<Bill> bills;
    private List<Payment> payments;

    public Customer() {
        this.customerId = "ABCD";
        this.balance = 0;
        this.bills = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

}

