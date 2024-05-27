package main.model;

import java.util.Date;

public class Bill {

    private static int billIdCounter = 1;
    private int billId;
    private String type;
    private long amount;
    private Date dueDate;
    private BillState state;
    private String provider;

    public Bill(String type, long amount, Date dueDate, String provider) {
        billIdCounter = 1;
        this.billId = billIdCounter++;
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.provider = provider;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BillState getState() {
        return state;
    }

    public void setState(BillState state) {
        this.state = state;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
