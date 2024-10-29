package developer.az.crmsystemapp.service.impl;

import developer.az.crmsystemapp.mapper.UserMapper;
import developer.az.crmsystemapp.model.User;
import developer.az.crmsystemapp.repository.UserRepository;
import developer.az.crmsystemapp.rest.model.request.UserDto;
import developer.az.crmsystemapp.rest.model.request.UserLoginDto;
import developer.az.crmsystemapp.rest.model.request.UserRegisterDto;
import developer.az.crmsystemapp.rest.model.response.UserResponse;
import developer.az.crmsystemapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    @Override
    public UserDto register(UserRegisterDto userRegisterDto) {
        User user = userMapper.toEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto login(UserLoginDto userLoginDto) {
        String email = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        User user = (User) userDetails;

        return new UserDto(user.getId(), user.getEmail(), user.getName(), user.getRole());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponse getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserDto> dtos =  users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return new UserResponse(dtos);
    }

    @Override
    public void updateUser(Long id, UserRegisterDto userRegisterDto) {

        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found: " +id));

        if (userRegisterDto.getName() != null){
            user.setName(userRegisterDto.getName());
        }
        if (userRegisterDto.getEmail() != null){
            user.setEmail(userRegisterDto.getEmail());
        }
        if (userRegisterDto.getRole() != null){
            user.setRole(userRegisterDto.getRole());
        }
        if (userRegisterDto.getPassword() != null){
            user.setPassword(userRegisterDto.getPassword());
        }

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
