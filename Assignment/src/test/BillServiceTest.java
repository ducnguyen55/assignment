package test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import main.model.Bill;
import main.model.Customer;
import main.service.BillService;
import org.junit.Test;

public class BillServiceTest {

    @Test
    public void testCreateBill() {

        Customer customer = new Customer();
        BillService billService = new BillService();

        billService.createBill(customer, "Electricity", 2000, new Date(2024, 6, 1), "Utility Provider");

        assertEquals(1, customer.getBills().size());
        assertEquals("Electricity", customer.getBills().get(0).getType());
    }

    @Test
    public void testDeleteBill() {
        Customer customer = new Customer();
        BillService billService = new BillService();

        billService.createBill(customer, "Electricity", 2000, new Date(2024, 6, 1), "Utility Provider");
        billService.deleteBill(customer, 1);

        assertEquals(0, customer.getBills().size());
    }

    @Test
    public void testUpdateBill() {
        Customer customer = new Customer();
        BillService billService = new BillService();

        billService.createBill(customer, "Electricity", 2000, new Date(2024, 6, 1), "Utility Provider");
        Bill bill = customer.getBills().get(0);
        billService.updateBill(bill, 2500, new Date(2024, 6, 15));

        assertEquals(2500, bill.getAmount());
        assertEquals(new Date(2024, 6, 15), bill.getDueDate());
    }

    @Test
    public void testViewBill() {
        Customer customer = new Customer();
        BillService billService = new BillService();

        billService.createBill(customer, "Electricity", 2000, new Date(2024, 6, 1), "Utility Provider");
        billService.createBill(customer, "Water", 1500, new Date(2024, 6, 1), "Utility Provider");

        Bill bill = customer.getBills().get(0);
        assertEquals("Electricity", bill.getType());
        assertEquals(2000, bill.getAmount());
    }

    @Test
    public void testListBillsByDueDate() {
        Customer customer = new Customer();
        BillService billService = new BillService();

        billService.createBill(customer, "Electricity", 2000, new Date(2024, 6, 1), "Utility Provider");
        billService.createBill(customer, "Water", 1500, new Date(2024, 6 , 1), "Utility Provider");

        billService.listBillsByDueDate(customer);

        assertEquals(2, customer.getBills().size());
    }
}
