package developer.az.crmsystemapp.service.impl;

import developer.az.crmsystemapp.mapper.CustomerMapper;
import developer.az.crmsystemapp.model.Customer;
import developer.az.crmsystemapp.repository.CustomerRepository;
import developer.az.crmsystemapp.rest.model.request.CustomerCreateDto;
import developer.az.crmsystemapp.rest.model.request.CustomerDto;
import developer.az.crmsystemapp.rest.model.response.CustomerResponse;
import developer.az.crmsystemapp.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper mapper;
    private final CustomerRepository repository;


    @Override
    public CustomerResponse getAllCustomers() {
        List<Customer> customerList = repository.findAll();

       return new CustomerResponse(customerList.stream()
               .map(mapper::toDto)
               .collect(Collectors.toList()));
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer =  repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id) );
        return mapper.toDto(customer);
    }

    @Override
    public void createCustomer(CustomerCreateDto customerCreateDto) {
        if (customerCreateDto == null) {
            throw new IllegalArgumentException("CustomerCreateDto cannot be null");
        }
        Customer customer = mapper.toEntity(customerCreateDto);
        repository.save(customer);
    }

    @Override
    public void updateCustomer(Long id, CustomerCreateDto customerCreateDto) {
        if (customerCreateDto == null) {
            throw new IllegalArgumentException("Customer details must be provided.");
        }

        Customer customer =  repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id) );

        if (customerCreateDto.getCompanyName() != null){
            customer.setCompanyName(customerCreateDto.getCompanyName());
        }
        if (customerCreateDto.getContactPersonName() != null){
            customer.setContactPersonName(customerCreateDto.getContactPersonName());
        }
        if (customerCreateDto.getPhoneNumber() != null){
            customer.setPhoneNumber(customerCreateDto.getPhoneNumber());
        }
        if (customerCreateDto.getUserId() != null){
            customer.setUserId(customerCreateDto.getUserId());
        }

        repository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
