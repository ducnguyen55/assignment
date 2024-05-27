package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Date;

import main.model.Bill;
import main.model.Customer;
import main.service.BillService;
import main.service.PaymentService;
import org.junit.Test;

public class PaymentServiceTest {

    @Test
    public void testPayBill() {
        Customer customer = new Customer();
        customer.setBalance(3000);
        BillService billService = new BillService();
        PaymentService paymentService = new PaymentService();

        billService.createBill(customer, "Electricity", 2000, new Date(2024, 6, 1), "Utility Provider");
        Bill bill = customer.getBills().get(0);

        paymentService.payBill(customer, bill);

        assertEquals(1000, customer.getBalance());
        assertEquals(1, customer.getPayments().size());
        assertEquals(2000, customer.getPayments().get(0).getAmount());
    }
}
