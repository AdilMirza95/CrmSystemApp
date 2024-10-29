package developer.az.crmsystemapp.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;

    @NotBlank(message = "Company name is required.")
    private String companyName;

    @NotBlank(message = "Contact person's name is required.")
    private String contactPersonName;

    @NotBlank(message = "Phone number is required.")
    private String phoneNumber;

    private Long userId;
}
