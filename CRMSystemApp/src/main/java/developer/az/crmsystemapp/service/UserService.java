package developer.az.crmsystemapp.service;

import developer.az.crmsystemapp.rest.model.request.UserDto;
import developer.az.crmsystemapp.rest.model.request.UserLoginDto;
import developer.az.crmsystemapp.rest.model.request.UserRegisterDto;
import developer.az.crmsystemapp.rest.model.response.UserResponse;

import java.util.List;

public interface UserService {
    UserDto register(UserRegisterDto userRegisterDto);
    UserDto login(UserLoginDto userLoginDto);
    UserDto getUserById(Long id);
    UserResponse getAllUsers();
    void updateUser(Long id, UserRegisterDto userRegisterDto);
    void deleteUser(Long id); //
}
