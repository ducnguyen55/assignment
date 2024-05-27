package test;

import static org.junit.Assert.assertEquals;

import main.model.Customer;
import main.service.CustomerService;
import org.junit.Test;

public class CustomerServiceTest {

    @Test
    public void testAddFunds() {
        Customer customer = new Customer();
        customer.setBalance(1000);

        CustomerService customerService = new CustomerService();
        customerService.addFunds(customer, 500);

        assertEquals(1500, customer.getBalance());
    }
}
