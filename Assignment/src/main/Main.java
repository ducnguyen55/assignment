package main;

import main.model.Customer;
import main.service.BillService;
import main.service.CustomerService;
import main.service.PaymentService;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        Customer customer = new Customer(); // Instantiate a customer object
        CustomerService customerService = new CustomerService();
        BillService billService = new BillService();
        PaymentService paymentService = new PaymentService();

        CommandLine cli = new CommandLine(customer, customerService, billService, paymentService);
        cli.start();
    }
}