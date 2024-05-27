package main.service;

import main.model.*;

import java.time.LocalDate;
import java.util.List;

public class PaymentService {

    public void payBill(Customer customer, Bill bill) {
        if (customer.getBalance() >= bill.getAmount()) {
            customer.setBalance(customer.getBalance() - bill.getAmount());
            bill.setState(BillState.PAID);
            Payment payment = new Payment(bill.getAmount(), LocalDate.now(), PaymentState.PROCESSED, bill.getBillId());
            customer.getPayments().add(payment);
            System.out.println("Payment completed for Bill with id " + bill.getBillId());
            System.out.println("Your current balance is: " + customer.getBalance());
        } else {
            System.out.println("Sorry! Not enough funds to proceed with payment.");
        }
    }

    public void payBills(Customer customer, List<Bill> bills) {
        long totalAmount = bills.stream().mapToLong(Bill::getAmount).sum();
        if (customer.getBalance() >= totalAmount) {
            for (Bill bill : bills) {
                customer.setBalance(customer.getBalance() - bill.getAmount());
                bill.setState(BillState.PAID);
                Payment payment = new Payment(bill.getAmount(), LocalDate.now(), PaymentState.PROCESSED, bill.getBillId());
                customer.getPayments().add(payment);
            }
            System.out.println("Payment completed for all bills.");
            System.out.println("Your current balance is: " + customer.getBalance());
        } else {
            System.out.println("Sorry! Not enough funds to proceed with payment.");
        }
    }
}
