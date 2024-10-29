package developer.az.crmsystemapp.controller;

import developer.az.crmsystemapp.rest.model.request.CustomerCreateDto;
import developer.az.crmsystemapp.rest.model.request.CustomerDto;
import developer.az.crmsystemapp.rest.model.response.CustomerResponse;
import developer.az.crmsystemapp.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers")
public class CustomerController {

   private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<CustomerResponse> getAllCustomers(){
        CustomerResponse customerResponse = customerService.getAllCustomers();
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id){
        CustomerDto customerDto =  customerService.getCustomerById(id);
        return ResponseEntity.ok(customerDto);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerCreateDto createDto){
        customerService.createCustomer(createDto);
       return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id,
                                             @RequestBody CustomerCreateDto createDto){
        customerService.updateCustomer(id,createDto);
         return  ResponseEntity.ok("Customer updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){

        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok("Customer deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }
}
