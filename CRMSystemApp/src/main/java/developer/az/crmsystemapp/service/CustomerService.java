package developer.az.crmsystemapp.service;

import developer.az.crmsystemapp.rest.model.request.CustomerCreateDto;
import developer.az.crmsystemapp.rest.model.request.CustomerDto;
import developer.az.crmsystemapp.rest.model.response.CustomerResponse;

public interface CustomerService {

    CustomerResponse getAllCustomers();

    CustomerDto getCustomerById(Long id);

    void createCustomer(CustomerCreateDto customerCreateDto);

    void updateCustomer(Long id,CustomerCreateDto customerCreateDto);

    void deleteCustomer(Long id);
}
