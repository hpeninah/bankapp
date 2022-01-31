package springboot.api.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.api.bankapp.data.models.Customer;
import springboot.api.bankapp.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/controller")
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Long customerId) {
        try {
            return customerService.getCustomer(customerId);
        } catch(IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @PostMapping("")
    public Customer createCustomer(@RequestBody Customer customer) { return customerService.createCustomer(customer); }

    @PutMapping("/{accountId}")
    public Customer updateCustomer(@PathVariable("customerId") Long customerId, Customer customer) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
            return updatedCustomer;
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{customerId}")
    public boolean deleteCustomer(@PathVariable("customerId") Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return true;
        } catch(IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
