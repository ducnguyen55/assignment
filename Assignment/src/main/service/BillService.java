package main.service;

import main.model.Bill;
import main.model.Customer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BillService {

    private List<Bill> bills;

    public BillService() {
        this.bills = new ArrayList<>();
    }

    public void createBill(Customer customer, String type, long amount, Date dueDate, String provider) {
        Bill bill = new Bill(type, amount, dueDate, provider);
        customer.getBills().add(bill);
        System.out.println("Bill created successfully. Bill ID: " + bill.getBillId());
    }

    public void deleteBill(Customer customer, int billId) {
        List<Bill> filteredBills = customer.getBills().stream()
                .filter(bill -> bill.getBillId() == billId)
                .toList();
        if (filteredBills.size() == 1) {
            customer.getBills().remove(filteredBills.get(0));
            System.out.println("Bill deleted successfully.");
        } else {
            System.out.println("No bill found with ID: " + billId);
        }
    }

    public void updateBill(Bill bill, long amount, Date dueDate) {
        bill.setAmount(amount);
        bill.setDueDate(dueDate);
        System.out.println("Bill updated successfully.");
    }

    public void viewBill(Customer customer, int billId) {
        List<Bill> filteredBills = customer.getBills().stream()
                .filter(bill -> bill.getBillId() == billId)
                .toList();
        if (filteredBills.size() == 1) {
            Bill bill = filteredBills.get(0);
            System.out.println("Bill ID: " + bill.getBillId() + ", Type: " + bill.getType() + ", Amount: "
                    + bill.getAmount() + ", Due Date: " + bill.getDueDate() + ", Provider: " + bill.getProvider());
        } else {
            System.out.println("No bill found with ID: " + billId);
        }
    }

    public void listBillsByDueDate(Customer customer) {
        List<Bill> sortedBills = customer.getBills().stream()
                .filter(bill -> bill.getDueDate().after(new Date()))
                .sorted(Comparator.comparing(Bill::getDueDate))
                .toList();

        if (!sortedBills.isEmpty()) {
            System.out.println("Bills due in the future:");
            for (Bill bill : sortedBills) {
                System.out.println("Bill ID: " + bill.getBillId() + ", Type: " + bill.getType() + ", Amount: "
                        + bill.getAmount() + ", Due Date: " + bill.getDueDate() + ", Provider: " + bill.getProvider());
            }
        } else {
            System.out.println("No bills due in the future.");
        }
    }

    public void searchBillByProvider(Customer customer, String provider) {
        List<Bill> filteredBills = customer.getBills().stream()
                .filter(bill -> bill.getProvider().equals(provider))
                .toList();
        if (!filteredBills.isEmpty()) {
            System.out.println("Bills provided by " + provider + ":");
            for (Bill bill : filteredBills) {
                System.out.println("Bill ID: " + bill.getBillId() + ", Type: " + bill.getType() + ", Amount: "
                        + bill.getAmount() + ", Due Date: " + bill.getDueDate() + ", Provider: " + bill.getProvider());
            }
        } else {
            System.out.println("No bills found provided by " + provider);
        }
    }
}
