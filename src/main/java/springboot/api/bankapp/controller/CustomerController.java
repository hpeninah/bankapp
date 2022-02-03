package springboot.api.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.api.bankapp.data.models.Customer;
import springboot.api.bankapp.exceptions.CustomerNotFoundException;
import springboot.api.bankapp.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
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
    public Customer getCustomerById(@PathVariable("customerId") Long customerId) throws CustomerNotFoundException {
        return customerService.getCustomer(customerId);
    }

    @PostMapping("")
    public Customer createCustomer(@RequestBody Customer customer) { return customerService.createCustomer(customer); }

    @PutMapping("/{accountId}")
    public Customer updateCustomer(@PathVariable("customerId") Long customerId, Customer customer) throws CustomerNotFoundException{
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/{customerId}")
    public boolean deleteCustomer(@PathVariable("customerId") Long customerId) throws CustomerNotFoundException{
        return customerService.deleteCustomer(customerId);
    }
}
