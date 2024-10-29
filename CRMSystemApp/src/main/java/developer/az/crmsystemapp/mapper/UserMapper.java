package developer.az.crmsystemapp.mapper;

import developer.az.crmsystemapp.model.User;
import developer.az.crmsystemapp.rest.model.request.UserDto;
import developer.az.crmsystemapp.rest.model.request.UserLoginDto;
import developer.az.crmsystemapp.rest.model.request.UserRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRegisterDto userRegisterDto);

    UserDto toDto(User user);

    UserLoginDto toLoginDto(User user);

}
