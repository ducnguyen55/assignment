package main;

import main.model.Bill;
import main.model.Customer;
import main.service.BillService;
import main.service.CustomerService;
import main.service.PaymentService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CommandLine {
    private Customer customer;
    private CustomerService customerService;
    private BillService billService;
    private PaymentService paymentService;

    public CommandLine(Customer customer, CustomerService customerService, BillService billService, PaymentService paymentService) {
        this.customer = customer;
        this.customerService = customerService;
        this.billService = billService;
        this.paymentService = paymentService;
    }

    public void start() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Payment Service!");

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            String command = parts[0];
            switch (command) {
                case "START":
                    System.out.println("START CONSOLE");
                    break;
                case "CASH_IN":
                    if (parts.length == 2) {
                        long amount = Long.parseLong(parts[1]);
                        customerService.addFunds(customer, amount);
                    } else {
                        System.out.println("Invalid command. Usage: CASH_IN <amount>");
                    }
                    break;
                case "CREATE_BILL":
                    if (parts.length == 5) {
                        String type = parts[1];
                        long amount = Long.parseLong(parts[2]);
                        Date dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(parts[3]);
                        String provider = parts[4];
                        billService.createBill(customer, type, amount, dueDate, provider);
                    } else {
                        System.out.println("Invalid command. Usage: CREATE_BILL <type> <amount> <dueDate(YYYY-MM-DD)> <provider>");
                    }
                    break;
                case "DELETE_BILL":
                    if (parts.length == 2) {
                        int billId = Integer.parseInt(parts[1]);
                        billService.deleteBill(customer, billId);
                    } else {
                        System.out.println("Invalid command. Usage: DELETE_BILL <billId>");
                    }
                    break;
                case "UPDATE_BILL":
                    if (parts.length == 4) {
                        int billId = Integer.parseInt(parts[1]);
                        long amount = Long.parseLong(parts[2]);
                        Date dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(parts[3]);
                        Bill bill = customer.getBills().stream()
                                .filter(b -> b.getBillId() == billId)
                                .findFirst()
                                .orElse(null);
                        if (bill != null) {
                            billService.updateBill(bill, amount, dueDate);
                        } else {
                            System.out.println("No bill found with ID: " + billId);
                        }
                    } else {
                        System.out.println("Invalid command. Usage: UPDATE_BILL <billId> <amount> <dueDate(YYYY-MM-DD)>");
                    }
                    break;
                case "VIEW_BILL":
                    if (parts.length == 2) {
                        int billId = Integer.parseInt(parts[1]);
                        billService.viewBill(customer, billId);
                    } else {
                        System.out.println("Invalid command. Usage: VIEW_BILL <billId>");
                    }
                    break;
                case "SEARCH_BILL_BY_PROVIDER":
                    if (parts.length == 2) {
                        String provider = parts[1];
                        billService.searchBillByProvider(customer, provider);
                    } else {
                        System.out.println("Invalid command. Usage: SEARCH_BILL_BY_PROVIDER <provider>");
                    }
                    break;
                case "PAY":
                    if (parts.length > 1) {
                        boolean allBillsPaid = true;
                        for (int i = 1; i < parts.length; i++) {
                            int billId = Integer.parseInt(parts[i]);
                            Bill bill = customer.getBills().stream()
                                    .filter(b -> b.getBillId() == billId)
                                    .findFirst()
                                    .orElse(null);
                            if (bill != null) {
                                paymentService.payBill(customer, bill);
                            } else {
                                allBillsPaid = false;
                                System.out.println("No bill found with ID: " + billId);
                            }
                        }
                        if (allBillsPaid) {
                            System.out.println("All bills paid successfully.");
                        } else {
                            System.out.println("Some bills could not be paid.");
                        }
                    } else {
                        System.out.println("Invalid command. Usage: PAY <billId1> <billId2> ...");
                    }
                    break;
                case "DUE_DATE":
                    billService.listBillsByDueDate(customer);
                    break;
                case "EXIT":
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
