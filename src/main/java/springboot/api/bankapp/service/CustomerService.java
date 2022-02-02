package springboot.api.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.api.bankapp.data.models.Customer;
import springboot.api.bankapp.data.repository.CustomerRepository;
import springboot.api.bankapp.exceptions.CustomerNotFoundException;

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
    public Customer getCustomer(Long customerId) throws CustomerNotFoundException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer with ID: " + customerId + " does not exist."));
    }
    //Create a new customer
    public Customer createCustomer(Customer customer) { return customerRepository.save(customer); }

    //Update a customer
    @Transactional
    public Customer updateCustomer(Long customerId, Customer customer) throws CustomerNotFoundException{
        Customer updatedCustomer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer with ID: " + customerId + " does not exist."));

        if(customer.getName() != null) {
            updatedCustomer.setName(customer.getName());
        }

        if(customer.getEmail() != null) {
            updatedCustomer.setEmail(customer.getEmail());
        }
        return customerRepository.save(updatedCustomer);
    }

    //Delete a customer
    public boolean deleteCustomer(Long customerId) throws CustomerNotFoundException{
        customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer with ID: " + customerId + " does not exist."));
        customerRepository.deleteById(customerId);
        return true;
    }
}
