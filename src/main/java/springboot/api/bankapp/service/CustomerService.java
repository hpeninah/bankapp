package springboot.api.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.api.bankapp.data.models.Customer;
import springboot.api.bankapp.data.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    //Return all customers
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    //Return customer by id
    public Customer getCustomer(Long customerId){
        return customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("Customer with ID: " + customerId + " does not exist."));
    }
    //Create a new customer
    public Customer createCustomer(Customer customer) { return customerRepository.save(customer); }

    //Update a customer
    @Transactional
    public Customer updateCustomer(Long customerId, Customer customer) {
        Customer updatedCustomer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("Customer with ID: " + customerId + " does not exist."));

        if(customer.getName() != null) {
            updatedCustomer.setName(customer.getName());
        }
        return customerRepository.save(updatedCustomer);
    }

    //Delete a customer
    public boolean deleteCustomer(Long customerId) {
        customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("Customer with ID: " + customerId + " does not exist."));

        try{
            customerRepository.deleteById(customerId);
        } catch(Exception exception) {
            System.out.println("Error: " + exception);
            return false;
        }

        return true;
    }
}
