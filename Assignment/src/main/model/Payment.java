package main.model;

import java.time.LocalDate;

public class Payment {

    private int paymentId;
    private long amount;
    private LocalDate paymentDate;
    private PaymentState state;
    private int billId;

    public Payment(long amount, LocalDate now, PaymentState paymentState, int billId) {
        this.amount = amount;
        this.paymentDate = now;
        this.state = paymentState;
        this.billId = billId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentState getState() {
        return state;
    }

    public void setState(PaymentState state) {
        this.state = state;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }
}
