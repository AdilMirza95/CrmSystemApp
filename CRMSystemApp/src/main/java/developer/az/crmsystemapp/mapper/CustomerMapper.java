package developer.az.crmsystemapp.mapper;

import developer.az.crmsystemapp.model.Customer;
import developer.az.crmsystemapp.rest.model.request.CustomerCreateDto;
import developer.az.crmsystemapp.rest.model.request.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer toEntity(CustomerCreateDto customerCreateDto);

    CustomerDto toDto(Customer customer);


}
