package testsupport;

import apt.ICustomerService;
import java.util.ArrayList;
import java.util.List;
import apt.Customer;

public class CustomerServiceStub implements ICustomerService {
	private List<Customer> customers = new ArrayList<Customer>();

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	
	@Override
	public List<Customer> getCustomersToReceiveMailout() {
		return customers;
	}
	
	@Override
	public List<Customer> getAllCustomers() {
		return customers;
	}

	@Override
	public void close() { }
}
