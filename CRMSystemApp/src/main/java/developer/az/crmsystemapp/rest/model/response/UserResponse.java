package developer.az.crmsystemapp.rest.model.response;

import developer.az.crmsystemapp.rest.model.request.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

   private List<UserDto> users;


}
