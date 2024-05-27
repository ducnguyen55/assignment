package main.service;

import main.model.Customer;

public class CustomerService {

    public void addFunds(Customer customer, long amount) {
        customer.setBalance(customer.getBalance() + amount);
        System.out.println("Funds added successfully. Current balance: " + customer.getBalance());
    }
}
